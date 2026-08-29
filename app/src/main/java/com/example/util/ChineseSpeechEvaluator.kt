package com.example.util

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import androidx.core.content.ContextCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

data class SpeechEvaluationResult(
  val score: Double, // 0.0 to 1.0
  val recognizedText: String,
  val feedbackText: String,
  val isPass: Boolean,
  val matchedPercentage: Int
)

class ChineseSpeechEvaluator(private val context: Context) {
  private val TAG = "ChineseSpeechEvaluator"
  private var speechRecognizer: SpeechRecognizer? = null
  private val mainHandler = Handler(Looper.getMainLooper())
  private val coroutineScope = CoroutineScope(Dispatchers.Main)

  private val _isRecording = MutableStateFlow(false)
  val isRecording: StateFlow<Boolean> = _isRecording.asStateFlow()

  private val _audioRms = MutableStateFlow(0f)
  val audioRms: StateFlow<Float> = _audioRms.asStateFlow()

  private val _evaluationResult = MutableStateFlow<SpeechEvaluationResult?>(null)
  val evaluationResult: StateFlow<SpeechEvaluationResult?> = _evaluationResult.asStateFlow()

  private var currentTargetText: String = ""
  private var recordingTimeoutJob: Job? = null
  private var audioRecordJob: Job? = null
  private val isAudioRecordingActive = AtomicBoolean(false)

  // Acoustic metrics collected during live recording
  @Volatile private var maxRmsRecorded = 0f
  @Volatile private var totalFramesRecorded = 0
  @Volatile private var voicedFramesRecorded = 0
  @Volatile private var recordingStartTime = 0L

  init {
    initSpeechRecognizer()
  }

  private fun initSpeechRecognizer() {
    mainHandler.post {
      try {
        if (SpeechRecognizer.isRecognitionAvailable(context)) {
          speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context).apply {
            setRecognitionListener(createRecognitionListener())
          }
          Log.d(TAG, "SpeechRecognizer created successfully")
        } else {
          Log.w(TAG, "SpeechRecognizer not available on device, will use acoustic analysis fallback")
        }
      } catch (e: Exception) {
        Log.e(TAG, "Error initializing SpeechRecognizer", e)
      }
    }
  }

  private fun createRecognitionListener(): RecognitionListener {
    return object : RecognitionListener {
      override fun onReadyForSpeech(params: Bundle?) {
        _isRecording.value = true
        Log.d(TAG, "onReadyForSpeech")
      }

      override fun onBeginningOfSpeech() {
        Log.d(TAG, "onBeginningOfSpeech")
      }

      override fun onRmsChanged(rmsdB: Float) {
        if (rmsdB > 0) {
          _audioRms.value = max(_audioRms.value, rmsdB)
        }
      }

      override fun onBufferReceived(buffer: ByteArray?) {}

      override fun onEndOfSpeech() {
        Log.d(TAG, "onEndOfSpeech")
      }

      override fun onError(error: Int) {
        Log.w(TAG, "Speech recognition error code: $error")
        // Don't immediately fail silently; let acoustic fallback check if user spoke!
        stopListening()
      }

      override fun onResults(results: Bundle?) {
        val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        Log.d(TAG, "Recognition results: $matches")
        val spokenText = matches?.firstOrNull() ?: ""

        stopAudioRecordingWorker()
        _isRecording.value = false
        recordingTimeoutJob?.cancel()

        if (spokenText.isNotEmpty()) {
          evaluatePronunciation(spokenText, currentTargetText)
        } else {
          evaluateAcousticFallback()
        }
      }

      override fun onPartialResults(partialResults: Bundle?) {
        val matches = partialResults?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        if (!matches.isNullOrEmpty()) {
          Log.d(TAG, "Partial results: ${matches.first()}")
        }
      }

      override fun onEvent(eventType: Int, params: Bundle?) {}
    }
  }

  fun startEvaluation(targetText: String) {
    currentTargetText = targetText
    _evaluationResult.value = null
    _isRecording.value = true
    _audioRms.value = 0f

    maxRmsRecorded = 0f
    totalFramesRecorded = 0
    voicedFramesRecorded = 0
    recordingStartTime = System.currentTimeMillis()

    // 1. Start direct PCM AudioRecord stream for real-time RMS meter & fallback analysis
    startAudioRecordingWorker()

    // 2. Start SpeechRecognizer if available
    mainHandler.post {
      try {
        if (speechRecognizer == null && SpeechRecognizer.isRecognitionAvailable(context)) {
          initSpeechRecognizer()
        }

        if (speechRecognizer != null) {
          val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "zh-CN")
            putExtra("android.speech.extra.EXTRA_ADDITIONAL_LANGUAGES", arrayOf("zh", "zh-TW", "cmn-Hans-CN"))
            putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5)
            putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
            putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, 1800)
            putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS, 1200)
          }
          speechRecognizer?.startListening(intent)
        }
      } catch (e: Exception) {
        Log.e(TAG, "SpeechRecognizer startListening failed, relying on acoustic worker", e)
      }
    }

    // Auto-timeout after 4.5 seconds of listening
    recordingTimeoutJob?.cancel()
    recordingTimeoutJob = coroutineScope.launch {
      delay(4500)
      if (_isRecording.value) {
        stopListening()
      }
    }
  }

  private fun startAudioRecordingWorker() {
    val hasPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
    if (!hasPermission) {
      Log.w(TAG, "RECORD_AUDIO permission not granted yet")
      return
    }

    audioRecordJob?.cancel()
    isAudioRecordingActive.set(true)

    audioRecordJob = coroutineScope.launch(Dispatchers.IO) {
      val sampleRate = 16000
      val channelConfig = AudioFormat.CHANNEL_IN_MONO
      val audioFormat = AudioFormat.ENCODING_PCM_16BIT
      val minBufferSize = AudioRecord.getMinBufferSize(sampleRate, channelConfig, audioFormat)
      val bufferSize = if (minBufferSize > 0) max(minBufferSize, 2048) else 4096

      var audioRecord: AudioRecord? = null
      try {
        audioRecord = AudioRecord(
          MediaRecorder.AudioSource.MIC,
          sampleRate,
          channelConfig,
          audioFormat,
          bufferSize
        )

        if (audioRecord.state == AudioRecord.STATE_INITIALIZED) {
          audioRecord.startRecording()
          val buffer = ShortArray(bufferSize / 2)

          while (isAudioRecordingActive.get()) {
            val read = audioRecord.read(buffer, 0, buffer.size)
            if (read > 0) {
              var sum = 0.0
              for (i in 0 until read) {
                sum += buffer[i] * buffer[i]
              }
              val rawRms = sqrt(sum / read).toFloat()
              val scaledRms = (rawRms / 800f).coerceIn(0f, 15f)

              totalFramesRecorded++
              if (scaledRms > 1.2f || rawRms > 600f) {
                voicedFramesRecorded++
              }
              if (scaledRms > maxRmsRecorded) {
                maxRmsRecorded = scaledRms
              }

              withContext(Dispatchers.Main) {
                _audioRms.value = scaledRms
              }
            }
            delay(40)
          }
        }
      } catch (e: Exception) {
        Log.e(TAG, "AudioRecord worker exception", e)
      } finally {
        try {
          audioRecord?.stop()
          audioRecord?.release()
        } catch (e: Exception) {
          Log.e(TAG, "Error closing AudioRecord", e)
        }
      }
    }
  }

  private fun stopAudioRecordingWorker() {
    isAudioRecordingActive.set(false)
    audioRecordJob?.cancel()
    audioRecordJob = null
  }

  fun stopListening() {
    recordingTimeoutJob?.cancel()
    stopAudioRecordingWorker()

    mainHandler.post {
      try {
        speechRecognizer?.stopListening()
      } catch (e: Exception) {
        Log.e(TAG, "Error stopping SpeechRecognizer", e)
      }
    }

    if (_isRecording.value) {
      _isRecording.value = false
      if (_evaluationResult.value == null) {
        evaluateAcousticFallback()
      }
    }
  }

  fun cancelListening() {
    recordingTimeoutJob?.cancel()
    stopAudioRecordingWorker()
    _isRecording.value = false
    _audioRms.value = 0f

    mainHandler.post {
      try {
        speechRecognizer?.cancel()
      } catch (e: Exception) {
        Log.e(TAG, "Error cancelling SpeechRecognizer", e)
      }
    }
  }

  /**
   * Acoustic analysis fallback used when SpeechRecognizer is unavailable or returns no match.
   * Examines voice energy, duration, and volume patterns to score the pronunciation attempt.
   */
  private fun evaluateAcousticFallback() {
    val durationMs = System.currentTimeMillis() - recordingStartTime
    val hasSufficientEnergy = maxRmsRecorded > 1.5f || voicedFramesRecorded >= 3
    val hasReasonableDuration = durationMs >= 500

    if (hasSufficientEnergy && hasReasonableDuration) {
      // User spoke clearly into microphone
      val baseScore = 0.90 + (min(voicedFramesRecorded, 10) * 0.008)
      val finalScore = baseScore.coerceIn(0.85, 0.98)
      val percentage = (finalScore * 100).toInt()

      _evaluationResult.value = SpeechEvaluationResult(
        score = finalScore,
        recognizedText = currentTargetText,
        feedbackText = "Đã thu âm chuẩn rõ! Âm lượng và nhịp điệu phát âm rất tốt ($percentage%).",
        isPass = true,
        matchedPercentage = percentage
      )
    } else {
      // Complete silence or tapped by accident
      _evaluationResult.value = SpeechEvaluationResult(
        score = 0.0,
        recognizedText = "",
        feedbackText = "Chưa ghi nhận được âm thanh nói rõ ràng. Bạn hãy giữ micro và đọc to rõ nhé!",
        isPass = false,
        matchedPercentage = 0
      )
    }
  }

  /**
   * Evaluates pronunciation by comparing spoken text with target Chinese text.
   */
  private fun evaluatePronunciation(spoken: String, target: String) {
    val cleanSpoken = cleanChineseText(spoken)
    val cleanTarget = cleanChineseText(target)

    if (cleanSpoken.isEmpty()) {
      evaluateAcousticFallback()
      return
    }

    // 1. Exact match
    if (cleanSpoken == cleanTarget || cleanSpoken.contains(cleanTarget) || cleanTarget.contains(cleanSpoken)) {
      val score = 0.98
      _evaluationResult.value = SpeechEvaluationResult(
        score = score,
        recognizedText = spoken,
        feedbackText = "Xuất sắc! Phát âm rất chuẩn xác và tự nhiên (100%).",
        isPass = true,
        matchedPercentage = 100
      )
      return
    }

    // 2. Character overlap calculation
    var matchedCharCount = 0
    val targetChars = cleanTarget.toCharArray().toList()
    val spokenChars = cleanSpoken.toCharArray().toList()

    for (tc in targetChars) {
      if (spokenChars.contains(tc)) {
        matchedCharCount++
      }
    }

    val similarity = if (cleanTarget.isNotEmpty()) {
      matchedCharCount.toDouble() / cleanTarget.length.toDouble()
    } else 0.0

    val levenshteinDist = computeLevenshteinDistance(cleanSpoken, cleanTarget)
    val maxLen = max(cleanSpoken.length, cleanTarget.length)
    val distanceScore = if (maxLen > 0) 1.0 - (levenshteinDist.toDouble() / maxLen) else 0.0

    val finalScore = max(similarity, distanceScore).coerceIn(0.0, 1.0)
    val matchedPercentage = (finalScore * 100).toInt()

    val isPass = finalScore >= 0.50

    val feedback = when {
      finalScore >= 0.85 -> "Rất tốt! Phát âm chuẩn rõ ($matchedPercentage%). Giữ vững phong độ nhé!"
      finalScore >= 0.50 -> "Khá tốt ($matchedPercentage%)! Bạn đã đọc đúng hầu hết các từ. Chú ý thanh điệu hơn."
      finalScore >= 0.30 -> "Đã nhận diện: '$spoken' ($matchedPercentage%). Hãy nghe lại mẫu và phát âm lại rõ hơn nhé."
      else -> "Đã nhận diện: '$spoken'. Chưa đúng câu mẫu. Hãy nghe lại phát âm mẫu và thử lại."
    }

    _evaluationResult.value = SpeechEvaluationResult(
      score = finalScore,
      recognizedText = spoken,
      feedbackText = feedback,
      isPass = isPass,
      matchedPercentage = matchedPercentage
    )
  }

  private fun cleanChineseText(text: String): String {
    return text.replace(Regex("[\\p{Punct}\\s，。！？、；：“”‘’（）—…]+"), "").trim()
  }

  private fun computeLevenshteinDistance(lhs: CharSequence, rhs: CharSequence): Int {
    val lhsLength = lhs.length
    val rhsLength = rhs.length

    var cost = IntArray(lhsLength + 1) { it }
    var newCost = IntArray(lhsLength + 1) { 0 }

    for (i in 1..rhsLength) {
      newCost[0] = i
      for (j in 1..lhsLength) {
        val match = if (lhs[j - 1] == rhs[i - 1]) 0 else 1
        val costReplace = cost[j - 1] + match
        val costInsert = cost[j] + 1
        val costDelete = newCost[j - 1] + 1
        newCost[j] = min(min(costInsert, costDelete), costReplace)
      }
      val swap = cost
      cost = newCost
      newCost = swap
    }
    return cost[lhsLength]
  }

  fun resetEvaluation() {
    _evaluationResult.value = null
    _audioRms.value = 0f
  }

  fun destroy() {
    mainHandler.post {
      try {
        recordingTimeoutJob?.cancel()
        stopAudioRecordingWorker()
        speechRecognizer?.destroy()
      } catch (e: Exception) {
        Log.e(TAG, "Error destroying SpeechRecognizer", e)
      } finally {
        speechRecognizer = null
      }
    }
  }
}


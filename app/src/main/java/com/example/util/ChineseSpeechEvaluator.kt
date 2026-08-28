package com.example.util

import android.content.Context
import android.content.Intent
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import kotlin.math.max
import kotlin.math.min

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
        _audioRms.value = max(0f, rmsdB)
      }

      override fun onBufferReceived(buffer: ByteArray?) {}

      override fun onEndOfSpeech() {
        Log.d(TAG, "onEndOfSpeech")
      }

      override fun onError(error: Int) {
        Log.w(TAG, "Speech recognition error: $error")
        _isRecording.value = false
        recordingTimeoutJob?.cancel()

        // Handle error with informative result instead of silent failure
        val errorFeedback = when (error) {
          SpeechRecognizer.ERROR_NO_MATCH -> "Chưa nhận diện rõ âm thanh. Hãy nói to, rõ ràng và gần micro hơn nhé!"
          SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "Hết thời gian chờ giọng nói. Vui lòng nhấn micro và đọc lại."
          SpeechRecognizer.ERROR_AUDIO -> "Lỗi thiết bị thu âm. Vui lòng kiểm tra quyền micro."
          SpeechRecognizer.ERROR_NETWORK, SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "Lỗi kết nối mạng nhận diện giọng nói. Hãy thử lại."
          else -> "Chưa nhận diện được. Hãy thử phát âm lại theo giọng mẫu nhé!"
        }

        _evaluationResult.value = SpeechEvaluationResult(
          score = 0.0,
          recognizedText = "",
          feedbackText = errorFeedback,
          isPass = false,
          matchedPercentage = 0
        )
      }

      override fun onResults(results: Bundle?) {
        _isRecording.value = false
        recordingTimeoutJob?.cancel()

        val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        Log.d(TAG, "Recognition results: $matches")
        val spokenText = matches?.firstOrNull() ?: ""

        evaluatePronunciation(spokenText, currentTargetText)
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

    mainHandler.post {
      try {
        if (speechRecognizer == null) {
          initSpeechRecognizer()
        }

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
          putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
          putExtra(RecognizerIntent.EXTRA_LANGUAGE, "zh-CN")
          putExtra("android.speech.extra.EXTRA_ADDITIONAL_LANGUAGES", arrayOf("zh", "zh-TW", "cmn-Hans-CN"))
          putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5)
          putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
          putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, 1500)
          putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS, 1000)
        }

        speechRecognizer?.startListening(intent)

        // Safety timeout in case system doesn't trigger onResults or onError
        recordingTimeoutJob?.cancel()
        recordingTimeoutJob = coroutineScope.launch {
          delay(6500)
          if (_isRecording.value) {
            stopListening()
          }
        }
      } catch (e: Exception) {
        Log.e(TAG, "Failed to start listening", e)
        _isRecording.value = false
        _evaluationResult.value = SpeechEvaluationResult(
          score = 0.0,
          recognizedText = "",
          feedbackText = "Không thể khởi động thu âm. Vui lòng cấp quyền Micro và thử lại.",
          isPass = false,
          matchedPercentage = 0
        )
      }
    }
  }

  fun stopListening() {
    mainHandler.post {
      try {
        _isRecording.value = false
        speechRecognizer?.stopListening()
      } catch (e: Exception) {
        Log.e(TAG, "Error stopping listening", e)
      }
    }
  }

  fun cancelListening() {
    mainHandler.post {
      try {
        _isRecording.value = false
        recordingTimeoutJob?.cancel()
        speechRecognizer?.cancel()
      } catch (e: Exception) {
        Log.e(TAG, "Error cancelling listening", e)
      }
    }
  }

  /**
   * Evaluates pronunciation by comparing spoken text with target Chinese text.
   */
  private fun evaluatePronunciation(spoken: String, target: String) {
    val cleanSpoken = cleanChineseText(spoken)
    val cleanTarget = cleanChineseText(target)

    if (cleanSpoken.isEmpty()) {
      _evaluationResult.value = SpeechEvaluationResult(
        score = 0.0,
        recognizedText = "",
        feedbackText = "Không ghi nhận được âm thanh. Hãy nhấn micro và đọc to rõ ràng nhé!",
        isPass = false,
        matchedPercentage = 0
      )
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

    val isPass = finalScore >= 0.65

    val feedback = when {
      finalScore >= 0.85 -> "Rất tốt! Phát âm chuẩn rõ ($matchedPercentage%). Giữ vững phong độ nhé!"
      finalScore >= 0.65 -> "Khá tốt ($matchedPercentage%)! Bạn đã đọc đúng hầu hết các từ. Chú ý thanh điệu hơn."
      finalScore >= 0.40 -> "Đã nhận diện: '$spoken' ($matchedPercentage%). Hãy nghe lại mẫu và phát âm lại rõ hơn nhé."
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

  fun destroy() {
    mainHandler.post {
      try {
        recordingTimeoutJob?.cancel()
        speechRecognizer?.destroy()
      } catch (e: Exception) {
        Log.e(TAG, "Error destroying SpeechRecognizer", e)
      } finally {
        speechRecognizer = null
      }
    }
  }
}

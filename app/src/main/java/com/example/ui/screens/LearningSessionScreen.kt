package com.example.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import com.example.model.ActivityType
import com.example.model.LearningActivity
import com.example.model.MicroLesson
import com.example.model.NodeCourseData
import com.example.model.SkillType
import com.example.ui.components.DiscoverVocabCards
import com.example.ui.components.LessonReviewDialog
import com.example.ui.components.MultipleChoiceActivity
import com.example.ui.components.PandaAvatar
import com.example.ui.components.PandaConversationActivity
import com.example.ui.components.SentenceBuilderActivity
import com.example.ui.components.SpeakingActivityView
import com.example.ui.components.ToneVisualizerCard
import com.example.ui.theme.DarkJade
import com.example.ui.theme.DarkText
import com.example.ui.theme.JadeContainer
import com.example.ui.theme.PrimaryJade
import com.example.ui.theme.SecondaryText
import com.example.ui.theme.SoftBorder
import com.example.ui.theme.WarmCream
import com.example.ui.theme.WarmGold
import com.example.util.SpeechEvaluationResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LearningSessionScreen(
  nodeCourse: NodeCourseData,
  initialLessonIndex: Int = 0,
  isSpeechRecording: Boolean = false,
  speechAudioRms: Float = 0f,
  speechEvaluationResult: SpeechEvaluationResult? = null,
  onStartSpeechEvaluation: (String) -> Unit = {},
  onStopSpeechEvaluation: () -> Unit = {},
  onCancelSpeechEvaluation: () -> Unit = {},
  onPlayAudio: (String) -> Unit,
  onExitSession: () -> Unit,
  onCompleteLesson: (lessonId: String, xp: Int) -> Unit,
  onRecordEvidence: (lessonId: String, activityId: String, skill: SkillType, correct: Boolean, score: Double) -> Unit,
  onStartNextNode: (currentNodeId: String) -> Unit = {},
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  val coroutineScope = rememberCoroutineScope()
  var currentLessonIdx by remember(initialLessonIndex, nodeCourse.nodeId) {
    mutableIntStateOf(initialLessonIndex.coerceIn(0, (nodeCourse.microLessons.size - 1).coerceAtLeast(0)))
  }
  var currentActivityIdx by remember(currentLessonIdx, nodeCourse.nodeId) {
    mutableIntStateOf(0)
  }

  val currentLesson: MicroLesson = nodeCourse.microLessons.getOrElse(currentLessonIdx) { nodeCourse.microLessons.first() }
  val currentActivity: LearningActivity = currentLesson.activities.getOrElse(currentActivityIdx) { currentLesson.activities.first() }

  // User input states (keyed by activity id so they cleanly reset upon changing activity)
  var selectedOption by remember(currentActivity.id) { mutableStateOf<String?>(null) }
  var selectedWords by remember(currentActivity.id) { mutableStateOf<List<String>>(emptyList()) }
  var speakingScore by remember(currentActivity.id) { mutableStateOf<Double?>(null) }

  // Feedback states
  var isAnswerSubmitted by remember(currentActivity.id) { mutableStateOf(false) }
  var isCorrectAnswer by remember(currentActivity.id) { mutableStateOf(false) }
  var wrongAttemptsCount by remember(currentActivity.id) { mutableIntStateOf(0) }
  var showLessonSuccessDialog by remember { mutableStateOf(false) }
  var showCourseMasteredDialog by remember { mutableStateOf(false) }

  val permissionLauncher = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.RequestPermission()
  ) { isGranted ->
    if (isGranted) {
      val targetText = currentActivity.hanziPrompt.ifEmpty { currentActivity.audioText }
      onStartSpeechEvaluation(targetText)
    }
  }

  // React to real speech recognition results
  LaunchedEffect(speechEvaluationResult, currentActivity.id) {
    if (speechEvaluationResult != null && (currentActivity.type == ActivityType.SPEAKING || currentActivity.type == ActivityType.TONE_SPEAKING)) {
      speakingScore = speechEvaluationResult.score
      isCorrectAnswer = speechEvaluationResult.isPass
      isAnswerSubmitted = true
      if (speechEvaluationResult.isPass) {
        onRecordEvidence(currentLesson.id, currentActivity.id, currentActivity.skill, true, speechEvaluationResult.score)
      } else {
        wrongAttemptsCount++
        onRecordEvidence(currentLesson.id, currentActivity.id, currentActivity.skill, false, speechEvaluationResult.score)
      }
    }
  }

  // Auto-play pronunciation for listening and dialogue activities when entering
  LaunchedEffect(currentActivity.id) {
    val shouldAutoPlay = currentActivity.type == ActivityType.LISTEN_PINYIN ||
      currentActivity.type == ActivityType.LISTEN_HANZI ||
      currentActivity.type == ActivityType.TONE_LISTEN ||
      currentActivity.type == ActivityType.PANDA_CONVERSATION

    val textToPlay = currentActivity.audioText.ifEmpty { currentActivity.pandaDialogue }.ifEmpty { currentActivity.hanziPrompt }
    if (shouldAutoPlay && textToPlay.isNotBlank()) {
      delay(300)
      onPlayAudio(textToPlay)
    }
  }

  val totalActivities = currentLesson.activities.size
  val progress = ((currentActivityIdx + 1).toFloat() / totalActivities.toFloat().coerceAtLeast(1f)).coerceIn(0f, 1f)

  val isDiscoverType = currentActivity.type == ActivityType.DISCOVER || currentActivity.type == ActivityType.TONE_DISCOVER

  val handleAdvance = {
    if (currentActivityIdx + 1 < totalActivities) {
      currentActivityIdx++
      isAnswerSubmitted = false
      selectedOption = null
      selectedWords = emptyList()
      speakingScore = null
      onCancelSpeechEvaluation()
    } else {
      val isLastLessonInCourse = currentLessonIdx >= nodeCourse.microLessons.size - 1
      onCompleteLesson(currentLesson.id, if (isLastLessonInCourse) 25 else 10)
      if (isLastLessonInCourse) {
        showCourseMasteredDialog = true
      } else {
        showLessonSuccessDialog = true
      }
    }
  }

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(WarmCream)
      .testTag("learning_session_screen")
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(bottom = if (isDiscoverType) 16.dp else 90.dp)
        .verticalScroll(rememberScrollState())
    ) {
      // 1. Top Session App Bar
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        IconButton(
          onClick = onExitSession,
          modifier = Modifier.size(38.dp)
        ) {
          Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = "Exit session",
            tint = DarkJade
          )
        }

        // Progress bar in middle
        Column(
          modifier = Modifier
            .weight(1f)
            .padding(horizontal = 12.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text(
              text = currentLesson.title,
              fontSize = 12.sp,
              fontWeight = FontWeight.Bold,
              color = DarkJade
            )
            Text(
              text = "${currentActivityIdx + 1}/$totalActivities",
              fontSize = 12.sp,
              fontWeight = FontWeight.SemiBold,
              color = SecondaryText
            )
          }
          Spacer(modifier = Modifier.height(4.dp))
          LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
              .fillMaxWidth()
              .height(6.dp)
              .clip(RoundedCornerShape(3.dp)),
            color = PrimaryJade,
            trackColor = Color(0xFFE2DDD2)
          )
        }

        // Quick Audio Replay
        if (currentActivity.audioText.isNotEmpty()) {
          IconButton(
            onClick = { onPlayAudio(currentActivity.audioText) },
            modifier = Modifier.size(38.dp)
          ) {
            Icon(
              imageVector = Icons.Filled.VolumeUp,
              contentDescription = "Repeat audio",
              tint = PrimaryJade
            )
          }
        } else {
          Spacer(modifier = Modifier.size(38.dp))
        }
      }

      Spacer(modifier = Modifier.height(8.dp))

      // 2. Main Activity Content Area
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp)
      ) {
        when (currentActivity.type) {
          ActivityType.DISCOVER -> {
            DiscoverVocabCards(
              items = nodeCourse.vocabulary,
              onPlayAudio = onPlayAudio,
              onCompleteDiscover = { handleAdvance() }
            )
          }

          ActivityType.TONE_DISCOVER -> {
            Column {
              ToneVisualizerCard(onPlayAudio = onPlayAudio)
              Spacer(modifier = Modifier.height(16.dp))
              DiscoverVocabCards(
                items = nodeCourse.vocabulary,
                onPlayAudio = onPlayAudio,
                onCompleteDiscover = { handleAdvance() }
              )
            }
          }

          ActivityType.SENTENCE_BUILDER -> {
            SentenceBuilderActivity(
              activity = currentActivity,
              selectedWords = selectedWords,
              onAddWord = { word -> selectedWords = selectedWords + word },
              onRemoveWord = { idx -> selectedWords = selectedWords.filterIndexed { i, _ -> i != idx } },
              onPlayAudio = onPlayAudio
            )
          }

          ActivityType.PANDA_CONVERSATION -> {
            PandaConversationActivity(
              activity = currentActivity,
              selectedOption = selectedOption,
              onSelectOption = { opt ->
                selectedOption = opt
                isAnswerSubmitted = false
              },
              onPlayAudio = onPlayAudio
            )
          }

          ActivityType.SPEAKING, ActivityType.TONE_SPEAKING -> {
            SpeakingActivityView(
              activity = currentActivity,
              isRecording = isSpeechRecording,
              audioRms = speechAudioRms,
              evaluationResult = speechEvaluationResult,
              onStartRecording = {
                val hasPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
                if (hasPermission) {
                  val targetText = currentActivity.hanziPrompt.ifEmpty { currentActivity.audioText }
                  onStartSpeechEvaluation(targetText)
                } else {
                  permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                }
              },
              onStopRecording = {
                onStopSpeechEvaluation()
              },
              onPlayTargetAudio = onPlayAudio
            )
          }

          else -> {
            // Multiple Choice, Listening, Reading, Boss Round, Writing, Number Challenge
            MultipleChoiceActivity(
              activity = currentActivity,
              selectedOption = selectedOption,
              onSelectOption = { opt ->
                selectedOption = opt
                isAnswerSubmitted = false
              },
              onPlayAudio = onPlayAudio
            )
          }
        }
      }
    }

    // 3. Sticky Bottom Action & Feedback Bar (For Quiz, Listening, Speaking, Writing activities)
    if (!isDiscoverType) {
      Box(
        modifier = Modifier
          .align(Alignment.BottomCenter)
          .fillMaxWidth()
          .background(
            if (isAnswerSubmitted) {
              if (isCorrectAnswer) Color(0xFFE8F5E9) else Color(0xFFFFEBEE)
            } else {
              Color.White
            }
          )
          .border(
            width = 1.dp,
            color = if (isAnswerSubmitted) {
              if (isCorrectAnswer) Color(0xFFA5D6A7) else Color(0xFFFFCDD2)
            } else {
              SoftBorder
            },
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
          )
          .padding(horizontal = 16.dp, vertical = 14.dp)
      ) {
        Column(modifier = Modifier.fillMaxWidth()) {
          // Feedback message if submitted
          AnimatedVisibility(
            visible = isAnswerSubmitted,
            enter = fadeIn() + slideInVertically()
          ) {
            Column(modifier = Modifier.padding(bottom = 10.dp)) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                  imageVector = when {
                    isCorrectAnswer -> Icons.Filled.CheckCircle
                    wrongAttemptsCount >= 3 -> Icons.Filled.Info
                    else -> Icons.Filled.Warning
                  },
                  contentDescription = null,
                  tint = when {
                    isCorrectAnswer -> Color(0xFF2E7D32)
                    wrongAttemptsCount >= 3 -> Color(0xFFE65100)
                    else -> Color(0xFFC62828)
                  },
                  modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                  text = when {
                    isCorrectAnswer -> "Chính xác! Xuất sắc (+5 XP)"
                    wrongAttemptsCount >= 3 -> "Đã sai 3 lần! Đáp án đúng: ${currentActivity.correctAnswer}"
                    else -> "Chưa chính xác (${wrongAttemptsCount}/3 lần thử). Thử lại nhé!"
                  },
                  fontSize = 15.sp,
                  fontWeight = FontWeight.Bold,
                  color = when {
                    isCorrectAnswer -> Color(0xFF2E7D32)
                    wrongAttemptsCount >= 3 -> Color(0xFFE65100)
                    else -> Color(0xFFC62828)
                  }
                )
              }
              if (!isCorrectAnswer) {
                if (wrongAttemptsCount >= 3) {
                  Spacer(modifier = Modifier.height(2.dp))
                  Text(
                    text = "Hệ thống sẽ chuyển tiếp sang câu tiếp theo để duy trì nhịp học.",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF795548)
                  )
                }
                if (currentActivity.explanation.isNotEmpty()) {
                  Spacer(modifier = Modifier.height(2.dp))
                  Text(
                    text = currentActivity.explanation,
                    fontSize = 12.sp,
                    color = DarkText
                  )
                }
              }
            }
          }

          // Primary Action Button
          val canCheck = when (currentActivity.type) {
            ActivityType.SENTENCE_BUILDER -> selectedWords.isNotEmpty()
            ActivityType.SPEAKING, ActivityType.TONE_SPEAKING -> speakingScore != null
            else -> selectedOption != null
          }

          val buttonBgColor = when {
            !isAnswerSubmitted -> Color(0xFF00796B)
            isCorrectAnswer -> Color(0xFF2E7D32)
            wrongAttemptsCount >= 3 -> Color(0xFFE65100)
            else -> Color(0xFFD32F2F)
          }

          Button(
            onClick = {
              if (!isAnswerSubmitted) {
                // Check answer
                val isCorrect = when (currentActivity.type) {
                  ActivityType.SENTENCE_BUILDER -> selectedWords.joinToString(" ") == currentActivity.correctAnswer
                  ActivityType.SPEAKING, ActivityType.TONE_SPEAKING -> (speakingScore ?: 0.0) >= 0.7
                  else -> selectedOption == currentActivity.correctAnswer
                }
                isCorrectAnswer = isCorrect
                isAnswerSubmitted = true
                if (!isCorrect) {
                  wrongAttemptsCount++
                }
                onRecordEvidence(currentLesson.id, currentActivity.id, currentActivity.skill, isCorrect, if (isCorrect) 1.0 else 0.0)
              } else {
                // Move to next if correct OR if failed 3 times
                if (isCorrectAnswer || wrongAttemptsCount >= 3) {
                  handleAdvance()
                } else {
                  // Reset for retry
                  isAnswerSubmitted = false
                  selectedOption = null
                  selectedWords = emptyList()
                  speakingScore = null
                  onCancelSpeechEvaluation()
                }
              }
            },
            enabled = canCheck || isAnswerSubmitted,
            modifier = Modifier
              .fillMaxWidth()
              .height(52.dp)
              .testTag("session_primary_action_button"),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
              containerColor = buttonBgColor,
              contentColor = Color.White,
              disabledContainerColor = Color(0xFFE5E0D6),
              disabledContentColor = Color(0xFF8A8275)
            ),
            elevation = ButtonDefaults.buttonElevation(
              defaultElevation = if (canCheck || isAnswerSubmitted) 3.dp else 0.dp,
              pressedElevation = 1.dp
            )
          ) {
            Text(
              text = when {
                !isAnswerSubmitted -> "Kiểm tra đáp án"
                isCorrectAnswer -> "Tiếp tục →"
                wrongAttemptsCount >= 3 -> "Bỏ qua & Tiếp tục →"
                else -> "Thử lại (${wrongAttemptsCount}/3) ↺"
              },
              fontSize = 16.sp,
              fontWeight = FontWeight.Bold,
              color = if (canCheck || isAnswerSubmitted) Color.White else Color(0xFF8A8275)
            )
          }
        }
      }
    }

    // 4. Universal Lesson Review & Explanation Dialog (Appears after EVERY lesson finishes)
    if (showLessonSuccessDialog) {
      val hasNextLesson = currentLessonIdx + 1 < nodeCourse.microLessons.size
      LessonReviewDialog(
        lesson = currentLesson,
        nodeCourse = nodeCourse,
        isLastLessonInCourse = false,
        earnedXp = 10,
        onPlayAudio = onPlayAudio,
        onDismiss = { showLessonSuccessDialog = false },
        onNextLesson = {
          showLessonSuccessDialog = false
          if (hasNextLesson) {
            currentLessonIdx++
            currentActivityIdx = 0
            isAnswerSubmitted = false
            selectedOption = null
            selectedWords = emptyList()
            speakingScore = null
          } else {
            onExitSession()
          }
        },
        onExitToJourney = {
          showLessonSuccessDialog = false
          onExitSession()
        }
      )
    }

    // 5. Final Lesson / Course Mastered Review & Explanation Dialog
    if (showCourseMasteredDialog) {
      LessonReviewDialog(
        lesson = currentLesson,
        nodeCourse = nodeCourse,
        isLastLessonInCourse = true,
        earnedXp = 25,
        onPlayAudio = onPlayAudio,
        onDismiss = {
          showCourseMasteredDialog = false
          onExitSession()
        },
        onNextLesson = {
          showCourseMasteredDialog = false
          onStartNextNode(nodeCourse.nodeId)
        },
        onExitToJourney = {
          showCourseMasteredDialog = false
          onExitSession()
        }
      )
    }
  }
}

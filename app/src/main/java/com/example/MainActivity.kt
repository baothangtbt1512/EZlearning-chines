package com.example

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.ui.components.AppBottomNavigation
import com.example.ui.components.NodeDetailDialog
import com.example.ui.components.ResetConfirmationDialog
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.JourneyScreen
import com.example.ui.screens.LearningSessionScreen
import com.example.ui.screens.OnboardingScreen
import com.example.ui.screens.ProfileScreen
import com.example.ui.screens.SettingsScreen
import com.example.ui.screens.WorldDetailScreen
import com.example.ui.theme.LearningChineseTheme
import com.example.ui.theme.WarmCream
import com.example.viewmodel.AppTab
import com.example.viewmodel.LearnerViewModel
import com.example.viewmodel.ScreenRoute

class MainActivity : ComponentActivity() {
  private val viewModel: LearnerViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      LearningChineseTheme {
        LearningChineseApp(viewModel = viewModel)
      }
    }
  }
}

@Composable
fun LearningChineseApp(viewModel: LearnerViewModel) {
  val state by viewModel.learnerState.collectAsState()
  val activeTab by viewModel.activeTab.collectAsState()
  val currentScreen by viewModel.currentScreen.collectAsState()
  val showResetDialog by viewModel.showResetDialog.collectAsState()
  val selectedNode by viewModel.selectedNode.collectAsState()
  val activeCourse by viewModel.activeCourse.collectAsState()
  val activeLessonIndex by viewModel.activeLessonIndex.collectAsState()
  val feedbackToast by viewModel.feedbackToast.collectAsState()
  val isSpeechRecording by viewModel.isSpeechRecording.collectAsState()
  val speechAudioRms by viewModel.speechAudioRms.collectAsState()
  val speechEvaluationResult by viewModel.speechEvaluationResult.collectAsState()

  val context = LocalContext.current
  val snackbarHostState = remember { SnackbarHostState() }

  LaunchedEffect(feedbackToast) {
    feedbackToast?.let { message ->
      Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
      viewModel.clearFeedbackToast()
    }
  }

  // 1. FIRST-LAUNCH ONBOARDING SCREEN
  if (!state.isOnboardingCompleted) {
    OnboardingScreen(
      onComplete = { name, avatar -> viewModel.completeOnboarding(name, avatar) },
      onPlayAudio = { text -> viewModel.playAudio(text) }
    )
    return
  }

  // 2. MAIN APPLICATION SCAFFOLD
  Scaffold(
    modifier = Modifier
      .fillMaxSize()
      .background(WarmCream),
    snackbarHost = { SnackbarHost(snackbarHostState) },
    bottomBar = {
      // Show bottom navigation on main tabs
      if (currentScreen != ScreenRoute.LEARNING_SESSION) {
        AppBottomNavigation(
          activeTab = activeTab,
          onTabSelected = { tab ->
            viewModel.selectTab(tab)
          }
        )
      }
    }
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
    ) {
      when (currentScreen) {
        ScreenRoute.HOME -> HomeScreen(
          state = state,
          onContinueAdventureClick = { viewModel.continueCurrentAdventure() },
          onViewJourneyClick = { viewModel.navigateTo(ScreenRoute.JOURNEY) },
          onNodeClick = { node -> viewModel.openNode(node) },
          onSettingsClick = { viewModel.navigateTo(ScreenRoute.SETTINGS) }
        )

        ScreenRoute.JOURNEY -> JourneyScreen(
          state = state,
          onBackClick = { viewModel.navigateBack() },
          onNodeClick = { node -> viewModel.openNode(node) },
          onSelectWorld = { worldId -> viewModel.selectWorld(worldId) }
        )

        ScreenRoute.PROFILE -> ProfileScreen(
          state = state,
          onBackClick = { viewModel.navigateBack() },
          onResetClick = { viewModel.showResetConfirmation(true) },
          onToggleSound = { viewModel.toggleSound(it) },
          onToggleReminders = { viewModel.toggleReminders(it) },
          onUpdateName = { viewModel.updateLearnerName(it) },
          onUpdateProfile = { name, avatar ->
            viewModel.updateLearnerName(name)
            viewModel.updateLearnerAvatar(avatar)
          }
        )

        ScreenRoute.SETTINGS -> SettingsScreen(
          state = state,
          onBackClick = { viewModel.navigateBack() },
          onResetClick = { viewModel.showResetConfirmation(true) },
          onToggleSound = { viewModel.toggleSound(it) },
          onToggleReminders = { viewModel.toggleReminders(it) },
          onUpdateName = { viewModel.updateLearnerName(it) },
          onUpdateProfile = { name, avatar ->
            viewModel.updateLearnerName(name)
            viewModel.updateLearnerAvatar(avatar)
          }
        )

        ScreenRoute.WORLD_1_DETAIL -> WorldDetailScreen(
          state = state,
          onBackClick = { viewModel.navigateBack() },
          onCompleteLesson = {
            viewModel.startLearningSession("w1_n1", 0)
          },
          onPlayAudio = { text -> viewModel.playAudio(text) }
        )

        ScreenRoute.LEARNING_SESSION -> {
          activeCourse?.let { course ->
            LearningSessionScreen(
              nodeCourse = course,
              initialLessonIndex = activeLessonIndex,
              isSpeechRecording = isSpeechRecording,
              speechAudioRms = speechAudioRms,
              speechEvaluationResult = speechEvaluationResult,
              onStartSpeechEvaluation = { text -> viewModel.startSpeechEvaluation(text) },
              onStopSpeechEvaluation = { viewModel.stopSpeechEvaluation() },
              onCancelSpeechEvaluation = { viewModel.cancelSpeechEvaluation() },
              onPlayAudio = { text -> viewModel.playAudio(text) },
              onExitSession = {
                viewModel.cancelSpeechEvaluation()
                viewModel.navigateBack()
              },
              onCompleteLesson = { lessonId, xp -> viewModel.completeMicroLesson(lessonId, xp) },
              onRecordEvidence = { lessonId, activityId, skill, correct, score ->
                viewModel.recordEvidence(lessonId, activityId, skill, correct, score)
              },
              onStartNextNode = { currentNodeId -> viewModel.startNextNode(currentNodeId) }
            )
          }
        }
      }

      // Reset Confirmation Modal Dialog
      if (showResetDialog) {
        ResetConfirmationDialog(
          onConfirm = { viewModel.confirmResetProgress() },
          onDismiss = { viewModel.showResetConfirmation(false) }
        )
      }

      // Interactive Node Course Overview Dialog
      selectedNode?.let { node ->
        NodeDetailDialog(
          node = node,
          nodeCourse = activeCourse,
          onDismiss = { viewModel.dismissNodeDialog() },
          onStartLesson = { lessonIdx -> viewModel.startLearningSession(node.id, lessonIdx) },
          onPlayAudio = { text -> viewModel.playAudio(text) }
        )
      }
    }
  }
}

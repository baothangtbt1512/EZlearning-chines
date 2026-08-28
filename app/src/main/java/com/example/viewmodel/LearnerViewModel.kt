package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.data.LearnerRepository
import com.example.data.World1Curriculum
import com.example.model.JourneyNode
import com.example.model.LearnerState
import com.example.model.NodeCourseData
import com.example.model.SkillType
import com.example.util.ChineseSpeechEvaluator
import com.example.util.ChineseTtsHelper
import com.example.util.SpeechEvaluationResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class AppTab {
  HOME,
  JOURNEY,
  PROFILE
}

enum class ScreenRoute {
  HOME,
  JOURNEY,
  PROFILE,
  SETTINGS,
  WORLD_1_DETAIL,
  LEARNING_SESSION
}

class LearnerViewModel(application: Application) : AndroidViewModel(application) {
  private val repository = LearnerRepository(application)
  private val ttsHelper = ChineseTtsHelper(application)
  private val speechEvaluator = ChineseSpeechEvaluator(application)

  val learnerState: StateFlow<LearnerState> = repository.state

  val isSpeechRecording: StateFlow<Boolean> = speechEvaluator.isRecording
  val speechAudioRms: StateFlow<Float> = speechEvaluator.audioRms
  val speechEvaluationResult: StateFlow<SpeechEvaluationResult?> = speechEvaluator.evaluationResult

  private val _activeTab = MutableStateFlow(AppTab.HOME)
  val activeTab: StateFlow<AppTab> = _activeTab.asStateFlow()

  private val _currentScreen = MutableStateFlow(ScreenRoute.HOME)
  val currentScreen: StateFlow<ScreenRoute> = _currentScreen.asStateFlow()

  private val _showResetDialog = MutableStateFlow(false)
  val showResetDialog: StateFlow<Boolean> = _showResetDialog.asStateFlow()

  private val _selectedNode = MutableStateFlow<JourneyNode?>(null)
  val selectedNode: StateFlow<JourneyNode?> = _selectedNode.asStateFlow()

  private val _activeCourse = MutableStateFlow<NodeCourseData?>(null)
  val activeCourse: StateFlow<NodeCourseData?> = _activeCourse.asStateFlow()

  private val _activeLessonIndex = MutableStateFlow(0)
  val activeLessonIndex: StateFlow<Int> = _activeLessonIndex.asStateFlow()

  private val _feedbackToast = MutableStateFlow<String?>(null)
  val feedbackToast: StateFlow<String?> = _feedbackToast.asStateFlow()

  fun selectTab(tab: AppTab) {
    _activeTab.value = tab
    _currentScreen.value = when (tab) {
      AppTab.HOME -> ScreenRoute.HOME
      AppTab.JOURNEY -> ScreenRoute.JOURNEY
      AppTab.PROFILE -> ScreenRoute.PROFILE
    }
  }

  fun selectWorld(worldId: String) {
    repository.selectWorld(worldId)
  }

  fun navigateTo(screen: ScreenRoute) {
    _currentScreen.value = screen
    when (screen) {
      ScreenRoute.HOME -> _activeTab.value = AppTab.HOME
      ScreenRoute.JOURNEY -> _activeTab.value = AppTab.JOURNEY
      ScreenRoute.PROFILE, ScreenRoute.SETTINGS -> _activeTab.value = AppTab.PROFILE
      ScreenRoute.WORLD_1_DETAIL -> _activeTab.value = AppTab.HOME
      ScreenRoute.LEARNING_SESSION -> _activeTab.value = AppTab.JOURNEY
    }
  }

  fun navigateBack() {
    when (_currentScreen.value) {
      ScreenRoute.SETTINGS -> {
        _currentScreen.value = ScreenRoute.PROFILE
        _activeTab.value = AppTab.PROFILE
      }
      ScreenRoute.WORLD_1_DETAIL -> {
        _currentScreen.value = ScreenRoute.HOME
        _activeTab.value = AppTab.HOME
      }
      ScreenRoute.LEARNING_SESSION -> {
        _currentScreen.value = ScreenRoute.JOURNEY
        _activeTab.value = AppTab.JOURNEY
      }
      else -> {
        _currentScreen.value = ScreenRoute.HOME
        _activeTab.value = AppTab.HOME
      }
    }
  }

  fun openNode(node: JourneyNode) {
    if (node.isLocked) {
      _feedbackToast.value = "${node.name} đang bị khóa! Hãy hoàn thành các chặng trước để mở khóa."
    } else {
      _selectedNode.value = node
      val course = repository.getNodeCourse(node.id)
      _activeCourse.value = course
      course?.let { c ->
        val words = c.vocabulary.map { it.hanzi }
        ttsHelper.preloadWords(words)
      }
    }
  }

  fun continueCurrentAdventure() {
    val state = repository.state.value
    val activeWorld = state.worlds.find { it.id == state.currentWorldId } ?: state.worlds.first()
    val currentNode = activeWorld.nodes.find { it.isCurrent }
      ?: activeWorld.nodes.firstOrNull { !it.isCompleted && !it.isLocked }
      ?: activeWorld.nodes.first()

    val course = repository.getNodeCourse(currentNode.id)
    val nextLessonIndex = course?.microLessons?.indexOfFirst { !it.isCompleted }?.takeIf { it >= 0 } ?: 0
    startLearningSession(currentNode.id, nextLessonIndex)
  }

  fun startLearningSession(nodeId: String, lessonIndex: Int = 0) {
    val course = repository.getNodeCourse(nodeId) ?: World1Curriculum.getNodeCourse(nodeId)
    _activeCourse.value = course
    _activeLessonIndex.value = lessonIndex
    _selectedNode.value = null
    _currentScreen.value = ScreenRoute.LEARNING_SESSION
    ttsHelper.maximizeVolume()
    course?.let { c ->
      val words = c.vocabulary.map { it.hanzi } + c.microLessons.flatMap { l -> l.activities.map { it.audioText } }
      ttsHelper.preloadWords(words.filter { it.isNotBlank() })
    }
  }

  fun dismissNodeDialog() {
    _selectedNode.value = null
  }

  fun completeOnboarding(name: String, avatar: String = "dudu") {
    val trimmed = name.trim().take(30)
    if (trimmed.isNotEmpty()) {
      repository.saveLearnerProfile(trimmed, avatar)
      _currentScreen.value = ScreenRoute.HOME
      _activeTab.value = AppTab.HOME
      val mascotName = if (avatar == "bubu") "Bubu 🐼" else "Dudu 🐻"
      _feedbackToast.value = "Chào mừng $trimmed và $mascotName đến với hành trình tiếng Trung!"
    }
  }

  fun updateLearnerName(name: String) {
    val trimmed = name.trim().take(30)
    if (trimmed.isNotEmpty()) {
      repository.updateName(trimmed)
      _feedbackToast.value = "Đã cập nhật tên người học: $trimmed"
    }
  }

  fun updateLearnerAvatar(avatar: String) {
    repository.updateAvatar(avatar)
    val mascotName = if (avatar == "bubu") "Bubu (Gấu trúc)" else "Dudu (Gấu nâu)"
    _feedbackToast.value = "Đã đổi bạn đồng hành sang $mascotName"
  }

  fun clearFeedbackToast() {
    _feedbackToast.value = null
  }

  fun showResetConfirmation(show: Boolean) {
    _showResetDialog.value = show
  }

  fun confirmResetProgress() {
    repository.resetProgress()
    _showResetDialog.value = false
    _feedbackToast.value = "Tiến độ học đã được đặt lại."
  }

  fun toggleSound(enabled: Boolean) {
    repository.toggleSoundEffects(enabled)
  }

  fun toggleReminders(enabled: Boolean) {
    repository.toggleReminders(enabled)
  }

  fun playAudio(text: String) {
    ttsHelper.speak(text)
  }

  fun completeMicroLesson(lessonId: String, xp: Int) {
    val course = _activeCourse.value ?: return
    repository.completeMicroLesson(course.nodeId, lessonId, xp)
    // Refresh course
    _activeCourse.value = repository.getNodeCourse(course.nodeId)
    _feedbackToast.value = "+$xp XP! Tuyệt vời lắm!"
  }

  fun startNextNode(currentNodeId: String) {
    val currentCourse = World1Curriculum.world1NodeCourses.find { it.nodeId == currentNodeId }
    val nextOrder = (currentCourse?.order ?: 1) + 1
    val nextCourse = World1Curriculum.world1NodeCourses.find { it.order == nextOrder }
    if (nextCourse != null) {
      startLearningSession(nextCourse.nodeId, 0)
    } else {
      navigateBack()
    }
  }

  fun recordEvidence(lessonId: String, activityId: String, skill: SkillType, correct: Boolean, score: Double) {
    val course = _activeCourse.value ?: return
    repository.recordEvidence(
      worldId = "world_1",
      nodeId = course.nodeId,
      lessonId = lessonId,
      activityId = activityId,
      itemId = activityId,
      skill = skill,
      correct = correct,
      score = score
    )
  }

  fun completeLessonDemo() {
    startLearningSession("w1_n1", 0)
  }

  fun startSpeechEvaluation(targetText: String) {
    speechEvaluator.startEvaluation(targetText)
  }

  fun stopSpeechEvaluation() {
    speechEvaluator.stopListening()
  }

  fun cancelSpeechEvaluation() {
    speechEvaluator.cancelListening()
  }

  override fun onCleared() {
    super.onCleared()
    ttsHelper.shutdown()
    speechEvaluator.destroy()
  }
}

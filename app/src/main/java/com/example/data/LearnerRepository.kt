package com.example.data

import android.content.Context
import android.content.SharedPreferences
import com.example.model.AchievementItem
import com.example.model.ItemSkillMastery
import com.example.model.JourneyNode
import com.example.model.LearnerState
import com.example.model.LearningEvidence
import com.example.model.NodeCourseData
import com.example.model.ReviewQueueItem
import com.example.model.SkillType
import com.example.model.WorldData
import com.example.model.defaultWorldsData
import com.example.model.initialAchievements
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

class LearnerRepository(context: Context) {
  private val prefs: SharedPreferences = context.getSharedPreferences("learning-chinese:state", Context.MODE_PRIVATE)

  private val _state = MutableStateFlow(loadState())
  val state: StateFlow<LearnerState> = _state.asStateFlow()

  private fun loadState(): LearnerState {
    val name = prefs.getString("learner_name", "") ?: ""
    val avatar = prefs.getString("learner_avatar", "dudu") ?: "dudu"
    val onboardingCompleted = prefs.getBoolean("onboarding_completed", false) && name.isNotBlank()
    val country = prefs.getString("learner_country", "Việt Nam") ?: "Việt Nam"
    val level = prefs.getInt("learner_level", 1)
    val xp = prefs.getInt("learner_xp", 0)
    val dailyGoalTarget = prefs.getInt("learner_daily_target", 10)
    val dailyGoalCurrent = prefs.getInt("learner_daily_current", 0)
    val streakDays = prefs.getInt("learner_streak", 0)
    val currentWorldId = prefs.getString("learner_current_world", "world_1") ?: "world_1"

    val soundEnabled = prefs.getBoolean("sound_effects", true)
    val remindersEnabled = prefs.getBoolean("reminders", true)

    val skills = SkillType.values().associateWith { skill ->
      prefs.getInt("skill_${skill.name}", 0)
    }

    val completedNodesCount = prefs.getInt("completed_nodes_count", 0)

    val updatedWorlds = buildWorlds(completedNodesCount, currentWorldId)
    val activeWorld = updatedWorlds.find { it.id == currentWorldId } ?: updatedWorlds.first()
    val currentNode = activeWorld.nodes.find { it.isCurrent } ?: activeWorld.nodes.firstOrNull { !it.isLocked } ?: activeWorld.nodes.first()
    val currentNodeId = currentNode.id

    val achievements = initialAchievements.map { ach ->
      val unlocked = prefs.getBoolean("ach_${ach.id}", false)
      ach.copy(isUnlocked = unlocked)
    }

    return LearnerState(
      name = name,
      avatar = avatar,
      country = country,
      level = level,
      xp = xp,
      dailyGoalTarget = dailyGoalTarget,
      dailyGoalCurrent = dailyGoalCurrent,
      streakDays = streakDays,
      currentWorldId = currentWorldId,
      currentNodeId = currentNodeId,
      skills = skills,
      soundEffectsEnabled = soundEnabled,
      remindersEnabled = remindersEnabled,
      worlds = updatedWorlds,
      journeyNodes = activeWorld.nodes,
      achievements = achievements,
      isOnboardingCompleted = onboardingCompleted
    )
  }

  private fun buildWorlds(completedNodesTotal: Int, currentWorldId: String): List<WorldData> {
    var globalNodeIndex = 0
    return defaultWorldsData.mapIndexed { worldIndex, world ->
      val nodesCount = world.nodes.size
      val worldStart = globalNodeIndex
      val worldEnd = globalNodeIndex + nodesCount
      globalNodeIndex += nodesCount

      val worldCompleted = completedNodesTotal >= worldEnd
      val worldLocked = worldIndex > 0 && completedNodesTotal < worldStart
      val isCurrent = world.id == currentWorldId

      val updatedNodes = world.nodes.mapIndexed { nodeIndex, node ->
        val nodeGlobalIdx = worldStart + nodeIndex
        when {
          nodeGlobalIdx < completedNodesTotal -> node.copy(isCompleted = true, isLocked = false, isCurrent = false)
          nodeGlobalIdx == completedNodesTotal -> node.copy(isCompleted = false, isLocked = false, isCurrent = true)
          else -> node.copy(isCompleted = false, isLocked = true, isCurrent = false)
        }
      }

      world.copy(
        isCompleted = worldCompleted,
        isLocked = worldLocked,
        isCurrent = isCurrent,
        nodes = updatedNodes
      )
    }
  }

  fun saveLearnerProfile(name: String, avatar: String) {
    val trimmed = name.trim().take(30)
    val safeAvatar = if (avatar == "bubu") "bubu" else "dudu"
    prefs.edit()
      .putString("learner_name", trimmed)
      .putString("learner_avatar", safeAvatar)
      .putBoolean("onboarding_completed", true)
      .apply()
    _state.value = _state.value.copy(
      name = trimmed,
      avatar = safeAvatar,
      isOnboardingCompleted = true
    )
  }

  fun saveLearnerName(name: String) {
    val trimmed = name.trim().take(30)
    prefs.edit()
      .putString("learner_name", trimmed)
      .putBoolean("onboarding_completed", true)
      .apply()
    _state.value = _state.value.copy(
      name = trimmed,
      isOnboardingCompleted = true
    )
  }

  fun updateAvatar(avatar: String) {
    val safeAvatar = if (avatar == "bubu") "bubu" else "dudu"
    prefs.edit()
      .putString("learner_avatar", safeAvatar)
      .apply()
    _state.value = _state.value.copy(
      avatar = safeAvatar
    )
  }

  fun updateName(name: String) {
    val trimmed = name.trim().take(30)
    prefs.edit()
      .putString("learner_name", trimmed)
      .apply()
    _state.value = _state.value.copy(
      name = trimmed
    )
  }

  fun resetProgress() {
    val currentName = prefs.getString("learner_name", "") ?: ""
    val currentAvatar = prefs.getString("learner_avatar", "dudu") ?: "dudu"
    val currentCountry = prefs.getString("learner_country", "Việt Nam") ?: "Việt Nam"
    val onboardingDone = prefs.getBoolean("onboarding_completed", false)
    val sound = prefs.getBoolean("sound_effects", true)
    val reminders = prefs.getBoolean("reminders", true)

    prefs.edit().clear()
      .putString("learner_name", currentName)
      .putString("learner_avatar", currentAvatar)
      .putString("learner_country", currentCountry)
      .putBoolean("onboarding_completed", onboardingDone)
      .putBoolean("sound_effects", sound)
      .putBoolean("reminders", reminders)
      .apply()

    _state.value = loadState()
  }

  fun selectWorld(worldId: String) {
    prefs.edit().putString("learner_current_world", worldId).apply()
    val completedCount = prefs.getInt("completed_nodes_count", 0)
    val updatedWorlds = buildWorlds(completedCount, worldId)
    val activeWorld = updatedWorlds.find { it.id == worldId } ?: updatedWorlds.first()
    val currentNode = activeWorld.nodes.find { it.isCurrent } ?: activeWorld.nodes.firstOrNull { !it.isLocked } ?: activeWorld.nodes.first()
    _state.value = _state.value.copy(
      currentWorldId = worldId,
      currentNodeId = currentNode.id,
      worlds = updatedWorlds,
      journeyNodes = activeWorld.nodes
    )
  }

  fun toggleSoundEffects(enabled: Boolean) {
    prefs.edit().putBoolean("sound_effects", enabled).apply()
    _state.value = _state.value.copy(soundEffectsEnabled = enabled)
  }

  fun toggleReminders(enabled: Boolean) {
    prefs.edit().putBoolean("reminders", enabled).apply()
    _state.value = _state.value.copy(remindersEnabled = enabled)
  }

  fun updateCountry(country: String) {
    prefs.edit().putString("learner_country", country).apply()
    _state.value = _state.value.copy(country = country)
  }

  fun isMicroLessonCompleted(nodeId: String, lessonId: String): Boolean {
    return prefs.getBoolean("lesson_comp_${nodeId}_${lessonId}", false)
  }

  private fun findCurriculumCourse(nodeId: String, learnerName: String = ""): NodeCourseData? {
    return when {
      nodeId.startsWith("w1_") -> World1Curriculum.getNodeCourse(nodeId, learnerName)
      nodeId.startsWith("w2_") -> World2Curriculum.getNodeCourse(nodeId, learnerName)
      nodeId.startsWith("w3_") -> World3Curriculum.getNodeCourse(nodeId, learnerName)
      nodeId.startsWith("w4_") -> World4Curriculum.getNodeCourse(nodeId, learnerName)
      nodeId.startsWith("w5_") -> World5Curriculum.getNodeCourse(nodeId, learnerName)
      else -> World1Curriculum.getNodeCourse(nodeId, learnerName)
        ?: World2Curriculum.getNodeCourse(nodeId, learnerName)
        ?: World3Curriculum.getNodeCourse(nodeId, learnerName)
        ?: World4Curriculum.getNodeCourse(nodeId, learnerName)
        ?: World5Curriculum.getNodeCourse(nodeId, learnerName)
    }
  }

  fun getCompletedMicroLessonsCount(nodeId: String): Int {
    val course = findCurriculumCourse(nodeId) ?: return 0
    return course.microLessons.count { isMicroLessonCompleted(nodeId, it.id) }
  }

  fun recordEvidence(
    worldId: String,
    nodeId: String,
    lessonId: String,
    activityId: String,
    itemId: String,
    skill: SkillType,
    correct: Boolean,
    score: Double
  ) {
    val evidenceKey = "ev_${System.currentTimeMillis()}_${UUID.randomUUID().toString().take(6)}"
    prefs.edit()
      .putBoolean("${evidenceKey}_correct", correct)
      .putFloat("${evidenceKey}_score", score.toFloat())
      .apply()

    // Update skill progress slightly
    val currentSkillScore = prefs.getInt("skill_${skill.name}", 0)
    val boost = if (correct) (score * 5).toInt().coerceAtLeast(2) else 1
    val newSkillScore = (currentSkillScore + boost).coerceAtMost(100)
    prefs.edit().putInt("skill_${skill.name}", newSkillScore).apply()

    val updatedSkills = _state.value.skills.toMutableMap()
    updatedSkills[skill] = newSkillScore
    _state.value = _state.value.copy(skills = updatedSkills)
  }

  fun completeMicroLesson(nodeId: String, lessonId: String, xpGained: Int = 10) {
    prefs.edit().putBoolean("lesson_comp_${nodeId}_${lessonId}", true).apply()

    val currentState = _state.value
    val newXp = currentState.xp + xpGained
    val newDailyCurrent = (currentState.dailyGoalCurrent + xpGained).coerceAtMost(currentState.dailyGoalTarget)
    val newStreak = if (currentState.streakDays == 0) 1 else currentState.streakDays

    prefs.edit()
      .putInt("learner_xp", newXp)
      .putInt("learner_daily_current", newDailyCurrent)
      .putInt("learner_streak", newStreak)
      .apply()

    // Check if all lessons in this node are completed
    val course = findCurriculumCourse(nodeId)
    if (course != null) {
      val allCompleted = course.microLessons.all { isMicroLessonCompleted(nodeId, it.id) }
      if (allCompleted) {
        completeNode(nodeId, xpBonus = 25)
        return
      }
    }

    _state.value = currentState.copy(
      xp = newXp,
      dailyGoalCurrent = newDailyCurrent,
      streakDays = newStreak
    )
  }

  fun completeNode(nodeId: String, xpBonus: Int = 25) {
    val currentState = _state.value
    val currentCompletedCount = prefs.getInt("completed_nodes_count", 0)

    // Calculate global index for this node across all worlds
    var targetGlobalIndex = 1
    var accumulatedNodes = 0
    for (world in currentState.worlds) {
      val foundIdx = world.nodes.indexOfFirst { it.id == nodeId }
      if (foundIdx >= 0) {
        targetGlobalIndex = accumulatedNodes + foundIdx + 1
        break
      }
      accumulatedNodes += world.nodes.size
    }

    val newCompletedCount = maxOf(currentCompletedCount, targetGlobalIndex)
    val newXp = currentState.xp + xpBonus

    val unlockFirstWords = true
    val unlockConversation = targetGlobalIndex >= 8
    val unlockExplorer = targetGlobalIndex >= 9

    prefs.edit()
      .putInt("learner_xp", newXp)
      .putInt("completed_nodes_count", newCompletedCount)
      .putBoolean("ach_first_words", unlockFirstWords)
      .putBoolean("ach_first_conversation", unlockConversation)
      .putBoolean("ach_world_explorer", unlockExplorer)
      .apply()

    val updatedWorlds = buildWorlds(newCompletedCount, currentState.currentWorldId)
    val activeWorld = updatedWorlds.find { it.id == currentState.currentWorldId } ?: updatedWorlds.first()
    val currentNode = activeWorld.nodes.find { it.isCurrent } ?: activeWorld.nodes.firstOrNull { !it.isLocked } ?: activeWorld.nodes.first()

    val updatedAchievements = currentState.achievements.map { ach ->
      when (ach.id) {
        "first_words" -> ach.copy(isUnlocked = true)
        "first_conversation" -> ach.copy(isUnlocked = ach.isUnlocked || unlockConversation)
        "world_explorer" -> ach.copy(isUnlocked = ach.isUnlocked || unlockExplorer)
        else -> ach
      }
    }

    _state.value = currentState.copy(
      xp = newXp,
      currentNodeId = currentNode.id,
      worlds = updatedWorlds,
      journeyNodes = activeWorld.nodes,
      achievements = updatedAchievements
    )
  }

  fun getNodeCourse(nodeId: String): NodeCourseData? {
    val course = findCurriculumCourse(nodeId, _state.value.name) ?: return null
    val completedCount = getCompletedMicroLessonsCount(nodeId)
    val isCompleted = completedCount >= course.microLessons.size
    val masteryPct = if (course.microLessons.isNotEmpty()) (completedCount * 100) / course.microLessons.size else 0

    val updatedLessons = course.microLessons.map { lesson ->
      lesson.copy(isCompleted = isMicroLessonCompleted(nodeId, lesson.id))
    }

    return course.copy(
      microLessons = updatedLessons,
      isCompleted = isCompleted,
      isMastered = isCompleted,
      masteryPercentage = masteryPct
    )
  }

  fun completeAdventureStep(xpGained: Int = 10) {
    completeMicroLesson("w1_n1", "w1_n1_l1", xpGained)
  }
}

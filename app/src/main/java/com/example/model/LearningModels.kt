package com.example.model

enum class ActivityType {
  DISCOVER,
  LISTEN_PINYIN,
  LISTEN_HANZI,
  READING,
  MULTIPLE_CHOICE,
  FILL_BLANK,
  SENTENCE_BUILDER,
  PANDA_CONVERSATION,
  SPEAKING,
  WRITING,
  MIXED_REVIEW,
  TONE_DISCOVER,
  TONE_LISTEN,
  TONE_SPEAKING,
  NUMBER_CHALLENGE,
  BOSS_ROUND
}

enum class PandaEmotion {
  NEUTRAL,
  HAPPY,
  CHEERING,
  THINKING,
  ENCOURAGING
}

data class LearningItem(
  val id: String,
  val hanzi: String,
  val pinyin: String,
  val meaning: String,
  val vietnameseMeaning: String = "",
  val usageNote: String = "",
  val exampleSentence: String = "",
  val examplePinyin: String = "",
  val exampleTranslation: String = "",
  val illustrationType: String = "",
  val audio: String = "",
  val category: String = "general",
  val toneNumber: Int? = null
)

data class LearningActivity(
  val id: String,
  val type: ActivityType,
  val skill: SkillType,
  val itemIds: List<String> = emptyList(),
  val prompt: String,
  val hanziPrompt: String = "",
  val pinyinPrompt: String = "",
  val audioText: String = "",
  val options: List<String> = emptyList(),
  val correctAnswer: String,
  val explanation: String = "",
  val sentenceWords: List<String> = emptyList(),
  val targetSentence: String = "",
  val pandaDialogue: String = "",
  val pandaEmotion: PandaEmotion = PandaEmotion.NEUTRAL,
  val scaffoldingLevel: Int = 1,
  val isReviewItem: Boolean = false,
  val roundNumber: Int? = null
)

data class MicroLesson(
  val id: String,
  val nodeId: String,
  val title: String,
  val subtitle: String,
  val type: String,
  val order: Int,
  val activities: List<LearningActivity>,
  val isCompleted: Boolean = false
)

data class NodeCourseData(
  val nodeId: String,
  val title: String,
  val subtitle: String,
  val description: String,
  val order: Int,
  val vocabulary: List<LearningItem>,
  val microLessons: List<MicroLesson>,
  val isCompleted: Boolean = false,
  val isMastered: Boolean = false,
  val masteryPercentage: Int = 0
)

data class LearningEvidence(
  val id: String,
  val learnerId: String = "",
  val worldId: String,
  val nodeId: String,
  val lessonId: String,
  val activityId: String,
  val itemId: String,
  val skill: SkillType,
  val activityType: ActivityType,
  val correct: Boolean,
  val score: Double,
  val attempts: Int = 1,
  val responseTimeMs: Long = 0,
  val timestamp: Long = System.currentTimeMillis()
)

data class ItemSkillMastery(
  val itemId: String,
  val skillScores: Map<SkillType, Double> = emptyMap(),
  val overallMastery: Double = 0.0,
  val lastPracticedTimestamp: Long = 0
)

data class ReviewQueueItem(
  val itemId: String,
  val sourceNodeId: String,
  val priority: Int = 5,
  val reason: String = "needs_review",
  val lastIncorrectTimestamp: Long = 0
)

data class SpeakingAttempt(
  val targetText: String,
  val spokenText: String,
  val pronunciationScore: Double,
  val toneScore: Double,
  val fluencyScore: Double,
  val overallScore: Double
)

data class SkillRequirement(
  val skill: SkillType,
  val minimumScore: Double = 0.65
)

data class WorldUnlockCondition(
  val worldId: String,
  val requirements: List<SkillRequirement> = emptyList()
)

data class Quest(
  val id: String,
  val worldId: String,
  val title: String,
  val description: String,
  val targetSkills: List<SkillType>,
  val nodeIds: List<String> = emptyList(),
  val activityIds: List<String> = emptyList(),
  val xpReward: Int = 50,
  val isCompleted: Boolean = false
)

data class WorldCompletionData(
  val worldId: String,
  val titleChinese: String,
  val titleEnglish: String,
  val achievementsUnlocked: List<String>,
  val masteredSkills: List<String>,
  val xpReward: Int = 100
)

data class LearningSession(
  val sessionId: String,
  val learnerId: String = "",
  val worldId: String = "world_1",
  val nodeId: String,
  val isReviewMode: Boolean = false,
  val microLessonIds: List<String> = emptyList(),
  val currentLessonIndex: Int = 0,
  val currentActivityIndex: Int = 0,
  val correctCount: Int = 0,
  val totalAttemptCount: Int = 0,
  val startedAt: Long = System.currentTimeMillis(),
  val completedAt: Long? = null,
  val isCompleted: Boolean = false
)

fun String.interpolateLearner(learnerName: String): String {
  val safeName = learnerName.trim().ifBlank { "Bạn" }
  return this
    .replace("{{learner.name}}", safeName)
    .replace("{{learner_name}}", safeName)
    .replace("{{name}}", safeName)
    .replace("Alex", safeName)
}

fun LearningItem.interpolateLearner(learnerName: String): LearningItem {
  return this.copy(
    exampleSentence = exampleSentence.interpolateLearner(learnerName),
    examplePinyin = examplePinyin.interpolateLearner(learnerName),
    exampleTranslation = exampleTranslation.interpolateLearner(learnerName),
    meaning = meaning.interpolateLearner(learnerName),
    vietnameseMeaning = vietnameseMeaning.interpolateLearner(learnerName),
    usageNote = usageNote.interpolateLearner(learnerName)
  )
}

fun LearningActivity.interpolateLearner(learnerName: String): LearningActivity {
  return this.copy(
    prompt = prompt.interpolateLearner(learnerName),
    hanziPrompt = hanziPrompt.interpolateLearner(learnerName),
    pinyinPrompt = pinyinPrompt.interpolateLearner(learnerName),
    audioText = audioText.interpolateLearner(learnerName),
    options = options.map { it.interpolateLearner(learnerName) },
    correctAnswer = correctAnswer.interpolateLearner(learnerName),
    explanation = explanation.interpolateLearner(learnerName),
    sentenceWords = sentenceWords.map { it.interpolateLearner(learnerName) },
    targetSentence = targetSentence.interpolateLearner(learnerName),
    pandaDialogue = pandaDialogue.interpolateLearner(learnerName)
  )
}

fun MicroLesson.interpolateLearner(learnerName: String): MicroLesson {
  return this.copy(
    title = title.interpolateLearner(learnerName),
    subtitle = subtitle.interpolateLearner(learnerName),
    activities = activities.map { it.interpolateLearner(learnerName) }
  )
}

fun NodeCourseData.interpolateLearner(learnerName: String): NodeCourseData {
  return this.copy(
    title = title.interpolateLearner(learnerName),
    subtitle = subtitle.interpolateLearner(learnerName),
    description = description.interpolateLearner(learnerName),
    vocabulary = vocabulary.map { it.interpolateLearner(learnerName) },
    microLessons = microLessons.map { it.interpolateLearner(learnerName) }
  )
}

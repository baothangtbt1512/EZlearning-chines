package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.model.LearningActivity
import com.example.model.LearningItem
import com.example.model.MicroLesson
import com.example.model.NodeCourseData
import com.example.ui.theme.DarkJade
import com.example.ui.theme.DarkText
import com.example.ui.theme.JadeContainer
import com.example.ui.theme.PrimaryJade
import com.example.ui.theme.SecondaryText
import com.example.ui.theme.SoftBorder
import com.example.ui.theme.WarmCream
import com.example.ui.theme.WarmGold

/**
 * Data representation for reviewing items learned during a micro-lesson.
 */
data class LessonReviewCardData(
  val hanzi: String,
  val pinyin: String,
  val vietnameseMeaning: String,
  val usageNote: String = "",
  val exampleSentence: String = "",
  val examplePinyin: String = "",
  val exampleTranslation: String = ""
)

/**
 * Extracts and synthesizes review items for any micro-lesson from the node course vocabulary
 * and activity contents.
 */
fun extractLessonReviewData(
  lesson: MicroLesson,
  nodeCourse: NodeCourseData
): Pair<List<LessonReviewCardData>, String> {
  val reviewList = mutableListOf<LessonReviewCardData>()
  val seenHanzi = mutableSetOf<String>()

  // 1. Check item IDs in lesson activities
  val activityItemIds = lesson.activities.flatMap { it.itemIds }.distinct()
  for (itemId in activityItemIds) {
    val vocab = nodeCourse.vocabulary.find { it.id == itemId }
    if (vocab != null && seenHanzi.add(vocab.hanzi)) {
      reviewList.add(
        LessonReviewCardData(
          hanzi = vocab.hanzi,
          pinyin = vocab.pinyin,
          vietnameseMeaning = vocab.meaning.ifEmpty { vocab.vietnameseMeaning },
          usageNote = vocab.usageNote,
          exampleSentence = vocab.exampleSentence,
          examplePinyin = vocab.examplePinyin,
          exampleTranslation = vocab.exampleTranslation
        )
      )
    }
  }

  // 2. Extract dialogue / sentence items from activities
  for (activity in lesson.activities) {
    if (activity.targetSentence.isNotBlank() && seenHanzi.add(activity.targetSentence)) {
      reviewList.add(
        LessonReviewCardData(
          hanzi = activity.targetSentence,
          pinyin = activity.pinyinPrompt.ifEmpty { activity.audioText },
          vietnameseMeaning = activity.prompt.ifEmpty { "Mẫu câu thực hành" },
          usageNote = activity.explanation
        )
      )
    } else if (activity.pandaDialogue.isNotBlank() && seenHanzi.add(activity.pandaDialogue)) {
      reviewList.add(
        LessonReviewCardData(
          hanzi = activity.pandaDialogue,
          pinyin = activity.pinyinPrompt,
          vietnameseMeaning = activity.prompt.ifEmpty { "Hội thoại cùng Bảo Bảo" },
          usageNote = activity.explanation
        )
      )
    } else if (activity.hanziPrompt.isNotBlank() && seenHanzi.add(activity.hanziPrompt)) {
      reviewList.add(
        LessonReviewCardData(
          hanzi = activity.hanziPrompt,
          pinyin = activity.pinyinPrompt,
          vietnameseMeaning = activity.prompt,
          usageNote = activity.explanation
        )
      )
    }
  }

  // 3. Fallback if reviewList is still empty: use all course vocabulary
  if (reviewList.isEmpty()) {
    for (vocab in nodeCourse.vocabulary) {
      if (seenHanzi.add(vocab.hanzi)) {
        reviewList.add(
          LessonReviewCardData(
            hanzi = vocab.hanzi,
            pinyin = vocab.pinyin,
            vietnameseMeaning = vocab.meaning.ifEmpty { vocab.vietnameseMeaning },
            usageNote = vocab.usageNote,
            exampleSentence = vocab.exampleSentence,
            examplePinyin = vocab.examplePinyin,
            exampleTranslation = vocab.exampleTranslation
          )
        )
      }
    }
  }

  // Synthesize comprehensive lesson explanation
  val explanationBuilder = StringBuilder()
  val activityExplanations = lesson.activities
    .map { it.explanation }
    .filter { it.isNotBlank() }
    .distinct()

  if (activityExplanations.isNotEmpty()) {
    explanationBuilder.append(activityExplanations.joinToString("\n• ", prefix = "• "))
  } else {
    explanationBuilder.append("Bạn đã hoàn thành xuất sắc các nội dung về: ")
    explanationBuilder.append(lesson.subtitle.ifEmpty { lesson.title })
    explanationBuilder.append(". Hãy lắng nghe lại phát âm và ghi nhớ cấu trúc câu để vận dụng tự nhiên vào giao tiếp thực tế nhé!")
  }

  return Pair(reviewList, explanationBuilder.toString())
}

/**
 * Universal Lesson Review & Explanation Popup Dialog shown after ANY lesson finishes.
 * Features:
 * - Clear Chinese (Hanzi) in high-contrast typography
 * - Pinyin with tonal accents
 * - Vietnamese translation
 * - Pronunciation audio button (tap to listen TTS)
 * - Key grammatical / contextual explanation
 * - Action buttons to proceed to next lesson or return
 */
@Composable
fun LessonReviewDialog(
  lesson: MicroLesson,
  nodeCourse: NodeCourseData,
  isLastLessonInCourse: Boolean,
  earnedXp: Int = 10,
  onPlayAudio: (String) -> Unit,
  onDismiss: () -> Unit,
  onNextLesson: () -> Unit,
  onExitToJourney: () -> Unit,
  modifier: Modifier = Modifier
) {
  val (reviewItems, lessonExplanation) = extractLessonReviewData(lesson, nodeCourse)

  Dialog(
    onDismissRequest = onDismiss,
    properties = DialogProperties(usePlatformDefaultWidth = false)
  ) {
    Surface(
      shape = RoundedCornerShape(24.dp),
      color = WarmCream,
      modifier = modifier
        .fillMaxWidth(0.94f)
        .fillMaxHeight(0.88f)
        .border(1.5.dp, WarmGold.copy(alpha = 0.6f), RoundedCornerShape(24.dp))
        .testTag("lesson_review_dialog")
    ) {
      Column(
        modifier = Modifier.fillMaxWidth()
      ) {
        // 1. TOP HEADER BANNER
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .background(
              Brush.verticalGradient(
                listOf(DarkJade, Color(0xFF00695C))
              )
            )
            .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier.weight(1f)
            ) {
              PandaAvatar(size = 46.dp)
              Spacer(modifier = Modifier.width(12.dp))
              Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                  Text(
                    text = if (isLastLessonInCourse) "🏆 HOÀN THÀNH KHÓA HỌC!" else "🎉 HOÀN THÀNH BÀI HỌC!",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = WarmGold
                  )
                }
                Text(
                  text = lesson.title,
                  fontSize = 13.sp,
                  fontWeight = FontWeight.Medium,
                  color = Color.White.copy(alpha = 0.9f)
                )
              }
            }

            // Close button
            IconButton(
              onClick = onDismiss,
              modifier = Modifier
                .size(32.dp)
                .background(Color.White.copy(alpha = 0.15f), CircleShape)
            ) {
              Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Đóng",
                tint = Color.White,
                modifier = Modifier.size(18.dp)
              )
            }
          }
        }

        // 2. SCROLLABLE REVIEW CONTENT
        LazyColumn(
          modifier = Modifier
            .weight(1f)
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
          contentPadding = PaddingValues(vertical = 14.dp),
          verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          // XP Reward Card
          item {
            Card(
              shape = RoundedCornerShape(16.dp),
              colors = CardDefaults.cardColors(containerColor = JadeContainer),
              modifier = Modifier.fillMaxWidth()
            ) {
              Column(
                modifier = Modifier.fillMaxWidth()
              ) {
                Row(
                  modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.SpaceBetween
                ) {
                  Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                      imageVector = Icons.Filled.AutoAwesome,
                      contentDescription = null,
                      tint = DarkJade,
                      modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                      text = "Điểm thưởng nhận được:",
                      fontSize = 13.sp,
                      fontWeight = FontWeight.SemiBold,
                      color = DarkJade
                    )
                  }
                  Box(
                    modifier = Modifier
                      .clip(RoundedCornerShape(20.dp))
                      .background(PrimaryJade)
                      .padding(horizontal = 12.dp, vertical = 4.dp)
                  ) {
                    Text(
                      text = "+$earnedXp XP",
                      fontSize = 14.sp,
                      fontWeight = FontWeight.Bold,
                      color = Color.White
                    )
                  }
                }

                Row(
                  modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE8F5E9))
                    .padding(horizontal = 14.dp, vertical = 6.dp),
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = null,
                    tint = Color(0xFF2E7D32),
                    modifier = Modifier.size(15.dp)
                  )
                  Spacer(modifier = Modifier.width(6.dp))
                  Text(
                    text = "Các thẻ ghi nhớ bài học này đã tự động lưu vào Sổ tay Profile của bạn.",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF1B5E20)
                  )
                }
              }
            }
          }

          // Lesson Explanation Box
          item {
            Card(
              shape = RoundedCornerShape(16.dp),
              colors = CardDefaults.cardColors(containerColor = Color.White),
              border = androidx.compose.foundation.BorderStroke(1.dp, SoftBorder),
              modifier = Modifier.fillMaxWidth()
            ) {
              Column(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(14.dp)
              ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                  Icon(
                    imageVector = Icons.Filled.Lightbulb,
                    contentDescription = null,
                    tint = Color(0xFFF57F17),
                    modifier = Modifier.size(20.dp)
                  )
                  Spacer(modifier = Modifier.width(8.dp))
                  Text(
                    text = "Giải thích & Ghi nhớ kiến thức",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkJade
                  )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                  text = lessonExplanation,
                  fontSize = 13.sp,
                  lineHeight = 19.sp,
                  color = DarkText
                )
              }
            }
          }

          // Header for Vocabulary / Sentences Review
          item {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier.padding(top = 4.dp, bottom = 2.dp)
            ) {
              Icon(
                imageVector = Icons.Filled.MenuBook,
                contentDescription = null,
                tint = DarkJade,
                modifier = Modifier.size(18.dp)
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "Ôn lại Nội dung bài học (${reviewItems.size})",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = DarkJade
              )
            }
          }

          // List of Vocabulary & Phrase Review Cards
          items(reviewItems) { item ->
            ReviewItemCard(
              item = item,
              onPlayAudio = onPlayAudio
            )
          }
        }

        // 3. BOTTOM ACTIONS BAR
        Surface(
          color = Color.White,
          shadowElevation = 8.dp,
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 16.dp, vertical = 12.dp)
          ) {
            Button(
              onClick = onNextLesson,
              modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .testTag("review_continue_button"),
              shape = RoundedCornerShape(14.dp),
              colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF00796B),
                contentColor = Color.White
              ),
              elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
            ) {
              Text(
                text = if (isLastLessonInCourse) "Hoàn tất & Tiếp tục trạm mới 🚀" else "Bắt đầu bài tiếp theo →",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
              )
            }

            if (isLastLessonInCourse) {
              Spacer(modifier = Modifier.height(8.dp))
              OutlinedButton(
                onClick = onExitToJourney,
                modifier = Modifier
                  .fillMaxWidth()
                  .height(44.dp)
                  .testTag("review_exit_journey_button"),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                  contentColor = DarkJade
                ),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFB0BEC5))
              ) {
                Text(
                  text = "Trở về Bản đồ thế giới 🗺️",
                  fontSize = 13.sp,
                  fontWeight = FontWeight.SemiBold,
                  color = DarkJade
                )
              }
            }
          }
        }
      }
    }
  }
}

/**
 * Individual Review Item Card showing Chinese Hanzi, Pinyin, Vietnamese meaning,
 * Audio replay button, and usage examples.
 */
@Composable
private fun ReviewItemCard(
  item: LessonReviewCardData,
  onPlayAudio: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  Card(
    shape = RoundedCornerShape(14.dp),
    colors = CardDefaults.cardColors(containerColor = Color.White),
    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE0DBD0)),
    modifier = modifier.fillMaxWidth()
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Column(modifier = Modifier.weight(1f)) {
          // Chinese Hanzi
          Text(
            text = item.hanzi,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = DarkJade
          )
          Spacer(modifier = Modifier.height(2.dp))
          // Pinyin
          Text(
            text = item.pinyin,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF00796B)
          )
        }

        // Audio Replay Button
        IconButton(
          onClick = { onPlayAudio(item.hanzi) },
          modifier = Modifier
            .size(38.dp)
            .clip(CircleShape)
            .background(JadeContainer)
        ) {
          Icon(
            imageVector = Icons.Filled.VolumeUp,
            contentDescription = "Nghe phát âm",
            tint = DarkJade,
            modifier = Modifier.size(20.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(6.dp))

      // Vietnamese Meaning
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(8.dp))
          .background(Color(0xFFF9F7F2))
          .padding(horizontal = 10.dp, vertical = 6.dp)
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(
            text = "🇻🇳 ",
            fontSize = 13.sp
          )
          Text(
            text = item.vietnameseMeaning,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = DarkText
          )
        }
      }

      // Usage Note / Example if available
      if (item.usageNote.isNotBlank()) {
        Spacer(modifier = Modifier.height(6.dp))
        Text(
          text = "💡 ${item.usageNote}",
          fontSize = 12.sp,
          color = SecondaryText,
          lineHeight = 16.sp
        )
      }

      if (item.exampleSentence.isNotBlank()) {
        Spacer(modifier = Modifier.height(4.dp))
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .background(Color(0xFFF0F4F2))
            .padding(8.dp)
        ) {
          Text(
            text = "Ví dụ: ${item.exampleSentence}",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = DarkJade
          )
          if (item.examplePinyin.isNotBlank()) {
            Text(
              text = item.examplePinyin,
              fontSize = 11.sp,
              color = Color(0xFF00796B)
            )
          }
          if (item.exampleTranslation.isNotBlank()) {
            Text(
              text = item.exampleTranslation,
              fontSize = 11.sp,
              color = SecondaryText
            )
          }
        }
      }
    }
  }
}

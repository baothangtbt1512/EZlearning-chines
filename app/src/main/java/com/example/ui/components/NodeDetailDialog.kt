package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.example.data.World1Curriculum
import com.example.model.JourneyNode
import com.example.model.NodeCourseData
import com.example.model.NodeIconType
import com.example.ui.theme.DarkJade
import com.example.ui.theme.DarkText
import com.example.ui.theme.JadeContainer
import com.example.ui.theme.PrimaryJade
import com.example.ui.theme.SecondaryText
import com.example.ui.theme.SoftBorder
import com.example.ui.theme.WarmCream
import com.example.ui.theme.WarmGold

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NodeDetailDialog(
  node: JourneyNode,
  nodeCourse: NodeCourseData?,
  onDismiss: () -> Unit,
  onStartLesson: (lessonIndex: Int) -> Unit,
  onPlayAudio: (String) -> Unit
) {
  val course = nodeCourse ?: World1Curriculum.getNodeCourse(node.id)
  val scrollState = rememberScrollState()

  Dialog(onDismissRequest = onDismiss) {
    Surface(
      shape = RoundedCornerShape(24.dp),
      color = Color.White,
      modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.85f)
        .border(1.dp, SoftBorder, RoundedCornerShape(24.dp))
        .testTag("node_detail_dialog")
    ) {
      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        // Top Close Row
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(10.dp))
              .background(DarkJade)
              .padding(horizontal = 10.dp, vertical = 4.dp)
          ) {
            Text(
              text = "THẾ GIỚI ${node.worldNumber} • CHẶNG HỌC",
              fontSize = 10.sp,
              fontWeight = FontWeight.Bold,
              color = Color.White,
              letterSpacing = 1.sp
            )
          }

          IconButton(
            onClick = onDismiss,
            modifier = Modifier.size(32.dp)
          ) {
            Icon(
              imageVector = Icons.Filled.Close,
              contentDescription = "Đóng",
              tint = SecondaryText
            )
          }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Column(
          modifier = Modifier
            .weight(1f)
            .verticalScroll(scrollState),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          // Destination Header Card
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(18.dp))
              .background(JadeContainer)
              .border(1.dp, Color(0xFFC6E5DC), RoundedCornerShape(18.dp))
              .padding(16.dp),
            contentAlignment = Alignment.Center
          ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Text(
                text = node.chinese.ifEmpty { "你好" },
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                color = DarkJade
              )
              if (node.pinyin.isNotEmpty()) {
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                  text = node.pinyin,
                  fontSize = 15.sp,
                  fontWeight = FontWeight.SemiBold,
                  color = PrimaryJade
                )
              }
              Spacer(modifier = Modifier.height(4.dp))
              Text(
                text = node.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText
              )
              Text(
                text = node.englishSummary.ifEmpty { course?.description ?: "Học tiếng Trung" },
                fontSize = 12.sp,
                color = SecondaryText
              )
            }
          }

          Spacer(modifier = Modifier.height(16.dp))

          // Vocabulary Flow Chips
          if (course != null && course.vocabulary.isNotEmpty()) {
            Text(
              text = "Từ vựng trong chặng này",
              fontSize = 13.sp,
              fontWeight = FontWeight.Bold,
              color = DarkJade,
              modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(8.dp))

            FlowRow(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(8.dp),
              verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              course.vocabulary.forEach { item ->
                Row(
                  modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFF6F3EB))
                    .border(1.dp, SoftBorder, RoundedCornerShape(12.dp))
                    .clickable { onPlayAudio(item.hanzi) }
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Text(
                    text = item.hanzi,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkJade
                  )
                  Spacer(modifier = Modifier.width(4.dp))
                  Text(
                    text = item.pinyin,
                    fontSize = 11.sp,
                    color = PrimaryJade
                  )
                  Spacer(modifier = Modifier.width(4.dp))
                  Icon(
                    imageVector = Icons.Filled.VolumeUp,
                    contentDescription = "Audio",
                    tint = PrimaryJade,
                    modifier = Modifier.size(14.dp)
                  )
                }
              }
            }

            Spacer(modifier = Modifier.height(18.dp))
          }

          // Micro-Lessons Track
          if (course != null && course.microLessons.isNotEmpty()) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = "Các bài học nhỏ (${course.microLessons.size})",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = DarkJade
              )
              Text(
                text = "${course.masteryPercentage}% Thành thạo",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = PrimaryJade
              )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(
              modifier = Modifier.fillMaxWidth(),
              verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              course.microLessons.forEachIndexed { index, lesson ->
                val isCompleted = lesson.isCompleted
                Row(
                  modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(if (isCompleted) Color(0xFFF2F9F6) else Color.White)
                    .border(1.dp, if (isCompleted) Color(0xFFC4E4DB) else SoftBorder, RoundedCornerShape(14.dp))
                    .clickable { onStartLesson(index) }
                    .padding(horizontal = 12.dp, vertical = 10.dp),
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.SpaceBetween
                ) {
                  Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                  ) {
                    Box(
                      modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(if (isCompleted) PrimaryJade else Color(0xFFEBE6DC)),
                      contentAlignment = Alignment.Center
                    ) {
                      if (isCompleted) {
                        Icon(
                          imageVector = Icons.Filled.CheckCircle,
                          contentDescription = null,
                          tint = Color.White,
                          modifier = Modifier.size(16.dp)
                        )
                      } else {
                        Text(
                          text = "${index + 1}",
                          fontSize = 12.sp,
                          fontWeight = FontWeight.Bold,
                          color = DarkText
                        )
                      }
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                      Text(
                        text = lesson.title,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isCompleted) PrimaryJade else DarkText
                      )
                      Text(
                        text = lesson.subtitle,
                        fontSize = 11.sp,
                        color = SecondaryText
                      )
                    }
                  }

                  Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = "Play lesson",
                    tint = PrimaryJade,
                    modifier = Modifier.size(18.dp)
                  )
                }
              }
            }
          }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // CTA Start Course Button
        Button(
          onClick = { onStartLesson(0) },
          modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .testTag("start_mini_course_button"),
          shape = RoundedCornerShape(16.dp),
          colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF00796B),
            contentColor = Color.White
          ),
          elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
              imageVector = Icons.Filled.PlayArrow,
              contentDescription = null,
              tint = WarmGold,
              modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = if (course?.isCompleted == true) "Ôn tập chặng học (+XP)" else "Bắt đầu học tương tác (+10 XP)",
              fontSize = 15.sp,
              fontWeight = FontWeight.Bold,
              color = Color.White
            )
          }
        }
      }
    }
  }
}

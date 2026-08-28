package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.LearnerState
import com.example.ui.components.AppHeaderBar
import com.example.ui.components.GreatWallHeroCanvas
import com.example.ui.components.PandaCompanionVector
import com.example.ui.components.TraditionalHouseIllustration
import com.example.ui.theme.DarkJade
import com.example.ui.theme.DarkText
import com.example.ui.theme.JadeContainer
import com.example.ui.theme.PrimaryJade
import com.example.ui.theme.SecondaryText
import com.example.ui.theme.SoftBorder
import com.example.ui.theme.WarmCream
import com.example.ui.theme.WarmGold

@Composable
fun WorldDetailScreen(
  state: LearnerState,
  onBackClick: () -> Unit,
  onCompleteLesson: () -> Unit,
  onPlayAudio: (String) -> Unit = {},
  modifier: Modifier = Modifier
) {
  val scrollState = rememberScrollState()
  var selectedPhraseIndex by remember { mutableStateOf(0) }

  val phrases = listOf(
    Triple("你好", "Nǐ hǎo", "Xin chào"),
    Triple("你好吗", "Nǐ hǎo ma", "Bạn có khỏe không?"),
    Triple("谢谢", "Xièxiè", "Cảm ơn"),
    Triple("再见", "Zàijiàn", "Tạm biệt")
  )

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(WarmCream)
      .testTag("world_detail_screen")
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
        .padding(bottom = 100.dp)
    ) {
      // 1. Hero Header
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(260.dp)
      ) {
        GreatWallHeroCanvas(modifier = Modifier.fillMaxSize())

        AppHeaderBar(
          showBackButton = true,
          streakDays = state.streakDays,
          onBackClick = onBackClick,
          modifier = Modifier.align(Alignment.TopCenter)
        )

        // World Info Overlay
        Row(
          modifier = Modifier
            .align(Alignment.BottomStart)
            .padding(start = 20.dp, bottom = 20.dp, end = 20.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Column(modifier = Modifier.weight(1f)) {
            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(PrimaryJade)
                .padding(horizontal = 8.dp, vertical = 3.dp)
            ) {
              Text(
                text = "THẾ GIỚI 1",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
              )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = "你好！ Lần Đầu Gặp Gỡ",
              fontSize = 22.sp,
              fontWeight = FontWeight.Bold,
              color = DarkJade
            )
            Text(
              text = "Chặng 1: Khởi Đầu",
              fontSize = 13.sp,
              color = SecondaryText
            )
          }

          TraditionalHouseIllustration(size = 90.dp)
        }
      }

      Spacer(modifier = Modifier.height(20.dp))

      // 2. Interactive Phrase Practice Card
      Box(
        modifier = Modifier
          .padding(horizontal = 16.dp)
          .fillMaxWidth()
          .shadow(elevation = 4.dp, shape = RoundedCornerShape(22.dp), spotColor = Color(0x15173B35))
          .clip(RoundedCornerShape(22.dp))
          .background(Color.White)
          .border(1.dp, SoftBorder, RoundedCornerShape(22.dp))
          .padding(18.dp)
      ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          Text(
            text = "Câu Chào Hỏi Thiết Yếu",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = DarkJade
          )
          Spacer(modifier = Modifier.height(2.dp))
          Text(
            text = "Chạm vào câu để luyện tập cùng Bảo Bảo",
            fontSize = 12.sp,
            color = SecondaryText
          )

          Spacer(modifier = Modifier.height(16.dp))

          // Large Active Phrase Card
          val currentPhrase = phrases[selectedPhraseIndex]
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(18.dp))
              .background(JadeContainer)
              .border(1.dp, Color(0xFFC4E4DB), RoundedCornerShape(18.dp))
              .clickable { onPlayAudio(currentPhrase.first) }
              .padding(20.dp),
            contentAlignment = Alignment.Center
          ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
              ) {
                Text(
                  text = currentPhrase.first,
                  fontSize = 38.sp,
                  fontWeight = FontWeight.ExtraBold,
                  color = DarkJade
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                  imageVector = Icons.Filled.VolumeUp,
                  contentDescription = "Phát âm",
                  tint = Color(0xFF00796B),
                  modifier = Modifier.size(24.dp)
                )
              }
              Spacer(modifier = Modifier.height(4.dp))
              Text(
                text = currentPhrase.second,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = PrimaryJade
              )
              Spacer(modifier = Modifier.height(4.dp))
              Text(
                text = currentPhrase.third,
                fontSize = 14.sp,
                color = DarkText
              )
            }
          }

          Spacer(modifier = Modifier.height(14.dp))

          // Phrase Selector Pills
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
          ) {
            phrases.forEachIndexed { index, item ->
              val isSelected = selectedPhraseIndex == index
              Box(
                modifier = Modifier
                  .clip(RoundedCornerShape(14.dp))
                  .background(if (isSelected) Color(0xFF00796B) else Color(0xFFF7F5F0))
                  .border(
                    width = if (isSelected) 2.dp else 1.dp,
                    color = if (isSelected) Color(0xFF004D40) else Color(0xFFDCD6CA),
                    shape = RoundedCornerShape(14.dp)
                  )
                  .clickable {
                    selectedPhraseIndex = index
                    onPlayAudio(item.first)
                  }
                  .padding(horizontal = 12.dp, vertical = 8.dp)
              ) {
                Text(
                  text = item.first,
                  fontSize = 14.sp,
                  fontWeight = FontWeight.Bold,
                  color = if (isSelected) Color.White else Color(0xFF1E293B)
                )
              }
            }
          }

          Spacer(modifier = Modifier.height(20.dp))

          // Complete Practice Button
          Button(
            onClick = onCompleteLesson,
            modifier = Modifier
              .fillMaxWidth()
              .height(50.dp)
              .testTag("complete_practice_button"),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
              containerColor = Color(0xFF00796B),
              contentColor = Color.White
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = null,
                tint = WarmGold,
                modifier = Modifier.size(20.dp)
              )
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = "Hoàn thành bài học (+10 XP)",
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
}

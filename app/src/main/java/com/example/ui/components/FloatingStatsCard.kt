package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Extension
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.DarkJade
import com.example.ui.theme.DarkText
import com.example.ui.theme.DestructiveRed
import com.example.ui.theme.LevelGreen
import com.example.ui.theme.SecondaryText
import com.example.ui.theme.WarmGold

@Composable
fun FloatingStatsCard(
  modifier: Modifier = Modifier,
  level: Int = 1,
  xp: Int = 0,
  streakDays: Int = 0
) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .shadow(
        elevation = 10.dp,
        shape = RoundedCornerShape(16.dp),
        ambientColor = Color(0x1A000000),
        spotColor = Color(0x1A000000)
      )
      .clip(RoundedCornerShape(16.dp))
      .background(Color.White)
      .padding(horizontal = 16.dp, vertical = 16.dp)
      .testTag("floating_stats_card")
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      // 1. Level Column
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
      ) {
        Box(
          modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(LevelGreen),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = "Level",
            tint = Color.White,
            modifier = Modifier.size(22.dp)
          )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column {
          Text(
            text = "CẤP ĐỘ",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = SecondaryText,
            letterSpacing = 0.5.sp
          )
          Text(
            text = level.toString(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = DarkJade
          )
        }
      }

      // Divider 1
      Box(
        modifier = Modifier
          .width(1.dp)
          .height(36.dp)
          .background(Color(0x33BFC9C3))
      )

      // 2. XP Column
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
      ) {
        Box(
          modifier = Modifier.size(40.dp),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Filled.Extension,
            contentDescription = "XP",
            tint = WarmGold,
            modifier = Modifier.size(28.dp)
          )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column {
          Text(
            text = "KINH NGHIỆM",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = SecondaryText,
            letterSpacing = 0.5.sp
          )
          Text(
            text = xp.toString(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = WarmGold
          )
        }
      }

      // Divider 2
      Box(
        modifier = Modifier
          .width(1.dp)
          .height(36.dp)
          .background(Color(0x33BFC9C3))
      )

      // 3. Streak Column
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
      ) {
        Box(
          modifier = Modifier.size(40.dp),
          contentAlignment = Alignment.Center
        ) {
          Text(
            text = "🔥",
            fontSize = 24.sp
          )
        }
        Spacer(modifier = Modifier.width(6.dp))
        Column {
          Text(
            text = "CHUỖI NGÀY",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = SecondaryText,
            letterSpacing = 0.5.sp
          )
          Text(
            text = buildAnnotatedString {
              append(streakDays.toString())
              append(" ")
              withStyle(style = SpanStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal)) {
                append("ngày")
              }
            },
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = DestructiveRed
          )
        }
      }
    }
  }
}

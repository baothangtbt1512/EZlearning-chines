package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.DarkJade
import com.example.ui.theme.PrimaryJade

/**
 * BUBU (Panda companion) vector component.
 */
@Composable
fun PandaCompanionVector(
  modifier: Modifier = Modifier,
  size: Dp = 120.dp,
  showWavingArm: Boolean = true
) {
  BubuVector(
    modifier = modifier,
    size = size,
    pose = if (showWavingArm) CharacterPose.WAVING else CharacterPose.STANDING_HAPPY
  )
}

/**
 * BUBU Avatar component with optional level badge.
 */
@Composable
fun PandaAvatar(
  modifier: Modifier = Modifier,
  size: Dp = 40.dp,
  level: Int? = null
) {
  Box(modifier = modifier, contentAlignment = Alignment.Center) {
    BubuAvatar(
      size = size
    )

    if (level != null) {
      Box(
        modifier = Modifier
          .align(Alignment.BottomEnd)
          .offset(x = 4.dp, y = 2.dp)
          .size(22.dp)
          .shadow(2.dp, CircleShape)
          .clip(CircleShape)
          .background(DarkJade)
          .border(1.5.dp, Color.White, CircleShape),
        contentAlignment = Alignment.Center
      ) {
        Text(
          text = level.toString(),
          color = Color.White,
          fontSize = 11.sp,
          fontWeight = FontWeight.Bold
        )
      }
    }
  }
}

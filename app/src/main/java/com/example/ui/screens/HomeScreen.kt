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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.model.JourneyNode
import com.example.model.LearnerState
import com.example.ui.components.BubuAvatar
import com.example.ui.components.DailyGoalCard
import com.example.ui.components.DuduAndBubuAvatar
import com.example.ui.components.DuduAvatar
import com.example.ui.components.FloatingStatsCard
import com.example.ui.components.GreatWallHeroCanvas
import com.example.ui.components.WorldCard
import com.example.ui.components.YourJourneyPreviewCard
import com.example.ui.theme.DarkJade
import com.example.ui.theme.DarkText
import com.example.ui.theme.DestructiveRed
import com.example.ui.theme.PrimaryJade
import com.example.ui.theme.SecondaryText
import com.example.ui.theme.WarmCream

private const val HERO_BG_URL = "https://lh3.googleusercontent.com/aida-public/AB6AXuCQlwH-FhrR85uakW9Z1V6FBEaC5nIRkNrne2p60D9XY8wqis7mMVf33fGtKBd0ltPwBFjZ8jNKlyB_65FOuIVv60xmrOCweEbpM3maYWyfCdeS7XYiJtQUuMnSuwPJxmDTQo5ypcy8gKar4jD4vdI0CCTAnjbbD84yeV-qPjT_L47UW1mEqWiu7x7xB0_gTydllDXXbOPW8SBXql782MdIzTC9ppDsO8Ppji7jff7lXpCK3UbMKkLptw"

@Composable
fun HomeScreen(
  state: LearnerState,
  onContinueAdventureClick: () -> Unit,
  onViewJourneyClick: () -> Unit,
  onSettingsClick: () -> Unit,
  modifier: Modifier = Modifier,
  onNodeClick: (JourneyNode) -> Unit = {}
) {
  val scrollState = rememberScrollState()

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(WarmCream)
      .testTag("home_screen")
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
        .padding(bottom = 96.dp)
    ) {
      // 1. Hero Section with Background Illustration & Overlay
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(390.dp)
          .clip(RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp))
      ) {
        // Fallback Vector Landscape
        GreatWallHeroCanvas(modifier = Modifier.fillMaxSize())

        // Cinematic Great Wall Heritage Background Image
        AsyncImage(
          model = HERO_BG_URL,
          contentDescription = "Hình nền Vạn Lý Trường Thành",
          modifier = Modifier.fillMaxSize(),
          contentScale = ContentScale.Crop
        )

        // Gradient overlay to WarmCream at the bottom
        Box(
          modifier = Modifier
            .fillMaxSize()
            .background(
              Brush.verticalGradient(
                colors = listOf(
                  Color.Transparent,
                  Color(0x20FFF8F0),
                  WarmCream.copy(alpha = 0.85f),
                  WarmCream
                ),
                startY = 180f
              )
            )
        )

        // Mobile Top Bar Overlay
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 20.dp, vertical = 12.dp)
            .align(Alignment.TopCenter),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          // Left: Active Companion Avatar + "Học Tiếng Trung"
          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable(onClick = onSettingsClick)
          ) {
            if (state.avatar == "bubu") {
              BubuAvatar(size = 46.dp)
            } else {
              DuduAvatar(size = 46.dp)
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column(
              verticalArrangement = Arrangement.Center
            ) {
              Text(
                text = if (state.avatar == "bubu") "BUBU 🐼" else "DUDU 🐻",
                fontSize = 17.sp,
                fontWeight = FontWeight.ExtraBold,
                color = DarkJade,
                lineHeight = 18.sp
              )
              Text(
                text = "Bạn Đồng Hành",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryJade,
                lineHeight = 16.sp
              )
            }
          }

          // Right: Streak pill badge
          Row(
            modifier = Modifier
              .shadow(3.dp, CircleShape)
              .clip(CircleShape)
              .background(Color.White.copy(alpha = 0.92f))
              .clickable(onClick = onSettingsClick)
              .padding(horizontal = 12.dp, vertical = 6.dp)
              .testTag("home_streak_pill"),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "🔥",
              fontSize = 16.sp
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
              text = state.streakDays.toString(),
              fontSize = 14.sp,
              fontWeight = FontWeight.Bold,
              color = DarkText
            )
            Spacer(modifier = Modifier.width(2.dp))
            Icon(
              imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
              contentDescription = null,
              tint = SecondaryText,
              modifier = Modifier.size(16.dp)
            )
          }
        }

        // Welcome Text Overlay near bottom of Hero
        Column(
          modifier = Modifier
            .align(Alignment.BottomStart)
            .padding(start = 20.dp, bottom = 82.dp)
        ) {
          Text(
            text = "你好, ${state.name}!",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = DarkJade,
            lineHeight = 40.sp
          )
          Spacer(modifier = Modifier.height(4.dp))
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(10.dp))
              .background(Color.White.copy(alpha = 0.55f))
              .padding(horizontal = 10.dp, vertical = 6.dp)
          ) {
            Text(
              text = "Sẵn sàng phiêu lưu tiếng Trung cùng DUDU & BUBU?",
              fontSize = 14.sp,
              fontWeight = FontWeight.Medium,
              color = SecondaryText,
              lineHeight = 18.sp
            )
          }
        }
      }

      // Content stack overlapping hero bottom
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .offset(y = (-64).dp)
          .padding(horizontal = 20.dp)
      ) {
        // 1. Floating Stats Card
        FloatingStatsCard(
          level = state.level,
          xp = state.xp,
          streakDays = state.streakDays
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 2. Current World Card (Adventure Card)
        WorldCard(
          state = state,
          onContinueClick = onContinueAdventureClick
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 3. Your Journey Section Card
        YourJourneyPreviewCard(
          state = state,
          onViewJourneyClick = onViewJourneyClick,
          onNodeClick = onNodeClick
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 4. Daily Goal Section Card
        DailyGoalCard(
          currentXp = state.dailyGoalCurrent,
          targetXp = state.dailyGoalTarget
        )
      }
    }
  }
}

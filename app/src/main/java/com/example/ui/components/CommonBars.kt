package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.CoralStreak
import com.example.ui.theme.DarkJade
import com.example.ui.theme.DarkText
import com.example.ui.theme.Ivory
import com.example.ui.theme.LockedGray
import com.example.ui.theme.PrimaryJade
import com.example.ui.theme.SecondaryText
import com.example.ui.theme.SoftBorder
import com.example.viewmodel.AppTab

@Composable
fun AppHeaderBar(
  modifier: Modifier = Modifier,
  showBackButton: Boolean = false,
  title: String? = null,
  isJourneyHeader: Boolean = false,
  streakDays: Int = 0,
  onBackClick: () -> Unit = {},
  onSettingsClick: () -> Unit = {},
  onStreakClick: () -> Unit = {}
) {
  Row(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 10.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    // Left Section
    Row(
      verticalAlignment = Alignment.CenterVertically
    ) {
      if (showBackButton) {
        Box(
          modifier = Modifier
            .size(38.dp)
            .shadow(2.dp, CircleShape)
            .clip(CircleShape)
            .background(Color.White)
            .border(1.dp, SoftBorder, CircleShape)
            .clickable(onClick = onBackClick)
            .testTag("back_button"),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = DarkText,
            modifier = Modifier.size(20.dp)
          )
        }
        Spacer(modifier = Modifier.width(10.dp))
      }

      if (isJourneyHeader) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Filled.Map,
            contentDescription = "Map",
            tint = PrimaryJade,
            modifier = Modifier.size(22.dp)
          )
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = "Hành Trình Của Bạn",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText
          )
        }
      } else {
        // Logo with panda avatar + "Học Tiếng Trung"
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.clickable(onClick = {})
        ) {
          PandaAvatar(size = 38.dp)
          Spacer(modifier = Modifier.width(8.dp))
          Column {
            Text(
              text = "Học",
              fontSize = 14.sp,
              fontWeight = FontWeight.Bold,
              color = DarkJade,
              lineHeight = 15.sp
            )
            Text(
              text = "Tiếng Trung",
              fontSize = 14.sp,
              fontWeight = FontWeight.Bold,
              color = DarkJade,
              lineHeight = 15.sp
            )
          }
        }
      }
    }

    // Right Section
    if (isJourneyHeader) {
      // Streak Pill on Journey screen
      Row(
        modifier = Modifier
          .shadow(2.dp, RoundedCornerShape(20.dp))
          .clip(RoundedCornerShape(20.dp))
          .background(Color.White)
          .border(1.dp, SoftBorder, RoundedCornerShape(20.dp))
          .clickable(onClick = onStreakClick)
          .padding(horizontal = 10.dp, vertical = 6.dp)
          .testTag("journey_streak_pill"),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(text = "🔥", fontSize = 16.sp)
        Spacer(modifier = Modifier.width(4.dp))
        Text(
          text = streakDays.toString(),
          fontSize = 14.sp,
          fontWeight = FontWeight.Bold,
          color = DarkText
        )
        Spacer(modifier = Modifier.width(2.dp))
        Icon(
          imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
          contentDescription = "Chi tiết chuỗi ngày",
          tint = SecondaryText,
          modifier = Modifier.size(16.dp)
        )
      }
    } else {
      // Settings Gear Button
      Box(
        modifier = Modifier
          .shadow(2.dp, RoundedCornerShape(20.dp))
          .clip(RoundedCornerShape(20.dp))
          .background(Color.White)
          .border(1.dp, SoftBorder, RoundedCornerShape(20.dp))
          .clickable(onClick = onSettingsClick)
          .padding(horizontal = 10.dp, vertical = 7.dp)
          .testTag("settings_button"),
        contentAlignment = Alignment.Center
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Filled.Settings,
            contentDescription = "Cài đặt",
            tint = DarkText,
            modifier = Modifier.size(18.dp)
          )
          Spacer(modifier = Modifier.width(2.dp))
          Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = SecondaryText,
            modifier = Modifier.size(14.dp)
          )
        }
      }
    }
  }
}

@Composable
fun AppBottomNavigation(
  activeTab: AppTab,
  onTabSelected: (AppTab) -> Unit,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .shadow(elevation = 16.dp, shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp), spotColor = Color(0x20000000))
      .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
      .background(Color(0xFFF5F3F3))
      .border(1.dp, Color(0x30BFC9C3), RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp, vertical = 6.dp),
      horizontalArrangement = Arrangement.SpaceAround,
      verticalAlignment = Alignment.CenterVertically
    ) {
      // Home Tab
      BottomNavItem(
        icon = if (activeTab == AppTab.HOME) Icons.Filled.Home else Icons.Outlined.Home,
        label = "Trang chủ",
        isSelected = activeTab == AppTab.HOME,
        testTag = "nav_home",
        onClick = { onTabSelected(AppTab.HOME) }
      )

      // Journey Tab
      BottomNavItem(
        icon = if (activeTab == AppTab.JOURNEY) Icons.Filled.Map else Icons.Outlined.Map,
        label = "Hành trình",
        isSelected = activeTab == AppTab.JOURNEY,
        testTag = "nav_journey",
        onClick = { onTabSelected(AppTab.JOURNEY) }
      )

      // Profile Tab
      BottomNavItem(
        icon = if (activeTab == AppTab.PROFILE) Icons.Filled.Person else Icons.Outlined.Person,
        label = "Hồ sơ",
        isSelected = activeTab == AppTab.PROFILE,
        testTag = "nav_profile",
        onClick = { onTabSelected(AppTab.PROFILE) }
      )
    }
  }
}

@Composable
private fun BottomNavItem(
  icon: androidx.compose.ui.graphics.vector.ImageVector,
  label: String,
  isSelected: Boolean,
  testTag: String,
  onClick: () -> Unit
) {
  val interactionSource = remember { MutableInteractionSource() }

  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
      .clickable(
        interactionSource = interactionSource,
        indication = null,
        onClick = onClick
      )
      .width(64.dp)
      .padding(vertical = 4.dp)
      .testTag(testTag)
  ) {
    // Top border indicator for active tab
    Box(
      modifier = Modifier
        .width(32.dp)
        .height(3.dp)
        .clip(RoundedCornerShape(2.dp))
        .background(if (isSelected) PrimaryJade else Color.Transparent)
    )

    Spacer(modifier = Modifier.height(4.dp))

    Icon(
      imageVector = icon,
      contentDescription = label,
      tint = if (isSelected) PrimaryJade else LockedGray,
      modifier = Modifier.size(24.dp)
    )

    Spacer(modifier = Modifier.height(2.dp))

    Text(
      text = label,
      fontSize = 11.sp,
      fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
      color = if (isSelected) PrimaryJade else SecondaryText
    )
  }
}

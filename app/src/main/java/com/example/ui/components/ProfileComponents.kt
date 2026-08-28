package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.AchievementItem
import com.example.model.SkillType
import com.example.ui.theme.DarkJade
import com.example.ui.theme.DarkText
import com.example.ui.theme.JadeContainer
import com.example.ui.theme.LockedGray
import com.example.ui.theme.PrimaryJade
import com.example.ui.theme.SecondaryText
import com.example.ui.theme.SoftBorder
import com.example.ui.theme.WarmGold

@Composable
fun LearningSkillsSection(
  skills: Map<SkillType, Int>,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .shadow(elevation = 3.dp, shape = RoundedCornerShape(20.dp), spotColor = Color(0x15004D40))
      .clip(RoundedCornerShape(20.dp))
      .background(Color.White)
      .border(1.5.dp, Color(0xFFDCD6CA), RoundedCornerShape(20.dp))
      .padding(18.dp)
      .testTag("learning_skills_card")
  ) {
    Column {
      Text(
        text = "KỸ NĂNG NGÔN NGỮ (SKILLS)",
        fontSize = 12.sp,
        fontWeight = FontWeight.ExtraBold,
        color = Color(0xFF004D40),
        letterSpacing = 1.sp,
        modifier = Modifier.padding(bottom = 12.dp)
      )

      SkillRowItem(
        icon = {
          Icon(
            imageVector = Icons.Filled.MenuBook,
            contentDescription = null,
            tint = Color(0xFF00796B),
            modifier = Modifier.size(20.dp)
          )
        },
        name = "Từ vựng",
        percentage = skills[SkillType.VOCABULARY] ?: 0
      )

      Spacer(modifier = Modifier.height(14.dp))

      SkillRowItem(
        icon = {
          Icon(
            imageVector = Icons.Filled.Headphones,
            contentDescription = null,
            tint = Color(0xFF00796B),
            modifier = Modifier.size(20.dp)
          )
        },
        name = "Luyện nghe",
        percentage = skills[SkillType.LISTENING] ?: 0
      )

      Spacer(modifier = Modifier.height(14.dp))

      SkillRowItem(
        icon = {
          Icon(
            imageVector = Icons.Filled.Mic,
            contentDescription = null,
            tint = Color(0xFF00796B),
            modifier = Modifier.size(20.dp)
          )
        },
        name = "Luyện nói",
        percentage = skills[SkillType.SPEAKING] ?: 0
      )

      Spacer(modifier = Modifier.height(14.dp))

      SkillRowItem(
        icon = {
          Icon(
            imageVector = Icons.Filled.GraphicEq,
            contentDescription = null,
            tint = Color(0xFF00796B),
            modifier = Modifier.size(20.dp)
          )
        },
        name = "Thanh điệu",
        percentage = skills[SkillType.PRONUNCIATION] ?: 0
      )

      Spacer(modifier = Modifier.height(14.dp))

      SkillRowItem(
        icon = {
          Text(
            text = "Aa",
            fontSize = 17.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFF00796B)
          )
        },
        name = "Ngữ pháp",
        percentage = skills[SkillType.GRAMMAR] ?: 0
      )
    }
  }
}

@Composable
fun SkillRowItem(
  icon: @Composable () -> Unit,
  name: String,
  percentage: Int
) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically
  ) {
    // Icon in soft jade circular frame
    Box(
      modifier = Modifier
        .size(36.dp)
        .clip(CircleShape)
        .background(Color(0xFFE0F2F1))
        .border(1.dp, Color(0xFFB2DFDB), CircleShape),
      contentAlignment = Alignment.Center
    ) {
      icon()
    }

    Spacer(modifier = Modifier.width(12.dp))

    // Skill Name
    Text(
      text = name,
      fontSize = 14.sp,
      fontWeight = FontWeight.Bold,
      color = Color(0xFF1E293B),
      modifier = Modifier.width(100.dp)
    )

    Spacer(modifier = Modifier.width(8.dp))

    // Progress Bar
    val progress = (percentage.toFloat() / 100f).coerceIn(0f, 1f)
    LinearProgressIndicator(
      progress = { if (progress == 0f) 0.04f else progress },
      modifier = Modifier
        .weight(1f)
        .height(8.dp)
        .clip(RoundedCornerShape(4.dp)),
      color = Color(0xFF00796B),
      trackColor = Color(0xFFEFECE5)
    )

    Spacer(modifier = Modifier.width(10.dp))

    // Percentage
    Text(
      text = "$percentage%",
      fontSize = 14.sp,
      fontWeight = FontWeight.Bold,
      color = Color(0xFF004D40)
    )
  }
}

@Composable
fun AchievementsSection(
  achievements: List<AchievementItem>,
  onViewAllClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(modifier = modifier.fillMaxWidth()) {
    // Section Header
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 4.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
          text = "🏆",
          fontSize = 18.sp
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = "Thành tích (Achievements)",
          fontSize = 17.sp,
          fontWeight = FontWeight.Bold,
          color = Color(0xFF004D40)
        )
      }

      Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
          .clip(RoundedCornerShape(10.dp))
          .background(Color(0xFFE0F2F1))
          .border(1.dp, Color(0xFFB2DFDB), RoundedCornerShape(10.dp))
          .clickable(onClick = onViewAllClick)
          .padding(horizontal = 10.dp, vertical = 4.dp)
      ) {
        Text(
          text = "Xem tất cả",
          fontSize = 12.sp,
          fontWeight = FontWeight.Bold,
          color = Color(0xFF004D40)
        )
        Spacer(modifier = Modifier.width(2.dp))
        Icon(
          imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
          contentDescription = null,
          tint = Color(0xFF004D40),
          modifier = Modifier.size(16.dp)
        )
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    // 3 Achievement Cards Row
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      achievements.forEach { achievement ->
        AchievementCard(
          item = achievement,
          modifier = Modifier.weight(1f)
        )
      }
    }
  }
}

@Composable
fun AchievementCard(
  item: AchievementItem,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .shadow(elevation = 2.dp, shape = RoundedCornerShape(18.dp), spotColor = Color(0x15004D40))
      .clip(RoundedCornerShape(18.dp))
      .background(Color.White)
      .border(1.5.dp, Color(0xFFDCD6CA), RoundedCornerShape(18.dp))
      .padding(10.dp)
      .testTag("achievement_${item.id}"),
    contentAlignment = Alignment.Center
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // Illustrated Art in frame
      Box(
        modifier = Modifier
          .size(54.dp)
          .clip(RoundedCornerShape(14.dp))
          .background(Color(0xFFF9F6EE))
          .border(1.dp, Color(0xFFE8DFC8), RoundedCornerShape(14.dp)),
        contentAlignment = Alignment.Center
      ) {
        when (item.id) {
          "first_words" -> Text(
            text = "你好",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF00796B)
          )
          "first_conversation" -> Icon(
            imageVector = Icons.Filled.ChatBubble,
            contentDescription = null,
            tint = Color(0xFF00897B),
            modifier = Modifier.size(28.dp)
          )
          "world_explorer" -> MapScrollMiniIcon(modifier = Modifier.size(34.dp))
        }
      }

      Spacer(modifier = Modifier.height(8.dp))

      // Lock badge or unlocked star
      if (!item.isUnlocked) {
        Box(
          modifier = Modifier
            .size(20.dp)
            .clip(CircleShape)
            .background(Color(0xFF8A8275)),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Filled.Lock,
            contentDescription = "Locked",
            tint = Color.White,
            modifier = Modifier.size(12.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(6.dp))

      // Title
      Text(
        text = item.title,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF1E293B),
        maxLines = 1
      )

      // Locked label
      Text(
        text = if (item.isUnlocked) "Đã mở khóa" else "Đang khóa",
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        color = if (item.isUnlocked) Color(0xFF2E7D32) else Color(0xFF8A8275)
      )
    }
  }
}

@Composable
fun MapScrollMiniIcon(modifier: Modifier = Modifier) {
  Canvas(modifier = modifier) {
    val w = size.width
    val h = size.height

    // Ancient Map Scroll illustration
    val scrollPath = Path().apply {
      moveTo(w * 0.15f, h * 0.25f)
      lineTo(w * 0.85f, h * 0.25f)
      lineTo(w * 0.85f, h * 0.75f)
      lineTo(w * 0.15f, h * 0.75f)
      close()
    }
    drawPath(scrollPath, color = Color(0xFFE4D5B8))

    // Scroll Rollers
    drawRoundRect(
      color = Color(0xFF8C6239),
      topLeft = Offset(w * 0.10f, h * 0.18f),
      size = Size(w * 0.08f, h * 0.64f),
      cornerRadius = CornerRadius(2f, 2f)
    )
    drawRoundRect(
      color = Color(0xFF8C6239),
      topLeft = Offset(w * 0.82f, h * 0.18f),
      size = Size(w * 0.08f, h * 0.64f),
      cornerRadius = CornerRadius(2f, 2f)
    )

    // Map continent outlines
    drawLine(color = Color(0xFF6B8E7D), start = Offset(w * 0.35f, h * 0.45f), end = Offset(w * 0.55f, h * 0.40f), strokeWidth = 3f)
    drawLine(color = Color(0xFF6B8E7D), start = Offset(w * 0.55f, h * 0.40f), end = Offset(w * 0.65f, h * 0.58f), strokeWidth = 3f)
  }
}

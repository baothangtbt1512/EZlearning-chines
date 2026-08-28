package com.example.ui.components

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
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.DarkJade
import com.example.ui.theme.DarkText
import com.example.ui.theme.DestructiveRed
import com.example.ui.theme.SecondaryText

val PrimaryFixedMint = Color(0xFFE0F2F1)
val PrimaryContainerDark = Color(0xFF00796B)
val ErrorContainerBg = Color(0xFFFFF2F0)
val ErrorIconBg = Color(0xFFFFDCD8)
val ErrorTextDark = Color(0xFFB71C1C)
val ErrorTextLight = Color(0xFF7F2626)
val OutlineVariantLight = Color(0xFFDCD6CA)
val CardSurfaceBg = Color(0xFFFBF9F8)

@Composable
fun SettingsSectionCard(
  sectionTitle: String,
  sectionIcon: ImageVector,
  modifier: Modifier = Modifier,
  content: @Composable () -> Unit
) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .shadow(
        elevation = 3.dp,
        shape = RoundedCornerShape(18.dp),
        spotColor = Color(0x12004D40)
      )
      .clip(RoundedCornerShape(18.dp))
      .background(Color.White)
      .border(1.5.dp, OutlineVariantLight.copy(alpha = 0.5f), RoundedCornerShape(18.dp))
  ) {
    Column {
      // Section Header with dark circular icon and uppercase title
      Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 18.dp, vertical = 14.dp)
      ) {
        Box(
          modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(Color(0xFF00796B)),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = sectionIcon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(18.dp)
          )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
          text = sectionTitle.uppercase(),
          fontSize = 12.sp,
          fontWeight = FontWeight.ExtraBold,
          color = Color(0xFF004D40),
          letterSpacing = 1.sp
        )
      }

      HorizontalDivider(color = OutlineVariantLight.copy(alpha = 0.40f), thickness = 1.dp)

      Column(modifier = Modifier.padding(horizontal = 18.dp, vertical = 4.dp)) {
        content()
      }
    }
  }
}

@Composable
fun SettingsRow(
  icon: ImageVector,
  title: String,
  subtitle: String,
  modifier: Modifier = Modifier,
  badgeText: String? = null,
  showSwitch: Boolean = false,
  switchChecked: Boolean = true,
  showDivider: Boolean = true,
  onSwitchToggle: ((Boolean) -> Unit)? = null,
  onClick: (() -> Unit)? = null
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
        .padding(vertical = 14.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.weight(1f)
      ) {
        // Icon in light mint circle
        Box(
          modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(Color(0xFFE0F2F1))
            .border(1.dp, Color(0xFFB2DFDB), CircleShape),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF00796B),
            modifier = Modifier.size(22.dp)
          )
        }

        Spacer(modifier = Modifier.width(14.dp))

        Column(modifier = Modifier.padding(end = 8.dp)) {
          Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1E293B)
          )
          Spacer(modifier = Modifier.height(2.dp))
          Text(
            text = subtitle,
            fontSize = 13.sp,
            color = Color(0xFF4A5568),
            lineHeight = 17.sp
          )
        }
      }

      if (showSwitch) {
        Switch(
          checked = switchChecked,
          onCheckedChange = { onSwitchToggle?.invoke(it) },
          colors = SwitchDefaults.colors(
            checkedThumbColor = Color.White,
            checkedTrackColor = Color(0xFF00796B),
            uncheckedThumbColor = Color.White,
            uncheckedTrackColor = Color(0xFFD4CDC0)
          ),
          modifier = Modifier.testTag("switch_$title")
        )
      } else if (badgeText != null) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.padding(start = 4.dp)
        ) {
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(8.dp))
              .background(Color(0xFFE0F2F1))
              .padding(horizontal = 8.dp, vertical = 3.dp)
          ) {
            Text(
              text = badgeText,
              fontSize = 14.sp,
              fontWeight = FontWeight.Bold,
              color = Color(0xFF004D40)
            )
          }
          Spacer(modifier = Modifier.width(4.dp))
          Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = Color(0xFF5A6872),
            modifier = Modifier.size(20.dp)
          )
        }
      } else {
        Icon(
          imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
          contentDescription = null,
          tint = Color(0xFF5A6872),
          modifier = Modifier.size(20.dp)
        )
      }
    }

    if (showDivider) {
      HorizontalDivider(color = OutlineVariantLight.copy(alpha = 0.35f), thickness = 1.dp)
    }
  }
}

@Composable
fun ResetProgressCard(
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .shadow(elevation = 2.dp, shape = RoundedCornerShape(18.dp), spotColor = Color(0x20B71C1C))
      .clip(RoundedCornerShape(18.dp))
      .background(ErrorContainerBg)
      .border(1.5.dp, Color(0xFFFFCDD2), RoundedCornerShape(18.dp))
      .clickable(onClick = onClick)
      .padding(18.dp)
      .testTag("reset_progress_card")
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
        Box(
          modifier = Modifier
            .size(42.dp)
            .clip(CircleShape)
            .background(ErrorIconBg)
            .border(1.dp, Color(0xFFFFB4AB), CircleShape),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = null,
            tint = ErrorTextDark,
            modifier = Modifier.size(22.dp)
          )
        }

        Spacer(modifier = Modifier.width(14.dp))

        Column(modifier = Modifier.padding(end = 8.dp)) {
          Text(
            text = "Reset Progress",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = ErrorTextDark
          )
          Spacer(modifier = Modifier.height(2.dp))
          Text(
            text = "This will erase all your progress and restart your journey.",
            fontSize = 13.sp,
            color = ErrorTextLight,
            lineHeight = 17.sp
          )
        }
      }

      Icon(
        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
        contentDescription = null,
        tint = ErrorTextDark,
        modifier = Modifier.size(22.dp)
      )
    }
  }
}

@Composable
fun ResetConfirmationDialog(
  onConfirm: () -> Unit,
  onDismiss: () -> Unit
) {
  AlertDialog(
    onDismissRequest = onDismiss,
    title = {
      Text(
        text = "Reset All Progress?",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF1E293B)
      )
    },
    text = {
      Text(
        text = "Are you sure you want to reset your learner progress? Your Level, XP, Streak, and unlocked destinations in World 1 will be restored to their initial state.",
        fontSize = 14.sp,
        color = Color(0xFF4A5568),
        lineHeight = 20.sp
      )
    },
    confirmButton = {
      Button(
        onClick = onConfirm,
        colors = ButtonDefaults.buttonColors(
          containerColor = Color(0xFFD32F2F),
          contentColor = Color.White
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
          .height(44.dp)
          .testTag("confirm_reset_button")
      ) {
        Text(
          text = "Reset Progress",
          fontWeight = FontWeight.Bold,
          color = Color.White,
          fontSize = 14.sp
        )
      }
    },
    dismissButton = {
      OutlinedButton(
        onClick = onDismiss,
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.outlinedButtonColors(
          containerColor = Color(0xFFF7F5F0),
          contentColor = Color(0xFF004D40)
        ),
        border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFFD4CDC0)),
        modifier = Modifier
          .height(44.dp)
          .testTag("cancel_reset_button")
      ) {
        Text(
          text = "Cancel",
          color = Color(0xFF004D40),
          fontWeight = FontWeight.Bold,
          fontSize = 14.sp
        )
      }
    },
    shape = RoundedCornerShape(22.dp),
    containerColor = Color.White
  )
}

@Composable
fun SettingsInfoDialog(
  title: String,
  message: String,
  onDismiss: () -> Unit
) {
  AlertDialog(
    onDismissRequest = onDismiss,
    title = {
      Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF004D40)
      )
    },
    text = {
      Text(
        text = message,
        fontSize = 14.sp,
        color = Color(0xFF4A5568),
        lineHeight = 20.sp
      )
    },
    confirmButton = {
      Button(
        onClick = onDismiss,
        colors = ButtonDefaults.buttonColors(
          containerColor = Color(0xFF00796B),
          contentColor = Color.White
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
          .fillMaxWidth()
          .height(46.dp)
      ) {
        Text(
          text = "Đã hiểu / Got It",
          fontWeight = FontWeight.Bold,
          color = Color.White,
          fontSize = 15.sp
        )
      }
    },
    shape = RoundedCornerShape(22.dp),
    containerColor = Color.White
  )
}

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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.VolumeUp
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.model.LearnerState
import com.example.ui.components.AchievementsSection
import com.example.ui.components.BubuAvatar
import com.example.ui.components.DuduAndBubuAvatar
import com.example.ui.components.DuduAvatar
import com.example.ui.components.EditNameDialog
import com.example.ui.components.GreatWallHeroCanvas
import com.example.ui.components.LearningSkillsSection
import com.example.ui.components.ResetProgressCard
import com.example.ui.components.SettingsInfoDialog
import com.example.ui.components.SettingsRow
import com.example.ui.components.SettingsSectionCard
import androidx.compose.material.icons.filled.Edit
import com.example.ui.components.EditNameDialog
import com.example.ui.theme.DarkJade
import com.example.ui.theme.DarkText
import com.example.ui.theme.PrimaryJade
import com.example.ui.theme.SecondaryText

private const val PROFILE_HEADER_BG_URL = "https://lh3.googleusercontent.com/aida-public/AB6AXuCm-DVzhqDomKUO3jJdwo_vKnqiAHry6ar798udZLtnVy4DL6YSE38kNPjURSMFu3toY6jk1Dx-Cmhayfud9AJ1CrRlUw4_ine_8nIYf7kybwTkLJg7gSOINqC9AELTjnBKPjv6tWVJLKcB15H9d85wUK_E84AMkCPKCCU9iunlRe3JHpCs0iGiGCBhdkhOzQEjqSdkrS8iXdfMt-5P2V1YDsM9wJVv992Q3kcpCb5wF7oaLMAc1VVMSw"

@Composable
fun ProfileScreen(
  state: LearnerState,
  onBackClick: () -> Unit,
  onResetClick: () -> Unit,
  onToggleSound: (Boolean) -> Unit,
  onToggleReminders: (Boolean) -> Unit,
  onUpdateName: (String) -> Unit = {},
  onUpdateProfile: (name: String, avatar: String) -> Unit = { n, _ -> onUpdateName(n) },
  modifier: Modifier = Modifier
) {
  val scrollState = rememberScrollState()
  var infoDialogContent by remember { mutableStateOf<Pair<String, String>?>(null) }
  var showEditNameDialog by remember { mutableStateOf(false) }

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(Color(0xFFFBF9F8))
      .testTag("profile_screen")
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
        .padding(bottom = 100.dp)
    ) {
      // Header Section with Cinematic Background (h-[340px])
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(340.dp)
          .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
      ) {
        // Fallback canvas
        GreatWallHeroCanvas(
          modifier = Modifier.fillMaxSize()
        )

        // Pagoda sunrise cinematic background
        AsyncImage(
          model = PROFILE_HEADER_BG_URL,
          contentDescription = "Cinematic Pagoda Landscape",
          modifier = Modifier.fillMaxSize(),
          contentScale = ContentScale.Crop
        )

        // Frost Overlay Gradient
        Box(
          modifier = Modifier
            .fillMaxSize()
            .background(
              Brush.verticalGradient(
                colors = listOf(
                  Color(0x00FBF9F8),
                  Color(0xAAFBF9F8),
                  Color(0xFFFBF9F8)
                ),
                startY = 0f,
                endY = Float.POSITIVE_INFINITY
              )
            )
        )

        // Top Navigation Bar
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 20.dp, vertical = 16.dp)
            .align(Alignment.TopCenter),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          // Left chevron button
          Box(
            modifier = Modifier
              .size(40.dp)
              .shadow(2.dp, CircleShape)
              .clip(CircleShape)
              .background(Color.White.copy(alpha = 0.95f))
              .border(1.dp, Color(0xFFDCD6CA), CircleShape)
              .clickable(onClick = onBackClick),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.AutoMirrored.Filled.ArrowBack,
              contentDescription = "Back",
              tint = Color(0xFF004D40),
              modifier = Modifier.size(20.dp)
            )
          }

          // Center: Active Mascot Avatar + "Hồ Sơ Học Tập"
          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable(onClick = onBackClick)
          ) {
            if (state.avatar == "bubu") {
              BubuAvatar(size = 38.dp)
            } else {
              DuduAvatar(size = 38.dp)
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column {
              Text(
                text = "Hồ sơ học tập",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF004D40)
              )
              Text(
                text = if (state.avatar == "bubu") "BUBU (Gấu trúc)" else "DUDU (Gấu nâu)",
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF00796B)
              )
            }
          }

          // Right: Settings Icon Button
          Box(
            modifier = Modifier
              .size(40.dp)
              .shadow(2.dp, CircleShape)
              .clip(CircleShape)
              .background(Color.White.copy(alpha = 0.95f))
              .border(1.dp, Color(0xFFDCD6CA), CircleShape),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Filled.Settings,
              contentDescription = "Settings",
              tint = Color(0xFF004D40),
              modifier = Modifier.size(20.dp)
            )
          }
        }

        // Profile Title & Stats Summary
        Column(
          modifier = Modifier
            .align(Alignment.BottomStart)
            .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = state.name.ifBlank { "Học viên" },
              fontSize = 28.sp,
              fontWeight = FontWeight.ExtraBold,
              color = Color(0xFF004D40)
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Edit name button
            Box(
              modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.9f))
                .border(1.dp, Color(0xFF80CBC4), CircleShape)
                .clickable { showEditNameDialog = true },
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = "Chỉnh sửa tên",
                tint = PrimaryJade,
                modifier = Modifier.size(16.dp)
              )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFE0F2F1))
                .border(1.dp, Color(0xFF80CBC4), RoundedCornerShape(8.dp))
                .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
              Text(
                text = "Cấp độ ${state.level}",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF004D40)
              )
            }
          }

          Spacer(modifier = Modifier.height(4.dp))

          Text(
            text = "Học viên khám phá tiếng Trung cùng DUDU & BUBU",
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF4A5568)
          )

          Spacer(modifier = Modifier.height(10.dp))

          // 3 Quick Stat Badges
          Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Box(
              modifier = Modifier
                .shadow(1.dp, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFDCD6CA), RoundedCornerShape(10.dp))
                .padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
              Text(
                text = "⚡ ${state.xp} XP",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF00796B)
              )
            }

            Box(
              modifier = Modifier
                .shadow(1.dp, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFDCD6CA), RoundedCornerShape(10.dp))
                .padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
              Text(
                text = "🔥 ${state.streakDays} Ngày streak",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFC05621)
              )
            }

            Box(
              modifier = Modifier
                .shadow(1.dp, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFDCD6CA), RoundedCornerShape(10.dp))
                .padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
              Text(
                text = "🌏 ${state.country}",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2B6CB0)
              )
            }
          }
        }
      }

      // Main Content Area
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .offset(y = (-8).dp)
          .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        // 1. Learning Skills Section
        LearningSkillsSection(
          skills = state.skills
        )

        // 2. Achievements Section
        AchievementsSection(
          achievements = state.achievements,
          onViewAllClick = {
            infoDialogContent = "Danh sách Thành tích (Achievements)" to "Bạn đã mở khóa ${state.achievements.count { it.isUnlocked }}/${state.achievements.size} danh hiệu! Tiếp tục hoàn thành các trạm phiêu lưu trong Vùng 1 để mở khóa thêm."
          }
        )

        // 3. Learning Experience Preferences Section
        SettingsSectionCard(
          sectionTitle = "Trải nghiệm học tập",
          sectionIcon = Icons.Filled.School
        ) {
          SettingsRow(
            icon = Icons.Filled.VolumeUp,
            title = "Hiệu ứng âm thanh & Giọng đọc",
            subtitle = "Phát âm tiếng Trung chuẩn và âm thanh tương tác",
            showSwitch = true,
            switchChecked = state.soundEffectsEnabled,
            showDivider = true,
            onSwitchToggle = onToggleSound
          )
          SettingsRow(
            icon = Icons.Filled.Notifications,
            title = "Nhắc nhở học tập hàng ngày",
            subtitle = "Duy trì chuỗi streak học tiếng Trung đều đặn",
            showSwitch = true,
            switchChecked = state.remindersEnabled,
            showDivider = false,
            onSwitchToggle = onToggleReminders
          )
        }

        // 4. Application & Information Section
        SettingsSectionCard(
          sectionTitle = "Thông tin & Hỗ trợ",
          sectionIcon = Icons.Filled.Apps
        ) {
          SettingsRow(
            icon = Icons.Filled.Person,
            title = "Thông tin người học",
            subtitle = "Xem chi tiết tiến trình cá nhân",
            showDivider = true,
            onClick = {
              infoDialogContent = "Hồ sơ người học" to "Họ tên: ${state.name}\nCấp độ: ${state.level} (${state.xp} XP)\nChuỗi Streak: ${state.streakDays} Ngày\nQuốc gia/Khu vực: ${state.country}"
            }
          )
          SettingsRow(
            icon = Icons.Filled.Language,
            title = "Khu vực học tập",
            subtitle = "Tối ưu hóa bài tập theo ngôn ngữ mẹ đẻ",
            badgeText = state.country,
            showDivider = true,
            onClick = {
              infoDialogContent = "Cài đặt Khu vực" to "Hiện tại đang thiết lập theo vùng: ${state.country}. Từ vựng, phiên âm pinyin và mẹo ghi nhớ được tối ưu cho tiếng Việt."
            }
          )
          SettingsRow(
            icon = Icons.Filled.Info,
            title = "Về DUDU & BUBU Học Tiếng Trung",
            subtitle = "Ứng dụng phiêu lưu học tiếng Trung tương tác",
            showDivider = true,
            onClick = {
              infoDialogContent = "DUDU & BUBU Học Tiếng Trung" to "Ứng dụng học tiếng Trung gamified phong cách cổ phong kết hợp bạn đồng hành DUDU & BUBU. Phiên bản 1.0.0."
            }
          )
          SettingsRow(
            icon = Icons.Filled.Help,
            title = "Trợ giúp & Phản hồi",
            subtitle = "Giải đáp thắc mắc về bài học và thanh điệu",
            showDivider = false,
            onClick = {
              infoDialogContent = "Trợ giúp & Phản hồi" to "Bạn cần hỗ trợ về quy tắc biến điệu, nét chữ Hán hoặc thanh mẫu? Liên hệ hỗ trợ tại support@dudububu.chinese.app."
            }
          )
        }

        // 5. Reset Progress Section
        ResetProgressCard(
          onClick = onResetClick
        )
      }
    }

    // Modal info dialogs
    infoDialogContent?.let { (title, msg) ->
      SettingsInfoDialog(
        title = title,
        message = msg,
        onDismiss = { infoDialogContent = null }
      )
    }

    // Modal edit name & avatar dialog
    if (showEditNameDialog) {
      EditNameDialog(
        currentName = state.name,
        currentAvatar = state.avatar,
        onConfirm = { newName, newAvatar ->
          onUpdateProfile(newName, newAvatar)
          showEditNameDialog = false
        },
        onDismiss = { showEditNameDialog = false }
      )
    }
  }
}

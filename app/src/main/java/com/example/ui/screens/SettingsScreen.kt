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
import com.example.ui.components.BubuAvatar
import com.example.ui.components.DuduAndBubuAvatar
import com.example.ui.components.DuduAvatar
import com.example.ui.components.EditNameDialog
import com.example.ui.components.GreatWallHeroCanvas
import com.example.ui.components.ResetProgressCard
import com.example.ui.components.SettingsInfoDialog
import com.example.ui.components.SettingsRow
import com.example.ui.components.SettingsSectionCard
import com.example.ui.theme.DarkJade
import com.example.ui.theme.DarkText
import com.example.ui.theme.SecondaryText

private const val SETTINGS_HEADER_BG_URL = "https://lh3.googleusercontent.com/aida-public/AB6AXuCm-DVzhqDomKUO3jJdwo_vKnqiAHry6ar798udZLtnVy4DL6YSE38kNPjURSMFu3toY6jk1Dx-Cmhayfud9AJ1CrRlUw4_ine_8nIYf7kybwTkLJg7gSOINqC9AELTjnBKPjv6tWVJLKcB15H9d85wUK_E84AMkCPKCCU9iunlRe3JHpCs0iGiGCBhdkhOzQEjqSdkrS8iXdfMt-5P2V1YDsM9wJVv992Q3kcpCb5wF7oaLMAc1VVMSw"

@Composable
fun SettingsScreen(
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
      .testTag("settings_screen")
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
        .padding(bottom = 100.dp)
    ) {
      // 1. Header Section with Cinematic Background (h-[320px])
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(320.dp)
          .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
      ) {
        // Fallback canvas if offline
        GreatWallHeroCanvas(
          modifier = Modifier.fillMaxSize()
        )

        // Cinematic Pagoda & Mountain Sunset Background Image
        AsyncImage(
          model = SETTINGS_HEADER_BG_URL,
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
                  Color(0xB3FBF9F8),
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
          // Back Button
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

          // Center: Active Companion Avatar + "Cài đặt & Tuỳ chọn"
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
                text = "Cài đặt & Tuỳ chọn",
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

        // Settings Title Area at bottom of header
        Column(
          modifier = Modifier
            .align(Alignment.BottomStart)
            .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
          Text(
            text = "Cài đặt hệ thống",
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFF004D40),
            lineHeight = 34.sp
          )
          Spacer(modifier = Modifier.height(2.dp))
          Text(
            text = "Tùy chỉnh trải nghiệm học tiếng Trung cùng DUDU & BUBU",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF4A5568)
          )
        }
      }

      // 2. Main Content Area
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .offset(y = (-14).dp)
          .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        // Section 1: Thông tin người học
        SettingsSectionCard(
          sectionTitle = "Thông tin người học",
          sectionIcon = Icons.Filled.Person
        ) {
          SettingsRow(
            icon = Icons.Filled.Person,
            title = "Tên hiển thị: ${state.name.ifBlank { "Học viên" }}",
            subtitle = "Chạm để đổi tên hiển thị trong bài học",
            badgeText = "✏️ Đổi tên",
            showDivider = true,
            onClick = {
              showEditNameDialog = true
            }
          )
          SettingsRow(
            icon = Icons.Filled.Person,
            title = "Bạn đồng hành: ${if (state.avatar == "bubu") "Bubu (Gấu trúc 🐼)" else "Dudu (Gấu nâu 🐻)"}",
            subtitle = "Nhân vật hướng dẫn phát âm và đồng hành",
            badgeText = "Thay đổi",
            showDivider = true,
            onClick = {
              showEditNameDialog = true
            }
          )
          SettingsRow(
            icon = Icons.Filled.Language,
            title = "Khu vực học tập",
            subtitle = "Nội dung và phiên âm pinyin tối ưu cho",
            badgeText = state.country,
            showDivider = false,
            onClick = {
              infoDialogContent = "Cài đặt Khu vực" to "Hiện tại đang thiết lập theo vùng: ${state.country}. Phiên âm pinyin và ngữ pháp được tối ưu hóa cho người học nói tiếng Việt."
            }
          )
        }

        // Section 2: Trải nghiệm học tập
        SettingsSectionCard(
          sectionTitle = "Trải nghiệm học tập",
          sectionIcon = Icons.Filled.School
        ) {
          SettingsRow(
            icon = Icons.Filled.VolumeUp,
            title = "Hiệu ứng âm thanh",
            subtitle = "Phát âm tiếng Trung và âm thanh tương tác",
            showSwitch = true,
            switchChecked = state.soundEffectsEnabled,
            showDivider = true,
            onSwitchToggle = onToggleSound
          )
          SettingsRow(
            icon = Icons.Filled.Notifications,
            title = "Nhắc nhở học tập",
            subtitle = "Thông báo nhắc duy trì chuỗi streak hàng ngày",
            showSwitch = true,
            switchChecked = state.remindersEnabled,
            showDivider = false,
            onSwitchToggle = onToggleReminders
          )
        }

        // Section 3: Ứng dụng & Hỗ trợ
        SettingsSectionCard(
          sectionTitle = "Ứng dụng & Hỗ trợ",
          sectionIcon = Icons.Filled.Apps
        ) {
          SettingsRow(
            icon = Icons.Filled.Info,
            title = "Về DUDU & BUBU Học Tiếng Trung",
            subtitle = "Ứng dụng phiêu lưu học tiếng Trung tương tác",
            showDivider = true,
            onClick = {
              infoDialogContent = "Về ứng dụng" to "DUDU & BUBU Học Tiếng Trung là ứng dụng học tiếng Trung gamified phong cách cổ phong kết hợp bạn đồng hành DUDU & BUBU. Phiên bản 1.0.0."
            }
          )
          SettingsRow(
            icon = Icons.Filled.Help,
            title = "Trợ giúp & Phản hồi",
            subtitle = "Giải đáp thắc mắc về bài học và thanh điệu",
            showDivider = true,
            onClick = {
              infoDialogContent = "Trợ giúp & Phản hồi" to "Bạn cần hỗ trợ về bài học hoặc kỹ thuật? Vui lòng liên hệ với chúng tôi qua email: support@dudububu.chinese.app."
            }
          )
          SettingsRow(
            icon = Icons.Filled.Security,
            title = "Chính sách bảo mật",
            subtitle = "Bảo vệ thông tin cá nhân của bạn",
            showDivider = true,
            onClick = {
              infoDialogContent = "Chính sách bảo mật" to "Tiến độ học tập và tên người học được lưu trữ an toàn ngay trên thiết bị của bạn (Local Storage)."
            }
          )
          SettingsRow(
            icon = Icons.Filled.Description,
            title = "Điều khoản sử dụng",
            subtitle = "Xem điều khoản dịch vụ",
            showDivider = false,
            onClick = {
              infoDialogContent = "Điều khoản sử dụng" to "Toàn bộ tài liệu học tập được biên soạn nhằm mục đích giáo dục cho học viên tiếng Trung."
            }
          )
        }

        // Section 4: Đặt lại tiến độ
        ResetProgressCard(
          onClick = onResetClick
        )
      }
    }

    // Informational popup dialog
    infoDialogContent?.let { (title, msg) ->
      SettingsInfoDialog(
        title = title,
        message = msg,
        onDismiss = { infoDialogContent = null }
      )
    }

    // Edit Name & Avatar dialog
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

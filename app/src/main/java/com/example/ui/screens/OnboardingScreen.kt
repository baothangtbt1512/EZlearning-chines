package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.BubuVector
import com.example.ui.components.CharacterEmotion
import com.example.ui.components.CharacterPose
import com.example.ui.components.DuduVector
import com.example.ui.components.GreatWallHeroCanvas
import com.example.ui.theme.DarkJade
import com.example.ui.theme.DarkText
import com.example.ui.theme.PrimaryJade
import com.example.ui.theme.SecondaryText
import com.example.ui.theme.WarmCream
import com.example.ui.theme.WarmGold

private const val MAX_NAME_LENGTH = 30

@Composable
fun OnboardingScreen(
  onComplete: (name: String, avatar: String) -> Unit,
  onPlayAudio: (String) -> Unit = {},
  modifier: Modifier = Modifier
) {
  var selectedAvatar by remember { mutableStateOf("dudu") } // "dudu" (Brown Bear) or "bubu" (Panda)
  var nameInput by remember { mutableStateOf("") }
  var hasAttemptedSubmit by remember { mutableStateOf(false) }
  val scrollState = rememberScrollState()
  val keyboardController = LocalSoftwareKeyboardController.current

  val trimmedName = nameInput.trim()
  val isNameValid = trimmedName.isNotEmpty() && trimmedName.length <= MAX_NAME_LENGTH
  val errorMessage = when {
    hasAttemptedSubmit && trimmedName.isEmpty() -> "Vui lòng nhập tên của bạn để tiếp tục."
    nameInput.length > MAX_NAME_LENGTH -> "Tên không được vượt quá $MAX_NAME_LENGTH ký tự."
    else -> null
  }

  fun handleSubmit() {
    hasAttemptedSubmit = true
    if (isNameValid) {
      keyboardController?.hide()
      onComplete(trimmedName, selectedAvatar)
    }
  }

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(WarmCream)
      .testTag("onboarding_screen")
  ) {
    // 1. Scenic Mountain & Wall Header Canvas
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(260.dp)
        .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
    ) {
      GreatWallHeroCanvas(modifier = Modifier.fillMaxSize())

      // Soft aesthetic gradient overlay blending into canvas
      Box(
        modifier = Modifier
          .fillMaxSize()
          .background(
            Brush.verticalGradient(
              colors = listOf(
                Color.Transparent,
                Color(0x99FDFBF7),
                WarmCream
              ),
              startY = 40f
            )
          )
      )
    }

    // 2. Scrollable Content
    Column(
      modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()
        .imePadding()
        .verticalScroll(scrollState)
        .padding(horizontal = 20.dp, vertical = 12.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Spacer(modifier = Modifier.height(12.dp))

      // Header Tag / Pill
      Box(
        modifier = Modifier
          .clip(RoundedCornerShape(20.dp))
          .background(Color.White.copy(alpha = 0.92f))
          .border(1.dp, PrimaryJade.copy(alpha = 0.3f), RoundedCornerShape(20.dp))
          .padding(horizontal = 14.dp, vertical = 6.dp)
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center
        ) {
          Text(
            text = "✨ KHÁM PHÁ TIẾNG TRUNG",
            fontSize = 12.sp,
            fontWeight = FontWeight.ExtraBold,
            color = PrimaryJade,
            letterSpacing = 1.sp
          )
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Main Greeting with Audio Pronunciation Button
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
      ) {
        Text(
          text = "你好！",
          fontSize = 38.sp,
          fontWeight = FontWeight.Black,
          color = DarkJade,
          letterSpacing = 2.sp
        )

        Spacer(modifier = Modifier.width(10.dp))

        Box(
          modifier = Modifier
            .size(42.dp)
            .shadow(4.dp, CircleShape)
            .clip(CircleShape)
            .background(Color.White)
            .border(1.5.dp, Color(0xFF80CBC4), CircleShape)
            .clickable { onPlayAudio("你好") }
            .testTag("onboarding_audio_btn"),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Filled.VolumeUp,
            contentDescription = "Nghe phát âm Nǐ hǎo",
            tint = PrimaryJade,
            modifier = Modifier.size(22.dp)
          )
        }
      }

      Text(
        text = "Nǐ hǎo! • Chào mừng bạn đến với thế giới tiếng Trung",
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color(0xFF2E7D32),
        textAlign = TextAlign.Center
      )

      Spacer(modifier = Modifier.height(24.dp))

      // ── STEP 1: CHỌN BẠN ĐỒNG HÀNH (AVATAR SELECTION) ─────────────────
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        border = CardDefaults.outlinedCardBorder().copy(
          brush = Brush.verticalGradient(
            colors = listOf(Color(0xFFE2DDD3), Color(0xFFECE7DF))
          )
        )
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
          ) {
            Text(
              text = "1. Chọn bạn đồng hành của bạn",
              fontSize = 16.sp,
              fontWeight = FontWeight.Bold,
              color = DarkText
            )
          }

          Text(
            text = "Người bạn này sẽ cùng bạn luyện phát âm và vượt ải mỗi ngày!",
            fontSize = 13.sp,
            color = SecondaryText,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
          )

          // 2 Avatar Cards: DUDU & BUBU
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
          ) {
            // CARD 1: DUDU (Brown Bear)
            AvatarOptionCard(
              id = "dudu",
              name = "Dudu",
              subtitle = "Gấu nâu thông thái",
              isSelected = selectedAvatar == "dudu",
              onSelect = {
                selectedAvatar = "dudu"
                onPlayAudio("你好")
              },
              modifier = Modifier.weight(1f),
              characterContent = {
                DuduVector(
                  size = 82.dp,
                  pose = if (selectedAvatar == "dudu") CharacterPose.CHEERING_HANDS_UP else CharacterPose.STANDING_HAPPY,
                  emotion = CharacterEmotion.HAPPY
                )
              }
            )

            // CARD 2: BUBU (Panda)
            AvatarOptionCard(
              id = "bubu",
              name = "Bubu",
              subtitle = "Gấu trúc đáng yêu",
              isSelected = selectedAvatar == "bubu",
              onSelect = {
                selectedAvatar = "bubu"
                onPlayAudio("你好")
              },
              modifier = Modifier.weight(1f),
              characterContent = {
                BubuVector(
                  size = 82.dp,
                  pose = if (selectedAvatar == "bubu") CharacterPose.WAVING else CharacterPose.STANDING_HAPPY,
                  emotion = CharacterEmotion.HAPPY
                )
              }
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(18.dp))

      // ── STEP 2: NHẬP TÊN NGƯỜI HỌC (NO SUGGESTIONS) ─────────────────
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        border = CardDefaults.outlinedCardBorder().copy(
          brush = Brush.verticalGradient(
            colors = listOf(Color(0xFFE2DDD3), Color(0xFFECE7DF))
          )
        )
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Text(
            text = "2. Bạn tên là gì?",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText
          )

          val mascotName = if (selectedAvatar == "bubu") "Bubu" else "Dudu"
          Text(
            text = "$mascotName sẽ gọi tên bạn trong các bài học và hội thoại:",
            fontSize = 13.sp,
            color = SecondaryText,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 4.dp, bottom = 14.dp)
          )

          // Name Input Field
          OutlinedTextField(
            value = nameInput,
            onValueChange = { newValue ->
              if (newValue.length <= MAX_NAME_LENGTH) {
                nameInput = newValue
              }
            },
            placeholder = {
              Text(
                text = "Nhập tên của bạn...",
                color = Color(0xFFA0AEC0),
                fontSize = 15.sp
              )
            },
            leadingIcon = {
              Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = null,
                tint = if (nameInput.isNotBlank()) PrimaryJade else Color(0xFFA0AEC0)
              )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
              capitalization = KeyboardCapitalization.Words,
              imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
              onDone = { handleSubmit() }
            ),
            isError = errorMessage != null,
            colors = OutlinedTextFieldDefaults.colors(
              focusedContainerColor = Color(0xFFFBFBFB),
              unfocusedContainerColor = Color(0xFFFBFBFB),
              focusedBorderColor = PrimaryJade,
              unfocusedBorderColor = Color(0xFFCBD5E0),
              errorBorderColor = Color(0xFFE53E3E),
              focusedTextColor = DarkText,
              unfocusedTextColor = DarkText
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
              .fillMaxWidth()
              .testTag("onboarding_name_input")
          )

          // Character counter & Error text
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(top = 6.dp, start = 4.dp, end = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            if (errorMessage != null) {
              Text(
                text = errorMessage,
                fontSize = 12.sp,
                color = Color(0xFFE53E3E),
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f)
              )
            } else {
              Spacer(modifier = Modifier.weight(1f))
            }

            Text(
              text = "${nameInput.length}/$MAX_NAME_LENGTH",
              fontSize = 12.sp,
              color = if (nameInput.length >= MAX_NAME_LENGTH) Color(0xFFE53E3E) else Color(0xFF718096),
              fontWeight = FontWeight.Medium
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(26.dp))

      // ── STEP 3: ACTION CTA BUTTON ────────────────────────────────────
      Button(
        onClick = { handleSubmit() },
        enabled = isNameValid,
        colors = ButtonDefaults.buttonColors(
          containerColor = PrimaryJade,
          disabledContainerColor = Color(0xFFCBD5E0),
          contentColor = Color.White,
          disabledContentColor = Color(0xFF718096)
        ),
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier
          .fillMaxWidth()
          .height(56.dp)
          .shadow(if (isNameValid) 6.dp else 0.dp, RoundedCornerShape(18.dp))
          .testTag("onboarding_start_button")
      ) {
        val mascotDisplay = if (selectedAvatar == "bubu") "Bubu 🐼" else "Dudu 🐻"
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center
        ) {
          Text(
            text = if (isNameValid) "Bắt đầu cùng $mascotDisplay" else "Bắt đầu hành trình",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold
          )
          Spacer(modifier = Modifier.width(10.dp))
          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = null,
            modifier = Modifier.size(20.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      Text(
        text = "Bạn có thể đổi tên và bạn đồng hành bất cứ lúc nào trong mục Hồ sơ.",
        fontSize = 12.sp,
        color = Color(0xFF718096),
        textAlign = TextAlign.Center
      )

      Spacer(modifier = Modifier.height(20.dp))
    }
  }
}

@Composable
private fun AvatarOptionCard(
  id: String,
  name: String,
  subtitle: String,
  isSelected: Boolean,
  onSelect: () -> Unit,
  modifier: Modifier = Modifier,
  characterContent: @Composable () -> Unit
) {
  val borderColor by animateColorAsState(
    targetValue = if (isSelected) PrimaryJade else Color(0xFFE2E8F0),
    animationSpec = spring(stiffness = Spring.StiffnessLow),
    label = "avatarBorder"
  )

  val cardBg by animateColorAsState(
    targetValue = if (isSelected) Color(0xFFF0FDF4) else Color(0xFFFAFAFA),
    animationSpec = spring(stiffness = Spring.StiffnessLow),
    label = "avatarBg"
  )

  val elevation by animateDpAsState(
    targetValue = if (isSelected) 4.dp else 0.dp,
    label = "avatarElevation"
  )

  val interactionSource = remember { MutableInteractionSource() }

  Box(
    modifier = modifier
      .shadow(elevation, RoundedCornerShape(20.dp))
      .clip(RoundedCornerShape(20.dp))
      .background(cardBg)
      .border(if (isSelected) 2.5.dp else 1.dp, borderColor, RoundedCornerShape(20.dp))
      .clickable(
        interactionSource = interactionSource,
        indication = null,
        onClick = onSelect
      )
      .padding(horizontal = 8.dp, vertical = 12.dp)
      .testTag("avatar_option_$id"),
    contentAlignment = Alignment.Center
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier.fillMaxWidth()
    ) {
      // Selected Checkmark Badge on Top Right
      Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopEnd
      ) {
        if (isSelected) {
          Box(
            modifier = Modifier
              .size(22.dp)
              .clip(CircleShape)
              .background(PrimaryJade),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Filled.Check,
              contentDescription = "Đã chọn",
              tint = Color.White,
              modifier = Modifier.size(14.dp)
            )
          }
        } else {
          Spacer(
            modifier = Modifier
              .size(22.dp)
              .clip(CircleShape)
              .background(Color(0xFFE2E8F0))
          )
        }
      }

      // Mascot Vector
      Box(
        modifier = Modifier
          .height(96.dp)
          .fillMaxWidth(),
        contentAlignment = Alignment.Center
      ) {
        characterContent()
      }

      Spacer(modifier = Modifier.height(6.dp))

      // Name & Title
      Text(
        text = name,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = if (isSelected) DarkJade else DarkText
      )

      Text(
        text = subtitle,
        fontSize = 11.sp,
        fontWeight = FontWeight.Medium,
        color = if (isSelected) PrimaryJade else SecondaryText,
        textAlign = TextAlign.Center,
        lineHeight = 14.sp
      )
    }
  }
}

package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.ActivityType
import com.example.model.LearningActivity
import com.example.model.LearningItem
import com.example.ui.theme.DarkJade
import com.example.ui.theme.DarkText
import com.example.ui.theme.JadeContainer
import com.example.ui.theme.PrimaryJade
import com.example.ui.theme.SecondaryText
import com.example.ui.theme.SoftBorder
import com.example.ui.theme.WarmGold
import kotlin.math.sin

// =========================================================================
// 1. REUSABLE ANIMATED UI ELEMENTS (SOUNDWAVES, RICE-GRID, BUTTONS, BADGES)
// =========================================================================

@Composable
fun AudioPlayButton(
  textToSpeak: String,
  onPlayAudio: (String) -> Unit,
  modifier: Modifier = Modifier,
  isLarge: Boolean = false
) {
  val infiniteTransition = rememberInfiniteTransition(label = "audio_ripple")
  val rippleScale by infiniteTransition.animateFloat(
    initialValue = 1f,
    targetValue = 1.15f,
    animationSpec = infiniteRepeatable(
      animation = tween(1200, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "scale"
  )

  val size = if (isLarge) 64.dp else 44.dp
  val iconSize = if (isLarge) 32.dp else 22.dp

  Box(
    modifier = modifier
      .size(size)
      .scale(if (isLarge) rippleScale else 1f)
      .shadow(if (isLarge) 6.dp else 2.dp, CircleShape)
      .clip(CircleShape)
      .background(
        Brush.linearGradient(
          colors = listOf(Color(0xFF00897B), Color(0xFF004D40))
        )
      )
      .clickable { onPlayAudio(textToSpeak) }
      .testTag("audio_play_button"),
    contentAlignment = Alignment.Center
  ) {
    Icon(
      imageVector = Icons.Filled.VolumeUp,
      contentDescription = "Listen to pronunciation",
      tint = Color.White,
      modifier = Modifier.size(iconSize)
    )
  }
}

/**
 * Animated Soundwave Equalizer displaying active audio waves with sinusoidal oscillation
 */
@Composable
fun AnimatedSoundwaveEqualizer(
  modifier: Modifier = Modifier,
  barCount: Int = 9,
  baseColor: Color = PrimaryJade
) {
  val infiniteTransition = rememberInfiniteTransition(label = "equalizer")
  val phase by infiniteTransition.animateFloat(
    initialValue = 0f,
    targetValue = (2 * Math.PI).toFloat(),
    animationSpec = infiniteRepeatable(
      animation = tween(1400, easing = LinearEasing),
      repeatMode = RepeatMode.Restart
    ),
    label = "phase"
  )

  Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(4.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    for (i in 0 until barCount) {
      val barOffset = i * 0.7f
      val barHeightFraction = (0.35f + 0.65f * ((sin(phase + barOffset) + 1f) / 2f)).coerceIn(0.2f, 1f)
      val heightDp = (10 + 26 * barHeightFraction).dp

      Box(
        modifier = Modifier
          .width(4.dp)
          .height(heightDp)
          .clip(RoundedCornerShape(2.dp))
          .background(
            Brush.verticalGradient(
              listOf(baseColor, DarkJade)
            )
          )
      )
    }
  }
}

/**
 * Traditional Chinese Calligraphy Rice-Grid Paper Background (米字格 - Mǐ Zì Gé)
 */
@Composable
fun CalligraphyMiZiGeCanvas(
  modifier: Modifier = Modifier,
  gridColor: Color = Color(0x33004D40)
) {
  Canvas(modifier = modifier.fillMaxSize()) {
    val w = size.width
    val h = size.height

    val stroke = Stroke(
      width = 1.5f,
      pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 6f), 0f)
    )

    // Outer border
    drawRect(
      color = gridColor.copy(alpha = 0.5f),
      topLeft = Offset(0f, 0f),
      size = Size(w, h),
      style = Stroke(width = 2f)
    )

    // Horizontal & Vertical Center Lines
    drawLine(gridColor, Offset(0f, h / 2f), Offset(w, h / 2f), strokeWidth = 1.5f, pathEffect = stroke.pathEffect)
    drawLine(gridColor, Offset(w / 2f, 0f), Offset(w / 2f, h), strokeWidth = 1.5f, pathEffect = stroke.pathEffect)

    // Diagonal Cross Lines
    drawLine(gridColor, Offset(0f, 0f), Offset(w, h), strokeWidth = 1.5f, pathEffect = stroke.pathEffect)
    drawLine(gridColor, Offset(w, 0f), Offset(0f, h), strokeWidth = 1.5f, pathEffect = stroke.pathEffect)
  }
}

// =========================================================================
// 2. DISCOVER VOCABULARY SCENE (LESSON 1)
// =========================================================================

@Composable
fun VocabIllustration(
  item: LearningItem,
  modifier: Modifier = Modifier
) {
  val infiniteTransition = rememberInfiniteTransition(label = "vocab_anim")
  val pulseScale by infiniteTransition.animateFloat(
    initialValue = 0.97f,
    targetValue = 1.03f,
    animationSpec = infiniteRepeatable(
      animation = tween(1800, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "pulse"
  )

  val bgGradient = when (item.illustrationType) {
    "hello" -> Brush.verticalGradient(listOf(Color(0xFFE8F5E9), Color(0xFFC8E6C9)))
    "hello_polite" -> Brush.verticalGradient(listOf(Color(0xFFFFF8E1), Color(0xFFFFECB3)))
    "goodbye" -> Brush.verticalGradient(listOf(Color(0xFFFFE0B2), Color(0xFFFFCCBC)))
    "thankyou" -> Brush.verticalGradient(listOf(Color(0xFFFCE4EC), Color(0xFFF8BBD0)))
    "welcome" -> Brush.verticalGradient(listOf(Color(0xFFE0F2F1), Color(0xFFB2DFDB)))
    "morning" -> Brush.verticalGradient(listOf(Color(0xFFFFF9C4), Color(0xFFFFE082)))
    "afternoon" -> Brush.verticalGradient(listOf(Color(0xFFE1F5FE), Color(0xFFB3E5FC)))
    "evening" -> Brush.verticalGradient(listOf(Color(0xFFEDE7F6), Color(0xFFD1C4E9)))
    else -> Brush.verticalGradient(listOf(JadeContainer, Color(0xFFDDEEE4)))
  }

  Box(
    modifier = modifier
      .fillMaxWidth()
      .height(190.dp)
      .clip(RoundedCornerShape(22.dp))
      .background(bgGradient)
      .border(1.5.dp, SoftBorder, RoundedCornerShape(22.dp)),
    contentAlignment = Alignment.Center
  ) {
    // Decorative Canvas Background
    Canvas(modifier = Modifier.fillMaxSize()) {
      val w = size.width
      val h = size.height

      // Background geometric Chinese motif circle
      drawCircle(
        color = Color.White.copy(alpha = 0.45f),
        radius = h * 0.48f,
        center = Offset(w * 0.5f, h * 0.5f)
      )

      // Decorative traditional corner patterns
      val cornerColor = Color(0x33004D40)
      drawLine(cornerColor, Offset(16.dp.toPx(), 16.dp.toPx()), Offset(36.dp.toPx(), 16.dp.toPx()), strokeWidth = 3f)
      drawLine(cornerColor, Offset(16.dp.toPx(), 16.dp.toPx()), Offset(16.dp.toPx(), 36.dp.toPx()), strokeWidth = 3f)
      drawLine(cornerColor, Offset(w - 16.dp.toPx(), 16.dp.toPx()), Offset(w - 36.dp.toPx(), 16.dp.toPx()), strokeWidth = 3f)
      drawLine(cornerColor, Offset(w - 16.dp.toPx(), 16.dp.toPx()), Offset(w - 16.dp.toPx(), 36.dp.toPx()), strokeWidth = 3f)
    }

    // Foreground Themed Illustration Scene
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
      modifier = Modifier
        .padding(12.dp)
        .scale(pulseScale)
    ) {
      when (item.illustrationType) {
        "hello" -> {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
          ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Box(
                modifier = Modifier
                  .clip(RoundedCornerShape(10.dp))
                  .background(Color(0xFFFFE8D6))
                  .padding(horizontal = 8.dp, vertical = 2.dp)
              ) {
                Text(text = "DUDU: 你好！", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFFE65100))
              }
              Spacer(modifier = Modifier.height(4.dp))
              DuduVector(modifier = Modifier.size(68.dp), pose = CharacterPose.WAVING)
            }
            Text(text = "👋", fontSize = 28.sp)
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Box(
                modifier = Modifier
                  .clip(RoundedCornerShape(10.dp))
                  .background(JadeContainer)
                  .padding(horizontal = 8.dp, vertical = 2.dp)
              ) {
                Text(text = "BUBU: 你好！", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DarkJade)
              }
              Spacer(modifier = Modifier.height(4.dp))
              BubuVector(modifier = Modifier.size(68.dp), pose = CharacterPose.WAVING)
            }
          }
        }

        "hello_polite" -> {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
          ) {
            DuduVector(modifier = Modifier.size(68.dp), pose = CharacterPose.STANDING_HAPPY)
            Box(
              modifier = Modifier
                .shadow(4.dp, RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .border(1.dp, WarmGold, RoundedCornerShape(16.dp))
                .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
              Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "🙇 您好！", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = DarkJade)
                Text(text = "DUDU & BUBU kính chào", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = PrimaryJade)
                Text(text = "❤ Bộ Tâm (心): Tôn kính từ tim", fontSize = 10.sp, color = SecondaryText)
              }
            }
            BubuVector(modifier = Modifier.size(68.dp), pose = CharacterPose.STANDING_HAPPY)
          }
        }

        "goodbye" -> {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
          ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Text(text = "Hẹn gặp lại!", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFFD84315))
              DuduVector(modifier = Modifier.size(68.dp), pose = CharacterPose.WAVING)
            }
            Text(text = "🌅", fontSize = 28.sp)
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Text(text = "再见！", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DarkJade)
              BubuVector(modifier = Modifier.size(68.dp), pose = CharacterPose.WAVING)
            }
          }
        }

        "thankyou" -> {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
          ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Text(text = "DUDU: 谢谢！", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFFC2185B))
              DuduVector(modifier = Modifier.size(68.dp), pose = CharacterPose.CHEERING_HANDS_UP)
            }
            Text(text = "🎁", fontSize = 28.sp)
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Text(text = "BUBU: 💖", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DarkJade)
              BubuVector(modifier = Modifier.size(68.dp), pose = CharacterPose.WAVING)
            }
          }
        }

        "welcome" -> {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
          ) {
            DuduVector(modifier = Modifier.size(68.dp), pose = CharacterPose.STANDING_HAPPY)
            Box(
              modifier = Modifier
                .shadow(4.dp, RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .border(1.dp, PrimaryJade, RoundedCornerShape(16.dp))
                .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
              Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "🍵 不客气！", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = DarkJade)
                Text(text = "BUBU: Không có chi nhé!", fontSize = 11.sp, color = DarkText)
                Text(text = "Đừng khách sáo", fontSize = 10.sp, color = SecondaryText)
              }
            }
            BubuVector(modifier = Modifier.size(68.dp), pose = CharacterPose.WAVING)
          }
        }

        "morning" -> {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
          ) {
            DuduVector(modifier = Modifier.size(64.dp), pose = CharacterPose.CHEERING_HANDS_UP)
            Text(text = "🌅\n早上好！", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFFE65100), textAlign = TextAlign.Center)
            BubuVector(modifier = Modifier.size(64.dp), pose = CharacterPose.WAVING)
          }
        }

        "afternoon" -> {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
          ) {
            DuduVector(modifier = Modifier.size(64.dp), pose = CharacterPose.STANDING_HAPPY)
            Text(text = "☀️\n下午好！", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0277BD), textAlign = TextAlign.Center)
            BubuVector(modifier = Modifier.size(64.dp), pose = CharacterPose.STANDING_HAPPY)
          }
        }

        "evening" -> {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
          ) {
            DuduVector(modifier = Modifier.size(64.dp), pose = CharacterPose.STANDING_HAPPY)
            Text(text = "🏮\n晚上好！", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF512DA8), textAlign = TextAlign.Center)
            BubuVector(modifier = Modifier.size(64.dp), pose = CharacterPose.WAVING)
          }
        }

        else -> {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
          ) {
            DuduVector(modifier = Modifier.size(64.dp), pose = CharacterPose.STANDING_HAPPY)
            Box(
              modifier = Modifier
                .shadow(4.dp, RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(horizontal = 14.dp, vertical = 10.dp)
            ) {
              Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = item.hanzi, fontSize = 26.sp, fontWeight = FontWeight.Bold, color = DarkJade)
                Text(text = item.pinyin, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = PrimaryJade)
              }
            }
            BubuVector(modifier = Modifier.size(64.dp), pose = CharacterPose.WAVING)
          }
        }
      }
    }
  }
}

@Composable
fun DiscoverVocabCards(
  items: List<LearningItem>,
  onPlayAudio: (String) -> Unit,
  onCompleteDiscover: () -> Unit = {},
  modifier: Modifier = Modifier
) {
  var currentIndex by remember { mutableIntStateOf(0) }
  val totalWords = items.size
  val currentItem = items.getOrElse(currentIndex) { items.first() }

  // Auto-play pronunciation whenever a new card is shown
  LaunchedEffect(currentIndex, currentItem.hanzi) {
    delay(250)
    onPlayAudio(currentItem.hanzi)
  }

  Column(
    modifier = modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    // 1. Step Progress Bar & Counter
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 4.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
          modifier = Modifier
            .clip(CircleShape)
            .background(DarkJade)
            .padding(horizontal = 10.dp, vertical = 4.dp)
        ) {
          Text(
            text = "Từ ${currentIndex + 1} / $totalWords",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
          )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = currentItem.hanzi,
          fontSize = 15.sp,
          fontWeight = FontWeight.Bold,
          color = DarkJade
        )
      }

      // Step Dots
      Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        items.indices.forEach { idx ->
          Box(
            modifier = Modifier
              .size(if (idx == currentIndex) 18.dp else 8.dp, 8.dp)
              .clip(RoundedCornerShape(4.dp))
              .background(
                if (idx == currentIndex) PrimaryJade
                else if (idx < currentIndex) DarkJade.copy(alpha = 0.5f)
                else Color(0xFFD4CDC0)
              )
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    // 2. Custom Illustration Card (Clickable to replay audio)
    Box(modifier = Modifier.clickable { onPlayAudio(currentItem.hanzi) }) {
      VocabIllustration(item = currentItem)
    }

    Spacer(modifier = Modifier.height(14.dp))

    // 3. Main Vocab Card Details (Clickable to replay audio)
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(22.dp))
        .background(Color.White)
        .border(1.dp, SoftBorder, RoundedCornerShape(22.dp))
        .clickable { onPlayAudio(currentItem.hanzi) }
        .padding(18.dp)
    ) {
      Column(modifier = Modifier.fillMaxWidth()) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Column {
            Text(
              text = currentItem.hanzi,
              fontSize = 38.sp,
              fontWeight = FontWeight.ExtraBold,
              color = DarkJade
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
              text = currentItem.pinyin,
              fontSize = 20.sp,
              fontWeight = FontWeight.Bold,
              color = PrimaryJade
            )
          }

          AudioPlayButton(
            textToSpeak = currentItem.hanzi,
            onPlayAudio = onPlayAudio,
            isLarge = true
          )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Vietnamese & English Meanings
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(JadeContainer)
            .padding(horizontal = 14.dp, vertical = 10.dp)
        ) {
          Column {
            if (currentItem.vietnameseMeaning.isNotEmpty()) {
              Text(
                text = currentItem.vietnameseMeaning,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = DarkJade
              )
              Spacer(modifier = Modifier.height(2.dp))
            }
            Text(
              text = currentItem.meaning,
              fontSize = 13.sp,
              color = SecondaryText
            )
          }
        }

        // Usage Context / Nuance Tip
        if (currentItem.usageNote.isNotEmpty()) {
          Spacer(modifier = Modifier.height(10.dp))
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(12.dp))
              .background(Color(0xFFFFF9C4).copy(alpha = 0.55f))
              .border(1.dp, Color(0xFFFFE082), RoundedCornerShape(12.dp))
              .padding(10.dp),
            verticalAlignment = Alignment.Top
          ) {
            Text(text = "💡", fontSize = 14.sp)
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = currentItem.usageNote,
              fontSize = 12.sp,
              color = DarkText,
              lineHeight = 17.sp
            )
          }
        }

        // Example Context Sentence
        if (currentItem.exampleSentence.isNotEmpty()) {
          Spacer(modifier = Modifier.height(10.dp))
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(12.dp))
              .background(Color(0xFFF5F7F6))
              .border(1.dp, SoftBorder, RoundedCornerShape(12.dp))
              .clickable { onPlayAudio(currentItem.exampleSentence) }
              .padding(12.dp)
          ) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Column(modifier = Modifier.weight(1f)) {
                Text(
                  text = "Ví dụ / Example:",
                  fontSize = 11.sp,
                  fontWeight = FontWeight.SemiBold,
                  color = SecondaryText
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                  text = currentItem.exampleSentence,
                  fontSize = 15.sp,
                  fontWeight = FontWeight.Bold,
                  color = DarkJade
                )
                Text(
                  text = currentItem.examplePinyin,
                  fontSize = 12.sp,
                  color = PrimaryJade
                )
                Text(
                  text = currentItem.exampleTranslation,
                  fontSize = 12.sp,
                  color = DarkText
                )
              }
              Spacer(modifier = Modifier.width(8.dp))
              Icon(
                imageVector = Icons.Filled.VolumeUp,
                contentDescription = "Play example audio",
                tint = PrimaryJade,
                modifier = Modifier.size(22.dp)
              )
            }
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    // In-Card Navigation Controls
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      if (currentIndex > 0) {
        Button(
          onClick = { currentIndex-- },
          modifier = Modifier
            .weight(1f)
            .height(48.dp)
            .testTag("vocab_prev_button"),
          shape = RoundedCornerShape(14.dp),
          colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFF7F5F0),
            contentColor = Color(0xFF004D40)
          ),
          border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFFD4CDC0))
        ) {
          Text(
            text = "← Quay lại",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF004D40)
          )
        }
      }

      val isLastWord = currentIndex == totalWords - 1
      Button(
        onClick = {
          if (!isLastWord) {
            currentIndex++
          } else {
            onCompleteDiscover()
          }
        },
        modifier = Modifier
          .weight(if (currentIndex > 0) 1.5f else 1f)
          .height(48.dp)
          .testTag("vocab_next_button"),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(
          containerColor = if (isLastWord) Color(0xFF00897B) else Color(0xFF00796B),
          contentColor = Color.White
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
      ) {
        Text(
          text = if (isLastWord) "Hoàn thành bài học ⭐" else "Từ tiếp theo (${currentIndex + 2}/$totalWords) →",
          fontSize = 14.sp,
          fontWeight = FontWeight.Bold,
          color = Color.White
        )
      }
    }
  }
}

// =========================================================================
// 3. LISTENING ACTIVITIES (LESSONS 2 & 3: LISTEN PINYIN / LISTEN HANZI)
// =========================================================================

@Composable
fun ListeningActivityView(
  activity: LearningActivity,
  selectedOption: String?,
  onSelectOption: (String) -> Unit,
  onPlayAudio: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  val infiniteTransition = rememberInfiniteTransition(label = "listen_scene")
  val headphoneFloat by infiniteTransition.animateFloat(
    initialValue = -3f,
    targetValue = 3f,
    animationSpec = infiniteRepeatable(
      animation = tween(1500, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "float"
  )

  Column(
    modifier = modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    // Prompt Header Badge
    Box(
      modifier = Modifier
        .clip(RoundedCornerShape(12.dp))
        .background(Color(0xFFE1F5FE))
        .border(1.dp, Color(0xFF81D4FA), RoundedCornerShape(12.dp))
        .padding(horizontal = 14.dp, vertical = 6.dp)
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = "🎧", fontSize = 14.sp)
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = "Luyện Nghe • Audio Listening",
          fontSize = 12.sp,
          fontWeight = FontWeight.Bold,
          color = Color(0xFF0277BD)
        )
      }
    }

    Spacer(modifier = Modifier.height(8.dp))

    Text(
      text = activity.prompt,
      fontSize = 15.sp,
      fontWeight = FontWeight.Bold,
      color = DarkJade,
      textAlign = TextAlign.Center,
      modifier = Modifier.padding(horizontal = 8.dp)
    )

    Spacer(modifier = Modifier.height(14.dp))

    // 1. Audio Listening Hero Stage with Panda & Equalizer
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(170.dp)
        .clip(RoundedCornerShape(24.dp))
        .background(
          Brush.verticalGradient(
            listOf(Color(0xFFE0F7FA), Color(0xFFB2EBF2))
          )
        )
        .border(1.5.dp, Color(0xFF80DEEA), RoundedCornerShape(24.dp))
        .padding(14.dp),
      contentAlignment = Alignment.Center
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
      ) {
        // Left: DUDU & BUBU Listening Mascots
        Box(
          modifier = Modifier.offset(y = headphoneFloat.dp),
          contentAlignment = Alignment.Center
        ) {
          DuduAndBubuAvatar(size = 64.dp)
          // Floating Music Notes
          Text(
            text = "🎵",
            fontSize = 18.sp,
            modifier = Modifier
              .align(Alignment.TopEnd)
              .offset(x = 10.dp, y = (-8).dp)
          )
        }

        // Center: Animated Soundwaves
        Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center
        ) {
          AnimatedSoundwaveEqualizer(barCount = 7, baseColor = Color(0xFF00838F))
          Spacer(modifier = Modifier.height(8.dp))
          Text(
            text = "Nhấn nút để nghe",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF006064)
          )
        }

        // Right: Glowing Play Audio Button
        AudioPlayButton(
          textToSpeak = activity.audioText.ifEmpty { "你好" },
          onPlayAudio = onPlayAudio,
          isLarge = true
        )
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // 2. Interactive Option Cards (with Alphabet Badges A, B, C, D)
    Column(
      modifier = Modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      activity.options.forEachIndexed { index, option ->
        val letterBadge = when (index) {
          0 -> "A"
          1 -> "B"
          2 -> "C"
          3 -> "D"
          else -> "${index + 1}"
        }
        OptionChoiceCard(
          badge = letterBadge,
          text = option,
          isSelected = selectedOption == option,
          onSelect = { onSelectOption(option) }
        )
      }
    }
  }
}

// =========================================================================
// 4. READING & CHARACTER MEANING ACTIVITIES (LESSON 4: READING / HANZI)
// =========================================================================

@Composable
fun ReadingActivityView(
  activity: LearningActivity,
  selectedOption: String?,
  onSelectOption: (String) -> Unit,
  onPlayAudio: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  val infiniteTransition = rememberInfiniteTransition(label = "reading_glow")
  val sealRotation by infiniteTransition.animateFloat(
    initialValue = -2f,
    targetValue = 2f,
    animationSpec = infiniteRepeatable(
      animation = tween(2000, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "seal_rot"
  )

  Column(
    modifier = modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    // Header Badge
    Box(
      modifier = Modifier
        .clip(RoundedCornerShape(12.dp))
        .background(JadeContainer)
        .border(1.dp, Color(0xFFB2DFDB), RoundedCornerShape(12.dp))
        .padding(horizontal = 14.dp, vertical = 6.dp)
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = "📖", fontSize = 14.sp)
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = "Đọc Hiểu & Nhận Diện Chữ Hán",
          fontSize = 12.sp,
          fontWeight = FontWeight.Bold,
          color = DarkJade
        )
      }
    }

    Spacer(modifier = Modifier.height(8.dp))

    Text(
      text = activity.prompt,
      fontSize = 15.sp,
      fontWeight = FontWeight.Bold,
      color = DarkJade,
      textAlign = TextAlign.Center,
      modifier = Modifier.padding(horizontal = 8.dp)
    )

    Spacer(modifier = Modifier.height(14.dp))

    // 1. Classical Chinese Scroll / Calligraphy Parchment Card (米字格)
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(175.dp)
        .clip(RoundedCornerShape(22.dp))
        .background(
          Brush.verticalGradient(
            listOf(Color(0xFFFFFDF7), Color(0xFFF7F3E8))
          )
        )
        .border(1.5.dp, WarmGold.copy(alpha = 0.6f), RoundedCornerShape(22.dp))
        .padding(14.dp),
      contentAlignment = Alignment.Center
    ) {
      // Calligraphy Grid in background
      Box(
        modifier = Modifier
          .size(135.dp)
          .align(Alignment.Center)
      ) {
        CalligraphyMiZiGeCanvas(gridColor = Color(0x228B5A2B))
      }

      // Traditional Red Vermilion Seal Stamp (印) in Top-Right
      Box(
        modifier = Modifier
          .align(Alignment.TopEnd)
          .size(34.dp)
          .offset(x = (-4).dp, y = 4.dp)
          .clip(RoundedCornerShape(6.dp))
          .background(Color(0xFFC62828))
          .border(1.dp, Color(0xFFFFCDD2), RoundedCornerShape(6.dp)),
        contentAlignment = Alignment.Center
      ) {
        Text(text = "汉", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
      }

      // Foreground Character & Pinyin Display
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
      ) {
        if (activity.hanziPrompt.isNotEmpty()) {
          Text(
            text = activity.hanziPrompt,
            fontSize = 44.sp,
            fontWeight = FontWeight.ExtraBold,
            color = DarkJade,
            textAlign = TextAlign.Center
          )
        }
        if (activity.pinyinPrompt.isNotEmpty()) {
          Spacer(modifier = Modifier.height(2.dp))
          Text(
            text = activity.pinyinPrompt,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryJade,
            textAlign = TextAlign.Center
          )
        }
      }

      // Quick Audio Replay Button in Bottom Right
      if (activity.hanziPrompt.isNotEmpty()) {
        Box(
          modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(4.dp)
        ) {
          AudioPlayButton(
            textToSpeak = activity.hanziPrompt,
            onPlayAudio = onPlayAudio,
            isLarge = false
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // 2. Options Grid
    Column(
      modifier = Modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      activity.options.forEachIndexed { index, option ->
        val letterBadge = when (index) {
          0 -> "A"
          1 -> "B"
          2 -> "C"
          3 -> "D"
          else -> "${index + 1}"
        }
        OptionChoiceCard(
          badge = letterBadge,
          text = option,
          isSelected = selectedOption == option,
          onSelect = { onSelectOption(option) }
        )
      }
    }
  }
}

// =========================================================================
// 5. FILL IN THE BLANK (LESSON 5: SOCIAL DIALOGUE & CONTEXT)
// =========================================================================

@Composable
fun FillBlankActivityView(
  activity: LearningActivity,
  selectedOption: String?,
  onSelectOption: (String) -> Unit,
  onPlayAudio: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    // Header Badge
    Box(
      modifier = Modifier
        .clip(RoundedCornerShape(12.dp))
        .background(Color(0xFFFFF8E1))
        .border(1.dp, Color(0xFFFFE082), RoundedCornerShape(12.dp))
        .padding(horizontal = 14.dp, vertical = 6.dp)
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = "💬", fontSize = 14.sp)
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = "Giao Tiếp Xã Hội • Fill Dialogue",
          fontSize = 12.sp,
          fontWeight = FontWeight.Bold,
          color = Color(0xFFF57F17)
        )
      }
    }

    Spacer(modifier = Modifier.height(8.dp))

    Text(
      text = activity.prompt,
      fontSize = 15.sp,
      fontWeight = FontWeight.Bold,
      color = DarkJade,
      textAlign = TextAlign.Center,
      modifier = Modifier.padding(horizontal = 8.dp)
    )

    Spacer(modifier = Modifier.height(14.dp))

    // 1. Social Comic Dialogue Cards
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(22.dp))
        .background(Color.White)
        .border(1.dp, SoftBorder, RoundedCornerShape(22.dp))
        .padding(16.dp)
    ) {
      Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        // Speaker A Bubble (e.g. Panda friend says "谢谢！")
        Row(
          modifier = Modifier.fillMaxWidth(),
          verticalAlignment = Alignment.CenterVertically
        ) {
          PandaAvatar(size = 46.dp)
          Spacer(modifier = Modifier.width(10.dp))
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 16.dp, bottomEnd = 16.dp, bottomStart = 16.dp))
              .background(JadeContainer)
              .border(1.dp, Color(0xFFC8E6C9), RoundedCornerShape(topStart = 4.dp, topEnd = 16.dp, bottomEnd = 16.dp, bottomStart = 16.dp))
              .padding(horizontal = 14.dp, vertical = 10.dp)
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(
                text = "A: 谢谢！(Cảm ơn bạn!)",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = DarkJade
              )
              Spacer(modifier = Modifier.width(6.dp))
              Icon(
                imageVector = Icons.Filled.VolumeUp,
                contentDescription = null,
                tint = PrimaryJade,
                modifier = Modifier
                  .size(20.dp)
                  .clickable { onPlayAudio("谢谢！") }
              )
            }
          }
        }

        // Speaker B Bubble (Your response slot with live selection preview!)
        Row(
          modifier = Modifier.fillMaxWidth(),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.End
        ) {
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 4.dp, bottomEnd = 16.dp, bottomStart = 16.dp))
              .background(if (selectedOption != null) Color(0xFFE8F5E9) else Color(0xFFFFF9C4))
              .border(
                1.5.dp,
                if (selectedOption != null) PrimaryJade else WarmGold,
                RoundedCornerShape(topStart = 16.dp, topEnd = 4.dp, bottomEnd = 16.dp, bottomStart = 16.dp)
              )
              .padding(horizontal = 16.dp, vertical = 10.dp)
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(
                text = "B: ",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = DarkJade
              )
              Text(
                text = selectedOption ?: "[  ?  ]",
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold,
                color = if (selectedOption != null) PrimaryJade else Color(0xFFE65100)
              )
              Text(
                text = " ！",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = DarkJade
              )
            }
          }
          Spacer(modifier = Modifier.width(10.dp))
          Box(
            modifier = Modifier
              .size(46.dp)
              .clip(CircleShape)
              .background(PrimaryJade),
            contentAlignment = Alignment.Center
          ) {
            Text(text = "Bạn", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.White)
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    // 2. Options Grid
    Column(
      modifier = Modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      activity.options.forEachIndexed { index, option ->
        val letterBadge = when (index) {
          0 -> "A"
          1 -> "B"
          2 -> "C"
          3 -> "D"
          else -> "${index + 1}"
        }
        OptionChoiceCard(
          badge = letterBadge,
          text = option,
          isSelected = selectedOption == option,
          onSelect = { onSelectOption(option) }
        )
      }
    }
  }
}

// =========================================================================
// 6. PANDA CONVERSATION DIALOGUE THEATER (LESSON 6: CONVERSATION)
// =========================================================================

@Composable
fun PandaConversationActivity(
  activity: LearningActivity,
  selectedOption: String?,
  onSelectOption: (String) -> Unit,
  onPlayAudio: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  val infiniteTransition = rememberInfiniteTransition(label = "panda_convo")
  val pandaBob by infiniteTransition.animateFloat(
    initialValue = -3f,
    targetValue = 3f,
    animationSpec = infiniteRepeatable(
      animation = tween(1600, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "bob"
  )

  Column(
    modifier = modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    // Header Badge
    Box(
      modifier = Modifier
        .clip(RoundedCornerShape(12.dp))
        .background(Color(0xFFEDE7F6))
        .border(1.dp, Color(0xFFD1C4E9), RoundedCornerShape(12.dp))
        .padding(horizontal = 14.dp, vertical = 6.dp)
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = "🐻🐼", fontSize = 14.sp)
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = "Hội Thoại Cùng DUDU & BUBU • Dialogue",
          fontSize = 12.sp,
          fontWeight = FontWeight.Bold,
          color = Color(0xFF512DA8)
        )
      }
    }

    Spacer(modifier = Modifier.height(8.dp))

    Text(
      text = activity.prompt,
      fontSize = 15.sp,
      fontWeight = FontWeight.Bold,
      color = DarkJade,
      textAlign = TextAlign.Center,
      modifier = Modifier.padding(horizontal = 8.dp)
    )

    Spacer(modifier = Modifier.height(14.dp))

    // 1. Scenic DUDU & BUBU Theatre Scene
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(22.dp))
        .background(
          Brush.verticalGradient(
            listOf(Color(0xFFFFF8E1), Color(0xFFE8F5E9))
          )
        )
        .border(1.5.dp, PrimaryJade.copy(alpha = 0.5f), RoundedCornerShape(22.dp))
        .padding(16.dp)
    ) {
      Column {
        Row(
          modifier = Modifier.fillMaxWidth(),
          verticalAlignment = Alignment.CenterVertically
        ) {
          // Animated DUDU & BUBU duo mascot
          Box(
            modifier = Modifier.offset(y = pandaBob.dp),
            contentAlignment = Alignment.Center
          ) {
            DuduAndBubuAvatar(size = 66.dp)
          }

          Spacer(modifier = Modifier.width(12.dp))

          // Parchment Speech Bubble with dialogue
          Box(
            modifier = Modifier
              .weight(1f)
              .shadow(3.dp, RoundedCornerShape(18.dp))
              .clip(RoundedCornerShape(18.dp))
              .background(Color.White)
              .border(1.dp, WarmGold, RoundedCornerShape(18.dp))
              .padding(14.dp)
          ) {
            Column {
              Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
              ) {
                Text(
                  text = "DUDU & BUBU nói:",
                  fontSize = 12.sp,
                  fontWeight = FontWeight.Bold,
                  color = PrimaryJade
                )
                if (activity.audioText.isNotEmpty() || activity.pandaDialogue.isNotEmpty()) {
                  IconButton(
                    onClick = { onPlayAudio(activity.audioText.ifEmpty { activity.pandaDialogue }) },
                    modifier = Modifier.size(28.dp)
                  ) {
                    Icon(
                      imageVector = Icons.Filled.VolumeUp,
                      contentDescription = "Listen to DUDU & BUBU",
                      tint = PrimaryJade,
                      modifier = Modifier.size(20.dp)
                    )
                  }
                }
              }

              Spacer(modifier = Modifier.height(2.dp))

              Text(
                text = activity.pandaDialogue,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = DarkJade
              )
            }
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Text(
      text = "Chọn câu trả lời của bạn:",
      fontSize = 13.sp,
      fontWeight = FontWeight.Bold,
      color = SecondaryText,
      modifier = Modifier.align(Alignment.Start)
    )

    Spacer(modifier = Modifier.height(10.dp))

    // 2. Response Options
    Column(
      modifier = Modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      activity.options.forEachIndexed { index, option ->
        val letterBadge = when (index) {
          0 -> "A"
          1 -> "B"
          2 -> "C"
          3 -> "D"
          else -> "${index + 1}"
        }
        OptionChoiceCard(
          badge = letterBadge,
          text = option,
          isSelected = selectedOption == option,
          onSelect = { onSelectOption(option) }
        )
      }
    }
  }
}

// =========================================================================
// 7. BAMBOO SCROLL SENTENCE BUILDER (LESSON 7: SENTENCE STRUCTURE)
// =========================================================================

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SentenceBuilderActivity(
  activity: LearningActivity,
  selectedWords: List<String>,
  onAddWord: (String) -> Unit,
  onRemoveWord: (Int) -> Unit,
  onPlayAudio: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  val availableWords = remember(activity.sentenceWords, selectedWords) {
    val remaining = activity.sentenceWords.toMutableList()
    selectedWords.forEach { remaining.remove(it) }
    remaining
  }

  Column(
    modifier = modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    // Header Badge
    Box(
      modifier = Modifier
        .clip(RoundedCornerShape(12.dp))
        .background(Color(0xFFE8F5E9))
        .border(1.dp, Color(0xFFA5D6A7), RoundedCornerShape(12.dp))
        .padding(horizontal = 14.dp, vertical = 6.dp)
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = "🧩", fontSize = 14.sp)
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = "Ghép Câu Hoàn Chỉnh • Sentence Forge",
          fontSize = 12.sp,
          fontWeight = FontWeight.Bold,
          color = Color(0xFF2E7D32)
        )
      }
    }

    Spacer(modifier = Modifier.height(8.dp))

    Text(
      text = activity.prompt,
      fontSize = 15.sp,
      fontWeight = FontWeight.Bold,
      color = DarkJade,
      textAlign = TextAlign.Center
    )

    Spacer(modifier = Modifier.height(14.dp))

    // 1. Bamboo Scroll Sentence Forge (Assembled Words Slots)
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(125.dp)
        .shadow(2.dp, RoundedCornerShape(20.dp))
        .clip(RoundedCornerShape(20.dp))
        .background(
          Brush.verticalGradient(
            listOf(Color.White, Color(0xFFFDFBF7))
          )
        )
        .border(1.5.dp, WarmGold, RoundedCornerShape(20.dp))
        .padding(14.dp)
    ) {
      if (selectedWords.isEmpty()) {
        Column(
          modifier = Modifier.align(Alignment.Center),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Text(
            text = "🎋 Chạm vào các thẻ từ vựng bên dưới để xếp câu",
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = SecondaryText
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = "(Tap tiles in word bank to place here)",
            fontSize = 11.sp,
            color = Color(0xFF9E9E9E)
          )
        }
      } else {
        FlowRow(
          horizontalArrangement = Arrangement.spacedBy(8.dp),
          verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          selectedWords.forEachIndexed { index, word ->
            // Animated Word Tile with Close Button
            Box(
              modifier = Modifier
                .shadow(3.dp, RoundedCornerShape(14.dp))
                .clip(RoundedCornerShape(14.dp))
                .background(
                  Brush.horizontalGradient(
                    listOf(Color(0xFF00897B), Color(0xFF004D40))
                  )
                )
                .clickable { onRemoveWord(index) }
                .padding(horizontal = 14.dp, vertical = 10.dp)
            ) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                  text = word,
                  fontSize = 17.sp,
                  fontWeight = FontWeight.Bold,
                  color = Color.White
                )
                Spacer(modifier = Modifier.width(6.dp))
                Icon(
                  imageVector = Icons.Filled.Close,
                  contentDescription = "Remove word",
                  tint = Color.White,
                  modifier = Modifier.size(16.dp)
                )
              }
            }
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(20.dp))

    // 2. Word Bank Header with Panda Tip
    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
          text = "Kho từ vựng (Word Bank):",
          fontSize = 13.sp,
          fontWeight = FontWeight.Bold,
          color = DarkJade
        )
      }

      Text(
        text = "Còn lại: ${availableWords.size} từ",
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        color = PrimaryJade
      )
    }

    Spacer(modifier = Modifier.height(10.dp))

    // 3. Tactile 3D Bamboo Word Bank Tiles
    FlowRow(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(10.dp),
      verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      availableWords.forEach { word ->
        Box(
          modifier = Modifier
            .shadow(2.dp, RoundedCornerShape(14.dp))
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White)
            .border(1.5.dp, Color(0xFF00897B), RoundedCornerShape(14.dp))
            .clickable { onAddWord(word) }
            .padding(horizontal = 18.dp, vertical = 12.dp)
        ) {
          Text(
            text = word,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF004D40)
          )
        }
      }
    }
  }
}

// =========================================================================
// 8. SPEAKING STUDIO (LESSON 8: VOICE & TONE RECORDING)
// =========================================================================

@Composable
fun SpeakingActivityView(
  activity: LearningActivity,
  isRecording: Boolean,
  audioRms: Float = 0f,
  evaluationResult: com.example.util.SpeechEvaluationResult? = null,
  onStartRecording: () -> Unit,
  onStopRecording: () -> Unit = {},
  onPlayTargetAudio: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  val infiniteTransition = rememberInfiniteTransition(label = "speaking_pulse")
  val dynamicPulse by animateFloatAsState(
    targetValue = if (isRecording) (1.0f + (audioRms.coerceIn(0f, 15f) / 20f)) else 1.0f,
    animationSpec = tween(120),
    label = "rms_scale"
  )

  val pulseScale1 by infiniteTransition.animateFloat(
    initialValue = 1.0f,
    targetValue = if (isRecording) 1.35f else 1.0f,
    animationSpec = infiniteRepeatable(
      animation = tween(600, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "pulse_scale1"
  )

  val pulseScale2 by infiniteTransition.animateFloat(
    initialValue = 1.0f,
    targetValue = if (isRecording) 1.6f else 1.0f,
    animationSpec = infiniteRepeatable(
      animation = tween(800, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "pulse_scale2"
  )

  Column(
    modifier = modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    // Header Badge
    Box(
      modifier = Modifier
        .clip(RoundedCornerShape(12.dp))
        .background(Color(0xFFFFEBEE))
        .border(1.dp, Color(0xFFFFCDD2), RoundedCornerShape(12.dp))
        .padding(horizontal = 14.dp, vertical = 6.dp)
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = "🎙️", fontSize = 14.sp)
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = "Phòng Thu Âm Phát Âm • Voice Studio",
          fontSize = 12.sp,
          fontWeight = FontWeight.Bold,
          color = Color(0xFFC62828)
        )
      }
    }

    Spacer(modifier = Modifier.height(8.dp))

    Text(
      text = activity.prompt,
      fontSize = 15.sp,
      fontWeight = FontWeight.Bold,
      color = DarkJade,
      textAlign = TextAlign.Center
    )

    Spacer(modifier = Modifier.height(14.dp))

    // 1. Target Pronunciation Spotlight Stage
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(22.dp))
        .background(
          Brush.verticalGradient(
            listOf(JadeContainer, Color(0xFFD4EDE5))
          )
        )
        .border(1.5.dp, PrimaryJade.copy(alpha = 0.4f), RoundedCornerShape(22.dp))
        .padding(18.dp),
      contentAlignment = Alignment.Center
    ) {
      Column(horizontalAlignment = Alignment.CenterHorizontally) {
        if (activity.hanziPrompt.isNotEmpty()) {
          Text(
            text = activity.hanziPrompt,
            fontSize = 38.sp,
            fontWeight = FontWeight.ExtraBold,
            color = DarkJade
          )
        }
        if (activity.pinyinPrompt.isNotEmpty()) {
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = activity.pinyinPrompt,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryJade
          )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center
        ) {
          AudioPlayButton(
            textToSpeak = activity.audioText.ifEmpty { activity.hanziPrompt },
            onPlayAudio = onPlayTargetAudio,
            isLarge = false
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "Nghe phát âm chuẩn",
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = DarkJade
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(20.dp))

    // 2. Animated Pulse Microphone Button with Sound Wave Rings
    Box(
      modifier = Modifier.size(130.dp),
      contentAlignment = Alignment.Center
    ) {
      if (isRecording) {
        // Outer wave ring 2
        Box(
          modifier = Modifier
            .size(110.dp)
            .scale(pulseScale2 * dynamicPulse)
            .clip(CircleShape)
            .background(Color(0xFFE53935).copy(alpha = 0.2f))
        )
        // Outer wave ring 1
        Box(
          modifier = Modifier
            .size(95.dp)
            .scale(pulseScale1 * dynamicPulse)
            .clip(CircleShape)
            .background(Color(0xFFE53935).copy(alpha = 0.35f))
        )
      }

      // Core Microphone Button
      Box(
        modifier = Modifier
          .size(86.dp)
          .shadow(6.dp, CircleShape)
          .clip(CircleShape)
          .background(
            if (isRecording) {
              Brush.linearGradient(listOf(Color(0xFFE53935), Color(0xFFB71C1C)))
            } else {
              Brush.linearGradient(listOf(PrimaryJade, DarkJade))
            }
          )
          .clickable {
            if (isRecording) {
              onStopRecording()
            } else {
              onStartRecording()
            }
          }
          .testTag("record_mic_button"),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = if (isRecording) Icons.Filled.Close else Icons.Filled.Mic,
          contentDescription = "Thu âm giọng đọc",
          tint = Color.White,
          modifier = Modifier.size(42.dp)
        )
      }
    }

    Spacer(modifier = Modifier.height(10.dp))

    Text(
      text = if (isRecording) "🎙️ Đang lắng nghe... Nói to và rõ ràng vào micro!" else "Nhấn vào Micro để bắt đầu đọc",
      fontSize = 14.sp,
      fontWeight = FontWeight.Bold,
      color = if (isRecording) Color(0xFFD32F2F) else Color(0xFF4A5568)
    )

    // 3. Pronunciation Real-Time Evaluation Result Card
    if (evaluationResult != null) {
      Spacer(modifier = Modifier.height(16.dp))
      val isPass = evaluationResult.isPass
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(18.dp))
          .background(if (isPass) Color(0xFFE8F5E9) else Color(0xFFFFF3E0))
          .border(
            width = 1.5.dp,
            color = if (isPass) Color(0xFFA5D6A7) else Color(0xFFFFCC80),
            shape = RoundedCornerShape(18.dp)
          )
          .padding(16.dp)
      ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(
                text = if (isPass) "✅" else "⚠️",
                fontSize = 18.sp
              )
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = if (isPass) "Đạt chuẩn phát âm" else "Cần luyện tập thêm",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = if (isPass) Color(0xFF2E7D32) else Color(0xFFE65100)
              )
            }
            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(if (isPass) Color(0xFFC8E6C9) else Color(0xFFFFE0B2))
                .padding(horizontal = 8.dp, vertical = 3.dp)
            ) {
              Text(
                text = "${evaluationResult.matchedPercentage}% Điểm",
                fontSize = 13.sp,
                fontWeight = FontWeight.ExtraBold,
                color = if (isPass) Color(0xFF1B5E20) else Color(0xFFBF360C)
              )
            }
          }

          if (evaluationResult.recognizedText.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Box(
              modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(10.dp))
                .padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
              Text(
                text = "Bạn vừa đọc: \"${evaluationResult.recognizedText}\"",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF212121)
              )
            }
          }

          Spacer(modifier = Modifier.height(8.dp))
          Text(
            text = evaluationResult.feedbackText,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = if (isPass) Color(0xFF2E7D32) else Color(0xFFC05621),
            textAlign = TextAlign.Center
          )
        }
      }
    }
  }
}

// =========================================================================
// 9. BOSS CHALLENGE / MIXED REVIEW ARENA (LESSON 9)
// =========================================================================

@Composable
fun BossRoundActivityView(
  activity: LearningActivity,
  selectedOption: String?,
  onSelectOption: (String) -> Unit,
  onPlayAudio: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  val infiniteTransition = rememberInfiniteTransition(label = "boss_glow")
  val glowAlpha by infiniteTransition.animateFloat(
    initialValue = 0.6f,
    targetValue = 1f,
    animationSpec = infiniteRepeatable(
      animation = tween(1000, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "glow"
  )

  Column(
    modifier = modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    // 1. Boss Arena Golden Banner
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(18.dp))
        .background(
          Brush.horizontalGradient(
            listOf(Color(0xFFFFF8E1), Color(0xFFFFECB3), Color(0xFFFFF8E1))
          )
        )
        .border(2.dp, WarmGold.copy(alpha = glowAlpha), RoundedCornerShape(18.dp))
        .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(text = "🔥", fontSize = 24.sp)
          Spacer(modifier = Modifier.width(8.dp))
          Column {
            Text(
              text = "TRẬN ĐẤU TRÙM • BOSS STAGE",
              fontSize = 13.sp,
              fontWeight = FontWeight.ExtraBold,
              color = Color(0xFFE65100)
            )
            Text(
              text = "Ôn tập tổng hợp toàn diện",
              fontSize = 11.sp,
              color = DarkText
            )
          }
        }

        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFFF6F00))
            .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
          Text(
            text = "2x XP ⭐",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    Text(
      text = activity.prompt,
      fontSize = 16.sp,
      fontWeight = FontWeight.Bold,
      color = DarkJade,
      textAlign = TextAlign.Center
    )

    Spacer(modifier = Modifier.height(12.dp))

    // Optional Character Spotlight
    if (activity.hanziPrompt.isNotEmpty() || activity.audioText.isNotEmpty()) {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(20.dp))
          .background(JadeContainer)
          .border(1.5.dp, PrimaryJade, RoundedCornerShape(20.dp))
          .padding(16.dp),
        contentAlignment = Alignment.Center
      ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          if (activity.hanziPrompt.isNotEmpty()) {
            Text(
              text = activity.hanziPrompt,
              fontSize = 36.sp,
              fontWeight = FontWeight.ExtraBold,
              color = DarkJade
            )
          }
          if (activity.pinyinPrompt.isNotEmpty()) {
            Text(
              text = activity.pinyinPrompt,
              fontSize = 18.sp,
              fontWeight = FontWeight.Bold,
              color = PrimaryJade
            )
          }
          if (activity.audioText.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            AudioPlayButton(
              textToSpeak = activity.audioText,
              onPlayAudio = onPlayAudio,
              isLarge = false
            )
          }
        }
      }
      Spacer(modifier = Modifier.height(16.dp))
    }

    // 2. Options Grid
    Column(
      modifier = Modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      activity.options.forEachIndexed { index, option ->
        val letterBadge = when (index) {
          0 -> "A"
          1 -> "B"
          2 -> "C"
          3 -> "D"
          else -> "${index + 1}"
        }
        OptionChoiceCard(
          badge = letterBadge,
          text = option,
          isSelected = selectedOption == option,
          onSelect = { onSelectOption(option) }
        )
      }
    }
  }
}

// =========================================================================
// 10. REUSABLE OPTION CHOICE CARD COMPONENT
// =========================================================================

@Composable
fun OptionChoiceCard(
  badge: String,
  text: String,
  isSelected: Boolean,
  onSelect: () -> Unit,
  modifier: Modifier = Modifier
) {
  val backgroundColor by animateColorAsState(
    if (isSelected) Color(0xFFE0F2F1) else Color.White,
    label = "opt_bg"
  )
  val borderColor by animateColorAsState(
    if (isSelected) Color(0xFF00897B) else Color(0xFFD9D4C7),
    label = "opt_border"
  )
  val scale by animateFloatAsState(
    if (isSelected) 1.015f else 1f,
    animationSpec = spring(stiffness = 500f),
    label = "opt_scale"
  )

  Box(
    modifier = modifier
      .fillMaxWidth()
      .scale(scale)
      .shadow(if (isSelected) 3.dp else 1.dp, RoundedCornerShape(16.dp))
      .clip(RoundedCornerShape(16.dp))
      .background(backgroundColor)
      .border(if (isSelected) 2.dp else 1.5.dp, borderColor, RoundedCornerShape(16.dp))
      .clickable { onSelect() }
      .padding(horizontal = 16.dp, vertical = 14.dp)
      .testTag("option_${text.take(10)}")
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      // Left: Alphabet Badge (A, B, C, D)
      Box(
        modifier = Modifier
          .size(30.dp)
          .clip(CircleShape)
          .background(if (isSelected) Color(0xFF00897B) else Color(0xFFEFECE5))
          .border(
            width = if (isSelected) 0.dp else 1.dp,
            color = if (isSelected) Color.Transparent else Color(0xFFD4CDC0),
            shape = CircleShape
          ),
        contentAlignment = Alignment.Center
      ) {
        Text(
          text = badge,
          fontSize = 14.sp,
          fontWeight = FontWeight.Bold,
          color = if (isSelected) Color.White else Color(0xFF2D3748)
        )
      }

      Spacer(modifier = Modifier.width(12.dp))

      // Center: Option Text
      Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold,
        color = if (isSelected) Color(0xFF004D40) else Color(0xFF1E293B),
        modifier = Modifier.weight(1f)
      )

      // Right: Checkmark indicator when selected
      if (isSelected) {
        Box(
          modifier = Modifier
            .size(26.dp)
            .clip(CircleShape)
            .background(Color(0xFF00897B)),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = "Selected",
            tint = Color.White,
            modifier = Modifier.size(16.dp)
          )
        }
      }
    }
  }
}

// =========================================================================
// 11. MANDARIN TONE PITCH VISUALIZER (4 TONES)
// =========================================================================

@Composable
fun ToneVisualizerCard(
  onPlayAudio: ((String) -> Unit)? = null,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(18.dp))
      .background(Color.White)
      .border(1.dp, SoftBorder, RoundedCornerShape(18.dp))
      .padding(16.dp)
  ) {
    Column {
      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Text(
          text = "🎵 4 Thanh Điệu Tiếng Trung (Chạm để nghe)",
          fontSize = 14.sp,
          fontWeight = FontWeight.Bold,
          color = DarkJade
        )
      }

      Spacer(modifier = Modifier.height(12.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        TonePitchTile("Thanh 1: Ngang", "mā", "ˉ", Color(0xFF1E88E5), onPlayAudio)
        TonePitchTile("Thanh 2: Sắc", "má", "ˊ", Color(0xFF43A047), onPlayAudio)
        TonePitchTile("Thanh 3: Hỏi/Huyền", "mǎ", "ˇ", Color(0xFFFB8C00), onPlayAudio)
        TonePitchTile("Thanh 4: Nặng", "mà", "ˋ", Color(0xFFE53935), onPlayAudio)
      }
    }
  }
}

@Composable
private fun TonePitchTile(
  label: String,
  example: String,
  symbol: String,
  accentColor: Color,
  onPlayAudio: ((String) -> Unit)? = null
) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
      .clip(RoundedCornerShape(12.dp))
      .background(Color(0xFFF2FAF7))
      .border(1.5.dp, accentColor.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
      .clickable { onPlayAudio?.invoke(example) }
      .padding(horizontal = 8.dp, vertical = 8.dp)
  ) {
    Text(text = symbol, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, color = accentColor)
    Text(text = example, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF004D40))
    Text(text = label, fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4A5568))
  }
}

// =========================================================================
// 12. GENERIC MULTIPLE CHOICE FALLBACK
// =========================================================================

@Composable
fun MultipleChoiceActivity(
  activity: LearningActivity,
  selectedOption: String?,
  onSelectOption: (String) -> Unit,
  onPlayAudio: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  when (activity.type) {
    ActivityType.LISTEN_PINYIN, ActivityType.LISTEN_HANZI, ActivityType.TONE_LISTEN -> {
      ListeningActivityView(
        activity = activity,
        selectedOption = selectedOption,
        onSelectOption = onSelectOption,
        onPlayAudio = onPlayAudio,
        modifier = modifier
      )
    }
    ActivityType.READING, ActivityType.NUMBER_CHALLENGE, ActivityType.WRITING -> {
      ReadingActivityView(
        activity = activity,
        selectedOption = selectedOption,
        onSelectOption = onSelectOption,
        onPlayAudio = onPlayAudio,
        modifier = modifier
      )
    }
    ActivityType.FILL_BLANK -> {
      FillBlankActivityView(
        activity = activity,
        selectedOption = selectedOption,
        onSelectOption = onSelectOption,
        onPlayAudio = onPlayAudio,
        modifier = modifier
      )
    }
    ActivityType.BOSS_ROUND -> {
      BossRoundActivityView(
        activity = activity,
        selectedOption = selectedOption,
        onSelectOption = onSelectOption,
        onPlayAudio = onPlayAudio,
        modifier = modifier
      )
    }
    else -> {
      ReadingActivityView(
        activity = activity,
        selectedOption = selectedOption,
        onSelectOption = onSelectOption,
        onPlayAudio = onPlayAudio,
        modifier = modifier
      )
    }
  }
}

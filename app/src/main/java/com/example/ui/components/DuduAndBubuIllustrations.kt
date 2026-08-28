package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.DarkJade
import com.example.ui.theme.JadeContainer
import com.example.ui.theme.PrimaryJade
import com.example.ui.theme.WarmGold

enum class CharacterPose {
  STANDING_HAPPY,
  WAVING,
  POINTING_RIGHT,
  POINTING_LEFT,
  THINKING,
  CHEERING_HANDS_UP,
  HOLDING_CARD,
  LISTENING_HEADPHONES,
  SURPRISED
}

enum class CharacterEmotion {
  HAPPY,
  CHEERING,
  EXCITED,
  THINKING,
  TALKING,
  ENCOURAGING
}

// Master Character Bible Color Palette
private val OutlineChocolate = Color(0xFF4A2924)
private val EyeSolidColor = Color(0xFF3B1E19)
private val MouthTongueCoral = Color(0xFFFF7B7B)

// Character A — PANDA (BUBU) Colors
private val PandaWhite = Color(0xFFFFFDFC)
private val PandaDarkChocolate = Color(0xFF4A2924)
private val PandaCheekPink = Color(0xFFFFAEC9)

// Character B — BROWN BEAR (DUDU) Colors
private val BearCaramelBrown = Color(0xFFC99068)
private val BearInnerEarChocolate = Color(0xFF5A342A)
private val BearCreamBelly = Color(0xFFF5E6CB)
private val BearCheekPeach = Color(0xFFFFB085)

/**
 * CHARACTER A — PANDA (BUBU)
 * Built with exact geometric proportions from the Master Character Bible:
 * - Height:Width ≈ 1.4:1
 * - Head ≈ 60% of total height (dramatically wider than body, rounded-bean silhouette)
 * - Body ≈ 40% of total height (compact, short stubby limbs)
 * - Eyes: X ≈ 39% & 61%, Y ≈ 45% (solid dark chocolate, ~9.5% head width)
 * - Cheeks: X ≈ 28% & 72%, Y ≈ 54% (pastel pink, ~17% head width)
 * - Mouth: X ≈ 50%, Y ≈ 53% (open curve with coral pink tongue)
 */
@Composable
fun BubuVector(
  modifier: Modifier = Modifier,
  size: Dp = 100.dp,
  pose: CharacterPose = CharacterPose.STANDING_HAPPY,
  emotion: CharacterEmotion = CharacterEmotion.HAPPY
) {
  Canvas(modifier = modifier.size(width = size * 0.78f, height = size)) {
    val totalW = this.size.width
    val totalH = this.size.height
    drawPandaCharacter(totalW, totalH, pose, emotion)
  }
}

/**
 * CHARACTER B — BROWN BEAR (DUDU)
 * Built with identical scale and facial geometry:
 * - Caramel brown `#C99068`
 * - Outer/inner ear structure
 * - Pale cream vertical oval belly patch `#F5E6CB`
 * - Soft peach blush `#FFB085`
 */
@Composable
fun DuduVector(
  modifier: Modifier = Modifier,
  size: Dp = 100.dp,
  pose: CharacterPose = CharacterPose.STANDING_HAPPY,
  emotion: CharacterEmotion = CharacterEmotion.HAPPY
) {
  Canvas(modifier = modifier.size(width = size * 0.78f, height = size)) {
    val totalW = this.size.width
    val totalH = this.size.height
    drawBrownBearCharacter(totalW, totalH, pose, emotion)
  }
}

private fun DrawScope.drawPandaCharacter(
  w: Float,
  h: Float,
  pose: CharacterPose,
  emotion: CharacterEmotion
) {
  val strokeW = (w * 0.024f).coerceAtLeast(1.8f)
  val outlineStroke = Stroke(width = strokeW, cap = StrokeCap.Round, join = StrokeJoin.Round)

  val headW = w * 0.88f
  val headH = h * 0.54f
  val headLeft = (w - headW) / 2f
  val headTop = h * 0.07f

  // 1. EARS (Dark Chocolate, behind head)
  val earRadius = headW * 0.12f
  val leftEarCenter = Offset(headLeft + headW * 0.15f, headTop + headH * 0.10f)
  val rightEarCenter = Offset(headLeft + headW * 0.85f, headTop + headH * 0.10f)

  drawCircle(color = PandaDarkChocolate, radius = earRadius, center = leftEarCenter)
  drawCircle(color = OutlineChocolate, radius = earRadius, center = leftEarCenter, style = outlineStroke)

  drawCircle(color = PandaDarkChocolate, radius = earRadius, center = rightEarCenter)
  drawCircle(color = OutlineChocolate, radius = earRadius, center = rightEarCenter, style = outlineStroke)

  // 2. BODY & LEGS (Compact, ~40% total height)
  val bodyTop = headTop + headH * 0.82f
  val bodyBottom = h * 0.95f
  val bodyW = headW * 0.62f
  val bodyLeft = (w - bodyW) / 2f
  val bodyRight = bodyLeft + bodyW
  val bodyH = bodyBottom - bodyTop

  val bodyPath = Path().apply {
    moveTo(bodyLeft + bodyW * 0.12f, bodyTop)
    cubicTo(bodyLeft - bodyW * 0.06f, h * 0.72f, bodyLeft + bodyW * 0.02f, bodyBottom, bodyLeft + bodyW * 0.30f, bodyBottom)
    quadraticBezierTo(w * 0.50f, bodyBottom - h * 0.035f, bodyRight - bodyW * 0.30f, bodyBottom)
    cubicTo(bodyRight - bodyW * 0.02f, bodyBottom, bodyRight + bodyW * 0.06f, h * 0.72f, bodyRight - bodyW * 0.12f, bodyTop)
    close()
  }

  // Draw Body Base (White)
  drawPath(path = bodyPath, color = PandaWhite)
  drawPath(path = bodyPath, color = OutlineChocolate, style = outlineStroke)

  // Panda dark chocolate chest/collar marking
  val collarPath = Path().apply {
    moveTo(bodyLeft + bodyW * 0.12f, bodyTop)
    quadraticBezierTo(w * 0.50f, bodyTop + bodyH * 0.22f, bodyRight - bodyW * 0.12f, bodyTop)
    lineTo(bodyRight - bodyW * 0.08f, bodyTop + bodyH * 0.32f)
    quadraticBezierTo(w * 0.50f, bodyTop + bodyH * 0.44f, bodyLeft + bodyW * 0.08f, bodyTop + bodyH * 0.32f)
    close()
  }
  drawPath(path = collarPath, color = PandaDarkChocolate)

  // 3. HEAD (Chubby rounded head with broad lower cheeks)
  val headPath = Path().apply {
    moveTo(headLeft + headW * 0.30f, headTop + headH * 0.05f)
    cubicTo(
      headLeft + headW * 0.40f, headTop,
      headLeft + headW * 0.60f, headTop,
      headLeft + headW * 0.70f, headTop + headH * 0.05f
    )
    cubicTo(
      headLeft + headW * 0.92f, headTop + headH * 0.12f,
      headLeft + headW * 1.00f, headTop + headH * 0.48f,
      headLeft + headW * 0.94f, headTop + headH * 0.78f
    )
    cubicTo(
      headLeft + headW * 0.88f, headTop + headH * 0.96f,
      headLeft + headW * 0.68f, headTop + headH * 1.00f,
      headLeft + headW * 0.50f, headTop + headH * 1.00f
    )
    cubicTo(
      headLeft + headW * 0.32f, headTop + headH * 1.00f,
      headLeft + headW * 0.12f, headTop + headH * 0.96f,
      headLeft + headW * 0.06f, headTop + headH * 0.78f
    )
    cubicTo(
      headLeft + headW * 0.00f, headTop + headH * 0.48f,
      headLeft + headW * 0.08f, headTop + headH * 0.12f,
      headLeft + headW * 0.30f, headTop + headH * 0.05f
    )
    close()
  }

  drawPath(path = headPath, color = PandaWhite)
  drawPath(path = headPath, color = OutlineChocolate, style = outlineStroke)

  // 4. FACIAL FEATURES
  val eyeRadius = headW * 0.046f
  val eyeY = headTop + headH * 0.46f
  val leftEyeX = headLeft + headW * 0.39f
  val rightEyeX = headLeft + headW * 0.61f

  drawCircle(color = EyeSolidColor, radius = eyeRadius, center = Offset(leftEyeX, eyeY))
  drawCircle(color = EyeSolidColor, radius = eyeRadius, center = Offset(rightEyeX, eyeY))

  // CHEEKS: Pastel pink
  val cheekRadius = headW * 0.085f
  val cheekY = headTop + headH * 0.55f
  val leftCheekX = headLeft + headW * 0.26f
  val rightCheekX = headLeft + headW * 0.74f

  drawCircle(color = PandaCheekPink, radius = cheekRadius, center = Offset(leftCheekX, cheekY))
  drawCircle(color = PandaCheekPink, radius = cheekRadius, center = Offset(rightCheekX, cheekY))

  // MOUTH: Small centered open curved outline with coral-pink tongue
  val mouthCenterX = headLeft + headW * 0.50f
  val mouthTopY = headTop + headH * 0.54f
  val mouthW = headW * 0.13f
  val mouthH = headH * 0.08f

  val mouthPath = Path().apply {
    moveTo(mouthCenterX - mouthW * 0.50f, mouthTopY)
    quadraticBezierTo(mouthCenterX, mouthTopY + mouthH * 1.25f, mouthCenterX + mouthW * 0.50f, mouthTopY)
    close()
  }
  drawPath(path = mouthPath, color = MouthTongueCoral)
  drawPath(path = mouthPath, color = OutlineChocolate, style = outlineStroke)

  // 5. ARMS (BOTH ARMS CLEARLY DRAWN IN FRONT WITH PLUMP ROUNDED PAWS)
  when (pose) {
    CharacterPose.WAVING -> {
      // LEFT ARM: Resting cutely on left side of belly
      val leftArm = Path().apply {
        moveTo(bodyLeft + bodyW * 0.08f, bodyTop + bodyH * 0.12f)
        cubicTo(
          bodyLeft - bodyW * 0.18f, bodyTop + bodyH * 0.25f,
          bodyLeft - bodyW * 0.08f, bodyTop + bodyH * 0.58f,
          bodyLeft + bodyW * 0.18f, bodyTop + bodyH * 0.55f
        )
        cubicTo(
          bodyLeft + bodyW * 0.32f, bodyTop + bodyH * 0.52f,
          bodyLeft + bodyW * 0.28f, bodyTop + bodyH * 0.26f,
          bodyLeft + bodyW * 0.18f, bodyTop + bodyH * 0.10f
        )
        close()
      }
      drawPath(leftArm, PandaDarkChocolate)
      drawPath(leftArm, OutlineChocolate, style = outlineStroke)

      // RIGHT ARM: Raised up high waving to the right
      val rightArm = Path().apply {
        moveTo(bodyRight - bodyW * 0.12f, bodyTop + bodyH * 0.14f)
        cubicTo(
          bodyRight + bodyW * 0.16f, bodyTop + bodyH * 0.04f,
          bodyRight + bodyW * 0.34f, bodyTop - bodyH * 0.25f,
          bodyRight + bodyW * 0.24f, bodyTop - bodyH * 0.44f
        )
        cubicTo(
          bodyRight + bodyW * 0.12f, bodyTop - bodyH * 0.50f,
          bodyRight - bodyW * 0.04f, bodyTop - bodyH * 0.32f,
          bodyRight - bodyW * 0.02f, bodyTop - bodyH * 0.10f
        )
        cubicTo(
          bodyRight - bodyW * 0.04f, bodyTop + bodyH * 0.02f,
          bodyRight - bodyW * 0.18f, bodyTop + bodyH * 0.06f,
          bodyRight - bodyW * 0.12f, bodyTop + bodyH * 0.14f
        )
        close()
      }
      drawPath(rightArm, PandaDarkChocolate)
      drawPath(rightArm, OutlineChocolate, style = outlineStroke)
    }

    CharacterPose.CHEERING_HANDS_UP -> {
      // LEFT ARM: Raised high to upper left
      val leftArm = Path().apply {
        moveTo(bodyLeft + bodyW * 0.12f, bodyTop + bodyH * 0.14f)
        cubicTo(
          bodyLeft - bodyW * 0.16f, bodyTop + bodyH * 0.04f,
          bodyLeft - bodyW * 0.34f, bodyTop - bodyH * 0.25f,
          bodyLeft - bodyW * 0.24f, bodyTop - bodyH * 0.44f
        )
        cubicTo(
          bodyLeft - bodyW * 0.12f, bodyTop - bodyH * 0.50f,
          bodyLeft + bodyW * 0.04f, bodyTop - bodyH * 0.32f,
          bodyLeft + bodyW * 0.02f, bodyTop - bodyH * 0.10f
        )
        cubicTo(
          bodyLeft + bodyW * 0.04f, bodyTop + bodyH * 0.02f,
          bodyLeft + bodyW * 0.18f, bodyTop + bodyH * 0.06f,
          bodyLeft + bodyW * 0.12f, bodyTop + bodyH * 0.14f
        )
        close()
      }
      drawPath(leftArm, PandaDarkChocolate)
      drawPath(leftArm, OutlineChocolate, style = outlineStroke)

      // RIGHT ARM: Raised high to upper right
      val rightArm = Path().apply {
        moveTo(bodyRight - bodyW * 0.12f, bodyTop + bodyH * 0.14f)
        cubicTo(
          bodyRight + bodyW * 0.16f, bodyTop + bodyH * 0.04f,
          bodyRight + bodyW * 0.34f, bodyTop - bodyH * 0.25f,
          bodyRight + bodyW * 0.24f, bodyTop - bodyH * 0.44f
        )
        cubicTo(
          bodyRight + bodyW * 0.12f, bodyTop - bodyH * 0.50f,
          bodyRight - bodyW * 0.04f, bodyTop - bodyH * 0.32f,
          bodyRight - bodyW * 0.02f, bodyTop - h * 0.10f
        )
        cubicTo(
          bodyRight - bodyW * 0.04f, bodyTop + bodyH * 0.02f,
          bodyRight - bodyW * 0.18f, bodyTop + bodyH * 0.06f,
          bodyRight - bodyW * 0.12f, bodyTop + bodyH * 0.14f
        )
        close()
      }
      drawPath(rightArm, PandaDarkChocolate)
      drawPath(rightArm, OutlineChocolate, style = outlineStroke)
    }

    CharacterPose.POINTING_RIGHT -> {
      // LEFT ARM: Resting on tummy
      val leftArm = Path().apply {
        moveTo(bodyLeft + bodyW * 0.08f, bodyTop + bodyH * 0.12f)
        cubicTo(
          bodyLeft - bodyW * 0.18f, bodyTop + bodyH * 0.25f,
          bodyLeft - bodyW * 0.08f, bodyTop + bodyH * 0.58f,
          bodyLeft + bodyW * 0.18f, bodyTop + bodyH * 0.55f
        )
        cubicTo(
          bodyLeft + bodyW * 0.32f, bodyTop + bodyH * 0.52f,
          bodyLeft + bodyW * 0.28f, bodyTop + bodyH * 0.26f,
          bodyLeft + bodyW * 0.18f, bodyTop + bodyH * 0.10f
        )
        close()
      }
      drawPath(leftArm, PandaDarkChocolate)
      drawPath(leftArm, OutlineChocolate, style = outlineStroke)

      // RIGHT ARM: Pointing to the right
      val rightArm = Path().apply {
        moveTo(bodyRight - bodyW * 0.10f, bodyTop + bodyH * 0.12f)
        cubicTo(
          bodyRight + bodyW * 0.14f, bodyTop + bodyH * 0.08f,
          bodyRight + bodyW * 0.36f, bodyTop + bodyH * 0.12f,
          bodyRight + bodyW * 0.38f, bodyTop + bodyH * 0.26f
        )
        cubicTo(
          bodyRight + bodyW * 0.36f, bodyTop + bodyH * 0.38f,
          bodyRight + bodyW * 0.18f, bodyTop + bodyH * 0.44f,
          bodyRight - bodyW * 0.06f, bodyTop + bodyH * 0.34f
        )
        close()
      }
      drawPath(rightArm, PandaDarkChocolate)
      drawPath(rightArm, OutlineChocolate, style = outlineStroke)
    }

    else -> {
      // STANDING_HAPPY (Default): BOTH ARMS resting symmetrically in front with cute rounded paws
      val leftArm = Path().apply {
        moveTo(bodyLeft + bodyW * 0.08f, bodyTop + bodyH * 0.12f)
        cubicTo(
          bodyLeft - bodyW * 0.18f, bodyTop + bodyH * 0.25f,
          bodyLeft - bodyW * 0.08f, bodyTop + bodyH * 0.58f,
          bodyLeft + bodyW * 0.18f, bodyTop + bodyH * 0.55f
        )
        cubicTo(
          bodyLeft + bodyW * 0.32f, bodyTop + bodyH * 0.52f,
          bodyLeft + bodyW * 0.28f, bodyTop + bodyH * 0.26f,
          bodyLeft + bodyW * 0.18f, bodyTop + bodyH * 0.10f
        )
        close()
      }
      drawPath(leftArm, PandaDarkChocolate)
      drawPath(leftArm, OutlineChocolate, style = outlineStroke)

      val rightArm = Path().apply {
        moveTo(bodyRight - bodyW * 0.08f, bodyTop + bodyH * 0.12f)
        cubicTo(
          bodyRight + bodyW * 0.18f, bodyTop + bodyH * 0.25f,
          bodyRight + bodyW * 0.08f, bodyTop + bodyH * 0.58f,
          bodyRight - bodyW * 0.18f, bodyTop + bodyH * 0.55f
        )
        cubicTo(
          bodyRight - bodyW * 0.32f, bodyTop + bodyH * 0.52f,
          bodyRight - bodyW * 0.28f, bodyTop + bodyH * 0.26f,
          bodyRight - bodyW * 0.18f, bodyTop + bodyH * 0.10f
        )
        close()
      }
      drawPath(rightArm, PandaDarkChocolate)
      drawPath(rightArm, OutlineChocolate, style = outlineStroke)
    }
  }
}

private fun DrawScope.drawBrownBearCharacter(
  w: Float,
  h: Float,
  pose: CharacterPose,
  emotion: CharacterEmotion
) {
  val strokeW = (w * 0.024f).coerceAtLeast(1.8f)
  val outlineStroke = Stroke(width = strokeW, cap = StrokeCap.Round, join = StrokeJoin.Round)

  val headW = w * 0.88f
  val headH = h * 0.54f
  val headLeft = (w - headW) / 2f
  val headTop = h * 0.07f

  // 1. EARS (Outer caramel brown + Inner dark chocolate)
  val outerEarRadius = headW * 0.12f
  val innerEarRadius = headW * 0.06f
  val leftEarCenter = Offset(headLeft + headW * 0.15f, headTop + headH * 0.10f)
  val rightEarCenter = Offset(headLeft + headW * 0.85f, headTop + headH * 0.10f)

  // Left Ear
  drawCircle(color = BearCaramelBrown, radius = outerEarRadius, center = leftEarCenter)
  drawCircle(color = BearInnerEarChocolate, radius = innerEarRadius, center = Offset(leftEarCenter.x + headW * 0.015f, leftEarCenter.y + headH * 0.015f))
  drawCircle(color = OutlineChocolate, radius = outerEarRadius, center = leftEarCenter, style = outlineStroke)

  // Right Ear
  drawCircle(color = BearCaramelBrown, radius = outerEarRadius, center = rightEarCenter)
  drawCircle(color = BearInnerEarChocolate, radius = innerEarRadius, center = Offset(rightEarCenter.x - headW * 0.015f, rightEarCenter.y + headH * 0.015f))
  drawCircle(color = OutlineChocolate, radius = outerEarRadius, center = rightEarCenter, style = outlineStroke)

  // 2. BODY & LEGS (Caramel Brown)
  val bodyTop = headTop + headH * 0.82f
  val bodyBottom = h * 0.95f
  val bodyW = headW * 0.62f
  val bodyLeft = (w - bodyW) / 2f
  val bodyRight = bodyLeft + bodyW
  val bodyH = bodyBottom - bodyTop

  val bodyPath = Path().apply {
    moveTo(bodyLeft + bodyW * 0.12f, bodyTop)
    cubicTo(bodyLeft - bodyW * 0.06f, h * 0.72f, bodyLeft + bodyW * 0.02f, bodyBottom, bodyLeft + bodyW * 0.30f, bodyBottom)
    quadraticBezierTo(w * 0.50f, bodyBottom - h * 0.035f, bodyRight - bodyW * 0.30f, bodyBottom)
    cubicTo(bodyRight - bodyW * 0.02f, bodyBottom, bodyRight + bodyW * 0.06f, h * 0.72f, bodyRight - bodyW * 0.12f, bodyTop)
    close()
  }

  drawPath(path = bodyPath, color = BearCaramelBrown)
  drawPath(path = bodyPath, color = OutlineChocolate, style = outlineStroke)

  // 3. BELLY PATCH: Vertical oval pale cream `#F5E6CB`
  val bellyW = bodyW * 0.46f
  val bellyH = bodyH * 0.58f
  val bellyLeft = w * 0.50f - bellyW / 2f
  val bellyTop = bodyTop + bodyH * 0.20f

  drawOval(
    color = BearCreamBelly,
    topLeft = Offset(bellyLeft, bellyTop),
    size = Size(bellyW, bellyH)
  )

  // 4. HEAD (Chubby rounded head with broad lower cheeks)
  val headPath = Path().apply {
    moveTo(headLeft + headW * 0.30f, headTop + headH * 0.05f)
    cubicTo(
      headLeft + headW * 0.40f, headTop,
      headLeft + headW * 0.60f, headTop,
      headLeft + headW * 0.70f, headTop + headH * 0.05f
    )
    cubicTo(
      headLeft + headW * 0.92f, headTop + headH * 0.12f,
      headLeft + headW * 1.00f, headTop + headH * 0.48f,
      headLeft + headW * 0.94f, headTop + headH * 0.78f
    )
    cubicTo(
      headLeft + headW * 0.88f, headTop + headH * 0.96f,
      headLeft + headW * 0.68f, headTop + headH * 1.00f,
      headLeft + headW * 0.50f, headTop + headH * 1.00f
    )
    cubicTo(
      headLeft + headW * 0.32f, headTop + headH * 1.00f,
      headLeft + headW * 0.12f, headTop + headH * 0.96f,
      headLeft + headW * 0.06f, headTop + headH * 0.78f
    )
    cubicTo(
      headLeft + headW * 0.00f, headTop + headH * 0.48f,
      headLeft + headW * 0.08f, headTop + headH * 0.12f,
      headLeft + headW * 0.30f, headTop + headH * 0.05f
    )
    close()
  }

  drawPath(path = headPath, color = BearCaramelBrown)
  drawPath(path = headPath, color = OutlineChocolate, style = outlineStroke)

  // 5. FACIAL FEATURES
  val eyeRadius = headW * 0.046f
  val eyeY = headTop + headH * 0.46f
  val leftEyeX = headLeft + headW * 0.39f
  val rightEyeX = headLeft + headW * 0.61f

  drawCircle(color = EyeSolidColor, radius = eyeRadius, center = Offset(leftEyeX, eyeY))
  drawCircle(color = EyeSolidColor, radius = eyeRadius, center = Offset(rightEyeX, eyeY))

  // CHEEKS: Warm Peach `#FFB085`
  val cheekRadius = headW * 0.085f
  val cheekY = headTop + headH * 0.55f
  val leftCheekX = headLeft + headW * 0.26f
  val rightCheekX = headLeft + headW * 0.74f

  drawCircle(color = BearCheekPeach, radius = cheekRadius, center = Offset(leftCheekX, cheekY))
  drawCircle(color = BearCheekPeach, radius = cheekRadius, center = Offset(rightCheekX, cheekY))

  // MOUTH: Small centered open shape with coral-pink tongue
  val mouthCenterX = headLeft + headW * 0.50f
  val mouthTopY = headTop + headH * 0.54f
  val mouthW = headW * 0.13f
  val mouthH = headH * 0.08f

  val mouthPath = Path().apply {
    moveTo(mouthCenterX - mouthW * 0.50f, mouthTopY)
    quadraticBezierTo(mouthCenterX, mouthTopY + mouthH * 1.25f, mouthCenterX + mouthW * 0.50f, mouthTopY)
    close()
  }
  drawPath(path = mouthPath, color = MouthTongueCoral)
  drawPath(path = mouthPath, color = OutlineChocolate, style = outlineStroke)

  // 6. ARMS (BOTH ARMS CLEARLY DRAWN IN FRONT WITH PLUMP ROUNDED PAWS)
  when (pose) {
    CharacterPose.WAVING -> {
      // LEFT ARM: Resting on belly
      val leftArm = Path().apply {
        moveTo(bodyLeft + bodyW * 0.08f, bodyTop + bodyH * 0.12f)
        cubicTo(
          bodyLeft - bodyW * 0.18f, bodyTop + bodyH * 0.25f,
          bodyLeft - bodyW * 0.08f, bodyTop + bodyH * 0.58f,
          bodyLeft + bodyW * 0.18f, bodyTop + bodyH * 0.55f
        )
        cubicTo(
          bodyLeft + bodyW * 0.32f, bodyTop + bodyH * 0.52f,
          bodyLeft + bodyW * 0.28f, bodyTop + bodyH * 0.26f,
          bodyLeft + bodyW * 0.18f, bodyTop + bodyH * 0.10f
        )
        close()
      }
      drawPath(leftArm, BearCaramelBrown)
      drawPath(leftArm, OutlineChocolate, style = outlineStroke)

      // RIGHT ARM: Waving up high
      val rightArm = Path().apply {
        moveTo(bodyRight - bodyW * 0.12f, bodyTop + bodyH * 0.14f)
        cubicTo(
          bodyRight + bodyW * 0.16f, bodyTop + bodyH * 0.04f,
          bodyRight + bodyW * 0.34f, bodyTop - bodyH * 0.25f,
          bodyRight + bodyW * 0.24f, bodyTop - bodyH * 0.44f
        )
        cubicTo(
          bodyRight + bodyW * 0.12f, bodyTop - bodyH * 0.50f,
          bodyRight - bodyW * 0.04f, bodyTop - bodyH * 0.32f,
          bodyRight - bodyW * 0.02f, bodyTop - bodyH * 0.10f
        )
        cubicTo(
          bodyRight - bodyW * 0.04f, bodyTop + bodyH * 0.02f,
          bodyRight - bodyW * 0.18f, bodyTop + bodyH * 0.06f,
          bodyRight - bodyW * 0.12f, bodyTop + bodyH * 0.14f
        )
        close()
      }
      drawPath(rightArm, BearCaramelBrown)
      drawPath(rightArm, OutlineChocolate, style = outlineStroke)
    }

    CharacterPose.CHEERING_HANDS_UP -> {
      // LEFT ARM: Cheering up
      val leftArm = Path().apply {
        moveTo(bodyLeft + bodyW * 0.12f, bodyTop + bodyH * 0.14f)
        cubicTo(
          bodyLeft - bodyW * 0.16f, bodyTop + bodyH * 0.04f,
          bodyLeft - bodyW * 0.34f, bodyTop - bodyH * 0.25f,
          bodyLeft - bodyW * 0.24f, bodyTop - bodyH * 0.44f
        )
        cubicTo(
          bodyLeft - bodyW * 0.12f, bodyTop - bodyH * 0.50f,
          bodyLeft + bodyW * 0.04f, bodyTop - bodyH * 0.32f,
          bodyLeft + bodyW * 0.02f, bodyTop - bodyH * 0.10f
        )
        cubicTo(
          bodyLeft + bodyW * 0.04f, bodyTop + bodyH * 0.02f,
          bodyLeft + bodyW * 0.18f, bodyTop + bodyH * 0.06f,
          bodyLeft + bodyW * 0.12f, bodyTop + bodyH * 0.14f
        )
        close()
      }
      drawPath(leftArm, BearCaramelBrown)
      drawPath(leftArm, OutlineChocolate, style = outlineStroke)

      // RIGHT ARM: Cheering up
      val rightArm = Path().apply {
        moveTo(bodyRight - bodyW * 0.12f, bodyTop + bodyH * 0.14f)
        cubicTo(
          bodyRight + bodyW * 0.16f, bodyTop + bodyH * 0.04f,
          bodyRight + bodyW * 0.34f, bodyTop - bodyH * 0.25f,
          bodyRight + bodyW * 0.24f, bodyTop - bodyH * 0.44f
        )
        cubicTo(
          bodyRight + bodyW * 0.12f, bodyTop - bodyH * 0.50f,
          bodyRight - bodyW * 0.04f, bodyTop - bodyH * 0.32f,
          bodyRight - bodyW * 0.02f, bodyTop - bodyH * 0.10f
        )
        cubicTo(
          bodyRight - bodyW * 0.04f, bodyTop + bodyH * 0.02f,
          bodyRight - bodyW * 0.18f, bodyTop + bodyH * 0.06f,
          bodyRight - bodyW * 0.12f, bodyTop + bodyH * 0.14f
        )
        close()
      }
      drawPath(rightArm, BearCaramelBrown)
      drawPath(rightArm, OutlineChocolate, style = outlineStroke)
    }

    CharacterPose.POINTING_RIGHT -> {
      val leftArm = Path().apply {
        moveTo(bodyLeft + bodyW * 0.08f, bodyTop + bodyH * 0.12f)
        cubicTo(
          bodyLeft - bodyW * 0.18f, bodyTop + bodyH * 0.25f,
          bodyLeft - bodyW * 0.08f, bodyTop + bodyH * 0.58f,
          bodyLeft + bodyW * 0.18f, bodyTop + bodyH * 0.55f
        )
        cubicTo(
          bodyLeft + bodyW * 0.32f, bodyTop + bodyH * 0.52f,
          bodyLeft + bodyW * 0.28f, bodyTop + bodyH * 0.26f,
          bodyLeft + bodyW * 0.18f, bodyTop + bodyH * 0.10f
        )
        close()
      }
      drawPath(leftArm, BearCaramelBrown)
      drawPath(leftArm, OutlineChocolate, style = outlineStroke)

      val rightArm = Path().apply {
        moveTo(bodyRight - bodyW * 0.10f, bodyTop + bodyH * 0.12f)
        cubicTo(
          bodyRight + bodyW * 0.14f, bodyTop + bodyH * 0.08f,
          bodyRight + bodyW * 0.36f, bodyTop + bodyH * 0.12f,
          bodyRight + bodyW * 0.38f, bodyTop + bodyH * 0.26f
        )
        cubicTo(
          bodyRight + bodyW * 0.36f, bodyTop + bodyH * 0.38f,
          bodyRight + bodyW * 0.18f, bodyTop + bodyH * 0.44f,
          bodyRight - bodyW * 0.06f, bodyTop + bodyH * 0.34f
        )
        close()
      }
      drawPath(rightArm, BearCaramelBrown)
      drawPath(rightArm, OutlineChocolate, style = outlineStroke)
    }

    else -> {
      // STANDING_HAPPY (Default): BOTH ARMS resting symmetrically in front with cute rounded paws
      val leftArm = Path().apply {
        moveTo(bodyLeft + bodyW * 0.08f, bodyTop + bodyH * 0.12f)
        cubicTo(
          bodyLeft - bodyW * 0.18f, bodyTop + bodyH * 0.25f,
          bodyLeft - bodyW * 0.08f, bodyTop + bodyH * 0.58f,
          bodyLeft + bodyW * 0.18f, bodyTop + bodyH * 0.55f
        )
        cubicTo(
          bodyLeft + bodyW * 0.32f, bodyTop + bodyH * 0.52f,
          bodyLeft + bodyW * 0.28f, bodyTop + bodyH * 0.26f,
          bodyLeft + bodyW * 0.18f, bodyTop + bodyH * 0.10f
        )
        close()
      }
      drawPath(leftArm, BearCaramelBrown)
      drawPath(leftArm, OutlineChocolate, style = outlineStroke)

      val rightArm = Path().apply {
        moveTo(bodyRight - bodyW * 0.08f, bodyTop + bodyH * 0.12f)
        cubicTo(
          bodyRight + bodyW * 0.18f, bodyTop + bodyH * 0.25f,
          bodyRight + bodyW * 0.08f, bodyTop + bodyH * 0.58f,
          bodyRight - bodyW * 0.18f, bodyTop + bodyH * 0.55f
        )
        cubicTo(
          bodyRight - bodyW * 0.32f, bodyTop + bodyH * 0.52f,
          bodyRight - bodyW * 0.28f, bodyTop + bodyH * 0.26f,
          bodyRight - bodyW * 0.18f, bodyTop + bodyH * 0.10f
        )
        close()
      }
      drawPath(rightArm, BearCaramelBrown)
      drawPath(rightArm, OutlineChocolate, style = outlineStroke)
    }
  }
}

/**
 * Avatar for DUDU (Brown Bear) - Clean transparent vector inside circle container.
 */
@Composable
fun DuduAvatar(
  modifier: Modifier = Modifier,
  size: Dp = 44.dp
) {
  Box(
    modifier = modifier
      .size(size)
      .shadow(2.dp, CircleShape)
      .clip(CircleShape)
      .background(Color(0xFFFFF7EF))
      .border(1.5.dp, BearCaramelBrown.copy(alpha = 0.5f), CircleShape)
      .padding(3.dp),
    contentAlignment = Alignment.Center
  ) {
    DuduVector(
      modifier = Modifier.fillMaxSize(),
      size = size,
      pose = CharacterPose.STANDING_HAPPY
    )
  }
}

/**
 * Avatar for BUBU (Panda) - Clean transparent vector inside circle container.
 */
@Composable
fun BubuAvatar(
  modifier: Modifier = Modifier,
  size: Dp = 44.dp
) {
  Box(
    modifier = modifier
      .size(size)
      .shadow(2.dp, CircleShape)
      .clip(CircleShape)
      .background(Color(0xFFF9FBFA))
      .border(1.5.dp, PrimaryJade.copy(alpha = 0.4f), CircleShape)
      .padding(3.dp),
    contentAlignment = Alignment.Center
  ) {
    BubuVector(
      modifier = Modifier.fillMaxSize(),
      size = size,
      pose = CharacterPose.STANDING_HAPPY
    )
  }
}

/**
 * Double Mascot Avatar: DUDU and BUBU together at equal scale on same ground level.
 */
@Composable
fun DuduAndBubuAvatar(
  modifier: Modifier = Modifier,
  size: Dp = 48.dp
) {
  Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center
  ) {
    // DUDU (Left)
    DuduVector(
      size = size,
      pose = CharacterPose.STANDING_HAPPY
    )

    Spacer(modifier = Modifier.width(4.dp))

    // BUBU (Right)
    BubuVector(
      size = size,
      pose = CharacterPose.WAVING
    )
  }
}

/**
 * Interactive DUDU & BUBU Scene Component for Lessons, Discover, and Questions.
 */
@Composable
fun DuduAndBubuInteractionCard(
  title: String,
  subtitle: String,
  duduDialogue: String = "",
  bubuDialogue: String = "",
  onPlayAudio: ((String) -> Unit)? = null,
  modifier: Modifier = Modifier,
  illustrationType: String = "general"
) {
  val infiniteTransition = rememberInfiniteTransition(label = "duo_bob")
  val bobY by infiniteTransition.animateFloat(
    initialValue = -2.5f,
    targetValue = 2.5f,
    animationSpec = infiniteRepeatable(
      animation = tween(1400, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "bob"
  )

  Box(
    modifier = modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(22.dp))
      .background(
        Brush.verticalGradient(
          listOf(Color(0xFFFFFBF5), Color(0xFFF3EFE6))
        )
      )
      .border(1.5.dp, WarmGold.copy(alpha = 0.5f), RoundedCornerShape(22.dp))
      .padding(14.dp)
  ) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      // Header tag
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
          .clip(RoundedCornerShape(10.dp))
          .background(Color.White)
          .border(1.dp, Color(0xFFE0D8C8), RoundedCornerShape(10.dp))
          .padding(horizontal = 10.dp, vertical = 4.dp)
      ) {
        Text(text = "🐻 DUDU & 🐼 BUBU", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DarkJade)
        if (title.isNotEmpty()) {
          Text(text = " • $title", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = PrimaryJade)
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Main Interaction Area
      when (illustrationType) {
        "hello", "waving" -> {
          // DUDU & BUBU greeting each other!
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
          ) {
            // DUDU
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Box(
                modifier = Modifier
                  .clip(RoundedCornerShape(12.dp))
                  .background(Color(0xFFFFE8D6))
                  .border(1.dp, Color(0xFFFFCC80), RoundedCornerShape(12.dp))
                  .padding(horizontal = 10.dp, vertical = 4.dp)
              ) {
                Text(text = "DUDU: 你好！", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFFE65100))
              }
              Spacer(modifier = Modifier.height(6.dp))
              DuduVector(
                size = 76.dp,
                modifier = Modifier.offset(y = bobY.dp),
                pose = CharacterPose.WAVING
              )
            }

            // Central Greeting Icon
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Text(text = "👋", fontSize = 24.sp)
              Text(text = "Chào nhau", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DarkJade)
            }

            // BUBU
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Box(
                modifier = Modifier
                  .clip(RoundedCornerShape(12.dp))
                  .background(JadeContainer)
                  .border(1.dp, Color(0xFFC8E6C9), RoundedCornerShape(12.dp))
                  .padding(horizontal = 10.dp, vertical = 4.dp)
              ) {
                Text(text = "BUBU: 你好！", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DarkJade)
              }
              Spacer(modifier = Modifier.height(6.dp))
              BubuVector(
                size = 76.dp,
                modifier = Modifier.offset(y = (-bobY).dp),
                pose = CharacterPose.WAVING
              )
            }
          }
        }

        "pronoun_you", "point_you" -> {
          // DUDU pointing to BUBU: "你" (Bạn)
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Box(
                modifier = Modifier
                  .clip(RoundedCornerShape(12.dp))
                  .background(Color(0xFFFFE8D6))
                  .padding(horizontal = 8.dp, vertical = 4.dp)
              ) {
                Text(text = "DUDU: '你' (Bạn)", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFFE65100))
              }
              Spacer(modifier = Modifier.height(6.dp))
              DuduVector(size = 76.dp, pose = CharacterPose.POINTING_RIGHT)
            }
            Text(text = "👉", fontSize = 22.sp)
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Box(
                modifier = Modifier
                  .clip(RoundedCornerShape(12.dp))
                  .background(JadeContainer)
                  .padding(horizontal = 8.dp, vertical = 4.dp)
              ) {
                Text(text = "BUBU lắng nghe", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DarkJade)
              }
              Spacer(modifier = Modifier.height(6.dp))
              BubuVector(size = 76.dp, pose = CharacterPose.STANDING_HAPPY)
            }
          }
        }

        "pronoun_i", "point_i" -> {
          // BUBU: "我" (Tôi / Mình)
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Box(
                modifier = Modifier
                  .clip(RoundedCornerShape(12.dp))
                  .background(Color(0xFFFFE8D6))
                  .padding(horizontal = 8.dp, vertical = 4.dp)
              ) {
                Text(text = "DUDU mỉm cười", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFFE65100))
              }
              Spacer(modifier = Modifier.height(6.dp))
              DuduVector(size = 76.dp, pose = CharacterPose.STANDING_HAPPY)
            }
            Text(text = "💖", fontSize = 22.sp)
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Box(
                modifier = Modifier
                  .clip(RoundedCornerShape(12.dp))
                  .background(JadeContainer)
                  .padding(horizontal = 8.dp, vertical = 4.dp)
              ) {
                Text(text = "BUBU: '我' (Tôi / Bản thân)", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DarkJade)
              }
              Spacer(modifier = Modifier.height(6.dp))
              BubuVector(size = 76.dp, pose = CharacterPose.WAVING)
            }
          }
        }

        "thankyou", "thanks" -> {
          // DUDU: "谢谢！" -> BUBU: "不客气！"
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Box(
                modifier = Modifier
                  .clip(RoundedCornerShape(12.dp))
                  .background(Color(0xFFFFEBEE))
                  .border(1.dp, Color(0xFFFFCDD2), RoundedCornerShape(12.dp))
                  .padding(horizontal = 8.dp, vertical = 4.dp)
              ) {
                Text(text = "DUDU: 谢谢！(Cảm ơn)", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFFC2185B))
              }
              Spacer(modifier = Modifier.height(6.dp))
              DuduVector(size = 74.dp, pose = CharacterPose.CHEERING_HANDS_UP)
            }
            Text(text = "🎁", fontSize = 24.sp)
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Box(
                modifier = Modifier
                  .clip(RoundedCornerShape(12.dp))
                  .background(JadeContainer)
                  .border(1.dp, Color(0xFFC8E6C9), RoundedCornerShape(12.dp))
                  .padding(horizontal = 8.dp, vertical = 4.dp)
              ) {
                Text(text = "BUBU: 不客气！(Đừng khách sáo)", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DarkJade)
              }
              Spacer(modifier = Modifier.height(6.dp))
              BubuVector(size = 74.dp, pose = CharacterPose.WAVING)
            }
          }
        }

        else -> {
          // Default side-by-side friendly duo
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
          ) {
            DuduVector(
              size = 72.dp,
              modifier = Modifier.offset(y = bobY.dp),
              pose = CharacterPose.STANDING_HAPPY
            )

            // Speech Bubble Center
            Box(
              modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
                .shadow(3.dp, RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .border(1.dp, WarmGold.copy(alpha = 0.6f), RoundedCornerShape(16.dp))
                .padding(10.dp),
              contentAlignment = Alignment.Center
            ) {
              Column(horizontalAlignment = Alignment.CenterHorizontally) {
                if (duduDialogue.isNotEmpty() || bubuDialogue.isNotEmpty()) {
                  if (duduDialogue.isNotEmpty()) {
                    Text(
                      text = "🐻 DUDU: $duduDialogue",
                      fontSize = 13.sp,
                      fontWeight = FontWeight.Bold,
                      color = Color(0xFFE65100),
                      textAlign = TextAlign.Center
                    )
                  }
                  if (bubuDialogue.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                      text = "🐼 BUBU: $bubuDialogue",
                      fontSize = 13.sp,
                      fontWeight = FontWeight.Bold,
                      color = DarkJade,
                      textAlign = TextAlign.Center
                    )
                  }
                } else {
                  Text(
                    text = subtitle.ifEmpty { "Cùng DUDU & BUBU học tiếng Trung nhé!" },
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkJade,
                    textAlign = TextAlign.Center
                  )
                }

                if (onPlayAudio != null && (duduDialogue.isNotEmpty() || bubuDialogue.isNotEmpty())) {
                  Spacer(modifier = Modifier.height(6.dp))
                  Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                      .clip(CircleShape)
                      .background(JadeContainer)
                      .clickable { onPlayAudio(duduDialogue.ifEmpty { bubuDialogue }) }
                      .padding(horizontal = 10.dp, vertical = 3.dp)
                  ) {
                    Icon(Icons.Filled.VolumeUp, contentDescription = null, tint = PrimaryJade, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Nghe", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = PrimaryJade)
                  }
                }
              }
            }

            BubuVector(
              size = 72.dp,
              modifier = Modifier.offset(y = (-bobY).dp),
              pose = CharacterPose.WAVING
            )
          }
        }
      }
    }
  }
}

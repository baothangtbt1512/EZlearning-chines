package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui.theme.LanternRed
import com.example.ui.theme.PrimaryJade
import com.example.ui.theme.RoofTerracotta
import com.example.ui.theme.WarmCream
import com.example.ui.theme.WarmGold

@Composable
fun TraditionalHouseIllustration(
  modifier: Modifier = Modifier,
  size: Dp = 140.dp
) {
  Canvas(modifier = modifier.size(size)) {
    val w = this.size.width
    val h = this.size.height

    // 1. Soft Warm Courtyard Glow / Background
    drawCircle(
      brush = Brush.radialGradient(
        colors = listOf(WarmCream, Color(0xFFF9EFE0), Color.Transparent),
        center = Offset(w * 0.5f, h * 0.5f),
        radius = w * 0.55f
      ),
      center = Offset(w * 0.5f, h * 0.5f),
      radius = w * 0.55f
    )

    // 2. Stone Ground Terrace
    drawRoundRect(
      brush = Brush.verticalGradient(
        colors = listOf(Color(0xFFE8DFD3), Color(0xFFC7BBAA)),
        startY = h * 0.82f,
        endY = h
      ),
      topLeft = Offset(w * 0.05f, h * 0.82f),
      size = Size(w * 0.90f, h * 0.18f),
      cornerRadius = CornerRadius(w * 0.04f, w * 0.04f)
    )

    // 3. House Main Walls (Warm stucco plaster)
    drawRoundRect(
      color = Color(0xFFFBF6EE),
      topLeft = Offset(w * 0.18f, h * 0.38f),
      size = Size(w * 0.64f, h * 0.46f),
      cornerRadius = CornerRadius(w * 0.02f, w * 0.02f)
    )

    // Timber Wooden Posts
    val postColor = Color(0xFF6B4524)
    drawRect(
      color = postColor,
      topLeft = Offset(w * 0.18f, h * 0.38f),
      size = Size(w * 0.04f, h * 0.46f)
    )
    drawRect(
      color = postColor,
      topLeft = Offset(w * 0.78f, h * 0.38f),
      size = Size(w * 0.04f, h * 0.46f)
    )

    // 4. Open Wooden Doorway with Interior Warmth
    drawRoundRect(
      brush = Brush.verticalGradient(
        colors = listOf(Color(0xFFFFF2D6), Color(0xFFFFDF9E), Color(0xFFE0B46A)),
        startY = h * 0.45f,
        endY = h * 0.84f
      ),
      topLeft = Offset(w * 0.36f, h * 0.45f),
      size = Size(w * 0.28f, h * 0.39f),
      cornerRadius = CornerRadius(w * 0.03f, w * 0.03f)
    )
    // Door Wooden Frame
    drawRoundRect(
      color = Color(0xFF5A381C),
      topLeft = Offset(w * 0.35f, h * 0.44f),
      size = Size(w * 0.30f, h * 0.40f),
      cornerRadius = CornerRadius(w * 0.03f, w * 0.03f),
      style = Stroke(width = w * 0.025f)
    )

    // Doorway Table & Tea set silhouette
    drawRect(
      color = Color(0xFF7A4A28),
      topLeft = Offset(w * 0.40f, h * 0.68f),
      size = Size(w * 0.20f, h * 0.04f)
    )

    // 5. Vertical Chinese Banner / Couplet (Red plaque on right wall)
    drawRoundRect(
      color = LanternRed,
      topLeft = Offset(w * 0.68f, h * 0.46f),
      size = Size(w * 0.065f, h * 0.26f),
      cornerRadius = CornerRadius(w * 0.01f, w * 0.01f)
    )
    // Gold dots representing Chinese characters "坚持学习"
    for (i in 0..3) {
      drawCircle(
        color = WarmGold,
        radius = w * 0.012f,
        center = Offset(w * 0.712f, h * (0.50f + i * 0.055f))
      )
    }

    // 6. Traditional Flared Eaves Tile Roof
    val roofPath = Path().apply {
      moveTo(w * 0.08f, h * 0.40f)
      cubicTo(
        w * 0.22f, h * 0.32f,
        w * 0.35f, h * 0.16f,
        w * 0.50f, h * 0.14f
      )
      cubicTo(
        w * 0.65f, h * 0.16f,
        w * 0.78f, h * 0.32f,
        w * 0.92f, h * 0.40f
      )
      lineTo(w * 0.84f, h * 0.41f)
      lineTo(w * 0.16f, h * 0.41f)
      close()
    }
    drawPath(
      path = roofPath,
      brush = Brush.verticalGradient(
        colors = listOf(Color(0xFF374643), Color(0xFF23302E), RoofTerracotta.copy(alpha = 0.5f)),
        startY = h * 0.14f,
        endY = h * 0.41f
      )
    )

    // Roof Top Ridge & Finials
    drawRoundRect(
      color = Color(0xFF1E2826),
      topLeft = Offset(w * 0.30f, h * 0.13f),
      size = Size(w * 0.40f, h * 0.035f),
      cornerRadius = CornerRadius(w * 0.015f, w * 0.015f)
    )

    // 7. Hanging Red Lanterns with Gold Tassels
    // Left Lantern
    drawLantern(this, w * 0.26f, h * 0.40f, w * 0.07f, h * 0.09f)
    // Right Lantern
    drawLantern(this, w * 0.74f, h * 0.40f, w * 0.07f, h * 0.09f)

    // 8. Ceramic Pots with Bamboo / Green Plants
    drawPottedPlant(this, w * 0.12f, h * 0.74f, w * 0.10f, h * 0.12f)
    drawPottedPlant(this, w * 0.78f, h * 0.74f, w * 0.10f, h * 0.12f)
  }
}

private fun drawLantern(
  drawScope: androidx.compose.ui.graphics.drawscope.DrawScope,
  x: Float,
  y: Float,
  w: Float,
  h: Float
) {
  // String
  drawScope.drawLine(
    color = Color(0xFF5A381C),
    start = Offset(x, y - h * 0.3f),
    end = Offset(x, y),
    strokeWidth = 2f
  )
  // Oval Lantern Body
  drawScope.drawOval(
    color = LanternRed,
    topLeft = Offset(x - w / 2, y),
    size = Size(w, h)
  )
  // Gold top & bottom caps
  drawScope.drawRoundRect(
    color = WarmGold,
    topLeft = Offset(x - w * 0.35f, y - h * 0.08f),
    size = Size(w * 0.7f, h * 0.16f),
    cornerRadius = CornerRadius(2f, 2f)
  )
  drawScope.drawRoundRect(
    color = WarmGold,
    topLeft = Offset(x - w * 0.35f, y + h * 0.92f),
    size = Size(w * 0.7f, h * 0.16f),
    cornerRadius = CornerRadius(2f, 2f)
  )
  // Gold Tassel
  drawScope.drawLine(
    color = WarmGold,
    start = Offset(x, y + h * 1.08f),
    end = Offset(x, y + h * 1.45f),
    strokeWidth = 3f
  )
}

private fun drawPottedPlant(
  drawScope: androidx.compose.ui.graphics.drawscope.DrawScope,
  x: Float,
  y: Float,
  w: Float,
  h: Float
) {
  // Clay Pot
  val potPath = Path().apply {
    moveTo(x, y + h * 0.45f)
    lineTo(x + w, y + h * 0.45f)
    lineTo(x + w * 0.85f, y + h)
    lineTo(x + w * 0.15f, y + h)
    close()
  }
  drawScope.drawPath(potPath, color = RoofTerracotta)

  // Green Bush / Bamboo leaves
  drawScope.drawCircle(
    color = PrimaryJade,
    radius = w * 0.45f,
    center = Offset(x + w / 2, y + h * 0.35f)
  )
  drawScope.drawCircle(
    color = Color(0xFF1E6B56),
    radius = w * 0.35f,
    center = Offset(x + w * 0.3f, y + h * 0.2f)
  )
  drawScope.drawCircle(
    color = Color(0xFF2D8A70),
    radius = w * 0.35f,
    center = Offset(x + w * 0.7f, y + h * 0.2f)
  )
}

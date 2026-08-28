package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.example.ui.theme.DarkJade
import com.example.ui.theme.MountainMist
import com.example.ui.theme.PrimaryJade
import com.example.ui.theme.RoofTerracotta
import com.example.ui.theme.SkyPeach
import com.example.ui.theme.SkyWarmYellow
import com.example.ui.theme.WarmGold

@Composable
fun GreatWallHeroCanvas(
  modifier: Modifier = Modifier,
  showFullHeightJourney: Boolean = false
) {
  Canvas(modifier = modifier.fillMaxSize()) {
    val w = size.width
    val h = size.height

    // 1. Sky & Sun Glow Background
    drawRect(
      brush = Brush.verticalGradient(
        colors = listOf(
          SkyWarmYellow,
          SkyPeach,
          Color(0xFFE8F1EB),
          Color(0xFFD3E5DC)
        ),
        startY = 0f,
        endY = h
      )
    )

    // Warm Sun circle
    drawCircle(
      brush = Brush.radialGradient(
        colors = listOf(
          WarmGold.copy(alpha = 0.5f),
          WarmGold.copy(alpha = 0.2f),
          Color.Transparent
        ),
        center = Offset(w * 0.82f, h * 0.22f),
        radius = w * 0.45f
      ),
      center = Offset(w * 0.82f, h * 0.22f),
      radius = w * 0.45f
    )

    // 2. Distant Misty Mountains
    val distantMountainPath = Path().apply {
      moveTo(0f, h * 0.45f)
      cubicTo(w * 0.25f, h * 0.32f, w * 0.40f, h * 0.42f, w * 0.65f, h * 0.28f)
      cubicTo(w * 0.80f, h * 0.22f, w * 0.92f, h * 0.36f, w, h * 0.30f)
      lineTo(w, h)
      lineTo(0f, h)
      close()
    }
    drawPath(
      path = distantMountainPath,
      brush = Brush.verticalGradient(
        colors = listOf(MountainMist.copy(alpha = 0.6f), Color(0xFFC7DBD3).copy(alpha = 0.8f)),
        startY = h * 0.25f,
        endY = h * 0.60f
      )
    )

    // 3. Mid-ground Mountain Ridges
    val midMountainPath = Path().apply {
      moveTo(0f, h * 0.55f)
      cubicTo(w * 0.15f, h * 0.42f, w * 0.35f, h * 0.50f, w * 0.55f, h * 0.38f)
      cubicTo(w * 0.75f, h * 0.30f, w * 0.88f, h * 0.44f, w, h * 0.40f)
      lineTo(w, h)
      lineTo(0f, h)
      close()
    }
    drawPath(
      path = midMountainPath,
      brush = Brush.verticalGradient(
        colors = listOf(Color(0xFF6E998E), Color(0xFF4C756B)),
        startY = h * 0.30f,
        endY = h * 0.75f
      )
    )

    // 4. Foreground Green Hills
    val foregroundHills = Path().apply {
      moveTo(0f, h * 0.68f)
      cubicTo(w * 0.28f, h * 0.58f, w * 0.52f, h * 0.65f, w * 0.78f, h * 0.52f)
      cubicTo(w * 0.90f, h * 0.48f, w * 0.96f, h * 0.58f, w, h * 0.56f)
      lineTo(w, h)
      lineTo(0f, h)
      close()
    }
    drawPath(
      path = foregroundHills,
      brush = Brush.verticalGradient(
        colors = listOf(PrimaryJade, DarkJade),
        startY = h * 0.45f,
        endY = h
      )
    )

    // 5. Great Wall Stone Rampart & Watchtowers
    drawGreatWallPath(w, h, showFullHeightJourney)

    // 6. Bamboo Foliage Accents on the Left & Right
    drawBambooSprigs(w, h)
  }
}

private fun DrawScope.drawGreatWallPath(w: Float, h: Float, fullHeight: Boolean) {
  // Upper Watchtower
  val towerX = w * 0.65f
  val towerY = if (fullHeight) h * 0.12f else h * 0.18f
  val towerW = w * 0.18f
  val towerH = h * 0.10f

  // Stone base of watchtower
  drawRect(
    brush = Brush.verticalGradient(
      colors = listOf(Color(0xFF9E8E7C), Color(0xFF7A6B5B)),
      startY = towerY,
      endY = towerY + towerH
    ),
    topLeft = Offset(towerX - towerW / 2, towerY + towerH * 0.35f),
    size = Size(towerW, towerH * 0.65f)
  )

  // Watchtower Arched Windows
  drawCircle(
    color = Color(0xFF3B2F23),
    radius = towerW * 0.12f,
    center = Offset(towerX - towerW * 0.25f, towerY + towerH * 0.65f)
  )
  drawCircle(
    color = Color(0xFF3B2F23),
    radius = towerW * 0.12f,
    center = Offset(towerX + towerW * 0.25f, towerY + towerH * 0.65f)
  )

  // Watchtower Pagoda Curved Roof (Terracotta & Gold rim)
  val roofPath = Path().apply {
    moveTo(towerX - towerW * 0.72f, towerY + towerH * 0.38f)
    cubicTo(
      towerX - towerW * 0.45f, towerY + towerH * 0.28f,
      towerX - towerW * 0.25f, towerY + towerH * 0.05f,
      towerX, towerY
    )
    cubicTo(
      towerX + towerW * 0.25f, towerY + towerH * 0.05f,
      towerX + towerW * 0.45f, towerY + towerH * 0.28f,
      towerX + towerW * 0.72f, towerY + towerH * 0.38f
    )
    lineTo(towerX + towerW * 0.55f, towerY + towerH * 0.38f)
    lineTo(towerX - towerW * 0.55f, towerY + towerH * 0.38f)
    close()
  }
  drawPath(roofPath, color = RoofTerracotta)

  // Roof crest finial
  drawCircle(
    color = WarmGold,
    radius = towerW * 0.06f,
    center = Offset(towerX, towerY)
  )

  // Winding Great Wall stone path snaking downwards
  val wallPath = Path().apply {
    moveTo(towerX, towerY + towerH)
    cubicTo(
      w * 0.48f, towerY + towerH + h * 0.08f,
      w * 0.32f, towerY + towerH + h * 0.18f,
      w * 0.42f, towerY + towerH + h * 0.32f
    )
    if (fullHeight) {
      cubicTo(
        w * 0.55f, towerY + towerH + h * 0.44f,
        w * 0.30f, towerY + towerH + h * 0.60f,
        w * 0.48f, h * 0.95f
      )
    }
  }

  // Stone Wall base stroke
  drawPath(
    path = wallPath,
    brush = Brush.verticalGradient(
      colors = listOf(Color(0xFFBAAA98), Color(0xFF8C7A68), Color(0xFF6E5F50)),
      startY = towerY,
      endY = h
    ),
    style = androidx.compose.ui.graphics.drawscope.Stroke(
      width = w * 0.12f,
      cap = StrokeCap.Round
    )
  )

  // Inner walking stones of Great Wall
  drawPath(
    path = wallPath,
    brush = Brush.verticalGradient(
      colors = listOf(Color(0xFFE4D7C7), Color(0xFFC4B4A2), Color(0xFFA69582)),
      startY = towerY,
      endY = h
    ),
    style = androidx.compose.ui.graphics.drawscope.Stroke(
      width = w * 0.07f,
      cap = StrokeCap.Round
    )
  )

  // Lower Left Watchtower on the wall
  val lowerTowerX = w * 0.26f
  val lowerTowerY = h * 0.48f
  val lowerTowerW = w * 0.22f
  val lowerTowerH = h * 0.12f

  drawRect(
    brush = Brush.verticalGradient(
      colors = listOf(Color(0xFF8A7A6A), Color(0xFF5F5143)),
      startY = lowerTowerY,
      endY = lowerTowerY + lowerTowerH
    ),
    topLeft = Offset(lowerTowerX - lowerTowerW / 2, lowerTowerY + lowerTowerH * 0.35f),
    size = Size(lowerTowerW, lowerTowerH * 0.65f)
  )

  // Lower watchtower roof
  val lowerRoof = Path().apply {
    moveTo(lowerTowerX - lowerTowerW * 0.70f, lowerTowerY + lowerTowerH * 0.36f)
    cubicTo(
      lowerTowerX - lowerTowerW * 0.4f, lowerTowerY + lowerTowerH * 0.25f,
      lowerTowerX - lowerTowerW * 0.2f, lowerTowerY + lowerTowerH * 0.05f,
      lowerTowerX, lowerTowerY
    )
    cubicTo(
      lowerTowerX + lowerTowerW * 0.2f, lowerTowerY + lowerTowerH * 0.05f,
      lowerTowerX + lowerTowerW * 0.4f, lowerTowerY + lowerTowerH * 0.25f,
      lowerTowerX + lowerTowerW * 0.70f, lowerTowerY + lowerTowerH * 0.36f
    )
    lineTo(lowerTowerX + lowerTowerW * 0.5f, lowerTowerY + lowerTowerH * 0.36f)
    lineTo(lowerTowerX - lowerTowerW * 0.5f, lowerTowerY + lowerTowerH * 0.36f)
    close()
  }
  drawPath(lowerRoof, color = RoofTerracotta)
}

private fun DrawScope.drawBambooSprigs(w: Float, h: Float) {
  // Left Bamboo stalks
  for (i in 0..2) {
    val xBase = w * (0.02f + i * 0.03f)
    drawLine(
      color = Color(0xFF2C5E43),
      start = Offset(xBase, 0f),
      end = Offset(xBase + w * 0.02f, h * 0.5f),
      strokeWidth = w * 0.012f,
      cap = StrokeCap.Round
    )
  }

  // Right Bamboo leaves
  for (i in 0..3) {
    val xLeaf = w * (0.92f - i * 0.04f)
    val yLeaf = h * (0.08f + i * 0.06f)
    val leafPath = Path().apply {
      moveTo(xLeaf, yLeaf)
      cubicTo(xLeaf - w * 0.06f, yLeaf + h * 0.02f, xLeaf - w * 0.08f, yLeaf + h * 0.04f, xLeaf - w * 0.10f, yLeaf + h * 0.03f)
      cubicTo(xLeaf - w * 0.06f, yLeaf + h * 0.01f, xLeaf - w * 0.02f, yLeaf - h * 0.01f, xLeaf, yLeaf)
      close()
    }
    drawPath(leafPath, color = Color(0xFF387252))
  }
}

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountTree
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.model.JourneyNode
import com.example.model.NodeIconType
import com.example.model.WorldData
import com.example.ui.theme.DarkJade
import com.example.ui.theme.DarkText
import com.example.ui.theme.PrimaryJade
import com.example.ui.theme.SecondaryText
import com.example.ui.theme.WarmCream
import com.example.ui.theme.WarmGold

private const val JOURNEY_NODE_1_HOME_URL = "https://lh3.googleusercontent.com/aida-public/AB6AXuBsQih4cmI5fgBylhRVJt4diV9jRSFB3DpFhET05LaXI8la6BJLvKauwLBVk0QB2n-KTh0CNbAJUMr4w_et_d2jryRnCA2y_GDJe_dbCOLj-Y8qriKNx4X2DJ7zd5ZPVwMl6AQ--IpAnM6X81w4CgCuhdmTYAlsY7bKBqMrP9w8PPYrxeDqyUTx6oY33RWJ-mV5wNzRSH9bH7nfWfugoB11MSP6Ii2ySQDOndqZ37hzp-KWcXxv0HBjbw"
private const val JOURNEY_NODE_2_GATE_URL = "https://lh3.googleusercontent.com/aida-public/AB6AXuCISeFy9rVxNlMB5YDCnABcnit-J6SMOoE8Qo4ArMIaAuM5jQGgjP9YdOBFHQkaaj7oB6T8X5Z9k5-MJmU-77awgyDzMsZTmC7hNO8CiWxvDTNVj3v_vw49pg7u6UnX7oGSB8HLFX_PFvyR4-YqT5nD_MziqjBYu4t9GIu-oQ6wU04s-njqca7F1dg2-Jwf3YdCIC1GWCwkxcdnEEVH9xoUvjyXgEWy0a_1YpF1pglOufsDCVskvGf65w"
private const val JOURNEY_NODE_4_PANDA_URL = "https://lh3.googleusercontent.com/aida-public/AB6AXuAqnmniEamuhKVk7ga5xxXxT_3LDcKeK1RyE13fAVsQ2LLWSFq4MJgvBIHUIKus48VxT2LqjAhWDWjkqFfopz6HRl65buQnOe86sCW29qxFknfIhLNRzubUe2kIdMTQ6YiJ7DSB6wHSjv6mY3OGuanF0YFpb5QqSlSJyGBVGHmwCw5DN7YCZ_owXEDjnQet4MIxmPvcTj5CyjaPDRqVFLaUoBd9qDNS8-ak83GKYwjzKiXxL7wRWLFdog"

/**
 * Clean, serene atmospheric background with smooth gradients and gentle misty hills.
 * Completely free of harsh vertical lines, stripes, or grid cuts.
 */
@Composable
fun JourneyCleanAtmosphericBackground(modifier: Modifier = Modifier) {
  Canvas(modifier = modifier.fillMaxSize()) {
    val w = size.width
    val h = size.height

    // 1. Soft sky gradient with warm dawn ivory & subtle sunrise peach
    drawRect(
      brush = Brush.verticalGradient(
        colors = listOf(
          Color(0xFFFFF9F0), // Warm ivory gold sky
          Color(0xFFFBF4E8), // Soft peach transition
          Color(0xFFF3EFE6), // Soft warm cream
          Color(0xFFEAF1EC), // Pale morning mist jade
          Color(0xFFF6F3ED)  // Base cream
        ),
        startY = 0f,
        endY = h
      )
    )

    // 2. Gentle Sun Glow in upper area
    drawCircle(
      brush = Brush.radialGradient(
        colors = listOf(
          WarmGold.copy(alpha = 0.22f),
          WarmGold.copy(alpha = 0.08f),
          Color.Transparent
        ),
        center = Offset(w * 0.78f, h * 0.16f),
        radius = w * 0.55f
      ),
      center = Offset(w * 0.78f, h * 0.16f),
      radius = w * 0.55f
    )

    // 3. Far distant mountain silhouette (very faint and smooth)
    val distantHill = Path().apply {
      moveTo(0f, h * 0.38f)
      cubicTo(w * 0.30f, h * 0.30f, w * 0.60f, h * 0.42f, w, h * 0.32f)
      lineTo(w, h)
      lineTo(0f, h)
      close()
    }
    drawPath(
      path = distantHill,
      brush = Brush.verticalGradient(
        colors = listOf(
          Color(0x15003426),
          Color(0x05003426)
        ),
        startY = h * 0.28f,
        endY = h * 0.65f
      )
    )

    // 4. Mid-range gentle rolling hill
    val midHill = Path().apply {
      moveTo(0f, h * 0.58f)
      cubicTo(w * 0.25f, h * 0.50f, w * 0.55f, h * 0.62f, w * 0.85f, h * 0.52f)
      cubicTo(w * 0.95f, h * 0.50f, w, h * 0.54f, w, h * 0.54f)
      lineTo(w, h)
      lineTo(0f, h)
      close()
    }
    drawPath(
      path = midHill,
      brush = Brush.verticalGradient(
        colors = listOf(
          Color(0x1C003426),
          Color(0x08003426)
        ),
        startY = h * 0.48f,
        endY = h * 0.85f
      )
    )

    // 5. Lower ground warm rolling contours
    val lowerHill = Path().apply {
      moveTo(0f, h * 0.78f)
      cubicTo(w * 0.40f, h * 0.72f, w * 0.70f, h * 0.82f, w, h * 0.75f)
      lineTo(w, h)
      lineTo(0f, h)
      close()
    }
    drawPath(
      path = lowerHill,
      brush = Brush.verticalGradient(
        colors = listOf(
          Color(0x2082BBA4),
          Color(0x0A003426)
        ),
        startY = h * 0.70f,
        endY = h
      )
    )
  }
}

/**
 * Connecting curve between consecutive journey nodes.
 * Accurately connects the center of node (x1) to node (x2).
 */
@Composable
fun JourneyNodeConnector(
  startOffsetDp: Int,
  endOffsetDp: Int,
  isCompleted: Boolean,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .height(44.dp)
  ) {
    Canvas(modifier = Modifier.fillMaxSize()) {
      val w = size.width
      val h = size.height
      val startX = w * 0.5f + startOffsetDp.dp.toPx()
      val endX = w * 0.5f + endOffsetDp.dp.toPx()

      val path = Path().apply {
        moveTo(startX, 0f)
        cubicTo(
          startX, h * 0.55f,
          endX, h * 0.45f,
          endX, h
        )
      }

      // Base smooth line
      drawPath(
        path = path,
        color = if (isCompleted) WarmGold.copy(alpha = 0.85f) else Color(0x60BFC9C3),
        style = Stroke(
          width = if (isCompleted) 4.dp.toPx() else 3.dp.toPx(),
          cap = StrokeCap.Round,
          pathEffect = if (isCompleted) null else PathEffect.dashPathEffect(floatArrayOf(12f, 10f), 0f)
        )
      )

      // Little decorative waypoint dot at midpoint
      val midX = (startX + endX) * 0.5f
      val midY = h * 0.5f
      drawCircle(
        color = if (isCompleted) WarmGold else Color(0xFFBFC9C3),
        radius = 3.dp.toPx(),
        center = Offset(midX, midY)
      )
    }
  }
}

@Composable
fun WorldSelectorRow(
  worlds: List<WorldData>,
  selectedWorldId: String,
  onSelectWorld: (String) -> Unit,
  onOpenRoadmapTree: () -> Unit,
  modifier: Modifier = Modifier
) {
  val listState = androidx.compose.foundation.lazy.rememberLazyListState()

  androidx.compose.runtime.LaunchedEffect(selectedWorldId) {
    val selectedIndex = worlds.indexOfFirst { it.id == selectedWorldId }
    if (selectedIndex >= 0) {
      listState.animateScrollToItem(selectedIndex)
    }
  }

  Row(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp, vertical = 6.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    // Tree Overview Button
    Box(
      modifier = Modifier
        .size(42.dp)
        .shadow(3.dp, RoundedCornerShape(12.dp))
        .clip(RoundedCornerShape(12.dp))
        .background(Color.White)
        .border(1.5.dp, DarkJade.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
        .clickable(onClick = onOpenRoadmapTree)
        .padding(8.dp)
        .testTag("open_roadmap_tree_button"),
      contentAlignment = Alignment.Center
    ) {
      Icon(
        imageVector = Icons.Filled.AccountTree,
        contentDescription = "Full Roadmap Tree",
        tint = DarkJade,
        modifier = Modifier.size(22.dp)
      )
    }

    Spacer(modifier = Modifier.width(8.dp))

    // Horizontal list of 9 Worlds
    LazyRow(
      state = listState,
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier.weight(1f)
    ) {
      items(worlds, key = { it.id }) { world ->
        val isSelected = world.id == selectedWorldId
        val bgContainer = if (isSelected) DarkJade else Color.White.copy(alpha = 0.95f)
        val contentColor = if (isSelected) Color.White else DarkText
        val borderModifier = if (isSelected) {
          Modifier.border(2.dp, WarmGold, RoundedCornerShape(20.dp))
        } else {
          Modifier.border(1.dp, Color(0x30BFC9C3), RoundedCornerShape(20.dp))
        }

        Box(
          modifier = Modifier
            .shadow(if (isSelected) 4.dp else 1.dp, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .background(bgContainer)
            .then(borderModifier)
            .clickable { onSelectWorld(world.id) }
            .padding(horizontal = 14.dp, vertical = 10.dp)
            .testTag("world_tab_${world.number}")
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
              text = "W${world.number}",
              fontSize = 12.sp,
              fontWeight = FontWeight.ExtraBold,
              color = if (isSelected) WarmGold else DarkJade
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = world.chinese,
              fontSize = 13.sp,
              fontWeight = FontWeight.Bold,
              color = contentColor
            )

            if (world.isCompleted) {
              Spacer(modifier = Modifier.width(4.dp))
              Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = "Completed",
                tint = if (isSelected) WarmGold else PrimaryJade,
                modifier = Modifier.size(14.dp)
              )
            } else if (world.isLocked) {
              Spacer(modifier = Modifier.width(4.dp))
              Icon(
                imageVector = Icons.Filled.Lock,
                contentDescription = "Locked",
                tint = if (isSelected) Color.White.copy(alpha = 0.7f) else Color(0xFF707974),
                modifier = Modifier.size(12.dp)
              )
            }
          }
        }
      }
    }
  }
}

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun WorldRoadmapTreeBottomSheet(
  worlds: List<WorldData>,
  currentWorldId: String,
  onSelectWorld: (String) -> Unit,
  onDismiss: () -> Unit
) {
  val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

  ModalBottomSheet(
    onDismissRequest = onDismiss,
    sheetState = sheetState,
    containerColor = Color(0xFFFBF9F8),
    shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp)
        .padding(bottom = 32.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = "LỘ TRÌNH HỌC TIẾNG TRUNG",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = DarkJade,
            letterSpacing = 1.sp
          )
          Text(
            text = "Hành Trình 9 Thế Giới",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText
          )
        }
        IconButton(onClick = onDismiss) {
          Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = "Đóng",
            tint = DarkText
          )
        }
      }

      Spacer(modifier = Modifier.height(16.dp))
      HorizontalDivider(color = Color(0x30BFC9C3))
      Spacer(modifier = Modifier.height(12.dp))

      // List of 9 Worlds in exact requested structure
      Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        worlds.forEachIndexed { index, world ->
          val isCurrent = world.id == currentWorldId
          val isLast = index == worlds.size - 1
          val branchPrefix = if (isLast) "└──" else "├──"

          Box(
            modifier = Modifier
              .fillMaxWidth()
              .shadow(if (isCurrent) 4.dp else 1.dp, RoundedCornerShape(16.dp))
              .clip(RoundedCornerShape(16.dp))
              .background(if (isCurrent) Color.White else Color(0xFFF4F1EC))
              .border(
                width = if (isCurrent) 2.dp else 1.dp,
                color = if (isCurrent) DarkJade else Color(0x25003426),
                shape = RoundedCornerShape(16.dp)
              )
              .clickable {
                onSelectWorld(world.id)
                onDismiss()
              }
              .padding(14.dp)
              .testTag("roadmap_world_${world.number}")
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
                // Tree Branch Symbol
                Text(
                  text = branchPrefix,
                  fontSize = 14.sp,
                  fontWeight = FontWeight.Bold,
                  color = if (isCurrent) DarkJade else Color(0xFF707974)
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Emoji icon badge
                Box(
                  modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .background(if (isCurrent) DarkJade else Color(0xFFE5E0D6)),
                  contentAlignment = Alignment.Center
                ) {
                  Text(text = world.iconEmoji, fontSize = 18.sp)
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                  Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                      text = "Thế Giới ${world.number} — ${world.chinese} ${world.english}",
                      fontSize = 15.sp,
                      fontWeight = FontWeight.Bold,
                      color = if (isCurrent) DarkJade else DarkText
                    )
                  }
                  Spacer(modifier = Modifier.height(2.dp))
                  Text(
                    text = "${world.nodes.size} chặng học • ${world.description}",
                    fontSize = 12.sp,
                    color = SecondaryText,
                    maxLines = 1
                  )
                }
              }

              // Status indicator
              if (world.isCompleted) {
                Box(
                  modifier = Modifier
                    .clip(CircleShape)
                    .background(WarmGold)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                  Text("Xong", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF694300))
                }
              } else if (isCurrent) {
                Box(
                  modifier = Modifier
                    .clip(CircleShape)
                    .background(DarkJade)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                  Text("Đang học", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }
              } else if (world.isLocked) {
                Icon(
                  imageVector = Icons.Filled.Lock,
                  contentDescription = "Locked",
                  tint = Color(0xFF707974),
                  modifier = Modifier.size(16.dp)
                )
              } else {
                Icon(
                  imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                  contentDescription = null,
                  tint = DarkJade,
                  modifier = Modifier.size(20.dp)
                )
              }
            }
          }
        }
      }
    }
  }
}

@Composable
fun JourneyPathNodeItem(
  node: JourneyNode,
  horizontalOffsetDp: Int,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  val infiniteTransition = rememberInfiniteTransition(label = "bounce")
  val bounceOffset by infiniteTransition.animateFloat(
    initialValue = 0f,
    targetValue = -10f,
    animationSpec = infiniteRepeatable(
      animation = tween(600, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "bounceAnim"
  )

  Column(
    modifier = modifier
      .fillMaxWidth()
      .offset(x = horizontalOffsetDp.dp)
      .padding(vertical = 6.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Box(
      contentAlignment = Alignment.Center,
      modifier = Modifier.clickable(onClick = onClick)
    ) {
      // 1. Current Bouncing Location Pin
      if (node.isCurrent) {
        Icon(
          imageVector = Icons.Filled.LocationOn,
          contentDescription = "Current Location",
          tint = DarkJade,
          modifier = Modifier
            .align(Alignment.TopCenter)
            .offset(y = (-42).dp + bounceOffset.dp)
            .size(38.dp)
        )
      }

      // 2. Node Circle Container
      val circleSize = if (node.isCurrent) 96.dp else if (node.isCompleted) 88.dp else 80.dp
      val borderColor = if (node.isCurrent) WarmGold else if (node.isCompleted) DarkJade else Color(0xFFBFC9C3)
      val borderWidth = if (node.isCurrent) 4.dp else if (node.isCompleted) 4.dp else 3.dp
      val shadowElevation = if (node.isCurrent) 10.dp else if (node.isCompleted) 5.dp else 2.dp

      Box(
        modifier = Modifier
          .size(circleSize)
          .shadow(
            elevation = shadowElevation,
            shape = CircleShape,
            spotColor = if (node.isCurrent) WarmGold.copy(alpha = 0.45f) else Color(0x25003426)
          )
          .clip(CircleShape)
          .background(if (node.isLocked) Color(0xFFECEAE8) else Color.White)
          .border(borderWidth, borderColor, CircleShape)
          .padding(if (node.isLocked) 8.dp else 4.dp),
        contentAlignment = Alignment.Center
      ) {
        when (node.iconType) {
          NodeIconType.HOME_BASE -> {
            TraditionalHouseIllustration(size = 54.dp)
            AsyncImage(
              model = JOURNEY_NODE_1_HOME_URL,
              contentDescription = node.name,
              modifier = Modifier.fillMaxSize(),
              contentScale = ContentScale.Crop
            )
          }
          NodeIconType.HELLO_GATE -> {
            PagodaGateMiniIcon(modifier = Modifier.size(50.dp))
            AsyncImage(
              model = JOURNEY_NODE_2_GATE_URL,
              contentDescription = node.name,
              modifier = Modifier.fillMaxSize(),
              contentScale = ContentScale.Fit
            )
          }
          NodeIconType.TONE_GAME -> {
            Icon(
              imageVector = Icons.Filled.MusicNote,
              contentDescription = node.name,
              tint = if (node.isLocked) Color(0xFF707974) else WarmGold,
              modifier = Modifier.size(36.dp)
            )
          }
          NodeIconType.PANDA_FRIEND -> {
            PandaAvatar(size = 44.dp)
            AsyncImage(
              model = JOURNEY_NODE_4_PANDA_URL,
              contentDescription = node.name,
              modifier = Modifier.size(48.dp),
              contentScale = ContentScale.Fit,
              alpha = if (node.isLocked) 0.65f else 1f
            )
          }
          NodeIconType.BOSS_CHALLENGE, NodeIconType.FIRST_CONVERSATION_BOSS -> {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Text(
                text = "👑",
                fontSize = 20.sp
              )
              Text(
                text = node.chinese.take(2),
                fontSize = 14.sp,
                fontWeight = FontWeight.ExtraBold,
                color = if (node.isLocked) Color(0xFF707974) else DarkJade
              )
            }
          }
          else -> {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Text(
                text = node.chinese.ifEmpty { "★" },
                fontSize = if (node.chinese.length > 3) 16.sp else 22.sp,
                fontWeight = FontWeight.Bold,
                color = if (node.isLocked) Color(0xFF707974) else DarkJade,
                textAlign = TextAlign.Center
              )
              if (node.pinyin.isNotEmpty()) {
                Text(
                  text = node.pinyin.split(" ").firstOrNull() ?: "",
                  fontSize = 10.sp,
                  color = SecondaryText
                )
              }
            }
          }
        }
      }

      // 3. Completed Checkmark Badge
      if (node.isCompleted) {
        Box(
          modifier = Modifier
            .align(Alignment.BottomEnd)
            .offset(x = 4.dp, y = 4.dp)
            .size(28.dp)
            .shadow(2.dp, CircleShape)
            .clip(CircleShape)
            .background(WarmGold)
            .border(2.dp, Color.White, CircleShape),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = "Completed",
            tint = Color(0xFF694300),
            modifier = Modifier.size(16.dp)
          )
        }
      }
    }

    // 4. Floating Info Label Card for Current Node / Detail Info
    if (node.isCurrent) {
      Spacer(modifier = Modifier.height(10.dp))
      Box(
        modifier = Modifier
          .shadow(4.dp, RoundedCornerShape(16.dp), spotColor = Color(0x18000000))
          .clip(RoundedCornerShape(16.dp))
          .background(Color.White.copy(alpha = 0.96f))
          .border(1.dp, Color(0x30BFC9C3), RoundedCornerShape(16.dp))
          .padding(horizontal = 18.dp, vertical = 8.dp)
      ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          Text(
            text = node.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = DarkJade
          )
          Spacer(modifier = Modifier.height(2.dp))
          Text(
            text = node.englishSummary,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = SecondaryText
          )
        }
      }
    }
  }
}

@Composable
fun NextWorldPortalNode(
  nextWorld: WorldData?,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  if (nextWorld == null) return

  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(top = 16.dp, bottom = 40.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Box(
      modifier = Modifier
        .shadow(4.dp, CircleShape)
        .clip(CircleShape)
        .background(Color.White)
        .border(2.dp, if (nextWorld.isLocked) Color(0xFFBFC9C3) else DarkJade, CircleShape)
        .clickable(onClick = onClick)
        .padding(18.dp),
      contentAlignment = Alignment.Center
    ) {
      Text(
        text = nextWorld.iconEmoji,
        fontSize = 32.sp
      )
    }

    Spacer(modifier = Modifier.height(10.dp))

    Text(
      text = "WORLD ${nextWorld.number}",
      fontSize = 11.sp,
      fontWeight = FontWeight.Bold,
      color = DarkJade,
      letterSpacing = 1.sp
    )
    Text(
      text = "${nextWorld.chinese} — ${nextWorld.english}",
      fontSize = 14.sp,
      fontWeight = FontWeight.SemiBold,
      color = if (nextWorld.isLocked) Color(0xFF707974) else DarkText,
      textAlign = TextAlign.Center
    )
  }
}

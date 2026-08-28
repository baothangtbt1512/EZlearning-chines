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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Adjust
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.model.JourneyNode
import com.example.model.LearnerState
import com.example.model.NodeIconType
import com.example.ui.theme.DarkJade
import com.example.ui.theme.DarkText
import com.example.ui.theme.GoalCoral
import com.example.ui.theme.JadeContainer
import com.example.ui.theme.LockedGray
import com.example.ui.theme.PrimaryJade
import com.example.ui.theme.WarmGold
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Pin
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material.icons.filled.School
import com.example.ui.theme.RoofTerracotta
import com.example.ui.theme.SecondaryText
import com.example.ui.theme.SoftBorder
import com.example.ui.theme.WarmCream
import com.example.ui.theme.WarmGold

private const val WORLD_1_BG_URL = "https://lh3.googleusercontent.com/aida-public/AB6AXuAE5cTTzLP_oZsN9smlUHngpir_n5ilGxJv8ddmLiMIDa4illi0uUQ2lwmFUaeSW7sPuh2IuIxk-U6zoHT-kfxLRPqr4ilYorwftCzju5tGn66TKc8ax2_ipB5d5RYpnDAu63m0jxcgKekdyVKARXzIZoqUK8JcsFW3FXRAKMuE0u-M0_U7YyxVxUDd-y0zUp_4M1hPJPFDwOetIaix8xyLo6aVdTOWSlMBeHGIi-Kq9TuU110t4d7S7A"
private const val NODE_1_URL = "https://lh3.googleusercontent.com/aida-public/AB6AXuDR3kB4be_M6TfifvQzyEUBBPVTVCu0BC9EJQhD7oM4cVaen5PhrJ8vuIj0KKBbiBdN25g0FnI2_wcRG-NC9RfZabiaoL6BfWSBYRM_Uac2rvqQzA0Zggfpksvsu0RQKPGpEPJNdyBxjA0ocYgai5Skb4z2hZpQvVxvgDFN1hrlPM5ikaTG9a8MckOCuwUlz4Y_qhYbN-oSSR4wKNDP5sBKe0JK99_f4NHn_qOYfgEZ9aq8mNRLC-zJcQ"
private const val NODE_2_URL = "https://lh3.googleusercontent.com/aida-public/AB6AXuDen5dWZYyopD9IyBaTZgbnDz9a8QoDZYBpnx8S7PlPrmCVBM-az28hpap22rf83Jy8QXaiCyPd7H-T9xVXg_95gaxESgpP24vzUx46VjsQpLTl7XJ1EU8HLfP3jwDdpFPxWJVFeYASGY3jqRbVcxmipf26xoBukECwqqkG2hYn_gIfiIzfglZXT5QL97YTlTI-SF4zb5qrvfNPBf9RnhdnDsaia6da1XKVSqSKjZ97bGcIZL8Wq4smGg"
private const val NODE_3_URL = "https://lh3.googleusercontent.com/aida-public/AB6AXuCLqPefcnwwrPtkbuVlnYpwtWSb2r4w8TBER5yKwwcPrxnLb9VvTcAxeMD-bAypKmAI8QoL5gmPDCvJw-l2upr35kVFRFu2dw8oj6eQGHbG2cYF1BGvX9eXadI8E5KsOZpaRKHXlT2D0Lq9tFfZo8a5O1jSWXcsD4HWv4xD44dp3-LSSiw2ioR-p7991Ors5Z7HvrhNzbzBydmLuzKRWvp4pCqX799UI7EvedFa09W1w0LRew5LCyIi0Q"
private const val CHEST_URL = "https://lh3.googleusercontent.com/aida-public/AB6AXuCl5fGZJRDHhuFOTZ3Yz1x91oViSwbciwZysfiohGJFFWLcQt_lc-3b3CauR7Zwxz63Sx4ywhKmwQvCcTIYGe0NwKDgcdFn1QhH0wFOVkc0piRp-_IdfUuCKgdkG4LN-qk1oI9ptxaEk7ACV6Y6rZ9h-pbUXFhi88bO7ouiyyjFLCvEHyeCL4gXqipaoXJROwRxCmcC-t26VI42GQ6UCXBGqeO9XIpPXkttgASaael1PGVw7S1mbbh_rA"

@Composable
fun WorldCard(
  state: LearnerState,
  modifier: Modifier = Modifier,
  onContinueClick: () -> Unit
) {
  val activeWorld = state.worlds.find { it.id == state.currentWorldId } ?: state.worlds.first()
  val currentNode = activeWorld.nodes.find { it.isCurrent }
    ?: activeWorld.nodes.firstOrNull { !it.isCompleted && !it.isLocked }
    ?: activeWorld.nodes.first()

  val totalNodes = activeWorld.nodes.size.coerceAtLeast(1)
  val completedNodes = activeWorld.nodes.count { it.isCompleted }
  val progressPct = (completedNodes * 100) / totalNodes
  val progressFloat = (completedNodes.toFloat() / totalNodes.toFloat()).coerceIn(0f, 1f)

  Box(
    modifier = modifier
      .fillMaxWidth()
      .heightIn(min = 360.dp)
      .shadow(
        elevation = 8.dp,
        shape = RoundedCornerShape(24.dp),
        ambientColor = Color(0x18000000),
        spotColor = Color(0x18000000)
      )
      .clip(RoundedCornerShape(24.dp))
      .background(Color.White)
      .testTag("world_1_card")
  ) {
    // 1. Background image / illustration
    Box(modifier = Modifier.fillMaxSize()) {
      TraditionalHouseIllustration(
        modifier = Modifier.fillMaxSize(),
        size = 200.dp
      )
      AsyncImage(
        model = WORLD_1_BG_URL,
        contentDescription = activeWorld.english,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
      )

      // Gradient overlay from bottom for pristine readability
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(280.dp)
          .align(Alignment.BottomCenter)
          .background(
            Brush.verticalGradient(
              colors = listOf(
                Color.Transparent,
                Color.White.copy(alpha = 0.70f),
                Color.White.copy(alpha = 0.95f),
                Color.White
              )
            )
          )
      )
    }

    // 2. Foreground content
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .align(Alignment.BottomStart)
        .padding(20.dp)
    ) {
      // World Badge
      Box(
        modifier = Modifier
          .clip(CircleShape)
          .background(PrimaryJade)
          .padding(horizontal = 12.dp, vertical = 4.dp)
      ) {
        Text(
          text = "THẾ GIỚI ${activeWorld.number}",
          fontSize = 10.sp,
          fontWeight = FontWeight.Bold,
          color = Color.White,
          letterSpacing = 1.sp
        )
      }

      Spacer(modifier = Modifier.height(8.dp))

      // Chinese & Vietnamese Title
      Text(
        text = activeWorld.chinese,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        color = DarkJade,
        lineHeight = 36.sp
      )
      Text(
        text = activeWorld.english,
        fontSize = 22.sp,
        fontWeight = FontWeight.SemiBold,
        color = DarkJade
      )

      Spacer(modifier = Modifier.height(10.dp))

      // Current Location
      Text(
        text = "VỊ TRÍ HIỆN TẠI",
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        color = SecondaryText,
        letterSpacing = 0.5.sp
      )
      Spacer(modifier = Modifier.height(2.dp))
      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
          imageVector = Icons.Filled.LocationOn,
          contentDescription = null,
          tint = PrimaryJade,
          modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = "${currentNode.name} (${currentNode.chinese})",
          fontSize = 15.sp,
          fontWeight = FontWeight.Bold,
          color = DarkText
        )
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Progress
      Text(
        text = "TIẾN ĐỘ",
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        color = SecondaryText,
        letterSpacing = 0.5.sp
      )
      Spacer(modifier = Modifier.height(4.dp))
      Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
      ) {
        Text(
          text = "${progressPct}%",
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold,
          color = DarkJade
        )
        Spacer(modifier = Modifier.width(10.dp))
        LinearProgressIndicator(
          progress = { progressFloat.coerceAtLeast(0.04f) },
          modifier = Modifier
            .weight(1f)
            .height(10.dp)
            .clip(CircleShape),
          color = PrimaryJade,
          trackColor = Color(0xFFE4E2E2)
        )
      }

      Spacer(modifier = Modifier.height(16.dp))

      // CONTINUE ADVENTURE Button
      Button(
        onClick = onContinueClick,
        modifier = Modifier
          .fillMaxWidth()
          .height(54.dp)
          .testTag("continue_adventure_button"),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
          containerColor = PrimaryJade,
          contentColor = Color.White
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp)
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center
        ) {
          Text(
            text = "TIẾP TỤC: ${currentNode.name.uppercase()}",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.5.sp
          )
          Spacer(modifier = Modifier.width(8.dp))
          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = null,
            modifier = Modifier.size(18.dp)
          )
        }
      }
    }
  }
}

@Composable
fun YourJourneyPreviewCard(
  state: LearnerState,
  modifier: Modifier = Modifier,
  onViewJourneyClick: () -> Unit,
  onNodeClick: ((JourneyNode) -> Unit)? = null
) {
  val activeWorld = state.worlds.find { it.id == state.currentWorldId } ?: state.worlds.first()
  val nodes = activeWorld.nodes

  // We show a 4-node window around the current active node
  val currentIdx = nodes.indexOfFirst { it.isCurrent }.coerceAtLeast(0)
  val startIdx = (currentIdx - 1).coerceAtLeast(0).coerceAtMost(maxOf(0, nodes.size - 4))
  val previewNodes = nodes.drop(startIdx).take(4)

  Box(
    modifier = modifier
      .fillMaxWidth()
      .shadow(
        elevation = 6.dp,
        shape = RoundedCornerShape(20.dp),
        ambientColor = Color(0x15000000),
        spotColor = Color(0x15000000)
      )
      .clip(RoundedCornerShape(20.dp))
      .background(Color.White)
      .padding(18.dp)
      .testTag("your_journey_card")
  ) {
    Column {
      // Header row
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Filled.Map,
            contentDescription = null,
            tint = PrimaryJade,
            modifier = Modifier.size(22.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "Hành Trình Của Bạn",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = DarkJade
          )
        }

        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onViewJourneyClick)
            .padding(horizontal = 4.dp, vertical = 4.dp)
            .testTag("view_journey_link")
        ) {
          Text(
            text = "Xem bản đồ",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryJade
          )
          Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = PrimaryJade,
            modifier = Modifier.size(16.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(20.dp))

      // Nodes connected by dashed line
      Box(modifier = Modifier.fillMaxWidth()) {
        // Dashed connection line
        Canvas(
          modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .align(Alignment.Center)
        ) {
          val yMid = size.height * 0.38f
          drawLine(
            color = Color(0x66BFC9C3),
            start = Offset(size.width * 0.12f, yMid),
            end = Offset(size.width * 0.88f, yMid),
            strokeWidth = 2.dp.toPx(),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 8f), 0f)
          )
        }

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.Top
        ) {
          previewNodes.forEach { node ->
            val isCurrent = node.isCurrent
            val isCompleted = node.isCompleted
            val isLocked = node.isLocked

            Column(
              horizontalAlignment = Alignment.CenterHorizontally,
              modifier = Modifier
                .width(72.dp)
                .clickable { onNodeClick?.invoke(node) ?: onViewJourneyClick() }
            ) {
              Box(contentAlignment = Alignment.Center) {
                Box(
                  modifier = Modifier
                    .size(if (isCurrent) 56.dp else 50.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .border(
                      width = if (isCurrent) 2.5.dp else if (isCompleted) 1.5.dp else 1.dp,
                      color = if (isCurrent) PrimaryJade else if (isCompleted) WarmGold else Color(0x40BFC9C3),
                      shape = CircleShape
                    )
                    .padding(3.dp),
                  contentAlignment = Alignment.Center
                ) {
                  Box(
                    modifier = Modifier
                      .fillMaxSize()
                      .clip(CircleShape)
                      .background(if (isCompleted) Color(0xFFE8F5E9) else Color(0xFFEFEDED)),
                    contentAlignment = Alignment.Center
                  ) {
                    when (node.iconType) {
                      NodeIconType.HOME_BASE -> {
                        TraditionalHouseIllustration(size = 30.dp)
                      }
                      NodeIconType.HELLO_GATE -> {
                        PagodaGateMiniIcon(modifier = Modifier.size(26.dp))
                      }
                      NodeIconType.TONE_GAME -> {
                        Icon(Icons.Filled.GraphicEq, contentDescription = null, tint = if (isLocked) LockedGray else PrimaryJade, modifier = Modifier.size(24.dp))
                      }
                      NodeIconType.PANDA_FRIEND -> {
                        PandaAvatar(size = 30.dp)
                      }
                      NodeIconType.MY_NAME -> {
                        Icon(Icons.Filled.Badge, contentDescription = null, tint = if (isLocked) LockedGray else PrimaryJade, modifier = Modifier.size(24.dp))
                      }
                      NodeIconType.MY_COUNTRY -> {
                        Icon(Icons.Filled.Public, contentDescription = null, tint = if (isLocked) LockedGray else PrimaryJade, modifier = Modifier.size(24.dp))
                      }
                      NodeIconType.NUMBERS -> {
                        Icon(Icons.Filled.Pin, contentDescription = null, tint = if (isLocked) LockedGray else PrimaryJade, modifier = Modifier.size(24.dp))
                      }
                      NodeIconType.FIRST_CONVERSATION -> {
                        Icon(Icons.Filled.QuestionAnswer, contentDescription = null, tint = if (isLocked) LockedGray else PrimaryJade, modifier = Modifier.size(24.dp))
                      }
                      NodeIconType.FIRST_CONVERSATION_BOSS, NodeIconType.BOSS_CHALLENGE -> {
                        Icon(Icons.Filled.EmojiEvents, contentDescription = null, tint = if (isLocked) LockedGray else GoalCoral, modifier = Modifier.size(24.dp))
                      }
                      else -> {
                        Icon(if (isLocked) Icons.Filled.Lock else Icons.Filled.School, contentDescription = null, tint = if (isLocked) LockedGray else PrimaryJade, modifier = Modifier.size(24.dp))
                      }
                    }
                  }
                }

                if (isCurrent) {
                  // Green map pin at top-right
                  Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = null,
                    tint = PrimaryJade,
                    modifier = Modifier
                      .align(Alignment.TopEnd)
                      .offset(x = 4.dp, y = (-4).dp)
                      .size(18.dp)
                  )
                } else if (isCompleted) {
                  // Gold checkmark at top-right
                  Box(
                    modifier = Modifier
                      .align(Alignment.TopEnd)
                      .offset(x = 2.dp, y = (-2).dp)
                      .size(16.dp)
                      .clip(CircleShape)
                      .background(WarmGold),
                    contentAlignment = Alignment.Center
                  ) {
                    Icon(
                      imageVector = Icons.Filled.Check,
                      contentDescription = null,
                      tint = Color.White,
                      modifier = Modifier.size(11.dp)
                    )
                  }
                }
              }

              Spacer(modifier = Modifier.height(6.dp))
              Text(
                text = node.name,
                fontSize = 11.sp,
                fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Medium,
                color = if (isCurrent) DarkText else if (isCompleted) DarkText.copy(alpha = 0.85f) else DarkText.copy(alpha = 0.5f),
                maxLines = 1
              )

              if (isCurrent) {
                Box(
                  modifier = Modifier
                    .padding(top = 2.dp)
                    .clip(CircleShape)
                    .background(PrimaryJade)
                    .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                  Text(
                    text = "HIỆN TẠI",
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                  )
                }
              } else if (isCompleted) {
                Box(
                  modifier = Modifier
                    .padding(top = 2.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE8F5E9))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                  Text(
                    text = "ĐÃ XONG",
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryJade
                  )
                }
              }
            }
          }
        }
      }
    }
  }
}

@Composable
fun DailyGoalCard(
  modifier: Modifier = Modifier,
  currentXp: Int = 0,
  targetXp: Int = 10
) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .shadow(
        elevation = 6.dp,
        shape = RoundedCornerShape(20.dp),
        ambientColor = Color(0x15000000),
        spotColor = Color(0x15000000)
      )
      .clip(RoundedCornerShape(20.dp))
      .background(Color.White)
      .padding(18.dp)
      .testTag("daily_goal_card")
  ) {
    Column {
      // Header: Target Bullseye Icon + "Mục Tiêu Ngày" + XP ratio
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Filled.Adjust,
            contentDescription = "Mục Tiêu Ngày",
            tint = GoalCoral,
            modifier = Modifier.size(22.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "Mục Tiêu Ngày",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = DarkJade
          )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(
            text = currentXp.toString(),
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = GoalCoral
          )
          Text(
            text = " / $targetXp XP",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText
          )
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Progress bar & Treasure Chest illustration
      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column(
          modifier = Modifier.weight(1f)
        ) {
          val progressVal = if (targetXp > 0) (currentXp.toFloat() / targetXp.toFloat()).coerceIn(0f, 1f) else 0f
          LinearProgressIndicator(
            progress = { if (progressVal == 0f) 0.05f else progressVal },
            modifier = Modifier
              .fillMaxWidth()
              .height(12.dp)
              .clip(CircleShape),
            color = GoalCoral,
            trackColor = Color(0xFFE4E2E2)
          )

          Spacer(modifier = Modifier.height(8.dp))

          Text(
            text = "Cố lên nhé! Từng bước nhỏ mỗi ngày sẽ tạo nên tiến bộ vượt bậc.",
            fontSize = 12.sp,
            color = SecondaryText,
            lineHeight = 16.sp
          )
        }

        Spacer(modifier = Modifier.width(14.dp))

        // 3D Treasure Chest
        Box(
          modifier = Modifier.size(60.dp),
          contentAlignment = Alignment.Center
        ) {
          TreasureChestVector(modifier = Modifier.size(52.dp))
          AsyncImage(
            model = CHEST_URL,
            contentDescription = "Golden Treasure Chest",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
          )
        }
      }
    }
  }
}

@Composable
fun TreasureChestVector(modifier: Modifier = Modifier) {
  Canvas(modifier = modifier) {
    val w = size.width
    val h = size.height

    // Chest Body (Rich Red/Wood & Gold Trim)
    drawRoundRect(
      color = Color(0xFFB53424),
      topLeft = Offset(w * 0.15f, h * 0.38f),
      size = Size(w * 0.70f, h * 0.50f),
      cornerRadius = CornerRadius(w * 0.06f, w * 0.06f)
    )

    // Chest Lid
    val lidPath = Path().apply {
      moveTo(w * 0.10f, h * 0.40f)
      cubicTo(w * 0.12f, h * 0.20f, w * 0.88f, h * 0.20f, w * 0.90f, h * 0.40f)
      close()
    }
    drawPath(lidPath, color = Color(0xFFD64331))

    // Gold Straps
    drawRect(
      color = WarmGold,
      topLeft = Offset(w * 0.28f, h * 0.24f),
      size = Size(w * 0.08f, h * 0.64f)
    )
    drawRect(
      color = WarmGold,
      topLeft = Offset(w * 0.64f, h * 0.24f),
      size = Size(w * 0.08f, h * 0.64f)
    )

    // Gold Lock in center
    drawRoundRect(
      color = WarmGold,
      topLeft = Offset(w * 0.44f, h * 0.42f),
      size = Size(w * 0.12f, h * 0.15f),
      cornerRadius = CornerRadius(w * 0.02f, w * 0.02f)
    )
    drawCircle(
      color = Color(0xFF4A2B10),
      radius = w * 0.02f,
      center = Offset(w * 0.50f, h * 0.49f)
    )

    // Sparkles ✨
    drawCircle(color = WarmGold, radius = w * 0.04f, center = Offset(w * 0.92f, h * 0.22f))
  }
}

@Composable
fun PagodaGateMiniIcon(modifier: Modifier = Modifier) {
  Canvas(modifier = modifier) {
    val w = size.width
    val h = size.height

    // Pillars
    drawRect(color = Color(0xFF8B4513), topLeft = Offset(w * 0.22f, h * 0.35f), size = Size(w * 0.10f, h * 0.55f))
    drawRect(color = Color(0xFF8B4513), topLeft = Offset(w * 0.68f, h * 0.35f), size = Size(w * 0.10f, h * 0.55f))

    // Curved Pagoda Gate Roof
    val gateRoof = Path().apply {
      moveTo(w * 0.08f, h * 0.42f)
      cubicTo(w * 0.25f, h * 0.30f, w * 0.50f, h * 0.18f, w * 0.50f, h * 0.18f)
      cubicTo(w * 0.50f, h * 0.18f, w * 0.75f, h * 0.30f, w * 0.92f, h * 0.42f)
      lineTo(w * 0.82f, h * 0.44f)
      lineTo(w * 0.18f, h * 0.44f)
      close()
    }
    drawPath(gateRoof, color = Color(0xFF2C4A5A))

    // Crossbar
    drawRect(color = RoofTerracotta, topLeft = Offset(w * 0.22f, h * 0.48f), size = Size(w * 0.56f, h * 0.08f))
  }
}

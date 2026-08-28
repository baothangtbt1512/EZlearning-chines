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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
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
import com.example.model.JourneyNode
import com.example.model.LearnerState
import com.example.ui.components.GreatWallHeroCanvas
import com.example.ui.components.JourneyNodeConnector
import com.example.ui.components.JourneyPathNodeItem
import com.example.ui.components.NextWorldPortalNode
import com.example.ui.components.PandaAvatar
import com.example.ui.components.WorldRoadmapTreeBottomSheet
import com.example.ui.components.WorldSelectorRow
import com.example.ui.theme.DarkJade
import com.example.ui.theme.DarkText
import com.example.ui.theme.SecondaryText
import com.example.ui.theme.WarmCream

private const val JOURNEY_BG_LANDSCAPE_URL = "https://lh3.googleusercontent.com/aida-public/AB6AXuBFIXKeTmfcu2rkRWyQRB07xiISL6VMnACSZPEMqw4W0tCS31ZERWWXvM6-LWT2sughvVSot_N9m4UX0NER1A3rm5qFRwjLG7Ocr_tuoksljUymSenAocvRBVLq8jG0AGVsa_KLTHqOmz-2BEpXCh2tL2rQXMYlQDjcVdXx8MncA-bJv66p8sTJC9M_GF42HYn631PsPwIVQgBhLP5FGmyOpaegkPxEvY3EDqRxB2GitIt6HHyYFats_g"
private const val PANDA_HEADER_URL = "https://lh3.googleusercontent.com/aida-public/AB6AXuCtd2rtj9ccbcyxEkhQDyYzo2ca7Cg0KFs4Sf0F8xvytfYXWVFqnkM79poFkB3poe6P-MoEf09DDxQNGuh9VQYjgILzp32ancbojX2cPmVAp1bpegG3l0VuHuZYhB-wnyO5i_b_0Rt-goKI35uF5JAYG3_TP72eMo9Nzn35j3Vgw9zTxtvnPYT7VGLf10nm3dQ3ZoIg3Q-2PfUms_3nGNae9pioJ2URlI0FMrXb1Fs4V2_AV6JlYAxJvQ"

@Composable
fun JourneyScreen(
  state: LearnerState,
  onBackClick: () -> Unit,
  onNodeClick: (JourneyNode) -> Unit,
  onSelectWorld: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  val scrollState = rememberScrollState()
  var showRoadmapTreeModal by remember { mutableStateOf(false) }

  val activeWorld = state.worlds.find { it.id == state.currentWorldId } ?: state.worlds.first()
  val nextWorld = state.worlds.getOrNull(activeWorld.number) // 0-indexed next

  // Reset scroll to top when user switches world
  androidx.compose.runtime.LaunchedEffect(state.currentWorldId) {
    scrollState.scrollTo(0)
  }

  Box(
    modifier = modifier
      .fillMaxSize()
      .background(WarmCream)
      .testTag("journey_screen")
  ) {
    // 1. Great Wall Landscape Background Image
    Box(modifier = Modifier.fillMaxSize()) {
      AsyncImage(
        model = JOURNEY_BG_LANDSCAPE_URL,
        contentDescription = "Great Wall Journey Landscape",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
      )
      Box(
        modifier = Modifier
          .fillMaxSize()
          .background(
            Brush.verticalGradient(
              colors = listOf(
                WarmCream.copy(alpha = 0.35f),
                Color.Transparent,
                WarmCream.copy(alpha = 0.60f),
                WarmCream.copy(alpha = 0.85f)
              )
            )
          )
      )
    }

    // 2. Scrollable Main Content (Underneath the fixed top header)
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
        .padding(top = 135.dp, bottom = 100.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // Journey Title Section
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(
          text = "World ${activeWorld.number} — ${activeWorld.chinese}",
          fontSize = 28.sp,
          fontWeight = FontWeight.Bold,
          color = DarkJade
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
          text = activeWorld.english,
          fontSize = 18.sp,
          fontWeight = FontWeight.SemiBold,
          color = DarkText
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
          text = activeWorld.description,
          fontSize = 13.sp,
          fontWeight = FontWeight.Normal,
          color = SecondaryText
        )
      }

      Spacer(modifier = Modifier.height(24.dp))

      // Nodes along the path with smooth connectors
      val nodes = activeWorld.nodes
      fun getNodeOffset(index: Int): Int = when (index % 4) {
        0 -> 0
        1 -> 32
        2 -> -28
        else -> 20
      }

      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        nodes.forEachIndexed { index, node ->
          val currentOffset = getNodeOffset(index)

          // The Node item
          JourneyPathNodeItem(
            node = node,
            horizontalOffsetDp = currentOffset,
            onClick = { onNodeClick(node) }
          )

          // Smooth curved connector to next destination
          if (index < nodes.size - 1) {
            val nextOffset = getNodeOffset(index + 1)
            JourneyNodeConnector(
              startOffsetDp = currentOffset,
              endOffsetDp = nextOffset,
              isCompleted = node.isCompleted
            )
          } else if (nextWorld != null) {
            // Connector from last node to Next World Portal (center 0)
            JourneyNodeConnector(
              startOffsetDp = currentOffset,
              endOffsetDp = 0,
              isCompleted = node.isCompleted
            )
          }
        }

        // Next World Portal / Anchor
        NextWorldPortalNode(
          nextWorld = nextWorld,
          onClick = {
            if (nextWorld != null) {
              onSelectWorld(nextWorld.id)
            }
          }
        )
      }
    }

    // 3. Fixed Top Bar & 9-World Quick Navigation (Rendered on top for guaranteed touch interaction)
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .align(Alignment.TopCenter)
        .background(
          Brush.verticalGradient(
            colors = listOf(
              WarmCream,
              WarmCream.copy(alpha = 0.96f),
              WarmCream.copy(alpha = 0.85f),
              Color.Transparent
            )
          )
        )
        .statusBarsPadding()
        .padding(bottom = 6.dp)
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 20.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        // Left: Panda avatar + "Learning Chinese"
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.clickable(onClick = onBackClick)
        ) {
          Box(
            modifier = Modifier
              .size(42.dp)
              .shadow(3.dp, CircleShape)
              .clip(CircleShape)
              .background(Color.White)
              .border(2.dp, Color.White, CircleShape),
            contentAlignment = Alignment.Center
          ) {
            PandaAvatar(size = 36.dp)
            AsyncImage(
              model = PANDA_HEADER_URL,
              contentDescription = "Panda Companion",
              modifier = Modifier.fillMaxSize(),
              contentScale = ContentScale.Crop
            )
          }

          Spacer(modifier = Modifier.width(10.dp))

          Text(
            text = "Learning Chinese",
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold,
            color = DarkJade
          )
        }

        // Right: Streak pill
        Row(
          modifier = Modifier
            .shadow(3.dp, CircleShape)
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.92f))
            .border(1.dp, Color(0x30BFC9C3), CircleShape)
            .padding(horizontal = 14.dp, vertical = 6.dp)
            .testTag("journey_streak_badge"),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(text = "🔥", fontSize = 16.sp)
          Spacer(modifier = Modifier.width(4.dp))
          Text(
            text = state.streakDays.toString(),
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = DarkText
          )
        }
      }

      // Quick 9-World Selector Bar
      WorldSelectorRow(
        worlds = state.worlds,
        selectedWorldId = state.currentWorldId,
        onSelectWorld = onSelectWorld,
        onOpenRoadmapTree = { showRoadmapTreeModal = true }
      )
    }

    // 4. Bottom Sheet showing the 9-World Roadmap Tree Structure
    if (showRoadmapTreeModal) {
      WorldRoadmapTreeBottomSheet(
        worlds = state.worlds,
        currentWorldId = state.currentWorldId,
        onSelectWorld = { worldId ->
          onSelectWorld(worldId)
        },
        onDismiss = { showRoadmapTreeModal = false }
      )
    }
  }
}

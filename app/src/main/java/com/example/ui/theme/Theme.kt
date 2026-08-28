package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LearningChineseColorScheme = lightColorScheme(
  primary = PrimaryJade,
  onPrimary = Color.White,
  primaryContainer = JadeContainer,
  onPrimaryContainer = DarkJade,
  secondary = WarmGold,
  onSecondary = Color.Black,
  secondaryContainer = GoldContainer,
  onSecondaryContainer = DarkText,
  tertiary = CoralStreak,
  onTertiary = Color.White,
  tertiaryContainer = CoralContainer,
  onTertiaryContainer = DarkText,
  background = WarmCream,
  onBackground = DarkText,
  surface = Ivory,
  onSurface = DarkText,
  surfaceVariant = SurfaceCream,
  onSurfaceVariant = SecondaryText,
  outline = SoftBorder,
  outlineVariant = DividerColor
)

@Composable
fun LearningChineseTheme(
  content: @Composable () -> Unit
) {
  MaterialTheme(
    colorScheme = LearningChineseColorScheme,
    typography = Typography,
    content = content
  )
}

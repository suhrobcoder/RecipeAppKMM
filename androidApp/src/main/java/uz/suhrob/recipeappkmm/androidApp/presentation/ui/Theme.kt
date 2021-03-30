package uz.suhrob.recipeappkmm.androidApp.presentation.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val LightColorPalette = lightColors(
    primary = LightGreen600,
    primaryVariant = Gray900,
    secondary = Gray900,
    secondaryVariant = Gray500,
    background = Color.White,
    onBackground = Gray900,
    surface = Color.White,
    onSurface = Gray900,
)

@Composable
fun RecipeAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = LightColorPalette,
        typography = SFProTypography,
        shapes = shapes,
        content = content,
    )
}
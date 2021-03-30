package uz.suhrob.recipeappkmm.androidApp.presentation.ui

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import uz.suhrob.recipeappkmm.androidApp.R

private val SFProFont = FontFamily(
    Font(R.font.sf_pro_regular, FontWeight.Normal),
    Font(R.font.sf_pro_medium, FontWeight.Medium),
    Font(R.font.sf_pro_semibold, FontWeight.SemiBold),
    Font(R.font.sf_pro_bold, FontWeight.Bold),
)

val SFProTypography = Typography(
    defaultFontFamily = SFProFont
)
package uz.suhrob.recipeappkmm.androidApp.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import uz.suhrob.recipeappkmm.androidApp.R

sealed class Navigation(val route: String) {
    object Onboarding : Navigation("onboarding")
    object Home : Navigation("home")
    object Details : Navigation("details")
}

sealed class HomeBottomNav(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val route: String,
) {
    object Recipes : HomeBottomNav(R.string.recipes, R.drawable.ic_recipe, "recipes")
    object Search : HomeBottomNav(R.string.search, R.drawable.ic_search, "search")
    object Saved : HomeBottomNav(R.string.saved, R.drawable.ic_bookmark, "saved")
    object Settings : HomeBottomNav(R.string.settings, R.drawable.ic_settings, "settings")
}

val homeBottomNavItems = listOf(
    HomeBottomNav.Recipes,
    HomeBottomNav.Search,
    HomeBottomNav.Saved,
    HomeBottomNav.Settings,
)
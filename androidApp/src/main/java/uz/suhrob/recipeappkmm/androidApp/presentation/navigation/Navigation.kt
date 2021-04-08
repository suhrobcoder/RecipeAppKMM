package uz.suhrob.recipeappkmm.androidApp.presentation.navigation

sealed class Navigation(val route: String) {
    object Onboarding: Navigation("onboarding")
    object Home: Navigation("home")
}

package uz.suhrob.recipeappkmm.androidApp.presentation

import android.graphics.Color
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Surface
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint
import uz.suhrob.recipeappkmm.androidApp.presentation.home.HomeScreen
import uz.suhrob.recipeappkmm.androidApp.presentation.navigation.Navigation
import uz.suhrob.recipeappkmm.androidApp.presentation.onboarding.OnboardingScreen
import uz.suhrob.recipeappkmm.androidApp.presentation.onboarding.OnboardingViewModel
import uz.suhrob.recipeappkmm.androidApp.presentation.ui.RecipeAppTheme

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<OnboardingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        setContent {
            RecipeAppTheme {
                ProvideWindowInsets {
                    Surface {
                        val navController = rememberNavController()
                        NavHost(
                            navController = navController,
                            startDestination = Navigation.Onboarding.route
                        ) {
                            composable(Navigation.Onboarding.route) {
                                OnboardingScreen(viewModel, navController)
                            }
                            composable(Navigation.Home.route) {
                                HomeScreen(navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

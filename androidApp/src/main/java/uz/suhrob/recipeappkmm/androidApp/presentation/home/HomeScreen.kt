package uz.suhrob.recipeappkmm.androidApp.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.google.accompanist.insets.navigationBarsPadding
import uz.suhrob.recipeappkmm.androidApp.presentation.home.recipes.RecipesScreen
import uz.suhrob.recipeappkmm.androidApp.presentation.home.recipes.RecipesViewModel
import uz.suhrob.recipeappkmm.androidApp.presentation.navigation.HomeBottomNav
import uz.suhrob.recipeappkmm.androidApp.presentation.navigation.homeBottomNavItems
import uz.suhrob.recipeappkmm.androidApp.presentation.ui.Gray400

@ExperimentalFoundationApi
@Composable
fun HomeScreen(mainNavController: NavController) {
    val bottomNavController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation(backgroundColor = MaterialTheme.colors.background) {
                val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
                homeBottomNavItems.forEach { screen ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                ImageVector.vectorResource(id = screen.icon),
                                contentDescription = null
                            )
                        },
                        label = { Text(stringResource(id = screen.title)) },
                        selected = currentRoute == screen.route,
                        selectedContentColor = MaterialTheme.colors.onBackground,
                        unselectedContentColor = Gray400,
                        onClick = {
                            bottomNavController.navigate(screen.route) {
                                popUpTo = bottomNavController.graph.startDestination
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        },
        modifier = Modifier.navigationBarsPadding(),
    ) {
        NavHost(
            navController = bottomNavController,
            startDestination = HomeBottomNav.Recipes.route
        ) {
            composable(HomeBottomNav.Recipes.route) {
                val viewModel = hiltNavGraphViewModel<RecipesViewModel>()
                RecipesScreen(mainNavController, viewModel)
            }
            composable(HomeBottomNav.Search.route) {
                Text("Search")
            }
            composable(HomeBottomNav.Saved.route) {
                Text("Saved")
            }
            composable(HomeBottomNav.Settings.route) {
                Text("Settings")
            }
        }
    }
}
package ua.kpi.practical_example_5.composables

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ua.kpi.practical_example_5.Screen
import ua.kpi.practical_example_5.advancedScreens.MainMenuScreen
import ua.kpi.practical_example_5.advancedScreens.SolarDetailsScreen
import ua.kpi.practical_example_5.advancedScreens.SolarForecastScreen
import ua.kpi.practical_example_5.advancedScreens.UserInfoScreen

@Composable
fun AdvancedNavHostController(
    navController: NavHostController,
) {

    // NavHost з усіма маршрутами
    NavHost(navController = navController, startDestination = Screen.AdvancedScreen.MainScreen.route) {
        composable(Screen.AdvancedScreen.MainScreen.route) { MainMenuScreen(navController) }
        composable(Screen.AdvancedScreen.UserInfo.route) { UserInfoScreen(navController) }
        composable(
            "${Screen.AdvancedScreen.SolarForecast.route}/{userName}",
            arguments = listOf(navArgument("userName") { type = NavType.StringType })
        ) { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("userName") ?: "Користувач"
            SolarForecastScreen(navController, userName)
        }
        composable(
            "${Screen.AdvancedScreen.SolarDetails.route}/{panelsCount}",
            arguments = listOf(navArgument("panelsCount") { type = NavType.IntType })
        ) { backStackEntry ->
            val panelsCount = backStackEntry.arguments?.getInt("panelsCount") ?: 0
            SolarDetailsScreen(navController, panelsCount)
        }
    }
}
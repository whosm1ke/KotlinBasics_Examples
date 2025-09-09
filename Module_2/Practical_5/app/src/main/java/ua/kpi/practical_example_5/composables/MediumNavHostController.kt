package ua.kpi.practical_example_5.composables

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ua.kpi.practical_example_5.Screen
import ua.kpi.practical_example_5.mediumScreens.MainMenuScreen
import ua.kpi.practical_example_5.mediumScreens.SolarDetailsScreen
import ua.kpi.practical_example_5.mediumScreens.SolarForecastScreen
import ua.kpi.practical_example_5.mediumScreens.UserInfoScreen

@Composable
fun MediumNavHostController(
    navController: NavHostController,
) {

    NavHost(navController = navController, startDestination = Screen.MediumScreen.MainScreen.route) {
        // Головне меню
        composable(Screen.MediumScreen.MainScreen.route) { MainMenuScreen(navController) }

        // Екран інформації про користувача
        composable(Screen.MediumScreen.UserInfo.route) { UserInfoScreen(navController) }

        // Екран прогнозу СЕС з передачею аргументів
        composable(
            "${Screen.MediumScreen.SolarForecast.route}/{userName}",
            arguments = listOf(navArgument("userName") { type = NavType.StringType })
        ) { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("userName") ?: "Користувач"
            SolarForecastScreen(navController, userName)
        }

        // Деталі прогнозу (третій екран)
        composable(
            "${Screen.MediumScreen.SolarDetails.route}/{panelsCount}",
            arguments = listOf(navArgument("panelsCount") { type = NavType.IntType })
        ) { backStackEntry ->
            val panelsCount = backStackEntry.arguments?.getInt("panelsCount") ?: 0
            SolarDetailsScreen(navController, panelsCount)
        }
    }
}
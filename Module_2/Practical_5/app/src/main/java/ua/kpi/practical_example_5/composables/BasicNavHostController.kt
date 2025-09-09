package ua.kpi.practical_example_5.composables

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ua.kpi.practical_example_5.Screen
import ua.kpi.practical_example_5.basicScreens.MainMenuScreen
import ua.kpi.practical_example_5.basicScreens.SolarForecastScreen
import ua.kpi.practical_example_5.basicScreens.UserInfoScreen

@Composable
fun BasicNavHostController(
    navController: NavHostController,
) {

    // NavHost: контейнер для екранів, визначає маршрути
    NavHost(navController = navController, startDestination = Screen.BasicScreen.MainScreen.route) {
        // Головне меню
        composable(Screen.BasicScreen.MainScreen.route) { MainMenuScreen(navController) }

        // Екран інформації про користувача
        composable(Screen.BasicScreen.UserInfo.route) { UserInfoScreen(navController) }

        // Екран прогнозу сонячної електростанції
        composable(Screen.BasicScreen.SolarForecast.route) { SolarForecastScreen(navController) }
    }
}
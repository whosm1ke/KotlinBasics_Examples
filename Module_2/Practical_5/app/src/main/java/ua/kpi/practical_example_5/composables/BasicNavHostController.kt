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
    navController: NavHostController, // Контролер навігації для керування переходами між екранами
) {

    // NavHost: контейнер для екранів, визначає маршрути та керує навігацією
    NavHost(navController = navController, startDestination = Screen.BasicScreen.MainScreen.route) {
        // Головне меню - відображається при запуску додатку
        composable(Screen.BasicScreen.MainScreen.route) { MainMenuScreen(navController) }

        // Екран інформації про користувача - відкривається за кнопкою "User Info" у головному меню
        composable(Screen.BasicScreen.UserInfo.route) { UserInfoScreen(navController) }

        // Екран прогнозу сонячної електростанції - відкривається за кнопкою "Solar Forecast" у головному меню
        composable(Screen.BasicScreen.SolarForecast.route) { SolarForecastScreen(navController) }
    }
}
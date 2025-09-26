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
    navController: NavHostController, // Контролер навігації для керування переходами між екранами
) {

    // Основний NavHost, який відповідає за відображення екранів в залежності від поточного маршруту
    NavHost(navController = navController, startDestination = Screen.AdvancedScreen.MainScreen.route) {
        // Компонента для головного меню
        composable(Screen.AdvancedScreen.MainScreen.route) { MainMenuScreen(navController) }
        
        // Компонента для екрана інформації про користувача
        composable(Screen.AdvancedScreen.UserInfo.route) { UserInfoScreen(navController) }
        
        // Компонента для прогнозу сонячної енергії з переданим ім'ям користувача в параметрах маршруту
        composable(
            "${Screen.AdvancedScreen.SolarForecast.route}/{userName}",
            arguments = listOf(navArgument("userName") { type = NavType.StringType }) // Визначення аргументу userName як рядка
        ) { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("userName") ?: "Користувач" // Отримання імені користувача з параметрів або встановлення значення за замовчуванням
            SolarForecastScreen(navController, userName) // Передача отриманого імені користувача в екран прогнозу
        }
        
        // Компонента для детального перегляду сонячних панелей з переданим кількістю панелей в параметрах маршруту
        composable(
            "${Screen.AdvancedScreen.SolarDetails.route}/{panelsCount}",
            arguments = listOf(navArgument("panelsCount") { type = NavType.IntType }) // Визначення аргументу panelsCount як цілого числа
        ) { backStackEntry ->
            val panelsCount = backStackEntry.arguments?.getInt("panelsCount") ?: 0 // Отримання кількості панелей з параметрів або встановлення значення за замовчуванням
            SolarDetailsScreen(navController, panelsCount) // Передача отриманої кількості панелей в екран деталей
        }
    }
}
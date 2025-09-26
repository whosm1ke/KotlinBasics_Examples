package ua.kpi.practical_example_5

import androidx.annotation.DrawableRes

// Абстрактний клас Screen, що використовується для представлення різних екранів додатку.
// Він має один властивість route, яка визначає маршрут до конкретного екрана.
sealed class Screen(
    val route: String,
) {

    // Вкладений абстрактний клас BasicScreen, що наслідується від Screen.
    // Він має властивість title, яка визначає заголовок екрана.
    sealed class BasicScreen(
        val title: String,
    ) : Screen(title) {

        // Об'єкт MainScreen, що представляє головний екран базового рівня.
        object MainScreen : BasicScreen("basic_main_menu")
        
        // Об'єкт SolarForecast, що представляє екран прогнозу сонячної енергії для базового рівня.
        object SolarForecast : BasicScreen("basic_solar_forecast")
        
        // Об'єкт UserInfo, що представляє екран інформації про користувача для базового рівня.
        object UserInfo : BasicScreen("basic_user_info")
    }

    // Вкладений абстрактний клас MediumScreen, що наслідується від Screen.
    // Він має властивість title, яка визначає заголовок екрана.
    sealed class MediumScreen(
        val title: String,
    ) : Screen(title) {

        // Об'єкт MainScreen, що представляє головний екран середнього рівня.
        object MainScreen : MediumScreen("medium_main_menu")
        
        // Об'єкт SolarForecast, що представляє екран прогнозу сонячної енергії для середнього рівня.
        object SolarForecast : MediumScreen("medium_solar_forecast")
        
        // Об'єкт UserInfo, що представляє екран інформації про користувача для середнього рівня.
        object UserInfo : MediumScreen("medium_user_info")
        
        // Об'єкт SolarDetails, що представляє детальний екран сонячної енергії для середнього рівня.
        object SolarDetails : MediumScreen("medium_solar_details")
    }

    // Вкладений абстрактний клас AdvancedScreen, що наслідується від Screen.
    // Він має властивість title, яка визначає заголовок екрана.
    sealed class AdvancedScreen(
        val title: String,
    ) : Screen(title) {

        // Об'єкт MainScreen, що представляє головний екран просунутого рівня.
        object MainScreen : AdvancedScreen("advanced_main_menu")
        
        // Об'єкт SolarForecast, що представляє екран прогнозу сонячної енергії для просунутого рівня.
        object SolarForecast : AdvancedScreen("advanced_solar_forecast")
        
        // Об'єкт UserInfo, що представляє екран інформації про користувача для просунутого рівня.
        object UserInfo : AdvancedScreen("advanced_user_info")
        
        // Об'єкт SolarDetails, що представляє детальний екран сонячної енергії для просунутого рівня.
        object SolarDetails : AdvancedScreen("advanced_solar_details")
    }
}
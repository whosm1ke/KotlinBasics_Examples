package ua.kpi.practical_example_5

import androidx.annotation.DrawableRes

sealed class Screen(
    val route: String,
) {
    sealed class BasicScreen(
        val title: String,
    ) : Screen(title) {
        object MainScreen : BasicScreen("basic_main_menu")
        object SolarForecast : BasicScreen("basic_solar_forecast")
        object UserInfo : BasicScreen("basic_user_info")
    }

    sealed class MediumScreen(
        val title: String,
    ) : Screen(title) {
        object MainScreen : MediumScreen("medium_main_menu")
        object SolarForecast : MediumScreen("medium_solar_forecast")
        object UserInfo : MediumScreen("medium_user_info")
        object SolarDetails : MediumScreen("medium_solar_details")
    }

    sealed class AdvancedScreen(
        val title: String,
    ) : Screen(title) {
        object MainScreen : AdvancedScreen("advanced_main_menu")
        object SolarForecast : AdvancedScreen("advanced_solar_forecast")
        object UserInfo : AdvancedScreen("advanced_user_info")
        object SolarDetails : AdvancedScreen("advanced_solar_details")
    }
}

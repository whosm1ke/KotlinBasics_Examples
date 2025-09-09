package ua.kpi.practical_example_5.advancedScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ua.kpi.practical_example_5.Screen
import ua.kpi.practical_example_5.components.MenuButton
import ua.kpi.practical_example_5.components.ScreenTitle

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun MainMenuScreen(navController: NavHostController) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isLargeScreen = maxWidth > 600.dp

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScreenTitle("Головне меню")

            if (isLargeScreen) {
                // На великих екранах кнопки в Row
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    MenuButton("Інформація про користувача") { navController.navigate(Screen.AdvancedScreen.UserInfo.route) }
                    MenuButton("Прогноз потужності СЕС") { navController.navigate("${Screen.AdvancedScreen.SolarForecast.route}/Anonymous") }
                }
            } else {
                // На малих екранах вертикально
                MenuButton("Інформація про користувача") { navController.navigate(Screen.AdvancedScreen.UserInfo.route) }
                MenuButton("Прогноз потужності СЕС") { navController.navigate("${Screen.AdvancedScreen.SolarForecast.route}/Anonymous") }
            }
        }
    }
}

package ua.kpi.practical_example_5.advancedScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ua.kpi.practical_example_5.Screen
import ua.kpi.practical_example_5.components.InfoRow
import ua.kpi.practical_example_5.components.MenuButton
import ua.kpi.practical_example_5.components.ScreenTitle

@Composable
fun SolarDetailsScreen(navController: NavHostController, panelsCount: Int) {
    val totalPower = panelsCount * 0.3 // Потужність однієї панелі 0.3 кВт

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScreenTitle("Деталі прогнозу")

        InfoRow(label = "Кількість панелей", value = panelsCount.toString())
        InfoRow(label = "Очікувана потужність", value = "%.1f кВт".format(totalPower))

        Spacer(modifier = Modifier.height(16.dp))

        MenuButton("Повернутися до прогнозу") { navController.navigate("${Screen.AdvancedScreen.SolarForecast.route}/Anonymous") }
        MenuButton("Повернутися в меню") { navController.navigate(Screen.AdvancedScreen.MainScreen.route) }
    }
}
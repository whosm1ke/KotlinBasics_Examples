package ua.kpi.practical_example_27.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_27.viewModel.ForecastViewModel

@Composable
fun MainMenuScreen(viewModel: ForecastViewModel) {
    // Створення стану для відстеження поточного екрана (forecast, history, analytics, settings)
    var currentScreen by remember { mutableStateOf("forecast") }

    Column(modifier = Modifier.fillMaxSize()) {
        // Верхня панель з кнопками для переходу між екранами
        Row(modifier = Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.SpaceAround) {
            Button(onClick = { currentScreen = "forecast" }) { Text("Прогноз") }
            Button(onClick = { currentScreen = "history" }) { Text("Історія") }
            Button(onClick = { currentScreen = "analytics" }) { Text("Аналітика") }
            Button(onClick = { currentScreen = "settings" }) { Text("Налаштування") }
        }
        Divider()

        // Відображення поточного екрану в залежності від стану currentScreen
        when (currentScreen) {
            "forecast" -> ForecastScreen(viewModel)
            "history" -> HistoryScreen(viewModel)
            "analytics" -> AnalyticsScreen(viewModel)
            "settings" -> SettingsScreen(viewModel)
        }
    }
}
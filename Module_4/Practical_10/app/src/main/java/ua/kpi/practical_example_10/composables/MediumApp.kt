package ua.kpi.practical_example_10.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.kpi.practical_example_10.medium.SolarViewModel

@Composable
fun MediumApp(viewModel: SolarViewModel = viewModel()) {

    // Отримуємо стани з ViewModel для станцій, прогнозів, помилок та завантаження
    val stations by viewModel.stations.collectAsState()
    val forecasts by viewModel.forecasts.collectAsState()
    val error by viewModel.error.collectAsState()
    val loading by viewModel.loading.collectAsState()

    // Завантажуємо список станцій при першому запуску компоненти
    LaunchedEffect(Unit) {
        viewModel.loadStations()
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) { // Основна колонка з відступами

        // Якщо йде завантаження, показуємо індикатор
        if (loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        // Якщо є помилка, відображаємо її текст
        error?.let { Text(it, color = MaterialTheme.colorScheme.error) }

        // Використовуємо LazyColumn для ефективного відображення списку станцій
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(stations) { station ->
                // Кожна станція відображається у Card (карточці)
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Тінь карточки
                ) {
                    Column(modifier = Modifier.padding(16.dp)) { // Внутрішня колонка з відступами
                        // Відображаємо назву станції з іконкою сонця
                        Text("🌞 ${station.name}", style = MaterialTheme.typography.titleMedium)
                        // Відображаємо розташування станції
                        Text("📍 ${station.location}")
                        // Відображаємо потужність станції
                        Text("⚡ Потужність: ${station.capacityKw} кВт")

                        Spacer(modifier = Modifier.height(8.dp)) // Простір між елементами

                        // Кнопка для завантаження прогнозів для конкретної станції
                        Button(onClick = { viewModel.loadForecasts(station.id) }) {
                            Text("📊 Завантажити прогнози")
                        }

                        Spacer(modifier = Modifier.height(8.dp)) // Простір між кнопкою та прогнозами

                        // Отримуємо прогнози для цієї станції або порожній список
                        val stationForecasts = forecasts[station.id] ?: emptyList()
                        Column {
                            // Відображаємо кожен прогноз
                            stationForecasts.forEach { f ->
                                Text(
                                    "${f.date}: ⚡ ${f.predictedPowerKw} кВт, " +
                                            "🌡 ${f.temperatureC}°C, ☁️ ${f.cloudCoveragePercent}%"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
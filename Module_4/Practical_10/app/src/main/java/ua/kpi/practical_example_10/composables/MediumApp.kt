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

    val stations by viewModel.stations.collectAsState()
    val forecasts by viewModel.forecasts.collectAsState()
    val error by viewModel.error.collectAsState()
    val loading by viewModel.loading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadStations()
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        if (loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        error?.let { Text(it, color = MaterialTheme.colorScheme.error) }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(stations) { station ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("🌞 ${station.name}", style = MaterialTheme.typography.titleMedium)
                        Text("📍 ${station.location}")
                        Text("⚡ Потужність: ${station.capacityKw} кВт")

                        Spacer(modifier = Modifier.height(8.dp))

                        // Кнопка завантаження прогнозів
                        Button(onClick = { viewModel.loadForecasts(station.id) }) {
                            Text("📊 Завантажити прогнози")
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Прогнози станції
                        val stationForecasts = forecasts[station.id] ?: emptyList()
                        Column {
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

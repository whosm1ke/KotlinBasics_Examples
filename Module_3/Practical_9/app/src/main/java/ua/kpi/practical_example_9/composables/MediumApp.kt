package ua.kpi.practical_example_9.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.kpi.practical_example_9.medium.SolarForecast
import ua.kpi.practical_example_9.medium.SolarViewModel

@Composable
fun MediumApp(viewModel: SolarViewModel = viewModel()) {
    val forecasts by viewModel.forecasts.collectAsState()

    var day by remember { mutableStateOf("") }
    var power by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Середній рівень: Retrofit2 + локальне кешування")

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = day,
            onValueChange = { day = it },
            label = { Text("День") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = power,
            onValueChange = { power = it },
            label = { Text("Потужність (кВт)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        Row {
            Button(onClick = {
                if (day.isNotBlank() && power.isNotBlank()) {
                    val id = (forecasts.maxOfOrNull { it.id } ?: 0) + 1
                    viewModel.addForecast(SolarForecast(id, day, power.toDouble()))
                    day = ""
                    power = ""
                }
            }) {
                Text("Додати")
            }
            Spacer(Modifier.width(8.dp))
            Button(onClick = { viewModel.loadForecasts() }) {
                Text("Завантажити")
            }
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn {
            items(forecasts) { forecast ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("${forecast.day}: ${forecast.predictedPower} кВт")
                    IconButton(onClick = { viewModel.deleteForecast(forecast.id) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Видалити")
                    }
                }
            }
        }
    }
}
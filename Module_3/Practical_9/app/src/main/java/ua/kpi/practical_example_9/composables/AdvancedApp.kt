package ua.kpi.practical_example_9.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.kpi.practical_example_9.advanced.SolarForecast
import ua.kpi.practical_example_9.advanced.SolarViewModel

@Composable
fun AdvancedApp(viewModel: SolarViewModel = viewModel()) {
    val forecasts by viewModel.forecasts.collectAsState()

    var filterDay by remember { mutableStateOf("") }
    var sortDescending by remember { mutableStateOf(false) }

    var newDay by remember { mutableStateOf("") }
    var newPower by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Просунутий рівень: агрегація, кешування, бізнес-логіка")

        // Фільтр і сортування
        Row {
            OutlinedTextField(
                value = filterDay,
                onValueChange = {
                    filterDay = it
                    viewModel.filterByDay(it)
                },
                label = { Text("Фільтр по дню") },
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(8.dp))
            Button(onClick = {
                sortDescending = !sortDescending
                viewModel.sortByPower(sortDescending)
            }) {
                Text(if (sortDescending) "Сортувати ↑" else "Сортувати ↓")
            }
        }

        Spacer(Modifier.height(16.dp))

        // Форма для додавання нового прогнозу
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = newDay,
                onValueChange = { newDay = it },
                label = { Text("День") },
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(8.dp))
            OutlinedTextField(
                value = newPower,
                onValueChange = { newPower = it },
                label = { Text("Потужність") },
                modifier = Modifier.width(120.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(Modifier.width(8.dp))
            Button(onClick = {
                val power = newPower.toDoubleOrNull()
                if (newDay.isNotBlank() && power != null) {
                    viewModel.addForecast(SolarForecast(forecasts.size + 1, newDay, power))
                    newDay = ""
                    newPower = ""
                }
            }) {
                Text("Додати")
            }
        }

        Spacer(Modifier.height(16.dp))

        // Кнопка завантаження всіх прогнозів
        Button(onClick = { viewModel.loadForecasts(forceRefresh = true) }) {
            Text("Завантажити прогнози")
        }

        Spacer(Modifier.height(16.dp))

        // Список прогнозів
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

package ua.kpi.practical_example_9.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.kpi.practical_example_9.basic.LocalSolarDataSource
import ua.kpi.practical_example_9.basic.SolarForecast
import ua.kpi.practical_example_9.basic.SolarRepository
import ua.kpi.practical_example_9.basic.SolarViewModel
import ua.kpi.practical_example_9.basic.SolarViewModelFactory

@Composable
fun BasicApp() {
    val repository = SolarRepository(LocalSolarDataSource())
    val viewModel: SolarViewModel = viewModel(
        factory = SolarViewModelFactory(repository)
    )
    val forecasts by viewModel.forecasts.collectAsState()

    var day by remember { mutableStateOf("") }
    var power by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Базовий рівень: локальне сховище", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = day,
            onValueChange = { day = it },
            label = { Text("День") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = power,
            onValueChange = { power = it },
            label = { Text("Потужність (кВт)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (day.isNotBlank() && power.isNotBlank()) {
                    val id = forecasts.size + 1
                    val item = SolarForecast(id, day, power.toDouble())
                    viewModel.addForecast(item)
                    day = ""
                    power = ""
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Додати")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Прогнози:", style = MaterialTheme.typography.titleMedium)

        LazyColumn {
            items(forecasts) { forecast ->
                Text("${forecast.day}: ${forecast.predictedPower} кВт",
                    style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
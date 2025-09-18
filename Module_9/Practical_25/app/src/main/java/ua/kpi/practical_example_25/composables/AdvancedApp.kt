package ua.kpi.practical_example_25.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ua.kpi.practical_example_25.data.AdvancedCalculator

@Composable
fun AdvancedApp() {
    var solarIrradiance by remember { mutableStateOf("") }
    var temperature by remember { mutableStateOf("") }
    var panelArea by remember { mutableStateOf("") }

    var isLoading by remember { mutableStateOf(false) }
    var result by remember { mutableStateOf<Double?>(null) }

    val scope = rememberCoroutineScope()
    val calculator = AdvancedCalculator()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = solarIrradiance,
            onValueChange = { solarIrradiance = it },
            label = { Text("Сонячна радіація (Вт/м²)") },
            modifier = Modifier.fillMaxWidth().testTag("SolarRadiation")
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = temperature,
            onValueChange = { temperature = it },
            label = { Text("Температура (°C)") },
            modifier = Modifier.fillMaxWidth().testTag("Temperature")
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = panelArea,
            onValueChange = { panelArea = it },
            label = { Text("Площа панелей (м²)") },
            modifier = Modifier.fillMaxWidth().testTag("PanelArea")
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val irradianceVal = solarIrradiance.toDoubleOrNull() ?: 900.0  // моковані дані
                val temperatureVal = temperature.toDoubleOrNull() ?: 20.0
                val areaVal = panelArea.toDoubleOrNull() ?: 12.0

                isLoading = true
                result = null

                scope.launch {
                    val calculated = calculator.calculatePowerAsync(irradianceVal, temperatureVal, areaVal)
                    result = calculated
                    isLoading = false
                }
            },
            modifier = Modifier.fillMaxWidth().testTag("CalculateButton")
        ) {
            Text("Отримати прогноз")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Показуємо індикатор завантаження
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.testTag("CircularProgressIndicator")
            )
        }

        // Показуємо результат
        result?.let {
            Text(
                text = "Прогнозована потужність: %.2f кВт".format(it),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 16.dp).testTag("Result")
            )
        }
    }
}
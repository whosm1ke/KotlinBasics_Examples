package ua.kpi.practical_example_25.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_25.data.BasicCalculator

@Composable
fun BasicApp() {
    // Змінні стану для введених користувачем даних
    var solarIrradiance by remember { mutableStateOf("") } // Сонячна радіація (Вт/м²)
    var temperature by remember { mutableStateOf("") }     // Температура (°C)
    var panelArea by remember { mutableStateOf("") }       // Площа панелей (м²)
    // Змінна для збереження результату розрахунку
    var result by remember { mutableStateOf<Double?>(null) }

    val calc = BasicCalculator()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        // Поле вводу сонячної радіації
        OutlinedTextField(
            value = solarIrradiance,
            onValueChange = { solarIrradiance = it },
            label = { Text("Сонячна радіація (Вт/м²)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Поле вводу температури
        OutlinedTextField(
            value = temperature,
            onValueChange = { temperature = it },
            label = { Text("Температура (°C)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Поле вводу площі панелей
        OutlinedTextField(
            value = panelArea,
            onValueChange = { panelArea = it },
            label = { Text("Площа панелей (м²)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка "Розрахувати"
        Button(
            onClick = {
                // Спроба конвертації введених даних у числа
                val irradianceVal = solarIrradiance.toDoubleOrNull()
                val temperatureVal = temperature.toDoubleOrNull()
                val areaVal = panelArea.toDoubleOrNull()

                if (irradianceVal != null && temperatureVal != null && areaVal != null) {
                    // Виклик функції розрахунку потужності
                    result = calc.calculatePower(irradianceVal, temperatureVal, areaVal)
                } else {
                    result = null // Якщо дані некоректні
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Розрахувати")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Виведення результату
        if (result != null) {
            Text(
                text = "Прогнозована потужність: %.2f кВт".format(result),
                style = MaterialTheme.typography.titleMedium
            )
        } else {
            Text(
                text = "Будь ласка, введіть коректні дані",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


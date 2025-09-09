package ua.kpi.practical_example_9.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.kpi.practical_example_9.viewModels.SolarViewModel
import ua.kpi.practical_example_9.viewModels.SolarViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicApp(viewModel: SolarViewModel = viewModel(factory = SolarViewModelFactory())) {
    // Локальний стан для введення користувачем
    var dayInput by remember { mutableStateOf("") }
    var powerInput by remember { mutableStateOf("") }

    // Отримання списку прогнозів із ViewModel
    val powerList by viewModel.powerList.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text(text = "Прогноз потужності сонячної електростанції", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // Поля введення
        OutlinedTextField(
            value = dayInput,
            onValueChange = { dayInput = it },
            label = { Text("День (наприклад, 2025-09-09)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = powerInput,
            onValueChange = { powerInput = it },
            label = { Text("Потужність (кВт)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка додавання
        Button(
            onClick = {
                val powerValue = powerInput.toDoubleOrNull() ?: 0.0
                viewModel.addPower(dayInput, powerValue)
                dayInput = ""
                powerInput = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Додати прогноз")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Відображення списку прогнозів
        Text(text = "Список прогнозів:", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        for (item in powerList) {
            Text(text = "${item.day}: ${item.power} кВт")
        }
    }
}
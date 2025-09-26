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
    // Створюємо репозиторій для роботи з даними про сонячну погоду, використовуючи локальне джерело даних
    val repository = SolarRepository(LocalSolarDataSource())
    
    // Створюємо ViewModel для управління даними прогнозів, передаючи репозиторій як фактор
    val viewModel: SolarViewModel = viewModel(
        factory = SolarViewModelFactory(repository)
    )
    
    // Отримуємо колекцію прогнозів з ViewModel як стан Composable
    val forecasts by viewModel.forecasts.collectAsState()

    // Створюємо змінні для зберігання значень введених користувачем даних
    var day by remember { mutableStateOf("") }  // Змінна для дня
    var power by remember { mutableStateOf("") } // Змінна для потужності

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Відображаємо заголовок додатку
        Text("Базовий рівень: локальне сховище", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))  // Пропуск між елементами

        // Поле вводу для дня
        OutlinedTextField(
            value = day,
            onValueChange = { day = it },
            label = { Text("День") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))  // Пропуск між елементами

        // Поле вводу для потужності з обмеженням на тип клавіатури (числовий)
        OutlinedTextField(
            value = power,
            onValueChange = { power = it },
            label = { Text("Потужність (кВт)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))  // Пропуск між елементами

        // Кнопка додавання нового прогнозу
        Button(
            onClick = {
                // Перевіряємо, чи обидва поля заповнені
                if (day.isNotBlank() && power.isNotBlank()) {
                    // Створюємо унікальний ID для нового прогнозу
                    val id = forecasts.size + 1
                    // Створюємо новий об'єкт прогнозу з введеними даними
                    val item = SolarForecast(id, day, power.toDouble())
                    // Додаємо новий прогноз через ViewModel
                    viewModel.addForecast(item)
                    // Очищуємо поля вводу після успішного додавання
                    day = ""
                    power = ""
                }
            },
            modifier = Modifier.align(Alignment.End)  // Вирівнювання кнопки праворуч
        ) {
            Text("Додати")
        }

        Spacer(modifier = Modifier.height(16.dp))  // Пропуск між елементами

        // Заголовок для списку прогнозів
        Text("Прогнози:", style = MaterialTheme.typography.titleMedium)

        // Відображаємо список прогнозів у вигляді списку з можливістю прокрутки
        LazyColumn {
            items(forecasts) { forecast ->
                Text("${forecast.day}: ${forecast.predictedPower} кВт",
                    style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
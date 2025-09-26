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
    // Отримуємо список прогнозів з ViewModel за допомогою collectAsState
    val forecasts by viewModel.forecasts.collectAsState()

    // Створюємо стан для введення даних про день і потужність
    var day by remember { mutableStateOf("") }
    var power by remember { mutableStateOf("") }

    // Основна колонка з елементами інтерфейсу
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Відображаємо заголовок додатку
        Text("Середній рівень: Retrofit2 + локальне кешування")

        // Простір між елементами
        Spacer(Modifier.height(8.dp))

        // Поле для введення дня
        OutlinedTextField(
            value = day,
            onValueChange = { day = it },
            label = { Text("День") },
            modifier = Modifier.fillMaxWidth()
        )

        // Простір між елементами
        Spacer(Modifier.height(8.dp))

        // Поле для введення потужності
        OutlinedTextField(
            value = power,
            onValueChange = { power = it },
            label = { Text("Потужність (кВт)") },
            modifier = Modifier.fillMaxWidth()
        )

        // Простір між елементами
        Spacer(Modifier.height(8.dp))

        // Група кнопок для додавання та завантаження даних
        Row {
            Button(onClick = {
                // Перевіряємо, чи заповнені обидва поля
                if (day.isNotBlank() && power.isNotBlank()) {
                    // Знаходимо максимальний ID у списку прогнозів і збільшуємо його на 1 для нового запису
                    val id = (forecasts.maxOfOrNull { it.id } ?: 0) + 1
                    // Додаємо новий прогноз у ViewModel
                    viewModel.addForecast(SolarForecast(id, day, power.toDouble()))
                    // Очищуємо поля вводу
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

        // Простір між елементами
        Spacer(Modifier.height(16.dp))

        // Відображення списку прогнозів у вигляді LazyColumn
        LazyColumn {
            items(forecasts) { forecast ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Відображаємо день і прогнозовану потужність
                    Text("${forecast.day}: ${forecast.predictedPower} кВт")
                    // Кнопка видалення прогнозу
                    IconButton(onClick = { viewModel.deleteForecast(forecast.id) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Видалити")
                    }
                }
            }
        }
    }
}
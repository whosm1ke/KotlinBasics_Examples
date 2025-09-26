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
    // Отримуємо стан прогнозів з ViewModel, автоматично оновлюється при зміні даних
    val forecasts by viewModel.forecasts.collectAsState()

    // Змінні для управління фільтром і сортуванням
    var filterDay by remember { mutableStateOf("") }
    var sortDescending by remember { mutableStateOf(false) }

    // Змінні для нового прогнозу, що додається
    var newDay by remember { mutableStateOf("") }
    var newPower by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Просунутий рівень: агрегація, кешування, бізнес-логіка")

        // Рядок з полем фільтрації та кнопкою сортування
        Row {
            OutlinedTextField(
                value = filterDay,
                onValueChange = {
                    filterDay = it
                    viewModel.filterByDay(it)  // Викликає метод фільтрації у ViewModel
                },
                label = { Text("Фільтр по дню") },
                modifier = Modifier.weight(1f)  // Займає всю доступну ширину
            )
            Spacer(Modifier.width(8.dp))
            Button(onClick = {
                sortDescending = !sortDescending
                viewModel.sortByPower(sortDescending)  // Переключає порядок сортування і викликає метод у ViewModel
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
                modifier = Modifier.weight(1f)  // Займає всю доступну ширину
            )
            Spacer(Modifier.width(8.dp))
            OutlinedTextField(
                value = newPower,
                onValueChange = { newPower = it },
                label = { Text("Потужність") },
                modifier = Modifier.width(120.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)  // Встановлює числовий тип клавіатури
            )
            Spacer(Modifier.width(8.dp))
            Button(onClick = {
                val power = newPower.toDoubleOrNull()  // Перетворюємо введене значення на число
                if (newDay.isNotBlank() && power != null) {  // Перевіряємо, чи заповнені поля
                    viewModel.addForecast(SolarForecast(forecasts.size + 1, newDay, power))  // Додаємо новий прогноз
                    newDay = ""
                    newPower = ""
                }
            }) {
                Text("Додати")
            }
        }

        Spacer(Modifier.height(16.dp))

        // Кнопка для оновлення всіх прогнозів з сервера
        Button(onClick = { viewModel.loadForecasts(forceRefresh = true) }) {
            Text("Завантажити прогнози")
        }

        Spacer(Modifier.height(16.dp))

        // Список прогнозів з можливістю видалення
        LazyColumn {
            items(forecasts) { forecast ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween  // Розміщує елементи на краях
                ) {
                    Text("${forecast.day}: ${forecast.predictedPower} кВт")
                    IconButton(onClick = { viewModel.deleteForecast(forecast.id) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Видалити")  // Іконка видалення
                    }
                }
            }
        }
    }
}
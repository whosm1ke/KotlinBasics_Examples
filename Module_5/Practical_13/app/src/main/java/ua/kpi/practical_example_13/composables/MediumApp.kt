package ua.kpi.practical_example_13.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediumApp() {
    var power by remember { mutableStateOf(0f) }
    var status by remember { mutableStateOf("Очікування даних...") }

    val scope = rememberCoroutineScope()

    // Використання ViewModel-подібної логіки через remember
    val powerFlow = remember { getSolarPowerFlow() }

    LaunchedEffect(Unit) {
        powerFlow
            .map { it * 1.05f } // Наприклад, додаємо коефіцієнт прогнозу
            .filter { it > 10f } // Фільтр для малих значень
            .catch { e -> status = "Помилка: ${e.message}" } // Обробка помилок
            .collect { value ->
                power = value
                status = "Дані отримано успішно"
            }
    }

    Column {
        Text(text = "Середній рівень: Трансформація та обробка даних")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Поточна потужність: ${"%.2f".format(power)} кВт")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Статус: $status")
    }
}

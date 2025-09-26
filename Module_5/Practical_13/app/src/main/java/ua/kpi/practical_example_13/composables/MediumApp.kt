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
    // Створюємо стан для потужності та статусу, які будуть оновлюватися в процесі роботи
    var power by remember { mutableStateOf(0f) }
    var status by remember { mutableStateOf("Очікування даних...") }

    // Отримуємо scope для запуску корутин
    val scope = rememberCoroutineScope()

    // Використовуємо remember для збереження потоку даних, щоб уникнути повторного створення
    val powerFlow = remember { getSolarPowerFlow() }

    // Запускаємо корутину при першому виклику Composable-функції
    LaunchedEffect(Unit) {
        powerFlow
            .map { it * 1.05f } // Масштабуємо значення потужності з коефіцієнтом 1.05 (прогноз)
            .filter { it > 10f } // Фільтруємо значення, щоб відкинути малі значення менше 10
            .catch { e -> status = "Помилка: ${e.message}" } // Обробляємо помилки у потоці даних
            .collect { value ->
                power = value // Оновлюємо стан потужності
                status = "Дані отримано успішно" // Оновлюємо статус
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
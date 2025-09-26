package ua.kpi.practical_example_13.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicApp() {
    // Створення стану для зберігання поточного значення потужності
    var power by remember { mutableStateOf(0f) }

    // Отримання об'єкта CoroutineScope для запуску корутин
    val scope = rememberCoroutineScope()

    // Запуск LaunchedEffect, який виконується один раз при старті Composable-елемента
    LaunchedEffect(Unit) {
        // Виклик функції для отримання Flow потоку даних про потужність
        getSolarPowerFlow()
            .collect { value ->
                // Оновлення стану power при отриманні нового значення з Flow
                power = value
            }
    }

    Column {
        // Відображення заголовка
        Text(text = "Базовий рівень: Прогноз потужності сонячної станції")
        Spacer(modifier = Modifier.height(16.dp))
        // Відображення поточного значення потужності з двома знаками після коми
        Text(text = "Поточна потужність: ${"%.2f".format(power)} кВт")
    }
}

// Функція, яка створює Flow для імітації даних від сенсора сонячної станції
fun getSolarPowerFlow(): Flow<Float> = flow {
    // Нескінченний цикл для генерації даних
    while (true) {
        delay(1000) // Затримка 1 секунда перед надсиланням нового значення
        val power = Random.nextFloat() * 100f // Генерація випадкової потужності від 0 до 100 кВт
        emit(power) // Надсилання значення через Flow
    }
}
package ua.kpi.practical_example_13.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdvancedApp() {
    // Створюємо змінні стану для зберігання даних сенсора, прогнозу та комбінованої потужності
    var powerSensor by remember { mutableStateOf(0f) }
    var powerForecast by remember { mutableStateOf(0f) }
    var combinedPower by remember { mutableStateOf(0f) }
    var status by remember { mutableStateOf("Очікування даних...") }

    // Отримуємо потоки даних для сенсора та прогнозу
    val sensorFlow = remember { getSolarPowerFlow() }
    val forecastFlow = remember { getForecastFlow() }

    LaunchedEffect(Unit) {
        // Використовуємо combine для об'єднання двох потоків даних
        combine(sensorFlow, forecastFlow) { sensor, forecast ->
            sensor to forecast
        }
            .retryWhen { cause, attempt ->
                // Повторюємо запит при помилці з затримкою 1 секунди
                delay(1000)
                true // Продовжуємо повторювати
            }
            .collect { (sensor, forecast) ->
                // Оновлюємо значення стану при отриманні нових даних
                powerSensor = sensor
                powerForecast = forecast
                combinedPower = sensor + forecast
                status = "Дані успішно отримані та об'єднані"
            }
    }

    Column {
        // Відображаємо заголовок
        Text(text = "Просунутий рівень: Комбінування потоків та автоматичний збір")
        Spacer(modifier = Modifier.height(16.dp))
        // Відображаємо дані сенсора
        Text(text = "Потужність сенсора: ${"%.2f".format(powerSensor)} кВт")
        // Відображаємо прогноз потужності
        Text(text = "Прогноз потужності: ${"%.2f".format(powerForecast)} кВт")
        // Відображаємо комбіновану потужність
        Text(text = "Комбінована потужність: ${"%.2f".format(combinedPower)} кВт")
        Spacer(modifier = Modifier.height(8.dp))
        // Відображаємо статус операції
        Text(text = "Статус: $status")
    }
}

// Функція, що повертає потік даних з сенсора
fun getSolarPowerFlow(): Flow<Float> = flow {
    while (true) {
        delay(1000) // дані сенсора надходять кожну секунду
        val sensorValue = Random.nextFloat() * 100f // значення від 0 до 100 кВт
        emit(sensorValue)
    }
}.shareIn(
    CoroutineScope(Dispatchers.Default),
    SharingStarted.Lazily,
    replay = 1
)

// Функція, що повертає потік прогнозу потужності
fun getForecastFlow(): Flow<Float> = flow {
    while (true) {
        delay(1500) // дані прогнозу надходять кожні 1.5 сек
        val forecast = Random.nextFloat() * 50f // прогноз від 0 до 50 кВт
        emit(forecast)
    }
}.shareIn(
    CoroutineScope(Dispatchers.Default),
    SharingStarted.Lazily,
    replay = 1
)
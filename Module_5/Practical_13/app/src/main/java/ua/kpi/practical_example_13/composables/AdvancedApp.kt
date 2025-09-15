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
    var powerSensor by remember { mutableStateOf(0f) }
    var powerForecast by remember { mutableStateOf(0f) }
    var combinedPower by remember { mutableStateOf(0f) }
    var status by remember { mutableStateOf("Очікування даних...") }

    val sensorFlow = remember { getSolarPowerFlow() }
    val forecastFlow = remember { getForecastFlow() }

    LaunchedEffect(Unit) {
        // Використання combine для об'єднання двох потоків
        combine(sensorFlow, forecastFlow) { sensor, forecast ->
            sensor to forecast
        }
            .retryWhen { cause, attempt ->
                // Автоматичне повторення при помилках
                delay(1000)
                true
            }
            .collect { (sensor, forecast) ->
                powerSensor = sensor
                powerForecast = forecast
                combinedPower = sensor + forecast
                status = "Дані успішно отримані та об'єднані"
            }
    }

    Column {
        Text(text = "Просунутий рівень: Комбінування потоків та автоматичний збір")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Потужність сенсора: ${"%.2f".format(powerSensor)} кВт")
        Text(text = "Прогноз потужності: ${"%.2f".format(powerForecast)} кВт")
        Text(text = "Комбінована потужність: ${"%.2f".format(combinedPower)} кВт")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Статус: $status")
    }

}

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
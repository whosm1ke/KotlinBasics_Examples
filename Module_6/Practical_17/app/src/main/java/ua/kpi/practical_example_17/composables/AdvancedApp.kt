package ua.kpi.practical_example_17.composables

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.math.sqrt

@Composable
fun AdvancedApp(sensorManager: SensorManager, sensors: List<Sensor?>) {
    // Створюємо стан для зберігання значень сенсорів у вигляді Map з назвою сенсора та його значенням
    var sensorValues by remember { mutableStateOf(mapOf<String, Float>()) }
    // Створюємо стан для вибраних сенсорів (тільки не-null)
    var selectedSensors by remember { mutableStateOf(sensors.filterNotNull()) }
    // Створюємо стан для порогового значення сповіщення
    var threshold by remember { mutableStateOf(0f) }

    // Створюємо список SensorEventListener для кожного сенсора
    val listeners = remember {
        sensors.mapNotNull { sensor ->
            sensor?.let {
                object : SensorEventListener {
                    // Обробка зміни даних від сенсора
                    override fun onSensorChanged(event: SensorEvent?) {
                        event?.let { ev ->
                            // Визначаємо значення залежно від типу сенсора
                            val value = when (sensor.type) {
                                Sensor.TYPE_ACCELEROMETER -> sqrt(ev.values[0]*ev.values[0] + ev.values[1]*ev.values[1] + ev.values[2]*ev.values[2])
                                Sensor.TYPE_LIGHT -> ev.values[0]
                                else -> 0f
                            }
                            // Оновлюємо map через копію, щоб уникнути проблем з неерозмінністю
                            sensorValues = sensorValues.toMutableMap().apply {
                                this[sensor.name] = value
                            }
                        }
                    }

                    // Метод для обробки зміни точності сенсора (не використовується)
                    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
                }
            }
        }
    }

    // Реєстрація сенсорів через DisposableEffect для правильного управління життєвим циклом
    DisposableEffect(Unit) {
        listeners.forEachIndexed { index, listener ->
            // Реєструємо кожен слухач на відповідний сенсор
            selectedSensors.getOrNull(index)?.let { sensorManager.registerListener(listener, it, SensorManager.SENSOR_DELAY_NORMAL) }
        }
        // Відписка при знищення компонента
        onDispose { listeners.forEach { sensorManager.unregisterListener(it) } }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        // Відображення заголовка
        Text("Просунутий рівень: Моніторинг сенсорів", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        // Відображення та налаштування порогового значення через слайдер
        Text("Встановіть порогове значення для сповіщення: ${"%.2f".format(threshold)}")
        Slider(
            value = threshold,
            onValueChange = { threshold = it },
            valueRange = 0f..20f,
            steps = 20
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Відображення даних сенсорів у вигляді списку
        LazyColumn {
            sensorValues.forEach { (name, value) ->
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("$name: ${"%.2f".format(value)}")
                        // Якщо значення перевищує поріг, відображаємо попередження
                        if (value > threshold) Text("⚠", color = MaterialTheme.colorScheme.error)
                    }
                }
            }
        }
    }
}
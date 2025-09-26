package ua.kpi.practical_example_17.composables

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlin.math.sqrt

@Composable
fun MediumApp(sensorManager: SensorManager, accelerometer: Sensor?, lightSensor: Sensor?) {
    // Стан для зберігання даних акселерометра
    var accelData by remember { mutableStateOf(listOf<Float>()) }
    // Стан для зберігання даних датчика світла
    var lightData by remember { mutableStateOf(listOf<Float>()) }

    // Об'єкт для обробки подій акселерометра
    val accelListener = remember {
        object : SensorEventListener {
            // Метод викликається при зміні даних з акселерометра
            override fun onSensorChanged(event: SensorEvent?) {
                event?.let {
                    // Обчислення величини прискорення за трьома осями
                    val magnitude = sqrt(it.values[0]*it.values[0] + it.values[1]*it.values[1] + it.values[2]*it.values[2])
                    // Оновлення стану з даними акселерометра
                    accelData = listOf(magnitude)
                }
            }

            // Метод викликається при зміні точності датчика (не використовується)
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }
    }

    // Об'єкт для обробки подій датчика світла
    val lightListener = remember {
        object : SensorEventListener {
            // Метод викликається при зміні даних з датчика світла
            override fun onSensorChanged(event: SensorEvent?) {
                event?.let {
                    // Оновлення стану з даними датчика світла
                    lightData = listOf(it.values[0])
                }
            }

            // Метод викликається при зміні точності датчика (не використовується)
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }
    }

    // Реєстрація слухачів для сенсорів при старті компоненти
    DisposableEffect(Unit) {
        accelerometer?.let { sensorManager.registerListener(accelListener, it, SensorManager.SENSOR_DELAY_NORMAL) }
        lightSensor?.let { sensorManager.registerListener(lightListener, it, SensorManager.SENSOR_DELAY_NORMAL) }
        // Відписка від сенсорів при знищенні компоненти
        onDispose {
            sensorManager.unregisterListener(accelListener)
            sensorManager.unregisterListener(lightListener)
        }
    }

    Column {
        // Відображення заголовка
        Text("Середній рівень: Акселерометр + Датчик світла", style = MaterialTheme.typography.titleMedium)
        LazyColumn {
            // Відображення значення акселерометра
            item { Text("Акселерометр: ${accelData.firstOrNull() ?: 0f} m/s²") }
            // Відображення значення датчика світла
            item { Text("Датчик світла: ${lightData.firstOrNull() ?: 0f} lx") }
        }
    }
}
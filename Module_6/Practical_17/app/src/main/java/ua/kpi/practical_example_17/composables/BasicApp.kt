package ua.kpi.practical_example_17.composables

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
fun BasicApp(sensorManager: SensorManager, accelerometer: Sensor?) {
    // Створюємо стан для зберігання даних акселерометра
    var accelData by remember { mutableStateOf(listOf<Float>()) }

    // Створюємо об'єкт-слухача для акселерометра
    val sensorListener = remember {
        object : SensorEventListener {
            // Метод викликається при зміні даних сенсора
            override fun onSensorChanged(event: SensorEvent?) {
                event?.let {
                    // Обчислюємо загальну величину прискорення за трьома осями
                    val magnitude = sqrt(it.values[0]*it.values[0] + it.values[1]*it.values[1] + it.values[2]*it.values[2])
                    // Оновлюємо стан з новим значенням величини прискорення
                    accelData = listOf(magnitude)
                }
            }

            // Метод викликається при зміні точності сенсора (в даному випадку нічого не робимо)
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }
    }

    // Реєстрація слухача для акселерометра, який буде виконуватись лише під час життєвого циклу компоненти
    DisposableEffect(Unit) {
        accelerometer?.let { sensorManager.registerListener(sensorListener, it, SensorManager.SENSOR_DELAY_NORMAL) }
        // Відписка від сенсора при завершенні життєвого циклу
        onDispose { sensorManager.unregisterListener(sensorListener) }
    }

    Column {
        // Виводимо заголовок для компоненти
        Text("Базовий рівень: Акселерометр", style = MaterialTheme.typography.titleMedium)
        LazyColumn {
            // Виводимо список значень величини прискорення
            items(accelData) { value ->
                Text("Поточна величина: ${"%.2f".format(value)} m/s²")
            }
        }
    }
}
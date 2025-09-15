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
    // Стан для збереження показників акселерометра
    var accelData by remember { mutableStateOf(listOf<Float>()) }

    // Listener для акселерометра
    val sensorListener = remember {
        object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                event?.let {
                    val magnitude = sqrt(it.values[0]*it.values[0] + it.values[1]*it.values[1] + it.values[2]*it.values[2])
                    accelData = listOf(magnitude) // Зберігаємо загальну величину
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }
    }

    // Реєстрація сенсора
    DisposableEffect(Unit) {
        accelerometer?.let { sensorManager.registerListener(sensorListener, it, SensorManager.SENSOR_DELAY_NORMAL) }
        onDispose { sensorManager.unregisterListener(sensorListener) }
    }

    Column {
        Text("Базовий рівень: Акселерометр", style = MaterialTheme.typography.titleMedium)
        LazyColumn {
            items(accelData) { value ->
                Text("Поточна величина: ${"%.2f".format(value)} m/s²")
            }
        }
    }
}

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
    // Стан для акселерометра
    var accelData by remember { mutableStateOf(listOf<Float>()) }
    // Стан для датчика світла
    var lightData by remember { mutableStateOf(listOf<Float>()) }

    val accelListener = remember {
        object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                event?.let {
                    val magnitude = sqrt(it.values[0]*it.values[0] + it.values[1]*it.values[1] + it.values[2]*it.values[2])
                    accelData = listOf(magnitude)
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }
    }

    val lightListener = remember {
        object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                event?.let {
                    lightData = listOf(it.values[0])
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }
    }

    DisposableEffect(Unit) {
        accelerometer?.let { sensorManager.registerListener(accelListener, it, SensorManager.SENSOR_DELAY_NORMAL) }
        lightSensor?.let { sensorManager.registerListener(lightListener, it, SensorManager.SENSOR_DELAY_NORMAL) }
        onDispose {
            sensorManager.unregisterListener(accelListener)
            sensorManager.unregisterListener(lightListener)
        }
    }

    Column {
        Text("Середній рівень: Акселерометр + Датчик світла", style = MaterialTheme.typography.titleMedium)
        LazyColumn {
            item { Text("Акселерометр: ${accelData.firstOrNull() ?: 0f} m/s²") }
            item { Text("Датчик світла: ${lightData.firstOrNull() ?: 0f} lx") }
        }
    }
}
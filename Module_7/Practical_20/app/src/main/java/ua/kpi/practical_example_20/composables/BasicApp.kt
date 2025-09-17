package ua.kpi.practical_example_20.composables

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BasicApp(sensorManager: SensorManager, accelerometer: Sensor?) {
    // Стан для нахилу
    var tiltX by remember { mutableStateOf(0f) }
    var tiltY by remember { mutableStateOf(0f) }

    // Реєстрація слухача акселерометра
    DisposableEffect(Unit) {
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                event?.let {
                    tiltX = it.values[0] // Нахил по X
                    tiltY = it.values[1] // Нахил по Y
                }
            }
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }
        accelerometer?.also { sensorManager.registerListener(listener, it, SensorManager.SENSOR_DELAY_UI) }
        onDispose { sensorManager.unregisterListener(listener) }
    }

    // UI — кружок, який рухається відповідно до нахилу
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFEFEF)),
        contentAlignment = Alignment.Center
    ) {
        val animatedX by animateFloatAsState(targetValue = tiltX * 20) // масштабування для наочності
        val animatedY by animateFloatAsState(targetValue = tiltY * 20)

        Box(
            modifier = Modifier
                .offset(x = animatedX.dp, y = (-animatedY).dp)
                .size(50.dp)
                .background(Color.Red, shape = CircleShape)
        )
    }
}
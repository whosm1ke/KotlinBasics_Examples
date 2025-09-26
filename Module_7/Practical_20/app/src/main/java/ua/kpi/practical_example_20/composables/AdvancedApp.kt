package ua.kpi.practical_example_20.composables

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.math.atan2


@Composable
fun AdvancedApp(sensorManager: SensorManager, accelerometer: Sensor?, gyroscope: Sensor?) {
    // Створюємо змінні для зберігання значень акселерометра та гіроскопа
    var accelX by remember { mutableStateOf(0f) }
    var accelY by remember { mutableStateOf(0f) }
    var gyroZ by remember { mutableStateOf(0f) }

    // Реєстрація SensorEventListener для отримання даних з сенсорів
    DisposableEffect(Unit) {
        val listener = object : SensorEventListener {
            // Метод викликається при зміні даних сенсора
            override fun onSensorChanged(event: SensorEvent?) {
                event?.let {
                    when (event.sensor.type) {
                        // Обробка даних акселерометра
                        Sensor.TYPE_ACCELEROMETER -> {
                            accelX = it.values[0]
                            accelY = it.values[1]
                        }
                        // Обробка даних гіроскопа
                        Sensor.TYPE_GYROSCOPE -> {
                            gyroZ = it.values[2]
                        }
                    }
                }
            }
            // Метод, який викликається при зміні точності сенсора (не використовується)
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }
        // Реєстрація слухача для акселерометра, якщо він існує
        accelerometer?.also { sensorManager.registerListener(listener, it, SensorManager.SENSOR_DELAY_UI) }
        // Реєстрація слухача для гіроскопа, якщо він існує
        gyroscope?.also { sensorManager.registerListener(listener, it, SensorManager.SENSOR_DELAY_UI) }
        // Відписка від сенсорів при завершенні компоненти
        onDispose { sensorManager.unregisterListener(listener) }
    }

    // Анімація зміни значень акселерометра та гіроскопа для плавного відображення
    val animatedX by animateFloatAsState(targetValue = accelX)
    val animatedY by animateFloatAsState(targetValue = accelY)
    val animatedGyro by animateFloatAsState(targetValue = gyroZ)

    // Основна колонка для розміщення елементів
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Відображення значень акселерометра
        Text("Accelerometer X: %.2f, Y: %.2f".format(animatedX, animatedY))
        // Відображення значення гіроскопа
        Text("Gyroscope Z: %.2f".format(animatedGyro))
        Spacer(modifier = Modifier.height(32.dp))
        // Візуалізація векторів на Canvas
        Canvas(modifier = Modifier.size(200.dp)) {
            val center = Offset(size.width/2, size.height/2)
            val radius = 80f
            // Малювання вектора акселерометра (синій колір)
            drawLine(
                color = Color.Blue,
                start = center,
                end = Offset(center.x + animatedX*10, center.y - animatedY*10),
                strokeWidth = 5f
            )
            // Малювання вектора гіроскопа (червоний колір)
            val angle = atan2(animatedGyro, 1f)
            drawLine(
                color = Color.Red,
                start = center,
                end = Offset(center.x + radius * kotlin.math.cos(angle), center.y + radius * kotlin.math.sin(angle)),
                strokeWidth = 5f
            )
        }
    }
}
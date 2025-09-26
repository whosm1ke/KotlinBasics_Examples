package ua.kpi.practical_example_20.composables

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.math.sqrt

@Composable
fun MediumApp(sensorManager: SensorManager, accelerometer: Sensor?) {
    // Створюємо стан для зберігання значення нахилу пристрою
    var tilt by remember { mutableStateOf(0f) }

    // Реєстрація слухача акселерометра для отримання даних про рух пристрою
    DisposableEffect(Unit) {
        val listener = object : SensorEventListener {
            // Метод викликається при зміні даних з сенсора
            override fun onSensorChanged(event: SensorEvent?) {
                event?.let {
                    // Обчислення магнітуди нахилу за двома осями (X та Y)
                    tilt = sqrt(it.values[0]*it.values[0] + it.values[1]*it.values[1])
                }
            }
            // Метод викликається при зміні точності сенсора (в даному випадку не використовується)
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }
        // Реєстрація слухача для акселерометра з визначеним інтервалом оновлення
        accelerometer?.also { sensorManager.registerListener(listener, it, SensorManager.SENSOR_DELAY_UI) }
        // Деструктор для відписки від сенсора при завершенні життєвого циклу Composable
        onDispose { sensorManager.unregisterListener(listener) }
    }

    // Анимація зміни значення нахилу для плавного відображення змін
    val animatedTilt by animateFloatAsState(
        targetValue = tilt,
        animationSpec = tween(durationMillis = 150) // Час анімації 150 мілісекунд
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Виведення значення нахилу у вигляді тексту
        Text("Tilt magnitude: %.2f".format(animatedTilt), style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(32.dp))
        Canvas(modifier = Modifier.size(200.dp)) {
            // Обчислення кута для відображення на графічному елементі
            val sweepAngle = (animatedTilt * 10 / 5f).coerceIn(0f, 360f)
            drawArc(
                color = Color.Green,
                startAngle = -90f,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 20f)
            )
        }
    }
}
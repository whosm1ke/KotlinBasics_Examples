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
    // Створення змінних стану для зберігання значень нахилу по осям X та Y
    var tiltX by remember { mutableStateOf(0f) }
    var tiltY by remember { mutableStateOf(0f) }

    // Реєстрація SensorEventListener для отримання даних з акселерометра
    DisposableEffect(Unit) {
        val listener = object : SensorEventListener {
            // Викликається при зміні даних з сенсора
            override fun onSensorChanged(event: SensorEvent?) {
                event?.let {
                    // Отримання значень нахилу по осях X та Y з події сенсора
                    tiltX = it.values[0] // Нахил по осі X
                    tiltY = it.values[1] // Нахил по осі Y
                }
            }
            // Викликається при зміні точності сенсора (в даному випадку не використовується)
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }
        // Реєстрація слухача для акселерометра з встановленням затримки вищезгаданого типу
        accelerometer?.also { sensorManager.registerListener(listener, it, SensorManager.SENSOR_DELAY_UI) }
        // Відписка від слухача при завершенні життєвого циклу компоненти
        onDispose { sensorManager.unregisterListener(listener) }
    }

    // Візуалізація кружка, який рухається відповідно до нахилу пристрою
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFEFEF)), // Налаштування фону
        contentAlignment = Alignment.Center // Вирівнювання вмісту по центру
    ) {
        // Анімація зміни позиції кружка по осях X та Y для плавного переміщення
        val animatedX by animateFloatAsState(targetValue = tiltX * 20) // Масштабування для більшої видимості нахилу
        val animatedY by animateFloatAsState(targetValue = tiltY * 20)

        Box(
            modifier = Modifier
                .offset(x = animatedX.dp, y = (-animatedY).dp) // Застосування зміщення відповідно до нахилу
                .size(50.dp)
                .background(Color.Red, shape = CircleShape) // Встановлення кольору та форми кружка
        )
    }
}
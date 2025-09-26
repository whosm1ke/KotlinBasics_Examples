package ua.kpi.practical_example_26.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AnimatedResourceBar(name: String, value: Float, color: Color) {
    // Анімація кольору залежно від рівня значення ресурсу
    val animatedColor by animateColorAsState(
        targetValue = when {
            value < 50f -> Color.Green  // Якщо значення менше 50, колір зелений
            value < 80f -> Color.Yellow // Якщо значення менше 80, колір жовтий
            else -> Color.Red            // У інших випадках — червоний
        }
    )

    Column {
        // Відображення назви ресурсу та його значення у відсотках
        Text("$name: ${value.toInt()}%", fontSize = 16.sp)
        
        // Лінійний індикатор прогресу з анімованим кольором
        LinearProgressIndicator(
            progress = value / 100f,             // Прогрес у діапазоні від 0 до 1
            color = animatedColor,                // Анімований колір залежно від значення
            modifier = Modifier
                .fillMaxWidth()                   // Заповнює всю доступну ширину
                .height(20.dp)                    // Встановлює висоту індикатора
                .clip(RoundedCornerShape(10.dp))  // Закруглені кути
        )
    }
}
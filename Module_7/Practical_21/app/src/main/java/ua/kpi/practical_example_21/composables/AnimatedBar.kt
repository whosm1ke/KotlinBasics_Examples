package ua.kpi.practical_example_21.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedBar(label: String, value: Float, color: Color) {
    // Анимированное изменение значения для плавного перехода
    val animatedValue by animateFloatAsState(targetValue = value)
    
    // Анимированное изменение цвета в зависимости от значения
    val animatedColor by animateColorAsState(
        targetValue = when {
            value > 75 -> Color.Green  // Если значение больше 75, цвет зеленый
            value > 40 -> Color.Yellow // Если значение больше 40, но меньше или равно 75, цвет желтый
            else -> Color.Red          // Иначе цвет красный
        }
    )

    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        // Отображение метки и текущего значения
        Text(text = "$label: ${value.toInt()}")

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .background(Color.LightGray, RoundedCornerShape(8.dp)) // Фон полосы с закругленными углами
        ) {
            val maxWidthPx = maxWidth  // Получаем максимальную ширину контейнера
            
            // Анимированная полоса, чья ширина зависит от анимированного значения
            Box(
                modifier = Modifier
                    .width(maxWidthPx * (animatedValue / 100))  // Ширина пропорциональна значению (в процентах)
                    .fillMaxHeight()
                    .background(animatedColor, RoundedCornerShape(8.dp)) // Цвет полосы зависит от значения
            )
        }
    }
}
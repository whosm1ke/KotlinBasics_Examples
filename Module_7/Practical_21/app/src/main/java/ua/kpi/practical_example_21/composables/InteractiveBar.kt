package ua.kpi.practical_example_21.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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

// Інтерактивна анімована шкала для просунутого рівня
@Composable
fun InteractiveBar(label: String, value: Float, color: Color, onValueChange: (Float) -> Unit) {
    // Анімація значення шкали для плавного переходу між значеннями
    val animatedValue by animateFloatAsState(targetValue = value)
    
    // Анімація кольору залежно від поточного значення шкали
    val animatedColor by animateColorAsState(
        targetValue = when {
            value > 75 -> Color.Green   // Якщо значення більше 75 — зелений колір
            value > 40 -> Color.Yellow  // Якщо значення більше 40 — жовтий колір
            else -> Color.Red           // Інакше — червоний колір
        }
    )

    Column(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clickable {
                // Симуляція взаємодії: підвищує значення на 10 при натисканні
                // Використовується коерція значення в діапазон [0, 100]
                onValueChange((value + 10f).coerceIn(0f, 100f))
            }
    ) {
        // Відображення мітки та поточного значення шкали
        Text(text = "$label: ${value.toInt()}")

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .height(28.dp)
                .background(Color.LightGray, RoundedCornerShape(12.dp))  // Фон шкали з закругленими кутами
        ) {
            val maxWidthPx = maxWidth  // Отримання максимальної ширини контейнера
            
            // Відображення анімованої частини шкали залежно від значення
            Box(
                modifier = Modifier
                    .width(maxWidthPx * (animatedValue / 100))  // Розрахунок ширини анімованої частини
                    .fillMaxHeight()
                    .background(animatedColor, RoundedCornerShape(12.dp))  // Кольорова анімація
            )
        }
    }
}
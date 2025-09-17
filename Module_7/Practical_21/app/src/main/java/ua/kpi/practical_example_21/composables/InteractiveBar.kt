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
    val animatedValue by animateFloatAsState(targetValue = value)
    val animatedColor by animateColorAsState(
        targetValue = when {
            value > 75 -> Color.Green
            value > 40 -> Color.Yellow
            else -> Color.Red
        }
    )

    Column(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .clickable {
                // Симуляція взаємодії: підвищує значення на 10 при натисканні
                onValueChange((value + 10f).coerceIn(0f, 100f))
            }
    ) {
        Text(text = "$label: ${value.toInt()}")

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .height(28.dp)
                .background(Color.LightGray, RoundedCornerShape(12.dp))
        ) {
            val maxWidthPx = maxWidth
            Box(
                modifier = Modifier
                    .width(maxWidthPx * (animatedValue / 100))
                    .fillMaxHeight()
                    .background(animatedColor, RoundedCornerShape(12.dp))
            )
        }
    }
}
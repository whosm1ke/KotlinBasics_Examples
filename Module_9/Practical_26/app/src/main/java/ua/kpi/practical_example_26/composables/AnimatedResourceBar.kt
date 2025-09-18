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
    // Анімація кольору залежно від рівня
    val animatedColor by animateColorAsState(
        targetValue = when {
            value < 50f -> Color.Green
            value < 80f -> Color.Yellow
            else -> Color.Red
        }
    )

    Column {
        Text("$name: ${value.toInt()}%", fontSize = 16.sp)
        LinearProgressIndicator(
            progress = value / 100f,
            color = animatedColor,
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .clip(RoundedCornerShape(10.dp))
        )
    }
}


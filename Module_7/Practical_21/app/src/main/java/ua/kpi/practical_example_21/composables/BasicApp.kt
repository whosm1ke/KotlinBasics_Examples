package ua.kpi.practical_example_21.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BasicApp() {
    // Стан поточної потужності сонячної електростанції
    var power by remember { mutableStateOf(50f) } // Від 0 до 100
    val animatedPower by animateFloatAsState(targetValue = power) // Плавна анімація

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Сонячна електростанція: потужність",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Прогрес-бар, що анімовано змінює ширину
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .background(Color.LightGray, RoundedCornerShape(8.dp))
        ) {
            val maxWidthPx = maxWidth // ширина контейнера
            Box(
                modifier = Modifier
                    .width(maxWidthPx * (animatedPower / 100)) // ширина пропорційна потужності
                    .fillMaxHeight()
                    .background(Color.Yellow, RoundedCornerShape(8.dp))
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Слайдер для зміни значення потужності
        Slider(
            value = power,
            onValueChange = { power = it },
            valueRange = 0f..100f,
            colors = SliderDefaults.colors(
                thumbColor = Color.Yellow,
                activeTrackColor = Color.Yellow
            )
        )
        Text(text = "${power.toInt()} кВт")
    }
}
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
    
    // Анімація зміни значення потужності для плавного переходу
    val animatedPower by animateFloatAsState(targetValue = power)

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Відображення назви додатку або сектора
        Text(
            text = "Сонячна електростанція: потужність",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp)) // Пропуск між елементами

        // Контейнер для прогрес-бара з визначенням максимальних розмірів
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth() // Займає всю ширину
                .height(30.dp) // Висота 30 dp
                .background(Color.LightGray, RoundedCornerShape(8.dp)) // Сірий фон з закругленими кутами
        ) {
            val maxWidthPx = maxWidth // Отримання максимальної ширини контейнера
            
            // Прогрес-бар, що показує поточну потужність
            Box(
                modifier = Modifier
                    .width(maxWidthPx * (animatedPower / 100)) // Ширина пропорційна значенню потужності
                    .fillMaxHeight() // Займає всю висоту контейнера
                    .background(Color.Yellow, RoundedCornerShape(8.dp)) // Жовтий колір з закругленими кутами
            )
        }

        Spacer(modifier = Modifier.height(16.dp)) // Пропуск між елементами

        // Слайдер для встановлення нового значення потужності
        Slider(
            value = power, // Поточне значення
            onValueChange = { power = it }, // Функція, що викликається при зміні значення
            valueRange = 0f..100f, // Діапазон від 0 до 100
            colors = SliderDefaults.colors(
                thumbColor = Color.Yellow, // Колір повзунка
                activeTrackColor = Color.Yellow // Колір активної частини слайдера
            )
        )
        
        // Відображення поточного значення потужності у кВт
        Text(text = "${power.toInt()} кВт")
    }
}
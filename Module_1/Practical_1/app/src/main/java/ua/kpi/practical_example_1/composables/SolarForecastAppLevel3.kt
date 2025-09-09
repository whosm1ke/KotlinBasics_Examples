package ua.kpi.practical_example_1.composables

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Composable
fun SolarForecastAppLevel3() {
    // State для збереження потужності
    var powerForecast by remember { mutableStateOf(0) }

    // Анімація зміни значення потужності
    val animatedPower by animateIntAsState(
        targetValue = powerForecast,
        label = "PowerAnimation"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Заголовок
        Text(
            text = "Сонячна електростанція",
            fontSize = 26.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Текст із плавною анімацією числа
        Text(
            text = "Прогноз потужності: $animatedPower Вт",
            fontSize = 22.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Кнопка MaterialTheme
        Button(onClick = {
            // При натисканні оновлюємо випадковим числом
            powerForecast = Random.nextInt(100, 5000)
        }) {
            Text("Оновити прогноз")
        }
    }
}
package ua.kpi.practical_example_1.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Composable
fun SolarForecastAppLevel2() {
    // Оголошуємо змінну стану для зберігання прогнозу потужності
    var powerForecast by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFDE7)) // Встановлюємо жовтуватий фон для імітації сонячного світла
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Відображаємо заголовок додатку
        Text(
            text = "Сонячна електростанція",
            fontSize = 24.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Відображаємо поточний прогноз потужності у ватах
        Text(
            text = "Прогноз потужності: $powerForecast Вт",
            fontSize = 20.sp,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Кнопка для оновлення прогнозу
        Button(onClick = {
            // Генеруємо нове випадкове значення потужності у діапазоні 100–5000 Вт
            powerForecast = Random.nextInt(100, 5000)
        }) {
            Text("Оновити прогноз")
        }
    }
}
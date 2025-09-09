package ua.kpi.practical_example_1.composables

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Composable
fun SolarStationApp() {
    // Основний показник: прогноз потужності у Ватах
    var powerForecast by remember { mutableStateOf(0) }

    // Додатковий показник: стан акумулятора у %
    var batteryLevel by remember { mutableStateOf(0) }

    // Використовуємо Column для вертикального розташування
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Заголовок екрану
        Text(
            text = "Моніторинг сонячної електростанції",
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Основна інформація — прогноз потужності
        Text(
            text = "Прогноз потужності: $powerForecast Вт",
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Додаткова інформація — стан акумулятора
        Text(
            text = "Стан акумулятора: $batteryLevel %",
            fontSize = 18.sp,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Кнопка для оновлення значень
        Button(onClick = {
            // Симуляція нового прогнозу: випадкове число 100–5000 Вт
            powerForecast = Random.nextInt(100, 5000)

            // Симуляція заряду акумулятора: випадкове число 20–100 %
            batteryLevel = Random.nextInt(20, 100)
        }) {
            Text("Оновити дані")
        }
    }
}
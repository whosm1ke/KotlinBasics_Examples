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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SolarForecastAppLevel1() {
    // Column розташовує елементи вертикально
    Column(
        modifier = Modifier
            .fillMaxSize()               // заповнити весь екран
            .background(Color(0xFFE3F2FD)) // встановлюємо простий колір фону (світло-блакитний)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Текст із заголовком
        Text(
            text = "Hello Solar Power",
            fontSize = 28.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Кнопка "Оновити прогноз" (поки що без логіки)
        Button(onClick = { /* натискання кнопки ще нічого не робить */ }) {
            Text("Оновити прогноз")
        }
    }
}
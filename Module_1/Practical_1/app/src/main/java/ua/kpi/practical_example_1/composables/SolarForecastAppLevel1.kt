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
    // Column розташовує елементи вертикально у центрі екрана
    Column(
        modifier = Modifier
            .fillMaxSize()               // заповнити весь доступний простір екрана
            .background(Color(0xFFE3F2FD)) // встановлюємо світло-блакитний колір фону
            .padding(16.dp),              // додаємо внутрішній відступ 16 dp
        horizontalAlignment = Alignment.CenterHorizontally, // вирівнювання по горизонталі по центру
        verticalArrangement = Arrangement.Center   // вирівнювання по вертикалі по центру
    ) {
        // Відображаємо текст із заголовком додатку
        Text(
            text = "Hello Solar Power",     // текст, що відображається
            fontSize = 28.sp,               // розмір шрифту 28 пікселів
            color = Color.Black             // колір тексту чорний
        )

        Spacer(modifier = Modifier.height(24.dp)) // додаємо простір між елементами (24 dp)

        // Кнопка для оновлення прогнозу сонячної енергії
        Button(onClick = { /* натискання кнопки ще нічого не робить */ }) {
            Text("Оновити прогноз")  // текст на кнопці
        }
    }
}
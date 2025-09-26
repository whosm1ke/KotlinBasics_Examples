package ua.kpi.practical_example_27.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_27.viewModel.ForecastViewModel

@Composable
fun AnalyticsScreen(viewModel: ForecastViewModel) {
    // Отримуємо дані про прогноз зараз з ViewModel у вигляді стану Composable
    val forecast by viewModel.todayForecast.collectAsState()
    
    // Використовуємо Column для вертикального розміщення елементів
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Відображаємо заголовок секції "Аналітика"
        Text("Аналітика", style = MaterialTheme.typography.titleMedium)
        
        // Додаємо простір між заголовком і даними
        Spacer(modifier = Modifier.height(8.dp))
        
        // Проходимо по кожному елементу прогнозу для відображення бар-чарту
        forecast.forEach { item ->
            // Використовуємо Row для горизонтального розміщення часу, графіка та потужності
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 4.dp)) {
                // Відображаємо час (наприклад, "09:00")
                Text(item.time, modifier = Modifier.width(50.dp))
                
                // Створюємо бар-графік (відповідно до потужності)
                Box(modifier = Modifier
                    .height(20.dp)
                    .width((item.powerKW / 10).dp)  // Ширина бара залежить від значення powerKW
                    .background(Color(0xFFFFC107))  // Потужний жовтий колір для бару
                )
                
                // Додаємо простір між графіком і значенням потужності
                Spacer(modifier = Modifier.width(8.dp))
                
                // Відображаємо значення потужності у кВт
                Text("${item.powerKW} kW")
            }
        }
    }
}
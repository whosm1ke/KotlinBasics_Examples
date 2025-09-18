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
    val forecast by viewModel.todayForecast.collectAsState()
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Аналітика", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        // Простий бар-чарт (симуляція)
        forecast.forEach { item ->
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 4.dp)) {
                Text(item.time, modifier = Modifier.width(50.dp))
                Box(modifier = Modifier
                    .height(20.dp)
                    .width((item.powerKW / 10).dp)
                    .background(Color(0xFFFFC107))
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("${item.powerKW} kW")
            }
        }
    }
}
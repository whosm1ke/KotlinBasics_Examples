package ua.kpi.practical_example_27.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_27.components.ForecastItem
import ua.kpi.practical_example_27.viewModel.ForecastViewModel

@Composable
fun ForecastScreen(viewModel: ForecastViewModel) {
    val forecast by viewModel.todayForecast.collectAsState()
    val context = LocalContext.current // Отримуємо контекст для Toast

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Прогноз потужності на сьогодні", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(forecast) { item ->
                ForecastItem(item)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            viewModel.sendNotification { message ->
                // Відображаємо Toast із повідомленням
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Оновити прогноз (сповіщення)")
        }
    }
}

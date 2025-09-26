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
    // Отримуємо дані прогнозу на сьогодні з ViewModel у вигляді стану
    val forecast by viewModel.todayForecast.collectAsState()
    // Отримуємо локальний контекст для відображення Toast-повідомлень
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Відображаємо заголовок екрану прогнозу
        Text("Прогноз потужності на сьогодні", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        // Створюємо список прогнозів за допомогою LazyColumn
        LazyColumn {
            items(forecast) { item ->
                // Відображаємо кожен елемент прогнозу за допомогою компонента ForecastItem
                ForecastItem(item)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Кнопка для оновлення прогнозу з відображенням сповіщення через Toast
        Button(onClick = {
            viewModel.sendNotification { message ->
                // Відображаємо Toast із отриманим повідомленням
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Оновити прогноз (сповіщення)")
        }
    }
}
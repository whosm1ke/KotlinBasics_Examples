package ua.kpi.practical_example_27.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_27.components.ForecastItem
import ua.kpi.practical_example_27.viewModel.ForecastViewModel

@Composable
fun HistoryScreen(viewModel: ForecastViewModel) {
    // Отримуємо стан історії прогнозів з ViewModel за допомогою collectAsState
    val history by viewModel.historicalForecast.collectAsState()
    
    // Використовуємо Column для вертикального розміщення елементів
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Відображаємо заголовок секції "Історія прогнозів"
        Text("Історія прогнозів", style = MaterialTheme.typography.titleMedium)
        
        // Додаємо простір між заголовком і списком
        Spacer(modifier = Modifier.height(8.dp))
        
        // Створюємо LazyColumn для ефективного відображення списку прогнозів
        LazyColumn {
            // Використовуємо items для відображення кожного елемента історії
            items(history) { item ->
                // Відображаємо кожен елемент історії за допомогою компонента ForecastItem
                ForecastItem(item)
            }
        }
    }
}
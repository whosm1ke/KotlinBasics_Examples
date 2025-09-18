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
    val history by viewModel.historicalForecast.collectAsState()
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Історія прогнозів", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(history) { item ->
                ForecastItem(item)
            }
        }
    }
}

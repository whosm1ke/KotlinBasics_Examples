package ua.kpi.practical_example_27.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_27.viewModel.ForecastViewModel

@Composable
fun SettingsScreen(viewModel: ForecastViewModel) {
    val context = LocalContext.current // Отримуємо контекст для Toast

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Налаштування та експорт", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            // Викликаємо фейковий метод експорту CSV
            viewModel.exportCSV { message ->
                // Показуємо Toast з повідомленням користувачу
                // У повідомленні зазначено, що файл фактично не згенеровано
                Toast.makeText(
                    context,
                    "$message (Файл фактично не створено, це симуляція)",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }) {
            Text("Експорт прогнозів у CSV")
        }
    }
}

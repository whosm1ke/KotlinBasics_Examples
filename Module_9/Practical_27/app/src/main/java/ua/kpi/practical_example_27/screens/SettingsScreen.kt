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
    // Отримуємо поточний контекст для відображення Toast-повідомлень
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Відображаємо заголовок екрану налаштувань
        Text("Налаштування та експорт", style = MaterialTheme.typography.titleMedium)
        
        // Додаємо простір між заголовком і кнопкою
        Spacer(modifier = Modifier.height(16.dp))
        
        // Кнопка для експорту даних у форматі CSV
        Button(onClick = {
            // Викликаємо метод експорту CSV з ViewModel
            // Передаємо анонімну функцію як callback-функцію для обробки результату
            viewModel.exportCSV { message ->
                // Показуємо Toast-повідомлення користувачеві з отриманим повідомленням
                // Додаємо пояснення, що файл не був фактично створений (це симуляція)
                Toast.makeText(
                    context,
                    "$message (Файл фактично не створено, це симуляція)",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }) {
            // Текст на кнопці для експорту
            Text("Експорт прогнозів у CSV")
        }
    }
}
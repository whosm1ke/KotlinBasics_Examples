package ua.kpi.practical_example_27

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.kpi.practical_example_27.ui.theme.Practical_Example_27Theme
import ua.kpi.practical_example_27.viewModel.ForecastViewModel

class MainActivity : ComponentActivity() {
    // Метод, який викликається під час створення активності
    override fun onCreate(savedInstanceState: Bundle?) {
        // Виклик базової реалізації методу для правильного ініціалізації активності
        super.onCreate(savedInstanceState)
        
        // Налаштування контенту Compose
        setContent {
            // Отримання екземпляра ViewModel через функцію viewModel(), яка автоматично
            // створює або повертає існуючий екземпляр ForecastViewModel
            val viewModel: ForecastViewModel = viewModel()
            
            // Встановлення теми для компонентів Compose
            Practical_Example_27Theme {
                // Створення Scaffold, який надає базову структуру для відображення контенту
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Відображення головного компонента SolarForecastApp з передачею ViewModel
                    SolarForecastApp(viewModel)
                }
            }
        }
    }
}
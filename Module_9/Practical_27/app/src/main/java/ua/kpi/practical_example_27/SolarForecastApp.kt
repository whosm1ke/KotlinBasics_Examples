package ua.kpi.practical_example_27

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import ua.kpi.practical_example_27.screens.LoginScreen
import ua.kpi.practical_example_27.screens.MainMenuScreen
import ua.kpi.practical_example_27.viewModel.ForecastViewModel

/**
 * Головна компонента додатку для прогнозу сонячної енергії.
 * Визначає, який екран відображати залежно від стану авторизації користувача.
 *
 * @param viewModel Екземпляр ForecastViewModel, що містить логіку для управління станом додатку.
 */
@Composable
fun SolarForecastApp(viewModel: ForecastViewModel) {
    // Отримуємо поточний стан авторизації з ViewModel за допомогою collectAsState
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()
    
    // Встановлюємо поверхню для відображення вмісту додатку з заповненням всього доступного простору
    Surface(modifier = Modifier.fillMaxSize()) {
        // Перевіряємо, чи користувач увійшов у систему
        if (!isLoggedIn) {
            // Якщо користувач не авторизований, відображаємо екран входу
            LoginScreen(viewModel)
        } else {
            // Якщо користувач авторизований, відображаємо головне меню
            MainMenuScreen(viewModel)
        }
    }
}
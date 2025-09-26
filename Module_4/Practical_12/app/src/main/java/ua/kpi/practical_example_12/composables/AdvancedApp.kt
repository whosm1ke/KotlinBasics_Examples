package ua.kpi.practical_example_12.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_12.advanced.ForecastViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdvancedApp() {
    // Отримуємо поточний контекст застосунку для ініціалізації ViewModel
    val context = LocalContext.current
    
    // Створюємо екземпляр ViewModel з використанням remember, щоб уникнути повторного створення
    val viewModel = remember { ForecastViewModel(context) }
    
    // Створюємо стан для управління Snackbar хостом, який відображає повідомлення про помилки
    val snackbarHostState = remember { SnackbarHostState() }

    // Ефект, який спрацьовує при зміні errorMessage у ViewModel
    // Якщо помилка існує, відображається Snackbar з текстом помилки
    LaunchedEffect(viewModel.errorMessage) {
        viewModel.errorMessage?.let { snackbarHostState.showSnackbar(it) }
    }

    Scaffold(
        // Встановлюємо Snackbar хост для відображення повідомлень
        snackbarHost = { SnackbarHost(snackbarHostState) },
        
        // Визначаємо верхню панель (TopAppBar) з назвою додатку
        topBar = { TopAppBar(title = { Text("Сонячна електростанція - Advanced") }) }
    ) { padding ->
        Column(
            // Налаштовуємо макет для вмісту з використанням padding, щоб уникнути перекриття з панеллю
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            
            // Вирівнювання елементів по горизонталі в центрі
            horizontalAlignment = Alignment.CenterHorizontally,
            
            // Вирівнювання елементів по вертикалі в центрі
            verticalArrangement = Arrangement.Center
        ) {

            // Кнопка для отримання прогнозу з повторними спробами (retry)
            Button(onClick = { viewModel.fetchForecastWithRetry() }) {
                Text("Отримати прогноз на сьогодні (з retry)")
            }

            // Пропуск простору між кнопками
            Spacer(modifier = Modifier.height(16.dp))

            // Кнопка для симуляції помилки сервера з кодом 500
            Button(onClick = { viewModel.fetchError(500) }) {
                Text("Симулювати помилку 500")
            }

            // Пропуск простору перед відображенням даних
            Spacer(modifier = Modifier.height(20.dp))

            // Якщо прогноз існує, відображаємо дані про прогноз
            viewModel.forecast?.let { f ->
                Text("Дата: ${f.date}")
                Text("Потужність: ${f.powerKwh} кВт·год")
                Text("Статус: ${f.status}")
            }
        }
    }
}
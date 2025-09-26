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
import ua.kpi.practical_example_12.medium.ForecastViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediumApp() {
    // Отримання контексту для використання в ViewModel
    val context = LocalContext.current
    
    // Створення екземпляра ViewModel з використанням remember для збереження стану
    val viewModel = remember { ForecastViewModel() }
    
    // Створення стану для SnackbarHost, який буде відображати повідомлення про помилки
    val snackbarHostState = remember { SnackbarHostState() }

    // Ефект, що спрацьовує при зміні errorMessage у ViewModel
    // Якщо помилка існує, відображається Snackbar з текстом помилки
    LaunchedEffect(viewModel.errorMessage) {
        viewModel.errorMessage?.let { snackbarHostState.showSnackbar(it) }
    }

    Scaffold(
        // Встановлення SnackbarHost для показу повідомлень
        snackbarHost = { SnackbarHost(snackbarHostState) },
        
        // Верхня панель застосунку з назвою
        topBar = { TopAppBar(title = { Text("Сонячна електростанція") }) }
    ) { padding ->
        Column(
            // Встановлення модифікаторів для колонки: заповнення всього простору, 
            // внутрішній відступ і вирівнювання по центру
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Кнопка для отримання прогнозу погоди
            Button(onClick = { viewModel.fetchForecast(context) }) {
                Text("Отримати прогноз на сьогодні")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Кнопка для симуляції помилки 404
            Button(onClick = { viewModel.fetchError(404, context) }) {
                Text("Симулювати помилку 404")
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Відображення даних прогнозу, якщо вони існують
            viewModel.forecast?.let { f ->
                Text("Дата: ${f.date}")
                Text("Потужність: ${f.powerKwh} кВт·год")
                Text("Статус: ${f.status}")
            }
        }
    }
}
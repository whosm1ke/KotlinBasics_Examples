package ua.kpi.practical_example_7.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.kpi.practical_example_7.viewModels.AdvancedViewModel

@Composable
fun AdvancedApp(viewModel: AdvancedViewModel = viewModel()) {
    // Отримуємо значення потужності з ViewModel та автоматично оновлюємо компонент при зміні даних
    val power by viewModel.power.collectAsState()
    
    // Отримуємо статус з ViewModel та автоматично оновлюємо компонент при зміні даних
    val status by viewModel.status.collectAsState()
    
    // Отримуємо стан завантаження з ViewModel та автоматично оновлюємо компонент при зміні даних
    val isLoading by viewModel.isLoading.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        // Відображаємо заголовок для компонента
        Text("Прогноз потужності сонячної станції (Просунутий рівень)")
        
        // Додаємо простір між елементами
        Spacer(modifier = Modifier.height(8.dp))

        // Перевіряємо, чи триває завантаження даних
        if (isLoading) {
            // Якщо дані завантажуються, відображаємо повідомлення
            Text("Завантаження даних...")
        } else {
            // Якщо дані завантажені, відображаємо поточну потужність та статус
            Text("Поточна потужність: $power кВт")
            Text("Статус: $status")
        }

        // Додаємо простір між елементами
        Spacer(modifier = Modifier.height(16.dp))
        
        // Кнопка для оновлення даних потужності
        Button(onClick = { viewModel.refreshPower() }) {
            Text("Оновити потужність")
        }
    }
}
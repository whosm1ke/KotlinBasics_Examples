package ua.kpi.practical_example_7.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.kpi.practical_example_7.viewModels.MediumViewModel

@Composable
fun MediumApp(viewModel: MediumViewModel = viewModel()) {
    // Отримуємо поточну потужність з ViewModel за допомогою collectAsState
    val power by viewModel.currentPower.collectAsState()
    
    // Отримуємо статус з ViewModel за допомогою collectAsState
    val status by viewModel.status.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        // Відображаємо прогноз потужності сонячної станції
        Text("Прогноз потужності сонячної станції (Середній рівень): $power кВт")
        
        // Відображаємо статус станції
        Text("Статус: $status")
        
        // Додаємо простір між елементами
        Spacer(modifier = Modifier.height(16.dp))
        
        // Кнопка для оновлення потужності випадковим значенням від 0 до 100
        Button(onClick = { viewModel.updatePower((0..100).random()) }) {
            Text("Оновити потужність")
        }
    }
}
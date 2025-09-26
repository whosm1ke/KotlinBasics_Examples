package ua.kpi.practical_example_7.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.kpi.practical_example_7.viewModels.BasicViewModel

@Composable
fun BasicApp(viewModel: BasicViewModel = viewModel()) {
    // Отримуємо поточне значення power з ViewModel за допомогою collectAsState
    val power by viewModel.power.collectAsState()

    Column( modifier = Modifier.padding(16.dp)) {
        // Відображаємо текст з поточним значенням потужності
        Text("Прогноз потужності сонячної станції (Базовий рівень): $power кВт")
        // Додаємо простір між елементами
        Spacer(modifier = Modifier.height(16.dp))
        // Кнопка для оновлення потужності випадковим значенням від 0 до 100
        Button(onClick = { viewModel.updatePower((0..100).random()) }) {
            Text("Оновити потужність")
        }
    }
}
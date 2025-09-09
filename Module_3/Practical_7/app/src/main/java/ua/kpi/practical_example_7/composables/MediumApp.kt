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
    val power by viewModel.currentPower.collectAsState()
    val status by viewModel.status.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Прогноз потужності сонячної станції (Середній рівень): $power кВт")
        Text("Статус: $status")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.updatePower((0..100).random()) }) {
            Text("Оновити потужність")
        }
    }
}
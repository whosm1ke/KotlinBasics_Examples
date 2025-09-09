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
    val power by viewModel.power.collectAsState()
    val status by viewModel.status.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Прогноз потужності сонячної станції (Просунутий рівень)")
        Spacer(modifier = Modifier.height(8.dp))

        if (isLoading) {
            Text("Завантаження даних...")
        } else {
            Text("Поточна потужність: $power кВт")
            Text("Статус: $status")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.refreshPower() }) {
            Text("Оновити потужність")
        }
    }
}
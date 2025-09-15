package ua.kpi.practical_example_15.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.kpi.practical_example_15.viewModels.SolarStationViewModel

@Composable
fun MediumApp(viewModel: SolarStationViewModel = viewModel()) {
    Column {
        // --- Пошук ---
        OutlinedTextField(
            value = viewModel.searchQuery,
            onValueChange = { viewModel.updateSearchQuery(it) },
            label = { Text("Пошук по назві") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // --- Фільтр за типом ---
        Row {
            listOf("Solar", "Wind", "Hydro", "All").forEach { type ->
                Button(
                    onClick = { viewModel.updateFilterType(if (type == "All") null else type) },
                    modifier = Modifier.padding(end = 8.dp)
                ) { Text(type) }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // --- Сортування ---
        Row {
            listOf("Name", "Type", "Power").forEach { criteria ->
                Button(
                    onClick = { viewModel.updateSortBy(criteria) },
                    modifier = Modifier.padding(end = 8.dp)
                ) { Text(criteria) }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // --- Список карток станцій ---
        LazyColumn {
            items(viewModel.filteredStations) { station ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text("Назва: ${station.name}", style = MaterialTheme.typography.titleMedium)
                        Text("Тип: ${station.type}")
                        Text("Потужність: ${station.power} кВт")
                        Text("Останнє оновлення: ${station.lastUpdate}")
                    }
                }
            }
        }
    }
}
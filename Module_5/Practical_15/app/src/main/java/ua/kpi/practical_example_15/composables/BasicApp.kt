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
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.kpi.practical_example_15.viewModels.SolarStationViewModel


@Composable
fun BasicApp(viewModel: SolarStationViewModel = viewModel()) {
    Column {
        // --- Поле пошуку по назві ---
        OutlinedTextField(
            value = viewModel.searchQuery,
            onValueChange = { viewModel.updateSearchQuery(it) },
            label = { Text("Пошук по назві") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // --- Сортування за назвою або типом ---
        Row {
            Button(onClick = { viewModel.updateSortBy("Name") }, modifier = Modifier.padding(end = 8.dp)) {
                Text("Сортувати за назвою")
            }
            Button(onClick = { viewModel.updateSortBy("Type") }) {
                Text("Сортувати за типом")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // --- Список станцій ---
        LazyColumn {
            items(viewModel.filteredStations) { station ->
                Text("${station.name} (${station.type})")
                Divider()
            }
        }
    }
}

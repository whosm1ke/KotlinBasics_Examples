package ua.kpi.practical_example_9.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.kpi.practical_example_9.viewModels.AdvancedSolarViewModel
import ua.kpi.practical_example_9.viewModels.AdvancedSolarViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdvancedApp(viewModel: AdvancedSolarViewModel = viewModel(factory = AdvancedSolarViewModelFactory())) {
    var dayInput by remember { mutableStateOf("") }
    var powerInput by remember { mutableStateOf("") }
    var searchQuery by remember { mutableStateOf("") }
    var ascending by remember { mutableStateOf(true) }

    val powerList by viewModel.powerList.collectAsState()
    val averagePower by viewModel.averagePower.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("Просунутий рівень: Прогноз сонячної станції", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(dayInput, { dayInput = it }, label = { Text("День") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            powerInput, { powerInput = it },
            label = { Text("Потужність (кВт)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            val powerValue = powerInput.toDoubleOrNull() ?: 0.0
            viewModel.addPower(dayInput, powerValue)
            dayInput = ""
            powerInput = ""
        }, modifier = Modifier.fillMaxWidth()) { Text("Додати прогноз") }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            OutlinedTextField(searchQuery, { searchQuery = it; viewModel.filterAndSort(it, ascending) },
                label = { Text("Фільтр за днем") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { ascending = !ascending; viewModel.filterAndSort(searchQuery, ascending) }) {
                Text(if (ascending) "↑" else "↓")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Середня потужність: ${String.format("%.2f", averagePower)} кВт", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        for (item in powerList) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("${item.day}: ${item.power} кВт")
                Row {
                    Button(onClick = { viewModel.deletePower(item.day) }) { Text("Видалити") }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = { viewModel.updatePower(item.day, item.power + 10) }) { Text("+10 кВт") }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}
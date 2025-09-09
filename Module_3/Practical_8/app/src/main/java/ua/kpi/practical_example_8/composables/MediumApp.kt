package ua.kpi.practical_example_8.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.kpi.practical_example_8.dbs.MediumAppDatabase
import ua.kpi.practical_example_8.repositories.EnergyRepository
import ua.kpi.practical_example_8.viewModels.EnergyViewModel


@Composable
fun MediumApp() {

    val context = LocalContext.current

    // Створення бази даних
    val db = remember {
        androidx.room.Room.databaseBuilder(
            context,
            MediumAppDatabase::class.java,
            "solar_station_db"
        ).build()
    }

    // Репозиторій
    val repository = remember { EnergyRepository(db.energyDao()) }

    // ViewModel через viewModel() + фабрика
    val viewModel: EnergyViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return EnergyViewModel(repository) as T
            }
        }
    )

    // Стейти для введення
    var inputPower by remember { mutableStateOf("") }
    var inputVoltage by remember { mutableStateOf("") }
    var inputTemp by remember { mutableStateOf("") }

    val powers by viewModel.powers.collectAsState()
    val voltages by viewModel.voltages.collectAsState()
    val temps by viewModel.temperatures.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        // Введення Потужності
        OutlinedTextField(
            value = inputPower,
            onValueChange = { inputPower = it },
            label = { Text("Потужність (кВт)") },
            modifier = Modifier.fillMaxWidth()
        )

        // Введення Напруги
        OutlinedTextField(
            value = inputVoltage,
            onValueChange = { inputVoltage = it },
            label = { Text("Напруга (В)") },
            modifier = Modifier.fillMaxWidth()
        )

        // Введення Температури
        OutlinedTextField(
            value = inputTemp,
            onValueChange = { inputTemp = it },
            label = { Text("Температура (°C)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                inputPower.toDoubleOrNull()?.let { viewModel.addPower(it) }
                inputVoltage.toDoubleOrNull()?.let { viewModel.addVoltage(it) }
                inputTemp.toDoubleOrNull()?.let { viewModel.addTemperature(it) }
                inputPower = ""
                inputVoltage = ""
                inputTemp = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Додати параметри")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Списки
        Text("Потужності:", style = MaterialTheme.typography.titleMedium)
        LazyColumn {
            items(powers) { item ->
                Text("Потужність: ${item.value} кВт")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Напруги:", style = MaterialTheme.typography.titleMedium)
        LazyColumn {
            items(voltages) { item ->
                Text("Напруга: ${item.value} В")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Температури:", style = MaterialTheme.typography.titleMedium)
        LazyColumn {
            items(temps) { item ->
                Text("Температура: ${item.value} °C")
            }
        }
    }

    // Завантаження всіх даних при старті
    LaunchedEffect(Unit) {
        viewModel.loadAll()
    }
}
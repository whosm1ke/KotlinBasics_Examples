package ua.kpi.practical_example_8.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room.databaseBuilder
import ua.kpi.practical_example_8.dbs.AdvancedAppDatabase
import ua.kpi.practical_example_8.repositories.AdvancedEnergyRepository
import ua.kpi.practical_example_8.viewModels.AdvancedEnergyViewModel

@Composable
fun AdvancedApp() {
    // Отримуємо поточний контекст для створення бази даних
    val context = LocalContext.current
    
    // Створюємо екземпляр бази даних з використанням Room
    val db = remember {
        databaseBuilder(
            context,
            AdvancedAppDatabase::class.java,
            "advanced_solar_db"
        ).build()
    }

    // Створюємо репозиторій для роботи з даними
    val repository = remember { AdvancedEnergyRepository(db.advancedDao()) }

    // Ініціалізуємо ViewModel з використанням фабрики для створення екземпляра
    val viewModel: AdvancedEnergyViewModel = viewModel(factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return AdvancedEnergyViewModel(repository) as T
        }
    })

    // Створюємо змінні для зберігання введених користувачем даних
    var stationName by remember { mutableStateOf("") }
    var powerInput by remember { mutableStateOf("") }
    var voltageInput by remember { mutableStateOf("") }

    // Отримуємо стан змінних з ViewModel для відображення у Composable
    val stations by viewModel.stations.collectAsState()
    val powers by viewModel.powers.collectAsState()
    val voltages by viewModel.voltages.collectAsState()
    val selectedStationId by viewModel.selectedStationId.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {

        // Поле вводу для назви станції
        OutlinedTextField(
            value = stationName,
            onValueChange = { stationName = it },
            label = { Text("Назва станції") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Поле вводу для потужностей (через кому)
        OutlinedTextField(
            value = powerInput,
            onValueChange = { powerInput = it },
            label = { Text("Потужності (через ,)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Поле вводу для напруг (через кому)
        OutlinedTextField(
            value = voltageInput,
            onValueChange = { voltageInput = it },
            label = { Text("Напруги (через ,)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка для додавання нової станції з даними
        Button(onClick = {
            // Розбиваємо введені значення на список чисел
            val powersList = powerInput.split(",").mapNotNull { it.toDoubleOrNull() }
            val voltagesList = voltageInput.split(",").mapNotNull { it.toDoubleOrNull() }
            
            // Викликаємо метод додавання станції з даними у ViewModel
            viewModel.addStationWithData(stationName, powersList, voltagesList)
            
            // Очищуємо поля вводу після додавання
            stationName = ""
            powerInput = ""
            voltageInput = ""
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Додати станцію з даними")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Відображаємо заголовок для списку станцій
        Text("Список станцій:", style = MaterialTheme.typography.titleMedium)
        
        // Створюємо список станцій з можливістю вибору
        LazyColumn {
            items(stations) { station ->
                Text(
                    text = station.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .clickable { viewModel.selectStation(station.id) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Якщо обрана станція, відображаємо її дані
        selectedStationId?.let {
            Text("Потужності станції:", style = MaterialTheme.typography.titleMedium)
            powers.forEach { power ->
                Text("• ${power.value} W")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text("Напруги станції:", style = MaterialTheme.typography.titleMedium)
            voltages.forEach { voltage ->
                Text("• ${voltage.value} V")
            }
        }
    }
}
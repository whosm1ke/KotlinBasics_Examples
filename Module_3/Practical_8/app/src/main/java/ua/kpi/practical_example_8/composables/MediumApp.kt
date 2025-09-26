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

    // Отримуємо контекст застосунку для створення бази даних
    val context = LocalContext.current

    // Створюємо базу даних з використанням Room через remember для запобігання повторному створенню
    val db = remember {
        androidx.room.Room.databaseBuilder(
            context,
            MediumAppDatabase::class.java,
            "solar_station_db"
        ).build()
    }

    // Створюємо репозиторій для взаємодії з базою даних
    val repository = remember { EnergyRepository(db.energyDao()) }

    // Ініціалізуємо ViewModel через фабрику, щоб передати репозиторій
    val viewModel: EnergyViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return EnergyViewModel(repository) as T // Перетворюємо тип
            }
        }
    )

    // Створюємо змінні для зберігання введених користувачем значень
    var inputPower by remember { mutableStateOf("") }
    var inputVoltage by remember { mutableStateOf("") }
    var inputTemp by remember { mutableStateOf("") }

    // Отримуємо потужності, напруги та температури з ViewModel як ObservableState
    val powers by viewModel.powers.collectAsState()
    val voltages by viewModel.voltages.collectAsState()
    val temps by viewModel.temperatures.collectAsState()

    // Основна колонка компонентів
    Column(modifier = Modifier.padding(16.dp)) {
        // Поле вводу для потужності
        OutlinedTextField(
            value = inputPower,
            onValueChange = { inputPower = it },
            label = { Text("Потужність (кВт)") },
            modifier = Modifier.fillMaxWidth()
        )

        // Поле вводу для напруги
        OutlinedTextField(
            value = inputVoltage,
            onValueChange = { inputVoltage = it },
            label = { Text("Напруга (В)") },
            modifier = Modifier.fillMaxWidth()
        )

        // Поле вводу для температури
        OutlinedTextField(
            value = inputTemp,
            onValueChange = { inputTemp = it },
            label = { Text("Температура (°C)") },
            modifier = Modifier.fillMaxWidth()
        )

        // Простір між елементами
        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка для додавання введених значень
        Button(
            onClick = {
                // Перевіряємо, чи можна перетворити введений текст у число і додаємо до ViewModel
                inputPower.toDoubleOrNull()?.let { viewModel.addPower(it) }
                inputVoltage.toDoubleOrNull()?.let { viewModel.addVoltage(it) }
                inputTemp.toDoubleOrNull()?.let { viewModel.addTemperature(it) }
                
                // Очищуємо поля після додавання
                inputPower = ""
                inputVoltage = ""
                inputTemp = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Додати параметри")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Відображення списку потужностей
        Text("Потужності:", style = MaterialTheme.typography.titleMedium)
        LazyColumn {
            items(powers) { item ->
                Text("Потужність: ${item.value} кВт")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Відображення списку напруг
        Text("Напруги:", style = MaterialTheme.typography.titleMedium)
        LazyColumn {
            items(voltages) { item ->
                Text("Напруга: ${item.value} В")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Відображення списку температур
        Text("Температури:", style = MaterialTheme.typography.titleMedium)
        LazyColumn {
            items(temps) { item ->
                Text("Температура: ${item.value} °C")
            }
        }
    }

    // Завантаження всіх даних при старті застосунку
    LaunchedEffect(Unit) {
        viewModel.loadAll()
    }
}
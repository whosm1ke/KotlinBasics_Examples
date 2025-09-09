package ua.kpi.practical_example_8.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.room.Room.databaseBuilder
import kotlinx.coroutines.launch
import ua.kpi.practical_example_8.data.EnergyParameter
import ua.kpi.practical_example_8.dbs.BasicAppDatabase

@Composable
fun BasicApp() {
    val context = LocalContext.current
    val db = remember {
        databaseBuilder(
            context,
            BasicAppDatabase::class.java,
            "basic_solar_station_db"
        ).build()
    }
    // Стейт для збережених параметрів
    var parameters by remember { mutableStateOf(listOf<EnergyParameter>()) }

    // Стейт для введеної потужності
    var inputPower by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier.padding(16.dp),
    ) {
        // Введення потужності
        OutlinedTextField(
            value = inputPower,
            onValueChange = { inputPower = it },
            label = { Text("Потужність (кВт)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка для додавання нового параметра
        Button(
            onClick = {
                val powerValue = inputPower.toDoubleOrNull()
                if (powerValue != null) {
                    coroutineScope.launch {
                        // Вставка у базу даних
                        db.energyParameterDao().insert(EnergyParameter(power = powerValue))
                        // Оновлення списку
                        parameters = db.energyParameterDao().getAll()
                        inputPower = "" // очищення поля
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Додати параметр")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Відображення списку збережених параметрів
        LazyColumn {
            items(parameters) { param ->
                Text(text = "Потужність: ${param.power} кВт", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
    // Початкове завантаження даних
    LaunchedEffect(Unit) {
        parameters = db.energyParameterDao().getAll()
    }
}
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
    // Отримуємо контекст застосунку для створення бази даних
    val context = LocalContext.current
    
    // Створюємо базу даних з використанням Room
    val db = remember {
        databaseBuilder(
            context,
            BasicAppDatabase::class.java,
            "basic_solar_station_db"
        ).build()
    }
    
    // Стейт для збережених параметрів енергії
    var parameters by remember { mutableStateOf(listOf<EnergyParameter>()) }

    // Стейт для введеного значення потужності
    var inputPower by remember { mutableStateOf("") }

    // Отримуємо корутинну область для запуску асинхронних операцій
    val coroutineScope = rememberCoroutineScope()
    
    Column(
        modifier = Modifier.padding(16.dp),
    ) {
        // Поле вводу потужності з підписом
        OutlinedTextField(
            value = inputPower,
            onValueChange = { inputPower = it },
            label = { Text("Потужність (кВт)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка для додавання нового параметра до бази даних
        Button(
            onClick = {
                val powerValue = inputPower.toDoubleOrNull() // Перетворюємо введений текст у число
                if (powerValue != null) { // Якщо перетворення вдалося
                    coroutineScope.launch { // Запускаємо корутину для асинхронної операції
                        // Вставляємо новий параметр у базу даних
                        db.energyParameterDao().insert(EnergyParameter(power = powerValue))
                        // Оновлюємо список параметрів з бази даних
                        parameters = db.energyParameterDao().getAll()
                        inputPower = "" // Очищуємо поле вводу
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Додати параметр")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Відображення списку збережених параметрів у вигляді списку LazyColumn
        LazyColumn {
            items(parameters) { param ->
                Text(text = "Потужність: ${param.power} кВт", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
    
    // Початкове завантаження даних з бази даних під час першого відображення компоненти
    LaunchedEffect(Unit) {
        parameters = db.energyParameterDao().getAll()
    }
}
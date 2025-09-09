package ua.kpi.practical_example_2.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// ===================
// Composable для базового рівня
// ===================
@Composable
fun BasicEnergyApp() {
    // Зберігаємо значення введеного параметру
    var parameter by remember { mutableStateOf("") }
    var result by remember { mutableStateOf(0.0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Базовий рівень: введіть параметр для розрахунку потужності", style = MaterialTheme.typography.titleMedium)

        // Поле для введення одного параметру
        TextField(
            value = parameter,
            onValueChange = { parameter = it },
            label = { Text("Параметр (наприклад, сонячна радіація) ") }
        )

        // Кнопка для обчислення
        Button(onClick = {
            result = calculatePowerBasic(parameter)
        }) {
            Text("Розрахувати")
        }

        // Відображення результату
        Text("Розрахована потужність: $result Вт", style = MaterialTheme.typography.bodyLarge)
    }
}

// ===================
// Базова бізнес-логіка
// ===================
fun calculatePowerBasic(input: String): Double {
    val value = input.toDoubleOrNull() ?: 0.0
    // Простий приклад обчислення потужності
    return value * 0.75
}
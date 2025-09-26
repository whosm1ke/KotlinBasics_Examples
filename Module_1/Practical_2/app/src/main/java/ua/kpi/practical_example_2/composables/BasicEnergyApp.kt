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
    // Зберігаємо значення введеного параметру у стані Compose
    var parameter by remember { mutableStateOf("") }
    // Зберігаємо результат обчислення потужності у стані Compose
    var result by remember { mutableStateOf(0.0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Відображення заголовка для користувача
        Text("Базовий рівень: введіть параметр для розрахунку потужності", style = MaterialTheme.typography.titleMedium)

        // Поле для введення одного параметру (наприклад, сонячна радіація)
        TextField(
            value = parameter,
            onValueChange = { parameter = it },
            label = { Text("Параметр (наприклад, сонячна радіація) ") }
        )

        // Кнопка для запуску обчислення потужності
        Button(onClick = {
            result = calculatePowerBasic(parameter)
        }) {
            Text("Розрахувати")
        }

        // Відображення результату обчислення потужності
        Text("Розрахована потужність: $result Вт", style = MaterialTheme.typography.bodyLarge)
    }
}

// ===================
// Базова бізнес-логіка
// ===================
fun calculatePowerBasic(input: String): Double {
    // Перетворюємо вхідний рядок у число, якщо це можливо, інакше повертаємо 0.0
    val value = input.toDoubleOrNull() ?: 0.0
    // Простий приклад обчислення потужності: множимо значення на коефіцієнт 0.75
    return value * 0.75
}
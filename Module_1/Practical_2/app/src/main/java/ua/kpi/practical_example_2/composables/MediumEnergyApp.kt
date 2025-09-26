package ua.kpi.practical_example_2.composables
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MediumEnergyApp() {
    // Створюємо змінні стану для зберігання вхідних даних та результату
    var solarRadiation by remember { mutableStateOf("") }  // Значення сонячної радіації
    var panelEfficiency by remember { mutableStateOf("") }  // Ефективність сонячної панелі
    var result by remember { mutableStateOf(0.0) }  // Результат обчислення потужності

    Column(
        modifier = Modifier
            .fillMaxSize()  // Заповнює весь доступний простір
            .padding(16.dp),  // Додає відступи навколо елементів
        verticalArrangement = Arrangement.spacedBy(12.dp)  // Відстань між елементами по вертикалі
    ) {
        // Відображає заголовок додатку
        Text("Середній рівень: декілька параметрів", style = MaterialTheme.typography.titleMedium)

        // Поле вводу для сонячної радіації
        TextField(
            value = solarRadiation,
            onValueChange = { solarRadiation = it },  // Оновлення значення при введенні
            label = { Text("Сонячна радіація (Вт/м²)") }
        )

        // Поле вводу для ефективності панелі
        TextField(
            value = panelEfficiency,
            onValueChange = { panelEfficiency = it },  // Оновлення значення при введенні
            label = { Text("Ефективність панелі (%)") }
        )

        // Кнопка для запуску обчислення потужності
        Button(onClick = {
            result = calculatePowerMedium(solarRadiation, panelEfficiency)  // Виклик функції обчислення
        }) {
            Text("Розрахувати")
        }

        // Відображення результату обчислення
        Text("Розрахована потужність: $result Вт", style = MaterialTheme.typography.bodyLarge)
    }
}

// ===================
// Бізнес-логіка середнього рівня
// ===================
fun calculatePowerMedium(radiation: String, efficiency: String): Double {
    // Перетворюємо радіацію на число або повертаємо 0.0, якщо конвертація невдала
    val rad = radiation.toDoubleOrNull() ?: 0.0
    // Перетворюємо ефективність на число і ділимо на 100 для отримання відсотка або повертаємо 0.0
    val eff = efficiency.toDoubleOrNull()?.div(100) ?: 0.0
    // Обчислення потужності: радіація * ефективність (в долях)
    return rad * eff
}
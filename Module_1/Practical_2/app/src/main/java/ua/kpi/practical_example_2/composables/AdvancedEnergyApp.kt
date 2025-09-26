package ua.kpi.practical_example_2.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_2.data.Calculator

@Composable
fun AdvancedEnergyApp() {
    // Створюємо змінні стану для зберігання введених користувачем даних
    var solarRadiation by remember { mutableStateOf("") }  // Сонячна радіація у Вт/м²
    var panelEfficiency by remember { mutableStateOf("") }  // Ефективність сонячної панелі у відсотках
    var panelArea by remember { mutableStateOf("") }  // Площа сонячної панелі у м²
    var result by remember { mutableStateOf(0.0) }  // Результат розрахунку потужності

    // Створюємо екземпляр класу Calculator для виконання розрахунків
    val calculator = Calculator()
    
    Column(
        modifier = Modifier
            .fillMaxSize()  // Заповнює весь доступний простір
            .padding(16.dp),  // Додає відступи навколо елементів
        verticalArrangement = Arrangement.spacedBy(12.dp)  // Встановлює відстань між елементами
    ) {
        // Відображаємо заголовок додатку
        Text("Просунутий рівень: масштабування проекту", style = MaterialTheme.typography.titleMedium)

        // Поле вводу для сонячної радіації
        TextField(
            value = solarRadiation,
            onValueChange = { solarRadiation = it },
            label = { Text("Сонячна радіація (Вт/м²)") }
        )

        // Поле вводу для ефективності панелі
        TextField(
            value = panelEfficiency,
            onValueChange = { panelEfficiency = it },
            label = { Text("Ефективність панелі (%)") }
        )

        // Поле вводу для площі панелі
        TextField(
            value = panelArea,
            onValueChange = { panelArea = it },
            label = { Text("Площа панелі (м²)") }
        )

        // Кнопка для запуску розрахунку потужності
        Button(onClick = {
            result = calculator.calculatePowerAdvanced(solarRadiation, panelEfficiency, panelArea)  // Викликаємо метод розрахунку
        }) {
            Text("Розрахувати")
        }

        // Відображаємо результат розрахунку потужності
        Text("Розрахована потужність: $result Вт", style = MaterialTheme.typography.bodyLarge)
    }
}
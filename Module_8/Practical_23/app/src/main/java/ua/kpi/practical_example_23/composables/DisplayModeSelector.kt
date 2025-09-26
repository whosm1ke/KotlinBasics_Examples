package ua.kpi.practical_example_23.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_23.DisplayFor

@Composable
fun DisplayModeSelector(
    // Текуче значення, що відображається у селекторі
    selected: DisplayFor,
    // Callback функція, яка викликається при зміні обраного режиму
    onSelectedChange: (DisplayFor) -> Unit
) {
    // Використовуємо Column для вертикального розташування елементів
    Column(
        modifier = Modifier
            .fillMaxWidth()  // Заповнює весь доступний простір по ширині
            .padding(12.dp),   // Додає внутрішній відступ
        horizontalAlignment = Alignment.CenterHorizontally  // Вирівнювання елементів по центру
    ) {
        // Виводимо заголовок селектора
        Text(
            text = "Вибір режиму відображення",
            style = MaterialTheme.typography.bodyLarge  // Застосовуємо стиль теми
        )

        Spacer(modifier = Modifier.height(12.dp))  // Додаємо простір між заголовком і кнопками

        // Проходимо по всім значенням enum DisplayFor і створюємо для кожного кнопку
        DisplayFor.values().forEach { mode ->
            Button(
                onClick = { onSelectedChange(mode) },  // Обробник події при натисканні
                modifier = Modifier
                    .fillMaxWidth()  // Кнопка заповнює весь доступний простір по ширині
                    .padding(vertical = 4.dp),   // Внутрішній відступ по вертикалі
                // Вимикаємо кнопку, якщо це поточний обраний режим (не дозволяє повторно вибирати)
                enabled = mode != selected
            ) {
                // Виводимо текст на кнопці залежно від режиму
                Text(text = when (mode) {
                    DisplayFor.BASIC_LEVEL -> "Базовий рівень"   // Текст для базового рівня
                    DisplayFor.MIDDLE_LEVEL -> "Середній рівень"  // Текст для середнього рівня
                    DisplayFor.ADVANCED_LEVEL -> "Просунутий рівень"  // Текст для просунутого рівня
                })
            }
        }
    }
}
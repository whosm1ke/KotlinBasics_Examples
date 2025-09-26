package ua.kpi.practical_example_14.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_14.DisplayFor

@Composable
fun DisplayModeSelector(
    // Текуче значення, яке відображається у селекторі
    selected: DisplayFor,
    // Callback, який викликається при зміні вибору режиму відображення
    onSelectedChange: (DisplayFor) -> Unit
) {
    // Використовуємо Column для розташування елементів вертикально
    Column(
        modifier = Modifier
            .fillMaxWidth()  // Заповнює весь доступний простір по ширині
            .padding(12.dp),   // Додає внутрішній відступ
        horizontalAlignment = Alignment.CenterHorizontally  // Вирівнювання по центру по горизонталі
    ) {
        // Виводимо заголовок селектора
        Text(
            text = "Вибір режиму відображення",
            style = MaterialTheme.typography.bodyLarge  // Використовуємо стиль теми Material
        )

        Spacer(modifier = Modifier.height(12.dp))  // Простір між заголовком і кнопками

        // Проходимо по всіх значеннях enum DisplayFor і створюємо для кожного кнопку
        DisplayFor.values().forEach { mode ->
            Button(
                onClick = { onSelectedChange(mode) },  // Обробник кліку: викликає callback з новим значенням
                modifier = Modifier
                    .fillMaxWidth()       // Кнопка заповнює весь доступний простір по ширині
                    .padding(vertical = 4.dp),  // Внутрішній відступ по вертикалі
                // Кнопка активна, якщо поточний режим не дорівнює обраному
                enabled = mode != selected
            ) {
                // Виводимо текст кнопки залежно від значення enum
                Text(text = when (mode) {
                    DisplayFor.BASIC_LEVEL -> "Базовий рівень"
                    DisplayFor.MIDDLE_LEVEL -> "Середній рівень"
                    DisplayFor.ADVANCED_LEVEL -> "Просунутий рівень"
                })
            }
        }
    }
}
package ua.kpi.practical_example_26.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_26.DisplayFor

@Composable
fun DisplayModeSelector(
    // Текуче значення, що вибране користувачем
    selected: DisplayFor,
    // Callback, який викликається при зміні вибраного режиму
    onSelectedChange: (DisplayFor) -> Unit
) {
    // Використовуємо Column для вертикального розташування елементів
    Column(
        modifier = Modifier
            .fillMaxWidth()  // Заповнює всю доступну ширину
            .padding(12.dp)   // Додає внутрішній відступ
            .testTag("DisplayModeSelector"), // Мітка для тестування
        horizontalAlignment = Alignment.CenterHorizontally  // Вирівнювання по центру по горизонталі
    ) {
        // Виводимо заголовок для селектора режимів
        Text(
            text = "Вибір режиму відображення",
            style = MaterialTheme.typography.bodyLarge  // Використовуємо стиль теми
        )

        Spacer(modifier = Modifier.height(12.dp))  // Простір між заголовком і кнопками

        // Проходимо по всіх значеннях enum DisplayFor і створюємо для кожного кнопку
        DisplayFor.values().forEach { mode ->
            Button(
                onClick = { onSelectedChange(mode) },  // Обробник кліку, який викликає callback
                modifier = Modifier
                    .fillMaxWidth()  // Кнопка заповнює всю ширину
                    .padding(vertical = 4.dp),  // Внутрішній відступ по вертикалі
                // Кнопка вимкнена, якщо це поточний обраний режим (не можна обрати те саме)
                enabled = mode != selected
            ) {
                Text(text = when (mode) {
                    DisplayFor.BASIC_LEVEL -> "Базовий рівень"  // Відображення назви для базового рівня
                    DisplayFor.MIDDLE_LEVEL -> "Середній рівень"  // Відображення назви для середнього рівня
                    DisplayFor.ADVANCED_LEVEL -> "Просунутий рівень"  // Відображення назви для просунутого рівня
                })
            }
        }
    }
}
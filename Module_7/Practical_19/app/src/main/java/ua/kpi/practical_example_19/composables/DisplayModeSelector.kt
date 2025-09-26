package ua.kpi.practical_example_19.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_19.DisplayFor

@Composable
fun DisplayModeSelector(
    // Текуче значення, яке відображається на екрані
    selected: DisplayFor,
    // Callback функція, яка викликається при зміні обраного режиму
    onSelectedChange: (DisplayFor) -> Unit
) {
    // Створюємо вертикальну колонку для розміщення кнопок
    Column(
        modifier = Modifier
            .fillMaxWidth()  // Заповнює всю доступну ширину
            .padding(12.dp),   // Додає відступи навколо
        horizontalAlignment = Alignment.CenterHorizontally  // Вирівнювання по центру по горизонталі
    ) {
        // Відображаємо заголовок для селектора режимів
        Text(
            text = "Вибір режиму відображення",
            style = MaterialTheme.typography.bodyLarge  // Стиль тексту з Material Theme
        )

        Spacer(modifier = Modifier.height(12.dp))  // Простір між заголовком і кнопками

        // Проходимо по всіх значеннях enum DisplayFor і створюємо для кожного кнопку
        DisplayFor.values().forEach { mode ->
            Button(
                onClick = { onSelectedChange(mode) },  // Обробник кліку, який викликає callback
                modifier = Modifier
                    .fillMaxWidth()  // Кнопка заповнює всю доступну ширину
                    .padding(vertical = 4.dp),      // Відступи зверху та знизу
                // Кнопка неактивна, якщо це поточний обраний режим
                enabled = mode != selected
            ) {
                // Відображаємо текст кнопки залежно від режиму
                Text(text = when (mode) {
                    DisplayFor.BASIC_LEVEL -> "Базовий рівень"
                    DisplayFor.MIDDLE_LEVEL -> "Середній рівень"
                    DisplayFor.ADVANCED_LEVEL -> "Просунутий рівень"
                })
            }
        }
    }
}
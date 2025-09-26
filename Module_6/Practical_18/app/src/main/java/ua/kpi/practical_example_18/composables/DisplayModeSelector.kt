package ua.kpi.practical_example_18.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_18.DisplayFor

@Composable
fun DisplayModeSelector(
    // Текуче значення, що вибрано для режиму відображення
    selected: DisplayFor,
    // Callback функція, яка викликається при зміні вибраного режиму
    onSelectedChange: (DisplayFor) -> Unit
) {
    // Створюємо вертикальний контейнер для розміщення кнопок
    Column(
        modifier = Modifier
            .fillMaxWidth()  // Заповнює весь доступний простір по ширині
            .padding(12.dp),   // Додає внутрішній відступ
        horizontalAlignment = Alignment.CenterHorizontally  // Вирівнювання елементів по центру
    ) {
        // Відображаємо заголовок для селектора режимів
        Text(
            text = "Вибір режиму відображення",
            style = MaterialTheme.typography.bodyLarge  // Застосовуємо стиль теми
        )

        Spacer(modifier = Modifier.height(12.dp))  // Простір між заголовком і кнопками

        // Проходить по всіх значеннях enum DisplayFor та створює кнопку для кожного
        DisplayFor.values().forEach { mode ->
            Button(
                onClick = { onSelectedChange(mode) },  // Викликає callback при натисканні
                modifier = Modifier
                    .fillMaxWidth()  // Кнопка заповнює весь доступний простір по ширині
                    .padding(vertical = 4.dp),   // Внутрішній відступ зверху і знизу
                // Активна кнопка, якщо це не поточний обраний режим
                enabled = mode != selected
            ) {
                // Відображаємо текст для кожної кнопки залежно від значення enum
                Text(text = when (mode) {
                    DisplayFor.BASIC_LEVEL -> "Базовий рівень"
                    DisplayFor.MIDDLE_LEVEL -> "Середній рівень"
                    DisplayFor.ADVANCED_LEVEL -> "Просунутий рівень"
                })
            }
        }
    }
}
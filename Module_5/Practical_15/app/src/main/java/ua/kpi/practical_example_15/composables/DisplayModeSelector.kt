package ua.kpi.practical_example_15.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_15.DisplayFor

@Composable
fun DisplayModeSelector(
    // Текуче значення, яке відображається на екрані
    selected: DisplayFor,
    // Callback функція, яка викликається при зміні режиму відображення
    onSelectedChange: (DisplayFor) -> Unit
) {
    // Створюємо вертикальний контейнер для розміщення елементів
    Column(
        modifier = Modifier
            .fillMaxWidth() // Заповнює весь доступний простір по ширині
            .padding(12.dp), // Додає внутрішній відступ
        horizontalAlignment = Alignment.CenterHorizontally // Вирівнювання елементів по центру
    ) {
        // Відображаємо заголовок для вибору режиму
        Text(
            text = "Вибір режиму відображення",
            style = MaterialTheme.typography.bodyLarge // Застосовуємо стиль теми Material
        )

        Spacer(modifier = Modifier.height(12.dp)) // Додаємо простір між заголовком і кнопками

        // Проходимо по всіх значеннях enum DisplayFor і створюємо кнопку для кожного
        DisplayFor.values().forEach { mode ->
            Button(
                onClick = { onSelectedChange(mode) }, // Обробник події при натисканні на кнопку
                modifier = Modifier
                    .fillMaxWidth() // Кнопка заповнює весь доступний простір по ширині
                    .padding(vertical = 4.dp), // Внутрішній відступ зверху і знизу
                // Встановлюємо стан кнопки: активна, якщо це не поточний режим
                enabled = mode != selected
            ) {
                Text(text = when (mode) {
                    // Відображаємо текст для базового рівня
                    DisplayFor.BASIC_LEVEL -> "Базовий рівень"
                    // Відображаємо текст для середнього рівня
                    DisplayFor.MIDDLE_LEVEL -> "Середній рівень"
                    // Відображаємо текст для просунутого рівня
                    DisplayFor.ADVANCED_LEVEL -> "Просунутий рівень"
                })
            }
        }
    }
}
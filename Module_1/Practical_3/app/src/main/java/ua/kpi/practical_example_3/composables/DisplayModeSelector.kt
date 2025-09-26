package ua.kpi.practical_example_3.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_3.DisplayFor

@Composable
fun DisplayModeSelector(
    // Текуче значення, що відображається на екрані
    selected: DisplayFor,
    // Callback-функція, яка викликається при зміні режиму відображення
    onSelectedChange: (DisplayFor) -> Unit
) {
    // Використовуємо Column для вертикального розміщення елементів
    Column(
        modifier = Modifier
            .fillMaxWidth()           // Заповнює весь доступний простір по ширині
            .padding(12.dp),          // Додає внутрішній відступ 12 dp
        horizontalAlignment = Alignment.CenterHorizontally  // Вирівнювання елементів по центру
    ) {
        // Відображаємо заголовок для вибору режиму
        Text(
            text = "Вибір режиму відображення",
            style = MaterialTheme.typography.bodyLarge  // Використовуємо стиль теми для тексту
        )

        Spacer(modifier = Modifier.height(12.dp))  // Додаємо простір між заголовком і кнопками

        // Проходимо по всім значенням enum DisplayFor та створюємо кнопку для кожного
        DisplayFor.values().forEach { mode ->
            Button(
                onClick = { onSelectedChange(mode) },  // При натисканні викликається callback
                modifier = Modifier
                    .fillMaxWidth()           // Кнопка заповнює весь доступний простір по ширині
                    .padding(vertical = 4.dp), // Внутрішній відступ по вертикалі
                // Кнопка неактивна, якщо це поточний режим (відображається як обраний)
                enabled = mode != selected
            ) {
                // Відображаємо текст кнопки в залежності від значення enum
                Text(text = when (mode) {
                    DisplayFor.BASIC_LEVEL -> "Базовий рівень"
                    DisplayFor.MIDDLE_LEVEL -> "Середній рівень"
                    DisplayFor.ADVANCED_LEVEL -> "Просунутий рівень"
                })
            }
        }
    }
}
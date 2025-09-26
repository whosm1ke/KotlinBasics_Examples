package ua.kpi.practical_example_13.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_13.DisplayFor

@Composable
fun DisplayModeSelector(
    // Текуче значення, що вибрано для режиму відображення
    selected: DisplayFor,
    // Callback-функція, яка викликається при зміні вибору режиму
    onSelectedChange: (DisplayFor) -> Unit
) {
    // Створюємо вертикальну колонку для розміщення кнопок вибору режимів
    Column(
        modifier = Modifier
            .fillMaxWidth()  // Заповнює весь доступний простір по ширині
            .padding(12.dp),   // Додає внутрішній відступ
        horizontalAlignment = Alignment.CenterHorizontally  // Вирівнювання елементів по центру
    ) {
        // Виводимо заголовок для секції вибору режиму
        Text(
            text = "Вибір режиму відображення",
            style = MaterialTheme.typography.bodyLarge  // Використовуємо стиль теми для тексту
        )

        Spacer(modifier = Modifier.height(12.dp))  // Додаємо простір між заголовком і кнопками

        // Проходимо по всіх значеннях enum DisplayFor та створюємо для кожного кнопку
        DisplayFor.values().forEach { mode ->
            Button(
                onClick = { onSelectedChange(mode) },  // При натисканні викликається callback з новим режимом
                modifier = Modifier
                    .fillMaxWidth()  // Кнопка заповнює всю доступну ширину
                    .padding(vertical = 4.dp),   // Внутрішній відступ по вертикалі
                // Кнопка вимкнена, якщо це поточний режим (не можна обрати те саме)
                enabled = mode != selected
            ) {
                Text(text = when (mode) {
                    DisplayFor.BASIC_LEVEL -> "Базовий рівень"      // Переклад для базового рівня
                    DisplayFor.MIDDLE_LEVEL -> "Середній рівень"    // Переклад для середнього рівня
                    DisplayFor.ADVANCED_LEVEL -> "Просунутий рівень" // Переклад для просунутого рівня
                })
            }
        }
    }
}
package ua.kpi.practical_example_21.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_21.DisplayFor

@Composable
fun DisplayModeSelector(
    // Текуче значення, що вибране користувачем
    selected: DisplayFor,
    // Callback, який викликається при зміні вибору режиму
    onSelectedChange: (DisplayFor) -> Unit
) {
    // Використовуємо Column для вертикального розташування елементів
    Column(
        modifier = Modifier
            .fillMaxWidth()  // Заповнює всю доступну ширину
            .padding(12.dp),   // Додає внутрішній відступ
        horizontalAlignment = Alignment.CenterHorizontally  // Вирівнювання по центру горизонтально
    ) {
        // Виводимо заголовок для селектора режимів відображення
        Text(
            text = "Вибір режиму відображення",
            style = MaterialTheme.typography.bodyLarge  // Використовуємо стандартний стиль теми
        )

        Spacer(modifier = Modifier.height(12.dp))  // Додаємо простір між заголовком і кнопками

        // Проходимо по всіх значеннях enum DisplayFor та створюємо для кожного кнопку
        DisplayFor.values().forEach { mode ->
            Button(
                onClick = { onSelectedChange(mode) },  // Обробник кліку, який викликає callback
                modifier = Modifier
                    .fillMaxWidth()  // Кнопка заповнює всю ширину контейнера
                    .padding(vertical = 4.dp),   // Внутрішній відступ по вертикалі
                // Кнопка активна, якщо поточний режим не збігається з обраним
                enabled = mode != selected
            ) {
                Text(text = when (mode) {
                    DisplayFor.BASIC_LEVEL -> "Базовий рівень"  // Відповідність значення enum до тексту
                    DisplayFor.MIDDLE_LEVEL -> "Середній рівень"
                    DisplayFor.ADVANCED_LEVEL -> "Просунутий рівень"
                })
            }
        }
    }
}
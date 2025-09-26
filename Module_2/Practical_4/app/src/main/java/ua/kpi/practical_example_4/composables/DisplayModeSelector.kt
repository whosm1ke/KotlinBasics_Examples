package ua.kpi.practical_example_4.composables

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
import ua.kpi.practical_example_4.DisplayFor

@Composable
fun DisplayModeSelector(
    // Текуче значення, що вибране користувачем
    selected: DisplayFor,
    // Callback функція, яка викликається при зміні вибору режиму
    onSelectedChange: (DisplayFor) -> Unit
) {
    // Використовуємо Column для вертикального розташування елементів
    Column(
        modifier = Modifier
            .fillMaxWidth()  // Заповнює весь доступний простір по ширині
            .padding(12.dp),  // Додає внутрішній відступ
        horizontalAlignment = Alignment.CenterHorizontally  // Вирівнювання по центру по горизонталі
    ) {
        // Виводимо заголовок для селектора режимів
        Text(
            text = "Вибір режиму відображення",
            style = MaterialTheme.typography.bodyLarge  // Використовуємо стандартну тему тексту
        )

        Spacer(modifier = Modifier.height(12.dp))  // Додаємо простір між заголовком і кнопками

        // Проходимо по всіх значеннях enum DisplayFor та створюємо кнопки для кожного
        DisplayFor.values().forEach { mode ->
            Button(
                onClick = { onSelectedChange(mode) },  // Обробник кліку, який викликає callback з новим значенням
                modifier = Modifier
                    .fillMaxWidth()  // Кнопка заповнює весь простір по ширині
                    .padding(vertical = 4.dp),  // Внутрішній відступ по вертикалі
                // Кнопка є активною, якщо поточний режим не дорівнює обраному
                enabled = mode != selected
            ) {
                // Виводимо текст на кнопці залежно від режиму
                Text(text = when (mode) {
                    DisplayFor.BASIC_LEVEL -> "Базовий рівень"
                    DisplayFor.MIDDLE_LEVEL -> "Середній рівень"
                    DisplayFor.ADVANCED_LEVEL -> "Просунутий рівень"
                })
            }
        }
    }
}
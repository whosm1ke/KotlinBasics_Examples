package ua.kpi.practical_example_11.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_11.DisplayFor

@Composable
fun DisplayModeSelector(
    // Текуче значення, що відображається на екрані
    selected: DisplayFor,
    // Callback функція, яка викликається при зміні режиму відображення
    onSelectedChange: (DisplayFor) -> Unit
) {
    // Відображаємо колонку з кнопками для вибору режиму відображення
    Column(
        modifier = Modifier
            .fillMaxWidth()  // Заповнює весь доступний простір по ширині
            .padding(12.dp),  // Додає внутрішній відступ
        horizontalAlignment = Alignment.CenterHorizontally  // Вирівнювання елементів по центру
    ) {
        // Відображаємо заголовок для селектора режимів
        Text(
            text = "Вибір режиму відображення",
            style = MaterialTheme.typography.bodyLarge  // Стиль тексту з MaterialTheme
        )

        Spacer(modifier = Modifier.height(12.dp))  // Простір між заголовком і кнопками

        // Проходимо по всіх значеннях enum DisplayFor та створюємо кнопку для кожного
        DisplayFor.values().forEach { mode ->
            Button(
                onClick = { onSelectedChange(mode) },  // Обробник події при натисканні
                modifier = Modifier
                    .fillMaxWidth()  // Кнопка заповнює весь доступний простір по ширині
                    .padding(vertical = 4.dp),  // Внутрішній відступ по вертикалі
                // Встановлюємо кнопку неактивною, якщо це поточний обраний режим
                enabled = mode != selected
            ) {
                // Виводимо текст на кнопці залежно від режиму
                Text(text = when (mode) {
                    DisplayFor.BASIC_LEVEL -> "Базовий рівень"  // Відповідність для базового рівня
                    DisplayFor.MIDDLE_LEVEL -> "Середній рівень"  // Відповідність для середнього рівня
                    DisplayFor.ADVANCED_LEVEL -> "Просунутий рівень"  // Відповідність для просунутого рівня
                })
            }
        }
    }
}
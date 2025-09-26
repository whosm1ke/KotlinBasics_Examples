package ua.kpi.practical_example_22.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_22.DisplayFor

@Composable
fun DisplayModeSelector(
    // Текуче значення, яке відображається на екрані
    selected: DisplayFor,
    // Callback функція, яка викликається при зміні обраного режиму відображення
    onSelectedChange: (DisplayFor) -> Unit
) {
    // Використовуємо Column для розташування елементів вертикально
    Column(
        modifier = Modifier
            .fillMaxWidth()  // Заповнює весь доступний простір по ширині
            .padding(12.dp),   // Додає внутрішній відступ
        horizontalAlignment = Alignment.CenterHorizontally  // Вирівнювання елементів по центру
    ) {
        // Виводимо заголовок для селектора режимів відображення
        Text(
            text = "Вибір режиму відображення",
            style = MaterialTheme.typography.bodyLarge  // Використовуємо тему Material для стилю тексту
        )

        Spacer(modifier = Modifier.height(12.dp))  // Додаємо простір між заголовком і кнопками

        // Проходимо по всіх значеннях enum DisplayFor та створюємо кнопку для кожного
        DisplayFor.values().forEach { mode ->
            Button(
                onClick = { onSelectedChange(mode) },  // Обробник події кліку, який викликає callback
                modifier = Modifier
                    .fillMaxWidth()  // Кнопка заповнює всю доступну ширину
                    .padding(vertical = 4.dp),   // Внутрішній відступ по вертикалі
                // Встановлюємо стан кнопки активною, якщо режим не є поточним
                enabled = mode != selected
            ) {
                // Виводимо текст на кнопці залежно від обраного режиму
                Text(text = when (mode) {
                    DisplayFor.BASIC_LEVEL -> "Базовий рівень"    // Текст для базового рівня
                    DisplayFor.MIDDLE_LEVEL -> "Середній рівень"   // Текст для середнього рівня
                    DisplayFor.ADVANCED_LEVEL -> "Просунутий рівень" // Текст для просунутого рівня
                })
            }
        }
    }
}
package ua.kpi.practical_example_6.composables

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
import ua.kpi.practical_example_6.DisplayFor

@Composable
fun DisplayModeSelector(
    // Текуче значення, що відображається на екрані
    selected: DisplayFor,
    // Callback функція, яка викликається при зміні вибору режиму відображення
    onSelectedChange: (DisplayFor) -> Unit
) {
    // Використовуємо Column для розташування елементів вертикально
    Column(
        modifier = Modifier
            .fillMaxWidth()  // Заповнює всю доступну ширину
            .padding(12.dp),   // Додає внутрішній відступ
        horizontalAlignment = Alignment.CenterHorizontally  // Вирівнювання по центру по горизонталі
    ) {
        // Відображаємо заголовок для селектора режимів
        Text(
            text = "Вибір режиму відображення",
            style = MaterialTheme.typography.bodyLarge  // Застосовуємо стиль теми
        )

        Spacer(modifier = Modifier.height(12.dp))  // Додаємо простір між заголовком і кнопками

        // Проходимо по всіх значеннях enum DisplayFor і створюємо кнопку для кожного
        DisplayFor.values().forEach { mode ->
            Button(
                onClick = { onSelectedChange(mode) },  // Обробник кліку, який викликає callback
                modifier = Modifier
                    .fillMaxWidth()  // Кнопка заповнює всю доступну ширину
                    .padding(vertical = 4.dp),       // Внутрішній відступ по вертикалі
                // Кнопка активна, якщо це не поточний обраний режим
                enabled = mode != selected
            ) {
                Text(text = when (mode) {
                    DisplayFor.BASIC_LEVEL -> "Базовий рівень"     // Відображення назви для базового рівня
                    DisplayFor.MIDDLE_LEVEL -> "Середній рівень"   // Відображення назви для середнього рівня
                    DisplayFor.ADVANCED_LEVEL -> "Просунутий рівень" // Відображення назви для просунутого рівня
                })
            }
        }
    }
}
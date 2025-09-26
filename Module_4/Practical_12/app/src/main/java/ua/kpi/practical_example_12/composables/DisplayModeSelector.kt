package ua.kpi.practical_example_12.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_12.DisplayFor

@Composable
fun DisplayModeSelector(
    // Текуче значення, що вибрано для режиму відображення
    selected: DisplayFor,
    // Callback-функція, яка викликається при зміні вибору режиму
    onSelectedChange: (DisplayFor) -> Unit
) {
    // Використовуємо Column для вертикального розміщення елементів
    Column(
        modifier = Modifier
            .fillMaxWidth()  // Заповнює весь доступний простір по ширині
            .padding(12.dp),   // Додає відступи навколо
        horizontalAlignment = Alignment.CenterHorizontally  // Вирівнювання елементів по центру
    ) {
        // Відображаємо заголовок для вибору режиму
        Text(
            text = "Вибір режиму відображення",
            style = MaterialTheme.typography.bodyLarge  // Використовуємо стиль теми Material
        )

        Spacer(modifier = Modifier.height(12.dp))  // Додаємо простір між заголовком і кнопками

        // Проходимо по всіх значеннях enum DisplayFor і створюємо для кожного кнопку
        DisplayFor.values().forEach { mode ->
            Button(
                onClick = { onSelectedChange(mode) },  // Обробник події натискання
                modifier = Modifier
                    .fillMaxWidth()  // Кнопка заповнює весь доступний простір по ширині
                    .padding(vertical = 4.dp),   // Відступи зверху і знизу
                // Кнопка неактивна, якщо це поточний вибраний режим
                enabled = mode != selected
            ) {
                Text(text = when (mode) {
                    DisplayFor.BASIC_LEVEL -> "Базовий рівень"     // Відображення назви для базового рівня
                    DisplayFor.MIDDLE_LEVEL -> "Середній рівень"   // Відображення назви для середнього рівня
                    DisplayFor.ADVANCED_LEVEL -> "Просунутий рівень"  // Відображення назви для просунутого рівня
                })
            }
        }
    }
}
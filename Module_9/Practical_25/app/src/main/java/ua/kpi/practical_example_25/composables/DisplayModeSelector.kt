package ua.kpi.practical_example_25.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_25.DisplayFor

@Composable
fun DisplayModeSelector(
    // Текуче значення, що відображається на екрані
    selected: DisplayFor,
    // Callback-функція, яка викликається при зміні режиму відображення
    onSelectedChange: (DisplayFor) -> Unit
) {
    // Використовуємо Column для розміщення елементів вертикально
    Column(
        modifier = Modifier
            .fillMaxWidth()           // Заповнює весь доступний простір по ширині
            .padding(12.dp)           // Додає відступи навколо
            .testTag("DisplayModeSelector"), // Тег для тестування
        horizontalAlignment = Alignment.CenterHorizontally  // Вирівнювання елементів по центру
    ) {
        // Виводимо заголовок для селектора режимів відображення
        Text(
            text = "Вибір режиму відображення",
            style = MaterialTheme.typography.bodyLarge  // Стиль тексту з Material Theme
        )

        Spacer(modifier = Modifier.height(12.dp))  // Простір між заголовком і кнопками

        // Проходить по всіх значеннях enum DisplayFor і створює кнопки для кожного
        DisplayFor.values().forEach { mode ->
            Button(
                onClick = { onSelectedChange(mode) },  // Обробник кліку: змінює режим відображення
                modifier = Modifier
                    .fillMaxWidth()        // Кнопка заповнює весь простір по ширині
                    .padding(vertical = 4.dp), // Відступи зверху і знизу
                // Кнопка неактивна, якщо це поточний обраний режим
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
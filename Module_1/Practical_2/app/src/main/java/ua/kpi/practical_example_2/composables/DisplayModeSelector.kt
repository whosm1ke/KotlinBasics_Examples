package ua.kpi.practical_example_2.composables

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
import ua.kpi.practical_example_2.DisplayFor

@Composable
fun DisplayModeSelector(
    // Текуче значення, що вибрано для режиму відображення
    selected: DisplayFor,
    // Callback-функція, яка викликається при зміні вибору режиму
    onSelectedChange: (DisplayFor) -> Unit
) {
    // Основний контейнер для розміщення елементів у вертикальному напрямку
    Column(
        modifier = Modifier
            .fillMaxWidth()           // Заповнює весь доступний простір по ширині
            .padding(12.dp),          // Додає внутрішній відступ 12 dp
        horizontalAlignment = Alignment.CenterHorizontally  // Вирівнювання елементів по центру
    ) {
        // Текстовий заголовок для селектора режимів відображення
        Text(
            text = "Вибір режиму відображення",
            style = MaterialTheme.typography.bodyLarge  // Використовується стиль теми Material Design
        )

        Spacer(modifier = Modifier.height(12.dp))  // Простір між заголовком і кнопками

        // Проходження по всім значенням enum DisplayFor для створення кнопок
        DisplayFor.values().forEach { mode ->
            Button(
                onClick = { onSelectedChange(mode) },  // Обробник кліку, який викликає callback з новим значенням
                modifier = Modifier
                    .fillMaxWidth()         // Кнопка заповнює всю доступну ширину
                    .padding(vertical = 4.dp),  // Внутрішній відступ по вертикалі
                // Кнопка неактивна, якщо це поточний обраний режим (не дозволяє повторний вибір)
                enabled = mode != selected
            ) {
                // Відображення тексту на кнопці залежно від значення enum
                Text(text = when (mode) {
                    DisplayFor.BASIC_LEVEL -> "Базовий рівень"     // Відповідний текст для базового рівня
                    DisplayFor.MIDDLE_LEVEL -> "Середній рівень"   // Відповідний текст для середнього рівня
                    DisplayFor.ADVANCED_LEVEL -> "Просунутий рівень" // Відповідний текст для просунутого рівня
                })
            }
        }
    }
}
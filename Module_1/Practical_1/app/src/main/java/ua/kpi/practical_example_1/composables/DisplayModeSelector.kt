package ua.kpi.practical_example_1.composables

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
import ua.kpi.practical_example_1.DisplayFor

/**
 * Composable-функція для вибору режиму відображення.
 *
 * @param selected Поточний обраний режим відображення.
 * @param onSelectedChange Callback, який викликається при зміні режиму відображення.
 */
@Composable
fun DisplayModeSelector(
    // Текуче значення
    selected: DisplayFor,
    // Callback при зміні
    onSelectedChange: (DisplayFor) -> Unit
) {
    // Column з кнопками для перемикання режимів відображення
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Відображаємо заголовок
        Text(
            text = "Вибір режиму відображення",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Для кожного значення enum DisplayFor створюємо кнопку вибору
        DisplayFor.values().forEach { mode ->
            Button(
                onClick = { onSelectedChange(mode) }, // Обробник події натискання на кнопку
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                // Робимо кнопку "неактивною", якщо це поточний режим (вже обраний)
                enabled = mode != selected
            ) {
                Text(text = when (mode) {
                    DisplayFor.BASIC_LEVEL -> "Базовий рівень"
                    DisplayFor.MIDDLE_LEVEL -> "Середній рівень"
                    DisplayFor.ADVANCED_LEVEL -> "Просунутий рівень"
                })
            }
        }
    }
}
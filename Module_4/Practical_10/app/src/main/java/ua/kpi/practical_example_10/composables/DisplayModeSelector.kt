package ua.kpi.practical_example_10.composables

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
import ua.kpi.practical_example_10.DisplayFor

@Composable
fun DisplayModeSelector(
    // Текуче значення режиму відображення
    selected: DisplayFor,
    // Callback, який викликається при зміні режиму відображення
    onSelectedChange: (DisplayFor) -> Unit
) {
    // Відображаємо колонку з кнопками для вибору режиму відображення
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

        // Проходимо по всіх значеннях enum DisplayFor і створюємо кнопку для кожного
        DisplayFor.values().forEach { mode ->
            Button(
                onClick = { onSelectedChange(mode) }, // Обробник події кліку
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                // Кнопка неактивна, якщо це поточний вибраний режим
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
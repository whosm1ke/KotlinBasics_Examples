package ua.kpi.practical_example_8.composables

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
import ua.kpi.practical_example_8.DisplayFor

@Composable
fun DisplayModeSelector(
    // Текуче значення, яке відображається на екрані
    selected: DisplayFor,
    // Callback-функція, що викликається при зміні режиму відображення
    onSelectedChange: (DisplayFor) -> Unit
) {
    // Відображаємо елементи у вертикальному стовпчику з вирівнюванням по центру
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Відображаємо заголовок для вибору режиму відображення
        Text(
            text = "Вибір режиму відображення",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Проходить по всім значенням enum DisplayFor і створює кнопки для кожного
        DisplayFor.values().forEach { mode ->
            Button(
                onClick = { onSelectedChange(mode) }, // Викликає callback при натисканні
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                // Кнопка активна, якщо поточний режим не співпадає з обраним
                enabled = mode != selected
            ) {
                // Відображаємо текст з назвою режиму відображення
                Text(text = when (mode) {
                    DisplayFor.BASIC_LEVEL -> "Базовий рівень"
                    DisplayFor.MIDDLE_LEVEL -> "Середній рівень"
                    DisplayFor.ADVANCED_LEVEL -> "Просунутий рівень"
                })
            }
        }
    }
}
package ua.kpi.practical_example_24.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_24.DisplayFor

@Composable
fun DisplayModeSelector(
    // Текуче значення, яке відображається на екрані
    selected: DisplayFor,
    // Callback функція, яка викликається при зміні вибраного режиму
    onSelectedChange: (DisplayFor) -> Unit
) {
    // Використовуємо Column для вертикального розміщення елементів
    Column(
        modifier = Modifier
            .fillMaxWidth()  // Заповнює весь доступний простір по ширині
            .padding(12.dp),  // Додає внутрішній відступ
        horizontalAlignment = Alignment.CenterHorizontally  // Вирівнювання елементів по центру
    ) {
        // Виводимо заголовок для селектора режимів відображення
        Text(
            text = "Вибір режиму відображення",
            style = MaterialTheme.typography.bodyLarge  // Використовуємо стиль теми Material Design
        )

        Spacer(modifier = Modifier.height(12.dp))  // Додаємо простір між заголовком і кнопками

        // Проходимо по всіх значеннях enum DisplayFor та створюємо кнопки для кожного
        DisplayFor.values().forEach { mode ->
            Button(
                onClick = { onSelectedChange(mode) },  // Обробник події кліку
                modifier = Modifier
                    .fillMaxWidth()  // Кнопка заповнює весь доступний простір по ширині
                    .padding(vertical = 4.dp),  // Внутрішній відступ зверху і знизу
                // Кнопка є активною, якщо режим не співпадає з поточним обраним
                enabled = mode != selected
            ) {
                // Виводимо текст на кнопці в залежності від режиму
                Text(text = when (mode) {
                    DisplayFor.BASIC_LEVEL -> "Базовий рівень"
                    DisplayFor.MIDDLE_LEVEL -> "Середній рівень"
                    DisplayFor.ADVANCED_LEVEL -> "Просунутий рівень"
                })
            }
        }
    }
}
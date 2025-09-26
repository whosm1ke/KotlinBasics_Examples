package ua.kpi.practical_example_6.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Composable-функція для створення кнопки "Зареєструвати".
 * Кнопка має ширину, що заповнює весь доступний простір.
 * При натисканні викликається функція onSubmit.
 *
 * @param onSubmit функція, яка виконується при натисканні на кнопку
 */
@Composable
fun SubmitButton(onSubmit: () -> Unit) {
    Button(
        onClick = onSubmit,  // Дія, що виконується при натисканні на кнопку
        modifier = Modifier.fillMaxWidth()  // Модифікатор для заповнення всього доступного простору
    ) {
        Text("Зареєструвати")  // Текст, що відображається на кнопці
    }
}
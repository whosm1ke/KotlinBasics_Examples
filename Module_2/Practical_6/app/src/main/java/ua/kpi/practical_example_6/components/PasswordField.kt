package ua.kpi.practical_example_6.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue

/**
 * Composable function для відображення поля вводу пароля.
 * Використовує OutlinedTextField з візуальною трансформацією паролю (приховує символи).
 *
 * @param value Поточне значення текстового поля
 * @param isError Прапорець, що вказує на наявність помилки введенння
 * @param onValueChange Функція зворотного виклику, яка викликається при зміні значення поля
 */
@Composable
fun PasswordField(value: TextFieldValue, isError: Boolean, onValueChange: (TextFieldValue) -> Unit) {
    // Відображення поля вводу пароля з відповідною трансформацією символів
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Пароль") }, // Мітка поля вводу
        isError = isError, // Позначення поля як помилкового, якщо isError = true
        modifier = Modifier.fillMaxWidth(), // Заповнює всю доступну ширину
        visualTransformation = PasswordVisualTransformation() // Приховує символи пароля
    )
    
    // Відображення підказки залежно від стану помилки
    if (isError) {
        // Якщо є помилка, показуємо детальний текст про вимоги до пароля
        Text(
            text = "Пароль має містити ≥8 символів, велику літеру та цифру",
            color = MaterialTheme.colorScheme.error, // Колір помилки з теми
            style = MaterialTheme.typography.bodySmall // Стиль тексту
        )
    } else {
        // Якщо немає помилки, показуємо загальну підказку
        Text(
            text = "Мін. 8 символів, велика літера та цифра",
            style = MaterialTheme.typography.bodySmall, // Стиль тексту
            color = MaterialTheme.colorScheme.onSurfaceVariant // Колір залежно від теми
        )
    }
}
package ua.kpi.practical_example_6.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue

/**
 * Composable function that displays a text field for entering power value.
 * It also shows an error message if the input is invalid or a hint if it's valid.
 *
 * @param value The current value of the text field.
 * @param isError A boolean flag indicating whether the input is invalid.
 * @param onValueChange A callback function to handle changes in the text field value.
 */
@Composable
fun PowerField(value: TextFieldValue, isError: Boolean, onValueChange: (TextFieldValue) -> Unit) {
    // Displays an outlined text field with a label for power input
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Потужність станції (кВт)") },
        isError = isError,
        modifier = Modifier.fillMaxWidth()
    )
    
    // If there is an error, display an error message
    if (isError) {
        Text(
            text = "Введіть число більше 0",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall
        )
    } else {
        // If no error, display a hint with an example value
        Text(
            text = "Наприклад: 5000",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
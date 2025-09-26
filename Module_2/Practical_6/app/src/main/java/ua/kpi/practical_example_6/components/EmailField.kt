package ua.kpi.practical_example_6.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue

/**
 * Composable function that displays an email input field with validation.
 * It shows either an error message or a hint depending on the isError flag.
 *
 * @param value The current value of the text field.
 * @param isError A boolean indicating whether the entered email is invalid.
 * @param onValueChange A callback function that is triggered when the user types in the field.
 */
@Composable
fun EmailField(value: TextFieldValue, isError: Boolean, onValueChange: (TextFieldValue) -> Unit) {
    // Displays an outlined text field for email input
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Email оператора") }, // Label displayed above the text field
        isError = isError, // Indicates if the input is in error state
        modifier = Modifier.fillMaxWidth() // Makes the field span the full width of its container
    )
    
    // If there's an error, display an error message
    if (isError) {
        Text(
            text = "Некоректний email", // Error message text
            color = MaterialTheme.colorScheme.error, // Sets the error color from Material Theme
            style = MaterialTheme.typography.bodySmall // Applies small body text style
        )
    } else {
        // If no error, display a hint about the correct email format
        Text(
            text = "Формат: example@mail.com", // Hint message with expected format
            style = MaterialTheme.typography.bodySmall, // Applies small body text style
            color = MaterialTheme.colorScheme.onSurfaceVariant // Sets the color for variant text
        )
    }
}
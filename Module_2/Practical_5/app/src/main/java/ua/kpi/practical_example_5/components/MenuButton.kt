package ua.kpi.practical_example_5.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Composable function that creates a menu button with specified text and click handler.
 * 
 * @param text The text to be displayed on the button.
 * @param onClick The lambda function to be executed when the button is clicked.
 */
@Composable
fun MenuButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick, // Sets the click handler for the button
        modifier = Modifier
            .fillMaxWidth() // Makes the button span the full width of its container
            .padding(vertical = 8.dp) // Adds vertical padding of 8 dp around the button
    ) {
        Text(text) // Displays the text on the button
    }
}
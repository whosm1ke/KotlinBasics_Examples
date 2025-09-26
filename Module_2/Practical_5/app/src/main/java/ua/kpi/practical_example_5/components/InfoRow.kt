package ua.kpi.practical_example_5.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * Composable function that displays a row with a label and its corresponding value.
 * The layout is horizontal with space between the label and value.
 *
 * @param label The text to be displayed as the label (medium weight)
 * @param value The text to be displayed as the value (bold weight)
 */
@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth() // Makes the row fill the entire width of its container
            .padding(vertical = 4.dp), // Adds vertical padding of 4 dp
        horizontalArrangement = Arrangement.SpaceBetween // Distributes children with space between them
    ) {
        Text(label, fontWeight = FontWeight.Medium) // Displays the label with medium font weight
        Text(value, fontWeight = FontWeight.Bold) // Displays the value with bold font weight
    }
}
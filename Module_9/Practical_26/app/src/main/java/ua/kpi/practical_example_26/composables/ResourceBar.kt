package ua.kpi.practical_example_26.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Composable function that displays a resource bar with a label and a progress indicator.
 * The progress bar visualizes the resource usage as a percentage.
 *
 * @param name Name of the resource (e.g., "CPU", "Memory").
 * @param value Current usage value in percent (0-100).
 * @param color Color of the progress indicator.
 */
@Composable
fun ResourceBar(name: String, value: Int, color: Color) {
    Column {
        // Display the resource name and current value as a percentage
        Text("$name: $value%", fontSize = 16.sp)
        
        // Render a linear progress indicator showing the resource usage
        LinearProgressIndicator(
            progress = value / 100f, // Convert percentage to float between 0 and 1
            color = color, // Set the color of the progress bar
            modifier = Modifier
                .fillMaxWidth() // Make the progress bar fill the available width
                .height(16.dp) // Set a fixed height for the progress bar
                .clip(RoundedCornerShape(8.dp)) // Apply rounded corners to the progress bar
        )
    }
}
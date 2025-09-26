package ua.kpi.practical_example_6.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Composable function that displays a circular progress indicator centered on the screen.
 * This is typically used to indicate that some operation is in progress.
 */
@Composable
fun LoadingIndicator() {
    // Creates a Row layout that fills the maximum width of its parent
    // and centers its content horizontally
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        // Displays a circular progress indicator (spinner) in the center of the row
        CircularProgressIndicator()
    }
}
package ua.kpi.practical_example_5.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Composable function that displays a title text with specific styling.
 * It takes a string parameter [title] and renders it as a bold, large text
 * with bottom padding for better visual spacing.
 *
 * @param title The text to be displayed as the screen title
 */
@Composable
fun ScreenTitle(title: String) {
    Text(
        text = title,                       // Sets the text content to be displayed
        fontSize = 24.sp,                   // Sets the font size to 24 sp (scalable pixels)
        fontWeight = FontWeight.Bold,       // Applies bold font weight to the text
        modifier = Modifier.padding(bottom = 16.dp) // Adds bottom padding of 16 dp for visual spacing
    )
}
package ua.kpi.practical_example_6.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun FormHeader() {
    Text(
        text = "Реєстрація сонячної електростанції",
        style = MaterialTheme.typography.headlineSmall
    )
}
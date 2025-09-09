package ua.kpi.practical_example_6.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SubmitButton(onSubmit: () -> Unit) {
    Button(
        onClick = onSubmit,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Зареєструвати")
    }
}
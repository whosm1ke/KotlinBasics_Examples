package ua.kpi.practical_example_2.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_2.data.Calculator

@Composable
fun AdvancedEnergyApp() {
    var solarRadiation by remember { mutableStateOf("") }
    var panelEfficiency by remember { mutableStateOf("") }
    var panelArea by remember { mutableStateOf("") }
    var result by remember { mutableStateOf(0.0) }

    val calculator = Calculator()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Просунутий рівень: масштабування проекту", style = MaterialTheme.typography.titleMedium)

        TextField(
            value = solarRadiation,
            onValueChange = { solarRadiation = it },
            label = { Text("Сонячна радіація (Вт/м²)") }
        )

        TextField(
            value = panelEfficiency,
            onValueChange = { panelEfficiency = it },
            label = { Text("Ефективність панелі (%)") }
        )

        TextField(
            value = panelArea,
            onValueChange = { panelArea = it },
            label = { Text("Площа панелі (м²)") }
        )

        Button(onClick = {
            result = calculator.calculatePowerAdvanced(solarRadiation, panelEfficiency, panelArea)
        }) {
            Text("Розрахувати")
        }

        Text("Розрахована потужність: $result Вт", style = MaterialTheme.typography.bodyLarge)
    }
}


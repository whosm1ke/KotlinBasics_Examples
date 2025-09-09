package ua.kpi.practical_example_2.composables
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MediumEnergyApp() {
    var solarRadiation by remember { mutableStateOf("") }
    var panelEfficiency by remember { mutableStateOf("") }
    var result by remember { mutableStateOf(0.0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Середній рівень: декілька параметрів", style = MaterialTheme.typography.titleMedium)

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

        Button(onClick = {
            result = calculatePowerMedium(solarRadiation, panelEfficiency)
        }) {
            Text("Розрахувати")
        }

        Text("Розрахована потужність: $result Вт", style = MaterialTheme.typography.bodyLarge)
    }
}

// ===================
// Бізнес-логіка середнього рівня
// ===================
fun calculatePowerMedium(radiation: String, efficiency: String): Double {
    val rad = radiation.toDoubleOrNull() ?: 0.0
    val eff = efficiency.toDoubleOrNull()?.div(100) ?: 0.0
    // Просте обчислення потужності з двома параметрами
    return rad * eff
}

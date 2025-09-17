package ua.kpi.practical_example_21.composables

import androidx.compose.runtime.Composable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.random.Random



@Composable
fun AdvancedApp() {
    // Стан даних для інтерактивного дашборду
    var power by remember { mutableStateOf(50f) }
    var temperature by remember { mutableStateOf(25f) }
    var efficiency by remember { mutableStateOf(75f) }

    // Анімовані значення
    val animatedPower by animateFloatAsState(targetValue = power)
    val animatedTemp by animateFloatAsState(targetValue = temperature)
    val animatedEff by animateFloatAsState(targetValue = efficiency)

    // Для демонстрації взаємозв'язку: зміна потужності впливає на ефективність
    LaunchedEffect(power) {
        efficiency = (power + Random.nextFloat() * 20).coerceIn(0f, 100f)
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Інтерактивний дашборд сонячної електростанції", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(16.dp))

        // Потужність
        InteractiveBar("Потужність (кВт)", animatedPower, Color.Yellow) { newValue ->
            power = newValue
        }

        // Температура
        InteractiveBar("Температура (°C)", animatedTemp, Color.Red) { newValue ->
            temperature = newValue
        }

        // Ефективність
        InteractiveBar("Ефективність (%)", animatedEff, Color.Green) { newValue ->
            efficiency = newValue
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Натисніть на бар для отримання прогнозу потужності",
            style = MaterialTheme.typography.bodySmall
        )
    }
}


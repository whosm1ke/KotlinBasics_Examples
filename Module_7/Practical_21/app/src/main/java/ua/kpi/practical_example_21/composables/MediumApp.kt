package ua.kpi.practical_example_21.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.random.Random


@Composable
fun MediumApp() {
    // Стан даних для трьох показників
    var power by remember { mutableStateOf(50f) }
    var temperature by remember { mutableStateOf(25f) }
    var efficiency by remember { mutableStateOf(75f) }

    // Анімовані значення для плавного переходу
    val animatedPower by animateFloatAsState(targetValue = power)
    val animatedTemp by animateFloatAsState(targetValue = temperature)
    val animatedEff by animateFloatAsState(targetValue = efficiency)

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Динамічний монітор сонячної електростанції", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(16.dp))

        // Індикатор потужності
        AnimatedBar("Потужність (кВт)", animatedPower, Color.Yellow)
        // Індикатор температури
        AnimatedBar("Температура (°C)", animatedTemp, Color.Red)
        // Індикатор ефективності
        AnimatedBar("Ефективність (%)", animatedEff, Color.Green)

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка для генерації нових випадкових даних
        Button(onClick = {
            power = Random.nextFloat() * 100
            temperature = Random.nextFloat() * 50
            efficiency = Random.nextFloat() * 100
        }) {
            Text("Оновити дані")
        }
    }
}
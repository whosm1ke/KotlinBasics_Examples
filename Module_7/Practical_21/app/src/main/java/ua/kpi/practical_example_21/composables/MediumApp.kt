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
    // Створюємо стан для трьох ключових показників сонячної електростанції
    var power by remember { mutableStateOf(50f) }          // Потужність у кВт
    var temperature by remember { mutableStateOf(25f) }     // Температура в градусах Цельсія
    var efficiency by remember { mutableStateOf(75f) }       // Ефективність у відсотках

    // Анімовані значення для плавного переходу між старими та новими значеннями
    val animatedPower by animateFloatAsState(targetValue = power)
    val animatedTemp by animateFloatAsState(targetValue = temperature)
    val animatedEff by animateFloatAsState(targetValue = efficiency)

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Відображаємо заголовок додатку
        Text(text = "Динамічний монітор сонячної електростанції", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(16.dp))

        // Відображаємо індикатор потужності з жовтим кольором
        AnimatedBar("Потужність (кВт)", animatedPower, Color.Yellow)
        // Відображаємо індикатор температури з червоним кольором
        AnimatedBar("Температура (°C)", animatedTemp, Color.Red)
        // Відображаємо індикатор ефективності з зеленим кольором
        AnimatedBar("Ефективність (%)", animatedEff, Color.Green)

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка для оновлення даних випадковими значеннями
        Button(onClick = {
            power = Random.nextFloat() * 100         // Генеруємо нове випадкове значення потужності від 0 до 100
            temperature = Random.nextFloat() * 50    // Генеруємо нове випадкове значення температури від 0 до 50
            efficiency = Random.nextFloat() * 100     // Генеруємо нове випадкове значення ефективності від 0 до 100
        }) {
            Text("Оновити дані")
        }
    }
}
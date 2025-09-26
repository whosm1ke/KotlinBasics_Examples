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
    // Створюємо стан для трьох ключових параметрів сонячної електростанції
    // Використовуємо remember для збереження стану між перерисуваннями Composable-функцій
    var power by remember { mutableStateOf(50f) }           // Потужність у кВт
    var temperature by remember { mutableStateOf(25f) }     // Температура в градусах Цельсія
    var efficiency by remember { mutableStateOf(75f) }      // Ефективність у відсотках

    // Анімовані значення для плавного переходу між значеннями
    val animatedPower by animateFloatAsState(targetValue = power)
    val animatedTemp by animateFloatAsState(targetValue = temperature)
    val animatedEff by animateFloatAsState(targetValue = efficiency)

    // Використовуємо LaunchedEffect для відстеження зміни потужності
    // При зміні потужності автоматично перераховується ефективність
    LaunchedEffect(power) {
        // Генеруємо випадкове значення для ефективності, що залежить від потужності
        efficiency = (power + Random.nextFloat() * 20).coerceIn(0f, 100f)
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Відображаємо заголовок дашборду
        Text("Інтерактивний дашборд сонячної електростанції", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(16.dp))

        // Відображаємо інтерактивний бар для потужності з анімацією
        InteractiveBar("Потужність (кВт)", animatedPower, Color.Yellow) { newValue ->
            power = newValue  // Оновлюємо стан потужності при зміні значення
        }

        // Відображаємо інтерактивний бар для температури
        InteractiveBar("Температура (°C)", animatedTemp, Color.Red) { newValue ->
            temperature = newValue  // Оновлюємо стан температури при зміні значення
        }

        // Відображаємо інтерактивний бар для ефективності
        InteractiveBar("Ефективність (%)", animatedEff, Color.Green) { newValue ->
            efficiency = newValue  // Оновлюємо стан ефективності при зміні значення
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Додатковий текст для інструкції користувачу
        Text(
            text = "Натисніть на бар для отримання прогнозу потужності",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
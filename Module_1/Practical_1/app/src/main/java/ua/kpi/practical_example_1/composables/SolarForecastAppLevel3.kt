package ua.kpi.practical_example_1.composables

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Composable
fun SolarForecastAppLevel3() {
    // Створюємо стан для зберігання прогнозу потужності, ініціалізуємо значення 0
    var powerForecast by remember { mutableStateOf(0) }

    // Анімуємо зміну значення потужності при зміні, щоб забезпечити плавний перехід між значеннями
    val animatedPower by animateIntAsState(
        targetValue = powerForecast, // Цільове значення для анімації
        label = "PowerAnimation" // Мітка для відлагодження
    )

    Column(
        modifier = Modifier
            .fillMaxSize() // Заповнює весь доступний простір
            .padding(20.dp), // Додає внутрішній відступ
        horizontalAlignment = Alignment.CenterHorizontally, // Вирівнювання по центру горизонтально
        verticalArrangement = Arrangement.Center // Вирівнювання по центру вертикально
    ) {
        // Відображаємо заголовок додатку
        Text(
            text = "Сонячна електростанція",
            fontSize = 26.sp,
            color = MaterialTheme.colorScheme.primary // Використовуємо первинний колір теми
        )

        Spacer(modifier = Modifier.height(16.dp)) // Простір між елементами

        // Відображаємо прогноз потужності з анімацією
        Text(
            text = "Прогноз потужності: $animatedPower Вт",
            fontSize = 22.sp,
            color = MaterialTheme.colorScheme.onBackground // Колір тексту відповідно до теми
        )

        Spacer(modifier = Modifier.height(24.dp)) // Простір між елементами

        // Кнопка для оновлення прогнозу
        Button(onClick = {
            // При натисканні на кнопку генеруємо нове випадкове значення потужності в діапазоні 100-5000 Вт
            powerForecast = Random.nextInt(100, 5000)
        }) {
            Text("Оновити прогноз") // Текст кнопки
        }
    }
}
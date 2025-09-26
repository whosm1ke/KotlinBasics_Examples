package ua.kpi.practical_example_3.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.kpi.practical_example_3.R

@Composable
fun BasicEnergyApp() {
    // Створюємо змінний стан для тексту, який буде змінюватися при натисканні кнопки
    var powerText by remember { mutableStateOf("Поточна потужність: 0 кВт") }

    // Основний контейнер Column для розміщення елементів
    Column(
        modifier = Modifier
            .fillMaxSize()           // Заповнює весь доступний простір
            .padding(16.dp),         // Додає внутрішній відступ 16 dp
        horizontalAlignment = Alignment.CenterHorizontally,  // Вирівнювання по горизонталі по центру
        verticalArrangement = Arrangement.Center  // Вирівнювання по вертикалі по центру
    ) {
        // Відображення заголовка додатку
        Text(
            text = "Сонячна електростанція",
            fontSize = 24.sp,              // Розмір шрифту 24 sp
            fontWeight = FontWeight.Bold   // Жирний стиль шрифту
        )

        Spacer(modifier = Modifier.height(16.dp))  // Простір між елементами (16 dp)

        // Відображення зображення сонячної панелі з ресурсів drawable
        Image(
            painter = painterResource(id = R.drawable.solar_panel),  // Використовуємо зображення з ресурсів
            contentDescription = "Сонячна панель",  // Опис для доступності
            modifier = Modifier
                .height(200.dp)         // Встановлюємо висоту зображення
                .fillMaxWidth()         // Заповнює ширину контейнера
        )

        Spacer(modifier = Modifier.height(16.dp))  // Простір між елементами

        // Відображення динамічного тексту, що змінюється при натисканні кнопки
        Text(
            text = powerText,
            fontSize = 18.sp   // Розмір шрифту 18 sp
        )

        Spacer(modifier = Modifier.height(16.dp))  // Простір між елементами

        // Кнопка для оновлення тексту
        Button(
            onClick = {  // Подія натискання кнопки
                powerText = "Прогнозована потужність: 50 кВт"  // Змінюємо значення тексту
            }
        ) {
            Text(text = "Оновити прогноз")  // Текст на кнопці
        }
    }
}
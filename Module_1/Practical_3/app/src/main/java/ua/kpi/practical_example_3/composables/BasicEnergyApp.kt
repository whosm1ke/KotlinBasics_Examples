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
    // Стан тексту, який змінюється при натисканні кнопки
    var powerText by remember { mutableStateOf("Поточна потужність: 0 кВт") }

    // Основний контейнер Column
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Заголовок
        Text(
            text = "Сонячна електростанція",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Зображення сонячної панелі (розміщено у ресурсах drawable)
        Image(
            painter = painterResource(id = R.drawable.solar_panel),
            contentDescription = "Сонячна панель",
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Динамічний текст
        Text(
            text = powerText,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка для зміни тексту
        Button(
            onClick = {
                powerText = "Прогнозована потужність: 50 кВт"
            }
        ) {
            Text(text = "Оновити прогноз")
        }
    }
}
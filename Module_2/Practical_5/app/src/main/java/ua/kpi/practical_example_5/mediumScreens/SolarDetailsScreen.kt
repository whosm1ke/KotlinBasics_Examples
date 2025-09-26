package ua.kpi.practical_example_5.mediumScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ua.kpi.practical_example_5.Screen

@Composable
fun SolarDetailsScreen(navController: NavHostController, panelsCount: Int) {
    // Обчислення загальної потужності на основі кількості сонячних панелей
    // Припускається, що одна панель виробляє 0.3 кВт енергії
    val totalPower = panelsCount * 0.3

    Column(
        modifier = Modifier
            .fillMaxSize() // Заповнює весь доступний простір
            .padding(16.dp), // Додає внутрішній відступ 16.dp
        verticalArrangement = Arrangement.Center, // Вирівнювання по вертикалі по центру
        horizontalAlignment = Alignment.CenterHorizontally // Вирівнювання по горизонталі по центру
    ) {
        // Відображення заголовка сторінки деталей прогнозу
        Text("Деталі прогнозу", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp)) // Простір між елементами

        // Відображення кількості сонячних панелей
        Text("Кількість панелей: $panelsCount", fontSize = 16.sp)
        // Відображення очікуваної потужності з округленням до одного знаку після коми
        Text("Очікувана потужність: %.1f кВт".format(totalPower), fontSize = 16.sp)

        Spacer(modifier = Modifier.height(16.dp)) // Простір між елементами

        // Кнопка для повернення до екрану прогнозу
        Button(onClick = { navController.navigate("${Screen.MediumScreen.SolarForecast.route}/Anonymous") }) {
            Text("Повернутися до прогнозу")
        }
    }
}
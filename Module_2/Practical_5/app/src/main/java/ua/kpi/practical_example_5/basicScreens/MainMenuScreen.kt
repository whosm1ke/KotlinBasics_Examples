package ua.kpi.practical_example_5.basicScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
fun MainMenuScreen(navController: NavHostController) {
    // Використовуємо Column для вертикального розміщення елементів
    Column(
        modifier = Modifier
            .fillMaxSize()  // Заповнює весь доступний простір
            .padding(16.dp), // Додає внутрішній відступ
        verticalArrangement = Arrangement.Center, // Вирівнювання по вертикалі по центру
        horizontalAlignment = Alignment.CenterHorizontally // Вирівнювання по горизонталі по центру
    ) {
        // Відображаємо заголовок головного меню
        Text(
            text = "Головне меню",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp) // Відступ знизу для відділення від кнопок
        )

        // Кнопка для переходу на екран інформації про користувача
        Button(
            onClick = { navController.navigate(Screen.BasicScreen.UserInfo.route) }, // Навігація до екрану користувача
            modifier = Modifier
                .fillMaxWidth()  // Заповнює всю доступну ширину
                .padding(vertical = 8.dp) // Відступи зверху і знизу для кнопки
        ) {
            Text("Інформація про користувача") // Текст на кнопці
        }

        // Кнопка для переходу на екран прогнозу потужності СЕС
        Button(
            onClick = { navController.navigate(Screen.BasicScreen.SolarForecast.route) }, // Навігація до екрану прогнозу
            modifier = Modifier
                .fillMaxWidth()  // Заповнює всю доступну ширину
                .padding(vertical = 8.dp) // Відступи зверху і знизу для кнопки
        ) {
            Text("Прогноз потужності СЕС") // Текст на кнопці
        }
    }
}
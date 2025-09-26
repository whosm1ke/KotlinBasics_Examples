package ua.kpi.practical_example_5.basicScreens

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

/**
 * Composable-функція, що відображає екран прогнозу потужності сонячної електростанції.
 * Він містить заголовок, прогноз на сьогодні та завтра, а також кнопку для повернення в головне меню.
 *
 * @param navController Навігаційний контролер для керування переходами між екранами.
 */
@Composable
fun SolarForecastScreen(navController: NavHostController) {
    // Використовуємо Column для вертикального розміщення елементів з центруванням
    Column(
        modifier = Modifier
            .fillMaxSize()  // Заповнює весь доступний простір
            .padding(16.dp), // Додає внутрішній відступ
        verticalArrangement = Arrangement.Center,  // Центрує елементи вертикально
        horizontalAlignment = Alignment.CenterHorizontally  // Центрує елементи горизонтально
    ) {
        // Відображаємо заголовок екрану
        Text("Прогноз потужності Сонячної електростанції",
            fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp)) // Додає простір між елементами

        // Відображаємо прогноз потужності на сьогодні
        Text("Сьогодні прогнозована потужність: 5.2 кВт", fontSize = 16.sp)
        // Відображаємо прогноз потужності на завтра
        Text("Завтра прогнозована потужність: 4.8 кВт", fontSize = 16.sp)

        Spacer(modifier = Modifier.height(16.dp)) // Додає простір між елементами

        // Кнопка для повернення до головного екрану
        Button(onClick = { navController.navigate(Screen.BasicScreen.MainScreen.route) }) {
            Text("Повернутися в меню")
        }
    }
}
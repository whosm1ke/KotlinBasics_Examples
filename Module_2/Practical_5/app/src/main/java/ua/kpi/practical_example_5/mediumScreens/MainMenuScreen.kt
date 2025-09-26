package ua.kpi.practical_example_5.mediumScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
 * Composable-функція, що відображає головне меню додатку.
 * Використовується для навігації між різними екранами.
 *
 * @param navController контролер навігації, який дозволяє переходити між екранами
 */
@Composable
fun MainMenuScreen(navController: NavHostController) {
    // Встановлюємо вертикальну колонку з вирівнюванням по центру
    Column(
        modifier = Modifier
            .fillMaxSize()  // Заповнює весь доступний простір
            .padding(16.dp), // Додає внутрішній відступ
        verticalArrangement = Arrangement.Center, // Вирівнювання по вертикалі по центру
        horizontalAlignment = Alignment.CenterHorizontally // Вирівнювання по горизонталі по центру
    ) {
        // Відображає заголовок головного меню
        Text("Головне меню", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        // Додає простір між заголовком і кнопками
        Spacer(modifier = Modifier.height(24.dp))

        // Кнопка для переходу до екрана інформації про користувача
        Button(
            onClick = { navController.navigate(Screen.MediumScreen.UserInfo.route) }, // Навігація на екран користувача
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp) // Заповнює ширину і має вертикальний відступ
        ) {
            Text("Інформація про користувача") // Текст кнопки
        }

        // Кнопка для переходу до екрана прогнозу потужності СЕС
        Button(
            onClick = { navController.navigate("${Screen.MediumScreen.SolarForecast.route}/Anonymous") }, // Навігація з параметром Anonymous
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp) // Заповнює ширину і має вертикальний відступ
        ) {
            Text("Прогноз потужності СЕС") // Текст кнопки
        }
    }
}
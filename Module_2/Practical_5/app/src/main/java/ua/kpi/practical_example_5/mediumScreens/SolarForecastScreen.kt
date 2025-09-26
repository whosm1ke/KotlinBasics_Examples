package ua.kpi.practical_example_5.mediumScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ua.kpi.practical_example_5.Screen

@Composable
fun SolarForecastScreen(navController: NavHostController, userName: String) {
    // Змінна для зберігання введеного користувачем значення кількості панелей
    var panelsCountInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize() // Заповнює весь доступний простір екрану
            .padding(16.dp), // Додає внутрішній відступ 16.dp
        verticalArrangement = Arrangement.Center, // Вирівнювання по вертикалі по центру
        horizontalAlignment = Alignment.CenterHorizontally // Вирівнювання по горизонталі по центру
    ) {
        // Відображення привітання з ім'ям користувача
        Text("Привіт, $userName!", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        // Відображення заголовка екрану
        Text("Прогноз потужності Сонячної електростанції", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(16.dp)) // Простір між елементами

        // Поле вводу для кількості сонячних панелей
        OutlinedTextField(
            value = panelsCountInput, // Поточне значення у полі вводу
            onValueChange = { panelsCountInput = it }, // Оновлення значення при зміні
            label = { Text("Кількість панелей") }, // Мітка до поля вводу
            modifier = Modifier.fillMaxWidth() // Заповнює всю доступну ширину
        )

        Spacer(modifier = Modifier.height(16.dp)) // Простір між елементами

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) { // Ряд кнопок з відстанню між ними
            Button(
                onClick = { 
                    // Навігація до екрану деталей прогнозу потужності
                    // Передається кількість панелей, якщо введено число, інакше 0
                    navController.navigate("${Screen.MediumScreen.SolarDetails.route}/${panelsCountInput.toIntOrNull() ?: 0}") 
                }
            ) {
                Text("Розрахувати потужність") // Текст кнопки розрахунку
            }

            Button(onClick = { 
                // Навігація назад до головного меню
                navController.navigate(Screen.MediumScreen.MainScreen.route) 
            }) {
                Text("Повернутися в меню") // Текст кнопки повернення
            }
        }
    }
}
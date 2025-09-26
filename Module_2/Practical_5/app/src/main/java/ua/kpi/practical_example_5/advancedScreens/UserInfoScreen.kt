package ua.kpi.practical_example_5.advancedScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ua.kpi.practical_example_5.Screen
import ua.kpi.practical_example_5.components.MenuButton
import ua.kpi.practical_example_5.components.ScreenTitle

@Composable
fun UserInfoScreen(navController: NavHostController) {
    // Створюємо змінні для зберігання введених даних користувача
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()           // Заповнює весь доступний простір
            .padding(16.dp)          // Додає внутрішній відступ 16 dp
            .verticalScroll(rememberScrollState()), // Дозволяє прокручувати вміст вертикально
        verticalArrangement = Arrangement.Center // Вирівнювання елементів по центру вертикально
    ) {
        // Відображаємо заголовок екрану
        ScreenTitle("Інформація про користувача")

        // Поле для вводу імені користувача
        OutlinedTextField(
            value = name,                           // Поточне значення поля
            onValueChange = { name = it },          // Оновлення значення при зміні
            label = { Text("Ім'я") },              // Мітка до поля
            modifier = Modifier.fillMaxWidth()      // Заповнює всю доступну ширину
        )

        Spacer(modifier = Modifier.height(8.dp)) // Простір між елементами

        // Поле для вводу email користувача
        OutlinedTextField(
            value = email,                          // Поточне значення поля
            onValueChange = { email = it },         // Оновлення значення при зміні
            label = { Text("Email") },             // Мітка до поля
            modifier = Modifier.fillMaxWidth()      // Заповнює всю доступну ширину
        )

        Spacer(modifier = Modifier.height(16.dp)) // Простір між елементами

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) { // Рядок з кнопками
            // Кнопка для повернення до головного меню
            MenuButton("Повернутися в меню") { 
                navController.navigate(Screen.AdvancedScreen.MainScreen.route) 
            }
            
            // Кнопка для переходу до екрану прогнозу, передаючи ім'я користувача
            MenuButton("Перейти до прогнозу") {
                navController.navigate("${Screen.AdvancedScreen.UserInfo.route}/${name.ifEmpty { "Anonymous" }}")
            }
        }
    }
}
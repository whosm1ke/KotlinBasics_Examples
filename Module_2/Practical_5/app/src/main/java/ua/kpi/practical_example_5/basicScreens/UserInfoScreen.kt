package ua.kpi.practical_example_5.basicScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
fun UserInfoScreen(navController: NavHostController) {
    // Створюємо змінні стану для зберігання значень полів вводу імені та email
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Відображаємо заголовок екрану
        Text("Інформація про користувача", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        // Поле вводу для імені користувача з підписом
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },  // Оновлення стану при зміні тексту
            label = { Text("Ім'я") },
            modifier = Modifier.fillMaxWidth()  // Заповнює всю доступну ширину
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Поле вводу для email користувача з підписом
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },  // Оновлення стану при зміні тексту
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()  // Заповнює всю доступну ширину
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка для повернення до головного екрану
        Button(
            onClick = { navController.navigate(Screen.BasicScreen.MainScreen.route) },  // Навігація на головний екран
            modifier = Modifier.align(Alignment.CenterHorizontally)  // Вирівнювання кнопки по центру
        ) {
            Text("Повернутися в меню")
        }
    }
}
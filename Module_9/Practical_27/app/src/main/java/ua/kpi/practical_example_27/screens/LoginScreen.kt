package ua.kpi.practical_example_27.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_27.models.User
import ua.kpi.practical_example_27.viewModel.ForecastViewModel

@Composable
fun LoginScreen(viewModel: ForecastViewModel) {
    // Створюємо змінні для зберігання введених даних користувача
    var username by remember { mutableStateOf("") }  // Змінна для зберігання імені користувача
    var password by remember { mutableStateOf("") }  // Змінна для зберігання пароля

    // Основний колонковий макет екрану входу
    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),  // Заповнює весь екран з відступами 32.dp
        verticalArrangement = Arrangement.Center,  // Вирівнювання по вертикалі по центру
        horizontalAlignment = Alignment.CenterHorizontally  // Вирівнювання по горизонталі по центру
    ) {
        // Відображаємо заголовок додатку
        Text("Сонячна електростанція", style = MaterialTheme.typography.titleLarge)
        
        // Простір між елементами
        Spacer(modifier = Modifier.height(16.dp))
        
        // Поле вводу для імені користувача
        OutlinedTextField(
            value = username,  // Поточне значення поля
            onValueChange = { username = it },  // Оновлення значення при зміні
            label = { Text("Користувач") }  // Мітка до поля
        )
        
        // Простір між елементами
        Spacer(modifier = Modifier.height(8.dp))
        
        // Поле вводу для пароля з прихованим вмістом
        OutlinedTextField(
            value = password,  // Поточне значення поля
            onValueChange = { password = it },  // Оновлення значення при зміні
            label = { Text("Пароль") },  // Мітка до поля
            visualTransformation = PasswordVisualTransformation()  // Приховує символи пароля
        )
        
        // Простір між елементами
        Spacer(modifier = Modifier.height(16.dp))
        
        // Кнопка входу, яка викликає метод login у ViewModel з переданим користувачем
        Button(onClick = {
            viewModel.login(User(username, password))  // Виклик методу login з параметрами
        }) {
            Text("Увійти")  // Текст на кнопці
        }
    }
}
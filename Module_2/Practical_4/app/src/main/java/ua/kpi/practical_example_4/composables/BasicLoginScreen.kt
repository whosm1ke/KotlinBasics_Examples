package ua.kpi.practical_example_4.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BasicLoginScreen() {
    // Створюємо вертикальний контейнер для розміщення елементів
    Column(
        modifier = Modifier
            .fillMaxSize()  // Заповнює весь доступний простір
            .padding(16.dp), // Додає внутрішній відступ
        horizontalAlignment = Alignment.CenterHorizontally, // Вирівнювання по горизонталі по центру
        verticalArrangement = Arrangement.Center // Вирівнювання по вертикалі по центру
    ) {
        // Відображаємо заголовок сторінки авторизації
        Text(
            text = "Авторизація для Сонячної електростанції",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 32.dp) // Відступ знизу
        )

        // Створюємо змінні для зберігання значень полів вводу та повідомлення
        var login by remember { mutableStateOf("") }         // Логін користувача
        var password by remember { mutableStateOf("") }      // Пароль користувача
        var message by remember { mutableStateOf("") }       // Повідомлення про результат авторизації

        // Поле вводу для логіна
        OutlinedTextField(
            value = login,  // Значення поля вводу
            onValueChange = { login = it }, // Оновлення значення при зміні
            label = { Text("Логін") },      // Мітка поля
            singleLine = true,              // Дозволяє вводити лише один рядок
            modifier = Modifier.fillMaxWidth() // Заповнює весь доступний простір по ширині
        )

        Spacer(modifier = Modifier.height(16.dp)) // Простір між полями

        // Поле вводу для пароля з прихованим відображенням символів
        OutlinedTextField(
            value = password,  // Значення поля вводу
            onValueChange = { password = it }, // Оновлення значення при зміні
            label = { Text("Пароль") },       // Мітка поля
            visualTransformation = PasswordVisualTransformation(), // Приховує символи пароля
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password), // Встановлює тип клавіатури для пароля
            singleLine = true,               // Дозволяє вводити лише один рядок
            modifier = Modifier.fillMaxWidth() // Заповнює весь доступний простір по ширині
        )

        Spacer(modifier = Modifier.height(24.dp)) // Простір між полями та кнопкою

        // Кнопка "Увійти" з функцією обробки натискання
        Button(
            onClick = {
                // Перевіряємо, чи заповнені обидва поля
                message = if (login.isBlank() || password.isBlank()) {
                    "Будь ласка, заповніть усі поля"  // Повідомлення про незаповнені поля
                } else {
                    "Авторизація успішна!" // Повідомлення про успішну авторизацію
                }
            },
            modifier = Modifier.fillMaxWidth() // Заповнює весь доступний простір по ширині
        ) {
            Text("Увійти") // Текст на кнопці
        }

        Spacer(modifier = Modifier.height(16.dp)) // Простір після кнопки

        // Відображаємо повідомлення з результатом авторизації
        Text(
            text = message,  // Текст повідомлення
            color = if (message.contains("успішна")) MaterialTheme.colorScheme.primary  // Колір тексту залежно від результату
            else MaterialTheme.colorScheme.error // Кольори для помилки
        )
    }
}
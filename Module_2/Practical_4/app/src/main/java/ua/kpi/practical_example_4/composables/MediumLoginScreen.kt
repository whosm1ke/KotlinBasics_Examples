package ua.kpi.practical_example_4.composables

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MediumLoginScreen() {
    // Стан для перемикання між режимом авторизації та реєстрації
    var isRegisterMode by remember { mutableStateOf(false) }

    // Колонка для вертикального розташування всіх елементів
    Column(
        modifier = Modifier
            .fillMaxSize() // займає весь екран
            .padding(16.dp), // відступи з усіх сторін
        horizontalAlignment = Alignment.CenterHorizontally, // центрування по горизонталі
        verticalArrangement = Arrangement.Center // центрування по вертикалі
    ) {
        // ================= Реєстрація =================
        if (isRegisterMode) {
            // Заголовок екрану
            Text(
                "Реєстрація Сонячної електростанції",
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // State для всіх полів введення
            var name by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var confirmPassword by remember { mutableStateOf("") }
            var message by remember { mutableStateOf("") } // повідомлення про помилки або успіх

            // Поле введення імені
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Ім'я") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp)) // відступ

            // Поле введення email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Поле введення пароля
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Пароль") },
                visualTransformation = PasswordVisualTransformation(), // маскування пароля
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Поле підтвердження пароля
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Підтвердження пароля") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Кнопка реєстрації
            Button(
                onClick = {
                    // Перевірка полів: не порожні та паролі співпадають
                    message = when {
                        name.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank() ->
                            "Заповніть усі поля"
                        password != confirmPassword -> "Паролі не співпадають"
                        else -> "Реєстрація успішна!"
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Зареєструватися")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Кнопка для переходу до авторизації
            TextButton(onClick = { isRegisterMode = false }) {
                Text("Вже є акаунт? Увійти")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Відображення повідомлення
            Text(
                text = message,
                color = if (message.contains("успішна"))
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.error
            )
        } else {
            // ================= Авторизація =================
            Text(
                text = "Авторизація Сонячної електростанції",
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // State для полів логіну та пароля
            var login by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var message by remember { mutableStateOf("") } // повідомлення про помилки або успіх

            // Поле введення логіну
            OutlinedTextField(
                value = login,
                onValueChange = { login = it },
                label = { Text("Логін") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Поле введення пароля
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Пароль") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Кнопка авторизації
            Button(
                onClick = {
                    message = if (login.isBlank() || password.isBlank()) {
                        "Будь ласка, заповніть усі поля"
                    } else {
                        "Авторизація успішна!"
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Увійти")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Кнопка для переходу до реєстрації
            TextButton(onClick = { isRegisterMode = true }) {
                Text("Ще немає акаунту? Зареєструватися")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Відображення повідомлення
            Text(
                text = message,
                color = if (message.contains("успішна"))
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.error
            )
        }

    }
}
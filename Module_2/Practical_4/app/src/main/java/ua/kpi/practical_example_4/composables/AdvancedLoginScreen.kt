package ua.kpi.practical_example_4.composables

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import ua.kpi.practical_example_4.forms.LoginForm
import ua.kpi.practical_example_4.forms.RegistrationForm

@Composable
fun AdvancedLoginScreen() {
    // Стан для визначення, чи знаходимося ми у режимі реєстрації
    var isRegisterMode by remember { mutableStateOf(false) }
    
    // Стан для показу індикатора завантаження
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Якщо у режимі реєстрації, відображаємо форму реєстрації
        if (isRegisterMode) {
            RegistrationForm(
                onRegister = { name, email, password, confirmPassword ->
                    isLoading = true
                    // Імітація обробки (замість реального API)
                    isLoading = false
                    when {
                        name.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank() -> "Заповніть усі поля"
                        password != confirmPassword -> "Паролі не співпадають"
                        else -> "Реєстрація успішна!"
                    }
                },
                onSwitchToLogin = { isRegisterMode = false }
            )
        } else {
            // Якщо у режимі входу, відображаємо форму авторизації
            LoginForm(
                onLogin = { login, password ->
                    isLoading = true
                    // Імітація обробки
                    isLoading = false
                    if (login.isBlank() || password.isBlank()) "Заповніть усі поля" else "Авторизація успішна!"
                },
                onSwitchToRegister = { isRegisterMode = true }
            )
        }

        // Якщо виконується завантаження, показуємо індикатор
        if (isLoading) {
            Spacer(modifier = Modifier.height(16.dp))
            CircularProgressIndicator()
        }
    }
}

// ======= Компоненти для просунутого рівня =======
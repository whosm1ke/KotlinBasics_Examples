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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Авторизація для Сонячної електростанції",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        var login by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var message by remember { mutableStateOf("") }

        OutlinedTextField(
            value = login,
            onValueChange = { login = it },
            label = { Text("Логін") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Пароль") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

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

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = message,
            color = if (message.contains("успішна")) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.error
        )
    }
}



package ua.kpi.practical_example_6.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun MediumApp() {
    var stationName by remember { mutableStateOf(TextFieldValue("")) }
    var operatorEmail by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var powerCapacity by remember { mutableStateOf(TextFieldValue("")) }

    // Стан помилок
    var stationNameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var powerError by remember { mutableStateOf(false) }

    var isSubmitted by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        // Заголовок
        Text(
            text = "Реєстрація сонячної електростанції",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Поле для назви станції
        OutlinedTextField(
            value = stationName,
            onValueChange = {
                stationName = it
                stationNameError = false
            },
            label = { Text("Назва станції") },
            isError = stationNameError,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Введіть офіційну назву вашої СЕС",
            style = MaterialTheme.typography.bodySmall,
            color = if (stationNameError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Використовуємо Row для Email + Пароль поруч
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                OutlinedTextField(
                    value = operatorEmail,
                    onValueChange = {
                        operatorEmail = it
                        emailError = false
                    },
                    label = { Text("Email оператора") },
                    isError = emailError,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = if (emailError) "Некоректний email" else "Формат: example@mail.com",
                    style = MaterialTheme.typography.bodySmall,
                    color = if (emailError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        passwordError = false
                    },
                    label = { Text("Пароль") },
                    isError = passwordError,
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation() // приховуємо символи
                )
                Text(
                    text = if (passwordError) "Пароль занадто короткий" else "Мін. довжина: 6 символів",
                    style = MaterialTheme.typography.bodySmall,
                    color = if (passwordError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Поле для потужності станції
        OutlinedTextField(
            value = powerCapacity,
            onValueChange = {
                powerCapacity = it
                powerError = false
            },
            label = { Text("Потужність станції (кВт)") },
            isError = powerError,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = if (powerError) "Введіть число більше 0" else "Наприклад: 5000",
            style = MaterialTheme.typography.bodySmall,
            color = if (powerError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка підтвердження
        Button(
            onClick = {
                isSubmitted = false
                var valid = true

                // Валідація
                if (stationName.text.isBlank()) {
                    stationNameError = true
                    valid = false
                }
                if (!operatorEmail.text.contains("@")) {
                    emailError = true
                    valid = false
                }
                if (password.text.length < 6) {
                    passwordError = true
                    valid = false
                }
                val powerValue = powerCapacity.text.toIntOrNull()
                if (powerValue == null || powerValue <= 0) {
                    powerError = true
                    valid = false
                }

                if (valid) {
                    isSubmitted = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Зареєструвати")
        }

        // Повідомлення про результат
        if (isSubmitted) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Станція успішно зареєстрована!",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
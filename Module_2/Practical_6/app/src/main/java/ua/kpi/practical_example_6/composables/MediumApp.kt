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
    // Створюємо змінні стану для полів введення
    var stationName by remember { mutableStateOf(TextFieldValue("")) }
    var operatorEmail by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var powerCapacity by remember { mutableStateOf(TextFieldValue("")) }

    // Стан помилок для кожного поля
    var stationNameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var powerError by remember { mutableStateOf(false) }

    // Стан, що вказує, чи була надіслана форма
    var isSubmitted by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        // Відображаємо заголовок додатку
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
                stationNameError = false // Скидаємо помилку при введенні
            },
            label = { Text("Назва станції") },
            isError = stationNameError,
            modifier = Modifier.fillMaxWidth()
        )
        // Підпис до поля назви станції
        Text(
            text = "Введіть офіційну назву вашої СЕС",
            style = MaterialTheme.typography.bodySmall,
            color = if (stationNameError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Використовуємо Row для розміщення Email та Пароля поруч
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                OutlinedTextField(
                    value = operatorEmail,
                    onValueChange = {
                        operatorEmail = it
                        emailError = false // Скидаємо помилку при введенні
                    },
                    label = { Text("Email оператора") },
                    isError = emailError,
                    modifier = Modifier.fillMaxWidth()
                )
                // Підпис до поля Email
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
                        passwordError = false // Скидаємо помилку при введенні
                    },
                    label = { Text("Пароль") },
                    isError = passwordError,
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation() // Приховуємо символи пароля
                )
                // Підпис до поля пароля
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
                powerError = false // Скидаємо помилку при введенні
            },
            label = { Text("Потужність станції (кВт)") },
            isError = powerError,
            modifier = Modifier.fillMaxWidth()
        )
        // Підпис до поля потужності
        Text(
            text = if (powerError) "Введіть число більше 0" else "Наприклад: 5000",
            style = MaterialTheme.typography.bodySmall,
            color = if (powerError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка підтвердження реєстрації
        Button(
            onClick = {
                isSubmitted = false // Скидаємо стан відправлення
                var valid = true // Прапорець валідності

                // Валідація полів
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
                    isSubmitted = true // Якщо валідація пройшла, встановлюємо прапорець успішного відправлення
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Зареєструвати")
        }

        // Відображаємо повідомлення про успішну реєстрацію
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
package ua.kpi.practical_example_6.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun BasicApp() {
    // Стан для першого поля (назва станції)
    var stationName by remember { mutableStateOf(TextFieldValue("")) }
    // Стан для другого поля (email оператора)
    var operatorEmail by remember { mutableStateOf(TextFieldValue("")) }

    // Стан для повідомлень про помилки
    var stationNameError by remember { mutableStateOf(false) }
    var operatorEmailError by remember { mutableStateOf(false) }

    // Стан для повідомлення про успішне відправлення
    var isSubmitted by remember { mutableStateOf(false) }

    // Основний контейнер
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), // Відступи
        verticalArrangement = Arrangement.Center
    ) {
        // Заголовок форми
        Text(
            text = "Реєстрація сонячної електростанції",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Поле для введення назви станції
        OutlinedTextField(
            value = stationName,
            onValueChange = {
                stationName = it
                stationNameError = false // При зміні очищаємо помилку
            },
            label = { Text("Назва станції") },
            isError = stationNameError, // Червона рамка при помилці
            modifier = Modifier.fillMaxWidth()
        )
        // Якщо є помилка – показуємо текст
        if (stationNameError) {
            Text(
                text = "Поле не може бути порожнім",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Поле для введення email
        OutlinedTextField(
            value = operatorEmail,
            onValueChange = {
                operatorEmail = it
                operatorEmailError = false // При зміні очищаємо помилку
            },
            label = { Text("Email оператора") },
            isError = operatorEmailError,
            modifier = Modifier.fillMaxWidth()
        )
        if (operatorEmailError) {
            Text(
                text = "Поле не може бути порожнім",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка підтвердження
        Button(
            onClick = {
                // Скидаємо попереднє повідомлення
                isSubmitted = false
                // Перевірка на порожність полів
                var valid = true
                if (stationName.text.isBlank()) {
                    stationNameError = true
                    valid = false
                }
                if (operatorEmail.text.isBlank()) {
                    operatorEmailError = true
                    valid = false
                }
                // Якщо все валідно – показуємо повідомлення про успіх
                if (valid) {
                    isSubmitted = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Підтвердити")
        }

        // Повідомлення про результат
        if (isSubmitted) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Сонячна станція успішно зареєстрована!",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
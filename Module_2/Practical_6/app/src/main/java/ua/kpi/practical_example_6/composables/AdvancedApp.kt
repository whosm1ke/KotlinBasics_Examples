package ua.kpi.practical_example_6.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ua.kpi.practical_example_6.components.EmailField
import ua.kpi.practical_example_6.components.FormHeader
import ua.kpi.practical_example_6.components.LoadingIndicator
import ua.kpi.practical_example_6.components.PasswordField
import ua.kpi.practical_example_6.components.PowerField
import ua.kpi.practical_example_6.components.StationNameField
import ua.kpi.practical_example_6.components.SubmitButton

@Composable
fun AdvancedApp() {
    // Створюємо стан для всіх полів форми з використанням remember для збереження значень між перерисуваннями
    var stationName by remember { mutableStateOf(TextFieldValue("")) }
    var operatorEmail by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var powerCapacity by remember { mutableStateOf(TextFieldValue("")) }

    // Створюємо стан для відображення помилок у полях форми
    var stationNameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var powerError by remember { mutableStateOf(false) }

    // Створюємо стан для відстеження статусу подання форми та завантаження
    var isSubmitted by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    // Отримуємо корутинний скоп для запуску асинхронних операцій
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        // Відображаємо заголовок форми
        FormHeader()

        Spacer(modifier = Modifier.height(16.dp))

        // Відображаємо поле для вводу назви станції з валідацією
        StationNameField(stationName, stationNameError) { stationName = it; stationNameError = false }
        Spacer(modifier = Modifier.height(8.dp))

        // Відображаємо поле для вводу email оператора з валідацією
        EmailField(operatorEmail, emailError) { operatorEmail = it; emailError = false }
        Spacer(modifier = Modifier.height(8.dp))

        // Відображаємо поле для вводу пароля з валідацією
        PasswordField(password, passwordError) { password = it; passwordError = false }
        Spacer(modifier = Modifier.height(8.dp))

        // Відображаємо поле для вводу потужності станції з валідацією
        PowerField(powerCapacity, powerError) { powerCapacity = it; powerError = false }
        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка надсилання форми з валідацією даних
        SubmitButton(
            onSubmit = {
                isSubmitted = false  // Скидаємо стан успішного подання
                var valid = true     // Флаг валідності форми

                // Валідація назви станції: перевіряємо, чи не порожнє значення
                if (stationName.text.isBlank()) {
                    stationNameError = true  // Встановлюємо помилку
                    valid = false            // Позначаємо форму як недійсну
                }

                // Валідація email: перевіряємо наявність символів '@' та '.'
                if (!operatorEmail.text.contains("@") || !operatorEmail.text.contains(".")) {
                    emailError = true       // Встановлюємо помилку
                    valid = false           // Позначаємо форму як недійсну
                }

                // Валідація пароля: перевіряємо довжину, наявність великої літери та цифри
                if (password.text.length < 8 ||
                    !password.text.any { it.isUpperCase() } ||
                    !password.text.any { it.isDigit() }
                ) {
                    passwordError = true    // Встановлюємо помилку
                    valid = false           // Позначаємо форму як недійсну
                }

                // Валідація потужності: перевіряємо, чи є числом і більше 0
                val powerValue = powerCapacity.text.toIntOrNull()
                if (powerValue == null || powerValue <= 0) {
                    powerError = true       // Встановлюємо помилку
                    valid = false           // Позначаємо форму як недійсну
                }

                // Якщо всі валідації пройдені
                if (valid) {
                    // Включаємо індикатор завантаження
                    isLoading = true
                    scope.launch {
                        delay(2000) // Імітуємо затримку 2 секунди
                        isLoading = false   // Завершуємо завантаження
                        isSubmitted = true  // Позначаємо успішне подання
                    }
                }
            }
        )

        // Якщо форма знаходиться у стані завантаження, відображаємо індикатор
        if (isLoading) {
            Spacer(modifier = Modifier.height(16.dp))
            LoadingIndicator()
        }

        // Якщо форма успішно подана, показуємо повідомлення про успіх
        if (isSubmitted) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Станція успішно зареєстрована та перевірена!",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
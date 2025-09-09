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
    // Стан для полів
    var stationName by remember { mutableStateOf(TextFieldValue("")) }
    var operatorEmail by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var powerCapacity by remember { mutableStateOf(TextFieldValue("")) }

    // Стан помилок
    var stationNameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var powerError by remember { mutableStateOf(false) }

    // Стан результату
    var isSubmitted by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        // Заголовок
        FormHeader()

        Spacer(modifier = Modifier.height(16.dp))

        // Поля форми
        StationNameField(stationName, stationNameError) { stationName = it; stationNameError = false }
        Spacer(modifier = Modifier.height(8.dp))

        EmailField(operatorEmail, emailError) { operatorEmail = it; emailError = false }
        Spacer(modifier = Modifier.height(8.dp))

        PasswordField(password, passwordError) { password = it; passwordError = false }
        Spacer(modifier = Modifier.height(8.dp))

        PowerField(powerCapacity, powerError) { powerCapacity = it; powerError = false }
        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка підтвердження з валідацією
        SubmitButton(
            onSubmit = {
                isSubmitted = false
                var valid = true

                // Валідація назви станції
                if (stationName.text.isBlank()) {
                    stationNameError = true
                    valid = false
                }

                // Валідація email
                if (!operatorEmail.text.contains("@") || !operatorEmail.text.contains(".")) {
                    emailError = true
                    valid = false
                }

                // Валідація пароля (>=8 символів, велика літера, цифра)
                if (password.text.length < 8 ||
                    !password.text.any { it.isUpperCase() } ||
                    !password.text.any { it.isDigit() }
                ) {
                    passwordError = true
                    valid = false
                }

                // Валідація потужності
                val powerValue = powerCapacity.text.toIntOrNull()
                if (powerValue == null || powerValue <= 0) {
                    powerError = true
                    valid = false
                }

                if (valid) {
                    // Імітуємо завантаження
                    isLoading = true
                    scope.launch {
                        delay(2000) // пауза 2с
                        isLoading = false
                        isSubmitted = true
                    }
                }
            }
        )

        // Індикатор завантаження
        if (isLoading) {
            Spacer(modifier = Modifier.height(16.dp))
            LoadingIndicator()
        }

        // Повідомлення про результат
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
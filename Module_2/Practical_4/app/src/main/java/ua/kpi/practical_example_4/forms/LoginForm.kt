package ua.kpi.practical_example_4.forms

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginForm(onLogin: (String, String) -> String, onSwitchToRegister: () -> Unit) {
    // Збереження стану для поля логіна
    var login by remember { mutableStateOf("") }
    // Збереження стану для поля паролю
    var password by remember { mutableStateOf("") }
    // Збереження стану для повідомлення про результат авторизації
    var message by remember { mutableStateOf("") }

    // Відображення заголовка форми авторизації
    Text("Авторизація Сонячної електростанції", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

    // Поле вводу для логіна з підписом
    OutlinedTextField(
        value = login,
        onValueChange = { login = it },
        label = { Text("Логін") },
        modifier = Modifier.fillMaxWidth()
    )
    
    // Простір між полями вводу
    Spacer(modifier = Modifier.height(8.dp))
    
    // Поле вводу для паролю з прихованим відображенням символів
    OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Пароль") },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = Modifier.fillMaxWidth()
    )
    
    // Простір між полями вводу та кнопкою
    Spacer(modifier = Modifier.height(16.dp))

    // Кнопка для виконання авторизації
    Button(onClick = { message = onLogin(login, password) }, modifier = Modifier.fillMaxWidth()) {
        Text("Увійти")
    }

    // Простір між кнопкою та посиланням на реєстрацію
    Spacer(modifier = Modifier.height(8.dp))
    
    // Кнопка переходу до форми реєстрації
    TextButton(onClick = onSwitchToRegister) {
        Text("Ще немає акаунту? Зареєструватися")
    }

    // Простір між посиланням та повідомленням
    Spacer(modifier = Modifier.height(16.dp))
    
    // Відображення повідомлення про результат авторизації
    // Якщо повідомлення містить слово "успішна", то виводиться з кольором первинної теми,
    // інакше — з кольором помилки
    Text(text = message, color = if (message.contains("успішна")) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error)
}
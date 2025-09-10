package ua.kpi.practical_example_11.composables

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.kpi.practical_example_11.basic.AuthApi
import ua.kpi.practical_example_11.basic.AuthViewModel
import ua.kpi.practical_example_11.basic.LoginRequest

@Composable
fun BasicApp(authViewModel: AuthViewModel = viewModel()) {
    val context = LocalContext.current
    // Retrofit для підключення до API
    val retrofit = remember {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5156/") // локальний хост для емулятора Android
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val authApi = retrofit.create(AuthApi::class.java)

    // Змінні для полів форми
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Login", style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.height(16.dp))

            // Поле логіну
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Поле пароля
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Кнопка входу
            Button(
                onClick = {
                    if (username.isBlank() || password.isBlank()) {
                        Toast.makeText(context, "Please enter username and password", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    // Корутину виконуємо на IO для мережевого запиту
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            val response = authApi.login(LoginRequest(username, password))
                            authViewModel.saveToken(response.token, response.role)

                            // Показуємо Toast на UI-потоці
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    context,
                                    "Login successful! Role: ${response.role}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } catch (e: Exception) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    context,
                                    "Login failed: ${e.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Показуємо токен, якщо він збережений
            authViewModel.token?.let {
                Text("Saved token: $it", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}


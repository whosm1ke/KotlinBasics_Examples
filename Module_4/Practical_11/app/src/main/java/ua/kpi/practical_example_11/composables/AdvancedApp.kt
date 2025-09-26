package ua.kpi.practical_example_11.composables

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.kpi.practical_example_11.advanced.AuthViewModel
import ua.kpi.practical_example_11.advanced.AuthViewModelFactory

@Composable
fun AdvancedApp() {
    // Отримуємо контекст активності для показу Toast-повідомлень
    val context = LocalContext.current
    
    // Створюємо ViewModel для авторизації з використанням фабрики
    val authViewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(context))
    
    // Створюємо змінні для зберігання введених даних
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        // Перевіряємо, чи користувач увійшов в систему
        if (authViewModel.token == null) {
            // Відображаємо заголовок для сторінки входу
            Text("Login", style = MaterialTheme.typography.titleLarge)

            // Поле вводу імені користувача
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            // Поле вводу паролю з приховуванням символів
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )

            // Кнопка входу з викликом методу login у ViewModel
            Button(
                onClick = {
                    authViewModel.login(username, password) { error ->
                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show() // Показ помилки у випадку невдачі
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text("Login")
            }
        } else {
            // Якщо користувач увійшов, відображаємо ім'я ролі
            Text("Logged in as ${authViewModel.role}", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            // Кнопка завантаження захищених даних
            Button(onClick = {
                authViewModel.loadProtectedData { error ->
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                }
            }) {
                Text("Load Protected Data")
            }

            // Відображаємо захищені дані, якщо вони доступні
            authViewModel.protectedData?.let {
                Text(it.message, modifier = Modifier.padding(top = 8.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Якщо роль користувача ADMIN, додаємо кнопку завантаження адміністративних даних
            if (authViewModel.role == "ADMIN") {
                Button(onClick = {
                    authViewModel.loadAdminData { error ->
                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                    }
                }) {
                    Text("Load Admin Data")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Відображаємо список станцій, якщо дані доступні
            authViewModel.adminData?.let { stations ->
                Text("Solar Stations", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn {
                    items(stations) { station ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Іконка для станції
                                Icon(
                                    imageVector = Icons.Default.Build,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(40.dp)
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                                Column {
                                    // Назва станції
                                    Text(station.Name, style = MaterialTheme.typography.titleMedium)
                                    // Ємність станції
                                    Text(
                                        "Capacity: ${station.CapacityMW} MW",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Кнопка виходу з системи
            Button(onClick = { authViewModel.logout() }) {
                Text("Logout")
            }
        }
    }
}
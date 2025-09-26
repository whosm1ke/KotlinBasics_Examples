package ua.kpi.practical_example_12.composables

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ua.kpi.practical_example_12.basic.RetrofitClient
import ua.kpi.practical_example_12.basic.SolarPanelForecast

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicApp() {
    // Отримуємо контекст додатку для перевірки підключення до інтернету
    val context = LocalContext.current
    
    // Стан для зберігання прогнозу сонячної електростанції
    var forecast by remember { mutableStateOf<SolarPanelForecast?>(null) }
    
    // Стан для керування відображенням Snackbar (сповіщення)
    val snackbarHostState = remember { SnackbarHostState() }

    // Scaffold — головний контейнер з верхньою панеллю та Snackbar
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(title = { Text("Сонячна електростанція") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Кнопка для отримання прогнозу
            Button(onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    // 1. Перевірка наявності підключення до Інтернету
                    if (!isInternetAvailable(context)) {
                        snackbarHostState.showSnackbar("Немає підключення до Інтернету")
                        return@launch // Якщо немає інтернету — вихід з функції
                    }

                    try {
                        // 2. Виконання запиту до API через Retrofit
                        val response = RetrofitClient.api.getTodayForecast()
                        forecast = response // Зберігаємо отриманий прогноз у стані
                    } catch (e: Exception) {
                        // Якщо сталася помилка (наприклад, сервер недоступний або некоректна відповідь)
                        snackbarHostState.showSnackbar("Помилка: ${e.message}")
                    }
                }
            }) {
                Text("Отримати прогноз на сьогодні")
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Відображення отриманого прогнозу, якщо він існує
            forecast?.let {
                Text("Дата: ${it.date}")
                Text("Потужність: ${it.powerKwh} кВт·год")
                Text("Статус: ${it.status}")
            }
        }
    }
}

// Функція перевірки наявності підключення до Інтернету
fun isInternetAvailable(context: Context): Boolean {
    // Отримуємо сервіс керування мережевими підключеннями
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    
    // Отримуємо активну мережу
    val network = connectivityManager.activeNetwork ?: return false
    
    // Отримуємо можливості активної мережі
    val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
    
    // Перевіряємо, чи має мережа підтримку доступу до Інтернету
    return activeNetwork.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}
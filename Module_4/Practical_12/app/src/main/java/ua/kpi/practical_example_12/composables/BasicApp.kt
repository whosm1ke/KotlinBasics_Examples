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
    val context = LocalContext.current
    var forecast by remember { mutableStateOf<SolarPanelForecast?>(null) } // збережений прогноз
    val snackbarHostState = remember { SnackbarHostState() }

    // Scaffold — каркас з Snackbar
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

            // Кнопка отримання прогнозу
            Button(onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    // 1. Перевірка інтернету
                    if (!isInternetAvailable(context)) {
                        snackbarHostState.showSnackbar("Немає підключення до Інтернету")
                        return@launch
                    }

                    try {
                        // 2. Виконання запиту через Retrofit
                        val response = RetrofitClient.api.getTodayForecast()
                        forecast = response
                    } catch (e: Exception) {
                        // Якщо сталася помилка (сервер недоступний, JSON некоректний і т.д.)
                        snackbarHostState.showSnackbar("Помилка: ${e.message}")
                    }
                }
            }) {
                Text("Отримати прогноз на сьогодні")
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Відображення результату
            forecast?.let {
                Text("Дата: ${it.date}")
                Text("Потужність: ${it.powerKwh} кВт·год")
                Text("Статус: ${it.status}")
            }
        }
    }
}

// Функція перевірки доступу до інтернету
fun isInternetAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
    return activeNetwork.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}


package ua.kpi.practical_example_12.medium

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ua.kpi.practical_example_12.composables.isInternetAvailable

// === ViewModel ===
class ForecastViewModel : ViewModel() {
    var forecast by mutableStateOf<SolarPanelForecast?>(null)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun fetchForecast(context: Context) {
        if (!isInternetAvailable(context)) {
            errorMessage = "Немає підключення до Інтернету"
            return
        }

        // Використовуємо CoroutineExceptionHandler для централізованої обробки
        val handler = CoroutineExceptionHandler { _, exception ->
            errorMessage = ErrorHandler.handleException(exception)
        }

        viewModelScope.launch(Dispatchers.IO + handler) {
            val response = RetrofitClient.api.getTodayForecast()
            forecast = response
            errorMessage = null // очищуємо помилку при успішному запиті
        }
    }

    // Виклик запиту, який штучно повертає помилку (для тесту)
    fun fetchError(code: Int, context: Context) {
        if (!isInternetAvailable(context)) {
            errorMessage = "Немає підключення до Інтернету"
            return
        }

        val handler = CoroutineExceptionHandler { _, exception ->
            errorMessage = ErrorHandler.handleException(exception)
        }

        viewModelScope.launch(Dispatchers.IO + handler) {
            RetrofitClient.api.getError(code)
            // Ми очікуємо, що цей виклик кине HttpException
        }
    }
}
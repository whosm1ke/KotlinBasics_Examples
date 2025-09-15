package ua.kpi.practical_example_12.advanced

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import ua.kpi.practical_example_12.composables.isInternetAvailable

// === ViewModel ===
class ForecastViewModel(private val context: Context) : ViewModel() {
    var forecast by mutableStateOf<SolarPanelForecast?>(null)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    private val maxRetries = 3
    private val retryDelay = 2000L // мс

    fun fetchForecastWithRetry() {
        if (!isInternetAvailable(context)) {
            errorMessage = "Немає підключення до Інтернету"
            return
        }

        viewModelScope.launch {
            var attempt = 0
            while (attempt < maxRetries) {
                try {
                    val response = RetrofitClient.api.getTodayForecast()
                    forecast = response
                    errorMessage = null
                    return@launch
                } catch (e: Throwable) {
                    attempt++
                    errorMessage = ErrorHandler.handleException(context, e)

                    // Retry тільки для тимчасових помилок
                    if (e is HttpException && (e.code() == 500 || e.code() == 503)) {
                        delay(retryDelay)
                        continue
                    } else if (e !is HttpException) {
                        // Retry для інших винятків (мережа)
                        delay(retryDelay)
                        continue
                    } else {
                        break
                    }
                }
            }
        }
    }

    // Тестова функція для виклику ендпоінту, який повертає помилку
    fun fetchError(code: Int) {
        if (!isInternetAvailable(context)) {
            errorMessage = "Немає підключення до Інтернету"
            return
        }

        viewModelScope.launch {
            try {
                RetrofitClient.api.getError(code)
            } catch (e: Throwable) {
                errorMessage = ErrorHandler.handleException(context, e)
            }
        }
    }
}
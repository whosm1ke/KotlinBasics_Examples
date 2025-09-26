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

// === ViewModel для отримання прогнозу сонячних панелей ===
class ForecastViewModel(private val context: Context) : ViewModel() {
    // Стate-змінна для зберігання прогнозу сонячних панелей
    var forecast by mutableStateOf<SolarPanelForecast?>(null)
        private set

    // Стate-змінна для зберігання повідомлення про помилку
    var errorMessage by mutableStateOf<String?>(null)
        private set

    // Максимальна кількість спроб повторного запиту
    private val maxRetries = 3
    // Затримка між спробами повторного запиту (в мілісекундах)
    private val retryDelay = 2000L // мс

    // Функція для отримання прогнозу з можливістю повторних спроб при помилках
    fun fetchForecastWithRetry() {
        // Перевіряємо, чи доступний інтернет
        if (!isInternetAvailable(context)) {
            errorMessage = "Немає підключення до Інтернету"
            return
        }

        // Запускаємо корутину в межах ViewModel
        viewModelScope.launch {
            var attempt = 0
            while (attempt < maxRetries) {
                try {
                    // Виконуємо запит до API для отримання прогнозу
                    val response = RetrofitClient.api.getTodayForecast()
                    forecast = response // Зберігаємо отриманий прогноз
                    errorMessage = null // Очищуємо помилку, якщо запит успішний
                    return@launch // Виходимо з корутини
                } catch (e: Throwable) {
                    attempt++ // Збільшуємо лічильник спроб
                    errorMessage = ErrorHandler.handleException(context, e) // Обробляємо помилку

                    // Якщо це тимчасова помилка сервера (500 або 503), робимо повторний запит
                    if (e is HttpException && (e.code() == 500 || e.code() == 503)) {
                        delay(retryDelay) // Затримка перед повторною спробою
                        continue // Продовжуємо цикл
                    } else if (e !is HttpException) {
                        // Для інших винятків (наприклад, мережеві помилки) також робимо повторний запит
                        delay(retryDelay)
                        continue
                    } else {
                        // Якщо це критична помилка (не тимчасова), припиняємо спроби
                        break
                    }
                }
            }
        }
    }

    // Тестова функція для виклику ендпоінту, який повертає помилку (для тестування)
    fun fetchError(code: Int) {
        // Перевіряємо, чи доступний інтернет
        if (!isInternetAvailable(context)) {
            errorMessage = "Немає підключення до Інтернету"
            return
        }

        // Запускаємо корутину в межах ViewModel
        viewModelScope.launch {
            try {
                // Викликаємо метод API, що повертає помилку з вказаним кодом
                RetrofitClient.api.getError(code)
            } catch (e: Throwable) {
                // Обробляємо помилку та встановлюємо повідомлення
                errorMessage = ErrorHandler.handleException(context, e)
            }
        }
    }
}
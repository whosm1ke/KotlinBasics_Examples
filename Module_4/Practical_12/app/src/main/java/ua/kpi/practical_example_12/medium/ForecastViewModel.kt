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

// === ViewModel для отримання прогнозу сонячної панелі ===
class ForecastViewModel : ViewModel() {
    // Стейт для зберігання прогнозу сонячної панелі, ініціалізується як null
    var forecast by mutableStateOf<SolarPanelForecast?>(null)
        private set

    // Стейт для зберігання повідомлення про помилку, ініціалізується як null
    var errorMessage by mutableStateOf<String?>(null)
        private set

    /**
     * Метод для отримання прогнозу сонячної панелі з API
     * @param context контекст додатку для перевірки підключення до Інтернету
     */
    fun fetchForecast(context: Context) {
        // Перевіряємо, чи доступний інтернет
        if (!isInternetAvailable(context)) {
            errorMessage = "Немає підключення до Інтернету"
            return
        }

        // Створюємо обробник помилок для корутин
        val handler = CoroutineExceptionHandler { _, exception ->
            errorMessage = ErrorHandler.handleException(exception)
        }

        // Запускаємо корутину в фоновому потоці (IO)
        viewModelScope.launch(Dispatchers.IO + handler) {
            // Виконуємо запит до API для отримання прогнозу на сьогодні
            val response = RetrofitClient.api.getTodayForecast()
            forecast = response // Оновлюємо стейт прогнозу
            errorMessage = null // Очищуємо помилку при успішному запиті
        }
    }

    /**
     * Метод для тестування обробки помилок з API
     * @param code код помилки, який має бути повернутий сервером
     * @param context контекст додатку для перевірки підключення до Інтернету
     */
    fun fetchError(code: Int, context: Context) {
        // Перевіряємо, чи доступний інтернет
        if (!isInternetAvailable(context)) {
            errorMessage = "Немає підключення до Інтернету"
            return
        }

        // Створюємо обробник помилок для корутин
        val handler = CoroutineExceptionHandler { _, exception ->
            errorMessage = ErrorHandler.handleException(exception)
        }

        // Запускаємо корутину в фоновому потоці (IO) з обробником помилок
        viewModelScope.launch(Dispatchers.IO + handler) {
            // Викликаємо метод API, який штучно повертає помилку за заданим кодом
            RetrofitClient.api.getError(code)
            // Очікуємо, що цей виклик кине HttpException, яка буде оброблена через CoroutineExceptionHandler
        }
    }
}
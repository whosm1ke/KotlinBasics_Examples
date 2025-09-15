package ua.kpi.practical_example_12.medium

import android.util.Log
import retrofit2.HttpException

// === Централізований обробник помилок ===
object ErrorHandler {
    // Обробка винятків та логування
    fun handleException(e: Throwable): String {
        return when (e) {
            is HttpException -> {
                val code = e.code()
                Log.e("ErrorHandler", "HTTP error $code: ${e.message()}")
                "Помилка сервера: $code"
            }
            else -> {
                Log.e("ErrorHandler", "Unknown error: ${e.localizedMessage}")
                "Помилка: ${e.localizedMessage}"
            }
        }
    }
}
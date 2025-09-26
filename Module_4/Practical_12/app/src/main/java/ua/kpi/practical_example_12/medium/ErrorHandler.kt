package ua.kpi.practical_example_12.medium

import android.util.Log
import retrofit2.HttpException

// === Централізований обробник помилок ===
object ErrorHandler {
    // Метод для обробки винятків та логування повідомлень про помилки
    // Приймає будь-який тип Throwable (виняток) і повертає текстове повідомлення про помилку
    fun handleException(e: Throwable): String {
        return when (e) {
            // Якщо виняток є HttpException (помилка HTTP запиту)
            is HttpException -> {
                // Отримуємо код помилки з HTTP відповіді
                val code = e.code()
                // Логуємо код та повідомлення про помилку у Android Logcat
                Log.e("ErrorHandler", "HTTP error $code: ${e.message()}")
                // Повертаємо зрозуміле повідомлення для користувача з кодом помилки
                "Помилка сервера: $code"
            }
            else -> {
                // Для інших типів винятків (наприклад, мережеві або системні помилки)
                // Логуємо повідомлення про невідому помилку
                Log.e("ErrorHandler", "Unknown error: ${e.localizedMessage}")
                // Повертаємо загальне повідомлення про помилку з деталями
                "Помилка: ${e.localizedMessage}"
            }
        }
    }
}
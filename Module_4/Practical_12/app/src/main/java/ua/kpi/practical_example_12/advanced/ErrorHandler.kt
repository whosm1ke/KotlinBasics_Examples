package ua.kpi.practical_example_12.advanced

import android.content.Context
import android.util.Log
import retrofit2.HttpException
import java.io.File
import java.io.FileOutputStream

// === Просунутий обробник помилок ===
object ErrorHandler {

    // Метод для логування помилок у файл
    // Приймає контекст додатку та повідомлення про помилку
    // Записує час та повідомлення у файл error_log.txt у директорії додатку
    fun logError(context: Context, message: String) {
        try {
            // Створюємо файл у внутрішній директорії додатку
            val file = File(context.filesDir, "error_log.txt")
            // Відкриваємо потік запису у файл у режимі додавання (true)
            FileOutputStream(file, true).bufferedWriter().use { writer ->
                // Записуємо поточний час та повідомлення про помилку
                writer.write("${System.currentTimeMillis()}: $message\n")
            }
        } catch (e: Exception) {
            // У разі помилки логування, виводимо помилку в Logcat
            Log.e("AdvancedErrorHandler", "Cannot log error: ${e.localizedMessage}")
        }
    }

    // Основний метод обробки винятків
    // Приймає контекст додатку та виняток
    // Повертає зрозуміле повідомлення про помилку для відображення користувачу
    fun handleException(context: Context, e: Throwable): String {
        // Визначаємо тип помилки та формуємо відповідне повідомлення
        val message = when (e) {
            is HttpException -> {
                // Обробка HTTP-помилок з різними кодами статусу
                when (e.code()) {
                    400 -> "Некоректний запит" // Помилка клієнта
                    404 -> "Прогноз не знайдено" // Ресурс не знайдено
                    500 -> "Внутрішня помилка сервера, спробуйте пізніше" // Помилка сервера
                    else -> "Помилка сервера: ${e.code()}" // Інша HTTP-помилка
                }
            }
            else -> "Помилка: ${e.localizedMessage}" // Загальна обробка інших винятків
        }

        // Логуємо помилку в Logcat для налагодження
        Log.e("AdvancedErrorHandler", message)
        // Логуємо помилку у файл
        logError(context, message)
        // Повертаємо зрозуміле повідомлення про помилку
        return message
    }
}
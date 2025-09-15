package ua.kpi.practical_example_12.advanced

import android.content.Context
import android.util.Log
import retrofit2.HttpException
import java.io.File
import java.io.FileOutputStream

// === Просунутий ErrorHandler ===
object ErrorHandler {

    // Логування в файл
    fun logError(context: Context, message: String) {
        try {
            val file = File(context.filesDir, "error_log.txt")
            FileOutputStream(file, true).bufferedWriter().use { writer ->
                writer.write("${System.currentTimeMillis()}: $message\n")
            }
        } catch (e: Exception) {
            Log.e("AdvancedErrorHandler", "Cannot log error: ${e.localizedMessage}")
        }
    }

    fun handleException(context: Context, e: Throwable): String {
        val message = when (e) {
            is HttpException -> {
                when (e.code()) {
                    400 -> "Некоректний запит"
                    404 -> "Прогноз не знайдено"
                    500 -> "Внутрішня помилка сервера, спробуйте пізніше"
                    else -> "Помилка сервера: ${e.code()}"
                }
            }
            else -> "Помилка: ${e.localizedMessage}"
        }

        Log.e("AdvancedErrorHandler", message)
        logError(context, message)
        return message
    }
}


package ua.kpi.practical_example_18.composables

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat

class NotificationActionReceiver : BroadcastReceiver() {
    // Метод, який викликається при отриманні сповіщення через Broadcast
    override fun onReceive(context: Context, intent: Intent) {
        // Отримуємо ідентифікатор сповіщення з наміру (Intent)
        val id = intent.getIntExtra("notification_id", -1)
        // Створюємо менеджер для керування сповіщеннями
        val manager = NotificationManagerCompat.from(context)

        // Перевіряємо дію, яку виконав користувач через сповіщення
        when (intent.action) {
            // Якщо дія - підтвердження події
            "CONFIRM" -> {
                // Відображаємо сповіщення про підтвердження
                Toast.makeText(context, "Подія підтверджена", Toast.LENGTH_SHORT).show()
                // Якщо ідентифікатор сповіщення дійсний, відміняємо його
                if (id != -1) manager.cancel(id)
            }
            // Якщо дія - відкладення події
            "SNOOZE" -> {
                // Відображаємо сповіщення про відкладення
                Toast.makeText(context, "Подія відкладена", Toast.LENGTH_SHORT).show()
                // Якщо ідентифікатор сповіщення дійсний, відміняємо його
                if (id != -1) manager.cancel(id)
            }
        }
    }
}
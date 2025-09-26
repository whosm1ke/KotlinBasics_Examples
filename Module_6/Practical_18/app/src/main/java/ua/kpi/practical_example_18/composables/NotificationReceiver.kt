package ua.kpi.practical_example_18.composables

import android.Manifest
import android.R
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import ua.kpi.practical_example_18.DisplayFor
import kotlin.random.Random

class NotificationReceiver(
) : BroadcastReceiver() {
    // Метод, який викликається при отриманні broadcast-повідомлення
    override fun onReceive(context: Context, intent: Intent) {
        // Отримуємо заголовок сповіщення з інтенту, якщо він не заданий — використовуємо значення за замовчуванням
        val title = intent.getStringExtra("title") ?: "Сонячна станція"
        // Отримуємо текст сповіщення з інтенту, якщо він не заданий — використовуємо значення за замовчуванням
        val message = intent.getStringExtra("message") ?: "Нове сповіщення"
        // Отримуємо ідентифікатор каналу сповіщень з інтенту, якщо він не заданий — використовуємо значення за замовчуванням
        val channel = intent.getStringExtra("channel") ?: DisplayFor.MIDDLE_LEVEL.toString()

        // Створюємо будівельник сповіщення з вказаним каналом
        val builder = NotificationCompat.Builder(context, channel)
            .setSmallIcon(R.drawable.ic_dialog_info) // Встановлюємо іконку сповіщення
            .setContentTitle(title) // Встановлюємо заголовок сповіщення
            .setContentText(message) // Встановлюємо текст сповіщення
            .setPriority(NotificationCompat.PRIORITY_HIGH) // Встановлюємо пріоритет сповіщення (високий)

        // Перевіряємо, чи надано дозвіл на відображення сповіщень
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Якщо дозвіл надано — отримуємо менеджер сповіщень і відправляємо сповіщення
            with(NotificationManagerCompat.from(context)) {
                notify(Random.nextInt(), builder.build()) // Відправляємо сповіщення з випадковим ID
            }
        }
    }
}
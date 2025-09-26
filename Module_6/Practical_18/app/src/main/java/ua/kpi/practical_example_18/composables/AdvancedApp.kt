package ua.kpi.practical_example_18.composables

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import ua.kpi.practical_example_18.DisplayFor

@Composable
fun AdvancedApp() {
    // Отримуємо контекст активності з Compose-середовища
    val context: Context = LocalContext.current
    Column {
        // Відображаємо заголовок для просунутого рівня
        Text("Просунутий рівень: інтерактивні сповіщення")
        Spacer(modifier = Modifier.height(8.dp))

        // Кнопка для надсилання інтерактивного сповіщення
        Button(onClick = { sendInteractiveNotification(context) }) {
            Text("Надіслати інтерактивне сповіщення")
        }
    }
}

fun sendInteractiveNotification(context: Context) {
    // Унікальний ідентифікатор для сповіщення
    val notificationId = 2001

    // Створюємо Intent для дії "Підтвердити"
    val confirmIntent = Intent(context, NotificationActionReceiver::class.java).apply {
        action = "CONFIRM"  // Дія, яку виконає отримувач
        putExtra("notification_id", notificationId)  // Передаємо ID сповіщення
    }
    // Створюємо PendingIntent для дії "Підтвердити"
    val confirmPending = PendingIntent.getBroadcast(
        context, notificationId + 1, confirmIntent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    // Створюємо Intent для дії "Відкласти"
    val snoozeIntent = Intent(context, NotificationActionReceiver::class.java).apply {
        action = "SNOOZE"  // Дія, яку виконає отримувач
        putExtra("notification_id", notificationId)  // Передаємо ID сповіщення
    }
    // Створюємо PendingIntent для дії "Відкласти"
    val snoozePending = PendingIntent.getBroadcast(
        context, notificationId + 2, snoozeIntent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    // Створюємо конструктор сповіщення з використанням NotificationCompat
    val builder = NotificationCompat.Builder(context, DisplayFor.ADVANCED_LEVEL.toString())
        .setSmallIcon(android.R.drawable.ic_dialog_info)  // Встановлюємо іконку сповіщення
        .setContentTitle("Сонячна станція")  // Заголовок сповіщення
        .setContentText("Прогноз потужності оновлено")  // Текст сповіщення
        .setPriority(NotificationCompat.PRIORITY_HIGH)  // Встановлюємо пріоритет
        .addAction(android.R.drawable.ic_input_add, "Підтвердити", confirmPending)  // Додаємо дію "Підтвердити"
        .addAction(android.R.drawable.ic_delete, "Відкласти", snoozePending)  // Додаємо дію "Відкласти"

    // Перевіряємо, чи надано дозвіл на надсилання сповіщень
    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        // Надсилаємо сповіщення через NotificationManagerCompat
        NotificationManagerCompat.from(context).notify(notificationId, builder.build())
    }
}
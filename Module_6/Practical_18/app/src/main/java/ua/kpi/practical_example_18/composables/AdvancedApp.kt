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
    val context: Context = LocalContext.current
    Column {
        Text("Просунутий рівень: інтерактивні сповіщення")
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { sendInteractiveNotification(context) }) {
            Text("Надіслати інтерактивне сповіщення")
        }
    }
}

fun sendInteractiveNotification(context: Context) {
    val notificationId = 2001

    // Confirm
    val confirmIntent = Intent(context, NotificationActionReceiver::class.java).apply {
        action = "CONFIRM"
        putExtra("notification_id", notificationId)
    }
    val confirmPending = PendingIntent.getBroadcast(
        context, notificationId + 1, confirmIntent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    // Snooze
    val snoozeIntent = Intent(context, NotificationActionReceiver::class.java).apply {
        action = "SNOOZE"
        putExtra("notification_id", notificationId)
    }
    val snoozePending = PendingIntent.getBroadcast(
        context, notificationId + 2, snoozeIntent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    // Build notification
    val builder = NotificationCompat.Builder(context, DisplayFor.ADVANCED_LEVEL.toString())
        .setSmallIcon(android.R.drawable.ic_dialog_info)
        .setContentTitle("Сонячна станція")
        .setContentText("Прогноз потужності оновлено")
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .addAction(android.R.drawable.ic_input_add, "Підтвердити", confirmPending)
        .addAction(android.R.drawable.ic_delete, "Відкласти", snoozePending)

    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        NotificationManagerCompat.from(context).notify(notificationId, builder.build())
    }
}

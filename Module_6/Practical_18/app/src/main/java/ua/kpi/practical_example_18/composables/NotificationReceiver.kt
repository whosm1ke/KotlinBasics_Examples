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
    override fun onReceive(context: Context, intent: Intent) {
        val title = intent.getStringExtra("title") ?: "Сонячна станція"
        val message = intent.getStringExtra("message") ?: "Нове сповіщення"
        val channel = intent.getStringExtra("channel") ?: DisplayFor.MIDDLE_LEVEL.toString()

        val builder = NotificationCompat.Builder(context, channel)
            .setSmallIcon(R.drawable.ic_dialog_info)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH) // середній рівень завжди високий

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            with(NotificationManagerCompat.from(context)) {
                notify(Random.nextInt(), builder.build())
            }
        }
    }
}


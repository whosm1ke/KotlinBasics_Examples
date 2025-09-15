package ua.kpi.practical_example_18.composables

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat

class NotificationActionReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val id = intent.getIntExtra("notification_id", -1)
        val manager = NotificationManagerCompat.from(context)

        when (intent.action) {
            "CONFIRM" -> {
                Toast.makeText(context, "Подія підтверджена", Toast.LENGTH_SHORT).show()
                if (id != -1) manager.cancel(id)
            }
            "SNOOZE" -> {
                Toast.makeText(context, "Подія відкладена", Toast.LENGTH_SHORT).show()
                if (id != -1) manager.cancel(id)
            }
        }
    }
}
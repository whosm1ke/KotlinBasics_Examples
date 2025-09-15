package ua.kpi.practical_example_18.composables

import android.Manifest
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.Toast
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
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import ua.kpi.practical_example_18.DisplayFor
import kotlin.jvm.java
import ua.kpi.practical_example_18.R
import java.util.concurrent.TimeUnit
import kotlin.random.Random

@Composable
fun MediumApp() {
    val context = LocalContext.current

    Column {
        Text("Середній рівень: планування та категоризація сповіщень")
        Spacer(modifier = Modifier.height(8.dp))

        // Кнопка для категорії "Температура"
        Button(onClick = {
            scheduleNotification(
                context,
                category = "Температура",
                message = "Температура сонячної панелі: 75°C",
                delaySeconds = 5
            )
        }) {
            Text("Нагадування про температуру")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Кнопка для категорії "Дія"
        Button(onClick = {
            scheduleNotification(
                context,
                category = "Дія",
                message = "Перевірте інвертор",
                delaySeconds = 10
            )
        }) {
            Text("Нагадування про дію")
        }
    }
}

// ------------------ Планування сповіщення ------------------
fun scheduleNotification(context: Context, category: String, message: String, delaySeconds: Int) {
    val workRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
        .setInitialDelay(delaySeconds.toLong(), TimeUnit.SECONDS)
        .setInputData(
            workDataOf(
                "title" to "Сонячна станція - $category",
                "message" to message,
                "channel" to when (category) {
                    "Температура" -> "temp_channel"
                    "Дія" -> "action_channel"
                    else -> DisplayFor.MIDDLE_LEVEL.toString()
                }
            )
        )
        .build()

    WorkManager.getInstance(context).enqueue(workRequest)

    Toast.makeText(
        context,
        "Сповіщення «$category» заплановано на $delaySeconds секунд",
        Toast.LENGTH_SHORT
    ).show()
}

// ------------------ Worker для сповіщень ------------------
class NotificationWorker(appContext: Context, params: WorkerParameters) :
    Worker(appContext, params) {

    override fun doWork(): Result {
        val title = inputData.getString("title") ?: "Сонячна станція"
        val message = inputData.getString("message") ?: "Нове сповіщення"
        val channel = inputData.getString("channel") ?: DisplayFor.MIDDLE_LEVEL.toString()

        val builder = NotificationCompat.Builder(applicationContext, channel)
            .setSmallIcon(R.drawable.outline_adjust_24)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)


        if (ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            with(NotificationManagerCompat.from(applicationContext)) {
                notify(Random.nextInt(), builder.build())
            }
        }

        return Result.success()
    }
}
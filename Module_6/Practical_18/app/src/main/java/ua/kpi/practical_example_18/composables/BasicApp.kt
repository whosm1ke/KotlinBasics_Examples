package ua.kpi.practical_example_18.composables

import android.Manifest
import android.content.Context
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
import kotlin.random.Random


@Composable
fun BasicApp() {
    val context: Context = LocalContext.current
    Column {
        Text("Базовий рівень: просте локальне сповіщення")
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { sendBasicNotification(context) }) {
            Text("Надіслати сповіщення про сонячну станцію")
        }
    }
}

private fun sendBasicNotification(context: Context) {
    val builder = NotificationCompat.Builder(context, DisplayFor.BASIC_LEVEL.toString())
        .setSmallIcon(android.R.drawable.ic_dialog_info)
        .setContentTitle("Сонячна станція")
        .setContentText("Прогноз потужності оновлено")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)


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
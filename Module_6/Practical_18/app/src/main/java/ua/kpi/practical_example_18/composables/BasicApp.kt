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
    // Отримуємо контекст активності з локального контекста Compose
    val context: Context = LocalContext.current
    Column {
        // Відображаємо текст заголовка для базового рівня сповіщень
        Text("Базовий рівень: просте локальне сповіщення")
        Spacer(modifier = Modifier.height(8.dp)) // Додаємо простір між елементами

        // Кнопка для відправки сповіщення
        Button(onClick = { sendBasicNotification(context) }) {
            Text("Надіслати сповіщення про сонячну станцію")
        }
    }
}

private fun sendBasicNotification(context: Context) {
    // Створюємо будівельник сповіщень з використанням NotificationCompat
    val builder = NotificationCompat.Builder(context, DisplayFor.BASIC_LEVEL.toString())
        .setSmallIcon(android.R.drawable.ic_dialog_info) // Встановлюємо маленьку іконку для сповіщення
        .setContentTitle("Сонячна станція") // Встановлюємо заголовок сповіщення
        .setContentText("Прогноз потужності оновлено") // Встановлюємо текст сповіщення
        .setPriority(NotificationCompat.PRIORITY_DEFAULT) // Встановлюємо пріоритет сповіщення (за замовчуванням)


    // Перевіряємо, чи має додаток дозвіл на відправку сповіщень
    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        // Якщо дозвіл є, використовуємо NotificationManagerCompat для відправки сповіщення
        with(NotificationManagerCompat.from(context)) {
            notify(Random.nextInt(), builder.build()) // Відправляємо сповіщення з випадковим ID
        }
    }
}
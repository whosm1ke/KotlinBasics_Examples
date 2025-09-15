package ua.kpi.practical_example_18

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import ua.kpi.practical_example_18.composables.AdvancedApp
import ua.kpi.practical_example_18.composables.BasicApp
import ua.kpi.practical_example_18.composables.DisplayModeSelector
import ua.kpi.practical_example_18.composables.MediumApp
import ua.kpi.practical_example_18.ui.theme.Practical_Example_18Theme

class MainActivity : ComponentActivity() {

    private val NOTIFICATION_ID = 101
    private val PERMISSION_REQUEST_CODE = 1001 // Код для запиту дозволу

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // --- Перевірка дозволу на сповіщення для Android 13+ ---
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), PERMISSION_REQUEST_CODE)
            }
        }

        ensureNotificationPermission()

        // Створення каналів для сповіщень
        createNotificationChannels()

        setContent {
            Practical_Example_18Theme {
                var displayFor by remember { mutableStateOf(DisplayFor.BASIC_LEVEL) }
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column {
                        // Перемикач між рівнями складності
                        DisplayModeSelector(
                            selected = displayFor,
                            onSelectedChange = { displayFor = it }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Відображення відповідного рівня
                        when (displayFor) {
                            DisplayFor.BASIC_LEVEL -> BasicApp()
                            DisplayFor.MIDDLE_LEVEL -> MediumApp()
                            DisplayFor.ADVANCED_LEVEL -> AdvancedApp()
                        }
                    }
                }
            }
        }
    }

    // Обробка відповіді користувача на запит дозволу
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                Toast.makeText(this, "Дозвіл на сповіщення надано", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Дозвіл на сповіщення відхилено", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Створюємо канали повідомлень для Android 8+
    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val basicChannel = NotificationChannel(
                DisplayFor.BASIC_LEVEL.toString(),
                "Базові сповіщення",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply { description = "Сповіщення про базові події" }

            val middleChannel = NotificationChannel(
                DisplayFor.MIDDLE_LEVEL.toString(),
                "Середні сповіщення",
                NotificationManager.IMPORTANCE_HIGH
            ).apply { description = "Сповіщення з категоріями та пріоритетами" }

            val advancedChannel = NotificationChannel(
                DisplayFor.ADVANCED_LEVEL.toString(),
                "Просунуті сповіщення",
                NotificationManager.IMPORTANCE_HIGH
            ).apply { description = "Інтерактивні сповіщення з діями" }

            val tempChannel = NotificationChannel(
                "temp_channel",
                "Сповіщення температури",
                NotificationManager.IMPORTANCE_HIGH
            ).apply { description = "Повідомлення про температуру панелі" }

            val actionChannel = NotificationChannel(
                "action_channel",
                "Сповіщення дій",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply { description = "Нагадування про перевірку інвертора" }
            val manager = getSystemService(NotificationManager::class.java)

            manager.createNotificationChannel(tempChannel)
            manager.createNotificationChannel(actionChannel)

            manager.createNotificationChannel(basicChannel)
            manager.createNotificationChannel(middleChannel)
            manager.createNotificationChannel(advancedChannel)
        }
    }

    private fun ensureNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    1001
                )
            }
        }
    }
}


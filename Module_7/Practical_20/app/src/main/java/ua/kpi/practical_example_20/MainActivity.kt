package ua.kpi.practical_example_20

import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_20.composables.AdvancedApp
import ua.kpi.practical_example_20.composables.BasicApp
import ua.kpi.practical_example_20.composables.DisplayModeSelector
import ua.kpi.practical_example_20.composables.MediumApp
import ua.kpi.practical_example_20.ui.theme.Practical_Example_20Theme

class MainActivity : ComponentActivity() {
    // Оголошення менеджера сенсорів та змінних для акселерометра та гіроскопа
    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private var gyroscope: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Ініціалізація менеджера сенсорів та отримання стандартних сенсорів
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        // Фіксація орієнтації екрана у портретному режимі
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setContent {
            // Створення стану для вибору рівня складності додатку
            var displayFor by remember { mutableStateOf(DisplayFor.BASIC_LEVEL) }

            Surface(modifier = Modifier.fillMaxSize()) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    // Відображення перемикача рівня складності
                    DisplayModeSelector(
                        selected = displayFor,
                        onSelectedChange = { displayFor = it }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Відображення відповідного UI залежно від обраного рівня складності
                    when (displayFor) {
                        DisplayFor.BASIC_LEVEL -> BasicApp(sensorManager, accelerometer)
                        DisplayFor.MIDDLE_LEVEL -> MediumApp(sensorManager, accelerometer)
                        DisplayFor.ADVANCED_LEVEL -> AdvancedApp(sensorManager, accelerometer, gyroscope)
                    }
                }
            }
        }
    }
}
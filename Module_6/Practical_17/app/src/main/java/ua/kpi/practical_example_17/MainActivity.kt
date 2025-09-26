package ua.kpi.practical_example_17

import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_17.composables.AdvancedApp
import ua.kpi.practical_example_17.composables.BasicApp
import ua.kpi.practical_example_17.composables.DisplayModeSelector
import ua.kpi.practical_example_17.composables.MediumApp
import ua.kpi.practical_example_17.ui.theme.Practical_Example_17Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Отримуємо менеджер сенсорів системи
        val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        // Отримуємо стандартний акселерометр
        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        // Отримуємо сенсор освітлення
        val lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        
        // Встановлюємо вміст активності за допомогою Compose
        setContent {
            Practical_Example_17Theme {
                // Створюємо стан для вибору рівня складності
                var displayFor by remember { mutableStateOf(DisplayFor.BASIC_LEVEL) }
                
                // Встановлюємо поверхню з максимальним розміром
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column {
                        // Відображаємо перемикач між рівнями складності
                        DisplayModeSelector(
                            selected = displayFor,
                            onSelectedChange = { displayFor = it }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Відображаємо відповідний компонент залежно від обраного рівня
                        when (displayFor) {
                            DisplayFor.BASIC_LEVEL -> BasicApp(sensorManager, accelerometer)
                            DisplayFor.MIDDLE_LEVEL -> MediumApp(sensorManager, accelerometer, lightSensor)
                            DisplayFor.ADVANCED_LEVEL -> AdvancedApp(sensorManager, listOf(accelerometer, lightSensor))
                        }
                    }
                }
            }
        }
    }
}
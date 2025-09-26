package ua.kpi.practical_example_1

import android.os.Bundle
import android.util.DisplayMetrics
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
import ua.kpi.practical_example_1.composables.DisplayModeSelector
import ua.kpi.practical_example_1.composables.SolarForecastAppLevel1
import ua.kpi.practical_example_1.composables.SolarForecastAppLevel2
import ua.kpi.practical_example_1.composables.SolarForecastAppLevel3
import ua.kpi.practical_example_1.ui.theme.Practical_Example_1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Вимкнено для прикладу, можна увімкнути для підтримки edge-to-edge режиму
//        enableEdgeToEdge()
        setContent {
            Practical_Example_1Theme {
                // Створюємо стан для вибору режиму відображення
                var displayFor by remember { mutableStateOf(DisplayFor.BASIC_LEVEL) }

                Surface(modifier = Modifier.fillMaxSize()) {
                    Column {
                        // Відображаємо перемикач режимів
                        DisplayModeSelector(
                            selected = displayFor,
                            onSelectedChange = { displayFor = it }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Відображаємо відповідний компонент залежно від обраного режиму
                        when (displayFor) {
                            DisplayFor.BASIC_LEVEL -> SolarForecastAppLevel1()
                            DisplayFor.MIDDLE_LEVEL -> SolarForecastAppLevel2()
                            DisplayFor.ADVANCED_LEVEL -> SolarForecastAppLevel3()
                        }
                    }
                }
            }
        }
    }
}
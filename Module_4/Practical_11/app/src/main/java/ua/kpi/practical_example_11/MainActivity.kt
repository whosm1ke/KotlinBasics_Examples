package ua.kpi.practical_example_11

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import ua.kpi.practical_example_11.composables.AdvancedApp
import ua.kpi.practical_example_11.composables.BasicApp
import ua.kpi.practical_example_11.composables.DisplayModeSelector
import ua.kpi.practical_example_11.composables.MediumApp
import ua.kpi.practical_example_11.ui.theme.Practical_Example_11Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Встановлюємо вміст активності з використанням Compose
        setContent {
            Practical_Example_11Theme {
                // Створюємо стан для вибору режиму відображення
                var displayFor by remember { mutableStateOf(DisplayFor.BASIC_LEVEL) }

                Surface(modifier = Modifier.fillMaxSize()) {
                    Column {
                        // Відображаємо перемикач режимів
                        DisplayModeSelector(
                            selected = displayFor,
                            onSelectedChange = { displayFor = it } // Оновлюємо стан при зміні вибору
                        )

                        Spacer(modifier = Modifier.height(16.dp)) // Простір між компонентами

                        // Відображаємо відповідний екран залежно від обраного режиму
                        when (displayFor) {
                            DisplayFor.BASIC_LEVEL -> BasicApp() // Відображення для базового рівня
                            DisplayFor.MIDDLE_LEVEL -> MediumApp() // Відображення для середнього рівня
                            DisplayFor.ADVANCED_LEVEL -> AdvancedApp() // Відображення для просунутого рівня
                        }
                    }
                }
            }
        }
    }
}
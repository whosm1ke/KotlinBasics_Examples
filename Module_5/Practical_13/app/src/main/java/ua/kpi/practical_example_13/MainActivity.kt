package ua.kpi.practical_example_13

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
import ua.kpi.practical_example_13.composables.AdvancedApp
import ua.kpi.practical_example_13.composables.BasicApp
import ua.kpi.practical_example_13.composables.DisplayModeSelector
import ua.kpi.practical_example_13.composables.MediumApp
import ua.kpi.practical_example_13.ui.theme.Practical_Example_13Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Встановлюємо вміст активності за допомогою Compose
        setContent {
            Practical_Example_13Theme {
                // Створюємо стан для вибору режиму відображення з використанням remember
                var displayFor by remember { mutableStateOf(DisplayFor.BASIC_LEVEL) }

                Surface(modifier = Modifier.fillMaxSize()) {
                    Column {
                        // Відображаємо перемикач для вибору рівня складності
                        DisplayModeSelector(
                            selected = displayFor,
                            onSelectedChange = { displayFor = it }
                        )

                        // Додаємо простір між елементами
                        Spacer(modifier = Modifier.height(16.dp))

                        // Відображаємо відповідний компонент залежно від обраного рівня
                        when (displayFor) {
                            DisplayFor.BASIC_LEVEL -> BasicApp() // Відображення базового рівня
                            DisplayFor.MIDDLE_LEVEL -> MediumApp() // Відображення середнього рівня
                            DisplayFor.ADVANCED_LEVEL -> AdvancedApp() // Відображення просунутого рівня
                        }
                    }
                }
            }
        }
    }
}
package ua.kpi.practical_example_16

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
import ua.kpi.practical_example_16.composables.AdvancedApp
import ua.kpi.practical_example_16.composables.BasicApp
import ua.kpi.practical_example_16.composables.DisplayModeSelector
import ua.kpi.practical_example_16.composables.MediumApp
import ua.kpi.practical_example_16.ui.theme.Practical_Example_16Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Встановлює вміст активності за допомогою Compose
        setContent {
            Practical_Example_16Theme {
                // Створюємо стан для вибору рівня складності, ініціалізуємо його значенням BASIC_LEVEL
                var displayFor by remember { mutableStateOf(DisplayFor.BASIC_LEVEL) }

                Surface(modifier = Modifier.fillMaxSize()) {
                    Column {
                        // Відображаємо компонент для вибору рівня складності
                        DisplayModeSelector(
                            selected = displayFor,
                            onSelectedChange = { displayFor = it } // Оновлюємо стан при зміні вибору
                        )

                        Spacer(modifier = Modifier.height(16.dp)) // Додаємо простір між елементами

                        // Відображаємо відповідний компонент в залежності від обраного рівня
                        when (displayFor) {
                            DisplayFor.BASIC_LEVEL -> BasicApp()         // Якщо базовий рівень — показуємо BasicApp
                            DisplayFor.MIDDLE_LEVEL -> MediumApp()      // Якщо середній рівень — показуємо MediumApp
                            DisplayFor.ADVANCED_LEVEL -> AdvancedApp()   // Якщо просунутий рівень — показуємо AdvancedApp
                        }
                    }
                }
            }
        }
    }
}
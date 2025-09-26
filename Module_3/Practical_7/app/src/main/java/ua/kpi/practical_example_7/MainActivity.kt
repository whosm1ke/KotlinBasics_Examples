package ua.kpi.practical_example_7

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
import ua.kpi.practical_example_7.composables.AdvancedApp
import ua.kpi.practical_example_7.composables.DisplayModeSelector
import ua.kpi.practical_example_7.composables.BasicApp
import ua.kpi.practical_example_7.composables.MediumApp
import ua.kpi.practical_example_7.ui.theme.Practical_Example_7Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Вмикаємо edge-to-edge режим для Activity
        enableEdgeToEdge()
        setContent {
            Practical_Example_7Theme {
                // Створюємо змінну для відстеження поточного режиму відображення
                var displayFor by remember { mutableStateOf(DisplayFor.BASIC_LEVEL) }
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column {
                        // Відображаємо перемикач для вибору рівня складності
                        DisplayModeSelector(
                            selected = displayFor,
                            onSelectedChange = { displayFor = it } // Оновлюємо значення при зміні вибору
                        )

                        Spacer(modifier = Modifier.height(4.dp)) // Додаємо простір між елементами

                        // Відображаємо відповідний компонент залежно від обраного рівня
                        when (displayFor) {
                            DisplayFor.BASIC_LEVEL -> BasicApp() // Якщо базовий рівень — показуємо BasicApp
                            DisplayFor.MIDDLE_LEVEL -> MediumApp() // Якщо середній рівень — показуємо MediumApp
                            DisplayFor.ADVANCED_LEVEL -> AdvancedApp() // Якщо просунутий рівень — показуємо AdvancedApp
                        }
                    }
                }
            }
        }
    }
}
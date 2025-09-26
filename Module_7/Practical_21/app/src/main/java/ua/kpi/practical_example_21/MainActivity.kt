package ua.kpi.practical_example_21

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_21.composables.AdvancedApp
import ua.kpi.practical_example_21.composables.BasicApp
import ua.kpi.practical_example_21.composables.DisplayModeSelector
import ua.kpi.practical_example_21.composables.MediumApp
import ua.kpi.practical_example_21.ui.theme.Practical_Example_21Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Вмикає підтримку edge-to-edge (відображення вмісту за межами статусного рядка та навігаційної панелі)
        enableEdgeToEdge()
        setContent {
            Practical_Example_21Theme {
                // Створюємо змінну для відстеження поточного рівня складності
                var displayFor by remember { mutableStateOf(DisplayFor.BASIC_LEVEL) }

                Surface(modifier = Modifier.fillMaxSize()) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        // Відображаємо перемикач для вибору рівня складності
                        DisplayModeSelector(
                            selected = displayFor,
                            onSelectedChange = { displayFor = it }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Відображаємо відповідний компонент залежно від обраного рівня складності
                        when (displayFor) {
                            DisplayFor.BASIC_LEVEL -> BasicApp()         // Базовий рівень
                            DisplayFor.MIDDLE_LEVEL -> MediumApp()       // Середній рівень
                            DisplayFor.ADVANCED_LEVEL -> AdvancedApp()   // Просунутий рівень
                        }
                    }
                }
            }
        }
    }
}
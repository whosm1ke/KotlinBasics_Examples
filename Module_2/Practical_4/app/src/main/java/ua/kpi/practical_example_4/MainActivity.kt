package ua.kpi.practical_example_4

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
import ua.kpi.practical_example_4.composables.AdvancedLoginScreen
import ua.kpi.practical_example_4.composables.BasicLoginScreen
import ua.kpi.practical_example_4.composables.DisplayModeSelector
import ua.kpi.practical_example_4.composables.MediumLoginScreen
import ua.kpi.practical_example_4.ui.theme.Practical_Example_4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Вмикаємо edge-to-edge режим для Activity
        enableEdgeToEdge()
        
        // Налаштовуємо вміст активності за допомогою Compose
        setContent {
            Practical_Example_4Theme {
                // Створюємо стан для вибору режиму відображення (рівень складності)
                var displayFor by remember { mutableStateOf(DisplayFor.BASIC_LEVEL) }

                Surface(modifier = Modifier.fillMaxSize()) {
                    Column {
                        // Відображаємо перемикач для вибору рівня складності
                        DisplayModeSelector(
                            selected = displayFor,
                            onSelectedChange = { displayFor = it } // Оновлюємо стан при зміні вибору
                        )

                        // Додаємо простір між компонентами
                        Spacer(modifier = Modifier.height(16.dp))

                        // Відображаємо відповідний екран в залежності від обраного рівня складності
                        when (displayFor) {
                            DisplayFor.BASIC_LEVEL -> BasicLoginScreen()     // Базовий екран входу
                            DisplayFor.MIDDLE_LEVEL -> MediumLoginScreen()   // Середній екран входу
                            DisplayFor.ADVANCED_LEVEL -> AdvancedLoginScreen() // Просунутий екран входу
                        }
                    }
                }
            }
        }
    }
}
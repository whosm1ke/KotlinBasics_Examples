package ua.kpi.practical_example_6

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_6.composables.AdvancedApp
import ua.kpi.practical_example_6.composables.BasicApp
import ua.kpi.practical_example_6.composables.DisplayModeSelector
import ua.kpi.practical_example_6.composables.MediumApp
import ua.kpi.practical_example_6.ui.theme.Practical_Example_6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Встановлюємо вміст активності за допомогою Compose
        setContent {
            Practical_Example_6Theme {
                // Створюємо стан для вибору режиму відображення
                var displayFor by remember { mutableStateOf(DisplayFor.BASIC_LEVEL) }
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column {
                        // Відображаємо перемикач для вибору рівня складності
                        DisplayModeSelector(
                            selected = displayFor,
                            onSelectedChange = { displayFor = it } // Оновлюємо стан при зміні вибору
                        )

                        Spacer(modifier = Modifier.height(4.dp)) // Додаємо простір між елементами

                        // Відображаємо відповідний компонент залежно від обраного рівня
                        when (displayFor) {
                            DisplayFor.BASIC_LEVEL -> BasicApp()       // Базовий рівень
                            DisplayFor.MIDDLE_LEVEL -> MediumApp()     // Середній рівень
                            DisplayFor.ADVANCED_LEVEL -> AdvancedApp()  // Просунутий рівень
                        }
                    }
                }
            }
        }
    }
}
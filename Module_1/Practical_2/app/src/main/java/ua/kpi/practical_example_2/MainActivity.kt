package ua.kpi.practical_example_2

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
import ua.kpi.practical_example_2.composables.AdvancedEnergyApp
import ua.kpi.practical_example_2.composables.BasicEnergyApp
import ua.kpi.practical_example_2.composables.DisplayModeSelector
import ua.kpi.practical_example_2.composables.MediumEnergyApp
import ua.kpi.practical_example_2.ui.theme.Practical_Example_2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Встановлюємо вміст активності за допомогою Compose
        setContent {
            Practical_Example_2Theme {
                // Створюємо змінну для відстеження обраного режиму відображення
                var displayFor by remember { mutableStateOf(DisplayFor.BASIC_LEVEL) }

                Surface(modifier = Modifier.fillMaxSize()) {
                    Column {
                        // Відображаємо перемикач для вибору режиму
                        DisplayModeSelector(
                            selected = displayFor,
                            onSelectedChange = { displayFor = it } // Оновлюємо стан при зміні вибору
                        )

                        Spacer(modifier = Modifier.height(16.dp)) // Додаємо простір між елементами

                        // Відображаємо відповідний компонент залежно від обраного режиму
                        when (displayFor) {
                            DisplayFor.BASIC_LEVEL -> BasicEnergyApp()        // Базовий рівень
                            DisplayFor.MIDDLE_LEVEL -> MediumEnergyApp()      // Середній рівень
                            DisplayFor.ADVANCED_LEVEL -> AdvancedEnergyApp()   // Просунутий рівень
                        }
                    }
                }
            }
        }
    }
}
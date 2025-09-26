package ua.kpi.practical_example_10

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
import ua.kpi.practical_example_10.composables.AdvancedApp
import ua.kpi.practical_example_10.composables.BasicApp
import ua.kpi.practical_example_10.composables.DisplayModeSelector
import ua.kpi.practical_example_10.composables.MediumApp
import ua.kpi.practical_example_10.ui.theme.Practical_Example_10Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Встановлюємо вміст активності за допомогою Compose
        setContent {
            Practical_Example_10Theme {
                // Створюємо змінну для відстеження поточного режиму відображення
                var displayFor by remember { mutableStateOf(DisplayFor.BASIC_LEVEL) }

                Surface(modifier = Modifier.fillMaxSize()) {
                    Column {
                        // Відображаємо перемикач режимів відображення
                        DisplayModeSelector(
                            selected = displayFor,
                            onSelectedChange = { displayFor = it } // Оновлюємо значення при зміні вибору
                        )

                        Spacer(modifier = Modifier.height(16.dp)) // Додаємо простір між елементами

                        // Відображаємо відповідну компоненту в залежності від обраного режиму
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
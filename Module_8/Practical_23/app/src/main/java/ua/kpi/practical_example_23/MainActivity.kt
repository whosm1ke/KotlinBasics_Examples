package ua.kpi.practical_example_23

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_23.composables.AdvancedApp
import ua.kpi.practical_example_23.composables.BasicApp
import ua.kpi.practical_example_23.composables.DisplayModeSelector
import ua.kpi.practical_example_23.composables.MediumApp
import ua.kpi.practical_example_23.ui.theme.Practical_Example_23Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Встановлення вмісту активності з використанням Compose
        setContent {
            Practical_Example_23Theme {
                // Створення змінної для відстеження поточного рівня відображення
                var displayFor by remember { mutableStateOf(DisplayFor.BASIC_LEVEL) }

                Surface(modifier = Modifier.fillMaxSize()) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        // Відображення перемикача для вибору рівня складності
                        DisplayModeSelector(
                            selected = displayFor,
                            onSelectedChange = { displayFor = it } // Оновлення значення при зміні вибору
                        )

                        Spacer(modifier = Modifier.height(16.dp)) // Простір між елементами

                        // Відображення відповідного компонента відповідно до обраного рівня
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
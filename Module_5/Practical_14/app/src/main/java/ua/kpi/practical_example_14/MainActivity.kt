package ua.kpi.practical_example_14

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
import ua.kpi.practical_example_14.composables.AdvancedApp
import ua.kpi.practical_example_14.composables.BasicApp
import ua.kpi.practical_example_14.composables.DisplayModeSelector
import ua.kpi.practical_example_14.composables.MediumApp
import ua.kpi.practical_example_14.ui.theme.Practical_Example_14Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Встановлення вмісту активності за допомогою Compose
        setContent {
            Practical_Example_14Theme {
                // Створення стану для вибору рівня складності з значенням за замовчуванням
                var displayFor by remember { mutableStateOf(DisplayFor.BASIC_LEVEL) }

                Surface(modifier = Modifier.fillMaxSize()) {
                    Column {
                        // Відображення перемикача для вибору рівня складності
                        DisplayModeSelector(
                            selected = displayFor,
                            onSelectedChange = { displayFor = it } // Оновлення стану при зміні вибору
                        )

                        Spacer(modifier = Modifier.height(16.dp)) // Простір між елементами

                        // Відображення відповідного компонента залежно від обраного рівня складності
                        when (displayFor) {
                            DisplayFor.BASIC_LEVEL -> BasicApp()         // Відображення базового рівня
                            DisplayFor.MIDDLE_LEVEL -> MediumApp()       // Відображення середнього рівня
                            DisplayFor.ADVANCED_LEVEL -> AdvancedApp()   // Відображення просунутого рівня
                        }
                    }
                }
            }
        }
    }
}
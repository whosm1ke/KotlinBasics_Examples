package ua.kpi.practical_example_5

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
import androidx.navigation.compose.rememberNavController
import ua.kpi.practical_example_5.composables.AdvancedNavHostController
import ua.kpi.practical_example_5.composables.BasicNavHostController
import ua.kpi.practical_example_5.composables.DisplayModeSelector
import ua.kpi.practical_example_5.composables.MediumNavHostController
import ua.kpi.practical_example_5.ui.theme.Practical_Example_5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Встановлення вмісту активності з використанням Compose
        setContent {
            Practical_Example_5Theme {
                // Створення стану для вибору режиму відображення
                var displayFor by remember { mutableStateOf(DisplayFor.BASIC_LEVEL) }
                // Створення навігаційного контролера для управління навігацією між екранами
                val navController = rememberNavController()
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column {
                        // Відображення перемикача режимів відображення
                        DisplayModeSelector(
                            selected = displayFor,
                            onSelectedChange = { displayFor = it }
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        // Вибір та відображення відповідного навігаційного контролера залежно від обраного режиму
                        when (displayFor) {
                            DisplayFor.BASIC_LEVEL -> BasicNavHostController(navController)
                            DisplayFor.MIDDLE_LEVEL -> MediumNavHostController(navController)
                            DisplayFor.ADVANCED_LEVEL -> AdvancedNavHostController(navController)
                        }
                    }
                }
            }
        }
    }
}
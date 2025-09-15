package ua.kpi.practical_example_12

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
import ua.kpi.practical_example_12.composables.AdvancedApp
import ua.kpi.practical_example_12.composables.BasicApp
import ua.kpi.practical_example_12.composables.DisplayModeSelector
import ua.kpi.practical_example_12.composables.MediumApp
import ua.kpi.practical_example_12.ui.theme.Practical_Example_12Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Practical_Example_12Theme {
                var displayFor by remember { mutableStateOf(DisplayFor.BASIC_LEVEL) }

                Surface(modifier = Modifier.fillMaxSize()) {
                    Column {
                        // Наш перемикач
                        DisplayModeSelector(
                            selected = displayFor,
                            onSelectedChange = { displayFor = it }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Відображення відповідного екрану
                        when (displayFor) {
                            DisplayFor.BASIC_LEVEL -> BasicApp() // - для базового
                            DisplayFor.MIDDLE_LEVEL -> MediumApp() // - для середнього
                            DisplayFor.ADVANCED_LEVEL -> AdvancedApp() // - для просунутого
                        }
                    }
                }
            }
        }
    }
}


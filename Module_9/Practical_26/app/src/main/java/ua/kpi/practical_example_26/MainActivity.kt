package ua.kpi.practical_example_26

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_26.composables.AdvancedApp
import ua.kpi.practical_example_26.composables.BasicApp
import ua.kpi.practical_example_26.composables.DisplayModeSelector
import ua.kpi.practical_example_26.composables.MediumApp
import ua.kpi.practical_example_26.ui.theme.Practical_Example_26Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Глобальний стан теми
            var darkTheme by remember { mutableStateOf(false) }
            var displayFor by remember { mutableStateOf(DisplayFor.BASIC_LEVEL) }

            // Тема динамічно реагує на darkTheme
            Practical_Example_26Theme(darkTheme = darkTheme) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {


                        // Перемикач рівня складності
                        DisplayModeSelector(
                            selected = displayFor,
                            onSelectedChange = { displayFor = it }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Перемикач теми на верхньому рівні
                        if (displayFor == DisplayFor.MIDDLE_LEVEL || displayFor == DisplayFor.ADVANCED_LEVEL) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(if (darkTheme) "Темна тема" else "Світла тема")
                                Spacer(modifier = Modifier.width(8.dp))
                                Switch(
                                    checked = darkTheme,
                                    onCheckedChange = { darkTheme = it } // зміна глобальної теми
                                )
                            }
                        }


                        // Відображення UI відповідно до рівня
                        when (displayFor) {
                            DisplayFor.BASIC_LEVEL -> BasicApp()
                            DisplayFor.MIDDLE_LEVEL -> MediumApp(
                                isDarkTheme = darkTheme,
                            )

                            DisplayFor.ADVANCED_LEVEL -> AdvancedApp(
                                isDarkTheme = darkTheme,
                            )
                        }
                    }
                }
            }
        }
    }
}
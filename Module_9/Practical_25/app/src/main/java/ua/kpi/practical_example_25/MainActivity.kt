package ua.kpi.practical_example_25

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
import ua.kpi.practical_example_25.composables.AdvancedApp
import ua.kpi.practical_example_25.composables.BasicApp
import ua.kpi.practical_example_25.composables.DisplayModeSelector
import ua.kpi.practical_example_25.composables.MediumApp
import ua.kpi.practical_example_25.ui.theme.Practical_Example_25Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Встановлення вмісту активності за допомогою Compose
        setContent {
            Practical_Example_25Theme {
                // Створення стану для вибору рівня складності (використовується remember для збереження стану між перерисуваннями)
                var displayFor by remember { mutableStateOf(DisplayFor.BASIC_LEVEL) }

                // Основна поверхня для відображення UI
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        // Відображення перемикача рівня складності
                        DisplayModeSelector(
                            selected = displayFor,
                            onSelectedChange = { displayFor = it } // Оновлення стану при зміні вибору
                        )

                        Spacer(modifier = Modifier.height(16.dp)) // Простір між елементами

                        // Відображення відповідного компонента в залежності від обраного рівня
                        when (displayFor) {
                            DisplayFor.BASIC_LEVEL -> BasicApp()          // Базовий рівень
                            DisplayFor.MIDDLE_LEVEL -> MediumApp()        // Середній рівень
                            DisplayFor.ADVANCED_LEVEL -> AdvancedApp()    // Розширений рівень
                        }
                    }
                }
            }
        }
    }
}
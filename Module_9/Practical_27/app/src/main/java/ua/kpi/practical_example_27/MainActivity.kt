package ua.kpi.practical_example_27

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.kpi.practical_example_27.ui.theme.Practical_Example_27Theme
import ua.kpi.practical_example_27.viewModel.ForecastViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: ForecastViewModel = viewModel()
            Practical_Example_27Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SolarForecastApp(viewModel)
                }
            }
        }
    }
}

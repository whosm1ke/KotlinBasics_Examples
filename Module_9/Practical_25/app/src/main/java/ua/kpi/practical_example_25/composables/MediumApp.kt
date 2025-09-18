package ua.kpi.practical_example_25.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ua.kpi.practical_example_25.data.MediumCalculator


@Composable
fun MediumApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "input") {
        composable("input") { InputScreen(navController) }
        composable("result/{value}") { backStackEntry ->
            val result = backStackEntry.arguments?.getString("value") ?: "0.0"
            ResultScreen(result)
        }
    }
}

/**
 * Екран введення даних
 */
@Composable
fun InputScreen(navController: NavHostController) {
    var solarIrradiance by remember { mutableStateOf("") }
    var temperature by remember { mutableStateOf("") }
    var panelArea by remember { mutableStateOf("") }
    val calculator = MediumCalculator()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        OutlinedTextField(
            value = solarIrradiance,
            onValueChange = { solarIrradiance = it },
            label = { Text("Сонячна радіація (Вт/м²)") },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("MediumSolarRadiation")
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = temperature,
            onValueChange = { temperature = it },
            label = { Text("Температура (°C)") },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("MediumTemperature")
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = panelArea,
            onValueChange = { panelArea = it },
            label = { Text("Площа панелей (м²)") },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("MediumPanelArea")
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val irradianceVal = solarIrradiance.toDoubleOrNull()
                val temperatureVal = temperature.toDoubleOrNull()
                val areaVal = panelArea.toDoubleOrNull()

                if (irradianceVal != null && temperatureVal != null && areaVal != null) {
                    val result = calculator.calculatePower(irradianceVal, temperatureVal, areaVal)
                    navController.navigate("result/${"%.2f".format(result)}")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("MediumCalculateButton")
        ) {
            Text("Розрахувати")
        }
    }
}

/**
 * Екран результату
 */
@Composable
fun ResultScreen(result: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Прогнозована потужність: $result кВт",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.testTag("MediumResult")
        )
    }
}

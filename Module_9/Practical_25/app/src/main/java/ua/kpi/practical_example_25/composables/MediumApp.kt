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

/**
 * Головна компонента додатку, що відповідає за навігацію між екранами
 */
@Composable
fun MediumApp() {
    // Створення контролера навігації для керування переходами між екранами
    val navController = rememberNavController()

    // Встановлення навігаційного контейнера з початковою точкою "input"
    NavHost(navController = navController, startDestination = "input") {
        // Компонента для екрана введення даних
        composable("input") { InputScreen(navController) }
        
        // Компонента для екрана результатів з передачею параметра результату
        composable("result/{value}") { backStackEntry ->
            val result = backStackEntry.arguments?.getString("value") ?: "0.0"
            ResultScreen(result)
        }
    }
}

/**
 * Екран введення даних для розрахунку потужності сонячної енергії
 */
@Composable
fun InputScreen(navController: NavHostController) {
    // Створення змінних стану для зберігання введених значень
    var solarIrradiance by remember { mutableStateOf("") }
    var temperature by remember { mutableStateOf("") }
    var panelArea by remember { mutableStateOf("") }
    
    // Створення екземпляра калькулятора для обчислення потужності
    val calculator = MediumCalculator()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top // Вирівнювання елементів по вертикалі зверху
    ) {
        // Поле вводу для сонячної радіації
        OutlinedTextField(
            value = solarIrradiance,
            onValueChange = { solarIrradiance = it },
            label = { Text("Сонячна радіація (Вт/м²)") },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("MediumSolarRadiation") // Тег для тестування
        )

        Spacer(modifier = Modifier.height(8.dp)) // Простір між полями вводу

        // Поле вводу для температури
        OutlinedTextField(
            value = temperature,
            onValueChange = { temperature = it },
            label = { Text("Температура (°C)") },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("MediumTemperature") // Тег для тестування
        )

        Spacer(modifier = Modifier.height(8.dp)) // Простір між полями вводу

        // Поле вводу для площі панелей
        OutlinedTextField(
            value = panelArea,
            onValueChange = { panelArea = it },
            label = { Text("Площа панелей (м²)") },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("MediumPanelArea") // Тег для тестування
        )

        Spacer(modifier = Modifier.height(16.dp)) // Простір перед кнопкою

        // Кнопка розрахунку потужності
        Button(
            onClick = {
                // Конвертація введених значень у тип double або повернення null, якщо некоректно
                val irradianceVal = solarIrradiance.toDoubleOrNull()
                val temperatureVal = temperature.toDoubleOrNull()
                val areaVal = panelArea.toDoubleOrNull()

                // Якщо всі значення коректні, обчислюємо результат
                if (irradianceVal != null && temperatureVal != null && areaVal != null) {
                    val result = calculator.calculatePower(irradianceVal, temperatureVal, areaVal)
                    // Переход на екран результату з передачею обчисленого значення
                    navController.navigate("result/${"%.2f".format(result)}")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("MediumCalculateButton") // Тег для тестування
        ) {
            Text("Розрахувати") // Текст кнопки
        }
    }
}

/**
 * Екран відображення результату розрахунку потужності
 */
@Composable
fun ResultScreen(result: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center // Вирівнювання елементів по центру вертикально
    ) {
        // Відображення результату з використанням теми Material Design
        Text(
            text = "Прогнозована потужність: $result кВт",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.testTag("MediumResult") // Тег для тестування
        )
    }
}
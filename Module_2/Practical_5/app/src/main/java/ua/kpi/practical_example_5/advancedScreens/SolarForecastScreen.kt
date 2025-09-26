package ua.kpi.practical_example_5.advancedScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ua.kpi.practical_example_5.Screen
import ua.kpi.practical_example_5.components.MenuButton
import ua.kpi.practical_example_5.components.ScreenTitle

@Composable
fun SolarForecastScreen(navController: NavHostController, userName: String) {
    // Змінна для зберігання введеного значення кількості панелей
    var panelsCountInput by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Відображення привітання з ім'ям користувача
        ScreenTitle("Привіт, $userName!")

        // Текст заголовка для прогнозу потужності СЕС
        Text("Прогноз потужності Сонячної електростанції", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(16.dp))

        // Поле вводу для кількості панелей
        OutlinedTextField(
            value = panelsCountInput,
            onValueChange = { panelsCountInput = it },
            label = { Text("Кількість панелей") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Ряд з кнопками для подальших дій
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            // Кнопка "Розрахувати потужність" - навігація до екрану деталей
            MenuButton("Розрахувати потужність") {
                // Перетворюємо введене значення на число або встановлюємо 0, якщо не вдалося
                val count = panelsCountInput.toIntOrNull() ?: 0
                // Навігація до екрану деталей з передачею кількості панелей
                navController.navigate("${Screen.AdvancedScreen.SolarDetails.route}/$count")
            }

            // Кнопка "Повернутися в меню" - навігація назад до головного екрану
            MenuButton("Повернутися в меню") { navController.navigate(Screen.AdvancedScreen.MainScreen.route) }
        }
    }
}
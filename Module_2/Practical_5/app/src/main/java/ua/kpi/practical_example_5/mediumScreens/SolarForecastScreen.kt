package ua.kpi.practical_example_5.mediumScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ua.kpi.practical_example_5.Screen

@Composable
fun SolarForecastScreen(navController: NavHostController, userName: String) {
    var panelsCountInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Привіт, $userName!", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Прогноз потужності Сонячної електростанції", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = panelsCountInput,
            onValueChange = { panelsCountInput = it },
            label = { Text("Кількість панелей") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(
                onClick = { navController.navigate("${Screen.MediumScreen.SolarDetails.route}/${panelsCountInput.toIntOrNull() ?: 0}") }
            ) {
                Text("Розрахувати потужність")
            }

            Button(onClick = { navController.navigate(Screen.MediumScreen.MainScreen.route) }) {
                Text("Повернутися в меню")
            }
        }
    }
}
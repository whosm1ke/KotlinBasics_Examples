package ua.kpi.practical_example_3.composables
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.kpi.practical_example_3.R


@Composable
fun MediumEnergyApp() {
    // Створюємо стан для зберігання значення потужності та обраного режиму
    var power by remember { mutableStateOf(0) }
    var mode by remember { mutableStateOf("Денні") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Відображаємо заголовок додатку
        Text(
            text = "Сонячна електростанція - Прогноз потужності",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Створюємо рядок з кнопками для вибору режиму роботи
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { mode = "Денні"; power = 60 }) {
                Text("Денні")
            }
            Button(onClick = { mode = "Нічні"; power = 10 }) {
                Text("Нічні")
            }
            Button(onClick = { mode = "Хмарні"; power = 30 }) {
                Text("Хмарні")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Відображаємо зображення сонячної панелі
        Image(
            painter = painterResource(id = R.drawable.solar_panel),
            contentDescription = "Сонячна панель",
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Відображаємо обраний режим та прогнозовану потужність
        Text(
            text = "Режим: $mode",
            fontSize = 18.sp
        )
        Text(
            text = "Прогнозована потужність: $power кВт",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
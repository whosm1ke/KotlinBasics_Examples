package ua.kpi.practical_example_3.composables
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.kpi.practical_example_3.R

@Composable
fun AdvancedEnergyApp() {
    // Створення станів для програми:
    // - power: потужність, що генерується (в кВт)
    // - mode: режим роботи ("Денні", "Нічні", "Хмарні")
    // - comments: коментарі до поточного режиму
    var power by remember { mutableStateOf(0) }
    var mode by remember { mutableStateOf("Денні") }
    var comments by remember { mutableStateOf("Все стабільно") }

    // Основний вертикальний layout для всієї програми
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Відображення заголовка додатку
        Text(
            text = "Сонячна електростанція - Детальний прогноз",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Ряд кнопок режимів роботи з можливістю вибору
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ModeButton("Денні", 60) { mode = it.first; power = it.second; comments = "Максимальна генерація" }
            ModeButton("Нічні", 10) { mode = it.first; power = it.second; comments = "Мінімальна генерація" }
            ModeButton("Хмарні", 30) { mode = it.first; power = it.second; comments = "Обмежена генерація" }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Зображення сонячної панелі
        Image(
            painter = painterResource(id = R.drawable.solar_panel),
            contentDescription = "Сонячна панель",
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Відображення інформаційної картки з даними про потужність, режим та коментарі
        InfoCard(power, mode, comments)
    }
}

// Компонент кнопки режиму:
// При натисканні встановлюється новий режим, потужність і коментар
@Composable
fun ModeButton(label: String, value: Int, onClick: (Pair<String, Int>) -> Unit) {
    Button(
        onClick = { onClick(label to value) },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = label)
    }
}

// Компонент інформаційної картки:
// Відображає режим, прогнозовану потужність та коментар
@Composable
fun InfoCard(power: Int, mode: String, comments: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Режим: $mode", fontSize = 18.sp)
            Text("Прогнозована потужність: $power кВт", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text("Коментар: $comments", fontSize = 16.sp)
        }
    }
}
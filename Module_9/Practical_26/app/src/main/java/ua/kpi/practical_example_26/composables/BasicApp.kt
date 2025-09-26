package ua.kpi.practical_example_26.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import ua.kpi.practical_example_26.DeviceUtil

@Composable
fun BasicApp() {
    // Отримання контексту застосунку для доступу до системних даних
    val context = LocalContext.current
    
    // Створення екземпляра утиліти для отримання інформації про пристрій
    val util = DeviceUtil()
    
    // Стан для зберігання рівня заряду батареї
    var batteryLevel by remember { mutableStateOf(0) }
    
    // Стан для зберігання навантаження процесора
    var cpuLoad by remember { mutableStateOf(0) }
    
    // Стан для зберігання використання пам'яті
    var memoryUsage by remember { mutableStateOf(0) }

    // Запуск фонового процесу для регулярного оновлення даних
    LaunchedEffect(Unit) {
        while (true) {
            // Отримання поточного рівня заряду батареї
            batteryLevel = util.getBatteryLevel(context)
            
            // Отримання навантаження процесора
            cpuLoad = util.getCpuUsage()
            
            // Отримання використання пам'яті
            memoryUsage = util.getMemoryUsage(context)
            
            // Затримка на 2 секунди перед наступним оновленням
            delay(2000)
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Відображення заголовка додатку
        Text("Базовий рівень: Моніторинг ресурсів", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        // Відображення прогрес-бара для процесора з використанням первинного кольору теми
        ResourceBar("CPU", cpuLoad, MaterialTheme.colorScheme.primary)
        
        // Відображення прогрес-бара для пам'яті з використанням вторинного кольору теми
        ResourceBar("Memory", memoryUsage, MaterialTheme.colorScheme.secondary)
        
        // Відображення прогрес-бара для батареї з використанням третинного кольору теми
        ResourceBar("Battery", batteryLevel, MaterialTheme.colorScheme.tertiary)
    }
}
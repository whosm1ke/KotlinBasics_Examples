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
    val context = LocalContext.current
    val util = DeviceUtil()
    var batteryLevel by remember { mutableStateOf(0) }
    var cpuLoad by remember { mutableStateOf(0) }
    var memoryUsage by remember { mutableStateOf(0) }

    // Циклічне оновлення ресурсів
    LaunchedEffect(Unit) {
        while (true) {
            batteryLevel = util.getBatteryLevel(context)
            cpuLoad = util.getCpuUsage()
            memoryUsage = util.getMemoryUsage(context)
            delay(2000)
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Базовий рівень: Моніторинг ресурсів", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        // Прогрес-бари з динамічним кольором теми
        ResourceBar("CPU", cpuLoad, MaterialTheme.colorScheme.primary)
        ResourceBar("Memory", memoryUsage, MaterialTheme.colorScheme.secondary)
        ResourceBar("Battery", batteryLevel, MaterialTheme.colorScheme.tertiary)
    }
}



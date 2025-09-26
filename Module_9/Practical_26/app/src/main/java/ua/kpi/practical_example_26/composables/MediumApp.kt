package ua.kpi.practical_example_26.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.coroutines.delay
import ua.kpi.practical_example_26.DeviceUtil
import android.graphics.Color as AndroidColor

@Composable
fun MediumApp(
    isDarkTheme: Boolean,
) {
    // Отримуємо поточний контекст для доступу до системних даних
    val context = LocalContext.current
    
    // Створюємо змінні стану для відображення показників пристрою
    var batteryLevel by remember { mutableStateOf(0) }
    var cpuLoad by remember { mutableStateOf(0) }
    var memoryUsage by remember { mutableStateOf(0) }
    
    // Ініціалізуємо утиліту для отримання даних пристрою
    val util = DeviceUtil()

    // Створюємо списки для зберігання історичних даних графіків
    val cpuHistory = remember { mutableStateListOf<Entry>() }
    val memoryHistory = remember { mutableStateListOf<Entry>() }
    val batteryHistory = remember { mutableStateListOf<Entry>() }

    // Запускаємо фоновий процес для оновлення даних
    LaunchedEffect(Unit) {
        var time = 0f
        while (true) {
            // Отримуємо актуальні дані з пристрою
            batteryLevel = util.getBatteryLevel(context)
            cpuLoad = util.getCpuUsage()
            memoryUsage = util.getMemoryUsage(context)

            // Додаємо нові точки на графік
            cpuHistory.add(Entry(time, cpuLoad.toFloat()))
            memoryHistory.add(Entry(time, memoryUsage.toFloat()))
            batteryHistory.add(Entry(time, batteryLevel.toFloat()))
            
            // Обмежуємо кількість точок на графіку до 20 для оптимізації
            if (cpuHistory.size > 20) {
                cpuHistory.removeAt(0)
                memoryHistory.removeAt(0)
                batteryHistory.removeAt(0)
            }

            time += 1f
            delay(2000) // Затримка між оновленнями (2 секунди)
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Відображаємо графіки ресурсів
        LazyColumn {
            item { UniversalResourceLineChart("CPU", cpuHistory, isDarkTheme) }
            item { UniversalResourceLineChart("Memory", memoryHistory, isDarkTheme) }
            item { UniversalResourceLineChart("Battery", batteryHistory, isDarkTheme) }
        }

    }
}
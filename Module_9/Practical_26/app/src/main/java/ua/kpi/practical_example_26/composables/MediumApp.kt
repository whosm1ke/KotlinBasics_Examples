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
    val context = LocalContext.current
    var batteryLevel by remember { mutableStateOf(0) }
    var cpuLoad by remember { mutableStateOf(0) }
    var memoryUsage by remember { mutableStateOf(0) }
    val util = DeviceUtil()

    // Збереження історії показників для графіка
    val cpuHistory = remember { mutableStateListOf<Entry>() }
    val memoryHistory = remember { mutableStateListOf<Entry>() }
    val batteryHistory = remember { mutableStateListOf<Entry>() }

    LaunchedEffect(Unit) {
        var time = 0f
        while (true) {
            batteryLevel = util.getBatteryLevel(context)
            cpuLoad = util.getCpuUsage()
            memoryUsage = util.getMemoryUsage(context)

            // Додаємо точки на графік
            cpuHistory.add(Entry(time, cpuLoad.toFloat()))
            memoryHistory.add(Entry(time, memoryUsage.toFloat()))
            batteryHistory.add(Entry(time, batteryLevel.toFloat()))
            if (cpuHistory.size > 20) { // обмежимо історію до 20 точок
                cpuHistory.removeAt(0)
                memoryHistory.removeAt(0)
                batteryHistory.removeAt(0)
            }

            time += 1f
            delay(2000)
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Графіки ресурсів
        LazyColumn {
            item { UniversalResourceLineChart("CPU", cpuHistory, isDarkTheme) }
            item { UniversalResourceLineChart("Memory", memoryHistory, isDarkTheme) }
            item { UniversalResourceLineChart("Battery", batteryHistory, isDarkTheme) }
        }

    }
}


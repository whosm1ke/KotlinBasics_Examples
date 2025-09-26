package ua.kpi.practical_example_26.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.mikephil.charting.data.Entry
import kotlinx.coroutines.delay
import ua.kpi.practical_example_26.DeviceUtil
import kotlin.random.Random

@Composable
fun AdvancedApp(
    isDarkTheme: Boolean,
) {
    // Отримуємо поточний контекст для доступу до системних даних
    val context = LocalContext.current
    // Створюємо екземпляр утиліти для отримання інформації про пристрій
    val util = DeviceUtil()

    // Стан для зберігання поточних значень ресурсів
    var batteryLevel by remember { mutableStateOf(0) }
    var cpuLoad by remember { mutableStateOf(0) }
    var memoryUsage by remember { mutableStateOf(0) }
    var networkUsage by remember { mutableStateOf(0) }

    // Списки для зберігання історії значень ресурсів (для графіків)
    val cpuHistory = remember { mutableStateListOf<Entry>() }
    val memoryHistory = remember { mutableStateListOf<Entry>() }
    val batteryHistory = remember { mutableStateListOf<Entry>() }
    val networkHistory = remember { mutableStateListOf<Entry>() }

    // Анімовані значення для плавного відображення прогресу
    val animatedCpu by animateFloatAsState(targetValue = cpuLoad.toFloat())
    val animatedMemory by animateFloatAsState(targetValue = memoryUsage.toFloat())
    val animatedBattery by animateFloatAsState(targetValue = batteryLevel.toFloat())
    val animatedNetwork by animateFloatAsState(targetValue = networkUsage.toFloat())

    // Запускаємо фонову задачу для регулярного оновлення даних
    LaunchedEffect(Unit) {
        var time = 0f // Змінна для відстеження часу на графіку
        while (true) {
            // Оновлюємо значення ресурсів
            batteryLevel = util.getBatteryLevel(context)
            cpuLoad = util.getCpuUsage()
            memoryUsage = util.getMemoryUsage(context)
            networkUsage = Random.nextInt(0, 100) // Випадкове значення для мережевого використання

            // Додаємо нові точки на графіки
            cpuHistory.add(Entry(time, cpuLoad.toFloat()))
            memoryHistory.add(Entry(time, memoryUsage.toFloat()))
            batteryHistory.add(Entry(time, batteryLevel.toFloat()))
            networkHistory.add(Entry(time, networkUsage.toFloat()))

            // Обмежуємо історію до 20 точок для ефективності
            if (cpuHistory.size > 20) {
                cpuHistory.removeAt(0)
                memoryHistory.removeAt(0)
                batteryHistory.removeAt(0)
                networkHistory.removeAt(0)
            }

            time += 1f // Збільшуємо час для наступної точки
            delay(2000) // Затримка 2 секунди перед наступним оновленням
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // Відображаємо анімовані індикатори ресурсів
        AnimatedResourceBar("CPU", animatedCpu, MaterialTheme.colorScheme.primary)
        AnimatedResourceBar("Memory", animatedMemory, MaterialTheme.colorScheme.secondary)
        AnimatedResourceBar("Battery", animatedBattery, MaterialTheme.colorScheme.tertiary)
        AnimatedResourceBar("Network", animatedNetwork, MaterialTheme.colorScheme.primary.copy(alpha = 0.7f))

        // Кнопка для очищення історії графіків
        Button(onClick = {
            cpuHistory.clear()
            memoryHistory.clear()
            batteryHistory.clear()
            networkHistory.clear()
        }) {
            Text("Очистити історію графіків")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Відображаємо графіки ресурсів у списку
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            item { UniversalResourceLineChart("CPU", cpuHistory, isDarkTheme) }
            item { UniversalResourceLineChart("Memory", memoryHistory, isDarkTheme) }
            item { UniversalResourceLineChart("Battery", batteryHistory, isDarkTheme) }
            item { UniversalResourceLineChart("Network", networkHistory, isDarkTheme) }
        }
    }
}
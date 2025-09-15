package ua.kpi.practical_example_13.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicApp() {
    // Стан для відображення потужності
    var power by remember { mutableStateOf(0f) }

    // CoroutineScope для збору даних
    val scope = rememberCoroutineScope()

    // Збирання даних з Flow при запуску UI
    LaunchedEffect(Unit) {
        getSolarPowerFlow()
            .collect { value ->
                power = value
            }
    }

    Column {
        Text(text = "Базовий рівень: Прогноз потужності сонячної станції")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Поточна потужність: ${"%.2f".format(power)} кВт")
    }
}

// Створення холодного потоку, який імітує надходження даних від сенсора
fun getSolarPowerFlow(): Flow<Float> = flow {
    while (true) {
        delay(1000) // затримка 1 секунда
        val power = Random.nextFloat() * 100f // випадкова потужність від 0 до 100 кВт
        emit(power)
    }
}


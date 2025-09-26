package ua.kpi.practical_example_19.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.BarChart
import kotlin.random.Random

@Composable
fun MediumApp() {
    // Основний колон для розміщення елементів
    Column(modifier = Modifier.fillMaxSize()) {
        // Відображення заголовка
        Text(
            text = "Порівняння енергоспоживання по зонах",
            style = MaterialTheme.typography.titleMedium
        )

        // Відступ між заголовком і графіком
        Spacer(modifier = Modifier.height(16.dp))

        // Стан для відстеження вибраного стовпчика (зона та значення споживання)
        var selectedBar by remember { mutableStateOf<Pair<String, Float>?>(null) }

        // Контейнер для графіка з фіксованою висотою
        Box(modifier = Modifier.fillMaxWidth().height(300.dp)) {
            AndroidView(
                factory = { context ->
                    // Створення об'єкта BarChart
                    BarChart(context).apply {
                        // Ініціалізація випадкового генератора для створення даних
                        val random = Random(123)
                        // Список зон
                        val zones = listOf("Північна", "Південна", "Східна", "Західна", "Центр")
                        // Колекція для зберігання даних стовпчиків
                        val entries = mutableListOf<com.github.mikephil.charting.data.BarEntry>()

                        // Генерація випадкових значень споживання для кожної зони
                        zones.forEachIndexed { index, _ ->
                            val consumption = 40 + random.nextFloat() * 60
                            entries.add(com.github.mikephil.charting.data.BarEntry(index.toFloat(), consumption))
                        }

                        // Створення набору даних для графіка
                        val dataSet = com.github.mikephil.charting.data.BarDataSet(entries, "Споживання (кВт)").apply {
                            color = android.graphics.Color.MAGENTA  // Колір стовпчиків
                            valueTextColor = android.graphics.Color.BLACK  // Колір тексту значень
                            valueTextSize = 12f  // Розмір шрифту для значень
                            isHighlightEnabled = true  // Включення підсвічування при виборі
                        }

                        // Налаштування форматування осі X (відображення назв зон)
                        xAxis.valueFormatter = object : com.github.mikephil.charting.formatter.ValueFormatter() {
                            override fun getFormattedValue(value: Float): String {
                                val index = value.toInt()
                                return if (index in zones.indices) zones[index] else ""
                            }
                        }
                        xAxis.granularity = 1f  // Встановлення кроку для осі X
                        xAxis.isGranularityEnabled = true  // Включення кроку
                        axisRight.isEnabled = false  // Вимкнення правої осі

                        // Встановлення даних у графік
                        data = com.github.mikephil.charting.data.BarData(dataSet)
                        description.text = "Зони станції"  // Назва графіка
                        setTouchEnabled(true)  // Увімкнення взаємодії з графіком
                        setPinchZoom(true)  // Увімкнення масштабування пальцем
                        setScaleEnabled(true)  // Дозвіл на масштабування
                        animateY(1000)  // Анімація появлення графіка

                        // Встановлення слухача для вибору стовпчика
                        setOnChartValueSelectedListener(object :
                            com.github.mikephil.charting.listener.OnChartValueSelectedListener {
                            override fun onValueSelected(
                                e: com.github.mikephil.charting.data.Entry?,
                                h: com.github.mikephil.charting.highlight.Highlight?
                            ) {
                                if (e != null) {
                                    // Оновлення стану при виборі стовпчика
                                    selectedBar = Pair(zones[e.x.toInt()], e.y)
                                }
                            }

                            override fun onNothingSelected() {
                                // Скидання вибору при натисканні поза графіком
                                selectedBar = null
                            }
                        })
                    }
                },
                modifier = Modifier.fillMaxSize()
            )

            // Відображення інформації про вибраний стовпчик у вигляді карточки
            selectedBar?.let { (zone, consumption) ->
                Card(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)  // Колір карточки
                ) {
                    Text(
                        text = "Зона: $zone\nСпоживання: ${"%.1f".format(consumption)} кВт",  // Відображення назви зони та значення споживання
                        modifier = Modifier.padding(8.dp),
                        color = MaterialTheme.colorScheme.onPrimary  // Колір тексту
                    )
                }
            }
        }
    }
}
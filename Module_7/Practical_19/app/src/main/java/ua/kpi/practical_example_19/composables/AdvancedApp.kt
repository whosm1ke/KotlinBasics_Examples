package ua.kpi.practical_example_19.composables

import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import kotlin.random.Random

@Composable
fun AdvancedApp() {
    // Основний колон з вмістом
    Column(modifier = Modifier.fillMaxSize()) {
        // Відображення заголовка графіка
        Text(
            text = "Комбінований графік енергоспоживання",
            style = MaterialTheme.typography.titleMedium
        )

        // Пропуск між заголовком і панеллю перемикання
        Spacer(modifier = Modifier.height(16.dp))

        // Стан для зберігання вибраного значення та інтервалу
        var selectedValue by remember { mutableStateOf<Pair<String, Float>?>(null) }
        var interval by remember { mutableStateOf("Години") }
        val intervals = listOf("Години", "Дні", "Тижні")

        // Панель перемикання інтервалів (Години, Дні, Тижні)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            intervals.forEach { value ->
                Button(
                    onClick = { interval = value }, // Зміна інтервалу при натисканні
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (interval == value) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text(text = value) // Відображення тексту кнопки
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Місце для відображення графіка
        Box(modifier = Modifier.fillMaxWidth().height(400.dp)) {
            AndroidView(
                factory = { context ->
                    // Створення об'єкта CombinedChart (комбінований графік)
                    com.github.mikephil.charting.charts.CombinedChart(context).apply {
                        description.text = "Сонячна електростанція" // Назва графіка
                        setTouchEnabled(true) // Дозвіл на взаємодію з графіком
                        setPinchZoom(true) // Дозвіл на масштабування пальцем
                        setScaleEnabled(true) // Дозвіл на масштабування
                        axisRight.isEnabled = false // Вимкнення правої осі
                        xAxis.granularity = 1f // Встановлення інтервалу між мітоками на осі X
                        xAxis.isGranularityEnabled = true // Увімкнення гранулярності для осі X
                    }
                },
                update = { chart ->
                    // Генератор випадкових чисел з фіксованим seed'ом для стабільності
                    val random = Random(321)
                    // Список міток для осі X, залежно від обраного інтервалу
                    val labels: List<String>
                    // Списки для даних графіків (стовпчиковий та лінійний)
                    val entriesBar = mutableListOf<com.github.mikephil.charting.data.BarEntry>()
                    val entriesLine = mutableListOf<com.github.mikephil.charting.data.Entry>()

                    // Визначення міток і даних в залежності від обраного інтервалу
                    when (interval) {
                        "Години" -> {
                            labels = (0..23).map { "$it:00" } // Мітки у форматі "HH:00"
                            labels.forEachIndexed { i, _ ->
                                val value = 50 + random.nextFloat() * 50 // Випадкове значення
                                entriesBar.add(com.github.mikephil.charting.data.BarEntry(i.toFloat(), value))
                                entriesLine.add(com.github.mikephil.charting.data.Entry(i.toFloat(), value))
                            }
                        }

                        "Дні" -> {
                            labels = (1..7).map { "День $it" } // Мітки типу "День 1"
                            labels.forEachIndexed { i, _ ->
                                val value = 200 + random.nextFloat() * 100
                                entriesBar.add(com.github.mikephil.charting.data.BarEntry(i.toFloat(), value))
                                entriesLine.add(com.github.mikephil.charting.data.Entry(i.toFloat(), value))
                            }
                        }

                        "Тижні" -> {
                            labels = (1..4).map { "Тиждень $it" } // Мітки типу "Тиждень 1"
                            labels.forEachIndexed { i, _ ->
                                val value = 800 + random.nextFloat() * 200
                                entriesBar.add(com.github.mikephil.charting.data.BarEntry(i.toFloat(), value))
                                entriesLine.add(com.github.mikephil.charting.data.Entry(i.toFloat(), value))
                            }
                        }

                        else -> labels = listOf() // Якщо інтервал не визначений, встановлюємо порожній список
                    }

                    // Створення даних для стовпчикового графіка
                    val barDataSet =
                        com.github.mikephil.charting.data.BarDataSet(entriesBar, "Споживання (кВт)").apply {
                            color = android.graphics.Color.MAGENTA // Колір стовпчиків
                            valueTextColor = android.graphics.Color.BLACK // Колір значень
                            valueTextSize = 10f // Розмір шрифту значень
                            isHighlightEnabled = true // Виділення при натисканні
                        }

                    // Створення даних для лінійного графіка
                    val lineDataSet =
                        com.github.mikephil.charting.data.LineDataSet(entriesLine, "Прогноз лінійний").apply {
                            color = android.graphics.Color.BLUE // Колір лінії
                            lineWidth = 2f // Товщина лінії
                            setDrawCircles(true) // Відображення точок на лінії
                            setDrawValues(false) // Не відображати значення на точках
                            mode = com.github.mikephil.charting.data.LineDataSet.Mode.CUBIC_BEZIER // Тип лінії (кубічна Безьє)
                        }

                    // Об'єднання даних для стовпчикового і лінійного графіків
                    val combinedData = com.github.mikephil.charting.data.CombinedData()
                    combinedData.setData(com.github.mikephil.charting.data.BarData(barDataSet))
                    combinedData.setData(com.github.mikephil.charting.data.LineData(lineDataSet))
                    chart.data = combinedData // Встановлення даних в графік

                    // Форматування осі X з використанням міток
                    chart.xAxis.valueFormatter = object : com.github.mikephil.charting.formatter.ValueFormatter() {
                        override fun getFormattedValue(value: Float): String {
                            val index = value.toInt()
                            return if (index in labels.indices) labels[index] else ""
                        }
                    }

                    // Обробник подій при виборі точки на графіку
                    chart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                        override fun onValueSelected(
                            e: com.github.mikephil.charting.data.Entry?,
                            h: com.github.mikephil.charting.highlight.Highlight?
                        ) {
                            if (e != null) {
                                selectedValue = Pair(labels[e.x.toInt()], e.y) // Оновлення вибраного значення
                            }
                        }

                        override fun onNothingSelected() {
                            selectedValue = null // Скидання вибраного значення при натисканні мимо графіка
                        }
                    })

                    chart.invalidate() // Оновлення графіка
                },
                modifier = Modifier.fillMaxSize()
            )

            // Відображення інформації про вибрану точку
            selectedValue?.let { (label, value) ->
                Card(
                    modifier = Modifier.align(Alignment.TopCenter).padding(top = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(
                        text = "Інтервал: $label\nСпоживання: ${"%.1f".format(value)} кВт",
                        modifier = Modifier.padding(8.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}
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
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Комбінований графік енергоспоживання",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        var selectedValue by remember { mutableStateOf<Pair<String, Float>?>(null) }
        var interval by remember { mutableStateOf("Години") }
        val intervals = listOf("Години", "Дні", "Тижні")

        // Панель перемикання інтервалів
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            intervals.forEach { value ->
                Button(
                    onClick = { interval = value },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (interval == value) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text(text = value)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.fillMaxWidth().height(400.dp)) {
            AndroidView(
                factory = { context ->
                    com.github.mikephil.charting.charts.CombinedChart(context).apply {
                        description.text = "Сонячна електростанція"
                        setTouchEnabled(true)
                        setPinchZoom(true)
                        setScaleEnabled(true)
                        axisRight.isEnabled = false
                        xAxis.granularity = 1f
                        xAxis.isGranularityEnabled = true
                    }
                },
                update = { chart ->
                    val random = Random(321)
                    val labels: List<String>
                    val entriesBar = mutableListOf<com.github.mikephil.charting.data.BarEntry>()
                    val entriesLine = mutableListOf<com.github.mikephil.charting.data.Entry>()

                    when (interval) {
                        "Години" -> {
                            labels = (0..23).map { "$it:00" }
                            labels.forEachIndexed { i, _ ->
                                val value = 50 + random.nextFloat() * 50
                                entriesBar.add(com.github.mikephil.charting.data.BarEntry(i.toFloat(), value))
                                entriesLine.add(com.github.mikephil.charting.data.Entry(i.toFloat(), value))
                            }
                        }

                        "Дні" -> {
                            labels = (1..7).map { "День $it" }
                            labels.forEachIndexed { i, _ ->
                                val value = 200 + random.nextFloat() * 100
                                entriesBar.add(com.github.mikephil.charting.data.BarEntry(i.toFloat(), value))
                                entriesLine.add(com.github.mikephil.charting.data.Entry(i.toFloat(), value))
                            }
                        }

                        "Тижні" -> {
                            labels = (1..4).map { "Тиждень $it" }
                            labels.forEachIndexed { i, _ ->
                                val value = 800 + random.nextFloat() * 200
                                entriesBar.add(com.github.mikephil.charting.data.BarEntry(i.toFloat(), value))
                                entriesLine.add(com.github.mikephil.charting.data.Entry(i.toFloat(), value))
                            }
                        }

                        else -> labels = listOf()
                    }

                    // DataSets
                    val barDataSet =
                        com.github.mikephil.charting.data.BarDataSet(entriesBar, "Споживання (кВт)").apply {
                            color = android.graphics.Color.MAGENTA
                            valueTextColor = android.graphics.Color.BLACK
                            valueTextSize = 10f
                            isHighlightEnabled = true
                        }

                    val lineDataSet =
                        com.github.mikephil.charting.data.LineDataSet(entriesLine, "Прогноз лінійний").apply {
                            color = android.graphics.Color.BLUE
                            lineWidth = 2f
                            setDrawCircles(true)
                            setDrawValues(false)
                            mode = com.github.mikephil.charting.data.LineDataSet.Mode.CUBIC_BEZIER
                        }

                    val combinedData = com.github.mikephil.charting.data.CombinedData()
                    combinedData.setData(com.github.mikephil.charting.data.BarData(barDataSet))
                    combinedData.setData(com.github.mikephil.charting.data.LineData(lineDataSet))
                    chart.data = combinedData

                    // Оновлення осі X
                    chart.xAxis.valueFormatter = object : com.github.mikephil.charting.formatter.ValueFormatter() {
                        override fun getFormattedValue(value: Float): String {
                            val index = value.toInt()
                            return if (index in labels.indices) labels[index] else ""
                        }
                    }

                    // Listener для інтерактивності
                    chart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                        override fun onValueSelected(
                            e: com.github.mikephil.charting.data.Entry?,
                            h: com.github.mikephil.charting.highlight.Highlight?
                        ) {
                            if (e != null) {
                                selectedValue = Pair(labels[e.x.toInt()], e.y)
                            }
                        }

                        override fun onNothingSelected() {
                            selectedValue = null
                        }
                    })

                    chart.invalidate()
                },
                modifier = Modifier.fillMaxSize()
            )

            // Compose підказка поверх графіка
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

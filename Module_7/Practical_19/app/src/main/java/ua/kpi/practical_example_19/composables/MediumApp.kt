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
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Порівняння енергоспоживання по зонах",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // State для відстеження вибраного стовпчика
        var selectedBar by remember { mutableStateOf<Pair<String, Float>?>(null) }

        Box(modifier = Modifier.fillMaxWidth().height(300.dp)) {
            AndroidView(
                factory = { context ->
                    BarChart(context).apply {
                        val random = Random(123)
                        val zones = listOf("Північна", "Південна", "Східна", "Західна", "Центр")
                        val entries = mutableListOf<com.github.mikephil.charting.data.BarEntry>()

                        zones.forEachIndexed { index, _ ->
                            val consumption = 40 + random.nextFloat() * 60
                            entries.add(com.github.mikephil.charting.data.BarEntry(index.toFloat(), consumption))
                        }

                        val dataSet = com.github.mikephil.charting.data.BarDataSet(entries, "Споживання (кВт)").apply {
                            color = android.graphics.Color.MAGENTA
                            valueTextColor = android.graphics.Color.BLACK
                            valueTextSize = 12f
                            isHighlightEnabled = true
                        }

                        xAxis.valueFormatter = object : com.github.mikephil.charting.formatter.ValueFormatter() {
                            override fun getFormattedValue(value: Float): String {
                                val index = value.toInt()
                                return if (index in zones.indices) zones[index] else ""
                            }
                        }
                        xAxis.granularity = 1f
                        xAxis.isGranularityEnabled = true
                        axisRight.isEnabled = false

                        data = com.github.mikephil.charting.data.BarData(dataSet)
                        description.text = "Зони станції"
                        setTouchEnabled(true)
                        setPinchZoom(true)
                        setScaleEnabled(true)
                        animateY(1000)

                        // Listener для вибору стовпчика
                        setOnChartValueSelectedListener(object :
                            com.github.mikephil.charting.listener.OnChartValueSelectedListener {
                            override fun onValueSelected(
                                e: com.github.mikephil.charting.data.Entry?,
                                h: com.github.mikephil.charting.highlight.Highlight?
                            ) {
                                if (e != null) {
                                    selectedBar = Pair(zones[e.x.toInt()], e.y)
                                }
                            }

                            override fun onNothingSelected() {
                                selectedBar = null
                            }
                        })
                    }
                },
                modifier = Modifier.fillMaxSize()
            )

            // Compose підказка поверх графіка
            selectedBar?.let { (zone, consumption) ->
                Card(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(
                        text = "Зона: $zone\nСпоживання: ${"%.1f".format(consumption)} кВт",
                        modifier = Modifier.padding(8.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}


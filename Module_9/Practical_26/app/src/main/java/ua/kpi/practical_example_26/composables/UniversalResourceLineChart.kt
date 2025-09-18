package ua.kpi.practical_example_26.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import android.graphics.Color as AndroidColor

@Composable
fun UniversalResourceLineChart(
    name: String,
    entries: List<Entry>,
    darkTheme: Boolean
) {
    AndroidView(
        factory = { context ->
            LineChart(context).apply {
                description.isEnabled = false
                setTouchEnabled(true)
                setPinchZoom(true)
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                axisRight.isEnabled = false
                legend.isEnabled = true
            }
        },
        update = { chart ->
            // Автоматичне визначення кольору за назвою ресурсу
            val color = when (name.lowercase()) {
                "cpu" -> AndroidColor.RED
                "memory" -> AndroidColor.GREEN
                "battery" -> AndroidColor.BLUE
                "network" -> AndroidColor.MAGENTA
                else -> AndroidColor.GRAY
            }

            val textColor = if (darkTheme) AndroidColor.WHITE else AndroidColor.BLACK

            chart.xAxis.textColor = textColor
            chart.axisLeft.textColor = textColor
            chart.legend.textColor = textColor

            val dataSet = LineDataSet(entries, name).apply {
                setDrawCircles(true)
                setDrawValues(false)
                setDrawFilled(true)
                fillAlpha = 50
                lineWidth = 2f
                this.color = color
            }

            chart.data = LineData(dataSet)
            chart.invalidate()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}
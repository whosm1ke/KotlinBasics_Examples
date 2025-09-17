package ua.kpi.practical_example_22.composables

import android.graphics.Color
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import ua.kpi.practical_example_22.EnergyRecord
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun AdvancedApp() {
    val context = LocalContext.current
    val file = File(context.filesDir, "energy_data.csv")

    // Джерело даних
    var records by remember { mutableStateOf(listOf<EnergyRecord>()) }

    // Фільтри та сортування
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    var sortBy by remember { mutableStateOf("date") }

    // Відфільтровані та відсортовані записи (автоматично оновлюються)
    val filteredRecords by remember(records, startDate, endDate, sortBy) {
        derivedStateOf { applyFilters(records, startDate, endDate, sortBy) }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Аналітика енергоспоживання (графік)", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(8.dp))

        // Фільтри по даті
        OutlinedTextField(
            value = startDate,
            onValueChange = { startDate = it },
            label = { Text("Дата від (yyyy-MM-dd)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = endDate,
            onValueChange = { endDate = it },
            label = { Text("Дата до (yyyy-MM-dd)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Spacer(modifier = Modifier.height(8.dp))

        // Кнопка завантаження CSV
        Button(
            onClick = {
                val loaded = mutableListOf<EnergyRecord>()
                if (file.exists()) {
                    try {
                        val reader = BufferedReader(FileReader(file))
                        var line: String?
                        while (reader.readLine().also { line = it } != null) {
                            val parts = line!!.split(",")
                            if (parts.size == 2) {
                                val d = parts[0]
                                val p = parts[1].toDoubleOrNull() ?: 0.0
                                loaded.add(EnergyRecord(d, p))
                            }
                        }
                        reader.close()
                        records = loaded
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Завантажити CSV")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Кнопка видалення CSV
        Button(
            onClick = {
                if (file.exists()) {
                    file.delete()
                    records = emptyList()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
        ) {
            Text("Видалити CSV-файл")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Графік MPAndroidChart
        AndroidView(
            factory = { ctx ->
                LineChart(ctx).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        600
                    )
                    description.isEnabled = false
                    xAxis.position = XAxis.XAxisPosition.BOTTOM
                    axisRight.isEnabled = false
                    legend.isEnabled = true
                }
            },
            update = { chart ->
                if (filteredRecords.isNotEmpty()) {
                    val entries = filteredRecords.mapIndexed { index, record ->
                        Entry(index.toFloat(), record.power.toFloat())
                    }

                    val dataSet = LineDataSet(entries, "Потужність (кВт)").apply {
                        color = Color.BLUE
                        setCircleColor(Color.RED)
                        lineWidth = 2f
                        circleRadius = 4f
                        valueTextSize = 12f
                    }

                    chart.data = LineData(dataSet)

                    val labels = filteredRecords.map { it.date }
                    chart.xAxis.valueFormatter = IndexAxisValueFormatter(labels)

                    chart.invalidate()
                } else {
                    chart.clear()
                    chart.invalidate()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        )
    }
}

// Функція для фільтрації та сортування
fun applyFilters(
    records: List<EnergyRecord>,
    startDate: String,
    endDate: String,
    sortBy: String
): List<EnergyRecord> {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val filtered = records.filter { rec ->
        val d = try { sdf.parse(rec.date) } catch (_: Exception) { null }
        val start = if (startDate.isNotBlank()) try { sdf.parse(startDate) } catch (_: Exception) { null } else null
        val end = if (endDate.isNotBlank()) try { sdf.parse(endDate) } catch (_: Exception) { null } else null

        if (d != null) {
            val afterStart = start?.let { !d.before(it) } ?: true
            val beforeEnd = end?.let { !d.after(it) } ?: true
            afterStart && beforeEnd
        } else false
    }

    return when (sortBy) {
        "power" -> filtered.sortedBy { it.power }
        else -> filtered.sortedBy { it.date }
    }
}

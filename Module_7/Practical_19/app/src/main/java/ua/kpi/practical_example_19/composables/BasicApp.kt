package ua.kpi.practical_example_19.composables

import android.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlin.random.Random

@Composable
fun BasicApp() {
    // Основний колон для розміщення елементів
    Column(modifier = Modifier.fillMaxSize()) {
        // Відображення заголовка графіка
        Text(
            text = "Прогноз енергоспоживання (лінійний графік)",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp)) // Відступ між заголовком і графіком

        // AndroidView дозволяє вставляти класичні View у Compose
        AndroidView(
            factory = { context ->
                LineChart(context).apply {
                    // Генерація даних для графіка
                    val entries = mutableListOf<Entry>()
                    val hours = 24 // 24 години доби
                    val random = Random(42) // Використовуємо фіксований seed для стабільності результатів

                    for (i in 0 until hours) {
                        // Генеруємо дані споживання (наприклад, 0-100 кВт)
                        val consumption = 50 + random.nextFloat() * 50
                        entries.add(Entry(i.toFloat(), consumption))
                    }

                    // Створення DataSet для LineChart з даними
                    val dataSet = LineDataSet(entries, "Споживання (кВт)").apply {
                        color = Color.BLUE // Колір лінії графіка
                        valueTextColor = Color.BLACK // Колір тексту значень
                        lineWidth = 2f // Товщина лінії
                        setDrawCircles(true) // Відображення точок на графіку
                        setDrawValues(false) // Не відображати значення над точками
                    }

                    // Прив'язка даних до графіка
                    data = LineData(dataSet)

                    // Налаштування графіка
                    description = Description().apply { text = "Години" } // Опис графіка
                    setTouchEnabled(true) // Дозволяє прокручувати та масштабувати графік
                    setPinchZoom(true) // Масштабування пальцями
                    setScaleEnabled(true) // Увімкнення масштабування
                    animateX(1000) // Анімація з'явлення графіка по осі X (1 секунда)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp) // Встановлює висоту графіка
        )
    }
}
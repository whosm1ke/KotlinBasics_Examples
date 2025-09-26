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
    name: String,           // Назва ресурсу (наприклад, "cpu", "memory")
    entries: List<Entry>,    // Список точок даних для графіка
    darkTheme: Boolean       // Прапорець темної теми
) {
    AndroidView(
        factory = { context ->
            // Створення екземпляра LineChart з контекстом
            LineChart(context).apply {
                description.isEnabled = false  // Вимкнення опису графіка
                setTouchEnabled(true)          // Увімкнення взаємодії з графіком (перетягування)
                setPinchZoom(true)             // Дозвіл масштабування пальцями
                xAxis.position = XAxis.XAxisPosition.BOTTOM  // Розміщення осі X внизу
                axisRight.isEnabled = false    // Вимкнення правої осі (використовується лише ліва)
                legend.isEnabled = true        // Увімкнення легенди
            }
        },
        update = { chart ->
            // Визначення кольору лінії залежно від назви ресурсу
            val color = when (name.lowercase()) {
                "cpu" -> AndroidColor.RED      // Червоний колір для CPU
                "memory" -> AndroidColor.GREEN // Зелений колір для пам'яті
                "battery" -> AndroidColor.BLUE // Синій колір для батареї
                "network" -> AndroidColor.MAGENTA // Фіолетовий колір для мережі
                else -> AndroidColor.GRAY      // Сірий колір за замовчуванням
            }

            // Визначення кольору тексту в залежності від теми
            val textColor = if (darkTheme) AndroidColor.WHITE else AndroidColor.BLACK

            // Налаштування кольору тексту для осей та легенди
            chart.xAxis.textColor = textColor
            chart.axisLeft.textColor = textColor
            chart.legend.textColor = textColor

            // Створення набору даних для графіка з точками
            val dataSet = LineDataSet(entries, name).apply {
                setDrawCircles(true)     // Відображення кіл на точках
                setDrawValues(false)     // Не відображати значення на точках
                setDrawFilled(true)      // Заповнення області під лінією
                fillAlpha = 50           // Прозорість заповнення (від 0 до 255)
                lineWidth = 2f           // Товщина лінії
                this.color = color       // Встановлення кольору лінії
            }

            // Призначення даних графіку та оновлення його відображення
            chart.data = LineData(dataSet)
            chart.invalidate()  // Оновлення графіка для відображення змін
        },
        modifier = Modifier
            .fillMaxWidth()   // Заповнює весь доступний простір по ширині
            .height(200.dp)   // Встановлює висоту графіка на 200 dp
    )
}
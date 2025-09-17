package ua.kpi.practical_example_24.composables

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Environment
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.itextpdf.io.image.ImageDataFactory
import com.itextpdf.kernel.font.PdfFontFactory
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Image
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.kernel.font.PdfFont
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.layout.element.AreaBreak
import com.itextpdf.layout.properties.UnitValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun AdvancedApp() {
    val context = LocalContext.current
    var pointsCount by remember { mutableStateOf("10") }
    var maxPower by remember { mutableStateOf("150") }
    var minTemp by remember { mutableStateOf("20") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Просунутий рівень: багатосторінковий PDF з секціями та графіком")

        Spacer(modifier = Modifier.height(8.dp))

        // Інтерактивні параметри
        OutlinedTextField(
            value = pointsCount,
            onValueChange = { pointsCount = it },
            label = { Text("Кількість точок") }
        )

        OutlinedTextField(
            value = maxPower,
            onValueChange = { maxPower = it },
            label = { Text("Макс потужність (кВт)") }
        )

        OutlinedTextField(
            value = minTemp,
            onValueChange = { minTemp = it },
            label = { Text("Мін температура (°C)") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            generateAdvancedPDF(
                context,
                points = pointsCount.toIntOrNull() ?: 10,
                maxPowerVal = maxPower.toIntOrNull() ?: 150,
                minTempVal = minTemp.toIntOrNull() ?: 20
            )
        }) {
            Text("Згенерувати PDF")
        }
    }
}

fun generateAdvancedPDF(
    context: Context,
    points: Int,
    maxPowerVal: Int,
    minTempVal: Int
) {
    try {
        // 1️⃣ Створення файлу
        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
        val file = File(path, "Advanced_SolarPlant_Report.pdf")
        val writer = PdfWriter(file.absolutePath)
        val pdf = PdfDocument(writer)
        val document = Document(pdf)

        // 2️⃣ Шрифт (кирилиця)
        val font: PdfFont = PdfFontFactory.createFont(
            "/system/fonts/Roboto-Regular.ttf",
            "Identity-H"
        )
        document.setFont(font)

        // --- СТОРІНКА 1: Таблиця ---
        document.add(Paragraph("Звіт Сонячної електростанції (Просунутий рівень)").setBold())
        document.add(Paragraph("Основні показники за день"))

        val table = Table(UnitValue.createPercentArray(floatArrayOf(2f, 2f, 2f)))
            .useAllAvailableWidth()
        table.addCell("Час")
        table.addCell("Продуктивність (кВт)")
        table.addCell("Температура (°C)")

        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        val chartEntries = mutableListOf<Entry>()

        for (i in 0 until points) {
            val power = (0..maxPowerVal).random().toFloat()
            val temp = (minTempVal..(minTempVal + 15)).random().toFloat()
            table.addCell(sdf.format(Date()))
            table.addCell(power.toString())
            table.addCell(temp.toString())

            chartEntries.add(Entry(i.toFloat(), power))
        }
        document.add(table)

        // --- СТОРІНКА 2: Графік ---
        document.add(AreaBreak())
        document.add(Paragraph("Графік продуктивності (кВт)"))

        val chart = LineChart(context)
        val dataSet = LineDataSet(chartEntries, "Продуктивність (кВт)")
        dataSet.color = android.graphics.Color.BLUE
        dataSet.valueTextColor = android.graphics.Color.BLACK
        val lineData = LineData(dataSet)
        chart.data = lineData

        chart.layout(0, 0, 800, 600)
        val bitmap = Bitmap.createBitmap(800, 600, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        chart.draw(canvas)

        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val imageData = ImageDataFactory.create(stream.toByteArray())
        val chartImage = Image(imageData)
        document.add(chartImage)

        // --- СТОРІНКА 3: Аналітика ---
        document.add(AreaBreak())
        document.add(Paragraph("Аналітичні висновки"))
        document.add(
            Paragraph(
                """
                ▸ Кількість точок: $points
                ▸ Максимальна потужність: $maxPowerVal кВт
                ▸ Мінімальна температура: $minTempVal °C
                
                Рекомендація: оптимізуйте навантаження у пікові години 
                та контролюйте температуру модулів для підвищення ефективності.
                """.trimIndent()
            )
        )

        // 6️⃣ Закриття документа
        document.close()

    } catch (e: Exception) {
        e.printStackTrace()
    }
}
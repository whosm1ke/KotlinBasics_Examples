package ua.kpi.practical_example_24.composables

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Environment
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
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
import com.itextpdf.layout.properties.UnitValue
import java.io.File
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun MediumApp() {
    val context = LocalContext.current
    Column {
        Text("Середній рівень: PDF з графіками та стилями")

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { generateMediumPDF(context) }) {
            Text("Згенерувати PDF")
        }
    }
}

// Функція для генерації PDF з графіком
fun generateMediumPDF(context: Context) {
    try {
        // 1️⃣ Створення файлу PDF у безпечному сховищі додатку
        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) // безпечніше для Android
        val file = File(path, "Medium_SolarPlant_Report.pdf")
        val writer = PdfWriter(file.absolutePath)
        val pdf = com.itextpdf.kernel.pdf.PdfDocument(writer)
        val document = Document(pdf)

        // 2️⃣ Шрифт для кирилиці
        val font: PdfFont = PdfFontFactory.createFont(
            "/system/fonts/Roboto-Regular.ttf",
            "Identity-H",
        )
        document.setFont(font)

        // 3️⃣ Заголовок
        document.add(Paragraph("Звіт Сонячної електростанції (Середній рівень)").setBold())
        document.add(Paragraph("Таблиця та графік продуктивності за день").setItalic())

        // 4️⃣ Таблиця динамічних даних
        val table = Table(UnitValue.createPercentArray(floatArrayOf(2f, 2f, 2f))).useAllAvailableWidth()
        table.addCell("Час")
        table.addCell("Продуктивність (кВт)")
        table.addCell("Температура (°C)")
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        val chartEntries = mutableListOf<Entry>()
        for (i in 0..5) {
            val power = (50..150).random().toFloat()
            val temp = (20..35).random().toFloat()
            table.addCell("${sdf.format(Date())}")
            table.addCell(power.toString())
            table.addCell(temp.toString())

            // Для графіка записуємо потужність
            chartEntries.add(Entry(i.toFloat(), power))
        }
        document.add(table)

        // 5️⃣ Генерація графіка через MPAndroidChart
        val chart = LineChart(context)
        val dataSet = LineDataSet(chartEntries, "Продуктивність (кВт)")
        dataSet.color = android.graphics.Color.BLUE
        dataSet.valueTextColor = android.graphics.Color.BLACK
        val lineData = LineData(dataSet)
        chart.data = lineData

        // Вимірювання та рендеринг у Bitmap
        chart.layout(0, 0, 800, 600)
        val bitmap = Bitmap.createBitmap(800, 600, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        chart.draw(canvas)

        // Конвертуємо Bitmap у iText Image і додаємо у PDF
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val imageData = ImageDataFactory.create(stream.toByteArray())
        val chartImage = Image(imageData)
        document.add(chartImage)

        // 6️⃣ Закриваємо документ
        document.close()

    } catch (e: Exception) {
        e.printStackTrace()
    }
}
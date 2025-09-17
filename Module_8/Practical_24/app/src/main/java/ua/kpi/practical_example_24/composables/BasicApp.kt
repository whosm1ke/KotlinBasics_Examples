package ua.kpi.practical_example_24.composables

import android.os.Environment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.itextpdf.kernel.font.PdfFont
import com.itextpdf.kernel.font.PdfFontFactory
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import com.itextpdf.layout.properties.UnitValue
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun BasicApp() {
    Column {
        Text("Базовий рівень: створення PDF з таблицею та текстом")

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { generateBasicPDF() }) {
            Text("Згенерувати PDF")
        }
    }
}

// Функція для генерації базового PDF
fun generateBasicPDF() {
    try {
        // Створюємо файл у внутрішньому сховищі
        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) // безпечніше для Android
        val file = File(path, "Basic_SolarPlant_Report.pdf")
        val writer = PdfWriter(file.absolutePath)
        val pdf = com.itextpdf.kernel.pdf.PdfDocument(writer)
        val document = Document(pdf)

        // --- Шрифт для кирилиці ---
        val font: PdfFont = PdfFontFactory.createFont(
            "/system/fonts/Roboto-Regular.ttf", // або будь-який ttf, який є на пристрої
            "Identity-H", // UTF-8 енкодінг для кирилиці
        )
        document.setFont(font)

        // Додаємо заголовок
        document.add(Paragraph("Звіт Сонячної електростанції").setBold())

        // Додаємо текстовий блок
        document.add(Paragraph("Пояснення стану мережі та основних показників за останній день."))

        // Таблиця з базовими даними
        val table = Table(UnitValue.createPercentArray(floatArrayOf(2f, 2f, 2f))).useAllAvailableWidth()
        table.addCell("Час")
        table.addCell("Продуктивність (кВт)")
        table.addCell("Температура (°C)")

        // Приклад заповнення даними
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        for (i in 0..5) {
            table.addCell("${sdf.format(Date())}")
            table.addCell("${(50..150).random()}")
            table.addCell("${(20..35).random()}")
        }

        document.add(table)
        document.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
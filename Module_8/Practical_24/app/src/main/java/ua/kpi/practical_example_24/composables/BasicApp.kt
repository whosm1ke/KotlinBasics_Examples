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
        // Відображення заголовка додатку
        Text("Базовий рівень: створення PDF з таблицею та текстом")

        Spacer(modifier = Modifier.height(8.dp)) // Простір між елементами

        // Кнопка для запуску генерації PDF
        Button(onClick = { generateBasicPDF() }) {
            Text("Згенерувати PDF")
        }
    }
}

// Функція для генерації базового PDF звіту
fun generateBasicPDF() {
    try {
        // Отримуємо шлях до директорії документів на пристрої
        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
        // Створюємо файл для збереження PDF
        val file = File(path, "Basic_SolarPlant_Report.pdf")
        // Ініціалізуємо записувач PDF
        val writer = PdfWriter(file.absolutePath)
        // Створюємо об'єкт PDF документу
        val pdf = com.itextpdf.kernel.pdf.PdfDocument(writer)
        // Ініціалізуємо основний документ для роботи з PDF
        val document = Document(pdf)

        // --- Налаштування шрифту для підтримки кирилиці ---
        // Використовуємо системний шрифт Roboto з кодуванням UTF-8
        val font: PdfFont = PdfFontFactory.createFont(
            "/system/fonts/Roboto-Regular.ttf", // Шлях до шрифту на пристрої
            "Identity-H", // Кодування для кириличних символів
        )
        // Встановлюємо шрифт для всього документу
        document.setFont(font)

        // Додаємо заголовок до PDF документу
        document.add(Paragraph("Звіт Сонячної електростанції").setBold())

        // Додаємо пояснення вмісту звіту
        document.add(Paragraph("Пояснення стану мережі та основних показників за останній день."))

        // Створюємо таблицю з трьома стовпцями
        val table = Table(UnitValue.createPercentArray(floatArrayOf(2f, 2f, 2f))).useAllAvailableWidth()
        // Додаємо заголовки таблиці
        table.addCell("Час")
        table.addCell("Продуктивність (кВт)")
        table.addCell("Температура (°C)")

        // Генеруємо прикладні дані для таблиці
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault()) // Форматування часу
        for (i in 0..5) {
            table.addCell("${sdf.format(Date())}") // Додаємо поточний час
            table.addCell("${(50..150).random()}") // Випадкове значення продуктивності
            table.addCell("${(20..35).random()}") // Випадкове значення температури
        }

        // Додаємо таблицю до документу
        document.add(table)
        // Закриваємо документ і зберігаємо файл
        document.close()
    } catch (e: Exception) {
        // Виводимо помилку у випадку невдачі
        e.printStackTrace()
    }
}
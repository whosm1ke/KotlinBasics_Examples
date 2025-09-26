package ua.kpi.practical_example_22.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.kpi.practical_example_22.EnergyRecord
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter

@Composable
fun BasicApp() {
    // Створення станів для зберігання даних, введених користувачем
    var date by remember { mutableStateOf("") }
    var power by remember { mutableStateOf("") }

    // Стан для зберігання списку записів енергоспоживання
    var records by remember { mutableStateOf(listOf<EnergyRecord>()) }

    // Отримання контексту додатку для доступу до внутрішнього сховища
    val context = androidx.compose.ui.platform.LocalContext.current
    // Створення шляху до файлу у внутрішньому сховищі додатку
    val file = File(context.filesDir, "energy_data.csv")

    Column(modifier = Modifier.padding(16.dp)) {
        // Відображення заголовка
        Text("Введіть дані про енергоспоживання", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(8.dp))

        // Поле для введення дати
        OutlinedTextField(
            value = date,
            onValueChange = { date = it },
            label = { Text("Дата (наприклад: 2025-09-17)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Поле для введення потужності
        OutlinedTextField(
            value = power,
            onValueChange = { power = it },
            label = { Text("Потужність (кВт)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Кнопка для збереження введених даних у CSV-файл
        Button(
            onClick = {
                try {
                    val writer = FileWriter(file, true) // Відкриття файлу у режимі дозапису
                    writer.append("$date,$power\n") // Додавання нового запису у форматі CSV
                    writer.flush() // Запис у файл
                    writer.close() // Закриття файлу
                    date = "" // Очищення поля дати
                    power = "" // Очищення поля потужності
                } catch (e: Exception) {
                    e.printStackTrace() // Виведення помилки у випадку неудачі
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Зберегти у CSV")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Кнопка для завантаження даних з CSV-файлу
        Button(
            onClick = {
                val loaded = mutableListOf<EnergyRecord>() // Створення списку для зчитаних записів
                if (file.exists()) { // Перевірка, чи існує файл
                    try {
                        val reader = BufferedReader(FileReader(file)) // Відкриття читача файлу
                        var line: String?
                        while (reader.readLine().also { line = it } != null) { // Читання рядків поки не закінчиться
                            val parts = line!!.split(",") // Розділення рядка за комою
                            if (parts.size == 2) { // Перевірка, що маємо 2 частини
                                val d = parts[0]
                                val p = parts[1].toDoubleOrNull() ?: 0.0 // Конвертація у double або 0.0 за замовчуванням
                                loaded.add(EnergyRecord(d, p)) // Додавання запису до списку
                            }
                        }
                        reader.close() // Закриття читача файлу
                        records = loaded // Оновлення стану зчитаними даними
                    } catch (e: Exception) {
                        e.printStackTrace() // Виведення помилки у випадку неудачі
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Завантажити з CSV")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 🔥 Нова кнопка для видалення CSV-файлу
        Button(
            onClick = {
                if (file.exists()) {
                    file.delete() // Видалення файлу зі сховища
                    records = emptyList() // Очищення списку у пам’яті
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error) // Зміна кольору кнопки на помилковий
        ) {
            Text("Видалити файл CSV")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Відображення заголовка для списку записів
        Text("Дані з файлу:", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        // Відображення списку записів за допомогою LazyColumn
        LazyColumn {
            items(records) { record ->
                Text("Дата: ${record.date}, Потужність: ${record.power} кВт")
                Divider() // Роздільник між записами
            }
        }
    }
}
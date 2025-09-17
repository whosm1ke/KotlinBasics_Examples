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
    // Стан для введення користувачем
    var date by remember { mutableStateOf("") }
    var power by remember { mutableStateOf("") }

    // Список зчитаних даних
    var records by remember { mutableStateOf(listOf<EnergyRecord>()) }

    // Посилання на файл у внутрішньому сховищі
    val context = androidx.compose.ui.platform.LocalContext.current
    val file = File(context.filesDir, "energy_data.csv")

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Введіть дані про енергоспоживання", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(8.dp))

        // Поле для дати
        OutlinedTextField(
            value = date,
            onValueChange = { date = it },
            label = { Text("Дата (наприклад: 2025-09-17)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Поле для потужності
        OutlinedTextField(
            value = power,
            onValueChange = { power = it },
            label = { Text("Потужність (кВт)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Кнопка для збереження у файл
        Button(
            onClick = {
                try {
                    val writer = FileWriter(file, true) // true = дозапис у файл
                    writer.append("$date,$power\n") // CSV-рядок
                    writer.flush()
                    writer.close()
                    date = ""
                    power = ""
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Зберегти у CSV")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Кнопка для читання з файлу
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
            Text("Завантажити з CSV")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 🔥 Нова кнопка для видалення CSV-файлу
        Button(
            onClick = {
                if (file.exists()) {
                    file.delete() // видаляємо файл
                    records = emptyList() // очищаємо список у пам’яті
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
        ) {
            Text("Видалити файл CSV")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Вивід списку записів
        Text("Дані з файлу:", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(records) { record ->
                Text("Дата: ${record.date}, Потужність: ${record.power} кВт")
                Divider()
            }
        }
    }
}

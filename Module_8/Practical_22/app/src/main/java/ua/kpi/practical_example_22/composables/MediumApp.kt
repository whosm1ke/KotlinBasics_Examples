package ua.kpi.practical_example_22.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ua.kpi.practical_example_22.EnergyRecord
import java.io.File


@Composable
fun MediumApp() {
    // Стан для введення користувачем
    var date by remember { mutableStateOf("") }
    var power by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }

    // Список зчитаних даних
    var records by remember { mutableStateOf(listOf<EnergyRecord>()) }

    // Gson для JSON-операцій
    val gson = Gson()

    // Посилання на файл
    val context = androidx.compose.ui.platform.LocalContext.current
    val file = File(context.filesDir, "energy_data.json")

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Введіть дані про енергоспоживання (JSON)", style = MaterialTheme.typography.titleMedium)

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

        // Поле для статусу
        OutlinedTextField(
            value = status,
            onValueChange = { status = it },
            label = { Text("Статус (Активна / Помилка / Обслуговування)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Кнопка для збереження у JSON
        Button(
            onClick = {
                try {
                    // Якщо файл існує — зчитуємо існуючі дані
                    val currentList: MutableList<EnergyRecord> = if (file.exists()) {
                        val json = file.readText()
                        if (json.isNotEmpty()) {
                            val type = object : TypeToken<List<EnergyRecord>>() {}.type
                            gson.fromJson(json, type)
                        } else mutableListOf()
                    } else mutableListOf()

                    // Додаємо новий запис
                    val newRecord = EnergyRecord(date, power.toDoubleOrNull() ?: 0.0, status)
                    currentList.add(newRecord)
                    date = ""
                    power = ""
                    status = ""

                    // Перезаписуємо файл оновленим списком
                    file.writeText(gson.toJson(currentList))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Зберегти у JSON")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Кнопка для читання з JSON
        Button(
            onClick = {
                if (file.exists()) {
                    try {
                        val json = file.readText()
                        if (json.isNotEmpty()) {
                            val type = object : TypeToken<List<EnergyRecord>>() {}.type
                            records = gson.fromJson(json, type)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Завантажити з JSON")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 🔥 Нова кнопка для видалення файлу
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
            Text("Видалити файл JSON")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Вивід списку записів
        Text("Дані з файлу (JSON):", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(records) { record ->
                Text("Дата: ${record.date}, Потужність: ${record.power} кВт, Статус: ${record.status}")
                Divider()
            }
        }
    }
}
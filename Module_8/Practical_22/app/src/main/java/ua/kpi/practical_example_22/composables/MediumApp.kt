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
    // Стан для зберігання значень полів введення користувача
    var date by remember { mutableStateOf("") }
    var power by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }

    // Стан для зберігання списку записів енергоспоживання
    var records by remember { mutableStateOf(listOf<EnergyRecord>()) }

    // Ініціалізація Gson для роботи з JSON
    val gson = Gson()

    // Отримання контексту застосунку для доступу до файлової системи
    val context = androidx.compose.ui.platform.LocalContext.current
    // Створення шляху до файлу у внутрішній пам’яті застосунку
    val file = File(context.filesDir, "energy_data.json")

    Column(modifier = Modifier.padding(16.dp)) {
        // Заголовок додаткового інтерфейсу
        Text("Введіть дані про енергоспоживання (JSON)", style = MaterialTheme.typography.titleMedium)

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

        // Поле для введення статусу
        OutlinedTextField(
            value = status,
            onValueChange = { status = it },
            label = { Text("Статус (Активна / Помилка / Обслуговування)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Кнопка для збереження даних у JSON файл
        Button(
            onClick = {
                try {
                    // Перевіряємо, чи існує файл; якщо так — зчитуємо поточний список записів
                    val currentList: MutableList<EnergyRecord> = if (file.exists()) {
                        val json = file.readText()
                        if (json.isNotEmpty()) {
                            val type = object : TypeToken<List<EnergyRecord>>() {}.type
                            gson.fromJson(json, type)
                        } else mutableListOf()
                    } else mutableListOf()

                    // Створюємо новий запис на основі введених даних
                    val newRecord = EnergyRecord(date, power.toDoubleOrNull() ?: 0.0, status)
                    currentList.add(newRecord)
                    
                    // Очищуємо поля введення після додавання запису
                    date = ""
                    power = ""
                    status = ""

                    // Записуємо оновлений список у файл JSON
                    file.writeText(gson.toJson(currentList))
                } catch (e: Exception) {
                    e.printStackTrace() // Виводимо помилку у логи
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Зберегти у JSON")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Кнопка для завантаження даних з JSON файлу
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
                        e.printStackTrace() // Виводимо помилку у логи
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Завантажити з JSON")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 🔥 Нова кнопка для видалення файлу JSON
        Button(
            onClick = {
                if (file.exists()) {
                    file.delete() // Видаляємо файл з пристрою
                    records = emptyList() // Очищуємо список у пам’яті
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
        ) {
            Text("Видалити файл JSON")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Відображаємо заголовок для списку записів
        Text("Дані з файлу (JSON):", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        // Виводимо список записів у LazyColumn
        LazyColumn {
            items(records) { record ->
                Text("Дата: ${record.date}, Потужність: ${record.power} кВт, Статус: ${record.status}")
                Divider() // Роздільник між записами
            }
        }
    }
}
package ua.kpi.practical_example_23.composables

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.rememberAsyncImagePainter
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun AdvancedApp() {
    // Отримуємо контекст за допомогою LocalContext
    val context = LocalContext.current

    // Модель файлу: визначає структуру даних для кожного елемента
    data class FileItem(
        val type: String, // "Фото" або "Документ"
        val uri: Uri,
        val addedDate: Long = System.currentTimeMillis(), // Дата додавання файлу
        val name: String // Назва файлу
    )

    // Стан списку файлів: зберігає список елементів у пам'яті Compose
    var files by remember { mutableStateOf(listOf<FileItem>()) }

    // Лаунчер для вибору фото з галереї
    val imageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            // Додаємо новий файл типу "Фото" до списку
            files = files + FileItem("Фото", it, System.currentTimeMillis(), getFileName(context, it))
        }
    }

    // Лаунчер для вибору документів (наприклад, PDF, DOCX)
    val documentLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            // Додаємо новий файл типу "Документ" до списку
            files = files + FileItem("Документ", it, System.currentTimeMillis(), getFileName(context, it))
        }
    }



    // Змінні для редагування назви файлу
    var fileToRename by remember { mutableStateOf<FileItem?>(null) } // Файл, який потрібно перейменувати
    var newFileName by remember { mutableStateOf("") } // Нова назва файлу

    // Опція сортування: "Тип" або "Дата"
    var sortOption by remember { mutableStateOf("Дата") }
    
    // Визначаємо відсортований список файлів
    val sortedFiles = files.let {
        when(sortOption) {
            "Тип" -> it.sortedBy { f -> f.type } // Сортування за типом
            else -> it.sortedByDescending { f -> f.addedDate } // Сортування за датою (від найновішого)
        }
    }

    // Змінні для показу галереї фото
    var galleryIndex by remember { mutableStateOf(0) } // Індекс поточного фото в галереї
    var showGallery by remember { mutableStateOf(false) } // Показувати або приховувати діалог галереї

    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        // Відображаємо заголовок застосунку
        Text("Просунутий рівень: редагування та сортування файлів", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        // Кнопки для додавання фото та документів
        Row {
            Button(onClick = { imageLauncher.launch("image/*") }, modifier = Modifier.padding(end = 8.dp)) { Text("Додати фото") }
            Button(onClick = { documentLauncher.launch("*/*") }) { Text("Додати документ") }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Кнопки для вибору способу сортування
        Row {
            listOf("Дата", "Тип").forEach { option ->
                Button(
                    onClick = { sortOption = option },
                    colors = if (sortOption == option) ButtonDefaults.buttonColors(Color.Cyan) else ButtonDefaults.buttonColors(),
                    modifier = Modifier.padding(end = 4.dp)
                ) { Text(option) }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Відображаємо список файлів у LazyColumn
        LazyColumn {
            itemsIndexed(sortedFiles) { index, file ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .background(Color(0xFFE0E0E0))
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Якщо файл - фото, відображаємо зображення
                    if (file.type == "Фото") {
                        Image(
                            painter = rememberAsyncImagePainter(file.uri),
                            contentDescription = null,
                            modifier = Modifier
                                .size(100.dp)
                                .clickable { galleryIndex = index; showGallery = true }, // Натискання на фото відкриває галерею
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(file.name, modifier = Modifier.weight(1f).clickable { galleryIndex = index; showGallery = true }) // Назва фото
                    } else {
                        // Якщо файл - документ, відкриваємо його через Intent
                        Text(file.name, modifier = Modifier.weight(1f).clickable {
                            val intent = Intent(Intent.ACTION_VIEW).apply {
                                setDataAndType(file.uri, context.contentResolver.getType(file.uri))
                                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_READ_URI_PERMISSION
                            }
                            context.startActivity(intent)
                        })
                    }

                    // Кнопка редагування назви файлу
                    Button(onClick = {
                        fileToRename = file
                        newFileName = file.name
                    }, modifier = Modifier.padding(start = 8.dp)) { Text("Редагувати") }
                }
            }
        }
    }

    // Діалог галереї для перегляду фото
    if (showGallery) {
        Dialog(onDismissRequest = { showGallery = false }) {
            Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
                val imageFiles = sortedFiles.filter { it.type == "Фото" } // Відбираємо лише фото
                imageFiles.getOrNull(galleryIndex)?.let { file ->
                    Image(
                        painter = rememberAsyncImagePainter(file.uri),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable { showGallery = false }, // Натискання на фото закриває галерею
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }
    }

    // Діалог редагування назви файлу
    if (fileToRename != null) {
        AlertDialog(
            onDismissRequest = { fileToRename = null },
            title = { Text("Редагувати назву") },
            text = { TextField(value = newFileName, onValueChange = { newFileName = it }, singleLine = true) }, // Поле для вводу нової назви
            confirmButton = {
                Button(onClick = {
                    // Оновлюємо список файлів з новою назвою
                    files = files.map { if (it.uri == fileToRename!!.uri) it.copy(name = newFileName) else it }.toMutableList()
                    fileToRename = null
                }) { Text("Зберегти") }
            },
            dismissButton = { Button(onClick = { fileToRename = null }) { Text("Скасувати") } }
        )
    }
}

// Допоміжна функція для отримання імені файлу з URI
fun getFileName(context: Context, uri: Uri): String {
    var name: String? = null
    val cursor = context.contentResolver.query(uri, arrayOf("_display_name"), null, null, null) // Запит до контент-провайдера
    cursor?.use {
        if (it.moveToFirst()) {
            val index = it.getColumnIndex("_display_name")
            if (index != -1) name = it.getString(index)
        }
    }
    return name ?: uri.lastPathSegment ?: "Файл" // Повертаємо ім'я або стандартне значення
}
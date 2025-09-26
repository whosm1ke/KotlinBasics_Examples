package ua.kpi.practical_example_23.composables

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun BasicApp() {
    // Отримуємо поточний контекст застосунку для доступу до системних функцій
    val context = LocalContext.current

    // Створюємо змінну стану для зберігання URI вибраних фотографій
    var imageUris by remember { mutableStateOf(listOf<Uri>()) }

    // Створюємо лаунчер для отримання результатів вибору зображення з пам'яті
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            // Додаємо вибране фото у список, якщо воно існує
            imageUris = imageUris + it
        }
    }

    Column {
        // Відображаємо заголовок застосунку
        Text("Базовий рівень: завантаження та перегляд фотографій", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(8.dp)) // Відступ між елементами

        // Кнопка для виклику меню вибору зображення
        Button(onClick = { launcher.launch("image/*") }) {
            Text("Додати фото")
        }

        Spacer(modifier = Modifier.height(16.dp)) // Відступ після кнопки

        // Створюємо вертикальний список для відображення зображень
        LazyColumn {
            items(imageUris) { uri ->
                // Відображаємо кожне зображення з використанням Coil для завантаження
                Image(
                    painter = rememberAsyncImagePainter(uri), // Створюємо пейнтер для завантаження зображення
                    contentDescription = null, // Відсутність опису для доступності
                    modifier = Modifier
                        .fillMaxWidth() // Заповнюємо ширину контейнера
                        .height(200.dp) // Фіксована висота зображення
                        .padding(4.dp), // Внутрішній відступ
                    contentScale = ContentScale.Fit // Зображення масштабується, щоб поміститись у контейнер
                )
            }
        }
    }
}
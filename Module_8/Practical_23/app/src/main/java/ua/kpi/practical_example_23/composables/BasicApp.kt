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
    val context = LocalContext.current

    // Список URI фотографій для відображення
    var imageUris by remember { mutableStateOf(listOf<Uri>()) }

    // Лаунчер для вибору зображення з пам'яті
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            // Додаємо вибране фото у список
            imageUris = imageUris + it
        }
    }

    Column {
        Text("Базовий рівень: завантаження та перегляд фотографій", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(8.dp))

        // Кнопка для вибору фото
        Button(onClick = { launcher.launch("image/*") }) {
            Text("Додати фото")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Відображення фотографій у списку
        LazyColumn {
            items(imageUris) { uri ->
                Image(
                    painter = rememberAsyncImagePainter(uri),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp) // можна залишити фіксовану висоту
                        .padding(4.dp),
                    contentScale = ContentScale.Fit // зображення поміститься цілком
                )
            }
        }

    }
}

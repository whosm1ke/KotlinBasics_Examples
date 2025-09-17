package ua.kpi.practical_example_23.composables

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
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
fun MediumApp() {
    val context = LocalContext.current

    var imageUris by remember { mutableStateOf(listOf<Uri>()) }
    var documentUris by remember { mutableStateOf(listOf<Uri>()) }

    val imageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { imageUris = imageUris + it }
    }

    val documentLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { documentUris = documentUris + it }
    }

    var filter by remember { mutableStateOf("Всі") }
    val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())

    var selectedPhoto by remember { mutableStateOf<Uri?>(null) }


    Column {
        Text("Середній рівень: фото та документи з фільтрацією", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Button(onClick = { imageLauncher.launch("image/*") }, modifier = Modifier.padding(end = 8.dp)) {
                Text("Додати фото")
            }
            Button(onClick = { documentLauncher.launch("*/*") }) {
                Text("Додати документ")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            listOf("Всі", "Фото", "Документи").forEach { type ->
                Button(
                    onClick = { filter = type },
                    modifier = Modifier.padding(end = 4.dp),
                    colors = if (filter == type) ButtonDefaults.buttonColors(Color.Cyan)
                    else ButtonDefaults.buttonColors()
                ) {
                    Text(type)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        val items = mutableListOf<Pair<String, Uri>>()
        imageUris.forEach { items.add("Фото" to it) }
        documentUris.forEach { items.add("Документи" to it) }

        val filteredItems = items.filter { filter == "Всі" || it.first == filter }

        LazyColumn {
            items(filteredItems) { pair ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .background(Color.LightGray)
                        .clickable {
                            if (pair.first == "Документи") {
                                // Додайте спробу відкрити вміст документу
                            } else {
                                // Відкриваємо фото у галереї (діалог)
                                selectedPhoto = pair.second
                            }
                        }
                        .padding(8.dp)
                ) {
                    Text(pair.first, modifier = Modifier.weight(1f))
                    Text(dateFormat.format(Date()))
                }
            }
        }
    }

    // Діалог для перегляду фото
    if (selectedPhoto != null) {
        Dialog(onDismissRequest = { selectedPhoto = null }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color.Black)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(selectedPhoto),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { selectedPhoto = null },
                    contentScale = ContentScale.Fit
                )
            }
        }
    }

}

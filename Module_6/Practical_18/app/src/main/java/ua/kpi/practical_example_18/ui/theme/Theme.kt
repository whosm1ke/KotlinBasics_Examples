package ua.kpi.practical_example_18.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

// Означення темної кольорової схеми з використанням темних кольорів
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,        // Основний колір для темної теми
    secondary = PurpleGrey80,  // Другорядний колір для темної теми
    tertiary = Pink80          // Третинний колір для темної теми
)

// Означення світлої кольорової схеми з використанням світлих кольорів
private val LightColorScheme = lightColorScheme(
    primary = Purple40,        // Основний колір для світлої теми
    secondary = PurpleGrey40,  // Другорядний колір для світлої теми
    tertiary = Pink40          // Третинний колір для світлої теми

    /* Інші стандартні кольори для перевизначення
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun Practical_Example_18Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),  // Параметр, що визначає темну тему; за замовчуванням - система
    // Динамічна колірна схема доступна на Android 12+
    dynamicColor: Boolean = true,               // Вмикає динамічні кольори для підтримки системних налаштувань
    content: @Composable () -> Unit                   // Композабельний вміст теми
) {
    val colorScheme = when {                      // Визначення кольорової схеми залежно від умов
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {  // Якщо підтримується динамічна колірна схема
            val context = LocalContext.current   // Отримання контексту активності
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)  // Вибір темної або світлої динамічної схеми
        }

        darkTheme -> DarkColorScheme   // Якщо темна тема, використовуємо темну кольорову схему
        else -> LightColorScheme       // Інакше - світлу
    }

    MaterialTheme(                               // Встановлення теми Material3 з вказаними параметрами
        colorScheme = colorScheme,                // Кольорова схема
        typography = Typography,                  // Типографія
        content = content                         // Композабельний вміст
    )
}
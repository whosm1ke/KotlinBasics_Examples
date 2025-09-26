package ua.kpi.practical_example_14.ui.theme

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
fun Practical_Example_14Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),  // Прапорець темної теми, за замовчуванням визначається системою
    // Динамічна колірна схема доступна на Android 12+
    dynamicColor: Boolean = true,                // Прапорець для використання динамічної кольорової схеми
    content: @Composable () -> Unit                   // Вміст теми, який буде відображений з використанням цієї теми
) {
    val colorScheme = when {  // Вибір кольорової схеми залежно від умов
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {  // Якщо динамічна тема доступна і версія Android >= 12
            val context = LocalContext.current  // Отримання поточного контексту
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)  // Вибір темної або світлої динамічної кольорової схеми
        }

        darkTheme -> DarkColorScheme   // Якщо темна тема активована, використовується темна кольорова схема
        else -> LightColorScheme       // Інакше використовується світла кольорова схема
    }

    MaterialTheme(  // Встановлення теми Material3 з використанням обраної кольорової схеми
        colorScheme = colorScheme,   // Кольорова схема для теми
        typography = Typography,     // Типографія для теми
        content = content            // Вміст теми
    )
}
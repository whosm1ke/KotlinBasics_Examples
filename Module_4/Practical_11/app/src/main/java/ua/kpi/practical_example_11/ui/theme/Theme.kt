package ua.kpi.practical_example_11.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

// Визначення темної схеми кольорів з використанням darkColorScheme
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,        // Основний колір для темної теми
    secondary = PurpleGrey80,  // Додатковий колір для темної теми
    tertiary = Pink80          // Третинний колір для темної теми
)

// Визначення світлої схеми кольорів з використанням lightColorScheme
private val LightColorScheme = lightColorScheme(
    primary = Purple40,        // Основний колір для світлої теми
    secondary = PurpleGrey40,  // Додатковий колір для світлої теми
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
fun Practical_Example_11Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),  // Параметр, що визначає темну тему (за замовчуванням - системна тема)
    // Динамічні кольори доступні на Android 12+
    dynamicColor: Boolean = true,                // Параметр для використання динамічної кольорової схеми
    content: @Composable () -> Unit                  // Функція, що містить вміст теми
) {
    // Визначення кольорової схеми залежно від параметрів теми та версії Android
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            // Якщо підтримується динамічна колірна схема і версія Android 12+, 
            // використовуємо відповідну динамічну тему
            val context = LocalContext.current  // Отримання поточного контексту
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme   // Якщо темна тема включена, використовуємо темну схему кольорів
        else -> LightColorScheme       // В іншому випадку - світлу схему кольорів
    }

    // Застосування MaterialTheme з визначеною кольоровою схемою та типографією
    MaterialTheme(
        colorScheme = colorScheme,  // Кольорова схема для теми
        typography = Typography,    // Типографія за замовчуванням
        content = content           // Вміст теми
    )
}
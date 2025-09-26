package ua.kpi.practical_example_26.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

// Означення темної кольорової схеми з використанням darkColorScheme
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,        // Основний колір для темної теми
    secondary = PurpleGrey80,  // Допоміжний колір для темної теми
    tertiary = Pink80          // Третинний колір для темної теми
)

// Означення світлої кольорової схеми з використанням lightColorScheme
private val LightColorScheme = lightColorScheme(
    primary = Purple40,        // Основний колір для світлої теми
    secondary = PurpleGrey40,  // Допоміжний колір для світлої теми
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
fun Practical_Example_26Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),  // Параметр для визначення темної теми, за замовчуванням — системна тема
    // Динамічні кольори доступні на Android 12+
    dynamicColor: Boolean = true,                // Флаг для використання динамічних кольорів
    content: @Composable () -> Unit                 // Вміст теми, який буде відображений у компоненті
) {
    // Визначення кольорової схеми залежно від параметрів теми та версії Android
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {  // Якщо увімкнено динамічні кольори і версія Android 12+
            val context = LocalContext.current  // Отримання контексту поточного активіті
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)  // Вибір темної або світлої динамічної кольорової схеми
        }

        darkTheme -> DarkColorScheme  // Якщо увімкнено темну тему — використовуємо темну схему
        else -> LightColorScheme     // Інакше — використовуємо світлу схему
    }

    // Встановлення MaterialTheme з обраною кольоровою схемою та типографією
    MaterialTheme(
        colorScheme = colorScheme,  // Кольорова схема для теми
        typography = Typography,     // Типографія для теми
        content = content            // Вміст теми
    )
}
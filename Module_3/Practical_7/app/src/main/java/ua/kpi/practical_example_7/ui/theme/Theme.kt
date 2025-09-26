package ua.kpi.practical_example_7.ui.theme

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

// Визначення темної кольорової схеми з використанням darkColorScheme
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,        // Основний колір для темної теми
    secondary = PurpleGrey80,  // Допоміжний колір для темної теми
    tertiary = Pink80           // Третинний колір для темної теми
)

// Визначення світлої кольорової схеми з використанням lightColorScheme
private val LightColorScheme = lightColorScheme(
    primary = Purple40,        // Основний колір для світлої теми
    secondary = PurpleGrey40,  // Допоміжний колір для світлої теми
    tertiary = Pink40           // Третинний колір для світлої теми

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
fun Practical_Example_7Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),  // Визначення теми (за замовчуванням - система)
    // Динамічна колірна схема доступна на Android 12+
    dynamicColor: Boolean = true,                // Чи використовувати динамічну кольорову схему
    content: @Composable () -> Unit                 // Вміст теми
) {
    // Вибір кольорової схеми залежно від умов
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            // Якщо динамічна колірна схема активна і версія Android ≥ 12
            val context = LocalContext.current  // Отримання контексту
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            // Вибір темної або світлої динамічної схеми в залежності від теми
        }

        darkTheme -> DarkColorScheme  // Якщо темна тема - використовуємо темну схему
        else -> LightColorScheme      // Інакше - світлу схему
    }

    // Встановлення MaterialTheme з обраною кольоровою схемою, типографією та вмістом
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
package ua.kpi.practical_example_4.ui.theme

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

// Определяем цветовую схему для темной темы
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,        // Основной цвет для темной темы
    secondary = PurpleGrey80,  // Вторичный цвет для темной темы
    tertiary = Pink80           // Третичный цвет для темной темы
)

// Определяем цветовую схему для светлой темы
private val LightColorScheme = lightColorScheme(
    primary = Purple40,        // Основной цвет для светлой темы
    secondary = PurpleGrey40,  // Вторичный цвет для светлой темы
    tertiary = Pink40           // Третичный цвет для светлой темы

    /* Другие стандартные цвета для переопределения
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
fun Practical_Example_4Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),  // Флаг темной темы, по умолчанию определяется системой
    // Динамическая цветовая схема доступна только на Android 12+
    dynamicColor: Boolean = true,                // Флаг включения динамической цветовой схемы
    content: @Composable () -> Unit                 // Композиция контента темы
) {
    val colorScheme = when {                    // Определяем текущую цветовую схему в зависимости от условий
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {  // Если включена динамическая цветовая схема и версия Android >= 12
            val context = LocalContext.current   // Получаем текущий контекст
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)  // Возвращаем соответствующую динамическую цветовую схему
        }

        darkTheme -> DarkColorScheme         // Если темная тема включена, используем темную цветовую схему
        else -> LightColorScheme             // Иначе используем светлую цветовую схему
    }

    MaterialTheme(                           // Применяем тему Material Design
        colorScheme = colorScheme,           // Цветовая схема
        typography = Typography,             // Типографика
        content = content                    // Контент темы
    )
}
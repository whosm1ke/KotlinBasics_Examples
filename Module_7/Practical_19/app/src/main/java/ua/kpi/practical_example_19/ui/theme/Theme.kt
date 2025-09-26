package ua.kpi.practical_example_19.ui.theme

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

// Определяем темную цветовую схему с использованием Purple80, PurpleGrey80 и Pink80
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

// Определяем светлую цветовую схему с использованием Purple40, PurpleGrey40 и Pink40
private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Другие цвета по умолчанию для переопределения
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
fun Practical_Example_19Theme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Определяет, используется ли темная тема; по умолчанию проверяет системную настройку
    // Динамическая цветовая схема доступна начиная с Android 12
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit // Содержимое, которое будет отображаться в теме
) {
    // Определяем цветовую схему в зависимости от параметров
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            // Получаем контекст активности для использования динамической цветовой схемы
            val context = LocalContext.current
            // Если темная тема включена, используем динамическую темную цветовую схему
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme // Используем темную цветовую схему при активной темной теме
        else -> LightColorScheme // В противном случае используем светлую цветовую схему
    }

    // Применяем выбранную цветовую схему в MaterialTheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // Используем типографику по умолчанию
        content = content // Передаем содержимое темы
    )
}
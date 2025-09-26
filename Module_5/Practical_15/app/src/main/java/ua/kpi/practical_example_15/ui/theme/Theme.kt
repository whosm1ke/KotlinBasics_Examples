package ua.kpi.practical_example_15.ui.theme

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

// Оголошення темної схеми кольорів з використанням darkColorScheme
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,        // Основний колір для темної теми
    secondary = PurpleGrey80,  // Другорядний колір для темної теми
    tertiary = Pink80          // Третинний колір для темної теми
)

// Оголошення світлої схеми кольорів з використанням lightColorScheme
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
fun Practical_Example_15Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),  // Параметр, що визначає темну тему (за замовчуванням - система)
    // Динамічні кольори доступні починаючи з Android 12
    dynamicColor: Boolean = true,                // Вмикає динамічні кольори при використанні Android 12+
    content: @Composable () -> Unit                  // Контент для рендерингу у межах теми
) {
    // Визначення схеми кольорів залежно від налаштувань користувача та версії Android
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            // Якщо увімкнено динамічні кольори і пристрій підтримує Android 12+
            val context = LocalContext.current  // Отримання контексту активності
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            // Вибір темної або світлої динамічної схеми кольорів
        }

        darkTheme -> DarkColorScheme  // Якщо увімкнено темну тему, використовуємо темну схему
        else -> LightColorScheme     // В іншому випадку — світлу схему
    }

    // Застосування MaterialTheme з вибраною схемою кольорів та типографією
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,     // Використання типографії з теми
        content = content             // Передача контенту для відображення
    )
}
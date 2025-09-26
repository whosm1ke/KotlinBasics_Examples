package ua.kpi.practical_example_27.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

// Визначення темної схеми кольорів з використанням darkColorScheme
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,         // Основний колір для темної теми
    secondary = PurpleGrey80,   // Додатковий колір для темної теми
    tertiary = Pink80           // Третинний колір для темної теми
)

// Визначення світлої схеми кольорів з використанням lightColorScheme
private val LightColorScheme = lightColorScheme(
    primary = Purple40,         // Основний колір для світлої теми
    secondary = PurpleGrey40,   // Додатковий колір для світлої теми
    tertiary = Pink40           // Третинний колір для світлої теми

    /* Інші стандартні кольори, які можна перевизначити
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
fun Practical_Example_27Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),  // Параметр, що визначає темну тему (за замовчуванням — системна тема)
    // Динамічні кольори доступні на Android 12+
    dynamicColor: Boolean = true,               // Прапорець для використання динамічних кольорів
    content: @Composable () -> Unit                // Вміст теми, який буде відображатися з цією темою
) {
    // Вибір схеми кольорів залежно від параметрів теми та версії Android
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            // Якщо динамічні кольори увімкнені і версія Android 12 або вище
            val context = LocalContext.current  // Отримання поточного контексту
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            // Вибір темної або світлої динамічної схеми кольорів
        }

        darkTheme -> DarkColorScheme  // Якщо темна тема увімкнена, використовуємо темну схему
        else -> LightColorScheme      // В іншому випадку — світлу схему
    }

    // Застосування MaterialTheme з обраною схемою кольорів, типографією та контентом
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
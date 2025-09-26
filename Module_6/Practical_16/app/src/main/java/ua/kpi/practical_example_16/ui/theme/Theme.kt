package ua.kpi.practical_example_16.ui.theme

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

// Визначення темної схеми кольорів з використанням стандартних значень
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

// Визначення світлої схеми кольорів з використанням стандартних значень
private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

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
fun Practical_Example_16Theme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Визначає, чи використовується темна тема; за замовчуванням — системна тема
    // Динамічні кольори доступні на Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit // Вміст, який буде відображатися в рамках цієї теми
) {
    // Вибір схеми кольорів залежно від умов: динамічна тема на Android 12+, темна або світла тема
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            // Отримання контексту активності для використання динамічних кольорів
            val context = LocalContext.current
            // Вибір темної або світлої динамічної схеми кольорів залежно від теми
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme // Якщо активна темна тема — використовуємо темну схему
        else -> LightColorScheme // В іншому випадку — світлу схему
    }

    // Застосування обраної схеми кольорів, типографії та вмісту до MaterialTheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
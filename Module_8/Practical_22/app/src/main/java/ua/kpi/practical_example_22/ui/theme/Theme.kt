package ua.kpi.practical_example_22.ui.theme

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

// Визначення темної схеми кольорів з використанням Purple80, PurpleGrey80 та Pink80
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

// Визначення світлої схеми кольорів з використанням Purple40, PurpleGrey40 та Pink40
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
fun Practical_Example_22Theme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Визначає, чи використовувати темну тему (за замовчуванням - згідно з системним налаштуванням)
    // Динамічні кольори доступні починаючи з Android 12
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit // Вміст, що буде відображатись у рамках теми
) {
    // Вибір схеми кольорів залежно від налаштувань теми та версії Android
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            // Отримання контексту активності для динамічних кольорів
            val context = LocalContext.current
            // Вибір темної або світлої динамічної схеми залежно від теми
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme // Якщо темна тема - використовуємо темну схему
        else -> LightColorScheme // В іншому випадку - світлу
    }

    // Застосування обраної схеми кольорів, типографії та контенту до MaterialTheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
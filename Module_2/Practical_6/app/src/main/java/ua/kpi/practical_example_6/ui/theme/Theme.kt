package ua.kpi.practical_example_6.ui.theme

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

// Визначення темної колірної схеми з використанням Purple80, PurpleGrey80 та Pink80
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

// Визначення світлої колірної схеми з використанням Purple40, PurpleGrey40 та Pink40
// За замовчуванням також включаються деякі інші кольори, які можна перевизначити
private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
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
fun Practical_Example_6Theme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Визначає, чи використовувати темну тему; за замовчуванням - згідно з системним налаштуванням
    // Динамічна колірна схема доступна починаючи з Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit // Вміст теми, який буде відображено
) {
    // Визначення кольорової схеми залежно від налаштувань користувача та версії Android
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            // Отримання контексту активності для використання динамічної кольорової схеми
            val context = LocalContext.current
            // Вибір темної або світлої динамічної кольорової схеми залежно від теми
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme // Якщо увімкнено темну тему, використовується темна схема
        else -> LightColorScheme // В іншому випадку - світла схема
    }

    // Встановлення MaterialTheme з обраною кольоровою схемою, типографією та вмістом
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
package ua.kpi.practical_example_23.ui.theme

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

// Означення темної кольорової схеми з використанням darkColorScheme
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,        // Основний колір для темної теми
    secondary = PurpleGrey80,  // Додатковий колір для темної теми
    tertiary = Pink80          // Третинний колір для темної теми
)

// Означення світлої кольорової схеми з використанням lightColorScheme
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
fun Practical_Example_23Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),  // Визначає, чи використовується темна тема; за замовчуванням — системна тема
    dynamicColor: Boolean = true,                // Вмикає динамічні кольори для Android 12+
    content: @Composable () -> Unit                 // Композабельний блок вмісту для теми
) {
    // Вибір кольорової схеми залежно від параметрів теми та версії Android
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            // Якщо підтримується динамічна кольорова схема і версія Android 12+
            val context = LocalContext.current  // Отримання поточного контексту
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            // Вибір темної або світлої динамічної кольорової схеми
        }

        darkTheme -> DarkColorScheme  // Якщо увімкнено темну тему — використовуємо темну схему
        else -> LightColorScheme     // В іншому випадку — світлу схему
    }

    // Застосування кольорової схеми, типографії та вмісту до MaterialTheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
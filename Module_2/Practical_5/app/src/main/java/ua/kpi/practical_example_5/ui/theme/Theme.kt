package ua.kpi.practical_example_5.ui.theme

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

// Визначення темної кольорової схеми з використанням функції darkColorScheme
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,        // Основний колір для темної теми
    secondary = PurpleGrey80,  // Допоміжний колір для темної теми
    tertiary = Pink80          // Третинний колір для темної теми
)

// Визначення світлої кольорової схеми з використанням функції lightColorScheme
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
fun Practical_Example_5Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),  // Прапорець темної теми, за замовчуванням визначається системою
    // Динамічні кольори доступні починаючи з Android 12+
    dynamicColor: Boolean = true,                // Прапорець для використання динамічних кольорів
    content: @Composable () -> Unit                 // Вміст теми, який буде відображатися з цією темою
) {
    // Визначення кольорової схеми в залежності від параметрів теми та версії Android
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            // Якщо динамічні кольори увімкнено і пристрій підтримує Android 12+
            val context = LocalContext.current  // Отримання контексту активності
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            // Вибір темної або світлої динамічної кольорової схеми в залежності від теми
        }

        darkTheme -> DarkColorScheme  // Якщо увімкнено темну тему, використовується темна схема
        else -> LightColorScheme      // Інакше — використовується світла схема
    }

    // Встановлення MaterialTheme з вибраною кольоровою схемою та типографією
    MaterialTheme(
        colorScheme = colorScheme,   // Кольорова схема для теми
        typography = Typography,     // Типографія для теми
        content = content             // Вміст, що відображається з цією темою
    )
}
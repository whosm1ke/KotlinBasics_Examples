package ua.kpi.practical_example_12.ui.theme

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

// Означення темної кольорової схеми з використанням темних кольорів
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,        // Основний колір для темної теми
    secondary = PurpleGrey80,  // Допоміжний колір для темної теми
    tertiary = Pink80          // Третинний колір для темної теми
)

// Означення світлої кольорової схеми з використанням світлих кольорів
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
fun Practical_Example_12Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),  // Визначає, чи використовується темна тема (за замовчуванням — системна)
    // Динамічні кольори доступні починаючи з Android 12
    dynamicColor: Boolean = true,                // Вмикає або вимикає динамічну кольорову схему
    content: @Composable () -> Unit                 // Функція, що містить компоненти теми
) {
    // Визначення кольорової схеми залежно від налаштувань користувача та версії Android
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {  // Якщо підтримується динамічна колірна схема і версія Android ≥ 12
            val context = LocalContext.current  // Отримання контексту активності
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)  // Вибір темної або світлої динамічної кольорової схеми
        }

        darkTheme -> DarkColorScheme   // Якщо включена темна тема, використовуємо темну схему
        else -> LightColorScheme       // В іншому випадку — світлу
    }

    // Застосування кольорової схеми до MaterialTheme
    MaterialTheme(
        colorScheme = colorScheme,   // Кольорова схема для теми
        typography = Typography,     // Типографія з визначеним стилем
        content = content            // Компоненти, які будуть використовувати цю тему
    )
}
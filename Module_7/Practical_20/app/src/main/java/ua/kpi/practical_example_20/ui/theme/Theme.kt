package ua.kpi.practical_example_20.ui.theme

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

// Опис темної кольорової схеми з використанням Purple80, PurpleGrey80 та Pink80
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

// Опис світлої кольорової схеми з використанням Purple40, PurpleGrey40 та Pink40
// Коментарі з іншими стандартними кольорами для перевизначення (закоментовано)
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
fun Practical_Example_20Theme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Визначає, чи використовується темна тема (за замовчуванням - системна)
    // Якщо true, то використовується динамічна кольорова схема для Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit // Композиція контенту, який буде оточений темою
) {
    // Визначення кольорової схеми в залежності від налаштувань користувача та версії Android
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            // Отримання контексту активності для динамічної кольорової схеми
            val context = LocalContext.current
            // Вибір темної або світлої динамічної кольорової схеми залежно від налаштувань темної теми
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme // Якщо темна тема - використовуємо темну схему
        else -> LightColorScheme // В іншому випадку - світлу
    }

    // Застосування кольорової схеми, типографії та контенту до MaterialTheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
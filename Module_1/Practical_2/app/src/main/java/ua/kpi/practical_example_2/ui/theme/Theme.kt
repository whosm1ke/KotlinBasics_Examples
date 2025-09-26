package ua.kpi.practical_example_2.ui.theme

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

// Опреділяє темну колірну схему з використанням Purple80, PurpleGrey80 та Pink80
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

// Опреділяє світлу колірну схему з використанням Purple40, PurpleGrey40 та Pink40
// За замовчуванням також визначено інші кольори, які можна перевизначити
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
fun Practical_Example_2Theme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Визначає, чи використовувати темну тему; за замовчуванням - згідно з системним налаштуванням
    // Dynamic color доступна на Android 12+
    dynamicColor: Boolean = true, // Визначає, чи використовувати динамічні кольори (якщо підтримуються)
    content: @Composable () -> Unit // Композабельна функція для вмісту теми
) {
    // Вибирає колірну схему залежно від налаштувань теми та версії Android
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            // Отримуємо контекст активності для динамічних кольорів
            val context = LocalContext.current
            // Вибираємо темну або світлу динамічну колірну схему в залежності від теми
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme // Якщо темна тема - використовуємо темну схему
        else -> LightColorScheme // Інакше - світлу
    }

    // Встановлює MaterialTheme з обраною колірною схемою, типографією та вмістом
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
package ua.kpi.practical_example_3.ui.theme

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

// Опис темної кольорової схеми з використанням функції darkColorScheme
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,        // Основний колір для темної теми
    secondary = PurpleGrey80,  // Додатковий колір для темної теми
    tertiary = Pink80          // Третинний колір для темної теми
)

// Опис світлої кольорової схеми з використанням функції lightColorScheme
private val LightColorScheme = lightColorScheme(
    primary = Purple40,        // Основний колір для світлої теми
    secondary = PurpleGrey40,  // Додатковий колір для світлої теми
    tertiary = Pink40          // Третинний колір для світлої теми

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
fun Practical_Example_3Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),  // Визначає, чи використовується темна тема; за замовчуванням - системна тема
    // Динамічні кольори доступні починаючи з Android 12
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit  // Композиція вмісту, який буде відображатися у рамках теми
) {
    // Визначення кольорової схеми залежно від параметрів теми та версії Android
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            // Отримання контексту активності для динамічного кольору
            val context = LocalContext.current
            // Вибір темної або світлої динамічної схеми залежно від параметра darkTheme
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme  // Якщо темна тема - використовуємо темну схему
        else -> LightColorScheme     // В іншому випадку - світлу
    }

    // Застосування кольорової схеми до MaterialTheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,   // Типографія з файлу Typography.kt
        content = content           // Вміст, який буде відображатись у цій темі
    )
}
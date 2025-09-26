package ua.kpi.practical_example_9.ui.theme

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
    primary = Purple80,         // Основний колір для темної теми
    secondary = PurpleGrey80,   // Допоміжний колір для темної теми
    tertiary = Pink80           // Третинний колір для темної теми
)

// Опис світлої кольорової схеми з використанням функції lightColorScheme
private val LightColorScheme = lightColorScheme(
    primary = Purple40,         // Основний колір для світлої теми
    secondary = PurpleGrey40,   // Допоміжний колір для світлої теми
    tertiary = Pink40           // Третинний колір для світлої теми

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
fun Practical_Example_9Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),  // Визначає, чи використовувати темну тему (за замовчуванням - згідно з системним налаштуванням)
    // Динамічна колірна схема доступна починаючи з Android 12
    dynamicColor: Boolean = true,                // Визначає, чи використовувати динамічну кольорову схему
    content: @Composable () -> Unit                 // Контент, який буде відображатись у межах теми
) {
    // Визначення кольорової схеми залежно від параметрів теми та версії Android
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {  // Якщо динамічна кольорова схема увімкнена і пристрій підтримує Android 12+
            val context = LocalContext.current  // Отримання поточного контексту
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)  // Вибір темної або світлої динамічної схеми
        }

        darkTheme -> DarkColorScheme  // Якщо увімкнено темну тему, використовуємо темну схему
        else -> LightColorScheme     // В іншому випадку - світлу схему
    }

    // Встановлення теми MaterialTheme з використанням обраної кольорової схеми та типографії
    MaterialTheme(
        colorScheme = colorScheme,   // Кольорова схема
        typography = Typography,     // Типографія
        content = content            // Вміст теми
    )
}
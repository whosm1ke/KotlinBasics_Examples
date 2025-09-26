package ua.kpi.practical_example_13.ui.theme

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

// Означує темну колірну схему з використанням специфічних кольорів
private val DarkColorScheme = darkColorScheme(
    primary = Purple80,        // Основний колір для темної теми
    secondary = PurpleGrey80,  // Допоміжний колір для темної теми
    tertiary = Pink80          // Третинний колір для темної теми
)

// Означує світлу колірну схему з використанням специфічних кольорів
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
fun Practical_Example_13Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),  // Визначає, чи використовувати темну тему (за замовчуванням - згідно з системним налаштуванням)
    // Динамічна колірна схема доступна лише на Android 12+
    dynamicColor: Boolean = true,                // Визначає, чи використовувати динамічну кольорову схему
    content: @Composable () -> Unit                   // Функція-вміст для компонування теми
) {
    // Визначає, яку колірну схему використовувати на основі параметрів
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {  // Якщо динамічна тема доступна і пристрій підтримує Android 12+
            val context = LocalContext.current   // Отримуємо поточний контекст
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)  // Вибираємо темну або світлу динамічну схему
        }

        darkTheme -> DarkColorScheme   // Якщо темна тема включена, використовуємо темну схему
        else -> LightColorScheme       // Інакше - світлу схему
    }

    // Застосовуємо обрану колірну схему до MaterialTheme
    MaterialTheme(
        colorScheme = colorScheme,  // Передаємо обрану колірну схему
        typography = Typography,    // Використовуємо типографію з теми
        content = content           // Передаємо вміст для компонування
    )
}
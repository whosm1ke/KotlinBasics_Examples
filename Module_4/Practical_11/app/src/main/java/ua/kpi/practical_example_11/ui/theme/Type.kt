package ua.kpi.practical_example_11.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Оголошення об'єкта Typography з використанням Material Design типографії
val Typography = Typography(
    // Визначення стилю для великого тексту тіла
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,  // Використовуємо стандартну родину шрифтів
        fontWeight = FontWeight.Normal,   // Нормальна жирність шрифту
        fontSize = 16.sp,                 // Розмір шрифту 16 пікселів
        lineHeight = 24.sp,               // Висота рядка 24 пікселів
        letterSpacing = 0.5.sp            // Пропуск між буквами 0.5 пікселів
    )
    /* Інші стилі тексту за замовчуванням, які можна перевизначити
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
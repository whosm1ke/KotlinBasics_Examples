package ua.kpi.practical_example_8.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Оголошення об'єкта Typography з використанням Material Design типографіки
// Це стандартна типографіка, яку можна використовувати для відображення тексту в Compose
val Typography = Typography(
    // Стиль для великого тексту тіла (наприклад, основний текст на сторінці)
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,  // Використовується стандартна родина шрифтів
        fontWeight = FontWeight.Normal,   // Нормальна жирність шрифту
        fontSize = 16.sp,                 // Розмір шрифту 16 пікселів
        lineHeight = 24.sp,               // Висота рядка (відстань між лініями)
        letterSpacing = 0.5.sp            // Пропуск між символами
    )
    /*
    // Наступні стилі можуть бути використані для налаштування інших типів тексту:
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
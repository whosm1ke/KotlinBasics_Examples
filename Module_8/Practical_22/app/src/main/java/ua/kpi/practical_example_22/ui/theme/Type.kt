package ua.kpi.practical_example_22.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Визначення набору стилів типографіки Material для використання в Compose
val Typography = Typography(
    // Стиль для великого тексту тіла (наприклад, основний текст)
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,  // Використовується стандартна шрифтовая сімейство
        fontWeight = FontWeight.Normal,   // Нормальна товщина шрифту
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
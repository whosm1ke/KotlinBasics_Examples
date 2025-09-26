package ua.kpi.practical_example_25.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Оголошення об'єкта Typography з використанням Material Design типографіки
// Це основний об'єкт для налаштування стилів тексту в Compose
val Typography = Typography(
    // Визначення стилю для великого тексту (наприклад, основного контенту)
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,  // Використання стандартної системної шрифтової сімейства
        fontWeight = FontWeight.Normal,   // Нормальна жирність шрифту
        fontSize = 16.sp,                 // Розмір шрифту 16 пікселів (відповідає sp для масштабування)
        lineHeight = 24.sp,               // Висота рядка (відстань між рядками)
        letterSpacing = 0.5.sp            // Проміжок між літерами
    )
    /* Інші стилі тексту, які можна перевизначити:
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
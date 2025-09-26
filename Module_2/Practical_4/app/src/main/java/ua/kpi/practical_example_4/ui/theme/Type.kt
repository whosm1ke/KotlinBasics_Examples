package ua.kpi.practical_example_4.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Оголошення змінної Typography, яка містить набір стилів тексту Material Design
// Цей об'єкт використовується для встановлення стандартних стилів тексту в додатку
val Typography = Typography(
    // Визначення стилю для великого тексту (наприклад, основний текст у списку)
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,  // Використовуємо стандартну родину шрифтів
        fontWeight = FontWeight.Normal,   // Нормальна товщина шрифту
        fontSize = 16.sp,                 // Розмір шрифту 16 пікселів (sp - scalable pixels)
        lineHeight = 24.sp,               // Висота рядка для правильного відстання між рядками
        letterSpacing = 0.5.sp            // Пропуск між буквами
    )
    /*
    // Наступні стилі можуть бути використані для перевизначення стандартних значень:
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
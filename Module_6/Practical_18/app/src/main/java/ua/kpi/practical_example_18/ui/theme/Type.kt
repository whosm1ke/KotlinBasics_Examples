package ua.kpi.practical_example_18.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Оголошення об'єкта Typography з використанням Material Design типографії
// Це основний стиль для тексту у додатку, який можна перевизначати для різних елементів
val Typography = Typography(
    // Стиль для великого тексту (наприклад, основний контент)
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,  // Використання стандартного шрифту системи
        fontWeight = FontWeight.Normal,   // Нормальна товщина шрифту
        fontSize = 16.sp,                 // Розмір шрифту 16 пікселів
        lineHeight = 24.sp,               // Висота рядка для кращого читання
        letterSpacing = 0.5.sp            // Проміжок між буквами
    )
    /* Інші стилі тексту, які можна перевизначати:
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
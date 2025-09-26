package ua.kpi.practical_example_21.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Оголошення об'єкта Typography з використанням Material Design типографії
// Цей об'єкт містить стандартні стилі тексту, які можна використовувати в компонентах Compose
val Typography = Typography(
    // Визначення стилю для великого тексту (наприклад, основний контент)
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,  // Використання стандартної родини шрифтів
        fontWeight = FontWeight.Normal,   // Нормальна товщина шрифту
        fontSize = 16.sp,                 // Розмір шрифту 16 пікселів (sp - scale-independent pixels)
        lineHeight = 24.sp,               // Висота рядка для правильного відстанню між лініями
        letterSpacing = 0.5.sp             // Проміжок між буквами
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
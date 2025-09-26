package ua.kpi.practical_example_6.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Оголошення об'єкта Typography з налаштуваннями типографіки Material Design
val Typography = Typography(
    // Визначення стилю для великого тексту (наприклад, основного контенту)
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,  // Використання стандартної родини шрифтів
        fontWeight = FontWeight.Normal,   // Нормальна жирність шрифту
        fontSize = 16.sp,                 // Розмір шрифту 16 пікселів (sp - scalable pixels)
        lineHeight = 24.sp,               // Висота лінії 24 пікселів
        letterSpacing = 0.5.sp            // Проміжок між буквами 0.5 пікселя
    )
    /* Інші стилі тексту за замовчуванням, які можна перевизначити:
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
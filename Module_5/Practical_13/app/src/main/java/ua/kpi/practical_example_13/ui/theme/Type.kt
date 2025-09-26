package ua.kpi.practical_example_13.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Оголошення об'єкта Typography з налаштуваннями типографіки Material Design
// Цей об'єкт містить стилі тексту, які використовуються в додатку для відображення різних елементів інтерфейсу
val Typography = Typography(
    // Стиль для великого тексту (наприклад, основного контенту)
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,  // Використовуємо стандартну фонтову сімейство
        fontWeight = FontWeight.Normal,   // Нормальна жирність шрифту
        fontSize = 16.sp,                 // Розмір шрифту 16 пікселів (sp - масштабований піксель)
        lineHeight = 24.sp,               // Висота рядка (відстань між рядками тексту)
        letterSpacing = 0.5.sp            // Пропуск між буквами
    )
    /*
    // Додаткові стилі, які можна перевизначити:
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
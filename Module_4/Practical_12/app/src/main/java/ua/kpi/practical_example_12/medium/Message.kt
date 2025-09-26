package ua.kpi.practical_example_12.medium

// Оголошення даних класу (data class) з одним властивістю msg типу String
// Data class автоматично генерує корисні методи, такі як toString(), equals(), hashCode() та copy()
data class Message(val msg: String)
package ua.kpi.practical_example_12.advanced

// Оголошення data-класу Message, який автоматично генерує
// конструктор, toString(), equals() і hashCode() методи
// Властивість msg типу String зберігає текст повідомлення

data class Message(val msg: String)
package ua.kpi.practical_example_10.basic

// ----------------------------
// Data models (мапляться з API)
// ----------------------------

data class Station(
    val id: Int,                    // Унікальний ідентифікатор станції
    val name: String,               // Назва станції
    val location: String,           // Розташування станції (адреса або координати)
    val capacityKw: Double          // Місткість станції в кіловатах (KW)
)
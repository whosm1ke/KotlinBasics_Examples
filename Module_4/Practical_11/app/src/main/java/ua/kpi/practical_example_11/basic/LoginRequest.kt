package ua.kpi.practical_example_11.basic

import com.google.gson.annotations.SerializedName

// --- Дані запиту та відповіді ---
data class LoginRequest(
    @SerializedName("Username") val username: String,
    @SerializedName("Password")val password: String)
package ua.kpi.practical_example_11.medium

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("Username") val username: String,
    @SerializedName("Password") val password: String
)
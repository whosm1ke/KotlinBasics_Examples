package ua.kpi.practical_example_11.medium

import com.google.gson.annotations.SerializedName

/**
 * Data class representing a login request payload.
 * This class is used to serialize login credentials into JSON format
 * for sending to the server.
 */
data class LoginRequest(
    /**
     * The username field, serialized with the key "Username" in JSON.
     * This corresponds to the user's login identifier.
     */
    @SerializedName("Username") val username: String,
    
    /**
     * The password field, serialized with the key "Password" in JSON.
     * This corresponds to the user's password for authentication.
     */
    @SerializedName("Password") val password: String
)
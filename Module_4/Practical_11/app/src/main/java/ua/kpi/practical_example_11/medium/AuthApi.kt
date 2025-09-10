package ua.kpi.practical_example_11.medium

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("register")
    suspend fun register(@Body request: LoginRequest): Response<Unit>

    @GET("protected")
    suspend fun getProtectedData(): ProtectedMessage

    @GET("stations")
    suspend fun getStations(): List<SolarStation>
}


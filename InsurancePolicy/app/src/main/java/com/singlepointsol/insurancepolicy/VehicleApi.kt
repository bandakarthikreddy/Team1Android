package com.singlepointsol.insurancepolicy

import com.singlepointsol.insurancepolicy.Vehicle
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface VehicleApi {
    @GET("https://abzvehiclewebapi-chana.azurewebsites.net/api/Vehicle")
    suspend fun getVehicles(): List<Vehicle>

    @POST("https://abzvehiclewebapi-chana.azurewebsites.net/api/Vehicle")
    suspend fun createVehicle(@Body vehicle: VehicleData): Vehicle

    @PUT("https://abzvehiclewebapi-chana.azurewebsites.net/api/Vehicle")
    suspend fun updateVehicle(@Body vehicle: VehicleData): Vehicle

    @DELETE("https://abzvehiclewebapi-chana.azurewebsites.net/api/Vehicle")
    suspend fun deleteVehicle(@Body vehicle: VehicleData): Vehicle


}
package com.example.vehicleinsuranceapp1.API

import com.example.vehicleinsuranceapp1.dataclass.ClaimDataClass
import retrofit2.Response
import retrofit2.http.*

interface ClaimApi {

    @GET("Claim/{claimNo}")
    suspend fun getClaim(@Path("claimNo") claimNo: String): Response<ClaimDataClass>

    @POST("Claim")
    suspend fun addClaim(@Body claim: ClaimDataClass): Response<ClaimDataClass>

    @PUT("Claim/{claimNo}")
    suspend fun updateClaim(@Path("claimNo") claimNo: String, @Body claim: ClaimDataClass): Response<ClaimDataClass>

    @DELETE("Claim/{claimNo}")
    suspend fun deleteClaim(@Path("claimNo") claimNo: String): Response<Void>
}

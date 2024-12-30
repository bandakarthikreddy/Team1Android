package com.example.vehicleinsuranceapp1.RetrofitInstance

import com.example.vehicleinsuranceapp1.API.ClaimApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClaimRetrofitClient {
    private const val BASE_URL_PRODUCT = "https://abzclaimwebapi-chana.azurewebsites.net/api/"

    private fun getInstance(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL_PRODUCT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val claimApi: ClaimApi = getInstance().create(ClaimApi::class.java)
}
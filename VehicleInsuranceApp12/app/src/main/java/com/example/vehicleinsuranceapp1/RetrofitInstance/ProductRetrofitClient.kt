package com.example.vehicleinsuranceapp1.RetrofitInstance

import com.example.vehicleinsuranceapp1.API.ProductApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProductRetrofitClient {
    private const val BASE_URL_PRODUCT = "https://abzproductwebapi-chana.azurewebsites.net/api/"

    private fun getInstance(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL_PRODUCT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val productApi: ProductApi = getInstance().create(ProductApi::class.java)
}

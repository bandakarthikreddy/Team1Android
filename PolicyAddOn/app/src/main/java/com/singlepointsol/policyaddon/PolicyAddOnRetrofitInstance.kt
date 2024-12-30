package com.singlepointsol.policyaddon

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PolicyAddOnRetrofitInstance {
    private val base_URL = ""

    private fun getInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val policyAddOnAPI : PolicyAddOnAPI = getInstance().create(PolicyAddOnAPI::class.java)
}
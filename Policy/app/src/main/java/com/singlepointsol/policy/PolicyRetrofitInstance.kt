package com.singlepointsol.policy

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PolicyRetrofitInstance {
    private val base_URL = ""

    private fun getInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val policyAPI : PolicyAPI = getInstance().create(PolicyAPI::class.java)
}
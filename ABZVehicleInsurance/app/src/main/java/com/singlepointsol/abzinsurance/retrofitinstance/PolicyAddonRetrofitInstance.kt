package com.singlepointsol.abzinsurance.retrofitinstance

import com.singlepointsol.abzinsurance.api.PolicyAddonAPI
import com.singlepointsol.abzinsurance.constants.token.AUTH_TOKEN
import com.singlepointsol.abzinsurance.constants.urls.POLICY_BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PolicyAddonRetrofitInstance {
    private fun getInstance(): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer ${AUTH_TOKEN}") // Add token to the header
                    .build()
                chain.proceed(request)
            }
            .build()

        return Retrofit.Builder()
            .baseUrl(POLICY_BASE_URL) // Set the base URL
            .client(client)   // Attach OkHttpClient with interceptor
            .addConverterFactory(GsonConverterFactory.create()) // Use Gson converter
            .build()
    }

    val policyAddonAPI: PolicyAddonAPI = getInstance().create(PolicyAddonAPI::class.java)

}
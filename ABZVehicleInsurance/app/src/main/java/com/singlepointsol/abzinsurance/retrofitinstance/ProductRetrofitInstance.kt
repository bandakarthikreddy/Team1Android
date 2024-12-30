package com.singlepointsol.abzinsurance.retrofitinstance

import com.singlepointsol.abzinsurance.api.ProductAPI
import com.singlepointsol.abzinsurance.constants.token.AUTH_TOKEN
import com.singlepointsol.abzinsurance.constants.urls.PRODUCT_BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProductRetrofitInstance {
    private fun getInstance(): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer ${AUTH_TOKEN}")
                    .build()
                chain.proceed(request)
            }
            .build()

        return Retrofit.Builder()
            .baseUrl(PRODUCT_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val productAPI: ProductAPI = getInstance().create(ProductAPI::class.java)
}

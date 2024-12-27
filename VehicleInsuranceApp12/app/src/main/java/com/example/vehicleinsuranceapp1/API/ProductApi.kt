package com.example.vehicleinsuranceapp1.API

import com.example.vehicleinsuranceapp1.dataclass.ProductDataClass
import retrofit2.Response
import retrofit2.http.*

interface ProductApi {

    @POST("products")
    suspend fun addProduct(@Body product: ProductDataClass): Response<ProductDataClass>

    @GET("products/{id}")
    suspend fun getProduct(@Path("id") productID: String): Response<ProductDataClass>

    @PUT("products/{id}")
    suspend fun updateProduct(@Path("id") productID: String, @Body product: ProductDataClass): Response<ProductDataClass>

    @DELETE("products/{id}")
    suspend fun deleteProduct(@Path("id") productID: String): Response<Void>
}

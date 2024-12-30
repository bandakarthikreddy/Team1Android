package com.singlepointsol.abzinsurance.api

import com.singlepointsol.abzinsurance.dataclass.ProductDataClassItem
import retrofit2.Response
import retrofit2.http.*

interface ProductAPI {

    @GET("Product")
    suspend fun getProducts(): Response<List<ProductDataClassItem>>

    @POST("Product/{productID}")
    suspend fun addProduct(
        @Path("productID") productID: String,
        @Body product: ProductDataClassItem
    ): Response<ProductDataClassItem>

    @GET("Product/{productID}")
    suspend fun getProductById(
        @Path("productID") productID: String
    ): Response<ProductDataClassItem>

    @PUT("Product/{productID}")
    suspend fun updateProduct(
        @Path("productID") productID: String,
        @Body product: ProductDataClassItem
    ): Response<Void>


    @DELETE("Product/{productID}")
    suspend fun deleteProduct(
        @Path("productID") productID: String
    ): Response<Void>
}

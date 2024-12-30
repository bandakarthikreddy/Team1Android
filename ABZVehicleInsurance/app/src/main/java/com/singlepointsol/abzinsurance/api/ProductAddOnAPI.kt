package com.singlepointsol.abzinsurance.api

import com.singlepointsol.abzinsurance.dataclass.ProductAddOnDataClassItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProductAddOnAPI {

    @GET("ProductAddOn")
    suspend fun getProductAddOn() : Response<List<ProductAddOnDataClassItem>>

    @POST("api/ProductAddOn/{Token}")
    suspend fun addProductAddOn(
        @Body productAddOn: ProductAddOnDataClassItem,
    ) : Response<ProductAddOnDataClassItem>

    @GET("api/ProductAddOn/{productID}/{addonId}")
    suspend fun getProductAddOnByID(
        @Path("productID") productID: String,
        @Path("addonId") addonId: String
    ) : Response<ProductAddOnDataClassItem>

    @PUT("api/ProductAddOn/{productID}/{addonId}")
    suspend fun updateProductAddOn(
        @Path("productID") productID : String,
        @Path("addonId") addonId: String,
        @Body productAddOn: ProductAddOnDataClassItem
    ) : Response<ProductAddOnDataClassItem>

    @DELETE("api/ProductAddOn/{productID}/{addonId}")
    suspend fun deleteProductAddOn(
        @Path("productID") productID : String,
        @Path("addonId") addonId: String,
    ) : Response<Void>
}
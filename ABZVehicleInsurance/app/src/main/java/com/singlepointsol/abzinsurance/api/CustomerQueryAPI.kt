package com.singlepointsol.abzinsurance.api

import com.singlepointsol.abzinsurance.dataclass.CustomerQueryDataClassItem
import com.singlepointsol.abzinsurance.viewmodel.CustomerQueryViewModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CustomerQueryAPI {

    @GET("CustomerQuery")
    suspend fun getCustomerQuery() : Response<List<CustomerQueryDataClassItem>>

    @GET("CustomerQuery/{queryId}")
    suspend fun getCustomerQueryById(
        @Path("queryId") queryId : String
    ) : Response<CustomerQueryDataClassItem>

    @POST("CustomerQuery/{queryId}")
    suspend fun addCustomerQuery(
        @Path("queryId") queryId : String,
        @Body customerQuery: CustomerQueryDataClassItem
    ) : Response<CustomerQueryDataClassItem>

    @PUT("CustomerQuery/{queryId}")
    suspend fun updateCustomerQuery(
        @Path("queryId") queryId: String,
        @Body customerQuery: CustomerQueryDataClassItem
    ) : Response<CustomerQueryDataClassItem>

    @DELETE("CustomerQuery/{queryId}")
    suspend fun deleteCustomerQuery(
        @Path("queryId") queryId : String
    ) : Response<Void>
}
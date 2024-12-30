package com.singlepointsol.abzinsurance.api

import com.singlepointsol.abzinsurance.dataclass.QueryResponseDataClassItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface QueryResponseAPI {
    @GET("QueryResponse")
    suspend fun getQueryResponse() : Response<List<QueryResponseDataClassItem>>

    @GET("QueryResponse/{queryId}/{srNo}")
    suspend fun getQueryResponseById(
        @Path("queryId") queryId : String,
        @Path("srNo") srNo : String
    ) : Response<QueryResponseDataClassItem>

    @POST("QueryResponse/{Token}")
    suspend fun addQueryResponse(
        @Body queryResponse: QueryResponseDataClassItem
    ) : Response<QueryResponseDataClassItem>

    @PUT("QueryResponse/{queryId}/{srNo}")
    suspend fun updateQueryResponse(
        @Path("queryId") queryId: String,
        @Path("srNo") srNo : String,
        @Body queryResponse: QueryResponseDataClassItem
    ) : Response<QueryResponseDataClassItem>

    @DELETE("QueryResponse/{queryId}/{srNo}")
    suspend fun deleteQueryResponse(
        @Path("queryId") queryId : String,
        @Path("srNo") srNo : String
    ) : Response<Void>
}
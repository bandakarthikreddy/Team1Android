package com.singlepointsol.abzinsurance.api

import com.singlepointsol.abzinsurance.dataclass.PolicyDataClassItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PolicyAPI {
    @GET("Policy")
    suspend fun getPolicy() : Response<List<PolicyDataClassItem>>

    @POST("Policy/{policyNo}")
    suspend fun addPolicy(
        @Path("policyNo") policyNo: String,
        @Body policy: PolicyDataClassItem
    ): Response<PolicyDataClassItem>


    @GET("Policy/{policyNo}")
    suspend fun getPolicyByPolicyNo(
        @Path("policyNo") policyNo : String
    ) : Response<PolicyDataClassItem>

    @PUT("Policy/{policyNo}")
    suspend fun updatePolicy(
        @Path("policyNo") policyNo: String,
        @Body policy : PolicyDataClassItem
    ) : Response<PolicyDataClassItem>

    @DELETE("Policy/{policyNo}")
    suspend fun deletePolicy(
        @Path("policyNo") policyNo: String
    ) : Response<Void>
}
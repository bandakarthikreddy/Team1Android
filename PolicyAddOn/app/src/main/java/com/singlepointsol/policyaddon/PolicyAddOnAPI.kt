package com.singlepointsol.policyaddon

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PolicyAddOnAPI {

    @GET("Policy")
    suspend fun getPolicyAddOn() : Response<List<PolicyAddOnDataClassItem>>

    @POST("Policy")
    suspend fun addPolicyAddOn(
        @Body policyAddOn: String
    ) : Response<PolicyAddOnDataClassItem>

    @GET("Policy/{addonID}")
    suspend fun getPolicyAddOnByAddOnNo(
        @Path("addonID") addonID : String
    ) : Response<PolicyAddOnDataClassItem>

    @PUT("Policy/{addonID}")
    suspend fun updatePolicyAddOn(
        @Path("addonID") addonID: String,
        @Body policyAddOn : PolicyAddOnDataClassItem
    ) : Response<PolicyAddOnDataClassItem>

    @DELETE("Policy/{addonID}")
    suspend fun deletePolicyAddOn(
        @Path("addonID") addonID: String
    ) : Response<PolicyAddOnDataClassItem>
}
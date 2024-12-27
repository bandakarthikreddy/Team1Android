package com.singlepointsol.insurancepolicy

import com.singlepointsol.insurancepolicy.MainActivity
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface AgentApi {
    @GET("https://abzagentwebapi-chana.azurewebsites.net/api/Agent")
    suspend fun getAgents(): List<MainActivity>

    @POST("https://abzagentwebapi-chana.azurewebsites.net/api/Agent")
    suspend fun createAgent(@Body agent: Agent): MainActivity

    @PUT("https://abzagentwebapi-chana.azurewebsites.net/api/Agent")
    suspend fun updateAgent(@Body agent: Agent): MainActivity

    @DELETE("https://abzagentwebapi-chana.azurewebsites.net/api/Agent")
    suspend fun deleteAgent(@Body agent: Agent): MainActivity


}
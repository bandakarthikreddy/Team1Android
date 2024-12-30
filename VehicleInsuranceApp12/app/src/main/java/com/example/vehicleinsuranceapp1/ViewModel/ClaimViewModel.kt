package com.example.vehicleinsuranceapp1.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vehicleinsuranceapp1.API.ClaimApi
import com.example.vehicleinsuranceapp1.RetrofitInstance.ClaimRetrofitClient
import com.example.vehicleinsuranceapp1.dataclass.ClaimDataClass
import kotlinx.coroutines.launch
import retrofit2.Response

class ClaimViewModel : ViewModel() {

    private val claimApi: ClaimApi = ClaimRetrofitClient.claimApi

    fun addClaim(claim: ClaimDataClass, onResult: (Response<ClaimDataClass>) -> Unit) {
        viewModelScope.launch {
            val response = claimApi.addClaim(claim)
            onResult(response)
        }
    }

    fun updateClaim(claimNo: String, claim: ClaimDataClass, onResult: (Response<ClaimDataClass>) -> Unit) {
        viewModelScope.launch {
            val response = claimApi.updateClaim(claimNo, claim)
            onResult(response)
        }
    }

    fun deleteClaim(claimNo: String, onResult: (Response<Void>) -> Unit) {
        viewModelScope.launch {
            val response = claimApi.deleteClaim(claimNo)
            onResult(response)
        }
    }

    fun getClaimByNo(claimNo: String, onResult: (Response<ClaimDataClass>) -> Unit) {
        viewModelScope.launch {
            val response = claimApi.getClaim(claimNo)
            onResult(response)
        }
    }
}

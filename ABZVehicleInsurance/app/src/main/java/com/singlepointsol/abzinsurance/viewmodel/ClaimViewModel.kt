package com.singlepointsol.abzinsurance.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.singlepointsol.abzinsurance.dataclass.ClaimDataClassItem
import com.singlepointsol.abzinsurance.retrofitinstance.ClaimRetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ClaimViewModel: ViewModel() {
    private val _claimData = MutableStateFlow<ClaimDataClassItem?>(null)
    val claimData : StateFlow<ClaimDataClassItem?> = _claimData

    // Fetch Vehicle data by ID

    fun getClaimByClaimNo(claimNo: String, context: Context) {
        viewModelScope.launch {
            try {
                val response = ClaimRetrofitInstance.claimAPI.getClaimByClaimNo(claimNo)
                if (response.isSuccessful) {
                    _claimData.value = response.body()
                    Toast.makeText(context,"Claim details fetched successfully", Toast.LENGTH_LONG).show()
                    Log.d("ClaimViewModel", "Claim details fetched successfully")
                } else {
                    _claimData.value = null
                    Toast.makeText(context,"Failed to Claim data : ${response.message()}", Toast.LENGTH_LONG).show()
                    Log.e("ClaimViewModel", "Failed to Claim data : ${response.message()}")
                }
            } catch (e : Exception) {
                Toast.makeText(context,"Error fetching Claim data", Toast.LENGTH_LONG).show()
                Log.e("ClaimViewModel", "Error fetching Claim data", e)
            }
        }
    }

    fun addClaim(claimNo: String, claim: ClaimDataClassItem, context: Context) {
        viewModelScope.launch {
            try {
                val response = ClaimRetrofitInstance.claimAPI.addClaim(claimNo,claim)
                if (response.isSuccessful) {
                    Toast.makeText(context,"Claim details added successfully", Toast.LENGTH_LONG).show()
                    Log.d("ClaimViewModel", "Claim details added successfully")
                } else {
                    Toast.makeText(context,"Failed to add Claim data : ${response.message()}", Toast.LENGTH_LONG).show()
                    Log.e("ClaimViewModel", "Failed to add Claim data : ${response.message()}")
                }
            } catch (e : Exception) {
                Toast.makeText(context,"Error adding Claim data", Toast.LENGTH_LONG).show()
                Log.e("ClaimViewModel", "Error adding Claim data", e)
            }
        }
    }

    fun updateClaim(updateClaim: ClaimDataClassItem, claimNo: String, context: Context) {
        viewModelScope.launch {
            try {
                val response = ClaimRetrofitInstance.claimAPI.updateClaim(claimNo,updateClaim )
                if (response.isSuccessful) {
                    Toast.makeText(context,"Claim details updated successfully", Toast.LENGTH_LONG).show()
                    Log.d("ClaimViewModel", "Claim details updated successfully")
                } else {
                    Toast.makeText(context,"Failed to update Claim data : ${response.message()}", Toast.LENGTH_LONG).show()
                    Log.e("ClaimViewModel", "Failed to update Claim data : ${response.message()}")
                }
            } catch (e : Exception) {
                Toast.makeText(context,"Error updating Claim data", Toast.LENGTH_LONG).show()
                Log.e("ClaimViewModel", "Error updating Claim data", e)
            }
        }
    }

    fun deleteClaim(claimNo:  String, context: Context) {
        viewModelScope.launch {
            try {
                val response = ClaimRetrofitInstance.claimAPI.deleteClaim(claimNo)
                if (response.isSuccessful) {
                    Toast.makeText(context,"Claim details deleted successfully", Toast.LENGTH_LONG).show()
                    Log.d("ClaimViewModel", "Claim details deleted successfully")
                } else {
                    Toast.makeText(context,"Failed to delete Claim data : ${response.message()}", Toast.LENGTH_LONG).show()
                    Log.e("ClaimViewModel", "Failed to delete Claim data : ${response.message()}")
                }
            } catch (e : Exception) {
                Toast.makeText(context,"Error deleting Claim data", Toast.LENGTH_LONG).show()
                Log.e("ClaimViewModel", "Error deleting Claim data", e)
            }
        }
    }
}

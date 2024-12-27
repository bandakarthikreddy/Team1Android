package com.singlepointsol.policy

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PolicyViewModel : ViewModel(){

    private val _policyData = MutableStateFlow<PolicyDataClassItem?>(null)
    val policyData : StateFlow<PolicyDataClassItem?> = _policyData

    // Fetch Vehicle data by ID

    fun getPolicyByPolicyNo(policyNo: String, context: Context) {
        viewModelScope.launch {
            try {
                val response = PolicyRetrofitInstance.policyAPI.getPolicyByPolicyNo(policyNo)
                if (response.isSuccessful) {
                    _policyData.value = response.body()
                    Toast.makeText(context,"Product details fetched successfully", Toast.LENGTH_LONG).show()
                    Log.d("ProductViewModel", "Policy details fetched successfully")
                } else {
                    _policyData.value = null
                    Log.e("ProductViewModel", "Failed to Policy data : ${response.message()}")
                }
            } catch (e : Exception) {
                Log.e("ProductViewModel", "Error fetching Policy data", e)
            }
        }
    }

    fun addPolicy(addPolicy: String, context: Context) {
        viewModelScope.launch {
            try {
                val response = PolicyRetrofitInstance.policyAPI.addPolicy(addPolicy)
                if (response.isSuccessful) {
                    Toast.makeText(context,"Policy details added successfully", Toast.LENGTH_LONG).show()
                    Log.d("PolicyViewModel", "Policy details added successfully")
                } else {
                    Log.e("PolicyViewModel", "Failed to add Policy data : ${response.message()}")
                }
            } catch (e : Exception) {
                Log.e("PolicyViewModel", "Error adding Policy data", e)
            }
        }
    }

    fun updatePolicy(updatePolicy: PolicyDataClassItem, policyNo: String, context: Context) {
        viewModelScope.launch {
            try {
                val response = PolicyRetrofitInstance.policyAPI.updatePolicy(policyNo, updatePolicy)
                if (response.isSuccessful) {
                    Toast.makeText(context,"Policy details updated successfully", Toast.LENGTH_LONG).show()
                    Log.d("PolicyViewModel", "Policy details updated successfully")
                } else {
                    Log.e("PolicyViewModel", "Failed to update Policy data : ${response.message()}")
                }
            } catch (e : Exception) {
                Log.e("PolicyViewModel", "Error updating Policy data", e)
            }
        }
    }

    fun deletePolicy(policyNo:  String, context: Context) {
        viewModelScope.launch {
            try {
                val response = PolicyRetrofitInstance.policyAPI.deletePolicy(policyNo)
                if (response.isSuccessful) {
                    Toast.makeText(context,"Policy details deleted successfully", Toast.LENGTH_LONG).show()
                    Log.d("PolicyViewModel", "Policy details deleted successfully")
                } else {
                    Log.e("PolicyViewModel", "Failed to delete Policy data : ${response.message()}")
                }
            } catch (e : Exception) {
                Log.e("PolicyViewModel", "Error deleting Policy data", e)
            }
        }
    }
}

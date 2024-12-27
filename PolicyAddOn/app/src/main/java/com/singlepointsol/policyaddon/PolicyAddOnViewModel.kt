package com.singlepointsol.policyaddon

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PolicyAddOnViewModel:ViewModel() {
    private val _policyAddOnData = MutableStateFlow<PolicyAddOnDataClassItem?>(null)
    val policyAddOnData: StateFlow<PolicyAddOnDataClassItem?> = _policyAddOnData

    // Fetch Vehicle data by ID

    fun getPolicyAddOnByAddOnNo(addonID: String, context: Context) {
        viewModelScope.launch {
            try {
                val response = PolicyAddOnRetrofitInstance.policyAddOnAPI.getPolicyAddOnByAddOnNo(addonID)
                if (response.isSuccessful) {
                    _policyAddOnData.value = response.body()
                    Toast.makeText(
                        context,
                        "Policy Add On details fetched successfully",
                        Toast.LENGTH_LONG
                    ).show()
                    Log.d("PolicyAddOnViewModel", "Policy Add On details fetched successfully")
                } else {
                    _policyAddOnData.value = null
                    Log.e("PolicyAddOnViewModel", "Failed to Policy Add On data : ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("PolicyAddOnViewModel", "Error fetching Policy Add On data", e)
            }
        }
    }

    fun addPolicyAddOn(addPolicyAddOn: String, context: Context) {
        viewModelScope.launch {
            try {
                val response = PolicyAddOnRetrofitInstance.policyAddOnAPI.addPolicyAddOn(addPolicyAddOn)
                if (response.isSuccessful) {
                    Toast.makeText(context, "Policy Add On details added successfully", Toast.LENGTH_LONG)
                        .show()
                    Log.d("PolicyAddOnViewModel", "Policy Add On details added successfully")
                } else {
                    Log.e("PolicyAddOnViewModel", "Failed to add Policy Add On data : ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("PolicyAddOnViewModel", "Error adding Policy Add On data", e)
            }
        }
    }

    fun updatePolicyAddOn(updatePolicyAddOn: PolicyAddOnDataClassItem, addonID: String, context: Context) {
        viewModelScope.launch {
            try {
                val response = PolicyAddOnRetrofitInstance.policyAddOnAPI.updatePolicyAddOn(addonID,updatePolicyAddOn)
                if (response.isSuccessful) {
                    Toast.makeText(
                        context,
                        "Policy Add On details updated successfully",
                        Toast.LENGTH_LONG
                    ).show()
                    Log.d("PolicyAddOnViewModel", "Policy Add On details updated successfully")
                } else {
                    Log.e(
                        "PolicyAddOnViewModel",
                        "Failed to update Policy Add On data : ${response.message()}"
                    )
                }
            } catch (e: Exception) {
                Log.e("PolicyAddOnViewModel", "Error updating Policy Add On data", e)
            }
        }
    }

    fun deletePolicyAddOn(addonID: String, context: Context) {
        viewModelScope.launch {
            try {
                val response = PolicyAddOnRetrofitInstance.policyAddOnAPI.deletePolicyAddOn(addonID)
                if (response.isSuccessful) {
                    Toast.makeText(
                        context,
                        "Product details deleted successfully",
                        Toast.LENGTH_LONG
                    ).show()
                    Log.d("PolicyAddOnViewModel", "Product details deleted successfully")
                } else {
                    Log.e(
                        "PolicyAddOnViewModel",
                        "Failed to delete product data : ${response.message()}"
                    )
                }
            } catch (e: Exception) {
                Log.e("PolicyAddOnViewModel", "Error deleting product data", e)
            }
        }
    }
}
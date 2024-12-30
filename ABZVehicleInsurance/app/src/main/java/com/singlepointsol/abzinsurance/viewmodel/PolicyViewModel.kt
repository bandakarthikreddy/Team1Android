package com.singlepointsol.abzinsurance.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.singlepointsol.abzinsurance.dataclass.PolicyDataClassItem
import com.singlepointsol.abzinsurance.retrofitinstance.PolicyRetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PolicyViewModel : ViewModel(){

        private val _policyData = MutableStateFlow<PolicyDataClassItem?>(null)
        val policyData: StateFlow<PolicyDataClassItem?> = _policyData

        // Fetch Policy data by Policy No
        fun getPolicyByPolicyNo(policyNo: String, context: Context) {
            viewModelScope.launch {
                try {
                    val response = PolicyRetrofitInstance.policyAPI.getPolicyByPolicyNo(policyNo)
                    if (response.isSuccessful) {
                        _policyData.value = response.body()
                        Toast.makeText(context, "Policy details fetched successfully", Toast.LENGTH_LONG).show()
                        Log.d("PolicyViewModel", "Policy details fetched successfully")
                    } else {
                        _policyData.value = null
                        Toast.makeText(context, "Failed to fetch Policy data: ${response.message()}", Toast.LENGTH_LONG).show()
                        Log.e("PolicyViewModel", "Failed to fetch Policy data: ${response.message()}")
                    }
                } catch (e: Exception) {
                    Toast.makeText(context, "Error fetching Policy data", Toast.LENGTH_LONG).show()
                    Log.e("PolicyViewModel", "Error fetching Policy data", e)
                }
            }
        }

        // Add a new Policy
        fun addPolicy(policyNo: String, policy: PolicyDataClassItem, context: Context) {
            viewModelScope.launch {
                try {
                    val response = PolicyRetrofitInstance.policyAPI.addPolicy(policyNo,policy)
                    if (response.isSuccessful) {
                        _policyData.value = response.body()
                        Toast.makeText(context, "Policy details added successfully", Toast.LENGTH_LONG).show()
                        Log.d("PolicyViewModel", "Policy details added successfully")
                    } else {
                        _policyData.value = null
                        Toast.makeText(context, "Failed to add Policy data: \${response.message()", Toast.LENGTH_LONG).show()
                        Log.e("PolicyViewModel", "Failed to add Policy data: ${response.message()}")
                    }
                } catch (e: Exception) {
                    Toast.makeText(context, "Error adding Policy data", Toast.LENGTH_LONG).show()
                    Log.e("PolicyViewModel", "Error adding Policy data", e)
                }
            }
        }

        // Update Policy details
        fun updatePolicy(policy: PolicyDataClassItem, policyNo: String, context: Context) {
            viewModelScope.launch {
                try {
                    val response = PolicyRetrofitInstance.policyAPI.updatePolicy(policyNo, policy)
                    if (response.isSuccessful) {
                        Toast.makeText(context, "Policy details updated successfully", Toast.LENGTH_LONG).show()
                        Log.d("PolicyViewModel", "Policy details updated successfully")
                    } else {
                        Toast.makeText(context, "Failed to update Policy data: ${response.message()}", Toast.LENGTH_LONG).show()
                        Log.e("PolicyViewModel", "Failed to update Policy data: ${response.message()}")
                    }
                } catch (e: Exception) {
                    Toast.makeText(context, "Error updating Policy data", Toast.LENGTH_LONG).show()
                    Log.e("PolicyViewModel", "Error updating Policy data", e)
                }
            }
        }

        // Delete a Policy
        fun deletePolicy(policyNo: String, context: Context) {
            viewModelScope.launch {
                try {
                    val response = PolicyRetrofitInstance.policyAPI.deletePolicy(policyNo)
                    if (response.isSuccessful) {
                        Toast.makeText(context, "Policy details deleted successfully", Toast.LENGTH_LONG).show()
                        Log.d("PolicyViewModel", "Policy details deleted successfully")
                    } else {
                        Toast.makeText(context, "Failed to delete Policy data: ${response.message()}", Toast.LENGTH_LONG).show()
                        Log.e("PolicyViewModel", "Failed to delete Policy data: ${response.message()}")
                    }
                } catch (e: Exception) {
                    Toast.makeText(context, "Error deleting Policy data", Toast.LENGTH_LONG).show()
                    Log.e("PolicyViewModel", "Error deleting Policy data", e)
                }
            }
        }
    }

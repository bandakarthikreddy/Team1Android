package com.singlepointsol.abzinsurance.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.singlepointsol.abzinsurance.dataclass.PolicyAddonDataClassItem
import com.singlepointsol.abzinsurance.retrofitinstance.PolicyAddonRetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PolicyAddonViewModel : ViewModel() {
    private val _policyAddonData = MutableStateFlow<PolicyAddonDataClassItem?>(null)
    val policyAddonData: StateFlow<PolicyAddonDataClassItem?> = _policyAddonData

    fun getPolicyAddonByID(policyNo: String, addonID: String, context: Context) {
        viewModelScope.launch {
            try {
                Log.d("PolicyAddonViewModel", "Fetching with params: policyNo=$policyNo, addonId=$addonID")
                val response = PolicyAddonRetrofitInstance.policyAddonAPI.getPolicyAddonByID(policyNo, addonID)

                if (response.isSuccessful) {
                    Log.d("PolicyAddonViewModel", "Response Code: ${response.code()}")
                    Log.d("PolicyAddonViewModel", "Response Message: ${response.message()}")
                    Log.d("PolicyAddonViewModel", "Response Error Body: ${response.errorBody()?.string()}")
                    _policyAddonData.value = response.body()
                    Toast.makeText(context, "Policy Addon Fetched Successfully", Toast.LENGTH_LONG).show()
                    Log.d("PolicyAddonViewModel", "Policy Addon Fetched Successfully")
                } else {

                    Log.d("PolicyAddonViewModel", "Response Code: ${response.code()}")
                    Log.d("PolicyAddonViewModel", "Response Message: ${response.message()}")
                    Log.d("PolicyAddonViewModel", "Response Error Body: ${response.errorBody()?.string()}")
                    Toast.makeText(context, "Policy Addon Not Fetched Successfully", Toast.LENGTH_LONG).show()
                    Log.e("PolicyAddonViewModel", "Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {

                Toast.makeText(context, "Something went wrong: ${e.message}", Toast.LENGTH_LONG).show()
                Log.e("PolicyAddonViewModel", "Exception in fetchPolicyAddon: ${e.message}")
            }
        }
    }

    fun addPolicyAddon( policyNo: String, addonID: String,policyAddon: PolicyAddonDataClassItem, context: Context) {
        viewModelScope.launch {
            try {


                val response = PolicyAddonRetrofitInstance.policyAddonAPI.addPolicyAddon( policyAddon)

                if (response.isSuccessful) {
                    Log.d("PolicyAddonViewModel", "Request Body: $policyAddon")
                    Log.d("PolicyAddonViewModel", "Response Code: ${response.code()}")
                    Log.d("PolicyAddonViewModel", "Response Message: ${response.message()}")
                    Log.d("PolicyAddonViewModel", "Response Error Body: ${response.errorBody()?.string()}")
                    Toast.makeText(context, "New Policy Addon Added Successfully", Toast.LENGTH_LONG).show()
                    Log.d("PolicyAddonViewModel", "New Policy Addon Added Successfully")
                } else {
                    Log.d("PolicyAddonViewModel", "Request Body: $policyAddon")
                    Log.d("PolicyAddonViewModel", "Response Code: ${response.code()}")
                    Log.d("PolicyAddonViewModel", "Response Message: ${response.message()}")
                    Log.d("PolicyAddonViewModel", "Response Error Body: ${response.errorBody()?.string()}")
                    Toast.makeText(context, "New Policy Addon Not Added Successfully", Toast.LENGTH_LONG).show()
                    Log.e("PolicyAddonViewModel", "Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {

                Toast.makeText(context, "Something went wrong: ${e.message}", Toast.LENGTH_LONG).show()
                Log.e("PolicyAddonViewModel", "Exception in addPolicyAddon: ${e.message}")
            }
        }
    }

    fun updatePolicyAddon(policyNo: String,addonID: String, policyAddon: PolicyAddonDataClassItem, context: Context) {
        viewModelScope.launch {
            try {
                Log.d("PolicyAddonViewModel", "Updating with params: policyNo=$policyNo, addonId=$addonID")
                val response = PolicyAddonRetrofitInstance.policyAddonAPI.updatePolicyAddon(policyNo,addonID, policyAddon)

                if (response.isSuccessful) {

                    Toast.makeText(context, "Policy Addon Updated Successfully", Toast.LENGTH_LONG).show()
                    Log.d("PolicyAddonViewModel", "Policy Addon Updated Successfully")
                } else {
                    Toast.makeText(context, "Policy Addon Not Updated Successfully", Toast.LENGTH_LONG).show()
                    Log.e("PolicyAddonViewModel", "Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Something went wrong: ${e.message}", Toast.LENGTH_LONG).show()
                Log.e("PolicyAddonViewModel", "Exception in updatePolicyAddon: ${e.message}")
            }
        }
    }

    fun deletePolicyAddon(policyNo: String,addonID: String, context: Context) {
        viewModelScope.launch {
            try {
                Log.d("PolicyAddonViewModel", "Deleting with params: policyNo=$policyNo, addonId=$addonID")
                val response = PolicyAddonRetrofitInstance.policyAddonAPI.deletePolicyAddon(policyNo,addonID)

                if (response.isSuccessful) {
                    Toast.makeText(context, "Policy Addon Deleted Successfully", Toast.LENGTH_LONG).show()
                    Log.d("PolicyAddonViewModel", "Policy Addon Deleted Successfully")
                } else {
                    Toast.makeText(context, "Policy Addon Not Deleted Successfully", Toast.LENGTH_LONG).show()
                    Log.e("PolicyAddonViewModel", "Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Something went wrong: ${e.message}", Toast.LENGTH_LONG).show()
                Log.e("PolicyAddonViewModel", "Exception in deletePolicyAddon: ${e.message}")
            }
        }
    }
}
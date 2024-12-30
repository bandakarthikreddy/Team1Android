package com.singlepointsol.abzinsurance.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.singlepointsol.abzinsurance.dataclass.CustomerQueryDataClassItem
import com.singlepointsol.abzinsurance.retrofitinstance.CustomerQueryRetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CustomerQueryViewModel: ViewModel() {
    val _customerQueryData = MutableStateFlow<CustomerQueryDataClassItem?>(null)
    val customerQueryData : StateFlow<CustomerQueryDataClassItem?> = _customerQueryData

    // Fetch Vehicle data by ID

    fun getCustomerQueryById(queryId: String, context: Context) {
        viewModelScope.launch {
            try {
                val response = CustomerQueryRetrofitInstance.customerQueryAPI.getCustomerQueryById(queryId)
                if (response.isSuccessful) {
                    _customerQueryData.value = response.body()
                    Toast.makeText(context,"Customer Query  fetched successfully", Toast.LENGTH_LONG).show()
                    Log.d("CustomerQueryViewModal", "Customer Query fetched successfully")
                } else {
                    _customerQueryData.value = null
                    Toast.makeText(context,"Failed to get Customer Query : ${response.message()}", Toast.LENGTH_LONG).show()
                    Log.e("CustomerQueryViewModal", "Failed to get Customer Query : ${response.message()}")
                }
            } catch (e : Exception) {
                Toast.makeText(context,"Failed to get Customer Query", Toast.LENGTH_LONG).show()
                Log.e("CustomerQueryViewModal", "Failed to get Customer Query", e)
            }
        }
    }

    fun addCustomerQuery(queryId: String, addCustomerQuery : CustomerQueryDataClassItem, context: Context) {
        viewModelScope.launch {
            try {
                val response = CustomerQueryRetrofitInstance.customerQueryAPI.addCustomerQuery(queryId, addCustomerQuery)
                if (response.isSuccessful) {
                    Toast.makeText(context,"Customer query added successfully", Toast.LENGTH_LONG).show()
                    Log.d("CustomerQueryViewModal", "Customer query added successfully")
                } else {
                    Toast.makeText(context,"Failed to add Customer query : ${response.message()}", Toast.LENGTH_LONG).show()
                    Log.e("CustomerQueryViewModal", "Failed to add Customer query : ${response.message()}")
                }
            } catch (e : Exception) {
                Toast.makeText(context,"Error adding Customer query", Toast.LENGTH_LONG).show()
                Log.e("CustomerQueryViewModal", "Error adding Customer query", e)
            }
        }
    }

    fun updateCustomerQuery(customerQuery : CustomerQueryDataClassItem, queryId: String, context: Context) {
        viewModelScope.launch {
            try {
                val response = CustomerQueryRetrofitInstance.customerQueryAPI.updateCustomerQuery(queryId,customerQuery)
                if (response.isSuccessful) {
                    Toast.makeText(context,"Customer query updated successfully", Toast.LENGTH_LONG).show()
                    Log.d("CustomerQueryViewModal", "Customer query updated successfully")
                } else {
                    Toast.makeText(context,"Failed to update Customer query : ${response.message()}", Toast.LENGTH_LONG).show()
                    Log.e("CustomerQueryViewModal", "Failed to update Customer query : ${response.message()}")
                }
            } catch (e : Exception) {
                Toast.makeText(context,"Error updating Customer query", Toast.LENGTH_LONG).show()
                Log.e("CustomerQueryViewModal", "Error updating Customer query", e)
            }
        }
    }

    fun deleteCustomerQuery(queryId: String, context: Context) {
        viewModelScope.launch {
            try {
                val response = CustomerQueryRetrofitInstance.customerQueryAPI.deleteCustomerQuery(queryId)
                if (response.isSuccessful) {
                    Toast.makeText(context,"Customer query deleted successfully", Toast.LENGTH_LONG).show()
                    Log.d("CustomerQueryViewModal", "Customer query deleted successfully")
                } else {
                    Toast.makeText(context,"Failed to delete Customer query : ${response.message()}", Toast.LENGTH_LONG).show()
                    Log.e("CustomerQueryViewModal", "Failed to delete Customer query : ${response.message()}")
                }
            } catch (e : Exception) {
                Toast.makeText(context,"Error deleting Customer query", Toast.LENGTH_LONG).show()
                Log.e("CustomerQueryViewModal", "Error deleting Customer query", e)
            }
        }
    }
}

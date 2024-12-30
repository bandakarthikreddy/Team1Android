package com.singlepointsol.abzinsurance.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.singlepointsol.abzinsurance.dataclass.CustomerDataClassItem
import com.singlepointsol.abzinsurance.retrofitinstance.CustomerRetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CustomerViewModel: ViewModel() {
    private val _customerData = MutableStateFlow<CustomerDataClassItem?>(null)
    val customerData: StateFlow<CustomerDataClassItem?> = _customerData

    // Fetch customer data by ID
    fun fetchCustomerDataById(customerId: String) {
        viewModelScope.launch {
            try {
                val response = CustomerRetrofitInstance.customerAPI.getCustomerById(customerId)
                if (response.isSuccessful) {
                    _customerData.value = response.body()
                    Log.d("CustomerViewModel", "Customer data fetched successfully")
                } else {
                    _customerData.value = null
                    Log.e("CustomerViewModel", "Failed to fetch customer data: ${response.message()}")
                }
            } catch (e: Exception) {
                _customerData.value = null
                Log.e("CustomerViewModel", "Error fetching customer data", e)
            }
        }
    }

    // Add a new customer
    fun addNewCustomer(id: String, addNewCustomer: CustomerDataClassItem) {
        viewModelScope.launch {
            try {
                val response = CustomerRetrofitInstance.customerAPI.postCustomer(id, addNewCustomer)
                if (response.isSuccessful) {
                    Log.d("CustomerViewModel", "Customer added successfully")
                } else {
                    Log.e("CustomerViewModel", "Failed to add customer: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("CustomerViewModel", "Error adding customer", e)
            }
        }
    }

    fun updateCustomerData(updatedCustomer: CustomerDataClassItem) {
        viewModelScope.launch {
            try {
                val response = CustomerRetrofitInstance.customerAPI.updateCustomer(
                    id = updatedCustomer.customerID,
                    customer = updatedCustomer
                )
                if (response.isSuccessful) {
                    Log.d("CustomerViewModel", "Customer updated successfully: ${response.body()}")
                } else {
                    Log.e("CustomerViewModel", "Failed to update customer: ${response.message()} - ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("CustomerViewModel", "Error updating customer", e)
            }
        }
    }


    // Delete customer data by ID
    fun deleteCustomerData(customerId: String) {
        viewModelScope.launch {
            try {
                val response = CustomerRetrofitInstance.customerAPI.deleteCustomer(customerId)
                if (response.isSuccessful) {
                    Log.d("CustomerViewModel", "Customer deleted successfully")
                } else {
                    Log.e("CustomerViewModel", "Failed to delete customer: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("CustomerViewModel", "Error deleting customer", e)
            }
        }
    }
}

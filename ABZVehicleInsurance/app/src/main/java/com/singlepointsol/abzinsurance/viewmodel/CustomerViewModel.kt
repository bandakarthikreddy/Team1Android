package com.singlepointsol.abzinsurance.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
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
    fun fetchCustomerDataById(customerId: String, context: Context) {
        viewModelScope.launch {
            try {
                val response = CustomerRetrofitInstance.customerAPI.getCustomerById(customerId)
                if (response.isSuccessful) {
                    Toast.makeText(context,"Customer data fetched successfully", Toast.LENGTH_LONG).show()
                    _customerData.value = response.body()
                    Log.d("CustomerViewModel", "Customer data fetched successfully")
                } else {
                    _customerData.value = null
                    Toast.makeText(context,"Failed to fetch customer data: ${response.message()}", Toast.LENGTH_LONG).show()
                    Log.e("CustomerViewModel", "Failed to fetch customer data: ${response.message()}")
                }
            } catch (e: Exception) {
                _customerData.value = null
                Toast.makeText(context,"Error fetching customer data", Toast.LENGTH_LONG).show()
                Log.e("CustomerViewModel", "Error fetching customer data", e)
            }
        }
    }

    // Add a new customer
    fun addNewCustomer(id: String, addNewCustomer: CustomerDataClassItem, context: Context) {
        viewModelScope.launch {
            try {
                val response = CustomerRetrofitInstance.customerAPI.postCustomer(id, addNewCustomer)
                if (response.isSuccessful) {
                    Toast.makeText(context,"Customer added successfully", Toast.LENGTH_LONG).show()
                    Log.d("CustomerViewModel", "Customer added successfully")
                } else {
                    Toast.makeText(context,"Failed to add customer: ${response.message()}", Toast.LENGTH_LONG).show()
                    Log.e("CustomerViewModel", "Failed to add customer: ${response.message()}")
                }
            } catch (e: Exception) {
                Toast.makeText(context,"Error adding customer", Toast.LENGTH_LONG).show()
                Log.e("CustomerViewModel", "Error adding customer", e)
            }
        }
    }

    fun updateCustomerData(updatedCustomer: CustomerDataClassItem, context: Context) {
        viewModelScope.launch {
            try {
                val response = CustomerRetrofitInstance.customerAPI.updateCustomer(
                    id = updatedCustomer.customerID,
                    customer = updatedCustomer
                )
                if (response.isSuccessful) {
                    Toast.makeText(context,"Customer updated successfully: ${response.body()}", Toast.LENGTH_LONG).show()
                    Log.d("CustomerViewModel", "Customer updated successfully: ${response.body()}")
                } else {
                    Toast.makeText(context,"Failed to update customer: ${response.message()} - ${response.errorBody()?.string()}", Toast.LENGTH_LONG).show()
                    Log.e("CustomerViewModel", "Failed to update customer: ${response.message()} - ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Toast.makeText(context,"Error updating customer", Toast.LENGTH_LONG).show()
                Log.e("CustomerViewModel", "Error updating customer", e)
            }
        }
    }


    // Delete customer data by ID
    fun deleteCustomerData(customerId: String, context: Context) {
        viewModelScope.launch {
            try {
                val response = CustomerRetrofitInstance.customerAPI.deleteCustomer(customerId)
                if (response.isSuccessful) {
                    Toast.makeText(context,"Customer deleted successfully", Toast.LENGTH_LONG).show()
                    Log.d("CustomerViewModel", "Customer deleted successfully")
                } else {
                    Toast.makeText(context,"Failed to delete customer: \${response.message()", Toast.LENGTH_LONG).show()
                    Log.e("CustomerViewModel", "Failed to delete customer: ${response.message()}")
                }
            } catch (e: Exception) {
                Toast.makeText(context,"Error deleting customer", Toast.LENGTH_LONG).show()
                Log.e("CustomerViewModel", "Error deleting customer", e)
            }
        }
    }
}

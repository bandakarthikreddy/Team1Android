package com.singlepointsol.abzinsurance.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.singlepointsol.abzinsurance.dataclass.ProductAddOnDataClassItem
import com.singlepointsol.abzinsurance.retrofitinstance.ProductAddOnRetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductAddOnViewModel:ViewModel() {

    private val _productAddOnData = MutableStateFlow<ProductAddOnDataClassItem?>(null)
    val productAddOnData : StateFlow<ProductAddOnDataClassItem?> = _productAddOnData

    fun getProductAddOnByID(productID: String, addonID: String, context: Context) {
        viewModelScope.launch {
            try {
                val response = ProductAddOnRetrofitInstance.productAddOnAPI.getProductAddOnByID(productID, addonID)
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        // Process the data and update the UI
                        _productAddOnData.value = data
                        Toast.makeText(context, "Product Addon Fetched Successfully", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(context, "Product Addon not found", Toast.LENGTH_LONG).show()
                        Log.e("productAddonViewModel", "Empty response body")
                    }
                } else {
                    Toast.makeText(context, "Error fetching Product Addon", Toast.LENGTH_LONG).show()
                    Log.e("productAddonViewModel", "Error: ${response.code()} - ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Something went wrong: ${e.message}", Toast.LENGTH_LONG).show()
                Log.e("productAddonViewModel", "Exception in fetch productAddon: ${e.message}")
            }
        }
    }


    fun addProductAddOn(productAddon: ProductAddOnDataClassItem, context: Context) {
        viewModelScope.launch {
            try {
                val response = ProductAddOnRetrofitInstance.productAddOnAPI.addProductAddOn(productAddon)

                if (response.isSuccessful) {
                    Log.e("productAddonViewModel", "Error: ${response.code()} - ${response.errorBody()?.string()}")
                    Toast.makeText(context, "New product Addon Added Successfully", Toast.LENGTH_LONG).show()
                    Log.d("productAddonViewModel", "New product Addon Added Successfully")
                } else {
                    Log.e("productAddonViewModel", "Error: ${response.code()} - ${response.errorBody()?.string()}")
                    Toast.makeText(context, "New product Addon Not Added Successfully", Toast.LENGTH_LONG).show()
                    Log.e("productAddonViewModel", "Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Something went wrong: ${e.message}", Toast.LENGTH_LONG).show()
                Log.e("productAddonViewModel", "Exception in add productAddon: ${e.message}")
            }
        }
    }

    fun updateProductAddOn(addonID: String, productID: String, productAddon: ProductAddOnDataClassItem, context: Context) {
        viewModelScope.launch {
            try {
                Log.d("productAddonViewModel", "Updating with params: productID=$productID, addonId=$addonID")
                val response = ProductAddOnRetrofitInstance.productAddOnAPI.updateProductAddOn(addonID, productID, productAddon)

                if (response.isSuccessful) {
                    Toast.makeText(context, "product Addon Updated Successfully", Toast.LENGTH_LONG).show()
                    Log.d("productAddonViewModel", "product Addon Updated Successfully")
                } else {
                    Toast.makeText(context, "product Addon Not Updated Successfully", Toast.LENGTH_LONG).show()
                    Log.e("productAddonViewModel", "Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Something went wrong: ${e.message}", Toast.LENGTH_LONG).show()
                Log.e("productAddonViewModel", "Exception in updateproductAddon: ${e.message}")
            }
        }
    }

    fun deleteProductAddOn(addonID: String, productID: String, context: Context) {
        viewModelScope.launch {
            try {
                Log.d("productAddonViewModel", "Deleting with params: productID=$productID, addonId=$addonID")
                val response = ProductAddOnRetrofitInstance.productAddOnAPI.deleteProductAddOn(addonID, productID)

                if (response.isSuccessful) {
                    Toast.makeText(context, "product Addon Deleted Successfully", Toast.LENGTH_LONG).show()
                    Log.d("productAddonViewModel", "product Addon Deleted Successfully")
                } else {
                    Toast.makeText(context, "product Addon Not Deleted Successfully", Toast.LENGTH_LONG).show()
                    Log.e("productAddonViewModel", "Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Something went wrong: ${e.message}", Toast.LENGTH_LONG).show()
                Log.e("productAddonViewModel", "Exception in delete productAddon: ${e.message}")
            }
        }
    }

}
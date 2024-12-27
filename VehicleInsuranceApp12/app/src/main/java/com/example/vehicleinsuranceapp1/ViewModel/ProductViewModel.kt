package com.example.vehicleinsuranceapp1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vehicleinsuranceapp1.API.ProductApi
import com.example.vehicleinsuranceapp1.RetrofitInstance.ProductRetrofitClient
import com.example.vehicleinsuranceapp1.dataclass.ProductDataClass
import kotlinx.coroutines.launch
import retrofit2.Response

class ProductViewModel : ViewModel() {

    private val productApi: ProductApi = ProductRetrofitClient.productApi

    fun addProduct(product: ProductDataClass, onResult: (Response<ProductDataClass>) -> Unit) {
        viewModelScope.launch {
            val response = productApi.addProduct(product)
            onResult(response)
        }
    }

    fun updateProduct(productID: String, product: ProductDataClass, onResult: (Response<ProductDataClass>) -> Unit) {
        viewModelScope.launch {
            val response = productApi.updateProduct(productID, product)
            onResult(response)
        }
    }

    fun deleteProduct(productID: String, onResult: (Response<Void>) -> Unit) {
        viewModelScope.launch {
            val response = productApi.deleteProduct(productID)
            onResult(response)
        }
    }

    fun getProduct(productID: String, onResult: (Response<ProductDataClass>) -> Unit) {
        viewModelScope.launch {
            val response = productApi.getProduct(productID)
            onResult(response)
        }
    }
}
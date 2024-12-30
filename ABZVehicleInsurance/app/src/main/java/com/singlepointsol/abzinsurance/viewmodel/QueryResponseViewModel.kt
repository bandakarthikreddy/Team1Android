package com.singlepointsol.abzinsurance.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.singlepointsol.abzinsurance.dataclass.QueryResponseDataClassItem
import com.singlepointsol.abzinsurance.retrofitinstance.QueryResponseRetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class QueryResponseViewModel: ViewModel() {
    val _queryResponseData = MutableStateFlow<QueryResponseDataClassItem?>(null)
    val queryResponseData : StateFlow<QueryResponseDataClassItem?> = _queryResponseData

    // Fetch Vehicle data by ID

    fun getQueryResponseById(queryId: String,srNo: String, context: Context) {
        viewModelScope.launch {
            try {
                val response = QueryResponseRetrofitInstance.queryResponse.getQueryResponseById(queryId,srNo)
                if (response.isSuccessful) {
                    _queryResponseData.value = response.body()
                    Toast.makeText(context,"Query Response  fetched successfully", Toast.LENGTH_LONG).show()
                    Log.d("QueryResponseViewModel", "Query Response fetched successfully")
                } else {
                    _queryResponseData.value = null
                    val errorBody = response.errorBody()?.string()
                    Log.e("QueryResponseViewModel", "Failed to get Query Response : ${response.message()} | Error Body: $errorBody")
                }
            } catch (e : Exception) {
                Log.e("QueryResponseViewModel", "Failed to get Query Response", e)
            }
        }
    }

    fun addQueryResponse(queryResponse : QueryResponseDataClassItem,  context: Context) {
        viewModelScope.launch {
            try {

                val response = QueryResponseRetrofitInstance.queryResponse.addQueryResponse(queryResponse)
                if (response.isSuccessful) {
                    Toast.makeText(context,"Query Response added successfully", Toast.LENGTH_LONG).show()
                    Log.d("QueryResponseViewModel", "Query Response added successfully")
                } else {
                    Log.e("QueryResponseViewModel", "Failed to add Query Response : ${response.message()}")
                }
            } catch (e : Exception) {
                Log.e("QueryResponseViewModel", "Error adding Query Response", e)
            }
        }
    }

    fun updateQueryResponse(queryId: String, srNo: String, queryResponse : QueryResponseDataClassItem, context: Context) {
        viewModelScope.launch {
            try {
                val response = QueryResponseRetrofitInstance.queryResponse.updateQueryResponse(queryId,srNo,queryResponse)
                if (response.isSuccessful) {
                    Toast.makeText(context,"Query Response updated successfully", Toast.LENGTH_LONG).show()
                    Log.d("QueryResponseViewModel", "Query Response updated successfully")
                } else {
                    Log.e("QueryResponseViewModel", "Failed to update Query Response : ${response.message()}")
                }
            } catch (e : Exception) {
                Log.e("QueryResponseViewModel", "Error updating Query Response", e)
            }
        }
    }

    fun deleteQueryResponse(queryId: String,srNo: String, context: Context) {
        viewModelScope.launch {
            try {
                val response = QueryResponseRetrofitInstance.queryResponse.deleteQueryResponse(queryId,srNo)
                if (response.isSuccessful) {
                    Toast.makeText(context,"Query Responsen deleted successfully", Toast.LENGTH_LONG).show()
                    Log.d("QueryResponseViewModel", "Query Response deleted successfully")
                } else {
                    Log.e("QueryResponseViewModel", "Failed to delete Query Response : ${response.message()}")
                }
            } catch (e : Exception) {
                Log.e("QueryResponseViewModel", "Error deleting Query Response", e)
            }
        }
    }
}
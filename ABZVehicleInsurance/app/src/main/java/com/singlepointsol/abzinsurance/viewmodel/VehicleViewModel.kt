package com.singlepointsol.abzinsurance.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.singlepointsol.abzinsurance.dataclass.VehicleDataClassItem
import com.singlepointsol.abzinsurance.retrofitinstance.VehicleRetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class VehicleViewModel: ViewModel() {

    private val _vehicleData = MutableStateFlow<VehicleDataClassItem?>(null)
    val vehicleData : StateFlow<VehicleDataClassItem?> = _vehicleData
    private val _owners = MutableStateFlow<VehicleDataClassItem?>(null)
    val owners : StateFlow<VehicleDataClassItem?> = _owners

    fun getOwners(ownerID: String, context: Context) {
        viewModelScope.launch {
            try {
                val response = VehicleRetrofitInstance.vehicleAPI.getOwners(ownerID)
                if (response.isSuccessful) {
                    _owners.value = response.body() // Save the fetched owner data
                    Log.d("VehicleViewModel", "Owner data fetched successfully")
                } else {
                    _owners.value = null
                    Log.e("VehicleViewModel", "Failed to fetch owner data : ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("VehicleViewModel", "Error fetching owner data", e)
            }
        }
    }
    // Fetch Vehicle data by ID

    fun getVehicleByRegNo(regNo: String, context: Context) {
        viewModelScope.launch {
            try {
                val response = VehicleRetrofitInstance.vehicleAPI.getVehicleByRegNo(regNo)
                if (response.isSuccessful) {
                    _vehicleData.value = response.body()
                    Toast.makeText(context,"Vehicle details fetched successfully", Toast.LENGTH_LONG).show()
                    Log.d("VehicleViewModel", "Vehicle details fetched successfully")
                } else {
                    _vehicleData.value = null
                    Log.e("VehicleViewModel", "Failed to vehicle data : ${response.message()}")
                }
            } catch (e : Exception) {
                Log.e("VehicleViewModel", "Error fetching vehicle data", e)
            }
        }
    }

    fun addVehicle(regNo: String,addVehicle: VehicleDataClassItem, context: Context) {
        viewModelScope.launch {
            try {
                // Log the data being sent
                Log.d("VehicleViewModel", "Sending vehicle data: $addVehicle")

                val response = VehicleRetrofitInstance.vehicleAPI.addVehicle(regNo,addVehicle)
                if (response.isSuccessful) {
                    Toast.makeText(context, "Vehicle details added successfully", Toast.LENGTH_LONG).show()
                    Log.d("VehicleViewModel", "Vehicle details added successfully")
                } else {
                    Log.e("VehicleViewModel", "Failed to add vehicle data: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("VehicleViewModel", "Error adding vehicle data", e)
            }
        }
    }


    fun updateVehicle(updateVehicle : VehicleDataClassItem,regNo: String, context: Context) {
        viewModelScope.launch {
            try {
                val response = VehicleRetrofitInstance.vehicleAPI.updateVehicle(regNo,updateVehicle)
                if (response.isSuccessful) {
                    Toast.makeText(context,"Vehicle details updated successfully", Toast.LENGTH_LONG).show()
                    Log.d("VehicleViewModel", "Vehicle details updated successfully")
                } else {
                    Log.e("VehicleViewModel", "Failed to update vehicle data : ${response.message()}")
                }
            } catch (e : Exception) {
                Log.e("VehicleViewModel", "Error updating vehicle data", e)
            }
        }
    }

    fun deleteVehicle(regNo: String, context: Context) {
        viewModelScope.launch {
            try {
                val response = VehicleRetrofitInstance.vehicleAPI.deleteVehicle(regNo)
                if (response.isSuccessful) {
                    Toast.makeText(context,"Vehicle details deleted successfully", Toast.LENGTH_LONG).show()
                    Log.d("VehicleViewModel", "Vehicle details deleted successfully")
                } else {
                    Log.e("VehicleViewModel", "Failed to delete vehicle data : ${response.message()}")
                }
            } catch (e : Exception) {
                Log.e("VehicleViewModel", "Error deleting vehicle data", e)
            }
        }
    }
}



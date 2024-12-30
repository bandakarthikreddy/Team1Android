package com.singlepointsol.insurancepolicy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val AGENT_BASE_URL = "https://abzagentwebapi-chana.azurewebsites.net/"
    private const val VEHICLE_BASE_URL = "https://abzvehiclewebapi-chana.azurewebsites.net/"

    private fun createRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val agentApi: AgentApi = createRetrofit(AGENT_BASE_URL).create(AgentApi::class.java)
    val vehicleApi: VehicleApi = createRetrofit(VEHICLE_BASE_URL).create(VehicleApi::class.java)
}

class Repository {
    private val agentApi = RetrofitClient.agentApi
    private val vehicleApi = RetrofitClient.vehicleApi

    suspend fun saveAgent(agent: Agent) = withContext(Dispatchers.IO) {
        agentApi.createAgent(agent)
    }

    suspend fun updateAgent(agent: Agent) = withContext(Dispatchers.IO) {
        agentApi.updateAgent(agent)
    }

    suspend fun deleteAgent(agent: Agent) = withContext(Dispatchers.IO) {
        agentApi.deleteAgent(agent)
    }

    suspend fun getAgents() = withContext(Dispatchers.IO) {
        agentApi.getAgents()
    }

    suspend fun saveVehicle(vehicle: VehicleData) = withContext(Dispatchers.IO) {
        vehicleApi.createVehicle(vehicle)
    }

    suspend fun updateVehicle(vehicle: VehicleData) = withContext(Dispatchers.IO) {
        vehicleApi.updateVehicle(vehicle)
    }

    suspend fun deleteVehicle(vehicle: VehicleData) = withContext(Dispatchers.IO) {
        vehicleApi.deleteVehicle(vehicle)
    }

    suspend fun getVehicles() = withContext(Dispatchers.IO) {
        vehicleApi.getVehicles()
    }
}

class InsuranceViewModel(private val repository: Repository) : ViewModel() {
    private val _agentState = MutableStateFlow<Agent?>(null)
    val agentState: StateFlow<Agent?> = _agentState

    private val _vehicleState = MutableStateFlow<VehicleData?>(null)
    val vehicleState: StateFlow<VehicleData?> = _vehicleState

    fun saveAgent(agent: Agent) {
        viewModelScope.launch {
            try {
                repository.saveAgent(agent)
                _agentState.value = agent
            } catch (_: Exception) {
            }
        }
    }

    fun updateAgent(agent: Agent) {
        viewModelScope.launch {
            try {
                repository.updateAgent(agent)
                _agentState.value = agent
            } catch (_: Exception) {
            }
        }
    }

    fun deleteAgent(agent: Agent) {
        viewModelScope.launch {
            try {
                repository.deleteAgent(agent)
                _agentState.value = null
            } catch (_: Exception) {
            }
        }
    }

    fun saveVehicle(vehicle: VehicleData) {
        viewModelScope.launch {
            try {
                repository.saveVehicle(vehicle)
                _vehicleState.value = vehicle
            } catch (_: Exception) {
            }
        }
    }

    fun updateVehicle(vehicle: VehicleData) {
        viewModelScope.launch {
            try {
                repository.updateVehicle(vehicle)
                _vehicleState.value = vehicle
            } catch (_: Exception) {
            }
        }
    }

    fun deleteVehicle(vehicle: VehicleData) {
        viewModelScope.launch {
            try {
                repository.deleteVehicle(vehicle)
                _vehicleState.value = null
            } catch (_: Exception) {
            }
        }
    }
}

@Composable
fun AgentFormScreen(
    viewModel: InsuranceViewModel,
    modifier: Modifier = Modifier
) {
    val agentID by remember { mutableStateOf("") }
    val agentName by remember { mutableStateOf("") }
    val agentPhone by remember { mutableStateOf("") }
    val agentEmail by remember { mutableStateOf("") }
    val licenseCode by remember { mutableStateOf("") }

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Row {
            Button(
                onClick = {
                    viewModel.saveAgent(
                        Agent(
                            id = agentID,
                            name = agentName,
                            phone = agentPhone,
                            email = agentEmail,
                            licenseCode = licenseCode
                        )
                    )
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Save")
            }

            Button(
                onClick = {
                    viewModel.updateAgent(
                        Agent(
                            id = agentID,
                            name = agentName,
                            phone = agentPhone,
                            email = agentEmail,
                            licenseCode = licenseCode
                        )
                    )
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Update")
            }

            Button(
                onClick = {
                    viewModel.deleteAgent(
                        Agent(
                            id = agentID,
                            name = agentName,
                            phone = agentPhone,
                            email = agentEmail,
                            licenseCode = licenseCode
                        )
                    )
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Delete")
            }
        }
    }
}

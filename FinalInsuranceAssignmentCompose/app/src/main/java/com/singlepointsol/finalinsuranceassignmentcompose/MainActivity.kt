package com.singlepointsol.finalinsuranceassignmentcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.singlepointsol.finalinsuranceassignmentcompose.ui.theme.FinalInsuranceAssignmentComposeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


data class ProposalFormState(
    val proposalNo: String = "",
    val regNo: String = "",
    val productId: String = "",
    val customerId: String = "",
    val fromDate: String = "",
    val toDate: String = "",
    val idv: String = "",
    val agentId: String = "",
    val basicAmount: String = "",
    val totalAmount: String = ""
)

data class CustomerFormState(
    val customerId: String = "",
    val customerName: String = "",
    val customerPhone: String = "",
    val customerEmail: String = "",
    val customerAddress: String = ""
)

data class ProductAddonFormState(
    val productId: String = "",
    val addonId: String = "",
    val addonTitle: String = "",
    val addonDescription: String = ""
)
// API Interfaces with CRUD operations
interface CustomerApiService {
    @POST("api/customers")
    suspend fun createCustomer(@Body customer: CustomerFormState)

    @GET("api/customers/{id}")
    suspend fun getCustomer(@Path("id") id: String)

    @PUT("api/customers/{id}")
    suspend fun updateCustomer(@Path("id") id: String, @Body customer: CustomerFormState)

    @DELETE("api/customers/{id}")
    suspend fun deleteCustomer(@Path("id") id: String)
}

interface ProposalApiService {
    @POST("api/proposals")
    suspend fun createProposal(@Body proposal: ProposalFormState)

    @GET("api/proposals/{id}")
    suspend fun getProposal(@Path("id") id: String)

    @PUT("api/proposals/{id}")
    suspend fun updateProposal(@Path("id") id: String, @Body proposal: ProposalFormState)

    @DELETE("api/proposals/{id}")
    suspend fun deleteProposal(@Path("id") id: String)
}

interface ProductAddonApiService {
    @POST("api/products/addons")
    suspend fun createProductAddon(@Body addon: ProductAddonFormState)

    @GET("api/products/addons/{id}")
    suspend fun getProductAddon(@Path("id") id: String)

    @PUT("api/products/addons/{id}")
    suspend fun updateProductAddon(@Path("id") id: String, @Body addon: ProductAddonFormState)

    @DELETE("api/products/addons/{id}")
    suspend fun deleteProductAddon(@Path("id") id: String)
}

// API Response
data class ApiResponse(
    val message: String,
    val status: String
)

// API Client
object ApiClient {
    private val customerRetrofit = Retrofit.Builder()
        .baseUrl("https://abzcustomerwebapi-chana.azurewebsites.net/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val proposalRetrofit = Retrofit.Builder()
        .baseUrl("https://abzproposalwebapi-chana.azurewebsites.net/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val customerService: CustomerApiService = customerRetrofit.create(CustomerApiService::class.java)
    val proposalService: ProposalApiService = proposalRetrofit.create(ProposalApiService::class.java)
    val productAddonService: ProductAddonApiService = proposalRetrofit.create(ProductAddonApiService::class.java)
}
class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _currentMode = MutableStateFlow<CrudMode>(CrudMode.CREATE)
    val currentMode: StateFlow<CrudMode> = _currentMode.asStateFlow()

    // Customer operations
    fun submitCustomerForm(customerData: CustomerFormState, mode: CrudMode) {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                val response = when (mode) {
                    CrudMode.CREATE -> ApiClient.customerService.createCustomer(customerData)
                    CrudMode.UPDATE -> ApiClient.customerService.updateCustomer(customerData.customerId, customerData)
                    CrudMode.DELETE -> ApiClient.customerService.deleteCustomer(customerData.customerId)
                    CrudMode.EDIT -> return@launch
                }

                if (response.isSuccessful) {
                    _uiState.value = UiState.Success("Customer ${mode.toString().lowercase()} successful")
                }
                else {
                    _uiState.value = UiState.Error("Failed to ${mode.toString().lowercase()} customer")
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }

    fun getCustomer(id: String) {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                val response = ApiClient.customerService.getCustomer(id)
                if (response.isSuccessful) {
                    _uiState.value = UiState.Success("Customer fetched successfully")
                    // Handle the fetched data
                } else {
                    _uiState.value = UiState.Error("Failed to fetch customer")
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }

    // Similar implementations for Proposal and ProductAddon...

    fun setMode(mode: CrudMode) {
        _currentMode.value = mode
    }

    fun clearState() {
        _uiState.value = UiState.Idle
    }
}

enum class CrudMode {
    CREATE, EDIT, UPDATE, DELETE
}
// UI State
sealed class UiState {
    object Idle : UiState()
    object Loading : UiState()
    data class Success(val message: String) : UiState()
    data class Error(val message: String) : UiState()
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinalInsuranceAssignmentComposeTheme {
                Surface {
                    FormScreenPreview()
                }
            }
        }
    }
}
@Preview
@Composable
fun FormScreenPreview() {
    FormScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProposalForm(
    onSubmit: (ProposalFormState) -> Unit,
    mode: CrudMode
) {
    var formState by remember { mutableStateOf(ProposalFormState()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = formState.proposalNo,
            onValueChange = {
                val it = null
                formState = it?.let { it1 -> formState.copy(proposalNo = it1) }!!
            },
            label = { Text("Proposal No") },
            modifier = Modifier.fillMaxWidth()
        )

        // Dropdown for RegNo
        ExposedDropdownMenuBox(
            expanded = false,
            onExpandedChange = {},
        ) {
            OutlinedTextField(
                value = formState.regNo,
                onValueChange = { formState = formState.copy(regNo = it) },
                label = { Text("Reg No") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Date picker fields
        OutlinedTextField(
            value = formState.fromDate,
            onValueChange = { formState = formState.copy(fromDate = it) },
            label = { Text("From Date") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = formState.toDate,
            onValueChange = { formState = formState.copy(toDate = it) },
            label = { Text("To Date") },
            modifier = Modifier.fillMaxWidth()
        )

        // Number fields with decimal keyboard
        OutlinedTextField(
            value = formState.idv,
            onValueChange = { formState = formState.copy(idv = it) },
            label = { Text("IDV") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = formState.basicAmount,
            onValueChange = { formState = formState.copy(basicAmount = it) },
            label = { Text("Basic Amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { onSubmit(formState) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit Proposal")
        }
    }
}

@Composable
fun CustomerForm(
    onSubmit: (CustomerFormState) -> Unit
) {
    var formState by remember { mutableStateOf(CustomerFormState()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = formState.customerId,
            onValueChange = { formState = formState.copy(customerId = it) },
            label = { Text("Customer ID") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = formState.customerName,
            onValueChange = { formState = formState.copy(customerName = it) },
            label = { Text("Customer Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = formState.customerPhone,
            onValueChange = { formState = formState.copy(customerPhone = it) },
            label = { Text("Phone") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = formState.customerEmail,
            onValueChange = { formState = formState.copy(customerEmail = it) },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = formState.customerAddress,
            onValueChange = { formState = formState.copy(customerAddress = it) },
            label = { Text("Address") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )

        Button(
            onClick = { onSubmit(formState) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit Customer Details")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductAddonForm(
    onSubmit: (ProductAddonFormState) -> Unit,
    mode: CrudMode
) {
    var formState by remember { mutableStateOf(ProductAddonFormState()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Dropdown for ProductID
        ExposedDropdownMenuBox(
            expanded = false,
            onExpandedChange = {},
        ) {
            OutlinedTextField(
                value = formState.productId,
                onValueChange = { formState = formState.copy(productId = it) },
                label = { Text("Product ID") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Dropdown for AddonID
        ExposedDropdownMenuBox(
            expanded = false,
            onExpandedChange = {},
        ) {
            OutlinedTextField(
                value = formState.addonId,
                onValueChange = { formState = formState.copy(addonId = it) },
                label = { Text("Addon ID") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        OutlinedTextField(
            value = formState.addonTitle,
            onValueChange = { formState = formState.copy(addonTitle = it) },
            label = { Text("Addon Title") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = formState.addonDescription,
            onValueChange = { formState = formState.copy(addonDescription = it) },
            label = { Text("Addon Description") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )

        Button(
            onClick = { onSubmit(formState) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit Product Addon")
        }
    }
}

// Example usage

@Composable
fun FormScreen(viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    var selectedTab by remember { mutableStateOf(0) }
    val uiState by viewModel.uiState.collectAsState()
    val currentMode by viewModel.currentMode.collectAsState()

    Column {
        // Status indicators
        when (val state = uiState) {
            is UiState.Loading -> {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
            is UiState.Success -> {
                Text(
                    text = state.message,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(16.dp)
                )
                LaunchedEffect(state) {
                    delay(3000)
                    viewModel.clearState()
                }
            }
            is UiState.Error -> {
                Text(
                    text = state.message,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            }
            else -> {}
        }

        // CRUD Mode buttons
        CrudModeButtons(
            currentMode = currentMode,
            onModeSelect = { viewModel.setMode(it) }
        )

        // Tabs
        TabRow(selectedTabIndex = selectedTab) {
            Tab(selected = selectedTab == 0, onClick = { selectedTab = 0 }) {
                Text("Proposal")
            }
            Tab(selected = selectedTab == 1, onClick = { selectedTab = 1 }) {
                Text("Customer")
            }
            Tab(selected = selectedTab == 2, onClick = { selectedTab = 2 }) {
                Text("Product Addon")
            }
        }

        // Forms
        when (selectedTab) {
            0 -> ProposalForm(
                mode = currentMode,
                onSubmit = { proposalData ->
                    viewModel.submitProposalForm(proposalData, currentMode)
                }
            )
            1 -> CustomerForm(
                mode = currentMode,
                onSubmit = { customerData ->
                    viewModel.submitCustomerForm(customerData, currentMode)
                }
            )
            2 -> ProductAddonForm(
                mode = currentMode,
                onSubmit = { addonData ->
                    viewModel.submitProductAddonForm(addonData, currentMode)
                }
            )
        }
    }
}

@Composable
fun CrudModeButtons(
    currentMode: CrudMode,
    onModeSelect: (CrudMode) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        CrudMode.values().forEach { mode ->
            Button(
                onClick = { onModeSelect(mode) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (currentMode == mode)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.secondary
                )
            ) {
                Text(mode.toString())
            }
        }
    }
}

// Example of updated form to handle CRUD modes
@Composable
fun CustomerForm(
    mode: CrudMode,
    onSubmit: (CustomerFormState) -> Unit
) {
    var formState by remember { mutableStateOf(CustomerFormState()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // ID field (visible in all modes except CREATE)
        if (mode != CrudMode.CREATE) {
            OutlinedTextField(
                value = formState.customerId,
                onValueChange = { formState = formState.copy(customerId = it) },
                label = { Text("Customer ID") },
                modifier = Modifier.fillMaxWidth(),
                enabled = mode == CrudMode.EDIT || mode == CrudMode.DELETE
            )
        }

        // Other fields (disabled in DELETE mode)
        OutlinedTextField(
            value = formState.customerName,
            onValueChange = { formState = formState.copy(customerName = it) },
            label = { Text("Customer Name") },
            modifier = Modifier.fillMaxWidth(),
            enabled = mode != CrudMode.DELETE
        )

        OutlinedTextField(
            value = formState.customerPhone,
            onValueChange = { formState = formState.copy(customerPhone = it) },
            label = { Text("Phone") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth(),
            enabled = mode != CrudMode.DELETE
        )

        OutlinedTextField(
            value = formState.customerEmail,
            onValueChange = { formState = formState.copy(customerEmail = it) },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth(),
            enabled = mode != CrudMode.DELETE
        )

        OutlinedTextField(
            value = formState.customerAddress,
            onValueChange = { formState = formState.copy(customerAddress = it) },
            label = { Text("Address") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3,
            enabled = mode != CrudMode.DELETE
        )

        Button(
            onClick = { onSubmit(formState) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(when (mode) {
                CrudMode.CREATE -> "Add Customer"
                CrudMode.EDIT -> "Edit Customer"
                CrudMode.UPDATE -> "Update Customer"
                CrudMode.DELETE -> "Delete Customer"
            })
        }
    }
}
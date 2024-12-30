package com.singlepointsol.abzinsurance.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.singlepointsol.abzinsurance.dataclass.CustomerDataClassItem
import com.singlepointsol.abzinsurance.form.CustomerForm
import com.singlepointsol.abzinsurance.viewmodel.CustomerViewModel


@Composable
fun CustomerPage(
    modifier: Modifier = Modifier,
    viewModel: CustomerViewModel
) {
    var formState by remember { mutableStateOf(CustomerForm()) }
    val customerData by viewModel.customerData.collectAsState() // Observe ViewModel's StateFlow

    // Update the formState when new customer data is fetched
    LaunchedEffect(customerData) {
        customerData?.let {
            formState = formState.copy(
                customerID = it.customerID,
                customerName = it.customerName,
                customerPhone = it.customerPhone,
                customerEmail = it.customerEmail,
                customerAddress = it.customerAddress
            )
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // LazyColumn for Input Fields
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                OutlinedTextField(
                    value = formState.customerID,
                    onValueChange = { formState = formState.copy(customerID = it) },
                    label = { Text("Customer ID") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                OutlinedTextField(
                    value = formState.customerName,
                    onValueChange = { formState = formState.copy(customerName = it) },
                    label = { Text("Customer Name") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                OutlinedTextField(
                    value = formState.customerPhone,
                    onValueChange = { formState = formState.copy(customerPhone = it) },
                    label = { Text("Phone") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                OutlinedTextField(
                    value = formState.customerEmail,
                    onValueChange = { formState = formState.copy(customerEmail = it) },
                    label = { Text("Email") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                OutlinedTextField(
                    value = formState.customerAddress,
                    onValueChange = { formState = formState.copy(customerAddress = it) },
                    label = { Text("Address") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3
                )
            }
        }

        // LazyRow for Buttons
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            item {
                Button(
                    onClick = {
                        val newCustomer = CustomerDataClassItem(
                            customerID = formState.customerID,
                            customerName = formState.customerName,
                            customerPhone = formState.customerPhone,
                            customerEmail = formState.customerEmail,
                            customerAddress = formState.customerAddress
                        )
                        viewModel.addNewCustomer(formState.customerID,newCustomer)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text("ADD")
                }
            }
            item {
                Button(
                    onClick = {
                        if (formState.customerID.isNotEmpty()) {
                            viewModel.fetchCustomerDataById(formState.customerID)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text("FETCH")
                }
            }
            item {
                Button(
                    onClick = {
                        if (formState.customerID.isNotEmpty()) {
                            val updatedCustomer = CustomerDataClassItem(
                                customerID = formState.customerID,
                                customerName = formState.customerName,
                                customerPhone = formState.customerPhone,
                                customerEmail = formState.customerEmail,
                                customerAddress = formState.customerAddress
                            )
                            viewModel.updateCustomerData(updatedCustomer)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text("UPDATE")
                }
            }
            item {
                Button(
                    onClick = {
                        if (formState.customerID.isNotEmpty()) {
                            viewModel.deleteCustomerData(formState.customerID)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text("DELETE")
                }
            }
        }
    }
}

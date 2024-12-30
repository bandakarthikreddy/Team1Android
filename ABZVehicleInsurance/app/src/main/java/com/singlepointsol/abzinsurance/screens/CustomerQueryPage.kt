package com.singlepointsol.abzinsurance.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.singlepointsol.abzinsurance.components.buttonTextFieldStyle
import com.singlepointsol.abzinsurance.components.textFieldStyle
import com.singlepointsol.abzinsurance.dataclass.CustomerQueryDataClassItem
import com.singlepointsol.abzinsurance.form.CustomerQueryForm
import com.singlepointsol.abzinsurance.viewmodel.CustomerQueryViewModel

@Composable
fun CustomerQueryPage(modifier: Modifier, viewModel: CustomerQueryViewModel) {

    var form by remember { mutableStateOf(CustomerQueryForm()) }
    val customerQueryData by viewModel.customerQueryData.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(customerQueryData) {
        customerQueryData?.let {
            form = form.copy(
                queryID = it.queryID,
                customerID = it.customerID,
                description = it.description,
                queryDate = it.queryDate,
                status = it.status
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            item {
                OutlinedTextField(
                    value = form.queryID,
                    onValueChange = { form = form.copy(queryID = it) },
                    label = {
                        Text("Query ID", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.customerID,
                    onValueChange = { form = form.copy(customerID = it) },
                    label = {
                        Text("Customer ID", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.description,
                    onValueChange = { form = form.copy(description = it) },
                    label = {
                        Text("Description", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.queryDate,
                    onValueChange = { form = form.copy(queryDate = it) },
                    label = {
                        Text("Query Date", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.status,
                    onValueChange = { form = form.copy(status = it) },
                    label = {
                        Text("Status", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier
                    .height(8.dp))
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp ),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            LazyRow (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                item {
                    Button(onClick = {
                        if (form.queryID.isNotEmpty()) {
                            val addCustomerQuery = CustomerQueryDataClassItem(
                                queryID = form.queryID,
                                customerID = form.customerID,
                                description = form.description,
                                queryDate = form.queryDate,
                                status = form.status
                            )
                            viewModel.addCustomerQuery(form.queryID, addCustomerQuery, context)
                        }
                    },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text("ADD", style = buttonTextFieldStyle())
                    }
                }

                item {
                    Button(onClick = {
                        if (form.queryID.isNotEmpty()) {
                            viewModel.getCustomerQueryById(form.queryID, context)
                        }
                    },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text("FETCH", style = buttonTextFieldStyle()
                        )
                    }
                }

                item {
                    Button(onClick = {
                        if (form.queryID.isNotEmpty()) {
                            val updatedCustomerQuery = CustomerQueryDataClassItem(
                                queryID = form.queryID,
                                customerID = form.customerID,
                                description = form.description,
                                queryDate = form.queryDate,
                                status = form.status
                            )
                            viewModel.updateCustomerQuery(updatedCustomerQuery, form.queryID, context)
                        }
                    },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text("UPDATE", style = buttonTextFieldStyle())
                    }
                }

                item {
                    Button(onClick = {
                        if (form.queryID.isNotEmpty()) {
                            viewModel.deleteCustomerQuery(form.queryID, context)
                        }
                    },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text("DELETE", style = buttonTextFieldStyle())
                    }
                }
            }
        }
    }
}




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
import com.singlepointsol.abzinsurance.dataclass.QueryResponseDataClassItem
import com.singlepointsol.abzinsurance.form.QueryResponseForm
import com.singlepointsol.abzinsurance.viewmodel.QueryResponseViewModel

@Composable
fun QueryResponsePage(modifier: Modifier, viewModel: QueryResponseViewModel) {
    var form by remember { mutableStateOf(QueryResponseForm()) }
    val queryResponseData by viewModel.queryResponseData.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(queryResponseData) {
        queryResponseData?.let {
            form = form.copy(
                queryID = it.queryID,
                srNo = it.srNo,
                agentID = it.agentID,
                description = it.description,
                responseDate = it.responseDate
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
                    value = form.srNo,
                    onValueChange = { form = form.copy(srNo = it) },
                    label = {
                        Text("Sr No", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))


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
                    value = form.agentID,
                    onValueChange = { form = form.copy(agentID = it) },
                    label = {
                        Text("Agent ID", style = textFieldStyle())
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
                    value = form.responseDate,
                    onValueChange = { form = form.copy(responseDate = it) },
                    label = {
                        Text("Response Date", style = textFieldStyle())
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
                .padding(horizontal = 8.dp )
        ) {
            LazyRow (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                item {
                    Button(onClick = {
                        if (form.queryID.isNotEmpty()) {
                            val addCustomerQuery = QueryResponseDataClassItem(
                                srNo = form.srNo,
                                queryID = form.queryID,
                                agentID = form.agentID,
                                description = form.description,
                                responseDate = form.responseDate
                            )
                            viewModel.addQueryResponse(  addCustomerQuery, context)
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
                            viewModel.getQueryResponseById(form.queryID,form.srNo, context)
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
                            val updatedCustomerQuery = QueryResponseDataClassItem(
                                srNo = form.srNo,
                                queryID = form.queryID,
                                agentID = form.agentID,
                                description = form.description,
                                responseDate = form.responseDate
                            )
                            viewModel.updateQueryResponse(form.queryID, form.srNo, updatedCustomerQuery, context)
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
                            viewModel.deleteQueryResponse(form.queryID,form.srNo, context)
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

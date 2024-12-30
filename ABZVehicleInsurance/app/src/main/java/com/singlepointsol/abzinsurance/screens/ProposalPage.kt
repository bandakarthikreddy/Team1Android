package com.singlepointsol.abzinsurance.screens

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
import com.singlepointsol.abzinsurance.dataclass.ProposalDataClassItem
import com.singlepointsol.abzinsurance.form.ProposalForm
import com.singlepointsol.abzinsurance.viewmodel.ProposalViewModel

@Composable
fun ProposalPage(modifier: Modifier, viewModel: ProposalViewModel) {
    var form by remember { mutableStateOf(ProposalForm()) }
    val proposalData by viewModel.proposalData.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(proposalData) {
        proposalData?.let {
            form = form.copy(
                proposalNo = it.proposalNo,
                regNo = it.regNo,
                productID = it.productID,
                customerID = it.customerID,
                fromDate = it.fromDate,
                toDate = it.toDate,
                idv = it.idv,
                agentID = it.agentID,
                basicAmount = it.basicAmount,
                totalAmount = it.totalAmount
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .windowInsetsPadding(WindowInsets.systemBars)
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            item {
                OutlinedTextField(
                    value = form.proposalNo,
                    onValueChange = { form = form.copy(proposalNo = it)},
                    label = {
                        Text("Proposal ID", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.regNo,
                    onValueChange = { form = form.copy(regNo = it)},
                    label = {
                        Text("Registration Number", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))


                OutlinedTextField(
                    value = form.productID,
                    onValueChange = { form = form.copy(productID = it)},
                    label = {
                        Text("Product ID", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.customerID,
                    onValueChange = { form = form.copy(customerID = it)},
                    label = {
                        Text("Customer ID", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.fromDate,
                    onValueChange = { form = form.copy(fromDate = it)},
                    label = {
                        Text("From Date", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.toDate,
                    onValueChange = { form = form.copy(toDate = it)},
                    label = {
                        Text("To Date", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.idv,
                    onValueChange = { form = form.copy(idv = it)},
                    label = {
                        Text("IDV", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.agentID,
                    onValueChange = { form = form.copy(agentID = it)},
                    label = {
                        Text("Agent ID", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.basicAmount,
                    onValueChange = { form = form.copy(basicAmount = it)},
                    label = {
                        Text("Basic Amount", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.totalAmount,
                    onValueChange = { form = form.copy(totalAmount = it)},
                    label = {
                        Text("Total Amount", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        Row(
            modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
        ) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                item {
                    Button(onClick = {
                        val newProposal = ProposalDataClassItem(

                            proposalNo = form.proposalNo,
                            regNo = form.regNo,
                            productID = form.productID,
                            customerID = form.customerID,
                            fromDate = form.fromDate,
                            toDate = form.toDate,
                            idv = form.idv,
                            agentID = form.agentID,
                            basicAmount = form.basicAmount,
                            totalAmount = form.totalAmount,

                        )
                        viewModel.addProposal(form.proposalNo, newProposal, context)
                    },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    ) {
                        Text("ADD", style = buttonTextFieldStyle())
                    }
                }

                item {
                    Button(onClick = {
                        if (form.proposalNo.isNotEmpty()) {
                            viewModel.getProposalByID(form.proposalNo, context)
                        }
                    },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    ) {
                        Text("FETCH", style = buttonTextFieldStyle())
                    }
                }

                item {
                    Button(onClick = {
                        if (form.proposalNo.isNotEmpty()) {
                            val updatedProposal = ProposalDataClassItem(
                                proposalNo = form.proposalNo,
                                regNo = form.regNo,
                                productID = form.productID,
                                customerID = form.customerID,
                                fromDate = form.fromDate,
                                toDate = form.toDate,
                                idv = form.idv,
                                agentID = form.agentID,
                                basicAmount = form.basicAmount,
                                totalAmount = form.totalAmount,
                            )
                            viewModel.updateProposal(updatedProposal,form.proposalNo,context)
                        }
                    },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    ) {
                        Text("UPDATE", style = buttonTextFieldStyle())
                    }
                }

                item {
                    Button(onClick = {
                        if(form.proposalNo.isNotEmpty()) {
                            viewModel.deleteProposal(form.proposalNo,context)
                        }
                    },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    ) {
                        Text("DELETE", style = buttonTextFieldStyle())
                    }
                }
            }
        }

    }

}
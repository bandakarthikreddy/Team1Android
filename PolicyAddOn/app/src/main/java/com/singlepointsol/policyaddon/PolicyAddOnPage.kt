package com.singlepointsol.policyaddon

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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

@Composable
fun PolicyAddOnPage(modifier: Modifier, viewModel: PolicyAddOnViewModel) {
    var form by  remember { mutableStateOf(PolicyAddOnForm()) }
    val policyAddOnData by viewModel.policyAddOnData.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(policyAddOnData) {
        policyAddOnData?.let {
            form = form.copy(
                addonID = it.addonID,
                policyNo = it.policyNo,
                amount = it.amount
            )
        }
    }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
                LazyColumn(modifier = Modifier
                    .fillMaxWidth()
                ) {
                    item {
                        OutlinedTextField(
                            value = form.addonID,
                            onValueChange = { form = form.copy(addonID = it)},
                            label = {
                                Text("Addon ID", style = textFieldStyle())
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = form.policyNo,
                            onValueChange = { form = form.copy(policyNo = it)},
                            label = {
                                Text("Policy Number", style = textFieldStyle())
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = form.amount,
                            onValueChange = { form = form.copy(amount = it)},
                            label = {
                                Text("Amount", style = textFieldStyle())
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
                            if (form.policyNo.isNotEmpty()) {
                                val newPolicyAddOn = PolicyAddOnDataClassItem(
                                    addonID = form.addonID,
                                    policyNo = form.policyNo,
                                    amount = form.amount
                                )
                                viewModel.addPolicyAddOn(form.policyNo,context)
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
                            if (form.policyNo.isNotEmpty()) {
                                viewModel.getPolicyAddOnByAddOnNo(form.policyNo, context)
                            }
                        },
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text("FETCH", style = buttonTextFieldStyle())
                        }
                    }
                    item {
                        Button(onClick = {
                            if(form.policyNo.isNotEmpty()) {
                                val updatePolicyAddOn = PolicyAddOnDataClassItem(
                                    addonID = form.addonID,
                                    policyNo = form.policyNo,
                                    amount = form.amount
                                )
                                viewModel.updatePolicyAddOn(updatePolicyAddOn, form.addonID, context)
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
                            if (form.policyNo.isNotEmpty()) {
                                viewModel.deletePolicyAddOn(form.policyNo, context)
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
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
import com.singlepointsol.abzinsurance.dataclass.PolicyAddonDataClassItem
import com.singlepointsol.abzinsurance.form.PolicyAddonForm
import com.singlepointsol.abzinsurance.viewmodel.PolicyAddonViewModel

@Composable
fun PolicyAddOnPage(modifier: Modifier, viewModel: PolicyAddonViewModel) {
    var form by remember { mutableStateOf(PolicyAddonForm()) }
    val policyAddOnData by viewModel.policyAddonData.collectAsState()
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
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            item {


                OutlinedTextField(
                    value = form.addonID,
                    onValueChange = { form = form.copy(addonID = it) },
                    label = {
                        Text("Addon ID", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.policyNo,
                    onValueChange = { form = form.copy(policyNo = it) },
                    label = {
                        Text("Policy Number", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.amount,
                    onValueChange = { form = form.copy(amount = it) },
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
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                item {
                    Button(
                        onClick = {
                            if (form.addonID.isNotEmpty() && form.policyNo.isNotEmpty()) {
                                val newPolicyAddon = PolicyAddonDataClassItem(
                                    addonID = form.addonID,
                                    policyNo = form.policyNo,
                                    amount = form.amount
                                )
                                viewModel.addPolicyAddon(form.policyNo, form.addonID, newPolicyAddon, context)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text("ADD", style = buttonTextFieldStyle())
                    }
                }

                item {
                    Button(
                        onClick = {
                            if (form.addonID.isNotEmpty() && form.policyNo.isNotEmpty()) {
                                viewModel.getPolicyAddonByID(form.policyNo, form.addonID, context)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text("FETCH", style = buttonTextFieldStyle())
                    }
                }
                item {
                    Button(
                        onClick = {
                            if (form.policyNo.isNotEmpty() && form.policyNo.isNotEmpty()) {
                                val updatePolicyAddOn = PolicyAddonDataClassItem(
                                    addonID = form.addonID,
                                    policyNo = form.policyNo,
                                    amount = form.amount
                                )
                                viewModel.updatePolicyAddon(form.policyNo, form.addonID, updatePolicyAddOn, context)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text("UPDATE", style = buttonTextFieldStyle())
                    }
                }
                item {
                    Button(
                        onClick = {
                            if (form.addonID.isNotEmpty()) {
                                viewModel.deletePolicyAddon(form.policyNo, form.addonID, context)
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
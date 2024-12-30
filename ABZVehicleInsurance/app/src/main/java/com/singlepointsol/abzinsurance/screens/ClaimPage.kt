package com.singlepointsol.abzinsurance.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.singlepointsol.abzinsurance.components.buttonTextFieldStyle
import com.singlepointsol.abzinsurance.components.textFieldStyle
import com.singlepointsol.abzinsurance.dataclass.ClaimDataClassItem
import com.singlepointsol.abzinsurance.form.ClaimForm
import com.singlepointsol.abzinsurance.viewmodel.ClaimViewModel

@Composable
fun ClaimPage(modifier: Modifier, viewModel: ClaimViewModel) {
    var form by remember { mutableStateOf(ClaimForm()) }
    val claimData by viewModel.claimData.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(claimData) {
        claimData?.let {
            form = form.copy(
                claimNo = it.claimNo,
                claimDate = it.claimDate,
                policyNo = it.policyNo,
                incidentDate = it.incidentDate,
                incidentLocation = it.incidentLocation,
                incidentDescription = it.incidentDescription,
                claimAmount = it.claimAmount,
                surveyorName = it.surveyorName,
                surveyorPhone = it.surveyorPhone,
                surveyorDate = it.surveyDate,
                surveyorDescription = it.surveyDescription,
                claimStatus = it.claimStatus
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Share space with buttons
        ) {
            item {
                OutlinedTextField(
                    value = form.claimNo,
                    onValueChange = { form = form.copy(claimNo = it) },
                    label = { Text("Claim Number", style = textFieldStyle()) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.claimDate,
                    onValueChange = { form = form.copy(claimDate = it) },
                    label = { Text("Claim Date", style = textFieldStyle()) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.policyNo,
                    onValueChange = { form = form.copy(policyNo = it) },
                    label = { Text("Policy Number", style = textFieldStyle()) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.incidentDate,
                    onValueChange = { form = form.copy(incidentDate = it) },
                    label = { Text("Incident Date", style = textFieldStyle()) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.incidentLocation,
                    onValueChange = { form = form.copy(incidentLocation = it) },
                    label = { Text("Incident Location", style = textFieldStyle()) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.incidentDescription,
                    onValueChange = { form = form.copy(incidentDescription = it) },
                    label = { Text("Incident Description", style = textFieldStyle()) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.claimAmount,
                    onValueChange = { form = form.copy(claimAmount = it) },
                    label = { Text("Claim Amount", style = textFieldStyle()) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.surveyorName,
                    onValueChange = { form = form.copy(surveyorName = it) },
                    label = { Text("Surveyor Name", style = textFieldStyle()) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.surveyorPhone,
                    onValueChange = { form = form.copy(surveyorPhone = it) },
                    label = { Text("Surveyor Phone", style = textFieldStyle()) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.surveyorDate,
                    onValueChange = { form = form.copy(surveyorDate = it) },
                    label = { Text("Surveyor Date", style = textFieldStyle()) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.surveyorDescription,
                    onValueChange = { form = form.copy(surveyorDescription = it) },
                    label = { Text("Surveyor Description", style = textFieldStyle()) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.claimStatus,
                    onValueChange = { form = form.copy(claimStatus = it) },
                    label = { Text("Claim Status", style = textFieldStyle()) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    if (form.claimNo.isNotEmpty()) {
                        val newClaim = ClaimDataClassItem(
                            claimNo = form.claimNo,
                            claimDate = form.claimDate,
                            policyNo = form.policyNo,
                            incidentDate = form.incidentDate,
                            incidentLocation = form.incidentLocation,
                            incidentDescription = form.incidentDescription,
                            claimAmount = form.claimAmount,
                            surveyorName = form.surveyorName,
                            surveyorPhone = form.surveyorPhone,
                            surveyDate = form.surveyorDate,
                            surveyDescription = form.surveyorDescription,
                            claimStatus = form.claimStatus
                        )
                        viewModel.addClaim(form.claimNo, newClaim, context)
                    }
                },
                modifier = Modifier.weight(1f)
                    .padding(horizontal = 4.dp)
            ) {
                Text("ADD", style = buttonTextFieldStyle())
            }

            Button(
                onClick = {
                    if (form.claimNo.isNotEmpty()) {
                        viewModel.getClaimByClaimNo(form.claimNo, context)
                    }
                },
                modifier = Modifier.weight(1f)
                    .padding(horizontal = 4.dp)
            ) {
                Text("FETCH", style = buttonTextFieldStyle())
            }

            Button(
                onClick = {
                    if (form.claimNo.isNotEmpty()) {
                        val updatedClaim = ClaimDataClassItem(
                            claimNo = form.claimNo,
                            claimDate = form.claimDate,
                            policyNo = form.policyNo,
                            incidentDate = form.incidentDate,
                            incidentLocation = form.incidentLocation,
                            incidentDescription = form.incidentDescription,
                            claimAmount = form.claimAmount,
                            surveyorName = form.surveyorName,
                            surveyorPhone = form.surveyorPhone,
                            surveyDate = form.surveyorDate,
                            surveyDescription = form.surveyorDescription,
                            claimStatus = form.claimStatus
                        )
                        viewModel.updateClaim(updatedClaim, form.claimNo, context)
                    }
                },
                modifier = Modifier.weight(1f)
                    .padding(horizontal = 4.dp)
            ) {
                Text("UPDATE", style = buttonTextFieldStyle())
            }

            Button(
                onClick = {
                    if (form.claimNo.isNotEmpty()) {
                        viewModel.deleteClaim(form.claimNo, context)
                    }
                },
                modifier = Modifier.weight(1f)
                    .padding(horizontal = 4.dp)
            ) {
                Text("DELETE", style = buttonTextFieldStyle())
            }
        }
    }
}

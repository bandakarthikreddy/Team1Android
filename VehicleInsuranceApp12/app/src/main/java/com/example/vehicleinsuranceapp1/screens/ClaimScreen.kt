package com.example.vehicleinsuranceapp1.ui.claim

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.vehicleinsuranceapp1.ui.components.SubmitButton
import com.example.vehicleinsuranceapp1.ui.components.DatePickerField
import com.example.vehicleinsuranceapp1.ui.components.TextInputField

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown

@Composable
fun ClaimScreen(navController: NavHostController) {
    var claimNo by remember { mutableStateOf(TextFieldValue("")) }
    var claimDate by remember { mutableStateOf("") }
    var policyNo by remember { mutableStateOf("") }
    val policyOptions = listOf("Policy1", "Policy2", "Policy3")
    var incidentDate by remember { mutableStateOf("") }
    var incidentLocation by remember { mutableStateOf(TextFieldValue("")) }
    var incidentDescription by remember { mutableStateOf(TextFieldValue("")) }
    var claimAmount by remember { mutableStateOf(TextFieldValue("")) }
    var surveyorName by remember { mutableStateOf(TextFieldValue("")) }
    var surveyorPhone by remember { mutableStateOf(TextFieldValue("")) }
    var surveyDate by remember { mutableStateOf("") }
    var surveyDescription by remember { mutableStateOf(TextFieldValue("")) }
    var claimStatus by remember { mutableStateOf("") }
    val claimStatusOptions = listOf("Pending", "Approved", "Rejected")

    var expandedPolicy by remember { mutableStateOf(false) }
    var expandedStatus by remember { mutableStateOf(false) }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(text = "File a Claim", style = MaterialTheme.typography.h5)


            TextInputField(label = "Claim No") { claimNo = TextFieldValue(it) }


            DatePickerField(label = "Claim Date") { date -> claimDate = date }


            TextField(
                value = policyNo,
                onValueChange = { policyNo = it },
                label = { Text("Policy No") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { expandedPolicy = !expandedPolicy }) {
                        Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Policy Dropdown")
                    }
                }
            )
            DropdownMenu(
                expanded = expandedPolicy,
                onDismissRequest = { expandedPolicy = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                policyOptions.forEach { option ->
                    DropdownMenuItem(onClick = {
                        policyNo = option
                        expandedPolicy = false
                    }) {
                        Text(text = option)
                    }
                }
            }


            DatePickerField(label = "Incident Date") { date -> incidentDate = date }


            TextInputField(label = "Incident Location") { incidentLocation = TextFieldValue(it) }


            TextInputField(label = "Incident Description") { incidentDescription = TextFieldValue(it) }


            TextInputField(label = "Claim Amount") { claimAmount = TextFieldValue(it) }


            TextInputField(label = "Surveyor Name") { surveyorName = TextFieldValue(it) }


            TextInputField(label = "Surveyor Phone") { surveyorPhone = TextFieldValue(it) }

            DatePickerField(label = "Survey Date") { date -> surveyDate = date }


            TextInputField(label = "Survey Description") { surveyDescription = TextFieldValue(it) }

            TextField(
                value = claimStatus,
                onValueChange = { claimStatus = it },
                label = { Text("Claim Status") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { expandedStatus = !expandedStatus }) {
                        Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Claim Status Dropdown")
                    }
                }
            )
            DropdownMenu(
                expanded = expandedStatus,
                onDismissRequest = { expandedStatus = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                claimStatusOptions.forEach { option ->
                    DropdownMenuItem(onClick = {
                        claimStatus = option
                        expandedStatus = false
                    }) {
                        Text(text = option)
                    }
                }
            }


            SubmitButton(label = "Submit Claim") {
                submitClaim(
                    claimNo.text,
                    claimDate,
                    policyNo,
                    incidentDate,
                    incidentLocation.text,
                    incidentDescription.text,
                    claimAmount.text,
                    surveyorName.text,
                    surveyorPhone.text,
                    surveyDate,
                    surveyDescription.text,
                    claimStatus
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


            SubmitButton(label = "Go to Product") {
                navController.navigate("product_screen")
            }
        }
    }


fun submitClaim(
    claimNo: String,
    claimDate: String,
    policyNo: String,
    incidentDate: String,
    incidentLocation: String,
    incidentDescription: String,
    claimAmount: String,
    surveyorName: String,
    surveyorPhone: String,
    surveyDate: String,
    surveyDescription: String,
    claimStatus: String
) {
    println(
        "Claim submitted with details: $claimNo, $claimDate, $policyNo, $incidentDate, " +
                "$incidentLocation, $incidentDescription, $claimAmount, $surveyorName, $surveyorPhone, " +
                "$surveyDate, $surveyDescription, $claimStatus"
    )
}


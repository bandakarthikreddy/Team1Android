package com.singlepointsol.policy

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
fun PolicyPage(modifier: Modifier, viewModel: PolicyViewModel) {

    var form by remember { mutableStateOf(PolicyForm()) }
    val policyData by viewModel.policyData.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(policyData) {
        policyData?.let {
            form = form.copy(
                policyNo = it.policyNo,
                proposalNo = it.proposalNo,
                noClaimBonus = it.noClaimBonus,
                receiptNo = it.receiptNo,
                receiptDate = it.receiptDate,
                paymentMode = it.paymentMode,
                amount = it.amount
            )
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            item {
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
                    value = form.proposalNo,
                    onValueChange = { form = form.copy(proposalNo = it)},
                    label = {
                        Text("Proposal Number", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.noClaimBonus,
                    onValueChange = { form = form.copy(noClaimBonus = it)},
                    label = {
                        Text("No Claim Bonus", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.receiptNo,
                    onValueChange = { form = form.copy(receiptNo = it)},
                    label = {
                        Text("Receipt Number", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.receiptDate,
                    onValueChange = { form = form.copy(receiptDate = it)},
                    label = {
                        Text("Receipt Date", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.paymentMode,
                    onValueChange = { form = form.copy(paymentMode = it)},
                    label = {
                        Text("Payment Mode", style = textFieldStyle())
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
                           val newPolicy = PolicyDataClassItem(
                               policyNo = form.policyNo,
                               proposalNo = form.proposalNo,
                               noClaimBonus = form.noClaimBonus,
                               receiptNo = form.receiptNo,
                               receiptDate = form.receiptDate,
                               paymentMode = form.paymentMode,
                               amount = form.amount
                           )
                           viewModel.addPolicy(form.policyNo, context)
                       }
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
                       if (form.policyNo.isNotEmpty()){
                           viewModel.addPolicy(form.policyNo,context)
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
                       if (form.policyNo.isNotEmpty()) {
                           val updatePolicy = PolicyDataClassItem(
                               policyNo = form.policyNo,
                               proposalNo = form.proposalNo,
                               noClaimBonus = form.noClaimBonus,
                               receiptNo = form.receiptNo,
                               receiptDate = form.receiptDate,
                               paymentMode = form.paymentMode,
                               amount = form.amount
                           )
                           viewModel.updatePolicy(updatePolicy,form.policyNo,context)
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
                       if (form.policyNo.isNotEmpty()) {
                           viewModel.deletePolicy(form.policyNo,context)
                       }
                   },
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(horizontal = 8.dp)) {
                       Text("DELETE", style = buttonTextFieldStyle())
                   }
               }
           }
        }
    }
}

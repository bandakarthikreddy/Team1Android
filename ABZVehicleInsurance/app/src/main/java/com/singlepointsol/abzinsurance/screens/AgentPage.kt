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
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.singlepointsol.abzinsurance.dataclass.AgentDataClassItem
import com.singlepointsol.abzinsurance.form.AgentForm
import com.singlepointsol.abzinsurance.viewmodel.AgentViewModel

@Composable
fun AgentPage(modifier: Modifier, viewModel: AgentViewModel) {
    var form by remember { mutableStateOf(AgentForm()) }
    val agentData by viewModel.agentData.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(agentData) {
        agentData?.let {
            form = form.copy(
                agentID = it.agentID,
                agentName = it.agentName,
                agentPhone = it.agentPhone,
                agentEmail = it.agentEmail,
                licenseCode = it.licenseCode
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
                    value = form.agentID,
                    onValueChange = { form = form.copy(agentID = it) },
                    label = { Text("Agent ID") },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.agentName,
                    onValueChange = { form = form.copy(agentName = it) },
                    label = { Text("Agent Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.agentPhone,
                    onValueChange = { form = form.copy(agentPhone = it) },
                    label = { Text("Agent Phone") },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.agentEmail,
                    onValueChange = { form = form.copy(agentEmail = it) },
                    label = { Text("Agent Email") },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.licenseCode,
                    onValueChange = { form = form.copy(licenseCode = it) },
                    label = { Text("License Code") },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Row {
            LazyRow {
                item {
                    Button(
                        onClick = {
                            if (form.agentID.isNotEmpty()) {
                                val addAgent = AgentDataClassItem(
                                    agentID = form.agentID,
                                    agentName = form.agentName,
                                    agentPhone = form.agentPhone,
                                    agentEmail = form.agentEmail,
                                    licenseCode = form.licenseCode
                                )
                                viewModel.addAgent(form.agentID,addAgent,context)
                            }
                        },
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        Text("ADD")
                    }
                }

                item {
                    Button(
                        onClick = {
                            if (form.agentID.isNotEmpty()) {
                                viewModel.getAgentByAgentID(form.agentID, context)
                            }
                        },
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        Text("FETCH")
                    }
                }

                item {
                    Button(
                        onClick = {
                            if (form.agentID.isNotEmpty()) {
                                val updatedAgent = AgentDataClassItem(
                                    agentID = form.agentID,
                                    agentName = form.agentName,
                                    agentPhone = form.agentPhone,
                                    agentEmail = form.agentEmail,
                                    licenseCode = form.licenseCode
                                )
                                viewModel.updateAgent(updatedAgent, form.agentID, context)
                            }
                        },
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        Text("UPDATE")
                    }
                }

                item {
                    Button(
                        onClick = {
                            if (form.agentID.isNotEmpty()) {
                                viewModel.deleteAgent(form.agentID, context)
                            }
                        },
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        Text("DELETE")
                    }
                }
            }
        }
    }
}

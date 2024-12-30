package com.singlepointsol.abzinsurance.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.singlepointsol.abzinsurance.components.buttonTextFieldStyle
import com.singlepointsol.abzinsurance.components.textFieldStyle
import com.singlepointsol.abzinsurance.dataclass.VehicleDataClassItem
import com.singlepointsol.abzinsurance.form.VehicleForm
import com.singlepointsol.abzinsurance.viewmodel.VehicleViewModel


@Composable
fun VehiclePage(modifier: Modifier, viewModel: VehicleViewModel) {
    var form by remember { mutableStateOf(VehicleForm()) }
    val vehicleData by viewModel.vehicleData.collectAsState()
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }


    LaunchedEffect(vehicleData){
        vehicleData?.let {
            form = form.copy(
                regNo = it.regNo,
                regAuthority = it.regAuthority,
                ownerId = it.ownerId,
                make = it.make,
                model = it.model,
                fuelType = it.fuelType,
                variant = it.variant,
                engineNo = it.engineNo,
                chassisNo = it.chassisNo,
                engineCapacity = it.engineCapacity,
                seatingCapacity = it.seatingCapacity,
                mfgYear = it.mfgYear,
                regDate = it.regDate,
                bodyType = it.bodyType,
                leasedBy = it.leasedBy
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            item {
                OutlinedTextField(
                    value = form.regNo,
                    onValueChange = { form = form.copy(regNo = it) },
                    label = {
                        Text("Registration Number", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        capitalization = KeyboardCapitalization.Characters
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.regAuthority,
                    onValueChange = { form = form.copy(regAuthority = it) },
                    label = {
                        Text("Registration Authority", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        capitalization = KeyboardCapitalization.Characters
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))


                OutlinedTextField(
                    value = form.ownerId,
                    onValueChange = { form = form.copy(ownerId = it) },
                    label = { Text("Owner ID") },
                    modifier = Modifier
                        .fillMaxWidth()
                )


                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.make,
                    onValueChange = { form = form.copy(make = it) },
                    label = {
                        Text("Make", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.model,
                    onValueChange = { form = form.copy(model = it) },
                    label = {
                        Text("Model", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))


                OutlinedTextField(
                    value = form.fuelType,
                    onValueChange = { form = form.copy(fuelType = it) },
                    label = {
                        Text("Fuel Type", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))


                OutlinedTextField(
                    value = form.variant,
                    onValueChange = { form = form.copy(variant = it) },
                    label = {
                        Text("Variant", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.engineNo,
                    onValueChange = { form = form.copy(engineNo = it) },
                    label = {
                        Text("Engine Number", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.chassisNo,
                    onValueChange = { form = form.copy(chassisNo = it) },
                    label = {
                        Text("Chassis Number", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.engineCapacity,
                    onValueChange = { form = form.copy(engineCapacity = it) },
                    label = {
                        Text("Engine Capacity", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.seatingCapacity,
                    onValueChange = { form = form.copy(seatingCapacity = it) },
                    label = {
                        Text("Seating Capacity", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.mfgYear,
                    onValueChange = { form = form.copy(mfgYear = it) },
                    label = {
                        Text("Manufactured Year", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Spacer(modifier = Modifier.height(8.dp))



                OutlinedTextField(
                    value = form.regDate,
                    onValueChange = { form = form.copy(regDate = it) },
                    label = {
                        Text("Registered Date", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.bodyType,
                    onValueChange = { form = form.copy(bodyType = it) },
                    label = {
                        Text("Body Type", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.leasedBy,
                    onValueChange = { form = form.copy(leasedBy = it) },
                    label = {
                        Text("Leased By", style = textFieldStyle())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                item {
                    Button(
                        onClick = {
                            if (form.regNo.isNotEmpty()) {
                                val newVehicle = VehicleDataClassItem(
                                    regNo = form.regNo,
                                    regAuthority = form.regAuthority,
                                    ownerId = form.ownerId,
                                    make = form.make,
                                    model = form.model,
                                    fuelType = form.fuelType,
                                    variant = form.variant,
                                    engineNo = form.engineNo,
                                    chassisNo = form.chassisNo,
                                    engineCapacity = form.engineCapacity,
                                    seatingCapacity = form.seatingCapacity,
                                    mfgYear = form.mfgYear,
                                    regDate = form.regDate,
                                    bodyType = form.bodyType,
                                    leasedBy = form.leasedBy
                                )
                                viewModel.addVehicle(form.regNo,newVehicle, context)
                            }

                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    ) {
                        Text(
                            "ADD", style = buttonTextFieldStyle()
                        )
                    }
                }

                item {
                    Button(
                        onClick = {
                            if (form.regNo.isNotEmpty()) {
                                viewModel.getVehicleByRegNo(form.regNo, context)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    ) {
                        Text(
                            "FETCH", style = buttonTextFieldStyle()
                        )
                    }
                }

                item {
                    Button(
                        onClick = {
                            if (form.regNo.isNotEmpty()) {
                                val updatedVehicle = VehicleDataClassItem(
                                    regNo = form.regNo,
                                    regAuthority = form.regAuthority,
                                    ownerId = form.ownerId,
                                    make = form.make,
                                    model = form.model,
                                    fuelType = form.fuelType,
                                    variant = form.variant,
                                    engineNo = form.engineNo,
                                    chassisNo = form.chassisNo,
                                    engineCapacity = form.engineCapacity,
                                    seatingCapacity = form.seatingCapacity,
                                    mfgYear = form.mfgYear,
                                    regDate = form.regDate,
                                    bodyType = form.bodyType,
                                    leasedBy = form.leasedBy
                                )
                                viewModel.updateVehicle(updatedVehicle,form.regNo, context)
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
                    Button(
                        onClick = {
                            if (form.regNo.isNotEmpty()) {
                                viewModel.deleteVehicle(form.regNo, context)
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
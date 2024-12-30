package com.singlepointsol.insurancepolicy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text



class Vehicle : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VehicleForm()
        }
    }
}

@Composable
fun VehicleForm() {
    var regNo by remember { mutableStateOf("") }
    var regAuthority by remember { mutableStateOf("") }
    var ownerID by remember { mutableStateOf("") }
    var make by remember { mutableStateOf("") }
    var model by remember { mutableStateOf("") }
    var fuelType by remember { mutableStateOf("") }
    var variant by remember { mutableStateOf("") }
    var engineNo by remember { mutableStateOf("") }
    var chasisNo by remember { mutableStateOf("") }
    var engineCapacity by remember { mutableStateOf("") }
    var seatingCapacity by remember { mutableStateOf("") }
    var mfgYear by remember { mutableStateOf("") }
    var regDate by remember { mutableStateOf("") }
    var bodyType by remember { mutableStateOf("") }
    var leasedBy by remember { mutableStateOf("") }

    val regAuthorities = listOf("Andaman and Nicobar Islands", "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar",
        "Chhattisgarh", "Chandigarh", "Daman and Diu", "Delhi", "Goa", "Gujarat", "Haryana",
        "Himachal Pradesh", "Jammu and Kashmir", "Karnataka", "Kerala", "Lakshadweep", "Madhya Pradesh",
        "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Punjab", "Rajasthan",
        "Sikkim", "Tamil Nadu", "Telangana", "Tripura", "Uttar Pradesh", "Uttarakhand", "West Bengal")

    val fuelTypes = listOf("Petrol", "Diesel", "CNG", "LPG", "Electric")
    val seatingCapacities = listOf("2", "4", "5", "6", "7", "9")
    val mfgYears = listOf("2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019",
        "2020", "2021", "2022", "2023", "2024")
    val bodyTypes = listOf("Sedan", "SUV", "Hatchback", "Compact Suv", "Coupe", "Convertible")

    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedTextField(
            value = regNo,
            onValueChange = { regNo = it },
            label = { Text("Registration Number") },
            modifier = Modifier.fillMaxWidth()
        )

        DropdownField(
            label = "Registration Authority",
            options = regAuthorities,
            selectedOption = regAuthority,
            onOptionSelected = { regAuthority = it })

        OutlinedTextField(
            value = ownerID,
            onValueChange = { ownerID = it },
            label = { Text("Owner ID") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = make,
            onValueChange = { make = it },
            label = { Text("Make") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = model,
            onValueChange = { model = it },
            label = { Text("Model") },
            modifier = Modifier.fillMaxWidth()
        )

        DropdownField(
            label = "Fuel Type",
            options = fuelTypes,
            selectedOption = fuelType,
            onOptionSelected = { fuelType = it })

        OutlinedTextField(
            value = variant,
            onValueChange = { variant = it },
            label = { Text("Variant") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = engineNo,
            onValueChange = { engineNo = it },
            label = { Text("Engine Number") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = chasisNo,
            onValueChange = { chasisNo = it },
            label = { Text("Chasis Number") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = engineCapacity,
            onValueChange = { engineCapacity = it },
            label = { Text("Engine Capacity") },
            modifier = Modifier.fillMaxWidth()
        )

        DropdownField(
            label = "Seating Capacity",
            options = seatingCapacities,
            selectedOption = seatingCapacity,
            onOptionSelected = { seatingCapacity = it })

        DropdownField(
            label = "Manufacturing Year",
            options = mfgYears,
            selectedOption = mfgYear,
            onOptionSelected = { mfgYear = it })

        OutlinedTextField(
            value = regDate,
            onValueChange = { regDate = it },
            label = { Text("Registration Date") },
            modifier = Modifier.fillMaxWidth()
        )

        DropdownField(
            label = "Body Type",
            options = bodyTypes,
            selectedOption = bodyType,
            onOptionSelected = { bodyType = it })

        OutlinedTextField(
            value = leasedBy,
            onValueChange = { leasedBy = it },
            label = { Text("Leased By") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row (
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween ) {
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save")
            }
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Load")
            }
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Delete")
            }
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Update")
        }
        }
    }
}

@Composable
fun DropdownField(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null
                    )
                }
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    },
                    text = { Text(text = option) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VehicleFormPreview() {
    VehicleForm()
}

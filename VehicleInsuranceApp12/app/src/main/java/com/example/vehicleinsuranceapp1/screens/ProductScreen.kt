package com.example.vehicleinsuranceapp1.ui.product

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.vehicleinsuranceapp1.ui.components.TextInputField
import com.example.vehicleinsuranceapp1.ui.components.SubmitButton
import androidx.compose.ui.platform.LocalDensity

@Composable
fun ProductScreen(navController: NavHostController) {
    var productID by remember { mutableStateOf("") }
    var productName by remember { mutableStateOf("") }
    var productDescription by remember { mutableStateOf("") }
    var productUIN by remember { mutableStateOf("") }
    var insuredInterests by remember { mutableStateOf("") }
    var policyCoverage by remember { mutableStateOf("") }


    Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(text = "Product Information", style = MaterialTheme.typography.h5)
            TextInputField(label = "Product ID") { productID = it }
            TextInputField(label = "Product Name") { productName = it }
            TextInputField(label = "Product Description") { productDescription = it }
            TextInputField(label = "Product UIN") { productUIN = it }
            TextInputField(label = "Insured Interests") { insuredInterests = it }
            TextInputField(label = "Policy Coverage") { policyCoverage = it }
            SubmitButton(label = "Submit Product") {
                submitProduct(
                    productID,
                    productName,
                    productDescription,
                    productUIN,
                    insuredInterests,
                    policyCoverage
                )
            }
        }
    }


fun submitProduct(
    productID: String,
    productName: String,
    productDescription: String,
    productUIN: String,
    insuredInterests: String,
    policyCoverage: String
) {
    println("Product submitted:")
    println("ID: $productID")
    println("Name: $productName")
    println("Description: $productDescription")
    println("UIN: $productUIN")
    println("Insured Interests: $insuredInterests")
    println("Policy Coverage: $policyCoverage")
}

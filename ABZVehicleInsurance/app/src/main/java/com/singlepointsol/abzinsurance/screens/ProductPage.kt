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
import com.singlepointsol.abzinsurance.dataclass.ProductDataClassItem
import com.singlepointsol.abzinsurance.form.ProductForm
import com.singlepointsol.abzinsurance.viewmodel.ProductViewModel

@Composable
fun ProductPage(modifier: Modifier, viewModel: ProductViewModel) {

    var form by remember { mutableStateOf(ProductForm()) }
    val productData by viewModel.productData.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(productData) {
        productData?.let {
            form = form.copy(
                productID = it.productID,
                productName = it.productName,
                productDescription = it.productDescription,
                productUIN = it.productUIN,
                insuredInterests = it.insuredInterests,
                policyCoverage = it.policyCoverage
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
                    value = form.productID,
                    onValueChange = { form = form.copy(productID = it) },
                    label = { Text("Product ID", style = textFieldStyle()) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.productName,
                    onValueChange = { form = form.copy(productName = it) },
                    label = { Text("Product Name", style = textFieldStyle()) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.productDescription,
                    onValueChange = { form = form.copy(productDescription = it) },
                    label = { Text("Product Description", style = textFieldStyle()) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.productUIN,
                    onValueChange = { form = form.copy(productUIN = it) },
                    label = { Text("Product UIN", style = textFieldStyle()) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.insuredInterests,
                    onValueChange = { form = form.copy(insuredInterests = it) },
                    label = { Text("Insured Interests", style = textFieldStyle()) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.policyCoverage,
                    onValueChange = { form = form.copy(policyCoverage = it) },
                    label = { Text("Policy Coverage", style = textFieldStyle()) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly // This will evenly space the buttons
        ) {
            Button(onClick = {
                if (form.productID.isNotEmpty()) {
                    val newProduct = ProductDataClassItem(
                        productID = form.productID,
                        productName = form.productName,
                        productDescription = form.productDescription,
                        productUIN = form.productUIN,
                        insuredInterests = form.insuredInterests,
                        policyCoverage = form.policyCoverage
                    )
                    viewModel.addProduct(form.productID,newProduct, context)
                }
            },
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            ) {
                Text("ADD", style = buttonTextFieldStyle())
            }

            Button(onClick = {
                if (form.productID.isNotEmpty()) {
                    viewModel.fetchProductById(form.productID, context)
                }
            }) {
                Text("FETCH", style = buttonTextFieldStyle())
            }

            Button(onClick = {
                if (form.productID.isNotEmpty()) {
                    val updatedProduct = ProductDataClassItem(
                        productID = form.productID,
                        productName = form.productName,
                        productDescription = form.productDescription,
                        productUIN = form.productUIN,
                        insuredInterests = form.insuredInterests,
                        policyCoverage = form.policyCoverage
                    )
                    viewModel.updateProduct(form.productID,updatedProduct, context)
                }
            }) {
                Text("UPDATE", style = buttonTextFieldStyle())
            }

            Button(onClick = {
                if (form.productID.isNotEmpty()) {
                    viewModel.deleteProduct(form.productID, context)
                }
            }) {
                Text("DELETE", style = buttonTextFieldStyle())
            }
        }
    }
}


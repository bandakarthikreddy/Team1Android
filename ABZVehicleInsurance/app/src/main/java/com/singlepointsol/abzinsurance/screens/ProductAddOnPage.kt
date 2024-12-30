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
import com.singlepointsol.abzinsurance.dataclass.ProductAddOnDataClassItem
import com.singlepointsol.abzinsurance.form.ProductAddOnForm
import com.singlepointsol.abzinsurance.viewmodel.ProductAddOnViewModel

@Composable
fun ProductAddOnPage(modifier: Modifier,viewModel: ProductAddOnViewModel) {
    var form by remember { mutableStateOf(ProductAddOnForm()) }
    val productAddOnData by viewModel.productAddOnData.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(productAddOnData) {
        productAddOnData?.let {
            form = form.copy(
                addonID = it.addonID,
                productID = it.productID,
                addonTitle = it.addonTitle,
                addonDescription = it.addonDescription
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
                    onValueChange = {form = form.copy(addonID = it)},
                    label = {
                        Text("Addon ID", style = textFieldStyle() )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.productID,
                    onValueChange = {form = form.copy(productID = it)},
                    label = {
                        Text("Product ID", style = textFieldStyle() )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.addonTitle,
                    onValueChange = {form = form.copy(addonTitle = it)},
                    label = {
                        Text("Addon Title", style = textFieldStyle() )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = form.addonDescription,
                    onValueChange = {form = form.copy(addonDescription = it)},
                    label = {
                        Text("Addon Description", style = textFieldStyle() )
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
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                item {
                    Button(onClick = {
                        if (form.addonID.isNotEmpty()) {
                            val newProductAddOn = ProductAddOnDataClassItem(
                                addonID = form.addonID,
                                productID = form.productID,
                                addonTitle = form.addonTitle,
                                addonDescription = form.addonDescription
                            )
                            viewModel.addProductAddOn( newProductAddOn, context)
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
                        if (form.addonID.isNotEmpty()) {
                            viewModel.getProductAddOnByID(form.productID, form.addonID, context)
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
                        if (form.addonID.isNotEmpty()) {
                            val updatedProductAddOn = ProductAddOnDataClassItem(
                                addonID = form.addonID,
                                productID = form.productID,
                                addonTitle = form.addonTitle,
                                addonDescription = form.addonDescription
                            )
                            viewModel.updateProductAddOn(form.productID, form.addonID, updatedProductAddOn,context)
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
                        if (form.addonID.isNotEmpty()) {
                            viewModel.deleteProductAddOn( form.productID,form.addonID, context)
                        }
                    },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 4.dp)) {
                        Text("DELETE", style = buttonTextFieldStyle())
                    }
                }
            }
        }
    }
}
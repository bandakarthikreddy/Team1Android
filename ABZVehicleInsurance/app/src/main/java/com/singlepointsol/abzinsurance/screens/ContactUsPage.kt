package com.singlepointsol.abzinsurance.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ContactUsPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "This is the Contact Us Page", modifier = Modifier.padding(8.dp))
        Text(text = "Email: support@abzinsurance.com", modifier = Modifier.padding(8.dp))
        Text(text = "Phone: 9182461554", modifier = Modifier.padding(8.dp))
    }
}

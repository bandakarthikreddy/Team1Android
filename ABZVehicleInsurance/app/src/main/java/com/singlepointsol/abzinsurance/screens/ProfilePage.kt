package com.singlepointsol.abzinsurance.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.singlepointsol.abzinsurance.navigation.Routes

@Composable
fun ProfilePage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "This is the Profile Page", modifier = Modifier.padding(8.dp))
        Text(text = "Name: Vellala Naga Sreekara Sarma", modifier = Modifier.padding(8.dp))
        Text(text = "Email: vellalasreekar@gmail.com", modifier = Modifier.padding(8.dp))
    }
}

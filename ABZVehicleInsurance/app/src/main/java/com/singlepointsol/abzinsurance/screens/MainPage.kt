package com.singlepointsol.abzinsurance.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.singlepointsol.abzinsurance.navigation.Routes

@Composable
fun MainPage(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        listOf(


            "Customer Page" to Routes.CustomerPage.route,
            "Vehicle Page" to Routes.VehiclePage.route,
            "Product Page" to Routes.ProductPage.route,
            "Product Add-On Page" to Routes.ProductAddOnPage.route,
            "Agent Page" to Routes.AgentPage.route,
            "Proposal Page" to Routes.ProposalPage.route,
            "Policy Page" to Routes.PolicyPage.route,
            "Policy Add-On Page" to Routes.PolicyAddOnPage.route,
            "Claim Page" to Routes.ClaimPage.route,
            "Customer Query" to Routes.CustomerQueryPage.route,
            "Query Response" to Routes.QueryResponsePage.route

        ).forEach { (label, route) ->
            Button(onClick = { navController.navigate(route) }) {
                Text(label)
            }
        }
    }
}
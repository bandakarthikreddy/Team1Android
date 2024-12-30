package com.example.vehicleinsuranceapp1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vehicleinsuranceapp1.ui.claim.ClaimScreen
import com.example.vehicleinsuranceapp1.ui.product.ProductScreen
import com.example.vehicleinsuranceapp1.ui.theme.VehicleInsuranceApp1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VehicleInsuranceApp1Theme {

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "claim_screen") {
                    composable("claim_screen") {
                        ClaimScreen(navController)
                    }
                    composable("product_screen") {
                        ProductScreen(navController)
                    }
                }
            }
        }
    }
}

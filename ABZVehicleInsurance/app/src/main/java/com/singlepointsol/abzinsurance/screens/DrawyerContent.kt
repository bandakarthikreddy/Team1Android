package com.singlepointsol.abzinsurance.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.singlepointsol.abzinsurance.navigation.Routes
import kotlinx.coroutines.launch

@Composable
fun DrawerContent(navController: NavController, drawerState: DrawerState) {
    val coroutineScope = rememberCoroutineScope()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {

        Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                TextButton(
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                            navController.navigate(Routes.ProfilePage.route)
                        }
                    }
                ) {
                    Text("Profile")
                }


                TextButton(
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                            navController.navigate(Routes.ContactUsPage.route)
                        }
                    }
                ) {
                    Text("Contact Us")
                }


                TextButton(
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                            navController.navigate(Routes.MainPage.route)
                        }
                    }
                ) {
                    Text("Forms")
                }
            }
        }
    }
}

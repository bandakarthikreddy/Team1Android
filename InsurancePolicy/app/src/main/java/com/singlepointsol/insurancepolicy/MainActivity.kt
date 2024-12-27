package com.singlepointsol.insurancepolicy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp



class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            enableEdgeToEdge()
        }
        setContent {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                content = { innerPadding ->
                    AgentFormScreen(
                        modifier = Modifier.padding(innerPadding))
                }
            )
        }
    }
}

@Composable
fun AgentFormScreen(modifier: Modifier = Modifier) {
    var agentID by remember { mutableStateOf("") }
    var agentName by remember { mutableStateOf("") }
    var agentPhone by remember { mutableStateOf("") }
    var agentEmail by remember { mutableStateOf("") }
    var licenseCode by remember { mutableStateOf("") }

    Column(
        modifier = modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {

        OutlinedTextField(
            value = agentID,
            onValueChange = { agentID = it },
            label = { Text("Agent ID") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = agentName,
            onValueChange = { agentName = it },
            label = { Text("Agent Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = agentPhone,
            onValueChange = { agentPhone = it },
            label = { Text("Agent Phone") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = agentEmail,
            onValueChange = { agentEmail = it },
            label = { Text("Agent Email") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = licenseCode,
            onValueChange = { licenseCode = it },
            label = { Text("License Code") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row {
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
            ){
                Text("Update")
            }
            }
        }


    }


@Preview(showBackground = true)
@Composable
fun AgentFormPreview() {
    AgentFormScreen()
}

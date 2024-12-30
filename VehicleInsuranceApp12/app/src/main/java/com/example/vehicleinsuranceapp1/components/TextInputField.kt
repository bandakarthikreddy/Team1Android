package com.example.vehicleinsuranceapp1.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun TextInputField(
    label: String,
    onValueChange: (String) -> Unit
) {
    var text = remember { androidx.compose.runtime.mutableStateOf("") }
    TextField(
        value = text.value,
        onValueChange = { newText ->
            text.value = newText
            onValueChange(newText)
        },
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth()
    )
}

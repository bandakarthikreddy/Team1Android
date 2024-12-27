package com.example.vehicleinsuranceapp1.ui.components

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.util.*

@Composable
fun DatePickerField(
    label: String,
    modifier: Modifier = Modifier,
    onDateSelected: (String) -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    var selectedDate by remember { mutableStateOf("") }

    OutlinedTextField(
        value = selectedDate,
        onValueChange = {},
        label = { Text(label) },
        readOnly = true,
        modifier = modifier.fillMaxWidth(),
        trailingIcon = {
            IconButton(onClick = {
                DatePickerDialog(
                    context,
                    { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
                        selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                        onDateSelected(selectedDate)
                    },
                    year, month, day
                ).show()
            }) {
                Icon(Icons.Default.DateRange, contentDescription = "Date picker icon")
            }
        }
    )
}

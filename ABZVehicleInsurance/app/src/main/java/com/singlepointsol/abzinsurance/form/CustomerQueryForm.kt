package com.singlepointsol.abzinsurance.form

data class CustomerQueryForm(
    val customerID: String = "",
    val description: String = "",
    val queryDate: String = "",
    val queryID: String = "",
    val status: String = ""
)

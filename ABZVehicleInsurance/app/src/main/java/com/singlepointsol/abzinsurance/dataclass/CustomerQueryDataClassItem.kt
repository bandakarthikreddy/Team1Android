package com.singlepointsol.abzinsurance.dataclass

data class CustomerQueryDataClassItem(
    val customerID: String,
    val description: String,
    val queryDate: String,
    val queryID: String,
    val status: String
)
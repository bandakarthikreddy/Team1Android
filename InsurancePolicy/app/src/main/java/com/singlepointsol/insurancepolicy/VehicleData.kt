package com.singlepointsol.insurancepolicy

data class VehicleData(
    val regNo : String, val regAuthority : String, val ownerID : String, val make : String, val model : String,
    val fuelType : String, val variant : String, val engineNo : String, val chasisNo : String,
    val engineCapacity : String, val seatingCapacity : String, val mfgYear : String, val regDate : String,
    val bodyType : String, val leasedBy : String
)

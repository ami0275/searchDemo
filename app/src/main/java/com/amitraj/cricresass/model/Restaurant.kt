package com.amitraj.cricresass.model

data class Restaurant(
    val address: String,
    val cuisine_type: String,
    val id: Int,
    val latlng: LatlngX,
    val name: String,
    val neighborhood: String,
    val operating_hours: OperatingHours,
    val photograph: String,
    val reviews: List<Review>
)
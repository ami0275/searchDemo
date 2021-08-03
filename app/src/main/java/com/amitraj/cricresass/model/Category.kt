package com.amitraj.cricresass.model

import com.google.gson.annotations.SerializedName

data class Category(
    val id: String,
    @SerializedName("menu-items")
    val menuItem: List<MenuItem>,
    val name: String
)
package com.amitraj.cricresass.model.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Resturant")
data class ResturantTableModel(

    @ColumnInfo(name = "resturantName")
    var name: String? = "",
    @ColumnInfo(name = "id")
    var Id: Int,
    @ColumnInfo(name = "operatingHr")
    var OperatingHours: String,
    @ColumnInfo(name = "lating")
    var Lating: String,
    @ColumnInfo(name = "items")
    var Items: String,
    @ColumnInfo(name = "descriptions")
    var Description: String,
    @ColumnInfo(name = "price")
    var Price: String,
    @ColumnInfo(name = "rname")
    var Rname: String,
    @ColumnInfo(name = "rdata")
    var Rdata: String,
    @ColumnInfo(name = "rating")
    var Rating: String,
    @ColumnInfo(name = "rcomment")
    var Rcomment: String,
    @ColumnInfo(name = "place")
    var Place: String,
    @ColumnInfo(name = "neighborhood")
    var Neighborhood: String,
    @ColumnInfo(name = "date")
    var Date: String

){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "resUniqueId")
    var resUniqueId: Int? = null

}
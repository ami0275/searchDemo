package com.amitraj.cricresass.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amitraj.cricresass.model.table.ResturantTableModel

@Dao
interface ResturantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertData(Res: ResturantTableModel)

    @Query("SELECT resturantName FROM resturant WHERE rcomment LIKE '%' || :searchedText || '%' OR rating LIKE '%' || :searchedText || '%' OR id LIKE '%' || :searchedText || '%' OR lating LIKE '%' || :searchedText || '%' OR descriptions LIKE '%' || :searchedText || '%' OR place LIKE '%' || :searchedText || '%'OR price LIKE '%' || :searchedText || '%' OR rname LIKE '%' || :searchedText || '%'  OR rdata LIKE '%' || :searchedText || '%' OR neighborhood LIKE '%' || :searchedText || '%' OR date LIKE '%' || :searchedText || '%' OR items LIKE '%' || :searchedText || '%' OR operatingHr LIKE '%' || :searchedText || '%' ")
    fun getAllData(searchedText: String?): LiveData<List<String>>

    @Query("SELECT resturantName FROM resturant")
    fun checkAllData(): LiveData<List<String>>
}
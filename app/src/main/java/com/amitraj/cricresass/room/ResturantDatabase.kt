package com.amitraj.cricresass.room

import android.content.Context
import androidx.room.*
import com.amitraj.cricresass.model.table.ResturantTableModel

@Database(entities = arrayOf(ResturantTableModel::class), version = 1, exportSchema = false)
abstract class ResturantDatabase : RoomDatabase() {

    abstract fun resturantDao() : ResturantDao

    companion object {

        @Volatile
        private var INSTANCE: ResturantDatabase? = null

        fun getDataseClient(context: Context) : ResturantDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, ResturantDatabase::class.java, "Resturant_Database")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }

}
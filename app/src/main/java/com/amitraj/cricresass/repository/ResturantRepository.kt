package com.amitraj.cricresass.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.amitraj.cricresass.model.MenuData
import com.amitraj.cricresass.model.MenuItem
import com.amitraj.cricresass.model.table.ResturantTableModel
import com.amitraj.cricresass.model.Rstrnts
import com.amitraj.cricresass.room.ResturantDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class ResturantRepository {

    companion object {

        var resturantDatabase: ResturantDatabase? = null

        var resTableModel: LiveData<List<String>>? = null

        fun initializeDB(context: Context): ResturantDatabase {
            return ResturantDatabase.getDataseClient(context)
        }

        fun insertData(context: Context, resturants: Rstrnts) {

            resturantDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                resturants?.restaurants?.forEach {
                    var rating: Int = 0
                    var comment = ""
                    var date = ""
                    var rName = ""

                    it?.reviews?.forEach {
                        rating = it.rating
                        comment = it.comments
                        date = it.date
                        rName = it.name
                    }

                    it?.let {
                        val resturantsDetails = ResturantTableModel(
                            it.name,
                            it.id,
                            it.operating_hours.toString(),
                            it.latlng.toString(),
                            "",
                            "",
                            "",
                            rName,
                            it.cuisine_type,
                            rating.toString(),
                            comment,
                            it.address,
                            it.neighborhood,
                            date
                        )
                        resturantDatabase!!.resturantDao().InsertData(resturantsDetails)
                    }
                }

            }

        }

        fun insertData(context: Context, menuData: MenuData) {

            resturantDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                menuData.menus.forEach {
                    var name = ""
                    var menuItem: List<MenuItem>? = null
                    var description = ""
                    var price = ""
                    var itemName = ""
                    it?.let {
                        it.categories.forEach {
                            name = it.name
                            menuItem = it.menuItem
                        }
                        menuItem?.forEach {
                            description = it.description
                            itemName = it.name
                            price = it.price
                        }
                        val menuDetails = ResturantTableModel(
                            name,
                            it.restaurantId,
                            "",
                            "",
                            itemName,
                            description,
                            price,
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            ""
                        )
                        resturantDatabase!!.resturantDao().InsertData(menuDetails)
                    }
                }

            }

        }

        fun getResturantDetails(context: Context, text: String): LiveData<List<String>>? {

            resturantDatabase = initializeDB(context)

            text.let {
                if (!it.isEmpty()) {
                    resTableModel = resturantDatabase!!.resturantDao().getAllData(text)
                }
            }

            return resTableModel
        }

        fun checkAllResturantDetails(context: Context): LiveData<List<String>>? {

            resturantDatabase = initializeDB(context)

            resTableModel = resturantDatabase!!.resturantDao().checkAllData()

            return resTableModel
        }


    }
}
package com.amitraj.cricresass.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData

import androidx.lifecycle.ViewModel
import com.amitraj.cricresass.model.MenuData
import com.amitraj.cricresass.model.Rstrnts
import com.amitraj.cricresass.repository.ResturantRepository


class ResturantViewModel : ViewModel() {

    var liveDataResturants: LiveData<List<String>>? = null

    fun insertData(context: Context, resturants: Rstrnts) {
        ResturantRepository.insertData(context, resturants)
    }

    fun insertData(context: Context, menus: MenuData) {
        ResturantRepository.insertData(context, menus)
    }

    fun getResturantsDetails(context: Context, text: String): LiveData<List<String>>? {
        liveDataResturants = ResturantRepository.getResturantDetails(context, text)
        return liveDataResturants
    }

    fun getAllDetails(context: Context): LiveData<List<String>>? {
        liveDataResturants = ResturantRepository.checkAllResturantDetails(context)
        return liveDataResturants
    }

}
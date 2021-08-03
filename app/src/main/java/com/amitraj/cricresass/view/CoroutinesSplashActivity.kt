package com.amitraj.cricresass.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.amitraj.cricresass.R
import com.amitraj.cricresass.model.MenuData
import com.amitraj.cricresass.model.Rstrnts
import com.amitraj.cricresass.viewmodel.ResturantViewModel
import com.google.gson.Gson
import kotlinx.coroutines.*

class CoroutinesSplashActivity : AppCompatActivity() {

    private val activityScope = CoroutineScope(Dispatchers.Main)
    private lateinit var resturantViewModel: ResturantViewModel
    private lateinit var context: Context
    private val menu = "menus.json"
    private val resturant = "resturants.json"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines_splash)
        context = this@CoroutinesSplashActivity

        resturantViewModel = ViewModelProvider(this).get(ResturantViewModel::class.java)

        resturantViewModel?.getAllDetails(context)!!.observe(this, Observer {
            if (it.size == 0) {
                insertJsonData()
            }
        })


        activityScope.launch {
            delay(2000)
            var intent = Intent(this@CoroutinesSplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun insertJsonData() {

        CoroutineScope(Dispatchers.IO).launch {
            val menuDetails = application.assets.open(menu)?.bufferedReader().use {
                it.readText()
            }

            val resturantsDeatils = application.assets.open(resturant)?.bufferedReader().use {
                it.readText()
            }

            Gson().fromJson<MenuData>(menuDetails, MenuData::class.java)?.also {
                resturantViewModel?.insertData(context, it)
            }

            Gson().fromJson<Rstrnts>(resturantsDeatils, Rstrnts::class.java)?.also {
                resturantViewModel?.insertData(context, it)
            }
        }

    }

    /*private fun doasyncInsert() {
        CoroutineScope {
            async (Dispatchers.IO ){
                retrun async value
            }
        }
        return value.await()
    }*/

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }
}
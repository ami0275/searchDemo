package com.amitraj.cricresass.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amitraj.cricresass.adapter.CustomRecyclerAdapter
import com.amitraj.cricresass.R
import com.amitraj.cricresass.viewmodel.ResturantViewModel
import kotlinx.android.synthetic.main.activity_main_res.*

class MainActivity : AppCompatActivity() {
    lateinit var resturantViewModel: ResturantViewModel
    lateinit var context: Context
    lateinit var adapter: CustomRecyclerAdapter
    val keyword: String = "chicken"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_res)
        context = this@MainActivity

        initView()

        initListener()
    }

    private fun initListener() {

        search_input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                fetchData(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    private fun initView() {
        val rvRecyclerView = findViewById<RecyclerView>(R.id.RecyclerView)
        resturantViewModel = ViewModelProvider(this).get(ResturantViewModel::class.java)
        resturantViewModel?.getResturantsDetails(context, keyword)!!.observe(this, Observer {
            rvRecyclerView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            adapter = CustomRecyclerAdapter(this, it)
            rvRecyclerView.adapter = adapter
        })
    }

    fun fetchData(text: String) {

        resturantViewModel?.getResturantsDetails(context, text)!!.observe(this, Observer {
            adapter.updateList(it)
        })
    }
}
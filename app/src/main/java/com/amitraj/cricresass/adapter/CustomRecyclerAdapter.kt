package com.amitraj.cricresass.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amitraj.cricresass.R


class CustomRecyclerAdapter(val context: Context, var resturantList: List<String>) :
    RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>() {


    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.imageView.setImageResource(R.drawable.ic_baseline_restaurant_menu_24)
        p0?.txtTitle?.text = resturantList[p1]
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.item_my_apps, p0, false)
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return resturantList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val imageView = itemView.findViewById<ImageView>(R.id.image_app)
        val txtTitle = itemView.findViewById<TextView>(R.id.textview)
    }

    // To get the data to search Category
    fun updateList(filteredCourseList: List<String>) {
        this.resturantList = filteredCourseList;
        notifyDataSetChanged();
    }
}
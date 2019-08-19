package com.rage.weatherapp.presentation.ui.citylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rage.weatherapp.R
import com.rage.weatherapp.presentation.model.CityModel
import kotlinx.android.synthetic.main.item_city.view.*
import java.util.*

class CityListAdapter : RecyclerView.Adapter<CityListAdapter.CityViewHolder>() {

    private var pages: Map<Int,List<CityModel>> = emptyMap()
    private var pageSize: Int = 1

    fun setData(pages: Map<Int,List<CityModel>>, pageSize: Int){
        this.pages = pages
        this.pageSize = pageSize
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_city,parent,false))
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        var count = 0
        for(value in pages.values) count+=value.size
        return count
    }

    inner class CityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind() = itemView.apply {
            val page = adapterPosition / pageSize
            val position = adapterPosition - (pageSize * page)
            val modelList = pages[page]
            if(modelList!=null) {
                val model = modelList[position]
                title.text = model.id.toString()
                name.text = model.name
            }
        }
    }
}
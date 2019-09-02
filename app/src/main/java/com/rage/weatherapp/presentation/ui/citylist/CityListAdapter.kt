package com.rage.weatherapp.presentation.ui.citylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rage.weatherapp.R
import com.rage.weatherapp.presentation.model.CityModel
import kotlinx.android.synthetic.main.item_city.view.*

class CityListAdapter : PagedListAdapter<CityModel, CityListAdapter.CityViewHolder>(CityListDiffUtil()) {

    var listener : ((CityModel)->Unit)?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false))
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CityListDiffUtil : DiffUtil.ItemCallback<CityModel>() {
        override fun areItemsTheSame(oldItem: CityModel, newItem: CityModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CityModel, newItem: CityModel): Boolean {
            return true
        }
    }

    inner class CityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(model: CityModel?) = itemView.apply {
            if (model != null) {
                title.text = model.id.toString()
                name.text = model.name
                setOnClickListener {
                    listener?.invoke(model)
                }
            }
        }
    }

}
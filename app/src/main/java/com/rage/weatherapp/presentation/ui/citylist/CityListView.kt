package com.rage.weatherapp.presentation.ui.citylist

import android.util.SparseArray
import com.rage.weatherapp.presentation.base.BaseView
import com.rage.weatherapp.presentation.model.CityModel
import java.util.*

interface CityListView : BaseView{
    fun showProgress(isVisible: Boolean)
    fun showErrorMessage(message: String)
    fun showCityList(cityPages: Map<Int,List<CityModel>>, pageSize: Int)
}
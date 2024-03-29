package com.rage.weatherapp.presentation.ui.citylist

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.rage.weatherapp.presentation.base.BaseView
import com.rage.weatherapp.presentation.model.CityModel

interface CityListView : BaseView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setCityDataSource(id: String,source: suspend (offset: Int, limit: Int) -> List<CityModel>)
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setProgressVisible(visible: Boolean, isAnimate: Boolean)
}
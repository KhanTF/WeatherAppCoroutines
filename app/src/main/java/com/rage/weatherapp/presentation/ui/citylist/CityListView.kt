package com.rage.weatherapp.presentation.ui.citylist

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.rage.weatherapp.presentation.base.BaseView
import com.rage.weatherapp.presentation.model.CityModel

interface CityListView : BaseView {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setCityDataSource(source: suspend (offset: Int, limit: Int) -> List<CityModel>)
}
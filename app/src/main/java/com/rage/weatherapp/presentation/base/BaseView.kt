package com.rage.weatherapp.presentation.base

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface BaseView: MvpView{
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showErrorMessage(message: String?)
}
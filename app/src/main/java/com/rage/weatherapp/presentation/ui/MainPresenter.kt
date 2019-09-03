package com.rage.weatherapp.presentation.ui

import com.arellomobile.mvp.InjectViewState
import com.rage.weatherapp.presentation.base.BasePresenter
import com.rage.weatherapp.presentation.ui.citylist.CityListScreen
import ru.terrakok.cicerone.Router

@InjectViewState
class MainPresenter constructor(private val router: Router) : BasePresenter<MainView>(){

    fun onFirstViewAttach(isRestore: Boolean) {
        if(!isRestore) router.newRootScreen(CityListScreen())
    }

}
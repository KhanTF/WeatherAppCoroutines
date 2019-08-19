package com.rage.weatherapp.presentation.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

abstract class BasePresenter<T : BaseView>: MvpPresenter<T>(), CoroutineScope by MainScope(){

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

}
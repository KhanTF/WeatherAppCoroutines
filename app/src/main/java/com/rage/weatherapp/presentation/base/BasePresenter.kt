package com.rage.weatherapp.presentation.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import kotlinx.coroutines.*

abstract class BasePresenter<T : BaseView>: MvpPresenter<T>(), CoroutineScope by MainScope(){

    private val handler = CoroutineExceptionHandler { _, throwable ->
        onHandleException(throwable)
    }

    protected open fun onHandleException(t: Throwable){
        t.printStackTrace()
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

}
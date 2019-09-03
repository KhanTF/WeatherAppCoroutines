package com.rage.weatherapp.presentation.base

import com.arellomobile.mvp.MvpPresenter
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

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
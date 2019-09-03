package com.rage.weatherapp.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun CoroutineScope.createDebounce(): CoroutineDebounce{
    return CoroutineDebounce(this)
}

class CoroutineDebounce(private val scope: CoroutineScope) {

    private var lastNotify: Long = 0L

    fun debounce(debounce: Long,f : ()->Unit){
        scope.launch {
            val lastLocalNotify = System.currentTimeMillis()
            lastNotify = lastLocalNotify
            delay(debounce)
            if(lastLocalNotify == lastNotify){
                f()
            }
        }
    }

}
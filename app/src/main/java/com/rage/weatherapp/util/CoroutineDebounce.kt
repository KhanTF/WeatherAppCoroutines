package com.rage.weatherapp.util

import kotlinx.coroutines.*

fun CoroutineScope.createDebounce(debounce: Long): CoroutineDebounce{
    return CoroutineDebounce(this,debounce)
}

class CoroutineDebounce(private val scope: CoroutineScope, private val debounce: Long) {

    private var lastNotify: Long = 0L

    fun debounce(f : ()->Unit){
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
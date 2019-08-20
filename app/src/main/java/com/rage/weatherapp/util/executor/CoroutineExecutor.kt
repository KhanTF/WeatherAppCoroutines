package com.rage.weatherapp.util.executor

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.Executor

class CoroutineExecutor(scope: CoroutineScope = GlobalScope, private val dispatcher: CoroutineDispatcher) : Executor, CoroutineScope by scope {
    override fun execute(runnable: Runnable) {
        launch(dispatcher) {
            runnable.run()
        }
    }
}
package com.rage.weatherapp.presentation.base

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpAppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder

abstract class BaseActivity : MvpAppCompatActivity(), BaseView, CoroutineScope by MainScope() {

    abstract val layoutId: Int

    protected open fun getContainerId(): Int = 0

    private val navigatorHolder: NavigatorHolder by inject()

    private val navigator: Navigator by inject {
        parametersOf(this, getContainerId())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onDestroy() {
        cancel()
        super.onDestroy()
    }

    override fun showErrorMessage(message: String?) {
        if (message != null)
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}
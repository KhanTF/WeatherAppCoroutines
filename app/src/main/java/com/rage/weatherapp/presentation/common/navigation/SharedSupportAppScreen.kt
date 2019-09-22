package com.rage.weatherapp.presentation.common.navigation

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class SharedSupportAppScreen(
        private val screen: SupportAppScreen,
        private val sharedParams: SharedSupportAppNavigator.SharedParams
) : SupportAppScreen() {

    override fun getActivityIntent(context: Context?): Intent? {
        return screen.getActivityIntent(context)
    }

    override fun getFragment(): Fragment? {
        return screen.fragment?.apply {

        }
    }

    override fun getScreenKey(): String? {
        return screen.screenKey
    }

    fun getSharedParams(): SharedSupportAppNavigator.SharedParams = sharedParams
}
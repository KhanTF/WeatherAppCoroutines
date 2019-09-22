package com.rage.weatherapp.presentation.ui.citylist

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen

class CityListScreen : SupportAppScreen(){
    override fun getFragment(): Fragment {
        return CityListFragment.getInstance()
    }
}
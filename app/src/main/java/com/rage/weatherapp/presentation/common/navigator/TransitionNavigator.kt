package com.rage.weatherapp.presentation.common.navigator

import android.view.View
import ru.terrakok.cicerone.Navigator

interface TransitionNavigator : Navigator{
    fun setSharedElement(sharedElements: List<Pair<View, String>>)
}
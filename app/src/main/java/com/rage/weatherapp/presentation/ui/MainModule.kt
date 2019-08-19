package com.rage.weatherapp.presentation.ui

import com.rage.weatherapp.R
import dagger.Module
import dagger.Provides
import kotlinx.android.synthetic.main.activity_main.view.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppNavigator

@Module(includes = [MainBuilder::class])
class MainModule{

    @Provides
    fun provideNavigator(mainActivity: MainActivity) : Navigator = SupportAppNavigator(mainActivity, R.id.main_container)

}
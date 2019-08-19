package com.rage.weatherapp.di.presentation

import com.rage.weatherapp.presentation.ui.MainActivity
import com.rage.weatherapp.presentation.ui.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder{
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun buildMainActivity(): MainActivity
}
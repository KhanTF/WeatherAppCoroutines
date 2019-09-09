package com.rage.weatherapp

import android.app.Application
import android.content.Context
import com.rage.weatherapp.di.AppModule
import com.rage.weatherapp.di.DataModule
import com.rage.weatherapp.di.DomainModule
import com.rage.weatherapp.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.definition.DefinitionFactory
import org.koin.dsl.module
import timber.log.Timber

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger()
            androidContext(this@WeatherApplication)
            modules(DataModule.getModules() + DomainModule.getModules() + PresentationModule.getModules())
        }
    }

}
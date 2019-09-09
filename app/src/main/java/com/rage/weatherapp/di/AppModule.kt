package com.rage.weatherapp.di

import android.app.Application
import android.content.Context
import org.koin.core.module.Module
import org.koin.dsl.module

class AppModule(private val application: Application) : ModuleRather{

    private val appModule = module {
        factory { application as Context }
    }

    override fun getModules(): List<Module> {
        return listOf(appModule)
    }

}
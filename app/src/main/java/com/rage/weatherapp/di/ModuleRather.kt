package com.rage.weatherapp.di

import org.koin.core.module.Module

interface ModuleRather{
    fun getModules() : List<Module>
}
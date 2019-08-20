package com.rage.weatherapp.di.data

import android.content.Context
import androidx.room.Room
import com.rage.weatherapp.data.db.city.CityDatabase
import com.rage.weatherapp.data.db.city.dao.CityDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): CityDatabase = Room
        .databaseBuilder(context, CityDatabase::class.java, "city.db")
        .createFromAsset("city.db")
        .build()

    @Provides
    fun provideCityDao(cityDatabase: CityDatabase): CityDao = cityDatabase.getCityDao()

}
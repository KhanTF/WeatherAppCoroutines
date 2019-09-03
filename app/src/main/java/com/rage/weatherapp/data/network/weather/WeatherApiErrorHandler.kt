package com.rage.weatherapp.data.network.weather

import com.rage.weatherapp.data.network.weather.pojo.WeatherInformationItem
import com.rage.weatherapp.domain.exceptions.NotInternetConnectionException
import com.rage.weatherapp.domain.exceptions.UnexpectedException
import kotlinx.io.IOException

class WeatherApiErrorHandler(weatherApi: WeatherApi) : WeatherApiDecorator(weatherApi) {

    private inline fun <T> catchApiError(f: () -> T): T {
        return try {
            f()
        } catch (e: IOException) {
            throw NotInternetConnectionException(e)
        } catch (e: Throwable) {
            throw UnexpectedException(e)
        }
    }

    override suspend fun getWeatherByCityId(id: Long, key: String): WeatherInformationItem = catchApiError { super.getWeatherByCityId(id, key) }

}
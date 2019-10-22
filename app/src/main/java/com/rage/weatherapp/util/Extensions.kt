package com.rage.weatherapp.util

private const val KELVIN = -273.15

fun Number.fromKevinToC(): Double {
    return this.toDouble() + KELVIN
}
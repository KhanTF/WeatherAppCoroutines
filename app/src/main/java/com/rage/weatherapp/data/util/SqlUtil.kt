package com.rage.weatherapp.data.util

object SqlUtil {

    private const val END_PATTERN = "%s%%"
    private const val START_PATTERN = "%%%s"

    fun getStartPattern(postfix: String): String = String.format(START_PATTERN,postfix)
    fun getEndPattern(prefix: String): String = String.format(END_PATTERN,prefix)

}
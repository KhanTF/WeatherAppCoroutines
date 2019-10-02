package com.rage.weatherapp.presentation.common

import android.view.View
import android.view.ViewGroup

fun View.setSelectPadding(left: Int = paddingLeft, top : Int = paddingTop, right: Int = paddingRight, bottom : Int = paddingBottom){
    setPadding(left, top, right, bottom)
}

fun View.getMarginLayoutParams(): ViewGroup.MarginLayoutParams{
    return layoutParams as ViewGroup.MarginLayoutParams
}

fun ViewGroup.MarginLayoutParams.setSelectMargin(left: Int = leftMargin, top : Int = topMargin, right: Int = rightMargin, bottom : Int = bottomMargin){
    leftMargin = left
    topMargin = top
    rightMargin = right
    bottomMargin = bottom
}
package com.rage.weatherapp.presentation.common.views.progressbar

sealed class CircleAlgorithm {

    abstract fun getAngleInterpolate(interpolate: Float, index: Int, size: Int): Float

    abstract fun getSweepInterpolate(interpolate: Float, index: Int, size: Int): Float

    abstract fun getOuterRadiusInterpolate(interpolate: Float): Float

    object ProgressiveCircleAlgorithm : CircleAlgorithm() {
        override fun getOuterRadiusInterpolate(interpolate: Float): Float = 1f

        override fun getAngleInterpolate(interpolate: Float, index: Int, size: Int): Float = interpolate * (size - index) * 2

        override fun getSweepInterpolate(interpolate: Float, index: Int, size: Int): Float = 1f
    }

    object ReverseProgressiveCircleAlgorithm : CircleAlgorithm(){
        override fun getAngleInterpolate(interpolate: Float, index: Int, size: Int): Float {
            val sign = if(index.rem(2) == 0) 1 else -1
            return  sign * interpolate * (size - index) * 2
        }

        override fun getSweepInterpolate(interpolate: Float, index: Int, size: Int): Float = 1f

        override fun getOuterRadiusInterpolate(interpolate: Float): Float = 1f

    }
}

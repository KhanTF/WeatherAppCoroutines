package com.rage.weatherapp.presentation.common.views.progressbar

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.animation.addListener
import com.rage.weatherapp.R
import kotlin.math.min

class GradientProgressBar @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attributeSet, defStyleAttr) {

    companion object {
        private const val MAX_ANGLE = 360f
        private const val DEFAULT_DURATION = 5000
    }

    private val paintProgress = Paint(Paint.ANTI_ALIAS_FLAG)
    private var interpolate: Float = 0f
    private var sweep = 90f
    private var strokeWidth = 5f
    private var countCircle = 5
    private var radius = 5f
    private var interval = 5f
    private var gradient = intArrayOf(Color.RED, Color.YELLOW, Color.GREEN)

    private val progressRect = RectF()
    private val animator = ValueAnimator.ofFloat(0f, 1f).apply {
        addUpdateListener {
            interpolate = it.animatedValue as Float
            postInvalidate()
        }
        duration = DEFAULT_DURATION.toLong()
        repeatMode = ObjectAnimator.RESTART
        interpolator = LinearInterpolator()
        repeatCount = ObjectAnimator.INFINITE
    }
    private val freeRadiusSpace: Float
        get() = measuredWidth / 2f - getOuterMeasuredRadius()
    private val visibleAnimator = ValueAnimator.ofFloat(0f, 1f).apply {
        duration = 500
        addListener(
            onStart = {
                visibility = VISIBLE
                setupAnimatedK()
            },
            onEnd = {
                setupAnimatedK()
            },
            onCancel = {
                setupAnimatedK()
            }
        )
        addUpdateListener {
            val value = it.animatedValue as Float
            extraFreeRadiusK = 1f - value
            alpha = value
            extraIntervalK = 2f - value
            postInvalidate()
        }
    }
    private val goneAnimator = ValueAnimator.ofFloat(1f, 0f).apply {
        duration = 500
        addListener(
            onStart = {
                setupAnimatedK()
            },
            onCancel = {
                visibility = GONE
                setupAnimatedK()
            },
            onEnd = {
                visibility = GONE
                setupAnimatedK()
            }
        )
        addUpdateListener {
            val value = it.animatedValue as Float
            extraFreeRadiusK = 1f - value
            alpha = value
            extraIntervalK = 2f - value
            postInvalidate()
        }
    }

    private fun setupAnimatedK() {
        extraFreeRadiusK = 0f
        alpha = 1f
        extraIntervalK = 1f
    }

    private var extraFreeRadiusK = 0f
    private var extraIntervalK = 1f

    var algorithm: CircleAlgorithm = CircleAlgorithm.ProgressiveCircleAlgorithm

    init {
        paintProgress.style = Paint.Style.STROKE
        paintProgress.strokeWidth = 10f
        if (attributeSet != null) {
            val typed = context.obtainStyledAttributes(
                attributeSet,
                R.styleable.GradientProgressBar,
                defStyleAttr,
                0
            )
            val gradientReference = typed.getResourceId(R.styleable.GradientProgressBar_gradient, 0)
            if (gradientReference != 0) {
                gradient = context.resources.getIntArray(gradientReference)
            }
            strokeWidth = typed.getDimension(R.styleable.GradientProgressBar_stroke, 5f)
            radius = typed.getDimension(R.styleable.GradientProgressBar_radius, 5f)
            interval = typed.getDimension(R.styleable.GradientProgressBar_interval, 5f)
            sweep = typed.getInteger(R.styleable.GradientProgressBar_sweep, 90).toFloat()
            countCircle = typed.getInteger(R.styleable.GradientProgressBar_circle_count, 5)
            animator.duration =
                typed.getInteger(R.styleable.GradientProgressBar_duration, DEFAULT_DURATION)
                    .toLong()
            typed.recycle()
        }
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        if (visibility == VISIBLE) {
            animator.resume()
        } else {
            animator.pause()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paintProgress.strokeWidth = strokeWidth
        val outerRadius =
            (getOuterMeasuredRadius() - strokeWidth / 2f + freeRadiusSpace * extraFreeRadiusK) * algorithm.getOuterRadiusInterpolate(
                interpolate
            )
        val centerX = width / 2f
        val centerY = height / 2f
        progressRect.set(
            centerX - outerRadius,
            centerY - outerRadius,
            centerX + outerRadius,
            centerY + outerRadius
        )
        for (i in 0 until countCircle) {
            paintProgress.color = getColor(i)
            canvas.drawArc(
                progressRect,
                algorithm.getAngleInterpolate(interpolate, i, countCircle) * MAX_ANGLE,
                algorithm.getSweepInterpolate(interpolate, i, countCircle) * sweep,
                false,
                paintProgress
            )
            progressRect.inset(interval * extraIntervalK, interval * extraIntervalK)
        }
    }

    fun setVisibility(visibility: Boolean, isAnimate: Boolean) {
        if (getVisibility() == VISIBLE && visibility) {
            return
        }
        if (getVisibility() == GONE && !visibility) {
            return
        }
        if (getVisibility() == INVISIBLE && !visibility) {
            setVisibility(GONE)
            return
        }
        if (isAnimate) {
            if (visibility) {
                goneAnimator.cancel()
                visibleAnimator.start()
            } else {
                visibleAnimator.cancel()
                goneAnimator.start()
            }
        } else {
            setupAnimatedK()
            setVisibility(if (visibility) VISIBLE else GONE)
        }
    }

    private fun getOuterMeasuredRadius(): Float {
        return ((strokeWidth + interval) * countCircle - interval) + radius
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val width = when (widthMode) {
            MeasureSpec.AT_MOST -> {
                min((getOuterMeasuredRadius() * 2).toInt(), widthSize)
            }
            MeasureSpec.EXACTLY -> widthSize
            else -> (getOuterMeasuredRadius() * 2).toInt()
        }
        val height = when (heightMode) {
            MeasureSpec.AT_MOST -> {
                min((getOuterMeasuredRadius() * 2).toInt(), heightSize)
            }
            MeasureSpec.EXACTLY -> heightSize
            else -> (getOuterMeasuredRadius() * 2).toInt()
        }
        setMeasuredDimension(width, height)
    }

    private fun getColor(indexLine: Int): Int {
        return gradient[indexLine.rem(gradient.size)]
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        animator.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animator.cancel()
        visibleAnimator.cancel()
        goneAnimator.cancel()
    }

}
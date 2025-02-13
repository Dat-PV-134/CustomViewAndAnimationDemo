package com.rekoj134.customviewandanimationdemo

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnRepeat
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import kotlin.math.abs

class CircleWithAnimation : View {
    private var radius = 100f
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.parseColor("#E13C3C")
    }
    private var scaleFactor = 1f
    private var scaleFactor2 = 1f


    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs) {
        initView()
    }

    private fun initView() {
        startCircleAnimation()
    }

    private fun startCircleAnimation() {
        val animator = ValueAnimator.ofFloat(0.1f, 0.4f)
        animator.duration = 500
        animator.repeatCount = ValueAnimator.INFINITE
        animator.repeatMode = ValueAnimator.RESTART
        animator.interpolator = FastOutLinearInInterpolator()
        animator.addUpdateListener {
            scaleFactor = it.animatedValue as Float
            invalidate()
        }
        animator.start()

        val animator2 = ValueAnimator.ofFloat(0f, 0.8f)
        animator2.duration = 1000
        animator2.repeatCount = ValueAnimator.INFINITE
        animator2.repeatMode = ValueAnimator.RESTART
        animator2.interpolator = FastOutSlowInInterpolator()
        animator2.addUpdateListener {
            scaleFactor2 = it.animatedValue as Float
            invalidate()
        }
        animator2.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCircle(canvas)
    }

    private fun drawCircle(canvas: Canvas) {
        paint.alpha = 150
        canvas.drawCircle(width/2f, height/2f, radius * scaleFactor2, paint)
        paint.alpha = 255
        canvas.drawCircle(width/2f, height/2f, radius * scaleFactor, paint)
    }
}
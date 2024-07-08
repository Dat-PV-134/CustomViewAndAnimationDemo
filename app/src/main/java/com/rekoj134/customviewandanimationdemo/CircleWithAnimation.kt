package com.rekoj134.customviewandanimationdemo

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.interpolator.view.animation.FastOutLinearInInterpolator

class CircleWithAnimation : View {
    private var radius = 100f
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.parseColor("#E13C3C")
    }
    private var scaleFactor = 1f
    private var scaleFactorForCenter = 1f
    private var alphaFactor = 1f

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
        val animator = ValueAnimator.ofFloat(0.5f, 1f)
        animator.duration = 500
        animator.repeatCount = ValueAnimator.INFINITE
        animator.repeatMode = ValueAnimator.REVERSE
        animator.interpolator = FastOutLinearInInterpolator()
        animator.addUpdateListener {
            scaleFactor = it.animatedValue as Float
            invalidate()
        }
        animator.start()

        val animator2 = ValueAnimator.ofFloat(0.5f, 1f)
        animator2.duration = 300
        animator2.repeatCount = ValueAnimator.INFINITE
        animator2.repeatMode = ValueAnimator.REVERSE
        animator2.interpolator = FastOutLinearInInterpolator()
        animator2.addUpdateListener {
            scaleFactorForCenter = it.animatedValue as Float
            invalidate()
        }
        animator2.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCircle(canvas)
    }

    private fun drawCircle(canvas: Canvas) {
        paint.alpha = (0.2 * 255).toInt()
        canvas.drawCircle(width/2f, height/2f, (radius + radius*0.6f) * scaleFactor, paint)
        paint.alpha = (0.4 * 255).toInt()
        canvas.drawCircle(width/2f, height/2f, (radius + radius*0.4f) * scaleFactor, paint)
        paint.alpha = (0.6 * 255).toInt()
        canvas.drawCircle(width/2f, height/2f, (radius + radius*0.2f) * scaleFactor, paint)
        paint.alpha = 255
        canvas.drawCircle(width/2f, height/2f, radius * scaleFactorForCenter, paint)
    }
}
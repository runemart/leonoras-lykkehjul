package com.runemartin.leonorasapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.toColorInt
import kotlin.math.floor

class Wheel @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
) : View(context, attrs, defStyle) {

    private val paint = Paint().apply {
        style = Paint.Style.FILL
    }

    private val colors = listOf(
        "#fed102".toColorInt(),
        "#ffa504".toColorInt(),
        "#ff4f44".toColorInt(),
        "#bc5fd4".toColorInt(),
        "#1198f4".toColorInt(),
        "#48c521".toColorInt(),
    )

    fun colorForDegree(degree: Float): Int {
        val adjustedDegree = (degree + 90) % 360
        val colorNr = floor(adjustedDegree / degreePrSection).toInt()
        return colors[colors.size - colorNr - 1]
    }

    private val degreePrSection = 360f / colors.size

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val h = canvas.height.toFloat()
        val w = canvas.width.toFloat()
        val oval = RectF(0f, 0f, w, h)

        for (i in colors.indices) {
            paint.color = colors[i]
            canvas.drawArc(oval, i * degreePrSection, degreePrSection, true, paint)
        }
    }

}
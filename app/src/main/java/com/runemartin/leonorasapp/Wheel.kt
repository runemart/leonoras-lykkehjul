package com.runemartin.leonorasapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.toColorInt

class Wheel @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
) : View(context, attrs, defStyle) {


    private val paint = Paint().apply {
        style = Paint.Style.FILL
    }

    private val colors = listOf(
        "#CC0088".toColorInt(),
        "#06D6A0".toColorInt(),
        "#1B9AAA".toColorInt(),
        "#EF476F".toColorInt(),
        "#FFC43D".toColorInt(),
        "#666666".toColorInt(),
    )

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
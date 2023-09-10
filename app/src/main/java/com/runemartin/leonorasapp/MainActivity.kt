package com.runemartin.leonorasapp

import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import android.viewbinding.library.activity.viewBinding
import androidx.appcompat.app.AppCompatActivity
import com.runemartin.leonorasapp.databinding.ActivityMainBinding
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.emitter.Emitter
import nl.dionsegijn.konfetti.core.models.Size

class MainActivity : AppCompatActivity() {

    private val viewBinding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewBinding.apply {
            button.setOnClickListener {
                wheel.rotation = wheel.rotation % 360
                wheel.animate()
                    .rotation(wheel.rotation - 90f)
                    .setDuration(500)
                    .withEndAction { spinWheel() }
            }
        }
    }

    private fun spinWheel() {
        viewBinding.wheel.animate()
            .rotation((360 * 5 + Math.random() * 360 * 10).toFloat())
            .setInterpolator(DecelerateInterpolator())
            .setDuration(7000)
            .withEndAction { konfetti() }
            .start()
    }

    private fun konfetti() {
        val colorInt = viewBinding.wheel.colorForDegree(viewBinding.wheel.rotation)
        try {
            viewBinding.konfetti.start(
                Party(
                    colors = listOf(colorInt),
                    emitter = Emitter(duration = 200).max(100),
                    speed = 10f,
                    maxSpeed = 30f,
                    damping = 0.95f,
                    angle = 270,
                    spread = 90,
                    size = listOf(Size.LARGE),
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}
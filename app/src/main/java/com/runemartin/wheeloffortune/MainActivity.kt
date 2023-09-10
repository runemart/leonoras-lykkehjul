package com.runemartin.wheeloffortune

import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import android.viewbinding.library.activity.viewBinding
import androidx.appcompat.app.AppCompatActivity
import com.runemartin.wheeloffortune.databinding.MainActivityBinding
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.emitter.Emitter
import nl.dionsegijn.konfetti.core.models.Size

class MainActivity : AppCompatActivity() {

    private val viewBinding: MainActivityBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.button.setOnClickListener { preLoadWheel() }
    }

    private fun preLoadWheel() = viewBinding.apply {
        wheel.rotation = wheel.rotation % 360
        wheel.animate()
            .rotation(wheel.rotation - 90f)
            .setDuration(500)
            .withEndAction { spinWheel() }
    }

    private fun spinWheel() = viewBinding.apply {
        wheel.animate()
            .rotation((360 * 5 + Math.random() * 360 * 10).toFloat())
            .setInterpolator(DecelerateInterpolator())
            .setDuration(7000)
            .withEndAction { konfetti() }
            .start()
    }

    private fun konfetti() = viewBinding.apply {
        val colorInt = wheel.colorForDegree(wheel.rotation)
        try {
            konfetti.start(
                Party(
                    colors = listOf(colorInt),
                    emitter = Emitter(duration = 200).perSecond(3000),
                    speed = 10f,
                    maxSpeed = 60f,
                    damping = 0.95f,
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}
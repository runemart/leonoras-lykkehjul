package com.runemartin.leonorasapp

import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.knapp)
        val wheel = findViewById<View>(R.id.wheel)

        button.setOnClickListener {
            wheel.rotation = wheel.rotation % 360
            wheel.animate()
                .rotation(wheel.rotation - 90f)
                .setDuration(500).withEndAction {
                    wheel.animate()
                        .rotation((360*5 + Math.random() * 360 * 10).toFloat())
                        .setInterpolator(DecelerateInterpolator(3f))
                        .setDuration(10000)
                        .start()
                }
        }

    }

}
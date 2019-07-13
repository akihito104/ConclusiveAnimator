package com.freshdigitable.conclusiveanimator

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val image = findViewById<View>(R.id.image)
        val anim = ValueAnimator.ofFloat(0f, 1f).apply {
            startDelay = 200
            duration = 5000
        }
        findViewById<Button>(R.id.start).setOnClickListener {
            if (anim.isRunning) {
                return@setOnClickListener
            }
            anim.setupWithConclusiveListener {
                image.alpha = it.animatedValue as Float
            }
            anim.start()
        }
        findViewById<Button>(R.id.cancel).setOnClickListener {
            anim.cancel()
        }
        findViewById<Button>(R.id.end).setOnClickListener {
            anim.end()
        }
    }
}

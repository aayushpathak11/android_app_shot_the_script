package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.airbnb.lottie.LottieAnimationView
import kotlinx.android.synthetic.main.activity_splash.*

class splash : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {




        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val lottie= findViewById<LottieAnimationView>(R.id.lottie)

        lottie.animate().setDuration(2700).setStartDelay(2900)


        Handler().postDelayed({
            val intent = Intent(this@splash, MainActivity::class.java)
            startActivity(intent)
            finish() } ,   2000)

    }
}
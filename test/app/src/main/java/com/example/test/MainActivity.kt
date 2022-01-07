package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.example.test.R.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        var btn:ImageButton = findViewById(id.btn);
        var status= 0;
        btn.setOnClickListener(){

            if(status==0) {
                btn.setImageResource(R.drawable.medium)
                status = 1
            }
            else if(status==1) {
                btn.setImageResource(R.drawable.high)
                status = 2
            }
            else {
                btn.setImageResource(R.drawable.low)
                status = 0
            }
        }

    }
}
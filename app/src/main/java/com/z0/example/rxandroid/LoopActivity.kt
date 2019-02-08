package com.z0.example.rxandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_loop.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class LoopActivity : AppCompatActivity() {

    val samples = Arrays.asList("banana", "orange", "apple", "apple mango", "melon", "watermelon")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loop)
        tvLog.text = ">>>Log :: find \"apple\""
        loop()
    }

    fun loop() {
        Observable.fromIterable(samples)
            .filter { s-> s.contains("apple") }
            .subscribe { it -> tvLog.text = it }
    }


}

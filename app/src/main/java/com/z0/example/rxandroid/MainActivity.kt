package com.z0.example.rxandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }

    fun btnClick(view: View) {
        when(view.id) {
            R.id.btnLoop ->{
                startActivity(Intent(this, LoopActivity::class.java))
            }
            R.id.btnTimer -> {
                startActivity(Intent(this, TimerTaskActivity::class.java))
            }
            R.id.btnPolling -> {
                startActivity(Intent(this, PollingActivity::class.java))
            }
            R.id.btnBanner -> {
                startActivity(Intent(this, BannerActivity::class.java))
            }
        }
    }
}

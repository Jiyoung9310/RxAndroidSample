package com.z0.example.rxandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_timer_task.*
import java.util.*

class TimerTaskActivity : AppCompatActivity() {

    private val DELAY = 0L
    private val PERIOD = 3000L

    private val mTimer = Timer()
    private var string = ""
    private var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer_task)

        timerStart()
    }

    fun timerStart() {
        mTimer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                i++
                string += "Log $i\n"
                //tvTimerLog.text = string
                Log.d("TImerTaskAct", string)
            }
        }, DELAY, PERIOD)
    }

    fun timerStop() {
        mTimer.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        timerStop()
    }
}

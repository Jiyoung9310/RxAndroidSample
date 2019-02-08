package com.z0.example.rxandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit
import android.content.Context
import android.view.View
import android.widget.ArrayAdapter
import io.reactivex.observers.DisposableObserver
import kotlinx.android.synthetic.main.activity_polling.*
import kotlin.collections.ArrayList


class PollingActivity : AppCompatActivity() {

    private var mLogs = mutableListOf<String>()
    private lateinit var mLogAdapter: LogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_polling)

        setupLogger()
    }

    fun setClick(view: View) {
        when (view.id) {
            R.id.btn_polling -> {
                startPollingV1()
            }
            R.id.btn_polling2 -> {
                startPollingV2()
            }
        }
    }

    fun startPollingV1() {
        val ob = Observable.interval(3, 3, TimeUnit.SECONDS)
            .flatMap { o -> Observable.just("polling #1 " + o.toString()) }

        ob.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<String>() {
                override fun onComplete() {
                }

                override fun onNext(t: String) {
                    log(t)
                }

                override fun onError(e: Throwable) {
                }
            })
        /*val ob = Observable.interval(3, TimeUnit.SECONDS)
            .flatMap { o -> Observable.just("polling #1") }
                .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ st -> log(st)  }*/
    }

    fun startPollingV2() {
        val ob2 = Observable.just("polling #2")
            .repeatWhen { o -> o.delay(3, TimeUnit.SECONDS) }
        ob2.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{s -> log(s)}
    }

    private fun setupLogger() {
        mLogs = ArrayList()
        mLogAdapter = LogAdapter(this, ArrayList())
        lv_polling_log.adapter= mLogAdapter
    }

    private fun log(log: String) {
        mLogs.add(log)
        mLogAdapter.clear()
        mLogAdapter.addAll(mLogs)

    }

    inner class LogAdapter(context: Context, logs: List<String>) :
        ArrayAdapter<String>(context, R.layout.textview_log, R.id.tv_log, logs)
}

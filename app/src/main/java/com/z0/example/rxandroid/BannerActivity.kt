package com.z0.example.rxandroid

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.activity_pager.*
import kotlinx.android.synthetic.main.imageview_item.view.*

class BannerActivity : AppCompatActivity() {

    private val list: ArrayList<String> = ArrayList<String>()

    init{
        list.add("https://t1.daumcdn.net/cfile/tistory/1776FE324CA28AC560")
        list.add("https://img1.daumcdn.net/thumb/R720x0.q80/?scode=mtistory&fname=http%3A%2F%2Fcfile21.uf.tistory.com%2Fimage%2F161DB7124CAD17CF633255")
        list.add("https://pbs.twimg.com/media/Bn41TFPIEAALDOC.jpg")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pager)

        vpBanner.adapter = BannerPagerAdapter(this)

    }

    inner class BannerPagerAdapter(private val context: Context): PagerAdapter(){
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.imageview_item, container, false)
            view.ivImage.setImageURI(list[position].toUri())
            container.addView(view)

            return view
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

        override fun getCount(): Int {
            return list.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }
    }
}

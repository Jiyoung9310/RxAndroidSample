package com.z0.example.rxandroid

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_pager.*
import kotlinx.android.synthetic.main.activity_pager.view.*
import kotlinx.android.synthetic.main.imageview_item.view.*
import java.util.concurrent.TimeUnit

class BannerActivity : AppCompatActivity() {

    private val list: ArrayList<Int> = ArrayList<Int>()
    private lateinit var auto: Observable<Int>

    init{
        list.add(R.color.colorPrimaryDark)
        list.add(R.color.black)
        list.add(R.color.grey)
        list.add(R.color.colorAccent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pager)

        vpBanner.adapter = BannerAdapter(this)

        auto = Observable.interval(3, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { data -> (data + 1).toInt()%list.size }
        auto.subscribe { vpBanner.setCurrentItem(it, true) }

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    inner class BannerAdapter (private val context: Context) : RecyclerView.Adapter<BannerViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
            return BannerViewHolder(LayoutInflater.from(context).inflate(R.layout.imageview_item, parent, false))
        }

        override fun getItemCount(): Int = list.size


        override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
            holder.itemView.ivImage.setBackgroundColor(resources.getColor(list[position]))
        }
    }

    inner class BannerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
        }
    }

    inner class BannerPagerAdapter(private val context: Context): PagerAdapter(){
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.imageview_item, container, false)
            view.ivImage.setBackgroundColor(resources.getColor(list[position]))
            container.addView(view)

            return view
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) = container.removeView(`object` as View)


        override fun getCount(): Int {
            return list.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }
    }
}

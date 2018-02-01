package io.jeffchang.detroitlabschallenge.ui.now.forecastdayweather.views

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.jeffchang.detroitlabschallenge.R
import io.jeffchang.detroitlabschallenge.data.model.ForecastDaoObject
import kotlinx.android.synthetic.main.viewpager_forecast_day_weather.view.*
import timber.log.Timber

/**
 * Created by jeffreychang on 1/31/18.
 */

class ForecastDayViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context) {

    constructor(context: Context): this(context, null) {
        init(context)
    }

    private fun init(context: Context) {
        inflate(context, R.layout.viewpager_forecast_day_weather, this)
        indicator_viewpager_ten_day_weather.setViewPager(this)
    }

    var forecastDay: ForecastDaoObject? = null
        set(forecastDayWeather) {
            adapter = ForecastDayPagerAdapter(context, forecastDayWeather)
        }

    private class ForecastDayPagerAdapter(val context: Context,
                                          val forecastDay: ForecastDaoObject?) : PagerAdapter() {

        override fun instantiateItem(collection: ViewGroup, position: Int): Any {
            val inflater = LayoutInflater.from(context)
            val layout = inflater.inflate(R.layout.view_page_forecast_day_weather, collection, false) as ViewGroup
            collection.addView(layout)
            Timber.e(forecastDay?.forecastDay?.size!!.toString())
            return layout
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun getCount(): Int {
            return forecastDay?.forecastDay?.size!!
        }

    }

}
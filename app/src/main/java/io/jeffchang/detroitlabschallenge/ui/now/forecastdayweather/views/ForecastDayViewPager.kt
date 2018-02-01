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

/**
 * Created by jeffreychang on 1/31/18.
 */

class ForecastDayViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context) {

    constructor(context: Context): this(context, null) {
        init(context)
    }

    private fun init(context: Context) {
        inflate(context, R.layout.viewpager_forecast_day_weather, this)
    }

    var forecastDay: ForecastDaoObject? = null
        set(forecastDayWeather) {
            adapter = ForecastDayPagerAdapter(context, forecastDayWeather)
        }

    private class ForecastDayPagerAdapter(val context: Context,
                                          val forecastDay: ForecastDaoObject?) : PagerAdapter() {

        private var numberOfPages: Int = forecastDay?.forecastDay?.size!! / 3

        private var pageModulo: Int = forecastDay?.forecastDay?.size!! % 3

        init {
            if (pageModulo % 3 != 0)
                numberOfPages += 1
        }

        override fun instantiateItem(collection: ViewGroup, position: Int): Any {
            val inflater = LayoutInflater.from(context)
            val layout = inflater.inflate(R.layout.view_page_forecast_day_weather,
                    collection, false) as ViewGroup
            collection.addView(layout)
            val multipleOfThree = position * 3

            val forecastDayArrayList = ArrayList(forecastDay?.forecastDay!!)
            val firstForecastDay = forecastDayArrayList[0]
            firstForecastDay.date?.weekdayShort = "Today"
            forecastDayArrayList[0] = firstForecastDay

            val firstForecast: ForecastDayView = layout.findViewById(R.id.view_page_first)
            val secondForecast: ForecastDayView = layout.findViewById(R.id.view_page_second)
            val thirdForecast: ForecastDayView = layout.findViewById(R.id.view_page_third)
            try {
                firstForecast.forecastDay = forecastDayArrayList[multipleOfThree]
                secondForecast.forecastDay = forecastDayArrayList[multipleOfThree + 1]
                thirdForecast.forecastDay = forecastDayArrayList[multipleOfThree + 2]

            } catch (error: IndexOutOfBoundsException) {

                /* Avoids throwing when the position is out of bounds.
                   Confused about how ViewPager handles its position   */
            }

            return layout
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun getCount(): Int {
            return numberOfPages
        }

    }

}
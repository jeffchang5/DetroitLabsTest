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
import io.jeffchang.detroitlabschallenge.data.model.LatLng

/**
 * Created by jeffreychang on 1/31/18.
 */

class ForecastDayViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context) {

    var onForecastDayClicked: ((LatLng?,  weatherDate: String) -> Unit)? = null

    var forecastDay: ForecastDaoObject? = null
        set(forecastDayWeather) {
            val forecastDayAdapter = ForecastDayPagerAdapter(context, forecastDayWeather)
            forecastDayAdapter.onWeatherDayClicked = onForecastDayClicked
            adapter = forecastDayAdapter
        }

    constructor(context: Context): this(context, null) {
        init(context)
    }

    private fun init(context: Context) {
        inflate(context, R.layout.viewpager_forecast_day_weather, this)
    }

    private class ForecastDayPagerAdapter(val context: Context,
                                          val forecastDay: ForecastDaoObject?) : PagerAdapter() {

        var onWeatherDayClicked: ((LatLng?,  weatherDate: String) -> Unit)? = null

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

            val firstForecast: ForecastDayView = layout.findViewById(R.id.view_page_forecastday_first)
            val secondForecast: ForecastDayView = layout.findViewById(R.id.view_page_forecastday_second)
            val thirdForecast: ForecastDayView = layout.findViewById(R.id.view_page_forecastday_third)

            try {
                firstForecast.forecastDay = forecastDayArrayList[multipleOfThree]
                firstForecast.setOnClickListener({
                    onWeatherDayClicked?.invoke(forecastDay.latLng,
                            "${forecastDayArrayList[multipleOfThree].date?.month}," +
                                    "${forecastDayArrayList[multipleOfThree].date?.day}," +
                                    "${forecastDayArrayList[multipleOfThree].date?.year}"
                            ) })

                secondForecast.forecastDay = forecastDayArrayList[multipleOfThree + 1]
                secondForecast.setOnClickListener({
                    onWeatherDayClicked?.invoke(forecastDay.latLng,
                    "${forecastDayArrayList[multipleOfThree + 1].date?.month}," +
                    "${forecastDayArrayList[multipleOfThree + 1].date?.day}," +
                            "${forecastDayArrayList[multipleOfThree + 1].date?.year}"
                    ) })

                thirdForecast.forecastDay = forecastDayArrayList[multipleOfThree + 2]
                thirdForecast.setOnClickListener({
                    onWeatherDayClicked?.invoke(forecastDay.latLng,
                            "${forecastDayArrayList[multipleOfThree + 2].date?.month}," +
                                    "${forecastDayArrayList[multipleOfThree + 2].date?.day}," +
                                    "${forecastDayArrayList[multipleOfThree + 2].date?.year}"
                    ) })

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
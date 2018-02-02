package io.jeffchang.detroitlabschallenge.ui.now.forecastdayweather.views

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.squareup.picasso.Picasso
import io.jeffchang.detroitlabschallenge.R
import io.jeffchang.detroitlabschallenge.data.model.ForecastDay
import kotlinx.android.synthetic.main.view_forecast_day.view.*
import android.view.MotionEvent



/**
 * Created by jeffreychang on 2/1/18.
 */
class ForecastDayView: LinearLayout {

    var forecastDay: ForecastDay? = null
        set(forecastDay) {
            updateUI(forecastDay)
        }

    private fun updateUI(forecastDay: ForecastDay?) {
        textview_dayoftheweek_forecast_day.text = forecastDay?.date?.weekdayShort
        textview_dayoftheweek_forecast_day.text = forecastDay?.date?.weekdayShort
        textview_date_forecast_day.text = String.format(resources.getString(R.string.date)
                ,forecastDay?.date?.monthnameShort
                ,forecastDay?.date?.day)
        textview_high_forecast_day.text = String.format(resources.getString(R.string.high_temp_C),
                forecastDay?.high?.celsius?.toInt())
        textview_low_forecast_day.text = String.format(resources.getString(R.string.low_temp_C),
                forecastDay?.low?.celsius?.toInt())
        textview_condition_forecast_day.text = forecastDay?.conditions
        Picasso.with(context).load(forecastDay?.iconUrl).into(imageview_weather_forecast_day)
    }

    constructor(context: Context): super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        init(context)
    }

    /*
     *  Hack to have parent handle the touch event.
     *  I have no idea what this does.
    */

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        onTouchEvent(ev)
        return false
    }
    private fun init(context: Context) {
        inflate(context, R.layout.view_forecast_day, this)
    }
}
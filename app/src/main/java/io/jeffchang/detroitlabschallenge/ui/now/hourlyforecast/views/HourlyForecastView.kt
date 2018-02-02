package io.jeffchang.detroitlabschallenge.ui.now.hourlyforecast.views

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import com.squareup.picasso.Picasso
import io.jeffchang.detroitlabschallenge.R
import io.jeffchang.detroitlabschallenge.data.model.HourlyForecast
import kotlinx.android.synthetic.main.view_hourly_forecast.view.*

/**
 * Created by jeffreychang on 2/2/18.
 */

class HourlyForecastView: ConstraintLayout {

    var hourlyForecast: HourlyForecast? = null
        set(forecast) {
            updateUI(forecast)
        }

    private fun updateUI(hourlyForecast: HourlyForecast?) {
        Picasso.with(context).load(hourlyForecast?.iconUrl).into(imageview_icon_hourly_forecast)

        textview_hour_hourly_forecast.text = hourlyForecast?.fCTTIME?.civil
        textview_temp_hourly_forecast.text = String.format(
                resources.getString(R.string.temp_label_C,
                        hourlyForecast?.temp?.metric))
        textview_feelslike_hourly_forecast.text = String.format(
                resources.getString(R.string.feelslike_label_C,
                        hourlyForecast?.feelslike?.metric))
        textview_condition_hourly_forecast.text = hourlyForecast?.condition
    }

    constructor(context: Context): super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    private fun init(context: Context) {
        inflate(context, R.layout.view_hourly_forecast, this)
    }
}
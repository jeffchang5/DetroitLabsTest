package io.jeffchang.detroitlabschallenge.ui.now.forecastdayweather.views

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.RelativeLayout
import io.jeffchang.detroitlabschallenge.R
import io.jeffchang.detroitlabschallenge.data.model.ForecastDay
import kotlinx.android.synthetic.main.view_circular_progress.view.*

/**
 * Created by jeffreychang on 2/1/18.
 */
class ForecastDayView: LinearLayout {

    var forecastDay: ForecastDay? = null
        set(text) {
            
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

    private fun init(context: Context) {
        inflate(context, R.layout.view_forecast_day, this)
    }
}
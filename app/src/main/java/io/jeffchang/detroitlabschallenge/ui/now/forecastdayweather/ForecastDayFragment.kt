package io.jeffchang.detroitlabschallenge.ui.now.forecastdayweather

import android.content.Context
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.pixelcan.inkpageindicator.InkPageIndicator
import io.jeffchang.detroitlabschallenge.DetroitLabsApplication
import io.jeffchang.detroitlabschallenge.MainActivity
import io.jeffchang.detroitlabschallenge.R
import io.jeffchang.detroitlabschallenge.data.model.ForecastDaoObject
import io.jeffchang.detroitlabschallenge.data.model.LatLng
import io.jeffchang.detroitlabschallenge.ui.common.InternetFragment
import io.jeffchang.detroitlabschallenge.ui.now.forecastdayweather.views.ForecastDayViewPager
import io.jeffchang.detroitlabschallenge.ui.now.hourlyforecast.HourlyForecastFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException

/**
 * Created by jeffreychang on 1/31/18.
 */
class ForecastDayFragment : InternetFragment() {

    override var layoutResourceID: Int = R.layout.fragment_forecastday_weather
    private val forecastDayWeatherViewModel = DetroitLabsApplication.injectForcastDayViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadCircularProgressBar("Loading 10 day forecast.")
    }
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        (context as? MainActivity)?.onForecastDayCallback = ::onGetLocationFromActivity
    }

    override fun onDetach() {
        super.onDetach()
        removeOnForecastDayCallback()
    }

    private fun removeOnForecastDayCallback() {
        (context as? MainActivity)?.onForecastDayCallback = null
    }

    private fun onGetLocationFromActivity(location:  Location) {
        removeOnForecastDayCallback()
        getForecastDay(location)
    }

    private fun getForecastDay(location: Location) {
        forecastDayWeatherViewModel
                .getForecastDays("${location.latitude},${location.longitude}")
                .doOnError({ })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ forecastDay ->
                    updateUI(forecastDay)
                }, { e ->
                    if (e is UnknownHostException)
                        loadNoInternet({ getForecastDay(location) }, null)
                })
    }
    private fun onForecastDayClicked(latLng: LatLng?, weatherDate: String) {
        (activity as MainActivity)
                .replaceFragmentWithBackStack(
                        HourlyForecastFragment.newInstance(latLng?.latLong!!, weatherDate))
    }

    private fun updateUI(forecastDay: ForecastDaoObject) {
        loadMainContent()
        val viewPager: ForecastDayViewPager = (childView as LinearLayout)
                .getChildAt(0) as ForecastDayViewPager
        val indicator: InkPageIndicator = (childView as LinearLayout)
                .getChildAt(1) as InkPageIndicator
        viewPager.onForecastDayClicked = ::onForecastDayClicked
        viewPager.forecastDay = forecastDay
        indicator.setViewPager(viewPager)
    }

    companion object {
        fun newInstance(): ForecastDayFragment {
            return ForecastDayFragment()
        }
    }

}
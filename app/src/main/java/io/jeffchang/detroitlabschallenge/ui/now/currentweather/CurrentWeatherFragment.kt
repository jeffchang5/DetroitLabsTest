package io.jeffchang.detroitlabschallenge.ui.now.currentweather

import android.content.Context
import android.location.Location
import android.os.Bundle
import com.squareup.picasso.Picasso
import io.jeffchang.detroitlabschallenge.DetroitLabsApplication
import io.jeffchang.detroitlabschallenge.MainActivity
import io.jeffchang.detroitlabschallenge.R
import io.jeffchang.detroitlabschallenge.data.model.CurrentObservation
import io.jeffchang.detroitlabschallenge.ui.common.InternetFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_current_weather.*
import java.net.UnknownHostException

/**
 * Created by jeffreychang on 1/29/18.
 */

class CurrentWeatherFragment: InternetFragment() {

    override var layoutResourceID: Int = R.layout.fragment_current_weather

    private val currentWeatherViewModel = DetroitLabsApplication.injectCurrentWeatherViewModel()

    var currentWeatherCallback: ((currentObservation: CurrentObservation) -> Unit)? = null

    private var isCurrentWeatherLoaded: Boolean = false

    private fun onGetLocationFromActivity(location: Location) {
        getCurrentWeather(location)
    }

    private fun getCurrentWeather(location: Location) {
        if (!isCurrentWeatherLoaded) {
            currentWeatherViewModel
                    .getCurrentWeather("${location.latitude},${location.longitude}")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ currentConditions ->
                        loadMainContent()
                        currentWeatherCallback?.invoke(currentConditions)
                        updateUI(currentConditions)
                    }, { e ->
                        if (e is UnknownHostException)
                            loadNoInternet({ getCurrentWeather(location) }, 300)
                    })
        }
    }

    private fun getCachedCurrentWeather() {
        currentWeatherViewModel.getCachedWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ currentConditions ->
                    loadMainContent()
                    isCurrentWeatherLoaded = true
                    currentWeatherCallback?.invoke(currentConditions)
                    updateUI(currentConditions)
                },{ e ->
                    if (e is UnknownHostException)
                        loadNoInternet({ getCachedCurrentWeather() }, 300)
                })
    }

    private fun updateUI(currentObservation: CurrentObservation?) {
        if (currentObservation != null) {
            textview_temp_current_weather.text =
                    String.format(getString(R.string.celsius), currentObservation.tempC?.toInt())
            textview_city_current_weather.text = currentObservation.displayLocation?.city
            textview_wind_current_weather.text =
                    String.format(getString(R.string.kph), currentObservation.windGustKph)
            textview_rain_current_weather.text =
                    String.format(getString(R.string.percent), currentObservation.precip1hrIn)
            Picasso.with(context).load(currentObservation.iconUrl).into(imageview_icon_current_weather)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        (context as? MainActivity)?.onUpdateCurrentWeatherCallback = ::onGetLocationFromActivity
    }

    override fun onDetach() {
        super.onDetach()
        (context as? MainActivity)?.onUpdateCurrentWeatherCallback = null
    }

    override fun onResume() {
        super.onResume()
        loadCircularProgressBar("Getting current weather.")
        getCachedCurrentWeather()
    }

    companion object {
        fun newInstance(): CurrentWeatherFragment {
            val args: Bundle = Bundle()
            val fragment = CurrentWeatherFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
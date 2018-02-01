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

/**
 * Created by jeffreychang on 1/29/18.
 */

class CurrentWeatherFragment: InternetFragment() {

    override var layoutResourceID: Int = R.layout.fragment_current_weather

    private val currentWeatherViewModel = DetroitLabsApplication.injectCurrentWeatherViewModel()

    var currentWeatherCallback: ((currentObservation: CurrentObservation) -> Unit)? = null

    private fun onGetLocationFromActivity(location: Location) {
        currentWeatherViewModel
                .getCurrentWeather("${location.latitude},${location.longitude}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ currentConditions ->
                    currentWeatherCallback?.invoke(currentConditions)
                    updateUI(currentConditions)
                })
    }

    private fun updateUI(currentObservation: CurrentObservation?) {
        if (currentObservation != null) {
            current_weather_temp.text =
                    String.format(getString(R.string.celsius), currentObservation.tempC?.toInt())
            current_weather_city.text = currentObservation.displayLocation?.city
            current_weather_wind_textview.text =
                    String.format(getString(R.string.kph), currentObservation.windGustKph)
            current_weather_rain_textview.text =
                    String.format(getString(R.string.percent), currentObservation.precip1hrIn)
            Picasso.with(context).load(currentObservation.iconUrl).into(current_weather_icon)
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
        currentWeatherViewModel.getCachedWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ currentConditions ->
                    currentWeatherCallback?.invoke(currentConditions)
                    updateUI(currentConditions)
                })
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
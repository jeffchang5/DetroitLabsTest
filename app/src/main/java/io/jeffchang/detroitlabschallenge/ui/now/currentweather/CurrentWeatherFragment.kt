package io.jeffchang.detroitlabschallenge.ui.now.currentweather

import android.content.Context
import android.location.Location
import android.os.Bundle
import android.view.View
import io.jeffchang.detroitlabschallenge.MainActivity
import io.jeffchang.detroitlabschallenge.R
import io.jeffchang.detroitlabschallenge.data.remote.NetworkSingleton
import io.jeffchang.detroitlabschallenge.data.remote.WeatherUndergroundService
import io.jeffchang.detroitlabschallenge.ui.common.InternetFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by jeffreychang on 1/29/18.
 */

class CurrentWeatherFragment: InternetFragment() {

    override var layoutResourceID: Int = R.layout.fragment_current_weather


    private lateinit var weatherUndergroundService: WeatherUndergroundService

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val networkSingleton =
                NetworkSingleton.getInstance(WeatherUndergroundService.WEATHER_URL)
        weatherUndergroundService =
                networkSingleton.getRetrofitService(WeatherUndergroundService::class.java)
    }

    private fun onGetLocationFromActivity(location: Location) {
        weatherUndergroundService.getCurrentConditions(
                getString(R.string.weather_underground_secret_key),
                "${location.latitude},${location.longitude}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ currentConditions ->
                    Timber.d("Callback from location")
//                    Toast.makeText(context, currentConditions.currentObservation.heatIndexC, Toast.LENGTH_LONG).show()
                })

    }
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        (context as? MainActivity)?.onGetLocationListener = ::onGetLocationFromActivity
    }

    override fun onDetach() {
        super.onDetach()
        (context as? MainActivity)?.onGetLocationListener = null
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
package io.jeffchang.detroitlabschallenge

import android.app.Application
import android.arch.persistence.room.Room
import com.facebook.stetho.Stetho
import io.jeffchang.detroitlabschallenge.data.remote.NetworkSingleton
import io.jeffchang.detroitlabschallenge.data.remote.WeatherUndergroundService
import io.jeffchang.detroitlabschallenge.data.room.WeatherDatabase
import io.jeffchang.detroitlabschallenge.ui.now.currentweather.CurrentWeatherRepository
import io.jeffchang.detroitlabschallenge.ui.now.currentweather.CurrentWeatherViewModel
import timber.log.Timber

/**
 * Created by jeffreychang on 1/29/18.
 */

class DetroitLabsApplication: Application() {


    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this);
        }
        val networkSingleton =
                NetworkSingleton.getInstance(WeatherUndergroundService.WEATHER_URL)

        weatherUndergroundService =
                networkSingleton.getRetrofitService(WeatherUndergroundService::class.java)

        weatherDatabase = Room.databaseBuilder(
                applicationContext,
                WeatherDatabase::class.java,
                "weather-database")
                .build()

        currentWeatherRepository = CurrentWeatherRepository(weatherUndergroundService,
                weatherDatabase.currentObservationDao()
        )

        currentWeatherViewModel = CurrentWeatherViewModel(currentWeatherRepository)
    }

    companion object {
        private lateinit var currentWeatherRepository: CurrentWeatherRepository

        private lateinit var currentWeatherViewModel: CurrentWeatherViewModel

        private lateinit var weatherUndergroundService: WeatherUndergroundService

        private lateinit var weatherDatabase: WeatherDatabase

        fun injectCurrentWeatherViewModel() = currentWeatherViewModel
    }

}
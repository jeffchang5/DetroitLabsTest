package io.jeffchang.detroitlabschallenge

import android.app.Application
import android.arch.persistence.room.Room
import com.facebook.stetho.Stetho
import io.jeffchang.detroitlabschallenge.data.remote.NetworkSingleton
import io.jeffchang.detroitlabschallenge.data.remote.WeatherUndergroundService
import io.jeffchang.detroitlabschallenge.data.room.WeatherDatabase
import io.jeffchang.detroitlabschallenge.ui.now.currentweather.CurrentWeatherRepository
import io.jeffchang.detroitlabschallenge.ui.now.currentweather.CurrentWeatherViewModel
import io.jeffchang.detroitlabschallenge.ui.now.forecastdayweather.ForcastDayViewModel
import io.jeffchang.detroitlabschallenge.ui.now.forecastdayweather.ForecastDayRepository
import io.jeffchang.detroitlabschallenge.ui.now.hourlyforecast.HourlyForecastRepository
import io.jeffchang.detroitlabschallenge.ui.now.hourlyforecast.HourlyForecastViewModel
import timber.log.Timber

/**
 * Created by jeffreychang on 1/29/18.
 */

class DetroitLabsApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
        }
        val apiKey = getString(R.string.weather_underground_secret_key)
        val networkSingleton =
                NetworkSingleton.getInstance("${WeatherUndergroundService.WEATHER_URL}$apiKey/")

        weatherUndergroundService =
                networkSingleton.getRetrofitService(WeatherUndergroundService::class.java)

        weatherDatabase = Room.databaseBuilder(
                applicationContext,
                WeatherDatabase::class.java,
                "weather-database")
                .build()

        currentWeatherRepository = CurrentWeatherRepository(
                weatherUndergroundService,
                weatherDatabase.currentObservationDao()
        )
        currentWeatherViewModel = CurrentWeatherViewModel(currentWeatherRepository)

        currentWeatherRepository = CurrentWeatherRepository(weatherUndergroundService,
                weatherDatabase.currentObservationDao()
        )
        forecastDayRepository = ForecastDayRepository(weatherUndergroundService,
                weatherDatabase.forecastDayDao())

        forecastDayViewModel = ForcastDayViewModel(forecastDayRepository)

        hourlyForecastRepository = HourlyForecastRepository(weatherUndergroundService)

        hourlyForecastViewModel= HourlyForecastViewModel(hourlyForecastRepository)
    }

    companion object {

        private lateinit var forecastDayRepository: ForecastDayRepository

        private lateinit var forecastDayViewModel: ForcastDayViewModel

        private lateinit var currentWeatherRepository: CurrentWeatherRepository

        private lateinit var currentWeatherViewModel: CurrentWeatherViewModel

        private lateinit var hourlyForecastRepository: HourlyForecastRepository

        private lateinit var hourlyForecastViewModel: HourlyForecastViewModel

        private lateinit var weatherUndergroundService: WeatherUndergroundService

        private lateinit var weatherDatabase: WeatherDatabase

        fun injectCurrentWeatherViewModel() = currentWeatherViewModel

        fun injectForcastDayViewModel() = forecastDayViewModel

        fun injectHourlyForecastViewModel() = hourlyForecastViewModel
    }

}
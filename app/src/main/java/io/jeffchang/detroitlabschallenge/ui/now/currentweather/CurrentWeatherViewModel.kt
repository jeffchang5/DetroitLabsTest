package io.jeffchang.detroitlabschallenge.ui.now.currentweather

import io.jeffchang.detroitlabschallenge.data.model.CurrentObservation
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * Created by jeffreychang on 1/30/18.
 */
class CurrentWeatherViewModel(private val currentWeatherRepository: CurrentWeatherRepository) {

    fun getCurrentWeather(latLong: String): Observable<CurrentObservation> {
        return currentWeatherRepository.getCurrentObservation(latLong)
                .debounce(400, TimeUnit.MILLISECONDS)
    }

    fun getCachedWeather(): Observable<CurrentObservation> {
        return currentWeatherRepository.getCurrentWeatherFromDb()
    }

}
package io.jeffchang.detroitlabschallenge.ui.now.currentweather

import io.jeffchang.detroitlabschallenge.data.model.CurrentObservation
import io.reactivex.Observable

/**
 * Created by jeffreychang on 1/30/18.
 */
class CurrentWeatherViewModel(private val currentWeatherRepository: CurrentWeatherRepository) {

    fun getCurrentWeather(apiKey: String, latLong: String): Observable<CurrentObservation> {
        return currentWeatherRepository.getCurrentObservation(apiKey, latLong)
    }

    fun getCachedWeather(): Observable<CurrentObservation> {
        return currentWeatherRepository.getCurrentWeatherFromDb()
    }

}
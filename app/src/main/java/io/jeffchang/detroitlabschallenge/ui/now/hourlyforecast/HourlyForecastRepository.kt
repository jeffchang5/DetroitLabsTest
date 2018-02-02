package io.jeffchang.detroitlabschallenge.ui.now.hourlyforecast

import io.jeffchang.detroitlabschallenge.data.model.HourlyForecast
import io.jeffchang.detroitlabschallenge.data.remote.WeatherUndergroundService
import io.reactivex.Observable
import timber.log.Timber

/**
 * Created by jeffreychang on 2/1/18.
 */
class HourlyForecastRepository(private val weatherUndergroundService: WeatherUndergroundService) {

    fun getHourlyForecast(latLong: String, date: String): Observable<List<HourlyForecast?>> {
        return getHourlyForecastFromApi(latLong)
                .flatMapIterable { list -> list }
                .filter {
                    val hourlyDate = "${it.fCTTIME?.mon},${it.fCTTIME?.mday},${it.fCTTIME?.year}"
                    date == hourlyDate
                }
                .toList()
                .toObservable()
    }

    fun getHourlyForecastFromApi(latLong: String): Observable<List<HourlyForecast?>> {
        return weatherUndergroundService
                .getHourlyTenDayWeather(latLong)
                .flatMap { Observable.just(it.hourlyForecast) }
    }
}
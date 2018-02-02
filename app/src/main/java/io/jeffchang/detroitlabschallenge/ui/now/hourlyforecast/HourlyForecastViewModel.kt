package io.jeffchang.detroitlabschallenge.ui.now.hourlyforecast

import io.jeffchang.detroitlabschallenge.data.model.HourlyForecast
import io.reactivex.Observable

/**
 * Created by jeffreychang on 2/1/18.
 */
class HourlyForecastViewModel(private val hourlyForecastRepository: HourlyForecastRepository) {
    fun getHourlyForecast(latLong: String, date: String): Observable<List<HourlyForecast?>> {
        return hourlyForecastRepository.getHourlyForecast(latLong, date)
    }
}
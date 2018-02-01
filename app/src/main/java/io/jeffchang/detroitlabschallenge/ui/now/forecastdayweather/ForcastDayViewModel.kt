package io.jeffchang.detroitlabschallenge.ui.now.forecastdayweather

import io.jeffchang.detroitlabschallenge.data.model.ForecastDaoObject
import io.reactivex.Observable

/**
 * Created by jeffreychang on 1/31/18.
 */
class ForcastDayViewModel(private val currentWeatherRepository: ForecastDayRepository) {
    fun getForecastDays(latLong: String): Observable<ForecastDaoObject> {
        return currentWeatherRepository.getForecastDays(latLong)
    }
}
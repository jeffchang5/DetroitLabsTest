package io.jeffchang.detroitlabschallenge.ui.now.forecastdayweather

import io.jeffchang.detroitlabschallenge.data.model.ForecastDaoObject
import io.jeffchang.detroitlabschallenge.data.model.LatLng
import io.jeffchang.detroitlabschallenge.data.remote.WeatherUndergroundService
import io.jeffchang.detroitlabschallenge.data.room.dao.ForecastDayDao
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by jeffreychang on 1/31/18.
 */

class ForecastDayRepository(private val weatherUndergroundService: WeatherUndergroundService,
                            private val forecastDayDao: ForecastDayDao) {
    fun getForecastDays(latLong: String): Observable<ForecastDaoObject> {
        return(getForecastDayFromApi(latLong))
//        return Observable.concatArray(
//                getForecastDayFromDb(),
//                getForecastDayFromApi(latLong)
//        )
    }

    fun getForecastDayFromDb(): Observable<List<ForecastDaoObject>> {
        return forecastDayDao
                .getForecastDays()
                .toObservable()
    }

    private fun getForecastDayFromApi(latLong: String): Observable<ForecastDaoObject> {
        return weatherUndergroundService
                .getTenDayWeather(latLong)
                .map { tenDayWeather -> tenDayWeather.forecast?.simpleforecast?.forecastday }
                .map { ForecastDaoObject(LatLng(null, latLong, null ), it) }
//                .toList()
//                .toObservable()
//                .doOnNext({ Timber.e(it[0].latLng.toString())})
//                .doOnNext({ storeForecastDaysInDb(it)})
    }
    private fun storeForecastDaysInDb(forecastDayList: List<ForecastDaoObject>) {
        Observable.fromIterable(forecastDayList)
                .subscribe {
                    Observable.fromCallable {
                        forecastDayDao.insertForecastsForLocation(it)
                    }
                            .subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.io())
                }

    }
}

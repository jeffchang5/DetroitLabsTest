package io.jeffchang.detroitlabschallenge.ui.now.currentweather

import io.jeffchang.detroitlabschallenge.data.model.CurrentObservation
import io.jeffchang.detroitlabschallenge.data.remote.WeatherUndergroundService
import io.jeffchang.detroitlabschallenge.data.room.dao.CurrentObservationsDao
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by jeffreychang on 1/30/18.
 */
class CurrentWeatherRepository(private val weatherUndergroundService: WeatherUndergroundService,
                               private val currentObservationsDao: CurrentObservationsDao) {

    fun getCurrentObservation(latLong: String): Observable<CurrentObservation> {
        return Observable.concatArray(
                getCurrentWeatherFromDb(),
                getCurrentWeatherFromApi(latLong)
            )
    }

    fun getCurrentWeatherFromDb(): Observable<CurrentObservation> {
        return currentObservationsDao
                .getCurrentObservation()
                .toObservable()
    }

    private fun getCurrentWeatherFromApi(latLong: String): Observable<CurrentObservation> {
        return weatherUndergroundService
                .getCurrentConditions(latLong)
                .flatMap { currentConditions ->
                    currentConditions.currentObservation.id = 1
                    Observable.just(currentConditions.currentObservation) }
                .doOnNext({ storeCurrentWeatherInDb(it)})
    }

    private fun storeCurrentWeatherInDb(currentObservation: CurrentObservation) {
        Observable.fromCallable {
            currentObservationsDao.insert(currentObservation) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe{
                    Timber.i("Current weather has been inserted.")
                }
    }
}
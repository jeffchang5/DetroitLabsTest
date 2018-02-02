package io.jeffchang.detroitlabschallenge.data.remote

import io.jeffchang.detroitlabschallenge.data.model.CurrentConditions
import io.jeffchang.detroitlabschallenge.data.model.HourlyTenDayWeather
import io.jeffchang.detroitlabschallenge.data.model.TenDayWeather
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by jeffreychang on 1/30/18.
 */
interface WeatherUndergroundService {

    @GET("conditions/q/{latLong}.json")
    fun getCurrentConditions(@Path("latLong") latLong: String): Observable<CurrentConditions>

    @GET("forecast10day/q/{latLong}.json")
    fun getTenDayWeather(@Path("latLong") latLong: String): Observable<TenDayWeather>

    @GET("hourly10day/q/{latLong}.json")
    fun getHourlyTenDayWeather(@Path("latLong") latLong: String)
            : Observable<HourlyTenDayWeather>

    companion object {
        const val WEATHER_URL ="http://api.wunderground.com/api/"
    }

}

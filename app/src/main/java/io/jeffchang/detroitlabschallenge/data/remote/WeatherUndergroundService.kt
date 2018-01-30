package io.jeffchang.detroitlabschallenge.data.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import com.squareup.moshi.Json
import io.jeffchang.detroitlabschallenge.data.model.CurrentConditions


/**
 * Created by jeffreychang on 1/30/18.
 */
interface WeatherUndergroundService {

    @GET("{apiKey}/conditions/q/{latLong}.json")
    fun getCurrentConditions(@Path("apiKey") apiKey: String,
                             @Path("latLong") latLong: String): Observable<CurrentConditions>


    companion object {
        const val WEATHER_URL ="http://api.wunderground.com/api/"
    }
}

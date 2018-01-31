package io.jeffchang.detroitlabschallenge.data.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.location.Location
import io.jeffchang.detroitlabschallenge.data.model.CurrentConditions
import io.jeffchang.detroitlabschallenge.data.model.CurrentObservation
import io.jeffchang.detroitlabschallenge.data.model.HourlyWeather
import io.jeffchang.detroitlabschallenge.data.model.TenDayWeather
import io.jeffchang.detroitlabschallenge.data.room.dao.CurrentObservationsDao
import io.jeffchang.detroitlabschallenge.data.room.dao.HourlyWeatherDao
import io.jeffchang.detroitlabschallenge.data.room.dao.TenDayWeatherDao

/**
 * Created by jeffreychang on 1/30/18.
 */

@Database(entities = [
    (CurrentObservation::class)
//    (TenDayWeather::class),
//    (HourlyWeather::class)
], version = 1, exportSchema = false)

abstract class WeatherDatabase: RoomDatabase() {

    abstract fun currentObservationDao(): CurrentObservationsDao

//    abstract fun tenDayWeatherDao(): TenDayWeatherDao
//
//    abstract fun hourlyWeatherDao(): HourlyWeatherDao
//
//    abstract fun locationDao(): Location

}
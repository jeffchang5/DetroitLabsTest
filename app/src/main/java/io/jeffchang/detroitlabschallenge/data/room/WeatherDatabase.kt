package io.jeffchang.detroitlabschallenge.data.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import io.jeffchang.detroitlabschallenge.data.model.CurrentObservation
import io.jeffchang.detroitlabschallenge.data.model.ForecastDay
import io.jeffchang.detroitlabschallenge.data.model.LatLng
import io.jeffchang.detroitlabschallenge.data.room.dao.CurrentObservationsDao
import io.jeffchang.detroitlabschallenge.data.room.dao.ForecastDayDao

/**
 * Created by jeffreychang on 1/30/18.
 */

@Database(entities = [
    (CurrentObservation::class),
    (ForecastDay::class),
    (LatLng::class)
], version = 1, exportSchema = false)

abstract class WeatherDatabase: RoomDatabase() {

    abstract fun currentObservationDao(): CurrentObservationsDao

    abstract fun forecastDayDao(): ForecastDayDao

}
package io.jeffchang.detroitlabschallenge.data.room.dao

import android.arch.persistence.room.*
import io.jeffchang.detroitlabschallenge.data.model.ForecastDaoObject
import io.jeffchang.detroitlabschallenge.data.model.ForecastDay
import io.jeffchang.detroitlabschallenge.data.model.LatLng
import io.reactivex.Maybe

/**
 * Created by jeffreychang on 1/30/18.
 */

@Dao
abstract class ForecastDayDao {

    @Transaction
    @Query("SELECT * FROM Location")
    abstract fun getForecastDays(): Maybe<List<ForecastDaoObject>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(forecastDay: List<ForecastDay>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertLocation(latLong: LatLng)

    fun insertForecastsForLocation(daoObject: ForecastDaoObject) {
        for (day in daoObject.forecastDay!!) {
            day.locationId = daoObject.latLng?.id
        }
        insertLocation(daoObject.latLng!!)
        insertAll(daoObject.forecastDay!!)

    }
}
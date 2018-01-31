package io.jeffchang.detroitlabschallenge.data.room.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.jeffchang.detroitlabschallenge.data.model.CurrentObservation
import io.reactivex.Maybe

/**
 * Created by jeffreychang on 1/30/18.
 */

@Dao
interface CurrentObservationsDao {

    @Query("SELECT * FROM currentObservations")
    fun getCurrentObservation(): Maybe<CurrentObservation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currentObservation: CurrentObservation)
}
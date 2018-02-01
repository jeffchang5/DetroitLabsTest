package io.jeffchang.detroitlabschallenge.data.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.Relation

/**
 * Created by jeffreychang on 1/31/18.
 */

data class ForecastDaoObject(
        @Embedded
        var latLng: LatLng? = null,
        @Relation(parentColumn = "id", entityColumn = "locationId", entity = ForecastDay::class)
        var forecastDay: List<ForecastDay>? = listOf()
)

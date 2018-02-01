package io.jeffchang.detroitlabschallenge.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by jeffreychang on 1/31/18.
 */
@Entity(tableName = "Location")
data class LatLng(
        @PrimaryKey(autoGenerate = true)
        var id: Int?,
        val latLong: String,
        var forecastId: Int?)
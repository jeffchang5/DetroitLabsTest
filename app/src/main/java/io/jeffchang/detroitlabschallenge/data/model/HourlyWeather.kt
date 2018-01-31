package io.jeffchang.detroitlabschallenge.data.model

import android.arch.persistence.room.Entity
import com.squareup.moshi.Json

/**
 * Created by jeffreychang on 1/30/18.
 */
@Entity(tableName = "hourlyWeather")

data class HourlyWeather(
        @Json(name = "response") val response: Response)
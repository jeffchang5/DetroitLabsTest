package io.jeffchang.detroitlabschallenge.data.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json

data class CurrentConditions(
        @Json(name = "response") val response: Response,
        @Json(name = "current_observation") val currentObservation: CurrentObservation
)

data class Response(
		@Json(name = "version") val version: String,
		@Json(name = "termsofService") val termsofService: String,
        @Embedded(prefix = "features_")
		@Json(name = "features") val features: Features?
)

data class Features(
		@Json(name = "conditions") val conditions: Int
)

@Entity(tableName = "currentObservations")
data class CurrentObservation(
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        @Embedded(prefix = "image_")
		@Json(name = "image") val image: Image?,
        @Embedded(prefix = "display_location_")
		@Json(name = "display_location") val displayLocation: DisplayLocation?,
        @Embedded(prefix = "observation_location_")
		@Json(name = "observation_location") val observationLocation: ObservationLocation?,
        @Embedded(prefix = "estimated_")
		@Json(name = "estimated") val estimated: Estimated?,
		@Json(name = "station_id") val stationId: String?,
		@Json(name = "observation_time") val observationTime: String?,
		@Json(name = "observation_time_rfc822") val observationTimeRfc822: String?,
		@Json(name = "observation_epoch") val observationEpoch: String?,
		@Json(name = "local_time_rfc822") val localTimeRfc822: String?,
		@Json(name = "local_epoch") val localEpoch: String?,
		@Json(name = "local_tz_short") val localTzShort: String?,
		@Json(name = "local_tz_long") val localTzLong: String?,
		@Json(name = "local_tz_offset") val localTzOffset: String?,
		@Json(name = "weather") val weather: String?,
		@Json(name = "temperature_string") val temperatureString: String?,
		@Json(name = "temp_f") val tempF: Double?,
		@Json(name = "temp_c") val tempC: Double?,
		@Json(name = "relative_humidity") val relativeHumidity: String?,
		@Json(name = "wind_string") val windString: String?,
		@Json(name = "wind_dir") val windDir: String?,
		@Json(name = "wind_degrees") val windDegrees: Int?,
		@Json(name = "wind_mph") val windMph: Double?,
		@Json(name = "wind_gust_mph") val windGustMph: String?,
		@Json(name = "wind_kph") val windKph: Double?,
		@Json(name = "wind_gust_kph") val windGustKph: String?,
		@Json(name = "pressure_mb") val pressureMb: String?,
		@Json(name = "pressure_in") val pressureIn: String?,
		@Json(name = "pressure_trend") val pressureTrend: String?,
		@Json(name = "dewpoint_string") val dewpointString: String?,
		@Json(name = "dewpoint_f") val dewpointF: Int?,
		@Json(name = "dewpoint_c") val dewpointC: Int?,
		@Json(name = "heat_index_string") val heatIndexString: String?,
		@Json(name = "heat_index_f") val heatIndexF: String?,
		@Json(name = "heat_index_c") val heatIndexC: String?,
		@Json(name = "windchill_string") val windchillString: String?,
		@Json(name = "windchill_f") val windchillF: String?,
		@Json(name = "windchill_c") val windchillC: String?,
		@Json(name = "feelslike_string") val feelslikeString: String?,
		@Json(name = "feelslike_f") val feelslikeF: String?,
		@Json(name = "feelslike_c") val feelslikeC: String?,
		@Json(name = "visibility_mi") val visibilityMi: String?,
		@Json(name = "visibility_km") val visibilityKm: String?,
		@Json(name = "solarradiation") val solarradiation: String,
		@Json(name = "UV") val uV: String,
		@Json(name = "precip_1hr_string") val precip1hrString: String,
		@Json(name = "precip_1hr_in") val precip1hrIn: String,
		@Json(name = "precip_1hr_metric") val precip1hrMetric: String,
		@Json(name = "precip_today_string") val precipTodayString: String,
		@Json(name = "precip_today_in") val precipTodayIn: String,
		@Json(name = "precip_today_metric") val precipTodayMetric: String,
		@Json(name = "icon") val icon: String,
		@Json(name = "icon_url") val iconUrl: String,
		@Json(name = "forecast_url") val forecastUrl: String,
		@Json(name = "history_url") val historyUrl: String,
		@Json(name = "ob_url") val obUrl: String
)

data class Image(
		@Json(name = "url") val url: String,
		@Json(name = "title") val title: String,
		@Json(name = "link") val link: String
)

data class DisplayLocation(
		@Json(name = "full") val full: String,
		@Json(name = "city") val city: String,
		@Json(name = "state") val state: String,
		@Json(name = "state_name") val stateName: String,
		@Json(name = "country") val country: String,
		@Json(name = "country_iso3166") val countryIso3166: String,
		@Json(name = "zip") val zip: String,
		@Json(name = "latitude") val latitude: String,
		@Json(name = "longitude") val longitude: String,
		@Json(name = "elevation") val elevation: String
)

class Estimated

data class ObservationLocation(
		@Json(name = "full") val full: String,
		@Json(name = "city") val city: String,
		@Json(name = "state") val state: String,
		@Json(name = "country") val country: String,
		@Json(name = "country_iso3166") val countryIso3166: String,
		@Json(name = "latitude") val latitude: String,
		@Json(name = "longitude") val longitude: String,
		@Json(name = "elevation") val elevation: String
)

package io.jeffchang.detroitlabschallenge.data.model

import android.arch.persistence.room.Entity
import com.squareup.moshi.Json

/**
 * Created by jeffreychang on 1/30/18.
 */


data class HourlyTenDayWeather(
		@Json(name = "response") var response: Response?,
		@Json(name = "hourly_forecast") var hourlyForecast: List<HourlyForecast?>?
)


data class HourlyFeatures(
		@Json(name = "hourly10day") var hourly10day: Int?
)

@Entity(tableName = "hourlyWeather")
data class HourlyForecast(
		@Json(name = "FCTTIME") var fCTTIME: FCTTIME?,
		@Json(name = "temp") var temp: Temp?,
		@Json(name = "dewpoint") var dewpoint: Dewpoint?,
		@Json(name = "condition") var condition: String?,
		@Json(name = "icon") var icon: String?,
		@Json(name = "icon_url") var iconUrl: String?,
		@Json(name = "fctcode") var fctcode: String?,
		@Json(name = "sky") var sky: String?,
		@Json(name = "wspd") var wspd: Wspd?,
		@Json(name = "wdir") var wdir: Wdir?,
		@Json(name = "wx") var wx: String?,
		@Json(name = "uvi") var uvi: String?,
		@Json(name = "humidity") var humidity: String?,
		@Json(name = "windchill") var windchill: Windchill?,
		@Json(name = "heatindex") var heatindex: Heatindex?,
		@Json(name = "feelslike") var feelslike: Feelslike?,
		@Json(name = "qpf") var qpf: Qpf?,
		@Json(name = "snow") var snow: Snow?,
		@Json(name = "pop") var pop: String?,
		@Json(name = "mslp") var mslp: Mslp?
)

data class Feelslike(
		@Json(name = "english") var english: String?,
		@Json(name = "metric") var metric: String?
)

data class Mslp(
		@Json(name = "english") var english: String?,
		@Json(name = "metric") var metric: String?
)

data class Wdir(
		@Json(name = "dir") var dir: String?,
		@Json(name = "degrees") var degrees: String?
)

data class Wspd(
		@Json(name = "english") var english: String?,
		@Json(name = "metric") var metric: String?
)

data class Heatindex(
		@Json(name = "english") var english: String?,
		@Json(name = "metric") var metric: String?
)

data class Snow(
		@Json(name = "english") var english: String?,
		@Json(name = "metric") var metric: String?
)

data class Temp(
		@Json(name = "english") var english: String?,
		@Json(name = "metric") var metric: String?
)

data class Dewpoint(
		@Json(name = "english") var english: String?,
		@Json(name = "metric") var metric: String?
)

data class Qpf(
		@Json(name = "english") var english: String?,
		@Json(name = "metric") var metric: String?
)

data class FCTTIME(
		@Json(name = "hour") var hour: String?,
		@Json(name = "hour_padded") var hourPadded: String?,
		@Json(name = "min") var min: String?,
		@Json(name = "min_unpadded") var minUnpadded: String?,
		@Json(name = "sec") var sec: String?,
		@Json(name = "year") var year: String?,
		@Json(name = "mon") var mon: String?,
		@Json(name = "mon_padded") var monPadded: String?,
		@Json(name = "mon_abbrev") var monAbbrev: String?,
		@Json(name = "mday") var mday: String?,
		@Json(name = "mday_padded") var mdayPadded: String?,
		@Json(name = "yday") var yday: String?,
		@Json(name = "isdst") var isdst: String?,
		@Json(name = "epoch") var epoch: String?,
		@Json(name = "pretty") var pretty: String?,
		@Json(name = "civil") var civil: String?,
		@Json(name = "month_name") var monthName: String?,
		@Json(name = "month_name_abbrev") var monthNameAbbrev: String?,
		@Json(name = "weekday_name") var weekdayName: String?,
		@Json(name = "weekday_name_night") var weekdayNameNight: String?,
		@Json(name = "weekday_name_abbrev") var weekdayNameAbbrev: String?,
		@Json(name = "weekday_name_unlang") var weekdayNameUnlang: String?,
		@Json(name = "weekday_name_night_unlang") var weekdayNameNightUnlang: String?,
		@Json(name = "ampm") var ampm: String?,
		@Json(name = "tz") var tz: String?,
		@Json(name = "age") var age: String?,
		@Json(name = "UTCDATE") var uTCDATE: String?
)

data class Windchill(
		@Json(name = "english") var english: String?,
		@Json(name = "metric") var metric: String?
)

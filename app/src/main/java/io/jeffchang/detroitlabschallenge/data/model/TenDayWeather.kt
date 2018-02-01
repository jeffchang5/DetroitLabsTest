package io.jeffchang.detroitlabschallenge.data.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json

/**
 * Created by jeffreychang on 1/30/18.
 */

data class TenDayWeather(
		@Json(name = "response") var response: Response?,
		@Json(name = "forecast") var forecast: Forecast?
)

data class Forecast(
		@Json(name = "txt_forecast") var txtForecast: TxtForecast?,
		@Json(name = "simpleforecast") var simpleforecast: Simpleforecast?
)

data class TxtForecast(
		@Json(name = "date") var date: String?,
		@Json(name = "forecastday") var forecastday: List<ForecastdayMeta?>?
)

data class ForecastdayMeta(
		@Json(name = "period") var period: Int?,
		@Json(name = "icon") var icon: String?,
		@Json(name = "icon_url") var iconUrl: String?,
		@Json(name = "title") var title: String?,
		@Json(name = "fcttext") var fcttext: String?,
		@Json(name = "fcttext_metric") var fcttextMetric: String?,
		@Json(name = "pop") var pop: String?
)

data class Simpleforecast(
		@Json(name = "forecastday") var forecastday: List<ForecastDay>
)

@Entity
data class ForecastDay(
		@PrimaryKey(autoGenerate = true)
		var id: Int?,
		var locationId: Int?,
		@Embedded(prefix = "date_")
		@Json(name = "date") var date: Date?,
		@Json(name = "period") var period: Int?,
		@Embedded(prefix = "high_")
		@Json(name = "high") var high: High?,
		@Embedded(prefix = "low_")
		@Json(name = "low") var low: Low?,
		@Json(name = "conditions") var conditions: String?,
		@Json(name = "icon") var icon: String?,
		@Json(name = "icon_url") var iconUrl: String?,
		@Json(name = "skyicon") var skyicon: String?,
		@Json(name = "pop") var pop: Int?,
		@Embedded(prefix = "qpf_all_day_")
		@Json(name = "qpf_allday") var qpfAllday: QpfAllday?,
		@Embedded(prefix = "qpf_day_")
		@Json(name = "qpf_day") var qpfDay: QpfDay?,
		@Embedded(prefix = "qpf_night_")
		@Json(name = "qpf_night") var qpfNight: QpfNight?,
		@Embedded(prefix = "snow_all_day_")
		@Json(name = "snow_allday") var snowAllday: SnowAllday?,
		@Embedded(prefix = "snow_day_")
		@Json(name = "snow_day") var snowDay: SnowDay?,
		@Embedded(prefix = "snow_day_night_")
		@Json(name = "snow_night") var snowNight: SnowNight?,
		@Embedded(prefix = "max_wind_")
		@Json(name = "maxwind") var maxwind: Maxwind?,
		@Embedded(prefix = "ave_wind_")
		@Json(name = "avewind") var avewind: Avewind?,
		@Json(name = "avehumidity") var avehumidity: Int?,
		@Json(name = "maxhumidity") var maxhumidity: Int?,
		@Json(name = "minhumidity") var minhumidity: Int?
)

data class High(
		@Json(name = "fahrenheit") var fahrenheit: String?,
		@Json(name = "celsius") var celsius: String?
)

data class Low(
		@Json(name = "fahrenheit") var fahrenheit: String?,
		@Json(name = "celsius") var celsius: String?
)

data class SnowDay(
		@Json(name = "in") var inX: Float?,
		@Json(name = "cm") var cm: Float?
)

data class Maxwind(
		@Json(name = "mph") var mph: Int?,
		@Json(name = "kph") var kph: Int?,
		@Json(name = "dir") var dir: String?,
		@Json(name = "degrees") var degrees: Int?
)

data class Date(
		@Json(name = "epoch") var epoch: String?,
		@Json(name = "pretty") var pretty: String?,
		@Json(name = "day") var day: Int?,
		@Json(name = "month") var month: Int?,
		@Json(name = "year") var year: Int?,
		@Json(name = "yday") var yday: Int?,
		@Json(name = "hour") var hour: Int?,
		@Json(name = "min") var min: String?,
		@Json(name = "sec") var sec: Int?,
		@Json(name = "isdst") var isdst: String?,
		@Json(name = "monthname") var monthname: String?,
		@Json(name = "monthname_short") var monthnameShort: String?,
		@Json(name = "weekday_short") var weekdayShort: String?,
		@Json(name = "weekday") var weekday: String?,
		@Json(name = "ampm") var ampm: String?,
		@Json(name = "tz_short") var tzShort: String?,
		@Json(name = "tz_long") var tzLong: String?
)

data class QpfNight(
		@Json(name = "in") var inX: Float?,
		@Json(name = "mm") var mm: Int?
)

data class Avewind(
		@Json(name = "mph") var mph: Int?,
		@Json(name = "kph") var kph: Int?,
		@Json(name = "dir") var dir: String?,
		@Json(name = "degrees") var degrees: Int?
)

data class QpfAllday(
		@Json(name = "in") var inX: Float?,
		@Json(name = "mm") var mm: Int?
)

data class SnowNight(
		@Json(name = "in") var inX: Float?,
		@Json(name = "cm") var cm: Float?
)

data class QpfDay(
		@Json(name = "in") var inX: Float?,
		@Json(name = "mm") var mm: Int?
)

data class SnowAllday(
		@Json(name = "in") var inX: Float?,
		@Json(name = "cm") var cm: Float?
)
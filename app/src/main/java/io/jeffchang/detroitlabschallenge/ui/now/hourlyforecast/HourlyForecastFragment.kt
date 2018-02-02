package io.jeffchang.detroitlabschallenge.ui.now.hourlyforecast

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import io.jeffchang.detroitlabschallenge.DetroitLabsApplication
import io.jeffchang.detroitlabschallenge.MainActivity
import io.jeffchang.detroitlabschallenge.R
import io.jeffchang.detroitlabschallenge.data.model.HourlyForecast
import io.jeffchang.detroitlabschallenge.ui.common.InternetFragment
import io.jeffchang.detroitlabschallenge.ui.now.hourlyforecast.views.HourlyForecastRecyclerViewAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_hourly_forecast.*
import java.net.UnknownHostException

/**
 * Created by jeffreychang on 2/1/18.
 */

class HourlyForecastFragment : InternetFragment() {

    override var layoutResourceID: Int = R.layout.fragment_hourly_forecast

    private val hourlyForecastViewModel = DetroitLabsApplication.injectHourlyForecastViewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val latLng = arguments?.getString(ARG_LAT_LNG)
        val date = arguments?.getString(ARG_WEATHER_DATE)
        getHourlyForecast(latLng!!, date!!)

    }

    private fun getHourlyForecast(latLng: String, date: String) {
        hourlyForecastViewModel.getHourlyForecast(latLng, date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ hourlyForecastList->
                    loadMainContent()
                    updateUI(hourlyForecastList)
                },{ e ->
                    if (e is UnknownHostException)
                        loadNoInternet({ getHourlyForecast(latLng, date) }, null)
                })
    }

    private fun updateUI(hourlyForecastList: List<HourlyForecast?>) {
        toolbar_hourly_forecast.title = String.format(resources.getString(R.string.week_date),
                hourlyForecastList[0]?.fCTTIME?.weekdayNameAbbrev,
                hourlyForecastList[0]?.fCTTIME?.monthName,
                hourlyForecastList[0]?.fCTTIME?.mday)
        toolbar_hourly_forecast.setNavigationOnClickListener { activity?.onBackPressed() }
        recyclerview_hourly_forecast.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false)
        recyclerview_hourly_forecast.adapter = HourlyForecastRecyclerViewAdapter(context!!,
                hourlyForecastList)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).setTheme(android.R.style.Theme_Material_Light_DarkActionBar)
        loadCircularProgressBar("Getting hourly forecast")
    }

    companion object {

        private const val ARG_LAT_LNG = "ARG_LAT_LNG"
        private const val ARG_WEATHER_DATE = "ARG_WEATHER_DATE"

        fun newInstance(latLng: String, weatherDate: String): HourlyForecastFragment {
            val args = Bundle()
            args.putString(ARG_LAT_LNG, latLng)
            args.putString(ARG_WEATHER_DATE, weatherDate)
            val fragment = HourlyForecastFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
package io.jeffchang.detroitlabschallenge.ui.now

import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import io.jeffchang.detroitlabschallenge.R
import io.jeffchang.detroitlabschallenge.ui.common.BaseFragment
import io.jeffchang.detroitlabschallenge.ui.now.currentweather.CurrentWeatherFragment
import io.jeffchang.detroitlabschallenge.ui.now.forecastdayweather.ForecastDayFragment
import kotlinx.android.synthetic.main.fragment_now.*

/**
 * Created by jeffreychang on 1/30/18.
 */

class NowFragment: BaseFragment() {

    override var layoutResourceID: Int = R.layout.fragment_now

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(main_toolbar)
        val montserrat = ResourcesCompat.getFont(context!!, R.font.montserrat)
        main_activity_collapsing_toolbar.setCollapsedTitleTypeface(montserrat)
        main_activity_collapsing_toolbar.setExpandedTitleTypeface(montserrat)
        val currentWeatherFragment = CurrentWeatherFragment.newInstance()
        childFragmentManager.beginTransaction()
                .replace(R.id.current_weather_now,
                        currentWeatherFragment)
                .replace(R.id.forecastday_weather_now,
                        ForecastDayFragment.newInstance())
                .commit()
    }

    override fun onResume() {
        super.onResume()
        setCurrentDate(main_toolbar)
    }

    companion object {
        fun newInstance(): NowFragment {
            return NowFragment()
        }
    }
}



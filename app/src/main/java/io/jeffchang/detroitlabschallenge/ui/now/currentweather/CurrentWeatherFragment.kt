package io.jeffchang.detroitlabschallenge.ui.now.currentweather

import android.os.Bundle
import android.view.View
import io.jeffchang.detroitlabschallenge.R
import io.jeffchang.detroitlabschallenge.ui.common.InternetFragment

/**
 * Created by jeffreychang on 1/29/18.
 */

class CurrentWeatherFragment: InternetFragment() {

    override var layoutResourceID: Int = R.layout.fragment_current_weather

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        fun newInstance(): CurrentWeatherFragment {
            val args: Bundle = Bundle()
            val fragment = CurrentWeatherFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
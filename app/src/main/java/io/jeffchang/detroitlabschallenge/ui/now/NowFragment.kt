package io.jeffchang.detroitlabschallenge.ui.now

import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import io.jeffchang.detroitlabschallenge.R
import io.jeffchang.detroitlabschallenge.ui.common.BaseFragment
import io.jeffchang.detroitlabschallenge.ui.now.currentweather.CurrentWeatherFragment
import kotlinx.android.synthetic.main.fragment_now.*
import java.text.SimpleDateFormat
import java.util.*

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
        childFragmentManager.beginTransaction()
                .replace(R.id.main_activity_current_weather,
                        CurrentWeatherFragment.newInstance(), null)
                .commit()
    }

    override fun onResume() {
        super.onResume()
        setCurrentDate()
    }

    private fun setCurrentDate() {
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("EEEE, MMMM dd", Locale.getDefault())
        main_toolbar.title = sdf.format(calendar.time)
    }
    companion object {
        fun newInstance(): NowFragment {
            val args: Bundle = Bundle()
            val fragment = NowFragment()
            fragment.arguments = args
            return fragment
        }
    }
}


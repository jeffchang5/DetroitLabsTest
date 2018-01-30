package io.jeffchang.detroitlabschallenge.ui.places

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

class PlacesFragment : BaseFragment() {
    override var layoutResourceID: Int = R.layout.fragment_places

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(main_toolbar)
    }

    override fun onResume() {
        super.onResume()
        setCurrentDate()
    }

    private fun setCurrentDate() {
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("EEEE, MMMM dd", Locale.getDefault())
    }
    companion object {
        fun newInstance(): PlacesFragment {
            val args: Bundle = Bundle()
            val fragment = PlacesFragment()
            fragment.arguments = args
            return fragment
        }
    }
}


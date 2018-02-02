package io.jeffchang.detroitlabschallenge.ui.now.hourlyforecast.views

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import io.jeffchang.detroitlabschallenge.data.model.HourlyForecast
import io.jeffchang.detroitlabschallenge.ui.now.hourlyforecast.views.HourlyForecastRecyclerViewAdapter.HourlyForecastRecyclerViewHolder
import timber.log.Timber

/**
 * Created by jeffreychang on 2/1/18.
 */

open class HourlyForecastRecyclerViewAdapter(val context: Context,
                                             private val hourlyForecastList: List<HourlyForecast?>) :
        RecyclerView.Adapter<HourlyForecastRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int)
            : HourlyForecastRecyclerViewHolder {
        val hourlyForecastView = HourlyForecastView(context)
        hourlyForecastView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        return HourlyForecastRecyclerViewHolder(hourlyForecastView)
    }

    override fun getItemCount(): Int {
        return hourlyForecastList.size
    }

    override fun onBindViewHolder(holder: HourlyForecastRecyclerViewHolder?, position: Int) {
        Timber.e("Why won't you show?")
        holder?.hourlyForecastView?.hourlyForecast = hourlyForecastList[position]
    }

    class HourlyForecastRecyclerViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val hourlyForecastView: HourlyForecastView = itemView as HourlyForecastView
    }
}
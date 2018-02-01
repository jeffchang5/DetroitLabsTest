package io.jeffchang.detroitlabschallenge.ui.common

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by jeffreychang on 1/30/18.
 */
abstract class BaseFragment : Fragment() {

    abstract var layoutResourceID: Int

    fun setCurrentDate(toolbar: Toolbar) {
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("EEEE, MMMM dd", Locale.getDefault())
        toolbar.title = sdf.format(calendar.time)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutResourceID, container, false)
    }

}
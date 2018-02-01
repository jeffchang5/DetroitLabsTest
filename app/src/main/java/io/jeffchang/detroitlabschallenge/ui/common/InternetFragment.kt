package io.jeffchang.detroitlabschallenge.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.FrameLayout
import io.jeffchang.detroitlabschallenge.ui.common.views.CircularProgressView
import io.jeffchang.detroitlabschallenge.ui.common.views.NoInternetView

/**
 * Created by jeffreychang on 1/29/18.
 */
abstract class InternetFragment : BaseFragment() {

    private lateinit var parent: FrameLayout

    var childView: View? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        parent = FrameLayout(context)
        parent.layoutParams = FrameLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        childView = inflater.inflate(layoutResourceID, container, false)
        parent.addView(childView)
        return parent
    }

    fun loadNoInternet(callback: (() -> Unit)?) {
        parent.removeAllViews()
        val noInternetView = NoInternetView(context!!)
        parent.addView(noInternetView)
    }

    fun loadCircularProgressBar(message: String) {
        parent.removeAllViews()
        val circularProgressView = CircularProgressView(context!!)
        circularProgressView.loadingText = message
        parent.addView(circularProgressView)
    }

    fun loadMainContent() {
        parent.removeAllViews()
        parent.addView(childView)
    }
}
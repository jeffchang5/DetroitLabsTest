package io.jeffchang.detroitlabschallenge.ui.common

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.FrameLayout
import io.jeffchang.detroitlabschallenge.ui.common.views.CircularProgressView
import io.jeffchang.detroitlabschallenge.ui.common.views.NoInternetView
import io.jeffchang.detroitlabschallenge.util.ResourceUtil

/**
 * Created by jeffreychang on 1/29/18.
 */
abstract class InternetFragment : BaseFragment() {

    private lateinit var parent: FrameLayout

    var childView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

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

    fun loadNoInternet(callback: (() -> Unit)?, height: Int?) {
        parent.removeAllViews()
        val noInternetView = NoInternetView(context!!)
        if (height != null)
            noInternetView.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,
                    ResourceUtil.convertDpToPixel(context!!, height))
        noInternetView.tryAgainCallback = {
            loadCircularProgressBar("Trying again...")
            callback?.invoke()
        }
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
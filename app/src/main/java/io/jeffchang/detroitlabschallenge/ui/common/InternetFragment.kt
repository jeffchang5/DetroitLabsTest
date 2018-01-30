package io.jeffchang.detroitlabschallenge.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.*
import android.widget.FrameLayout
import io.jeffchang.detroitlabschallenge.ui.common.views.NoInternetView

/**
 * Created by jeffreychang on 1/29/18.
 */
abstract class InternetFragment : BaseFragment() {

    private lateinit var parent: FrameLayout

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        parent = FrameLayout(context)
        parent.layoutParams = FrameLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        val child = inflater.inflate(layoutResourceID, container, false)
        parent.addView(child)
        failAndTryAgain(null)
        return parent
    }

    fun failAndTryAgain(callback: (() -> Unit)?) {
        parent.removeAllViews()
        parent.addView(NoInternetView(context!!))
    }
}
package io.jeffchang.detroitlabschallenge.ui.common

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by jeffreychang on 1/30/18.
 */
abstract class BaseFragment : Fragment() {

    abstract var layoutResourceID: Int


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutResourceID, container, false)
    }

}
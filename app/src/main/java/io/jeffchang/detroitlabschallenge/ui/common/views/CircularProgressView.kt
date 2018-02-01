package io.jeffchang.detroitlabschallenge.ui.common.views

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.widget.RelativeLayout
import android.widget.TextView
import io.jeffchang.detroitlabschallenge.R
import kotlinx.android.synthetic.main.view_circular_progress.view.*

/**
 * Created by jeffreychang on 1/29/18.
 */
class CircularProgressView : RelativeLayout {

    var loadingText: String? = null
        set(text) {
            circular_progress_textview_loading.text = text
        }

    constructor(context: Context): super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {
        inflate(context, R.layout.view_circular_progress, this)
    }
}
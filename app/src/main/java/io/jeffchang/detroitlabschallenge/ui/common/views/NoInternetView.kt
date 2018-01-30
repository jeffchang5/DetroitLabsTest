package io.jeffchang.detroitlabschallenge.ui.common.views

import android.content.Context
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.AttributeSet
import android.widget.RelativeLayout
import io.jeffchang.detroitlabschallenge.R
import kotlinx.android.synthetic.main.view_no_internet.view.*

/**
 * Created by jeffreychang on 1/29/18.
 */
class NoInternetView: RelativeLayout  {

    constructor(context: Context): super(context) {
        init(context, null, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs, null)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int?) {
        inflate(context, R.layout.view_no_internet, this)
        val tryAgainSpan = SpannableString(no_internet_textview_try_again.text)
        tryAgainSpan.setSpan(UnderlineSpan(), 0 , tryAgainSpan.length, 0)
        no_internet_textview_try_again.text = tryAgainSpan
    }

}
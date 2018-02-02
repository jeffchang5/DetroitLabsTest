package io.jeffchang.detroitlabschallenge.util

import android.content.Context
import android.support.annotation.DimenRes
import android.util.TypedValue

/**
 * Created by jeffreychang on 2/1/18.
 */

object ResourceUtil{

    fun convertDpResToPixel(context: Context, @DimenRes res: Int): Int {
        return context.resources.getDimensionPixelSize(res)
    }
    fun convertDpToPixel(context: Context, dim: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                (dim.toFloat()),
                context.resources.getDisplayMetrics())
                .toInt()
    }
}
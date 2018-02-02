package io.jeffchang.detroitlabschallenge.util

import android.app.Activity
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability

/**
 * Created by jeffreychang on 2/1/18.
 */

private const val PLAY_SERVICES_RESOLUTION_REQUEST = 1337

object GooglePlayUtil {
    fun checkPlayServices(activity: Activity): Boolean {
        val apiAvailability = GoogleApiAvailability.getInstance()
        val resultCode = apiAvailability.isGooglePlayServicesAvailable(activity)
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(activity, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show()
            } else {
                activity.finish()
            }
            return false
        }
        return true
    }
}

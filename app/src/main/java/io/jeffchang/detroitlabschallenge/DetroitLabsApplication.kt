package io.jeffchang.detroitlabschallenge

import android.app.Application
import com.facebook.stetho.Stetho
import timber.log.Timber

/**
 * Created by jeffreychang on 1/29/18.
 */

class DetroitLabsApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this);
        }
    }

}
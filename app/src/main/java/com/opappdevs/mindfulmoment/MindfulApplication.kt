package com.opappdevs.mindfulmoment

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Application class for configuring the project.
 * */
@HiltAndroidApp     // enable Hilt DI
class MindfulApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {                // enable Timber logging for debug builds
            Timber.plant(object: Timber.DebugTree() {
                override fun log(               // add prefix to logcat tags (easier to find)
                    priority: Int, tag: String?, message: String, t: Throwable?
                ) {
                    super.log(priority, "t:$tag", message, t)
                }
                // add method name to logcat entries
                override fun createStackElementTag(element: StackTraceElement): String {
                    return String.format(
                        "%s:%s",
                        super.createStackElementTag(element),
                        element.methodName
                    )
                }
            })
        }
    }
}
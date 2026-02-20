package com.opappdevs.mindfulmoment

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

/**
 * Application class for configuring the project.
 * */
@HiltAndroidApp
class MindfulApplication: Application(), Configuration.Provider {

    // make Hilt function in workers
    // Inject the HiltWorkerFactory. Hilt provides this automatically.
    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()
        // enable Timber logging for debug builds
        if (BuildConfig.DEBUG) {
            Timber.plant(object: Timber.DebugTree() {
                // add prefix to logcat tags (easier to find)
                override fun log(
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

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory) // Tell WorkManager to use Hilt's factory
            .build()
}
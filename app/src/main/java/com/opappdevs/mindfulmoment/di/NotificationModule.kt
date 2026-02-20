package com.opappdevs.mindfulmoment.di

import android.content.Context
import com.opappdevs.mindfulmoment.data.prefs.PreferencesManager
import com.opappdevs.mindfulmoment.domain.notification.AlarmScheduler
import com.opappdevs.mindfulmoment.services.DefaultAlarmSchedulerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {

    @Provides
    @Singleton
    fun provideAlarmScheduler(
        @ApplicationContext context: Context,
        preferencesManager: PreferencesManager
    ): AlarmScheduler {
        return DefaultAlarmSchedulerImpl(
            context = context,
            _preferencesManager = preferencesManager
        )
    }
}
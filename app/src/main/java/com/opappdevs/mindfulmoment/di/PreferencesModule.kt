package com.opappdevs.mindfulmoment.di

import android.app.Application
import com.opappdevs.mindfulmoment.data.prefs.PreferencesManager
import com.opappdevs.mindfulmoment.data.prefs.SimplifiedPreferencesManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    @Provides
    @Singleton
    fun providePreferencesManager(context: Application): PreferencesManager {
        return SimplifiedPreferencesManagerImpl(context)
    }
}
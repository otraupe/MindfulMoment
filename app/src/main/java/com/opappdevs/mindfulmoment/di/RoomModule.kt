package com.opappdevs.mindfulmoment.di

import android.content.Context
import com.opappdevs.mindfulmoment.data.db.RoomDb
import com.opappdevs.mindfulmoment.data.db.model.user.UserDao
import com.opappdevs.mindfulmoment.data.db.model.user.UserDbRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * Hilt dependency injection module for Room ORM. Provides the user DAO and DB repository.
 * */
@InstallIn(SingletonComponent::class)
@Module
object DBModule {

    @Provides
    fun provideUserDao(@ApplicationContext appContext: Context) : UserDao {
        return RoomDb.getInstance(appContext).userDao()
    }
    @Provides
    fun provideUserDBRepository(userDao: UserDao) = UserDbRepository(userDao)
}
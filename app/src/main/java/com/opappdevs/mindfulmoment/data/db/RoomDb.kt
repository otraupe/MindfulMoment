package com.opappdevs.mindfulmoment.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.opappdevs.mindfulmoment.constants.ROOM_DB_NAME
import com.opappdevs.mindfulmoment.data.db.model.user.User
import com.opappdevs.mindfulmoment.data.db.model.user.UserDao
import timber.log.Timber

/**
 * RoomDatabase class creating and providing access to the Room ORM.
 * */
@Database(entities = [User::class], version = 1)
abstract class RoomDb : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        private var instance: RoomDb? = null                // singleton pattern

        @Synchronized
        fun getInstance(ctx: Context): RoomDb {             // create new or open existing
            if(instance == null)
                instance = Room.databaseBuilder(
                    ctx.applicationContext, RoomDb::class.java,
                    ROOM_DB_NAME
                )               // could also set db name
                    // from local.defaults.properties
                    // via Secrets Gradle Plugin
                    .fallbackToDestructiveMigration(false)
                    .addCallback(roomCallback)
                    .build()

            return instance!!
        }

        private val roomCallback = object : Callback() {    // callback for db creation
            override fun onCreate(db: SupportSQLiteDatabase) {
                Timber.d("Room DB created, version is :" + db.version.toString())
                super.onCreate(db)
            }
        }
    }
}
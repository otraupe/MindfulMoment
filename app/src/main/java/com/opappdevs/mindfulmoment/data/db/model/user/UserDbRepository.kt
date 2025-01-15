package com.opappdevs.mindfulmoment.data.db.model.user

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import timber.log.Timber
import java.util.*
import javax.inject.Inject

/**
 * User DB repository for Room ORM. Also serves the purpose of a user manager. In production we
 * better stick to the single responsibility principle.
 * */
class UserDbRepository @Inject constructor(private val userDao: UserDao){   // injectable by Hilt

    private val mutex = Mutex()                 // synchronize public functions

    // cache
    private var currentUser: User? = null       // cache current user

    // db queries are always private
    private suspend fun insert(user: User) = userDao.insert(user)
    private suspend fun update(user: User) = userDao.update(user)
    private suspend fun delete(user: User) = userDao.delete(user)
    private suspend fun deleteByName(name: String) = userDao.deleteByName(name)
    private suspend fun get(id: Int) = userDao.get(id)
    private suspend fun getByName(name: String) = userDao.getByName(name)
    private suspend fun getMostRecentUpdated() = userDao.getMostRecentUpdated()

    //TODO: extract UserManager functions from repo

    // public functions are synchronized via mutex to prevent db queries while update performed
    // also always return getCurrentUser()
    suspend fun setCurrentUser(name: String): User {
        mutex.withLock {
            // 1. invalidate cache (make sure returned current user is always up-to-date)
            invalidateCache()
            // 2. look for user in db
            var user = getByName(name)
            // 3. if present, set currentUser and return it
            if (user != null) {
                currentUser = user
                return@withLock
            }
            // 4. else create this user
            Timber.w("User not found, creating new user")
            insert(User(name = name, createdDate = Date(), updatedDate = Date()))
            // 5. load this user from db (ensure persisting worked)
            user = getByName(name)
            // 6. if success, set current user
            if (user != null) {
                currentUser = user
            } else {
                Timber.w("Creating new user failed, returning default current user")
            }
        }
        // 7. return new current user or apply alternative methods for getCurrentUser()
        return getCurrentUser()     // mutex is not a reentrant lock!
    }

    suspend fun getCurrentUser() : User {
        mutex.withLock {
            return currentUser ?: getLatestUser() ?: createFirstUser()
        }
    }

    private suspend fun getLatestUser(): User? {
        val user = getMostRecentUpdated()
        if (user != null) {
            currentUser = user
            return getCurrentUser()
        }
        return null
    }

    private suspend fun createFirstUser(): User {
        val name = "Spieler"
        insert(User(name = name, createdDate = Date(), updatedDate = Date())) // auto-id
        val user = getByName(name)
        if (user != null) {
            currentUser = user
            return getCurrentUser()
        }
        val message = "Creation of first user failed"
        Timber.e(message)
        throw IllegalStateException(message)     // fail fast   // TODO: inform user via Toast
    }

    private fun invalidateCache() {
        currentUser = null
    }
}
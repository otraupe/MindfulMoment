package com.opappdevs.mindfulmoment.data.db.model.user

import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)     // need to 'update' for changing values
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("DELETE FROM user_table WHERE name LIKE :name")
    suspend fun deleteByName(name: String)

    @Query("SELECT * FROM user_table WHERE id LIKE :id")
    suspend fun get(id: Int): User?

    @Query("SELECT * FROM user_table WHERE name LIKE :name")
    suspend fun getByName(name: String): User?

    @Query("SELECT * FROM user_table WHERE updatedDate = ( SELECT MAX(updatedDate) FROM user_table )")
    suspend fun getMostRecentUpdated(): User?
}
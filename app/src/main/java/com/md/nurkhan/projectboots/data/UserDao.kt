package com.md.nurkhan.projectboots.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.md.nurkhan.projectboots.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUser()

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE name LIKE :searchQuery OR name3 LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<User>>
}
package com.md.nurkhan.projectboots.reposirory

import androidx.lifecycle.LiveData
import com.md.nurkhan.projectboots.data.UserDao
import com.md.nurkhan.projectboots.model.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun  updateUser(user: User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }

    suspend fun deleteUserAll(){
        userDao.deleteAllUser()
    }

    suspend fun searchDatabase(searchQuery: String): Flow<List<User>> {
        return userDao.searchDatabase(searchQuery)
    }
}
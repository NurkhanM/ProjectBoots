package com.md.nurkhan.projectboots.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.md.nurkhan.projectboots.data.UserDataBase
import com.md.nurkhan.projectboots.reposirory.UserRepository
import com.md.nurkhan.projectboots.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(aplication: Application): AndroidViewModel(aplication) {

    val readAllData: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = UserDataBase.getDataBase(aplication).UserDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            repository.addUser(user)
        }
    }

    fun updateUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

    fun deleteUser(user: User){
        viewModelScope.launch (Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

    fun deleteAllUsers(){
        viewModelScope.launch (Dispatchers.IO) {
            repository.deleteUserAll()
        }
    }

    suspend fun searchDatabase(searchQuery: String): LiveData<List<User>>{
        return repository.searchDatabase(searchQuery).asLiveData()
    }
}
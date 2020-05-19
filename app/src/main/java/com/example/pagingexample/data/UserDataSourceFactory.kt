package com.example.pagingexample.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.pagingexample.data.UserDataSource
import com.example.pagingexample.model.User

class UserDataSourceFactory : DataSource.Factory<Int, User>() {
    val userLiveDataSource = MutableLiveData<UserDataSource>()
    override fun create(): DataSource<Int, User> {
        val userDataSource = UserDataSource()
        userLiveDataSource.postValue(userDataSource)
        return userDataSource
    }
}
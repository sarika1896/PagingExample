package com.example.pagingexample.viewmodel

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.pagingexample.data.UserDataSource
import com.example.pagingexample.data.UserDataSourceFactory
import com.example.pagingexample.model.User
import com.squareup.picasso.Picasso

class UserViewModel: ViewModel() {

    var userPagedList : LiveData<PagedList<User>>
    private var liveDataSource: LiveData<UserDataSource>

    init {
        val itemDataSourceFactory = UserDataSourceFactory()
        liveDataSource = itemDataSourceFactory.userLiveDataSource
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(UserDataSource.PAGE_SIZE)
            .build()
        userPagedList = LivePagedListBuilder(itemDataSourceFactory, config)
            .build()
    }
}
@BindingAdapter("app:setImage")
fun setImage(view: ImageView, url: String) {

    Picasso.get().load(url).into(view)

}
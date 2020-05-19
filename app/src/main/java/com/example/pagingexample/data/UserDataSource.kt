package com.example.pagingexample.data

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.pagingexample.model.User
import com.example.pagingexample.model.UserResponse
import dev.ashish.recyclerviewwithviewmodellivedata.retrofit.ApiClient
import dev.ashish.recyclerviewwithviewmodellivedata.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDataSource : PageKeyedDataSource<Int, User>() {
    companion object {
        const val PAGE_SIZE = 6
        const val FIRST_PAGE = 1
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, User>
    ) {
        val service = ApiClient.createService(ApiInterface::class.java)
        val call = service.getUserDetails(FIRST_PAGE)
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.data
                    responseItems?.let {
                        callback.onResult(responseItems, null, FIRST_PAGE + 1)
                    }
                    Log.d("sddda1",responseItems.toString())
                }
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
            }
        })

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        val service = ApiClient.createService(ApiInterface::class.java)
        val call = service.getUserDetails(params.key)
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.data
                    val key = params.key + 1
                    responseItems?.let {
                        callback.onResult(responseItems, key)
                    }
                    Log.d("sddda2",responseItems.toString())
                }
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
            }
        })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        val service = ApiClient.createService(ApiInterface::class.java)
        val call = service.getUserDetails(params.key)

        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.data
                    val key = if (params.key > 1) params.key - 1 else 0
                    responseItems?.let {
                        callback.onResult(responseItems, key)
                    }
                    Log.d("sddda",responseItems.toString())
                }
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
            }
        })
    }
}
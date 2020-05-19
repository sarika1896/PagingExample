package dev.ashish.recyclerviewwithviewmodellivedata.retrofit

import com.example.pagingexample.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("users")
    fun getUserDetails(@Query("page") page: Int): Call<UserResponse>
}
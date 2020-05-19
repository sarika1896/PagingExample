package com.example.pagingexample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pagingexample.R
import com.example.pagingexample.adapter.UserDetailsAdapter
import com.example.pagingexample.databinding.ActivityMainBinding
import com.example.pagingexample.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val userViewModel by lazy {
        ViewModelProviders.of(this).get(UserViewModel::class.java)
    }

    private lateinit var adapter: UserDetailsAdapter
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

       activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        adapter = UserDetailsAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)

        userViewModel.userPagedList.observe(this, Observer {
            adapter.submitList(it)
        })
        recyclerView.adapter = adapter
    }

}


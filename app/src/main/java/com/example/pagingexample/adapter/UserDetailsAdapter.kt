package com.example.pagingexample.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingexample.R
import com.example.pagingexample.model.User
import com.example.pagingexample.databinding.UserDetailsBinding

class UserDetailsAdapter:
    PagedListAdapter<User, UserDetailsAdapter.ItemViewHolder>(UserDetailsDiffCallback()) {

    inner class ItemViewHolder(private val userDetailsBinding: UserDetailsBinding) :
        RecyclerView.ViewHolder(userDetailsBinding.root) {
        fun bind(item: User) {
            userDetailsBinding.userModel = item
            userDetailsBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view: UserDetailsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.lsv_user_details,
            parent, false
        )
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val user = getItem(position)
        user?.let { holder.bind(it) }

    }

    class UserDetailsDiffCallback : DiffUtil.ItemCallback<User>() {

        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }


    }
}
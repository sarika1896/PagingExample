package com.example.pagingexample.model

data class User (
    var avatar: String,
    var email: String,
    var first_name: String,
    var id: Long,
    var last_name: String
)
data class UserResponse (
    var data: List<User>,
    var page: Int,
    var per_page: Long,
    var total: Long,
    var total_pages: Int
)
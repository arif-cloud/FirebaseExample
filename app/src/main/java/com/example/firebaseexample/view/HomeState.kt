package com.example.firebaseexample.view

import com.example.firebaseexample.model.User

data class HomeState(
    val isLoading: Boolean = false,
    val data: List<User> = emptyList(),
    val error: String = ""
)

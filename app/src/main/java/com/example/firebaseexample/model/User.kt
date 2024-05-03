package com.example.firebaseexample.model

data class User(
    val firstName : String,
    val lastName : String,
    val age : Int
) {
    constructor() : this("", "", 0)
}

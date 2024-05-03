package com.example.firebaseexample.repository

import com.example.firebaseexample.model.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class UserRepository(
    private val db : FirebaseFirestore = Firebase.firestore
) {

    fun getUsers(getUsersCallback : (Result<List<User>>) -> Unit) {
        db.collection("users").addSnapshotListener { value, error ->
            value?.let {querySnapshot ->
                val userList = querySnapshot.documents.map { it.toObject(User::class.java) ?: User() }
                getUsersCallback(Result.success(userList))
            }
            error?.let {e ->
                getUsersCallback(Result.failure(e))
            }
        }
    }

    fun addUser(user : User, addUserCallback : (Result<String>) -> Unit) {
        db.collection("users").add(user).addOnSuccessListener {
            addUserCallback(Result.success("User added"))
        }.addOnFailureListener {e ->
            addUserCallback(Result.failure(e))
        }
    }

    fun deleteUser(user : User) {
        val usersCollection = db.collection("users")
        val query = usersCollection
            .whereEqualTo("firstName", user.firstName)
            .whereEqualTo("lastName", user.lastName)
            .whereEqualTo("age", user.age)
        query.get().addOnSuccessListener {querySnapshot ->
            querySnapshot.documents[0].reference.delete()
        }
    }

}
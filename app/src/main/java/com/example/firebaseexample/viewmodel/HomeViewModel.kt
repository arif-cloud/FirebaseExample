package com.example.firebaseexample.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.firebaseexample.model.User
import com.example.firebaseexample.repository.UserRepository
import com.example.firebaseexample.view.HomeState

class HomeViewModel(
    private val repository: UserRepository = UserRepository()
) : ViewModel() {

    private val _homeState : MutableState<HomeState> = mutableStateOf(HomeState())
    val homeState : State<HomeState> = _homeState

    init {
        fetchData()
    }

    private fun fetchData() {
        _homeState.value = HomeState(isLoading = true)
        repository.getUsers {result ->
            result.onSuccess {
                userList -> _homeState.value = HomeState(data = userList)
            }.onFailure {e ->
                _homeState.value = HomeState(error = e.localizedMessage.orEmpty())
            }
        }
    }

    fun addUser(user: User, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        repository.addUser(user) {result ->
            result.onSuccess {
                onSuccess()
            }.onFailure {e ->
                onFailure(e.localizedMessage.orEmpty())
            }
        }
    }

    fun deleteUser(user: User) {
        repository.deleteUser(user)
    }

}
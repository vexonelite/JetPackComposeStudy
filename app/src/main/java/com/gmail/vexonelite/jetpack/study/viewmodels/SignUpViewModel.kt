package com.gmail.vexonelite.jetpack.study.viewmodels


import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class SimpleLoginState(
    val loginResult: Int = Int.MIN_VALUE
)


class SignUpViewModel : ViewModel() {


    var username by mutableStateOf("")
        private set

    fun updateUsername(input: String) {
        username = input
    }

    private val _loginResultStates = MutableStateFlow<SimpleLoginState>(SimpleLoginState())
    val loginResultStates: StateFlow<SimpleLoginState> = _loginResultStates.asStateFlow()

    fun resetLoginResultStates() {
        _loginResultStates.update { currentState ->
            currentState.copy(loginResult = Int.MIN_VALUE)
        }
    }

    fun fakeLogin() {
        _loginResultStates.update { currentState ->
            currentState.copy(loginResult = 0)
        }

        viewModelScope.launch {
            delay(3000L)
            _loginResultStates.update { currentState ->
                currentState.copy(loginResult = 1)
            }
        }
    }

}




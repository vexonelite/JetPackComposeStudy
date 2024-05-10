package com.gmail.vexonelite.jetpack.study.viewmodels


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class AppUiStateViewModel : ViewModel() {
    private val _appDialogStates = MutableStateFlow<DefaultAppDialogStates>(DefaultAppDialogStates())
    val appDialogStates: StateFlow<AppDialogStateDelegate> = _appDialogStates.asStateFlow()


    fun alterProgressDialogState(newState: Boolean, title: String? = null) {
        _appDialogStates.update { currentState ->
            currentState.copy(
                theProgressDialogState = newState,
                theProgressDialogTitle = title ?: currentState.theProgressDialogTitle,
            )
        }
    }


    fun alterSingleActionDialogState(newState: Boolean, title: String? = null, message: String? = null) {
        _appDialogStates.update { currentState ->
            currentState.copy(
                singleActionDialogState = newState,
                theSingleActionDialogTitle = title ?: currentState.theSingleActionDialogTitle,
                theSingleActionDialogMessage = message ?: currentState.theSingleActionDialogMessage
            )
        }
    }


    fun alterTwinActionsDialogState(newState: Boolean, title: String? = null, message: String? = null) {
        _appDialogStates.update { currentState ->
            currentState.copy(
                twinActionsDialogState = newState,
                theTwinActionsDialogTitle = title ?: currentState.theTwinActionsDialogTitle,
                theTwinActionsDialogMessage = message ?: currentState.theTwinActionsDialogMessage
            )
        }
    }
}






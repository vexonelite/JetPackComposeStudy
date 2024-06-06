package com.gmail.vexonelite.jetpack.study.viewmodels


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class LegacyBuiltInUiStateViewModel : ViewModel() {
    private val _appDialogStates = MutableStateFlow<LegacyBuiltInDialogStateImpl>(LegacyBuiltInDialogStateImpl())
    val appDialogStates: StateFlow<LegacyBuiltInDialogStateDelegate> = _appDialogStates.asStateFlow()


    fun alterProgressDialogState(newState: Boolean, title: String? = null) {
        _appDialogStates.update { currentState: LegacyBuiltInDialogStateImpl ->
            currentState.copy(
                theProgressDialogState = newState,
                theProgressDialogTitle = title ?: currentState.theProgressDialogTitle,
            )
        }
    }


    fun alterSingleActionDialogState(newState: Boolean, title: String? = null, message: String? = null) {
        _appDialogStates.update { currentState: LegacyBuiltInDialogStateImpl ->
            currentState.copy(
                singleActionDialogState = newState,
                theSingleActionDialogTitle = title ?: currentState.theSingleActionDialogTitle,
                theSingleActionDialogMessage = message ?: currentState.theSingleActionDialogMessage
            )
        }
    }


    fun alterTwinActionsDialogState(newState: Boolean, title: String? = null, message: String? = null) {
        _appDialogStates.update { currentState: LegacyBuiltInDialogStateImpl ->
            currentState.copy(
                twinActionsDialogState = newState,
                theTwinActionsDialogTitle = title ?: currentState.theTwinActionsDialogTitle,
                theTwinActionsDialogMessage = message ?: currentState.theTwinActionsDialogMessage
            )
        }
    }
}



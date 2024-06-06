package com.gmail.vexonelite.jetpack.study.viewmodels


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.logging.Level
import java.util.logging.Logger


class BuiltInUiStateViewModel : ViewModel() {
    private val _progressDialogState = MutableStateFlow<BuiltInDialogStateImpl>(BuiltInDialogStateImpl())
    val progressDialogState: StateFlow<BuiltInDialogStateDelegate> = _progressDialogState.asStateFlow()

    private val _singleActionDialogState = MutableStateFlow<BuiltInDialogStateImpl>(BuiltInDialogStateImpl())
    val singleActionDialogState: StateFlow<BuiltInDialogStateDelegate> = _singleActionDialogState.asStateFlow()

    private val _twinActionsDialogState = MutableStateFlow<BuiltInDialogStateImpl>(BuiltInDialogStateImpl())
    val twinActionsDialogState: StateFlow<BuiltInDialogStateDelegate> = _twinActionsDialogState.asStateFlow()

    fun alterProgressDialogState(newState: Boolean, message: String? = null, ) {
        Logger.getLogger("BuiltInUiStateViewModel").log(Level.INFO, "alterProgressDialogState()： newState: [$newState]")
        _progressDialogState.update { currentState: BuiltInDialogStateImpl ->
            currentState.copy(
                theDialogType = if (newState) { DialogType.Progress } else { DialogType.None },
                theDialogState = newState,
                theMessage = message ?: currentState.theMessage,
            )
        }
    }


    fun alterSingleActionDialogState(
        newState: Boolean,
        title: String? = null,
        message: String? = null,
        onDismiss: () -> Unit = {},
        onConfirm: () -> Unit = {},
    ) {
        Logger.getLogger("BuiltInUiStateViewModel").log(Level.INFO, "alterSingleActionDialogState()： newState: [$newState]")
        _singleActionDialogState.update { currentState: BuiltInDialogStateImpl ->
            currentState.copy(
                theDialogType = if (newState) { DialogType.SingleAction } else { DialogType.None },
                theDialogState = newState,
                theTitle = title ?: currentState.theTitle,
                theMessage = message ?: currentState.theMessage,
                onDismiss = if (newState) { onDismiss } else { {} },
                onConfirm = if (newState) { onConfirm } else { {} },
            )
        }
    }


    fun alterTwinActionsDialogState(
        newState: Boolean,
        title: String? = null,
        message: String? = null,
        onDismiss: () -> Unit = {},
        onConfirm: () -> Unit = {},
    ) {
        Logger.getLogger("BuiltInUiStateViewModel").log(Level.INFO, "alterTwinActionsDialogState()： newState: [$newState]")
        _twinActionsDialogState.update { currentState ->
            currentState.copy(
                theDialogType = if (newState) { DialogType.TwinActions } else { DialogType.None },
                theDialogState = newState,
                theTitle = title ?: currentState.theTitle,
                theMessage = message ?: currentState.theMessage,
                onDismiss = if (newState) { onDismiss } else { {} },
                onConfirm = if (newState) { onConfirm } else { {} },
            )
        }
    }
}


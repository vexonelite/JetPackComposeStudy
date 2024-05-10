package com.gmail.vexonelite.jetpack.study.screens


import androidx.compose.runtime.Composable
import com.gmail.vexonelite.jetpack.study.viewmodels.AppDialogStateDelegate
import com.gmail.vexonelite.jetpack.study.viewmodels.FmBuiltInSingleActionDialog
import com.gmail.vexonelite.jetpack.study.viewmodels.FmBuiltInTwinActionsDialog
import com.gmail.vexonelite.jetpack.study.viewmodels.FmProgressDialog


@Composable
fun AppDialogs01(
    appDialogStates: AppDialogStateDelegate,
    onProgressDialogDismiss: () -> Unit,
    onSingleActionDialogConfirmClick: () -> Unit,
    onSingleActionDialogDismiss: () -> Unit,
    onTwinActionsDialogConfirmClick: () -> Unit,
    onTwinActionsDialogDismiss: () -> Unit,
) {
    FmProgressDialog(
        title = appDialogStates.theProgressDialogTitle,
        dialogState = appDialogStates.theProgressDialogState,
        onDismiss = onProgressDialogDismiss,
    )

    FmBuiltInSingleActionDialog(
        dialogState = appDialogStates.singleActionDialogState,
        onConfirm = onSingleActionDialogConfirmClick,
        onDismiss = onSingleActionDialogDismiss,
        title = appDialogStates.theSingleActionDialogTitle,
        message = appDialogStates.theSingleActionDialogMessage,
    )

    FmBuiltInTwinActionsDialog(
        dialogState = appDialogStates.twinActionsDialogState,
        onConfirm = onTwinActionsDialogConfirmClick,
        onDismiss = onTwinActionsDialogDismiss,
        title = appDialogStates.theTwinActionsDialogTitle,
        message = appDialogStates.theTwinActionsDialogMessage,
    )
}







package com.gmail.vexonelite.jetpack.study.ntmofa


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.gmail.vexonelite.jetpack.study.screens.LoginScreenContent
import com.gmail.vexonelite.jetpack.study.ui.theme.navigateToExt
import com.gmail.vexonelite.jetpack.study.viewmodels.BuiltInProgressDialog01
import com.gmail.vexonelite.jetpack.study.viewmodels.BuiltInSingleActionDialog01
import com.gmail.vexonelite.jetpack.study.viewmodels.BuiltInTwinActionsDialog01
import com.gmail.vexonelite.jetpack.study.viewmodels.BuiltInUiStateViewModel
import com.gmail.vexonelite.jetpack.study.viewmodels.DialogType
import com.gmail.vexonelite.jetpack.study.viewmodels.SignUpViewModel
import com.gmail.vexonelite.jetpack.study.viewmodels.SimpleLoginState
import java.util.logging.Level
import java.util.logging.Logger


@Composable
fun NtmofaLoginScreen(
    navController: NavHostController,
) {
//    LoginScreenContent(
//        onLoginButtonClick = {
//            //navController.navigate(NtmofaRouteDestination.Menu.theRoute)
//            navController.navigateToExt(NtmofaRouteDestination.Menu.theRoute, clearBackStack = true)
//        },
//    )

    val builtInUiStateViewModel: BuiltInUiStateViewModel = viewModel()
    val signUpViewModel: SignUpViewModel = viewModel()

    val progressDialogState by builtInUiStateViewModel.progressDialogState.collectAsState()
    val singleActionDialogState by builtInUiStateViewModel.singleActionDialogState.collectAsState()
    val twinActionsDialogState by builtInUiStateViewModel.twinActionsDialogState.collectAsState()

    Logger.getLogger("NtmofaLoginScreen").log(Level.INFO, "progressDialogState: [${progressDialogState.theDialogType}, ${progressDialogState.theDialogState}]")
    Logger.getLogger("NtmofaLoginScreen").log(Level.INFO, "singleActionDialogState: [${singleActionDialogState.theDialogType}, ${singleActionDialogState.theDialogState}]")
    Logger.getLogger("NtmofaLoginScreen").log(Level.INFO, "twinActionsDialogState: [${twinActionsDialogState.theDialogType}, ${twinActionsDialogState.theDialogState}]")

    LoginScreenContent(
        onLoginButtonClick = {
            signUpViewModel.fakeLogin()
        },
    )

    val loginState: SimpleLoginState by signUpViewModel.loginResultStates.collectAsState()
    Logger.getLogger("NtmofaLoginScreen").log(Level.INFO, "loginState.loginResult: [${loginState.loginResult}]")
    when (loginState.loginResult) {
        0 -> {
            builtInUiStateViewModel.alterProgressDialogState(
                newState = true, message = "Loggin in..."
            )
        }
        1 -> {
            if ((progressDialogState.theDialogState) && (progressDialogState.theDialogType == DialogType.Progress)) {
                builtInUiStateViewModel.alterProgressDialogState(false,)
            }
            if ((!progressDialogState.theDialogState) && (progressDialogState.theDialogType == DialogType.None) &&
                (!singleActionDialogState.theDialogState) && (singleActionDialogState.theDialogType == DialogType.None)) {
                builtInUiStateViewModel.alterSingleActionDialogState(
                    newState = true,
                    title = "Login Result",
                    message = "Login Successfully!!",
                    onConfirm = {
                        Logger.getLogger("NtmofaLoginScreen").log(Level.INFO, "SingleAction.onConfirm: [1]")
                        signUpViewModel.resetLoginResultStates()
                        builtInUiStateViewModel.alterSingleActionDialogState(newState = false,)
                        navController.navigateToExt(NtmofaRouteDestination.Menu.theRoute, clearBackStack = true)
                    },
                    onDismiss = {
                        Logger.getLogger("NtmofaLoginScreen").log(Level.INFO, "SingleAction.onDismiss: [1]")
                        signUpViewModel.resetLoginResultStates()
                        builtInUiStateViewModel.alterSingleActionDialogState(newState = false,)
                    },
                )
            }

        }
        -1 -> {
            if ((progressDialogState.theDialogState) && (progressDialogState.theDialogType == DialogType.Progress)) {
                builtInUiStateViewModel.alterProgressDialogState(false,)
            }
            if ((!progressDialogState.theDialogState) && (progressDialogState.theDialogType == DialogType.None) &&
                (!singleActionDialogState.theDialogState) && (singleActionDialogState.theDialogType == DialogType.None)) {
                builtInUiStateViewModel.alterSingleActionDialogState(
                    newState = true,
                    title = "Login Result",
                    message = "Fail to Login!!",
                    onConfirm = {
                        Logger.getLogger("NtmofaLoginScreen").log(Level.INFO, "SingleAction.onConfirm: [-1]")
                        signUpViewModel.resetLoginResultStates()
                        builtInUiStateViewModel.alterSingleActionDialogState(newState = false,)
                    },
                    onDismiss = {
                        Logger.getLogger("NtmofaLoginScreen").log(Level.INFO, "SingleAction.onDismiss: [-1]")
                        signUpViewModel.resetLoginResultStates()
                        builtInUiStateViewModel.alterSingleActionDialogState(newState = false,)
                    },
                )
            }
        }
        else -> { }
    }

    BuiltInProgressDialog01(progressDialogState,)
    BuiltInSingleActionDialog01(singleActionDialogState,)
    BuiltInTwinActionsDialog01(twinActionsDialogState,)

}


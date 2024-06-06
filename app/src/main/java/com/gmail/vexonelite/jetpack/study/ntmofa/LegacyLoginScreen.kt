package com.gmail.vexonelite.jetpack.study.ntmofa


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.gmail.vexonelite.jetpack.study.screens.LoginScreenContent
import com.gmail.vexonelite.jetpack.study.ui.theme.navigateToExt
import com.gmail.vexonelite.jetpack.study.viewmodels.LegacyBuiltInDialogSet01
import com.gmail.vexonelite.jetpack.study.viewmodels.LegacyBuiltInUiStateViewModel
import com.gmail.vexonelite.jetpack.study.viewmodels.SignUpViewModel
import com.gmail.vexonelite.jetpack.study.viewmodels.SimpleLoginState


@Composable
fun LegacyNtmofaLoginScreen(
    navController: NavHostController,
) {

    val builtInUiStateViewModel: LegacyBuiltInUiStateViewModel = viewModel()
    val signUpViewModel: SignUpViewModel = viewModel()

    val loginState: SimpleLoginState by signUpViewModel.loginResultStates.collectAsState()
    when (loginState.loginResult) {
        0 -> {
            builtInUiStateViewModel.alterProgressDialogState(
                newState = true, title = "Loggin in..."
            )
        }
        1 -> {
            builtInUiStateViewModel.alterProgressDialogState(false)
            builtInUiStateViewModel.alterSingleActionDialogState(
                newState = true,
                title = "Login Result",
                message = "Login Successfully!!")
        }
        -1 -> {
            builtInUiStateViewModel.alterProgressDialogState(false)
            builtInUiStateViewModel.alterSingleActionDialogState(
                newState = true,
                title = "Login Result",
                message = "Fail to Login!!")
        }
        else -> {

        }
    }

    LoginScreenContent(
        onLoginButtonClick = {

            signUpViewModel.fakeLogin()
        },
    )

    val appDialogStates by builtInUiStateViewModel.appDialogStates.collectAsState()
    LegacyBuiltInDialogSet01(
        appDialogStates = appDialogStates,
        onProgressDialogDismiss = {
            builtInUiStateViewModel.alterProgressDialogState(false)
            println("Progress Dismiss")
        },
        onSingleActionDialogConfirmClick= {
            builtInUiStateViewModel.alterSingleActionDialogState(false)
            println("SingleAction Confirm")
            if(loginState.loginResult == 1) {
                signUpViewModel.resetLoginResultStates()
                navController.navigateToExt(NtmofaRouteDestination.Menu.theRoute, clearBackStack = true)
            }
        },
        onSingleActionDialogDismiss= {
            builtInUiStateViewModel.alterSingleActionDialogState(false)
            println("SingleAction Dismiss")
        },
        onTwinActionsDialogConfirmClick= {
            builtInUiStateViewModel.alterTwinActionsDialogState(false)
            println("TwinActions Confirm")
        },
        onTwinActionsDialogDismiss= {
            builtInUiStateViewModel.alterTwinActionsDialogState(false)
            println("TwinActions Dismiss")
        },
    )
}
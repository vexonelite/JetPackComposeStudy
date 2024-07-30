package com.gmail.vexonelite.jetpack.study


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.gmail.vexonelite.jetpack.study.ntmofa.NtmofaLoginScreen
import com.gmail.vexonelite.jetpack.study.ntmofa.NtmofaRfidApp
import com.gmail.vexonelite.jetpack.study.ntmofa.NtmofaRouteDestination
import com.gmail.vexonelite.jetpack.study.viewmodels.BuiltInDialogStateImpl
import com.gmail.vexonelite.jetpack.study.viewmodels.BuiltInDropDownMenu01
import com.gmail.vexonelite.jetpack.study.viewmodels.BuiltInDropDownMenu02
import com.gmail.vexonelite.jetpack.study.viewmodels.BuiltInProgressDialog01
import com.gmail.vexonelite.jetpack.study.viewmodels.BuiltInSingleActionDialog01
import com.gmail.vexonelite.jetpack.study.viewmodels.BuiltInTwinActionsDialog01
import com.gmail.vexonelite.jetpack.study.viewmodels.BuiltInUiStateViewModel
import com.gmail.vexonelite.jetpack.study.viewmodels.RequestBluetoothPermissionIfNeeded01
import com.gmail.vexonelite.jetpack.study.viewmodels.RequestCameraPermissionIfNeeded01
import com.gmail.vexonelite.jetpack.study.viewmodels.RequestTakePhotoPermissionIfNeeded01
import com.gmail.vexonelite.jetpack.study.viewmodels.RequestWriteStoragePermissionIfNeeded01
import java.util.logging.Level
import java.util.logging.Logger


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // AAA
        
        // Template
//        setContent {
//            JetPackComposeStudyTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
//            }
//        }

        setContent {
            // [start] Lesson 1
            //MessageCard("Irene")
            // [end] Lesson 1

            // [start] Lesson 2
            //MessageCard2(Message("Elite", "Irene"))
            //MessageCard2C(Message("Elite", "Irene"))
            //MessageCard2Image(Message("Elite", "Irene"))
            //MessageCard2ImageWithModifier(Message("Elite", "Irene"))
            //MessageCard2ImageWithModifier2(Message("Elite", "Irene"))
            // [end] Lesson 2

            // [start] Lesson 4
//            val messages = listOf<Message>(
//                Message("Elite", "Irene"),
//                Message("Jason", "Fu"),
//                Message("Tenchi", "Hwang")
//            )
//            Conversation(messages)
            // [end] Lesson 4

            //MinimalDialog({ println("Confirmation registered") })
            //DialogDemo01()
            //ProgressDemo02()
            //CustomDialogSample()
            ChipAlls()

            // [start] LazyColumn
            //ListColumnDemo02()
            // [end] LazyColumn

            // [start] LazyVerticalGrid
            //LazyVerticalGridDemo01()
            //LazyVerticalGridDemo02()
            // [end] LazyVerticalGrid

            // [start] TextField
            //TextFieldDemo01()
            //BasicTextFieldDemo01()
            //FocusDemo01()
            // [end] TextField

            //ScaffoldExample02()
            //ScaffoldExample03()

            //SainSignatureExample01()

            //BottomSheetExample02()

            //RequestCameraPermissionIfNeeded01()
            //RequestWriteStoragePermissionIfNeeded01()
            //RequestTakePhotoPermissionIfNeeded01()
            //RequestBluetoothPermissionIfNeeded01()

            // [start] Button
            //ButtonDemo01()
            // [end] Button

            //MenuScreen01()

            //RadioGroupDemo01()
            //BasicRadioButton001()
            //CustomRadioButton001()

            //CheckBoxDemo001()
            //CustomCheckboxExample()

            //UiStateSample1()
            //UiStateSample1Rev()
            //SampleManageHome1()

            // [start] DropDownMenu test - 2024/06/11
//            Column {
//                ExposedDropdownMenuSample01()
//
//                Spacer(modifier = Modifier.padding(vertical = 8.dp))
//
//                ExposedDropDownMenuWithBasicTextField01()
//
//                Spacer(modifier = Modifier.padding(vertical = 8.dp))
//
//                BuiltInDropDownMenu01()
//
//                Spacer(modifier = Modifier.padding(vertical = 8.dp))
//
//                BuiltInDropDownMenu02()
//            }
            // [end] DropDownMenu test - 2024/06/11

            // [start] navigation compose
//            val navController = rememberNavController()
//            val onBackPressedCallback = object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    when (navController.currentBackStackEntry?.destination?.route) {
//                        RouteDestination.Home.theRoute -> { finish() }
//                        RouteDestination.Login.theRoute -> { finish() }
//                        else ->  {
//                            // Navigate back
//                            navController.popBackStack()
//                        }
//                    }
//                }
//            }
//            onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
//
//            MyApp(navController)
            // [end] navigation compose

            // [start] 2024/05/08

//            val appUiStateViewModel: AppUiStateViewModel = viewModel()
//            val appDialogStates by appUiStateViewModel.appDialogStates.collectAsState()
//            LoginScreenContent(
//                onLoginButtonClick = {
//                    appUiStateViewModel.alterProgressDialogState(
//                        newState = true, title = "12345...")
//                    Handler(Looper.getMainLooper()).postDelayed({
//                        appUiStateViewModel.alterProgressDialogState(newState = false)
//                        appUiStateViewModel.alterTwinActionsDialogState(
//                            newState = true,
//                            title = "Off work",
//                            message = "Go buy cakes!! LOL")
//                    }, 2000L)
//                },
//            )
//
//            AppDialogs01(
//                appDialogStates = appDialogStates,
//                onProgressDialogDismiss = {
//                    appUiStateViewModel.alterProgressDialogState(false)
//                    println("Progress Dismiss")
//                },
//                onSingleActionDialogConfirmClick= {
//                    appUiStateViewModel.alterSingleActionDialogState(false)
//                    println("SingleAction Confirm")
//                },
//                onSingleActionDialogDismiss= {
//                    appUiStateViewModel.alterSingleActionDialogState(false)
//                    println("SingleAction Dismiss")
//                },
//                onTwinActionsDialogConfirmClick= {
//                    appUiStateViewModel.alterTwinActionsDialogState(false)
//                    println("TwinActions Confirm")
//                },
//                onTwinActionsDialogDismiss= {
//                    appUiStateViewModel.alterTwinActionsDialogState(false)
//                    println("TwinActions Dismiss")
//                },
////                progressDialogTitle = "Logging in...",
////                singleActionDialogTitle = "This is the Single Action dialog title",
////                singleActionDialogMessage = "This is the Dialog message: 1234567890 foo bar qoo zoo, balabala....",
////                twinActionsDialogTitle = "This is the Twin Actions    dialog title",
////                twinActionsDialogMessage = "This is the twin Dialog message: 1234567890 foo bar qoo zoo, balabala....",
//            )
            // [end] 2024/05/08



            // [start] navigation compose 02
//            val navController = rememberNavController()
//            val onBackPressedCallback = object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    when (navController.currentBackStackEntry?.destination?.route) {
//                        NtmofaRouteDestination.Menu.theRoute -> { finish() }
//                        NtmofaRouteDestination.Login.theRoute -> { finish() }
//                        else ->  {
//                            // Navigate back
//                            navController.popBackStack()
//                        }
//                    }
//                }
//            }
//            onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
//
//            NtmofaRfidApp(navController)
            // [end] navigation compose 02

            // [start] dialog-test - 2024/06/06
            // if not using Navigation Compose - the below dialog states get updated!
//            NtmofaLoginScreen()
//
//            val builtInUiStateViewModel: BuiltInUiStateViewModel = viewModel()
//            val progressDialogState by builtInUiStateViewModel.progressDialogState.collectAsState()
//            val singleActionDialogState by builtInUiStateViewModel.singleActionDialogState.collectAsState()
//            val twinActionsDialogState by builtInUiStateViewModel.twinActionsDialogState.collectAsState()
//
//            Logger.getLogger("MainActivity").log(Level.INFO, "progressDialogState: [${progressDialogState?.theDialogType}, ${progressDialogState?.theDialogState}]")
//            Logger.getLogger("MainActivity").log(Level.INFO, "singleActionDialogState: [${singleActionDialogState?.theDialogType}, ${singleActionDialogState?.theDialogState}]")
//            Logger.getLogger("MainActivity").log(Level.INFO, "twinActionsDialogState: [${twinActionsDialogState?.theDialogType}, ${twinActionsDialogState?.theDialogState}]")
//
//            BuiltInProgressDialog01(progressDialogState?: BuiltInDialogStateImpl(),)
//            BuiltInSingleActionDialog01(singleActionDialogState?: BuiltInDialogStateImpl(),)
//            BuiltInTwinActionsDialog01(twinActionsDialogState?: BuiltInDialogStateImpl(),)
            // [end] dialog-test - 2024/06/06

        }

    }
}





///



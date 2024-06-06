package com.gmail.vexonelite.jetpack.study


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.gmail.vexonelite.jetpack.study.ntmofa.NtmofaRfidApp
import com.gmail.vexonelite.jetpack.study.ntmofa.NtmofaRouteDestination


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            val navController = rememberNavController()
            val onBackPressedCallback = object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    when (navController.currentBackStackEntry?.destination?.route) {
                        NtmofaRouteDestination.Menu.theRoute -> { finish() }
                        NtmofaRouteDestination.Login.theRoute -> { finish() }
                        else ->  {
                            // Navigate back
                            navController.popBackStack()
                        }
                    }
                }
            }
            onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

            NtmofaRfidApp(navController)
            // [end] navigation compose 02

        }

    }
}





///



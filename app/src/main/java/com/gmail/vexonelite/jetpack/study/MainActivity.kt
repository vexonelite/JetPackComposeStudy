package com.gmail.vexonelite.jetpack.study


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.gmail.vexonelite.jetpack.study.screens.LoginScreen01
import com.gmail.vexonelite.jetpack.study.screens.MenuScreen01
import com.gmail.vexonelite.jetpack.study.ui.theme.JetPackComposeStudyTheme


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

            val progressDialogState: MutableState<Boolean> = remember { mutableStateOf(false) }
            val singleActionDialogState: MutableState<Boolean> = remember { mutableStateOf(false) }
            val twinActionsDialogState: MutableState<Boolean> = remember { mutableStateOf(false) }

            LoginScreen01(
                progressDialogState = progressDialogState,
                singleActionDialogState = singleActionDialogState,
                twinActionsDialogState = twinActionsDialogState,
                onLoginButtonClick = {
                    progressDialogState.value = true
                    Handler(Looper.getMainLooper()).postDelayed({
                        progressDialogState.value = false
                        twinActionsDialogState.value = true

                    }, 2000L)
                },
                onProgressDialogDismiss = {
                    progressDialogState.value = false
                    println("Progress Dismiss")
                },
                onSingleActionDialogConfirmClick= {
                    singleActionDialogState.value = false
                    println("SingleAction Confirm")
                },
                onSingleActionDialogDismiss= {
                    singleActionDialogState.value = false
                    println("SingleAction Dismiss")
                },
                onTwinActionsDialogConfirmClick= {
                    twinActionsDialogState.value = false
                    println("TwinActions Confirm")
                },
                onTwinActionsDialogDismiss= {
                    twinActionsDialogState.value = false
                    println("TwinActions Dismiss")
                },
                progressDialogTitle = "Logging in...",
                singleActionDialogTitle = "This is the Single Action dialog title",
                singleActionDialogMessage = "This is the Dialog message: 1234567890 foo bar qoo zoo, balabala....",
                twinActionsDialogTitle = "This is the Twin Actions    dialog title",
                twinActionsDialogMessage = "This is the twin Dialog message: 1234567890 foo bar qoo zoo, balabala....",
            )


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
        }


    }
}


///



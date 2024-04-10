package com.gmail.vexonelite.jetpack.study


import android.os.Bundle
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
            //ListColumnDemo02()

            //TextFieldDemo01()
            //BasicTextFieldDemo01()
            FocusDemo01()

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



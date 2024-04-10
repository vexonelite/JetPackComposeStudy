package com.gmail.vexonelite.jetpack.study

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gmail.vexonelite.jetpack.study.ui.theme.JetPackComposeStudyTheme


// [start] Template
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetPackComposeStudyTheme {
        Greeting("Android")
    }
}
// [end] Template


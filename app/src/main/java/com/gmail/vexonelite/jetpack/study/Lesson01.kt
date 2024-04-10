package com.gmail.vexonelite.jetpack.study


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


// [start] Lesson 1
@Composable
fun MessageCard(name: String) {
    Text(text = "I love $name!")
}

@Preview
@Composable
fun PreviewMessageCard() {
    MessageCard("Irene")
}
// [end] Lesson 1


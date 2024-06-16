package com.gmail.vexonelite.jetpack.study


import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun ScrollableRowContent01() {
    // Scroll state to remember the scroll position
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .horizontalScroll(scrollState) // Add horizontal scrolling behavior
            .padding(16.dp)
    ) {
        // Sample children to demonstrate scrolling
        for (i in 1..10) {
            Text(
                text = "Item $i",
                modifier = Modifier
                    .padding(8.dp)
                    .width(100.dp) // Adjust the width as needed
            )
        }
    }
}


@Composable
fun ScrollableColumnContent01() {
    // Scroll state to remember the scroll position
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState) // Add vertical scrolling behavior
            .padding(16.dp)
    ) {
        // Sample children to demonstrate scrolling
        for (i in 1..10) {
            Text(
                text = "Item $i",
                modifier = Modifier
                    .padding(8.dp)
                    .size(100.dp) // Adjust the size as needed
            )
        }
    }
}


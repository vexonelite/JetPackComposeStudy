package com.gmail.vexonelite.jetpack.study


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.gmail.vexonelite.jetpack.study.ui.theme.StringResource


/**
 * ``onClick``: The function called when the user presses the button.
 * ``enabled``: When false, this parameter causes the button to appear unavailable and inactive.
 * ``colors``: An instance of ButtonColors that determines the colors used in the button.
 * ``contentPadding``: The padding within the button.
 */
@Composable
fun Modifier01() {
    val context = LocalContext.current
    Modifier.padding(all = 8.dp)
    Modifier.padding(vertical = 15.dp)
    Modifier.padding(horizontal = 15.dp)
    Modifier.size(40.dp)
    Modifier.width(8.dp)
    Modifier.height(4.dp)
    Modifier.fillMaxWidth()
    Modifier.background(Color.Green)
    //Modifier.weight(1f)
    Modifier.clickable(
        onClick = {
            Toast.makeText(context, "你点击了此文本", Toast.LENGTH_LONG).show()
        },)
    Row(Modifier.background(Color.Green)) {
        Text(text = "AAA")
        Text(text = "BBB")
    }
}


@Composable
fun Spacer01() {
    Spacer(Modifier.size(40.dp))
    Spacer(Modifier.padding(vertical = 15.dp))
    Spacer(Modifier.padding(horizontal = 15.dp))
    Spacer(modifier = Modifier.width(8.dp))
    Spacer(modifier = Modifier.height(4.dp))
    //Spacer(Modifier.weight(1f))
}










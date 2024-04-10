package com.gmail.vexonelite.jetpack.study


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest


// [start] Lesson 2
data class Message(
    val author: String,
    val body: String
)

@Composable
fun MessageCard2(msg: Message) {
    Text(text = msg.author)
    Text(text = msg.body)
    // overlapping
}

@Composable
fun MessageCard2C(msg: Message) {
    Column {
        Text(text = msg.author)
        Text(text = msg.body)
    }
}

@Composable
fun MessageCard2Image(msg: Message) {
    Row {
        Image(
            painter = painterResource(R.drawable.lesson2_05),
            contentDescription = "Contact profile picture",
        )
        Column {
            Text(text = msg.author)
            Text(text = msg.body)
        }
    }
}

@Composable
fun MessageCard2ImageWithModifier(msg: Message) {
    // Add padding around our message
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.lesson2_05),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                // Set image size to 40 dp
                .size(40.dp)
                // Clip image to be shaped as a circle
                .clip(CircleShape)
        )

        // Add a horizontal space between the image and the column
        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(text = msg.author)
            // Add a vertical space between the author and message texts
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = msg.body)
        }
    }
}


@Composable
fun MessageCard2ImageWithModifier2(msg: Message) {
    // Add padding around our message
    Row(modifier = Modifier.padding(all = 8.dp)) {
//        Image(
//            painter = painterResource(R.drawable.lesson2_05),
//            contentDescription = "Contact profile picture",
//            modifier = Modifier
//                // Set image size to 40 dp
//                .size(40.dp)
//                // Clip image to be shaped as a circle
//                .clip(CircleShape)
//        )

        val imageModifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .border(BorderStroke(1.dp, Color.Black))
            .background(Color.Yellow)


        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://developer.android.com/static/images/jetpack/compose-tutorial/profile_picture.png")
                .crossfade(true)
                .build(),
            // java.lang.IllegalArgumentException: Only VectorDrawables and rasterized asset types are supported ex. PNG, JPG, WEBP
            //cannot use xml drawable!!
            //placeholder = painterResource(R.drawable.rect_gray_01),
            contentDescription = stringResource(R.string.image_description),
            //contentScale = ContentScale.Crop,
            //modifier = Modifier.clip(CircleShape)
            modifier = imageModifier
        )

        // Add a horizontal space between the image and the column
        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(text = msg.author)
            // Add a vertical space between the author and message texts
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = msg.body)
        }
    }
}
// [end] Lesson 2


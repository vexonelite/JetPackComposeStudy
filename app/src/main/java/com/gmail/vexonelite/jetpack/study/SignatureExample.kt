package com.gmail.vexonelite.jetpack.study


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import com.gmail.vexonelite.jetpack.study.ui.theme.Grey94
import com.gmail.vexonelite.jetpack.study.viewmodels.BuiltInSainSignature01
import java.util.logging.Level
import java.util.logging.Logger


@Composable
fun SainSignatureExample01() {
    var imageBitmap: ImageBitmap? by remember {
        mutableStateOf(null)
    }

//    val state: SignatureState = remember { SignatureState() }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            // Add vertical scrolling behavior
            .verticalScroll(scrollState)
            .fillMaxSize()
            .padding(all = 10.dp)
    ) {
        // Sample children to demonstrate scrolling
        BuiltInSainSignature01(
            onClear = {
                imageBitmap = null
            },
            onComplete = { signatureBitmap: ImageBitmap? ->
                Logger.getLogger("SainSignatureExample01").log(Level.INFO, "SainSignatureExample01() - onComplete - signatureBitmap is null? [${(null == signatureBitmap)}] }]")
                if (signatureBitmap != null) {
                    imageBitmap = signatureBitmap
                }
                else {
                    println("Signature is empty")
                }
            },
        )

        Spacer(modifier = Modifier.padding(vertical = 6.dp))

        Text("Result:")

        Spacer(modifier = Modifier.padding(vertical = 4.dp))

        val boxModifier = Modifier
            .fillMaxSize()
            .height(250.dp)
            .border(
                BorderStroke(
                    width = .5.dp,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                shape = RoundedCornerShape(8.dp)
            ).then(
                if (null != imageBitmap) { Modifier }
                else { Modifier.background(color = Grey94)}
            )
        Box(
            modifier = boxModifier,
            contentAlignment = Alignment.Center
        ) {
            imageBitmap?.let { bitmap ->
                Image(bitmap = bitmap, contentDescription = null)
            }
        }

        Spacer(modifier = Modifier.padding(vertical = 6.dp))

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




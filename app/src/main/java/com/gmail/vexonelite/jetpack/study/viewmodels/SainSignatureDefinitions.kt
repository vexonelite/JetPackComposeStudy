package com.gmail.vexonelite.jetpack.study.viewmodels


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gmail.vexonelite.jetpack.study.ui.theme.theBuiltInButtonColor01
import io.github.joelkanyi.sain.Sain
import io.github.joelkanyi.sain.SignatureAction
import io.github.joelkanyi.sain.SignatureState


/**
 * [Ref](https://joelkanyi.github.io/sain/usage/)
 */
@Preview
@Composable
fun BuiltInSainSignature01(
    height: Dp = 250.dp,
    borderStrokeWidth: Dp = .5.dp,
    borderStrokeColor: Color = MaterialTheme.colorScheme.onSurface,
    borderShape: Shape = RoundedCornerShape(8.dp),
    buttonFontSize: TextUnit = 20.sp,
    clearTitle: String = "Clear",
    completeTitle: String = "Complete",
    clearTitleColor: Color = Color.White,
    completeTitleColor: Color = Color.White,
    onClear: () -> Unit = {},
    onComplete: (signature: ImageBitmap?) -> Unit = {},
) {

//    var imageBitmap: ImageBitmap? by remember {
//        mutableStateOf(null)
//    }

    val state: SignatureState = remember { SignatureState() }

    Sain(
        state = state,
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .border(
                BorderStroke(
                    width = borderStrokeWidth,
                    color = borderStrokeColor
                ),
                shape = borderShape
            ),
        onComplete = onComplete,
    ) { action ->
        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                modifier = Modifier.weight(1f),
                shape = ButtonDefaults.shape, // | elevatedShape | outlinedShape | textShape
                colors = theBuiltInButtonColor01(),
                onClick = {
                    //imageBitmap = null
                    action(SignatureAction.CLEAR)
                    onClear()
                }
            ) {
                Text(
                    text = clearTitle,
                    fontSize = buttonFontSize,
                    color = clearTitleColor,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 5.dp, horizontal = 20.dp)
                )
            }
            Button(
                modifier = Modifier.weight(1f),
                shape = ButtonDefaults.shape, // | elevatedShape | outlinedShape | textShape
                colors = theBuiltInButtonColor01(),
                onClick = {
                    action(SignatureAction.COMPLETE)
                }
            ) {
                Text(
                    text = completeTitle,
                    fontSize = buttonFontSize,
                    color = completeTitleColor,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 5.dp, horizontal = 20.dp)
                )
            }
        }
    }
}


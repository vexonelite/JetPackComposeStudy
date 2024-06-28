package com.gmail.vexonelite.jetpack.study


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties


/**
 * * [Ref](https://developer.android.com/develop/ui/compose/components/dialog)
 * <p>
 * ``Dialog`` is a basic composable that doesn't provide any styling or predefined slots for content.
 * It is a relatively straightforward container that you should populate with a container such as ``Card``.
 * The following are some of the key parameters of a dialog:
 * ``onDismissRequest``: The lambda called when the user closes the dialog.
 * ``properties``: An instance of DialogProperties that provides some additional scope for customization.
 * <p>
 * __Caution__: Unlike the example of ``AlertDialog`` in the preceding section,
 * you need to manually specify the ``size`` and ``shape`` of ``Dialog``.
 * You also need to provide an inner container.
 */
@Preview
@Composable
fun MinimalDialog(onDismissRequest: () -> Unit= {}) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                //.fillMaxWidth()
                //.padding(all = 16.dp),  // when using wrapContentXXXX(), padding() has no effect!!
                .wrapContentHeight(unbounded = true),
                //.height(200.dp)
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                text = "This is a minimal dialog",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
                    .padding(vertical = 50.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
}


@Composable
fun DialogWithImage(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    painter: Painter,
    imageDescription: String,
    dialogState: State<Boolean>
) {
    if (!dialogState.value) { return }

    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true),
    ) {
        Card(
            modifier = Modifier
                //.fillMaxWidth()
                //.padding(all = 16.dp),  // when using wrapContentXXXX(), padding() has no effect!!
                .wrapContentHeight(unbounded = true),
                //.height(200.dp)
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                //Spacer(modifier = Modifier.padding(vertical = 8.dp))
                Image(
                    painter = painter,
                    contentDescription = imageDescription,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.height(160.dp)
                )
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                Text(
                    text = "This is a dialog with buttons and an image.",
                    //modifier = Modifier.padding(16.dp),
                )
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        //modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Dismiss")
                    }
                    TextButton(
                        onClick = { onConfirmation() },
                        //modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Confirm")
                    }
                }
            }
        }
    }
}


/**
 *  * [Ref1](https://stackoverflow.com/questions/74506670/how-to-dismiss-a-composable-dialog)
 *  * [Ref2](https://www.sinasamaki.com/custom-dialog-animation-in-jetpack-compose/)
 */
@Preview
@Composable
fun DialogDemo01() {
    val showDialog = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(
            onClick = {
                showDialog.value = true
            },
            //Modifier.weight(1 / 2f)
        ) {
            Text("Show Dialog")
        }
    }

    DialogWithImage(
        onDismissRequest = {
            showDialog.value = false
            println("Dismiss")
        },
        onConfirmation = {
            showDialog.value = false
            println("Confirmation")
        },
        painter = painterResource(id = R.drawable.feathertop),
        imageDescription = "WTF",
        showDialog
    )
}


@Composable
fun CustomDialog(
    onDismissRequest: () -> Unit,
    dialogContent: @Composable () -> Unit,
    dialogShape: Shape = ShapeDefaults.Medium
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            .clickable(
                onClick = onDismissRequest,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 32.dp)
                .background(color = Color.White, shape = dialogShape)
                .graphicsLayer {
                    shape = dialogShape
                    clip = true
                }
        ) {
            dialogContent()
        }
    }
}


@Composable
fun CustomDialogSample() {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        CustomDialog(
            onDismissRequest = { showDialog = false },
            dialogContent = {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(text = "This is a custom dialog")
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { showDialog = false }) {
                        Text(text = "Close")
                    }
                }
            }
        )
    }

    Button(onClick = { showDialog = true }) {
        Text(text = "Show Custom Dialog")
    }
}



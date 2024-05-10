package com.gmail.vexonelite.jetpack.study.viewmodels


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue003
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue007
import com.gmail.vexonelite.jetpack.study.ui.theme.Grey20
import com.gmail.vexonelite.jetpack.study.ui.theme.Pink001
import com.gmail.vexonelite.jetpack.study.ui.theme.Yellow001


enum class DialogAction {
    CONFIRM,
    CANCEL,
}


fun interface DialogDecisionDelegate2<T> {
    fun onDecisionMade(action: DialogAction, item: T?)
}


@Composable
fun FmProgressDialog(
    dialogState: State<Boolean>,
    title: String = "",
    onDismiss: () -> Unit = {},
) {
    if (!dialogState.value) { return }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true),
    ) {
        Card(
            modifier = Modifier
                //.padding(all = 16.dp) // when using wrapContentXXXX(), padding() has no effect!!
                .wrapContentSize(unbounded = true),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier.padding(all = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(32.dp),
                    color = Pink001,
                    trackColor = Yellow001,
                )
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
                Text(
                    text = title,
                    //modifier = Modifier.padding(16.dp),
                )
            }
        }
    }
}


@Composable
fun FmBuiltInSingleActionDialog(
    dialogState: State<Boolean>,
    title: String = "Title",
    titleFontSize: TextUnit = 26.sp,
    titleTextColor: Color = Blue003,
    message: String = "Message",
    messageFontSize: TextUnit = 20.sp,
    messageTextColor: Color = Blue003,
    confirmTitle: String = "Confirm",
    confirmFontSize: TextUnit = 20.sp,
    confirmTextColor: Color = Blue003,
    confirmBackgroundColor: Color = Color.Unspecified,
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {},
) {
    if (!dialogState.value) { return }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true),
    ) {
        Card(
            modifier = Modifier
                //.fillMaxWidth()
                //.padding(all = 16.dp),  // when using wrapContentXXXX(), padding() has no effect!!
                .wrapContentHeight(unbounded = true),
            shape = RoundedCornerShape(16.dp),

            ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.padding(vertical = 8.dp))

                Text(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    text = title,
                    fontSize = titleFontSize,
                    color = titleTextColor,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.padding(vertical = 8.dp))

                Text(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    text = message,
                    fontSize = messageFontSize,
                    color = messageTextColor,
                )

                Spacer(modifier = Modifier.padding(vertical = 8.dp))

                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color.Gray,
                )

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = onConfirm,),
                    //shape = MaterialTheme.shapes.small,
                    //shape = RoundedCornerShape(10.dp),
                    color = confirmBackgroundColor,
                    //contentColor = Color.White,
                    //border = BorderStroke(2.dp, Blue007),
                ) {
                    Text(
                        text = confirmTitle,
                        textAlign = TextAlign.Center,
                        color = confirmTextColor,
                        fontSize = confirmFontSize,
                        modifier = Modifier.padding(all = 16.dp),
                    )
                }
            }
        }
    }
}


@Composable
fun FmBuiltInTwinActionsDialog(
    dialogState: State<Boolean>,
    title: String = "Title",
    titleFontSize: TextUnit = 26.sp,
    titleTextColor: Color = Blue003,
    message: String = "Message",
    messageFontSize: TextUnit = 20.sp,
    messageTextColor: Color = Blue003,
    confirmTitle: String = "Confirm",
    confirmFontSize: TextUnit = 20.sp,
    confirmTextColor: Color = Blue003,
    confirmBackgroundColor: Color = Color.Unspecified,
    cancelTitle: String = "Cancel",
    cancelFontSize: TextUnit = 20.sp,
    cancelTextColor: Color = Grey20,
    cancelBackgroundColor: Color = Color.Unspecified,
    onDismiss: () -> Unit = {},
    onConfirm: () -> Unit = {},
) {
    if (!dialogState.value) { return }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true),
    ) {
        Card(
            modifier = Modifier
                //.fillMaxWidth()
                //.padding(all = 16.dp),  // when using wrapContentXXXX(), padding() has no effect!!
                .wrapContentHeight(unbounded = true),
            shape = RoundedCornerShape(16.dp),

        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.padding(vertical = 8.dp))

                Text(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    text = title,
                    fontSize = titleFontSize,
                    color = titleTextColor,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.padding(vertical = 8.dp))

                Text(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    text = message,
                    fontSize = messageFontSize,
                    color = messageTextColor,
                )

                Spacer(modifier = Modifier.padding(vertical = 8.dp))

                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color.Gray,
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min) // needed - make sure have enough height to accommodate the ``VerticalDivider``
                ) {
                    Surface(
                        modifier = Modifier
                            .clickable(onClick = onDismiss,)
                            .weight(1f),
                        //shape = MaterialTheme.shapes.small,
                        //shape = RoundedCornerShape(10.dp),
                        color = cancelBackgroundColor,
                        //contentColor = Color.White,
                        //border = BorderStroke(2.dp, Blue007),
                    ) {
                        Text(
                            text = cancelTitle,
                            textAlign = TextAlign.Center,
                            color = cancelTextColor,
                            fontSize = cancelFontSize,
                            modifier = Modifier.padding(all = 16.dp),
                        )
                    }

                    VerticalDivider(
                        //modifier = Modifier.fillMaxHeight().width(1.dp),
                        thickness = 1.dp,
                        color = Color.Gray,
                    )

                    Surface(
                        modifier = Modifier
                            .clickable(onClick = onConfirm,)
                            .weight(1f),
                        //shape = MaterialTheme.shapes.small,
                        //shape = RoundedCornerShape(10.dp),
                        color = confirmBackgroundColor,
                        //contentColor = Color.White,
                        //border = BorderStroke(2.dp, Blue007),
                    ) {
                        Text(
                            text = confirmTitle,
                            textAlign = TextAlign.Center,
                            color = confirmTextColor,
                            fontSize = confirmFontSize,
                            modifier = Modifier.padding(all = 16.dp),
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun FmItemPickerDialog(
    dialogState: State<Boolean>,
    title: String = "",
    onDismiss: () -> Unit = {},
) {
    if (!dialogState.value) { return }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true),
    ) {
        Card(
            modifier = Modifier
                //.padding(all = 16.dp) // when using wrapContentXXXX(), padding() has no effect!!
                .wrapContentSize(unbounded = true),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier.padding(all = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(32.dp),
                    color = Pink001,
                    trackColor = Yellow001,
                )
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
                Text(
                    text = title,
                    //modifier = Modifier.padding(16.dp),
                )
            }
        }
    }
}



package com.gmail.vexonelite.jetpack.study.viewmodels


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.gmail.vexonelite.jetpack.study.HolderItemClickDelegate
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue002
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue003
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue004
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue005
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue008
import com.gmail.vexonelite.jetpack.study.ui.theme.Grey005
import com.gmail.vexonelite.jetpack.study.ui.theme.Grey20
import com.gmail.vexonelite.jetpack.study.ui.theme.Pink001
import com.gmail.vexonelite.jetpack.study.ui.theme.Yellow001
import java.util.logging.Level
import java.util.logging.Logger


enum class DialogType {
    Progress,
    SingleAction,
    TwinActions,
    ItemPicker,
    None,
}


interface BuiltInDialogStateDelegate {
    val theDialogType: DialogType
    val theDialogState: Boolean

    val theTitle: String
    val theTitleFontSize: TextUnit
    val theTitleTextColor: Color

    val theMessage: String
    val theMessageFontSize: TextUnit
    val theMessageTextColor: Color

    val theConfirmTitle: String
    val theConfirmTitleFontSize: TextUnit
    val theConfirmTitleTextColor: Color
    val theConfirmBackgroundColor: Color

    val theCancelTitle: String
    val theCancelTitleFontSize: TextUnit
    val theCancelTitleTextColor: Color
    val theCancelBackgroundColor: Color

    val onDismiss: () -> Unit
    val onConfirm: () -> Unit
}


data class BuiltInDialogStateImpl(
    override val theDialogType: DialogType = DialogType.None,
    override val theDialogState: Boolean = false,

    override val theTitle: String = "Title",
    override val theTitleFontSize: TextUnit = 26.sp,
    override val theTitleTextColor: Color = Blue002,

    override val theMessage: String = "Message",
    override val theMessageFontSize: TextUnit = 26.sp,
    override val theMessageTextColor: Color = Blue002,

    override val theConfirmTitle: String = "Confirm",
    override val theConfirmTitleFontSize: TextUnit = 26.sp,
    override val theConfirmTitleTextColor: Color = Blue002,
    override val theConfirmBackgroundColor: Color = Color.Unspecified,

    override val theCancelTitle: String = "Cancel",
    override val theCancelTitleFontSize: TextUnit = 26.sp,
    override val theCancelTitleTextColor: Color = Grey005,
    override val theCancelBackgroundColor: Color = Color.Unspecified,

    override val onDismiss: () -> Unit = {},
    override val onConfirm: () -> Unit = {},
) : BuiltInDialogStateDelegate


interface MutableBuiltInDialogStateDelegate: BuiltInDialogStateDelegate {

    override var theDialogState: Boolean

    override var theTitle: String
    override var theTitleFontSize: TextUnit
    override var theTitleTextColor: Color

    override var theMessage: String
    override var theMessageFontSize: TextUnit
    override var theMessageTextColor: Color

    override var theConfirmTitle: String
    override var theConfirmTitleFontSize: TextUnit
    override var theConfirmTitleTextColor: Color
    override var theConfirmBackgroundColor: Color

    override var theCancelTitle: String
    override var theCancelTitleFontSize: TextUnit
    override var theCancelTitleTextColor: Color
    override var theCancelBackgroundColor: Color

    override var onDismiss: () -> Unit
    override var onConfirm: () -> Unit
}


data class MutableBuiltInDialogStateImpl(
    override val theDialogType: DialogType = DialogType.None,
    override var theDialogState: Boolean = false,

    override var theTitle: String = "Title",
    override var theTitleFontSize: TextUnit = 26.sp,
    override var theTitleTextColor: Color = Blue002,

    override var theMessage: String = "Message",
    override var theMessageFontSize: TextUnit = 26.sp,
    override var theMessageTextColor: Color = Blue002,

    override var theConfirmTitle: String = "Confirm",
    override var theConfirmTitleFontSize: TextUnit = 26.sp,
    override var theConfirmTitleTextColor: Color = Blue002,
    override var theConfirmBackgroundColor: Color = Color.Unspecified,

    override var theCancelTitle: String = "Cancel",
    override var theCancelTitleFontSize: TextUnit = 26.sp,
    override var theCancelTitleTextColor: Color = Grey005,
    override var theCancelBackgroundColor: Color = Color.Unspecified,

    override var onDismiss: () -> Unit = {},
    override var onConfirm: () -> Unit = {},
) : MutableBuiltInDialogStateDelegate


fun MutableBuiltInDialogStateDelegate.toBuiltInDialogStateDelegate(): BuiltInDialogStateDelegate =
    BuiltInDialogStateImpl(
        theDialogType = this.theDialogType,
        theDialogState = this.theDialogState,

        theTitle = this.theTitle,
        theTitleFontSize = this.theTitleFontSize,
        theTitleTextColor = this.theTitleTextColor,

        theMessage = this.theMessage,
        theMessageFontSize = this.theMessageFontSize,
        theMessageTextColor = this.theMessageTextColor,
        theConfirmTitle = this.theConfirmTitle,
        theConfirmTitleFontSize = this.theConfirmTitleFontSize,
        theConfirmTitleTextColor = this.theConfirmTitleTextColor,
        theConfirmBackgroundColor = this.theConfirmBackgroundColor,

        theCancelTitle = this.theCancelTitle,
        theCancelTitleFontSize = this.theCancelTitleFontSize,
        theCancelTitleTextColor = this.theCancelTitleTextColor,
        theCancelBackgroundColor = this.theCancelBackgroundColor,

        onDismiss = this.onDismiss,
        onConfirm = this.onConfirm,
    )


interface BuiltInWrapperDialogStateDelegate<T>: BuiltInDialogStateDelegate {
    val theWrappedObject: T?
}


data class BuiltInWrapperDialogStateImpl<T>(
    override val theWrappedObject: T? = null,

    override val theDialogType: DialogType = DialogType.None,
    override val theDialogState: Boolean = true,

    override val theTitle: String = "Title",
    override val theTitleFontSize: TextUnit = 26.sp,
    override val theTitleTextColor: Color = Blue002,

    override val theMessage: String = "Message",
    override val theMessageFontSize: TextUnit = 26.sp,
    override val theMessageTextColor: Color = Blue002,

    override val theConfirmTitle: String = "Confirm",
    override val theConfirmTitleFontSize: TextUnit = 26.sp,
    override val theConfirmTitleTextColor: Color = Blue002,
    override val theConfirmBackgroundColor: Color = Color.Unspecified,

    override val theCancelTitle: String = "Cancel",
    override val theCancelTitleFontSize: TextUnit = 26.sp,
    override val theCancelTitleTextColor: Color = Grey005,
    override val theCancelBackgroundColor: Color = Color.Unspecified,

    override val onDismiss: () -> Unit = {},
    override val onConfirm: () -> Unit = {},

    ) : BuiltInWrapperDialogStateDelegate<T>


@Composable
fun BuiltInCustomDialog01(
    onDismissRequest: () -> Unit,
    dialogContent: @Composable () -> Unit,
    dialogBackgroundColor: Color = Color.White,
    dialogShape: Shape = ShapeDefaults.Medium,
    isFillMaxSize: Boolean = true,
    horizontalDialogPadding: Dp = 40.dp,
    verticalDialogPadding: Dp = 80.dp,
) {
    Logger.getLogger("BuiltInCustomDialog01").log(Level.INFO, "BuiltInCustomDialog01")

    Box(
        modifier = Modifier
            .fillMaxSize()
            //.padding(vertical = 32.dp)
            .background(Color.Black.copy(alpha = 0.5f))
            .clickable(
                onClick = onDismissRequest,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = if (isFillMaxSize) {
                Modifier.fillMaxSize()
            } else {
                Modifier.wrapContentSize()
            }
                .padding(horizontal = horizontalDialogPadding, vertical = verticalDialogPadding)
                .background(color = dialogBackgroundColor, shape = dialogShape)
                .graphicsLayer {
                    shape = dialogShape
                    clip = true
                }

        ) {
            dialogContent()
        }
    }
}


@Preview
@Composable
fun BuiltInProgressDialog02(
    dialogState: BuiltInDialogStateDelegate = BuiltInDialogStateImpl(
        theDialogType = DialogType.Progress,
        theDialogState = true,
        theMessage = "Loading"
    ),
    onDismiss: () -> Unit = dialogState.onDismiss,
    progressColor: Color = Blue003,         // Pink001
    progressTrackColor: Color = Blue008,    // Yellow001
    dismissOnBackPress: Boolean = true,
    dismissOnClickOutside: Boolean = true,
) {
    Logger.getLogger("BuiltInProgressDialog01").log(Level.INFO, "BuiltInProgressDialog01 - theDialogType: [${dialogState.theDialogType}], theDialogState: [${dialogState.theDialogState}]")
    if (dialogState.theDialogType != DialogType.Progress) { return }
    if (!dialogState.theDialogState) { return }

    BuiltInCustomDialog01(
        onDismissRequest = onDismiss,
        dialogContent = {
            BuiltInProgressContent01(
                dialogState = dialogState,
                progressColor = progressColor,
                progressTrackColor = progressTrackColor,
            )
        },
        isFillMaxSize = false
    )
}


@Preview
@Composable
fun BuiltInProgressDialog01(
    dialogState: BuiltInDialogStateDelegate = BuiltInDialogStateImpl(
        theDialogType = DialogType.Progress,
        theDialogState = true,
        theMessage = "Loading"
    ),
    onDismiss: () -> Unit = dialogState.onDismiss,
    progressColor: Color = Blue003,         // Pink001
    progressTrackColor: Color = Blue008,    // Yellow001
    dismissOnBackPress: Boolean = true,
    dismissOnClickOutside: Boolean = true,
) {
    Logger.getLogger("BuiltInProgressDialog01").log(Level.INFO, "BuiltInProgressDialog01 - theDialogType: [${dialogState.theDialogType}], theDialogState: [${dialogState.theDialogState}]")
    if (dialogState.theDialogType != DialogType.Progress) { return }
    if (!dialogState.theDialogState) { return }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = dismissOnBackPress,
            dismissOnClickOutside = dismissOnClickOutside),
    ) {
        Card(
            modifier = Modifier
                //.padding(all = 16.dp) // when using wrapContentXXXX(), padding() has no effect!!
                .wrapContentSize(unbounded = true),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors().copy(
                containerColor = Color.White,
            ),
        ) {
            BuiltInProgressContent01(
                dialogState = dialogState,
                progressColor = progressColor,
                progressTrackColor = progressTrackColor,
            )
        }
    }
}


@Preview
@Composable
fun BuiltInTitleOnlyContent01(
    dialogState: BuiltInDialogStateDelegate = BuiltInDialogStateImpl(),
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        Text(
            modifier = Modifier.padding(horizontal = 12.dp),
            text = dialogState.theTitle,
            fontSize = dialogState.theTitleFontSize,
            color = dialogState.theTitleTextColor,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.padding(vertical = 8.dp))
    }
}


@Preview
@Composable
fun BuiltInProgressContent01(
    dialogState: BuiltInDialogStateDelegate = BuiltInDialogStateImpl(),
    progressColor: Color = Blue003,         // Pink001
    progressTrackColor: Color = Blue008,    // Yellow001
) {
    Column(
        modifier = Modifier.padding(all = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        CircularProgressIndicator(
            modifier = Modifier.size(32.dp),
            color = progressColor,
            trackColor = progressTrackColor,
        )
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        Text(
            text = dialogState.theMessage,
            fontSize = dialogState.theMessageFontSize,
            color = dialogState.theMessageTextColor,
            //modifier = Modifier.padding(16.dp),
        )
    }
}

@Preview
@Composable
fun BuiltInMessageOnlyContent01(
    dialogState: BuiltInDialogStateDelegate = BuiltInDialogStateImpl(),
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        Text(
            modifier = Modifier.padding(horizontal = 12.dp),
            text = dialogState.theMessage,
            fontSize = dialogState.theMessageFontSize,
            color = dialogState.theMessageTextColor,
        )

        Spacer(modifier = Modifier.padding(vertical = 8.dp))
    }
}


@Preview
@Composable
fun BuiltInTitleMessageContent01(
    dialogState: BuiltInDialogStateDelegate = BuiltInDialogStateImpl(),
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        Text(
            modifier = Modifier.padding(horizontal = 12.dp),
            text = dialogState.theTitle,
            fontSize = dialogState.theTitleFontSize,
            color = dialogState.theTitleTextColor,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        Text(
            modifier = Modifier.padding(horizontal = 12.dp),
            text = dialogState.theMessage,
            fontSize = dialogState.theMessageFontSize,
            color = dialogState.theMessageTextColor,
        )

        Spacer(modifier = Modifier.padding(vertical = 8.dp))
    }
}


@Preview
@Composable
fun BuiltInSingleActionBottom01(
    dialogState: BuiltInDialogStateDelegate = BuiltInDialogStateImpl(),
    onDismiss: () -> Unit = dialogState.onDismiss,
    onConfirm: () -> Unit = dialogState.onConfirm,
) {
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
        color = dialogState.theConfirmBackgroundColor,
        //contentColor = Color.White,
        //border = BorderStroke(2.dp, Blue007),
    ) {
        Text(
            text = dialogState.theConfirmTitle,
            textAlign = TextAlign.Center,
            color = dialogState.theConfirmTitleTextColor,
            fontSize = dialogState.theConfirmTitleFontSize,
            modifier = Modifier.padding(all = 16.dp),
        )
    }
}


@Preview
@Composable
fun BuiltInTwinActionsBottom01(
    dialogState: BuiltInDialogStateDelegate = BuiltInDialogStateImpl(),
    onDismiss: () -> Unit = dialogState.onDismiss,
    onConfirm: () -> Unit = dialogState.onConfirm,
) {
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
            color = dialogState.theCancelBackgroundColor,
            //contentColor = Color.White,
            //border = BorderStroke(2.dp, Blue007),
        ) {
            Text(
                text = dialogState.theCancelTitle,
                textAlign = TextAlign.Center,
                color = dialogState.theCancelTitleTextColor,
                fontSize = dialogState.theCancelTitleFontSize,
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
            color = dialogState.theConfirmBackgroundColor,
            //contentColor = Color.White,
            //border = BorderStroke(2.dp, Blue007),
        ) {
            Text(
                text = dialogState.theConfirmTitle,
                textAlign = TextAlign.Center,
                color = dialogState.theConfirmTitleTextColor,
                fontSize = dialogState.theConfirmTitleFontSize,
                modifier = Modifier.padding(all = 16.dp),
            )
        }
    }
}


@Preview
@Composable
fun BuiltInSingleActionDialog02(
    dialogState: BuiltInDialogStateDelegate = BuiltInDialogStateImpl(
        theDialogType = DialogType.SingleAction,
        theDialogState = true,
    ),
    onDismiss: () -> Unit = dialogState.onDismiss,
    onConfirm: () -> Unit = dialogState.onConfirm,
    dismissOnBackPress: Boolean = true,
    dismissOnClickOutside: Boolean = true,
) {
    Logger.getLogger("BuiltInSingleActionDialog02").log(Level.INFO, "BuiltInSingleActionDialog02 - theDialogType: [${dialogState.theDialogType}], theDialogState: [${dialogState.theDialogState}]")
    if (dialogState.theDialogType != DialogType.SingleAction) { return }
    if (!dialogState.theDialogState) { return }

    BuiltInCustomDialog01(
        onDismissRequest = onDismiss,
        dialogContent = {
            Column(
                modifier = Modifier.wrapContentSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                BuiltInTitleMessageContent01(dialogState = dialogState,)

                BuiltInSingleActionBottom01(
                    dialogState = dialogState, onDismiss = onDismiss, onConfirm = onConfirm,
                )
            }
        },
        isFillMaxSize = false
    )
}


@Preview
@Composable
fun BuiltInSingleActionDialog01(
    dialogState: BuiltInDialogStateDelegate = BuiltInDialogStateImpl(
        theDialogType = DialogType.SingleAction,
        theDialogState = true,
    ),
    onDismiss: () -> Unit = dialogState.onDismiss,
    onConfirm: () -> Unit = dialogState.onConfirm,
    dismissOnBackPress: Boolean = true,
    dismissOnClickOutside: Boolean = true,
) {
    Logger.getLogger("BuiltInSingleActionDialog01").log(Level.INFO, "BuiltInSingleActionDialog01 - theDialogType: [${dialogState.theDialogType}], theDialogState: [${dialogState.theDialogState}]")
    if (dialogState.theDialogType != DialogType.SingleAction) { return }
    if (!dialogState.theDialogState) { return }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = dismissOnBackPress,
            dismissOnClickOutside = dismissOnClickOutside),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                //.padding(all = 16.dp),  // when using wrapContentXXXX(), padding() has no effect!!
                .wrapContentHeight(unbounded = true),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors().copy(
                containerColor = Color.White,
            ),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                BuiltInTitleMessageContent01(dialogState = dialogState,)

                BuiltInSingleActionBottom01(
                    dialogState = dialogState, onDismiss = onDismiss, onConfirm = onConfirm,
                )
            }
        }
    }
}


@Preview
@Composable
fun BuiltInTwinActionsDialog02(
    dialogState: BuiltInDialogStateDelegate = BuiltInDialogStateImpl(
        theDialogType = DialogType.TwinActions,
        theDialogState = true,
    ),
    onDismiss: () -> Unit = dialogState.onDismiss,
    onConfirm: () -> Unit = dialogState.onConfirm,
    dismissOnBackPress: Boolean = true,
    dismissOnClickOutside: Boolean = true,
) {
    Logger.getLogger("BuiltInTwinActionsDialog02").log(Level.INFO, "BuiltInTwinActionsDialog02 - theDialogType: [${dialogState.theDialogType}], theDialogState: [${dialogState.theDialogState}]")

    if (dialogState.theDialogType != DialogType.TwinActions) { return }
    if (!dialogState.theDialogState) { return }

    BuiltInCustomDialog01(
        onDismissRequest = onDismiss,
        dialogContent = {
            Column(
                modifier = Modifier.wrapContentSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                BuiltInTitleMessageContent01(dialogState = dialogState,)

                BuiltInTwinActionsBottom01(
                    dialogState = dialogState, onDismiss = onDismiss, onConfirm = onConfirm,
                )
            }
        },
        isFillMaxSize = false
    )
}


@Preview
@Composable
fun BuiltInTwinActionsDialog01(
    dialogState: BuiltInDialogStateDelegate = BuiltInDialogStateImpl(
        theDialogType = DialogType.TwinActions,
        theDialogState = true,
    ),
    onDismiss: () -> Unit = dialogState.onDismiss,
    onConfirm: () -> Unit = dialogState.onConfirm,
    dismissOnBackPress: Boolean = true,
    dismissOnClickOutside: Boolean = true,
) {
    Logger.getLogger("BuiltInTwinActionsDialog01").log(Level.INFO, "BuiltInTwinActionsDialog01 - theDialogType: [${dialogState.theDialogType}], theDialogState: [${dialogState.theDialogState}]")

    if (dialogState.theDialogType != DialogType.TwinActions) { return }
    if (!dialogState.theDialogState) { return }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = dismissOnBackPress,
            dismissOnClickOutside = dismissOnClickOutside),
    ) {
        Card(
            modifier = Modifier
                //.padding(all = 16.dp),  // when using wrapContentXXXX(), padding() has no effect!!
                .wrapContentHeight(unbounded = true),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors().copy(
                containerColor = Color.White,
            ),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                BuiltInTitleMessageContent01(dialogState = dialogState,)

                BuiltInTwinActionsBottom01(
                    dialogState = dialogState, onDismiss = onDismiss, onConfirm = onConfirm,
                )
            }
        }
    }
}



@Preview
@Composable
fun BuiltInDialogSet01(
    dialogState: BuiltInDialogStateDelegate = BuiltInDialogStateImpl(
        theDialogType = DialogType.Progress,
        theDialogState = true,
        theConfirmTitle = "確定",
        theCancelTitle = "取消",
    ),
    //theParameters: RfidTagParameters = RfidTagParameters.None,
    onDismiss: () -> Unit = dialogState.onDismiss,
    onConfirm: () -> Unit = dialogState.onConfirm,
    dismissOnBackPress: Boolean = false,
    dismissOnClickOutside: Boolean = false,
) {
    Logger.getLogger("BuiltInDialogSet01").log(Level.INFO, "BuiltInDialogSet01 [-1]")
    if (!dialogState.theDialogState) { return }

    when(dialogState.theDialogType) {
        DialogType.Progress -> {
            Logger.getLogger("BuiltInDialogSet01").log(Level.INFO, "BuiltInDialogSet01 [0] - [Progress]")
            BuiltInProgressDialog01(dialogState = dialogState, onDismiss = onDismiss, )
        }
        DialogType.SingleAction -> {
            Logger.getLogger("BuiltInDialogSet01").log(Level.INFO, "BuiltInDialogSet01 [0] - [SingleAction]")
            BuiltInSingleActionDialog01(dialogState = dialogState, onDismiss = onDismiss, onConfirm = onConfirm,)
        }
        DialogType.TwinActions -> {
            Logger.getLogger("BuiltInDialogSet01").log(Level.INFO, "BuiltInDialogSet01 [0] - [TwinActions]")
            BuiltInTwinActionsDialog01(dialogState = dialogState, onDismiss = onDismiss, onConfirm = onConfirm,)
        }
        else -> {
            Logger.getLogger("BuiltInDialogSet01").log(Level.INFO, "BuiltInDialogSet01 [0] - [Else]")
        }
    }
}


@Composable
fun BuiltInDialogSet02(
    progressDialogState: BuiltInDialogStateDelegate = BuiltInDialogStateImpl(
        theDialogType = DialogType.Progress,
        theMessage = "Loading"
    ),
    singleActionDialogState: BuiltInDialogStateDelegate = BuiltInDialogStateImpl(
        theDialogType = DialogType.SingleAction,
    ),
    twinActionsDialogState: BuiltInDialogStateDelegate = BuiltInDialogStateImpl(
        theDialogType = DialogType.TwinActions,
    ),
) {
    BuiltInProgressDialog01(progressDialogState,)

    BuiltInSingleActionDialog01(singleActionDialogState,)

    BuiltInTwinActionsDialog01(twinActionsDialogState,)
}



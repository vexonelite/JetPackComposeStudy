package com.gmail.vexonelite.jetpack.study


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.gmail.vexonelite.jetpack.study.ui.theme.Pink001
import com.gmail.vexonelite.jetpack.study.ui.theme.Yellow001


/**
 * [Ref](https://developer.android.com/develop/ui/compose/components/progress)
 * <p>
 * * ``progress``: The current progress that the indicator displays. Pass a Float between 0.0 and 1.0.
 * * ``color``: The color of the actual indicator. That is, the part of the component that reflects progress and which fully encompasses the component when progress is complete.
 * * ``trackColor``: The color of the track over which the indicator is drawn.
 */
@Preview
@Composable
fun ProgressDemo01() {
    //var loading by remember { mutableStateOf(false) }
    val loading = remember { mutableStateOf(false) }
    //val showDialog = remember { mutableStateOf(false) }

    Button(
        onClick = {
            //loading = true
            loading.value = true
        },
        //enabled = !loading
        enabled = !loading.value
    ) {
        Text("Start loading")
    }

    //if (!loading) { return }
    if (!loading.value) { return }

    CircularProgressIndicator(
        modifier = Modifier.width(64.dp),
        color = Pink001,
        trackColor = Yellow001,
    )
}



@Composable
fun ProgressDialog(
    onDismissRequest: () -> Unit,
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
                    text = "Loading....",
                    //modifier = Modifier.padding(16.dp),
                )
            }
        }
    }
}


@Preview
@Composable
fun ProgressDemo02() {
    val showDialog = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp),
        ) {
            Button(
                onClick = {
                    showDialog.value = true
                },
                modifier = Modifier.weight(1 / 2f),
            ) {
                Text("Show Progress")
            }

            Spacer(modifier = Modifier.padding(horizontal = 8.dp))

            Button(
                onClick = {
                    showDialog.value = false
                },
                modifier = Modifier.weight(1 / 2f),
            ) {
                Text("Dismiss Progress")
            }
        }
    }

    ProgressDialog(
        onDismissRequest = {
            showDialog.value = false
            println("Dismiss")
        },
        showDialog
    )


}
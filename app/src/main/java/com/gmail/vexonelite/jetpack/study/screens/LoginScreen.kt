package com.gmail.vexonelite.jetpack.study.screens


import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gmail.vexonelite.jetpack.study.RouteDestination
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue003
import com.gmail.vexonelite.jetpack.study.ui.theme.Green001
import com.gmail.vexonelite.jetpack.study.ui.theme.Grey80
import com.gmail.vexonelite.jetpack.study.ui.theme.HoloBlueDark
import com.gmail.vexonelite.jetpack.study.ui.theme.Pink001
import com.gmail.vexonelite.jetpack.study.ui.theme.navigateToExt
import com.gmail.vexonelite.jetpack.study.viewmodels.ImmutableObjectList
import com.gmail.vexonelite.jetpack.study.viewmodels.ListViewModel

import java.util.logging.Level
import java.util.logging.Logger


@Composable
fun LoginScreen01(viewModel: ListViewModel = viewModel()) {
    val context = LocalContext.current

    val initText: String = ""
    var accountTextState: TextFieldValue by remember {
        mutableStateOf(
            TextFieldValue(text = initText, selection = TextRange(initText.length))
        )
    }
    var pwdTextState: TextFieldValue by remember {
        mutableStateOf(
            TextFieldValue(text = initText, selection = TextRange(initText.length))
        )
    }

    val accountFocusRequester: FocusRequester = remember { FocusRequester() }
    val pwdFocusRequester: FocusRequester = remember { FocusRequester() }

    val textFieldColors = TextFieldDefaults.colors().copy(
        focusedTextColor = Color.Black,
        unfocusedTextColor = Color.White,
        focusedContainerColor = Green001,
        unfocusedContainerColor = Pink001,
    )

    Column(
        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Title",
            fontSize = 26.sp,
            color = Blue003,
            //textAlign = TextAlign.Center,
            //maxLines = 1,
            //modifier = Modifier.fillMaxWidth().background(Grey80).padding(vertical = 12.dp)
        )

        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        BasicTextField(
            value = accountTextState,
            onValueChange = { accountTextState = it },
            // https://proandroiddev.com/decorating-text-field-in-jetpack-compose-b033ade8ad6
            modifier = Modifier
                .focusRequester(accountFocusRequester)
                .fillMaxWidth()
                .wrapContentHeight() // work only for BasicTextField
                .padding(vertical = 6.dp, horizontal = 0.dp)
                //.border(width = 1.5.dp, color = Color.Black, RoundedCornerShape(12.dp)),
                //.border(BorderStroke(1.dp, Color.Transparent), shape = MaterialTheme.shapes.small)
                .drawBehind {
                    val strokeWidth = 1.dp.toPx()
                    val y = size.height - strokeWidth / 2
                    drawLine(
                        Color.Gray,
                        start = Offset(0f, y),
                        end = Offset(size.width, y),
                        strokeWidth
                    )
                },
            //textStyle = MaterialTheme.typography.titleMedium,
            //colors = theAppTextFieldColor01(),
        )

        Spacer(modifier = Modifier.padding(vertical = 10.dp))

//        value: TextFieldValue,
//        onValueChange: (TextFieldValue) -> Unit,
//        modifier: Modifier = Modifier,
//        enabled: Boolean = true,
//        readOnly: Boolean = false,
//        textStyle: TextStyle = TextStyle.Default,
//        keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
//        keyboardActions: KeyboardActions = KeyboardActions.Default,
//        singleLine: Boolean = false,
//        maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
//        minLines: Int = 1,
//        visualTransformation: VisualTransformation = VisualTransformation.None,
//        onTextLayout: (TextLayoutResult) -> Unit = {},
//        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
//        cursorBrush: Brush = SolidColor(Color.Black),
//        decorationBox: @Composable (innerTextField: @Composable () -> Unit) -> Unit =

        TextField(
            value = pwdTextState,
            onValueChange = { pwdTextState = it },
            modifier = Modifier.focusRequester(pwdFocusRequester),
            colors = theAppTextFieldColor01(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        )

        Spacer(modifier = Modifier.padding(vertical = 20.dp))

        Button(
            onClick = {
                Toast.makeText(context, "Login", Toast.LENGTH_LONG).show()
            },
//            modifier = Modifier
//                .background(Color.Transparent)
//                .padding(16.dp), // padding
            enabled = true,
            shape = ButtonDefaults.shape, // | elevatedShape | outlinedShape | textShape
            colors = theAppButtonColor01(),
        ) {
            Text(
                text = "登入",
                fontSize = 20.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 0.dp, horizontal = 20.dp)
            )
        }
    }


}
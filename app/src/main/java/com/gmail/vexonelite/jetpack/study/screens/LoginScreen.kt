package com.gmail.vexonelite.jetpack.study.screens


import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gmail.vexonelite.jetpack.study.SearchBar
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue003
import com.gmail.vexonelite.jetpack.study.ui.theme.Green001
import com.gmail.vexonelite.jetpack.study.ui.theme.Pink001
import com.gmail.vexonelite.jetpack.study.viewmodels.FmTextField01
import com.gmail.vexonelite.jetpack.study.viewmodels.FmTextField02


@Composable
fun LoginScreen01(
    onLoginButtonClick: () -> Unit,
) {
    val accountFocusRequester: FocusRequester = remember { FocusRequester() }
    val pwdFocusRequester: FocusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Spacer(modifier = Modifier.padding(vertical = 30.dp))

        Text(
            text = "Title",
            fontSize = 26.sp,
            color = Blue003,
            //textAlign = TextAlign.Center,
            //maxLines = 1,
            //modifier = Modifier.fillMaxWidth().background(Grey80).padding(vertical = 12.dp)
        )

        Spacer(modifier = Modifier.padding(vertical = 10.dp))



        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        FmTextField01(
            modifier = Modifier.padding(horizontal = 20.dp),
            backgroundColor = Color.Transparent,
            hint = "account",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { pwdFocusRequester.requestFocus() }),
            focusRequester = accountFocusRequester,
        )

        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        FmTextField01(
            modifier = Modifier.padding(horizontal = 20.dp),
            backgroundColor = Color.Transparent,
            hint = "pwd",
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done),
            focusRequester = pwdFocusRequester,
        )

        Spacer(modifier = Modifier.padding(vertical = 30.dp))

        Button(
            onClick = onLoginButtonClick,
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


@Composable
fun TestLoginScreen01() {
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
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Spacer(modifier = Modifier.padding(vertical = 30.dp))

        Text(
            text = "Title",
            fontSize = 26.sp,
            color = Blue003,
            //textAlign = TextAlign.Center,
            //maxLines = 1,
            //modifier = Modifier.fillMaxWidth().background(Grey80).padding(vertical = 12.dp)
        )

        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        FmTextField01(
            modifier = Modifier.padding(horizontal = 20.dp),
            backgroundColor = Color.Transparent,
        )

        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        FmTextField02()

        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        TextField(
            value = pwdTextState,
            onValueChange = { pwdTextState = it },
            modifier = Modifier
                .focusRequester(pwdFocusRequester)
                .defaultMinSize(
                    minWidth = 20.dp,
                    minHeight = 26.dp
                ),
            colors = theAppTextFieldColor01(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        )

        Spacer(modifier = Modifier.padding(vertical = 20.dp))

        SearchBar(hint = "WTF")

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

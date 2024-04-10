package com.gmail.vexonelite.jetpack.study


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gmail.vexonelite.jetpack.study.ui.theme.Green001
import com.gmail.vexonelite.jetpack.study.ui.theme.Pink001
import com.gmail.vexonelite.jetpack.study.viewmodels.ListViewModel
import com.gmail.vexonelite.jetpack.study.viewmodels.SignUpViewModel


/**
 * [Ref](https://developer.android.com/develop/ui/compose/text/user-input)
 */
@Composable
fun SimpleFilledTextFieldSample() {
    var text by remember { mutableStateOf("Hello") }

    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Label") }
    )
}


@Composable
fun SimpleOutlinedTextFieldSample() {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Label") },
        //modifier = Modifier.padding(PaddingValues.Absolute(0.dp, 0.dp, 0.dp, 0.dp)) no effect
    )
}


@Composable
fun StyledTextField() {
    var value by remember { mutableStateOf("Hello\nWorld\nInvisible") }

    TextField(
        value = value,
        onValueChange = { value = it },
        label = { Text("Enter text") },
        maxLines = 2,
        textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
        modifier = Modifier
            .padding(20.dp)
            //.padding(PaddingValues.Absolute())
    )
}


@Composable
fun PasswordTextField() {
    var password by rememberSaveable { mutableStateOf("") }

    TextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Enter password") },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}


@Composable
fun NoLeadingZeroes() {
    var input by rememberSaveable { mutableStateOf("") }

    TextField(
        value = input,
        onValueChange = { newText ->
            input = newText.trimStart { it == '0' }
        }
    )
}


@Composable
fun SignUpScreen(viewModel: SignUpViewModel = viewModel()) {
    OutlinedTextField(
        value = viewModel.username,
        label = { Text("User name") },
        onValueChange = { username -> viewModel.updateUsername(username) },
    )
}


@Composable
fun TextFieldDemo01() {
    Column(
        modifier = Modifier.fillMaxSize(),
        //horizontalAlignment = Alignment.CenterHorizontally

    ) {
        SimpleFilledTextFieldSample()
        //Spacer(modifier = Modifier.padding(vertical = 8.dp))
        SimpleOutlinedTextFieldSample()
        //Spacer(modifier = Modifier.padding(vertical = 8.dp))
        PasswordTextField()
        //Spacer(modifier = Modifier.padding(vertical = 8.dp))
        NoLeadingZeroes()
        //Spacer(modifier = Modifier.padding(vertical = 8.dp))
        SignUpScreen()
    }
}


/**
 * [Ref](https://foso.github.io/Jetpack-Compose-Playground/foundation/basictextfield/)
 */
@Composable
fun BasicTextFieldDemo01() {
    val initText = "Hello World"
    var textState by remember {
        mutableStateOf(
            //TextFieldValue("Hello World")
            TextFieldValue(text = initText, selection = TextRange(initText.length))
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        BasicTextField(
            value = textState,
            onValueChange = { textState = it }
        )
        Text("The textfield has this text: " + textState.text)
    }
}


/**
 * Refs:
 * * [1](https://developer.android.com/develop/ui/compose/touch-input/focus/change-focus-behavior)
 * * [2]((https://developer.android.com/develop/ui/compose/touch-input/focus/react-to-focus))
 */
@Composable
fun FocusDemo01() {
    val initText = "Hello World"
    var textState by remember {
        mutableStateOf(
            //TextFieldValue("Hello World")
            TextFieldValue(text = initText, selection = TextRange(initText.length))
        )
    }

    val focusRequester1 = remember { FocusRequester() }
    val focusRequester2 = remember { FocusRequester() }
    val textFieldColors = TextFieldDefaults.colors().copy(
        focusedTextColor = Color.Black,
        unfocusedTextColor = Color.White,
        focusedContainerColor = Green001,
        unfocusedContainerColor = Pink001,
    )

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        TextField(
            value = textState,
            onValueChange = { textState = it },
            modifier = Modifier.focusRequester(focusRequester1),
            colors = textFieldColors
        )
        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        Button(
            onClick = { focusRequester1.requestFocus() }
        ) {
            Text("Request focus on TextField1")
        }

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        TextField(
            value = textState,
            onValueChange = { textState = it },
            modifier = Modifier.focusRequester(focusRequester2),
            colors = textFieldColors
        )

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        Button(
            onClick = { focusRequester2.requestFocus() }
        ) {
            Text("Request focus on TextField2")
        }
    }
}



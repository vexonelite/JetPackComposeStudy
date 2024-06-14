package com.gmail.vexonelite.jetpack.study


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue012
import com.gmail.vexonelite.jetpack.study.ui.theme.Green001
import com.gmail.vexonelite.jetpack.study.ui.theme.Pink001
import com.gmail.vexonelite.jetpack.study.ui.theme.Pink002
import com.gmail.vexonelite.jetpack.study.ui.theme.Purple002
import com.gmail.vexonelite.jetpack.study.ui.theme.Teal700
import com.gmail.vexonelite.jetpack.study.ui.theme.Yellow002
import com.gmail.vexonelite.jetpack.study.viewmodels.SignUpViewModel
import java.util.logging.Level
import java.util.logging.Logger


/**
 * [Ref](https://developer.android.com/develop/ui/compose/text/user-input)
 */
@Preview
@Composable
fun SimpleFilledTextFieldSample() {
    var text by remember { mutableStateOf("Hello") }

    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Label") }
    )
}


@Preview
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


@Preview
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


@Preview
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


@Preview
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


@Preview
@Composable
fun SignUpScreen(viewModel: SignUpViewModel = viewModel()) {
    OutlinedTextField(
        value = viewModel.username,
        label = { Text("User name") },
        onValueChange = { username -> viewModel.updateUsername(username) },
    )
}


@Preview
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
@Preview
@Composable
fun BasicTextFieldDemo01() {
    val initText = "Hello World"
    var textValue by remember {
        mutableStateOf(
            //TextFieldValue("Hello World")
            TextFieldValue(text = initText, selection = TextRange(initText.length))
        )
    }


    Column(
        modifier = Modifier.fillMaxSize(),

    ) {
        BasicTextField(

            value = textValue,
            onValueChange = { newValue: TextFieldValue -> textValue = newValue },
            modifier = Modifier
                .padding(all = 10.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = Blue012)
                .padding(vertical = 10.dp)
        )
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        Text("The textfield has this text: " + textValue.text)
    }
}


fun TextFieldValue.builtInTextFieldValueChangeHandler01(
    selectAllOnFocus: Boolean,
    focusIndicator: Int
): Pair<TextFieldValue, Int> {
    var focusIndicatorX: Int = focusIndicator

    val returnedValue: TextFieldValue = if (selectAllOnFocus) {
        Logger.getLogger("TextFieldValue Ktx").log(Level.INFO, "builtInTextFieldValueChangeHandler01() - onValueChange() - focusIndicator: [$focusIndicator], newValue： [${this.text}]")
        if (focusIndicatorX == 2) {
            focusIndicatorX--
            TextFieldValue(
                text = this.text, selection = TextRange(0, this.text.length)
            )
        }
        else {
            this
        }
    }
    else {
        this
    }

    return Pair<TextFieldValue, Int>(returnedValue, focusIndicatorX)
}


fun TextFieldValue.builtInTextFieldFocusChangedHandler01(
    selectAllOnFocus: Boolean,
    focusState: FocusState
): Pair<TextFieldValue, Int> {
    val focusIndicator: Int = if (focusState.isFocused) { 2 } else { 0 }
    Logger.getLogger("TextFieldValue Ktx").log(Level.INFO, "builtInTextFieldFocusChangedHandler01 - onFocusChanged() - isFocused： [${focusState.isFocused}], focusIndicator: [$focusIndicator]")

    val returnedValue: TextFieldValue = if (selectAllOnFocus && (focusIndicator == 2)) {
        TextFieldValue(
            text = this.text, selection = TextRange(0, this.text.length)
        )
    }
    else {
        TextFieldValue(
            text = this.text, selection = TextRange(this.text.length)
        )
    }

    return Pair<TextFieldValue, Int>(returnedValue, focusIndicator)
}


/**
 * Refs:
 * * [1](https://developer.android.com/develop/ui/compose/touch-input/focus/change-focus-behavior)
 * * [2]((https://developer.android.com/develop/ui/compose/touch-input/focus/react-to-focus))
 */
@Preview
@Composable
fun FocusDemo01(
    selectAllOnFocus: Boolean = true,
) {

    val textFieldColors = TextFieldDefaults.colors().copy(
        focusedTextColor = Pink002,
        unfocusedTextColor = Purple002,
        focusedContainerColor = Yellow002,
        unfocusedContainerColor = Blue012,
    )

    val initText = "Hello World"
    var textValue1 by remember {
        mutableStateOf(
            //TextFieldValue("Hello World")
            TextFieldValue(text = initText, selection = TextRange(initText.length))
        )
    }
    var textValue2 by remember {
        mutableStateOf(
            TextFieldValue(text = initText, selection = TextRange(initText.length))
        )
    }

    val focusRequester1 = remember { FocusRequester() }
    val focusRequester2 = remember { FocusRequester() }

    var focusIndicator1 by remember { mutableIntStateOf(0) }
    var focusIndicator2 by remember { mutableIntStateOf(0) }



    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        TextField(
            value = textValue1,
            onValueChange = { newValue: TextFieldValue ->
                if (selectAllOnFocus) {

                }
                Logger.getLogger("FocusDemo01").log(Level.INFO, "FocusDemo01 - onValueChange() - focusIndicator1: [$focusIndicator1], newValue： [${textValue1.text}]")
                if (focusIndicator1 == 2) {
                    focusIndicator1--
                    textValue1 = TextFieldValue(
                        text = newValue.text, selection = TextRange(0, newValue.text.length)
                    )
                }
                else {
                    textValue1 = newValue
                }
            },
            modifier = Modifier
                .focusRequester(focusRequester1)
                .onFocusChanged {
                    focusIndicator1 = if (it.isFocused) { 2 } else { 0 }
                    Logger.getLogger("FocusDemo01").log(Level.INFO, "FocusDemo01 - onFocusChanged() - isFocused： [${it.isFocused}], focusIndicator1: [$focusIndicator1]")
                    if (focusIndicator1 == 2) {
                        textValue1 = TextFieldValue(
                            text = textValue1.text, selection = TextRange(0, textValue1.text.length)
                        )
                    }
                    else {
                        textValue1 = TextFieldValue(
                            text = textValue1.text, selection = TextRange(textValue1.text.length)
                        )
                    }
                }
                .padding(all = 10.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = Blue012)
                .padding(vertical = 10.dp),
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
            value = textValue2,
            onValueChange = { newValue: TextFieldValue ->
                val pair: Pair<TextFieldValue, Int> = newValue.builtInTextFieldValueChangeHandler01(selectAllOnFocus, focusIndicator2)
                focusIndicator2 = pair.second
                textValue2 = pair.first
//                Logger.getLogger("FocusDemo01").log(Level.INFO, "FocusDemo01 - onValueChange() - focusIndicator2: [$focusIndicator2], newValue： [${textValue1.text}]")
//                if (focusIndicator2 == 2) {
//                    focusIndicator2--
//                    textValue2 = TextFieldValue(
//                        text = newValue.text, selection = TextRange(0, newValue.text.length)
//                    )
//                }
//                else {
//                    textValue2 = newValue
//                }
            },
            modifier = Modifier
                .focusRequester(focusRequester2)
                .onFocusChanged {
                    val pair: Pair<TextFieldValue, Int> = textValue2.builtInTextFieldFocusChangedHandler01(selectAllOnFocus, it)
                    focusIndicator2 = pair.second
                    textValue2 = pair.first
                    //focusIndicator2 = if (it.isFocused) { 2 } else { 0 }
                    Logger.getLogger("FocusDemo01").log(Level.INFO, "FocusDemo01 - onFocusChanged() - isFocused： [${it.isFocused}], focusIndicator2: [$focusIndicator2]")
//                    if (focusIndicator2 == 2) {
//                        textValue2 = TextFieldValue(
//                            text = textValue2.text, selection = TextRange(0, textValue2.text.length)
//                        )
//                    }
//                    else {
//                        textValue2 = TextFieldValue(
//                            text = textValue2.text, selection = TextRange(textValue2.text.length)
//                        )
//                    }
                }
                .padding(all = 10.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = Blue012)
                .padding(vertical = 10.dp),
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


/**
 * * [Ref](https://rivaldy.medium.com/jetpack-compose-customize-your-searchbar-with-basictextfield-c1cdcbd3e3aa)
 */
@Preview
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "Hint",
    isEnabled: (Boolean) = true,
    height: Dp = 40.dp,
    elevation: Dp = 4.dp,
    cornerShape: Shape = RoundedCornerShape(8.dp),
    backgroundColor: Color = Color.White,
    onSearchClicked: () -> Unit = {},
    onTextChange: (String) -> Unit = {},
) {
    var text by remember { mutableStateOf(TextFieldValue()) }
    Row(
        modifier = Modifier
            .height(height)
            .fillMaxWidth()
            .shadow(elevation = elevation, shape = cornerShape)
            .background(color = backgroundColor, shape = cornerShape)
            .clickable { onSearchClicked() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BasicTextField(
            modifier = modifier
                .weight(5f)
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            value = text,
            onValueChange = {
                text = it
                onTextChange(it.text)
            },
            enabled = isEnabled,
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ),
            decorationBox = { innerTextField ->
                if (text.text.isEmpty()) {
                    Text(
                        text = hint,
                        color = Color.Gray.copy(alpha = 0.5f),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
                innerTextField()
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = { onSearchClicked() }),
            singleLine = true
        )
        Box(
            modifier = modifier
                .weight(1f)
                .size(40.dp)
                .background(color = Color.Transparent, shape = CircleShape)
                .clickable {
                    if (text.text.isNotEmpty()) {
                        text = TextFieldValue(text = "")
                        onTextChange("")
                    }
                },
        ) {
            if (text.text.isNotEmpty()) {
                Icon(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    imageVector = Icons.Rounded.Clear,
                    //contentDescription = stringResource(R.string.search),
                    contentDescription = "search",
                    tint = MaterialTheme.colorScheme.primary,
                )
            } else {
                Icon(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    //painter = painterResource(id = R.drawable.ic_search),
                    imageVector = Icons.Rounded.Search,
                    //contentDescription = stringResource(R.string.search),
                    contentDescription = "search",
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}


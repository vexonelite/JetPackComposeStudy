package com.gmail.vexonelite.jetpack.study.viewmodels


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue001
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue002
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue003
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue009
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue012
import com.gmail.vexonelite.jetpack.study.ui.theme.Grey005
import com.gmail.vexonelite.jetpack.study.ui.theme.Grey85
import com.gmail.vexonelite.jetpack.study.ui.theme.theBuiltInOutlinedTextFieldColor01
import java.util.logging.Level
import java.util.logging.Logger


fun DrawScope.drawUnderlineExt(underlineWidth: Dp = 1.dp, underlineColor: Color = Grey85, ) {
    val strokeWidth = underlineWidth.toPx()
    val y = size.height - (strokeWidth / 2f)
    drawLine(
        underlineColor,
        start = Offset(0f, y),
        end = Offset(size.width, y),
        strokeWidth
    )
}


fun TextFieldValue.builtInTextFieldValueChangeHandler01(
    selectAllOnFocus: Boolean,
    focusIndicator: Int
): Pair<TextFieldValue, Int> {
    var focusIndicatorX: Int = focusIndicator
    Logger.getLogger("TextFieldValue Ktx").log(Level.INFO, "builtInTextFieldValueChangeHandler01() - onValueChange() - focusIndicator: [$focusIndicator], newValue： [${this.text}]")
    val returnedValue: TextFieldValue = if (selectAllOnFocus && (focusIndicatorX == 2)) {
        focusIndicatorX--
        TextFieldValue(
            text = this.text, selection = TextRange(0, this.text.length)
        )
    }
    else {
        TextFieldValue(
            text = this.text, selection = TextRange(this.text.length)
        )
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


@Preview
@Composable
fun BuiltInTextField01(
    modifier: Modifier = Modifier,
    textValue: TextFieldValue = TextFieldValue(),
    onValueChange: (TextFieldValue) -> Unit = {},
    hint: String = "Hint",
    paddingHorizontal: Dp = 12.dp,
    paddingVertical: Dp = 12.dp,
    textColor: Color = Blue002,
    hintColor: Color = Grey85,
    fontSize: TextUnit = 20.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    underlineWidth: Dp = 2.dp,
    underlineColor: Color = Blue,
    backgroundColor: Color = Color.Transparent,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true,
    enabled: Boolean = true,
    selectAllOnFocus: Boolean = false,
    onFocusChanged: (FocusState) -> Unit = {},
    focusRequester: FocusRequester? = null
) {
//    var textState: TextFieldValue by remember {
//        mutableStateOf(
//            TextFieldValue(text = initText, selection = TextRange(initText.length))
//        )
//    }
//    val textState: MutableState<TextFieldValue> = remember {
//        mutableStateOf(
//            TextFieldValue(text = initText, selection = TextRange(initText.length))
//        )
//    }

    Logger.getLogger("BuiltInTextField01").log(Level.INFO, "BuiltInTextField01 - textValue: [${textValue.text}]")

    val textStyle = TextStyle(
        color = textColor,
        fontWeight = fontWeight,
        fontSize = fontSize,
    )

    Row(
        modifier = modifier
            .background(backgroundColor)
            .fillMaxWidth()
            .wrapContentHeight()
            .drawBehind {
                this.drawUnderlineExt(underlineWidth, underlineColor)
//                val strokeWidth = underlineWidth.toPx()
//                val y = size.height - (strokeWidth / 2f)
//                drawLine(
//                    underlineColor,
//                    start = Offset(0f, y),
//                    end = Offset(size.width, y),
//                    strokeWidth
//                )
            }
            .padding(horizontal = paddingHorizontal, vertical = paddingVertical),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val textFieldModifier: Modifier = if (null != focusRequester) {
            Modifier.focusRequester(focusRequester)
        } else { Modifier }
            .then(
                if(selectAllOnFocus) {
                    Modifier.onFocusChanged(onFocusChanged)
                }
                else { Modifier }
            )
            .fillMaxWidth()
            .wrapContentHeight()

        BasicTextField(
            value = textValue,
            onValueChange = onValueChange,
            // https://proandroiddev.com/decorating-text-field-in-jetpack-compose-b033ade8ad6
            modifier = textFieldModifier,
            // .padding(horizontal = 8.dp, vertical = 16.dp) // margin
            //.fillMaxWidth()
            //.wrapContentHeight(), // work only for BasicTextField
            //.border(width = 1.5.dp, color = Color.Black, RoundedCornerShape(12.dp)),
            //.border(BorderStroke(1.dp, Color.Transparent), shape = MaterialTheme.shapes.small),
            textStyle = textStyle,
            //colors = theAppTextFieldColor01(), // colors only works for TextField,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
            singleLine = singleLine,
            enabled = enabled,
            decorationBox = { innerTextField ->
                if (textValue.text.isEmpty()) {
                    Text(
                        text = hint,
                        color = hintColor,
                        fontSize = fontSize,
                        fontWeight = fontWeight,
                    )
                }
                innerTextField()
            },
        )
    }
}


///////////////////////not yet
@Preview
@Composable
fun BuiltInTextField02(
    modifier: Modifier = Modifier,
    textValue: TextFieldValue = TextFieldValue(),
    onValueChange: (TextFieldValue) -> Unit = {},
    hint: String = "Hint",
    height: Dp = 0.dp,
    paddingHorizontal: Dp = 12.dp,
    paddingVertical: Dp = 12.dp,
    textColor: Color = Blue002,
    hintColor: Color = Grey85,
    fontSize: TextUnit = 20.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    elevation: Dp = 3.dp,
    cornerShape: Shape = RoundedCornerShape(4.dp),
    borderEnabled: Boolean = true,
    borderWidth: Dp = 1.dp,
    borderColor: Color = Blue002,
    backgroundColor: Color = Blue012,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    enabled: Boolean = true,
    selectAllOnFocus: Boolean = false,
    onFocusChanged: (FocusState) -> Unit = {},
    focusRequester: FocusRequester? = null
) {

//    val textModifier = if (null != focusRequester) {
//        if (height > 0.dp) {
//            if (borderEnabled) {
//                Modifier
//                    .focusRequester(focusRequester)
//                    .fillMaxWidth()
//                    .height(height)
//                    .border(borderWidth, SolidColor(borderColor), cornerShape)
//                    .background(color = backgroundColor, shape = cornerShape)
//                    .padding(horizontal = paddingHorizontal, vertical = paddingVertical)
//            }
//            else {
//                Modifier
//                    .focusRequester(focusRequester)
//                    .fillMaxWidth()
//                    .height(height)
//                    .shadow(elevation = elevation, shape = cornerShape)
//                    .background(color = backgroundColor, shape = cornerShape)
//                    .padding(horizontal = paddingHorizontal, vertical = paddingVertical)
//            }
//        }
//        else {
//            if (borderEnabled) {
//                Modifier
//                    .focusRequester(focusRequester)
//                    .fillMaxWidth()
//                    .border(borderWidth, SolidColor(borderColor), cornerShape)
//                    .background(color = backgroundColor, shape = cornerShape)
//                    .padding(horizontal = paddingHorizontal, vertical = paddingVertical)
//            }
//            else {
//                Modifier
//                    .focusRequester(focusRequester)
//                    .fillMaxWidth()
//                    .shadow(elevation = elevation, shape = cornerShape)
//                    .background(color = backgroundColor, shape = cornerShape)
//                    .padding(horizontal = paddingHorizontal, vertical = paddingVertical)
//            }
//        }
//    }
//    else {
//        if (height > 0.dp) {
//            if (borderEnabled) {
//                Modifier
//                    .fillMaxWidth()
//                    .height(height)
//                    .border(borderWidth, SolidColor(borderColor), cornerShape)
//                    .background(color = backgroundColor, shape = cornerShape)
//                    .padding(horizontal = paddingHorizontal, vertical = paddingVertical)
//            }
//            else {
//                Modifier
//                    .fillMaxWidth()
//                    .height(height)
//                    .shadow(elevation = elevation, shape = cornerShape)
//                    .background(color = backgroundColor, shape = cornerShape)
//                    .padding(horizontal = paddingHorizontal, vertical = paddingVertical)
//            }
//        }
//        else {
//            if (borderEnabled) {
//                Modifier
//                    .fillMaxWidth()
//                    .border(borderWidth, SolidColor(borderColor), cornerShape)
//                    .background(color = backgroundColor, shape = cornerShape)
//                    .padding(horizontal = paddingHorizontal, vertical = paddingVertical)
//            }
//            else {
//                Modifier
//                    .fillMaxWidth()
//                    .shadow(elevation = elevation, shape = cornerShape)
//                    .background(color = backgroundColor, shape = cornerShape)
//                    .padding(horizontal = paddingHorizontal, vertical = paddingVertical)
//            }
//        }
//    }

//        else if ((height > 0.dp) && borderEnabled) {
//
//        }
//        Modifier
//            .focusRequester(focusRequester)
//            .fillMaxWidth()
//            .height(height)
//            .border(borderWidth, SolidColor(borderColor), cornerShape)
//            .background(color = backgroundColor, shape = cornerShape)
//            .padding(horizontal = paddingHorizontal, vertical = paddingVertical)
//
//    } ((null != focusRequester) && (height > 0.dp) &&  borderEnabled) {
//        Logger.getLogger("BuiltInTextField02").log(Level.INFO, "BuiltInTextField02 - textModifier [3]")
//
//    }
//    else if ((height > 0.dp) && borderEnabled) {
//        Logger.getLogger("BuiltInTextField02").log(Level.INFO, "BuiltInTextField02 - textModifier [2]")
//        Modifier
//            .fillMaxWidth()
//            .height(height)
//            .border(borderWidth, SolidColor(borderColor), cornerShape)
//            .background(color = backgroundColor, shape = cornerShape)
//            .padding(horizontal = paddingHorizontal, vertical = paddingVertical)
//    }
//    else if (borderEnabled) {
//        Logger.getLogger("BuiltInTextField02").log(Level.INFO, "BuiltInTextField02 - textModifier [1]")
//        Modifier
//            .fillMaxWidth()
//            .border(borderWidth, SolidColor(borderColor), cornerShape)
//            .background(color = backgroundColor, shape = cornerShape)
//            .padding(horizontal = paddingHorizontal, vertical = paddingVertical)
//    }
//    else {
//        Logger.getLogger("BuiltInTextField02").log(Level.INFO, "BuiltInTextField02 - textModifier [0]")
//        Modifier
//            .fillMaxWidth()
//            .shadow(elevation = elevation, shape = cornerShape)
//            .background(color = backgroundColor, shape = cornerShape)
//            .padding(horizontal = paddingHorizontal, vertical = paddingVertical)
//    }

    val textModifier = if (null != focusRequester) {
        Modifier.focusRequester(focusRequester)
    }
    else { Modifier }
        .then(
            if(selectAllOnFocus) {
                Modifier.onFocusChanged(onFocusChanged)
            }
            else { Modifier }
        )
        .fillMaxWidth()
        .then(
            if (height > 0.dp) { Modifier.height(height) } else { Modifier }
        )
        .then(
            if (borderEnabled) {
                Modifier.border(borderWidth, SolidColor(borderColor), cornerShape)
            }
            else {
                Modifier.shadow(elevation = elevation, shape = cornerShape)
            }
        )
        .background(color = backgroundColor, shape = cornerShape)
        .padding(horizontal = paddingHorizontal, vertical = paddingVertical)

    BasicTextField(
        modifier = textModifier,
        value = textValue,
        onValueChange = onValueChange,
        textStyle = TextStyle(
            color = textColor,
            fontSize = fontSize,
            fontWeight = fontWeight
        ),
        decorationBox = { innerTextField ->
            if (textValue.text.isEmpty()) {
                Text(
                    text = hint,
                    color = hintColor,
                    fontSize = fontSize,
                    fontWeight = fontWeight,
                )
            }
            innerTextField()
        },
//            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Text,
//                imeAction = ImeAction.Search
//            ),
//            keyboardActions = KeyboardActions(onSearch = { onSearchClicked() }),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = visualTransformation,
        singleLine = singleLine,
        enabled = enabled,
    )
}


///////////////////////////////////not yet
@Preview
@Composable
fun BuiltInTextField03(
    modifier: Modifier = Modifier,
    value: TextFieldValue = TextFieldValue(),
    onValueChange: (TextFieldValue) -> Unit = {},
    hint: String = "Hint",
    //height: Dp = 40.dp,
    paddingHorizontal: Dp = 12.dp,
    paddingVertical: Dp = 12.dp,
    textColor: Color = Blue002,
    hintColor: Color = Grey85,
    fontSize: TextUnit = 20.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    elevation: Dp = 3.dp,
    cornerShape: Shape = RoundedCornerShape(4.dp),
    borderEnabled: Boolean = true,
    borderWidth: Dp = 1.dp,
    borderColor: Color = Blue002,
    backgroundColor: Color = Blue012,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    enabled: Boolean = true,
    selectAllOnFocus: Boolean = false,
    onFocusChanged: (FocusState) -> Unit = {},
    focusRequester: FocusRequester? = null
) {
    val rowModifier = Modifier
        .fillMaxWidth()
        .then(
            if (borderEnabled) {
                Modifier.border(1.dp, SolidColor(borderColor), cornerShape)
            }
            else {
                Modifier.shadow(elevation = elevation, shape = cornerShape)
            }
        )
        .background(color = backgroundColor, shape = cornerShape)

    Row(
        modifier = rowModifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val textModifier = if (null != focusRequester) {
            Modifier.focusRequester(focusRequester)
        }
        else { Modifier }
            .then(
                if(selectAllOnFocus) {
                    Modifier.onFocusChanged(onFocusChanged)
                }
                else { Modifier }
            )
            .fillMaxWidth()
            .padding(horizontal = paddingHorizontal, vertical = paddingVertical)

        BasicTextField(
            modifier = textModifier,
            //.weight(5f)
            //.fillMaxWidth()
            //.padding(horizontal = paddingHorizontal, vertical = paddingVertical),
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(
                color = textColor,
                fontSize = fontSize,
                fontWeight = fontWeight
            ),
            decorationBox = { innerTextField ->
                if (value.text.isEmpty()) {
                    Text(
                        text = hint,
                        color = hintColor,
                        fontSize = fontSize,
                        fontWeight = fontWeight,
                    )
                }
                innerTextField()
            },
//            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Text,
//                imeAction = ImeAction.Search
//            ),
//            keyboardActions = KeyboardActions(onSearch = { onSearchClicked() }),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
            singleLine = singleLine,
            enabled = enabled,
        )
    }
}

///


@Preview
@Composable
fun BuiltInDropDownMenu01(
    value: TextFieldValue = TextFieldValue(),
    onValueChange: (TextFieldValue) -> Unit = {},
    items: List<String> = listOf("Option 1", "Option 2", "Option 3", "Option 4"),
    hint: String = "Hint",
    textColor: Color = Blue002,
    hintColor: Color = Grey85,
    paddingHorizontal: Dp = 12.dp,
    paddingVertical: Dp = 12.dp,
    fontSize: TextUnit = 20.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    dropDownMenuBackgroundColor: Color = Blue012,
    dropDownMenuTextColor: Color = Grey005,
    dropDownMenuTextBackgroundColor: Color = Blue012,
    dropDownMenuTextUnderlineWidth: Dp = 1.dp,
    dropDownMenuTextUnderlineColor: Color = Grey85,
) {
    var expanded by remember { mutableStateOf(false) }

//    val initText: String = if (items.isNotEmpty()) { items[0] } else { "" }
//    var selectedText: String by remember { mutableStateOf(initText) }
    var selectedText: String by remember { mutableStateOf("") }

    val keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current

    Column {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            label = {
                Text(
                    text = hint,
                    color = hintColor,
                    fontSize = fontSize,
                    fontWeight = fontWeight,
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                    Modifier.clickable { expanded = !expanded }
                )
            },
            colors = theBuiltInOutlinedTextFieldColor01(),
            readOnly = true, // key: preventing text input via the keyboard.
            modifier = Modifier
                .fillMaxWidth()
                //.background(color = Color.Cyan)
                .clickable { expanded = !expanded }
                .onFocusChanged {
                    if (it.isFocused) {
                        keyboardController?.hide() // Hides the keyboard when the TextField gains focus
                    }
                },
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(color = dropDownMenuBackgroundColor)
        ) {
            items.forEach { label: String ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = label,
                            textAlign = TextAlign.Start,
                            color = dropDownMenuTextColor,
                            fontSize = fontSize,
                            fontWeight = fontWeight,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = dropDownMenuTextBackgroundColor)
                                .drawBehind {
                                    this.drawUnderlineExt(dropDownMenuTextUnderlineWidth, dropDownMenuTextUnderlineColor)
//                                    val strokeWidth = dropDownMenuTextUnderlineWidth.toPx()
//                                    val y = size.height - (strokeWidth / 2f)
//                                    drawLine(
//                                        dropDownMenuTextUnderlineColor,
//                                        start = Offset(0f, y),
//                                        end = Offset(size.width, y),
//                                        strokeWidth
//                                    )
                                }
                                .padding(horizontal = paddingHorizontal, vertical = paddingVertical)
                        )
                    },
                    onClick = {
                        selectedText = label
                        expanded = false
                    },
                    contentPadding = PaddingValues(horizontal = 0.dp, vertical = 0.dp),
//                    colors = MenuDefaults.itemColors(
//                        textColor = Color.Red,
//                    ),
                )
            }
        }
    }
}


@Preview
@Composable
fun BuiltInDropDownMenu02(
    value: TextFieldValue = TextFieldValue(),
    onValueChange: (TextFieldValue) -> Unit = {},
    items: List<String> = listOf("Option 1", "Option 2", "Option 3", "Option 4"),
    hint: String = "Hint",
    paddingHorizontal: Dp = 12.dp,
    paddingVertical: Dp = 12.dp,
    textColor: Color = Blue002,
    hintColor: Color = Grey85,
    fontSize: TextUnit = 20.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    elevation: Dp = 3.dp,
    cornerShape: Shape = RoundedCornerShape(4.dp),
    borderEnabled: Boolean = true,
    borderWidth: Dp = 1.dp,
    borderColor: Color = Blue002,
    backgroundColor: Color = Blue012,
    dropDownMenuBackgroundColor: Color = Blue012,
    dropDownMenuTextColor: Color = Grey005,
    dropDownMenuTextBackgroundColor: Color = Blue012,
    dropDownMenuTextUnderlineWidth: Dp = 1.dp,
    dropDownMenuTextUnderlineColor: Color = Grey85,
) {
    var expanded: Boolean by remember { mutableStateOf(false) }

//    val initText: String = if (items.isNotEmpty()) { items[0] } else { "" }
//    var selectedText: String by remember { mutableStateOf(initText) }
    var selectedText: String by remember { mutableStateOf("") }

    val keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                //.padding(16.dp)
                .clickable { expanded = !expanded }
                .then(
                    if (borderEnabled) {
                        Modifier.border(1.dp, SolidColor(borderColor), cornerShape)
                    }
                    else {
                        Modifier.shadow(elevation = elevation, shape = cornerShape)
                    }
                )
                .background(color = backgroundColor, shape = cornerShape),
            //.border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
            //.padding(horizontal = 8.dp, vertical = 12.dp)
        ) {

            Box(
                Modifier.fillMaxWidth(),
            ) {
                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = backgroundColor)
                        .padding(horizontal = paddingHorizontal, vertical = paddingVertical)
                        .onFocusChanged {
                            if (it.isFocused) {
                                keyboardController?.hide() // Hides the keyboard when the TextField gains focus
                            }
                        },
                    value = selectedText,
                    onValueChange = { selectedText = it },
                    textStyle = TextStyle(
                        textAlign = TextAlign.Start,
                        color = textColor,
                        fontSize = fontSize,
                        fontWeight = fontWeight
                    ),
                    readOnly = true, // key: preventing text input via the keyboard.
                    decorationBox = { innerTextField ->
                        if (selectedText.isEmpty()) {
                            Text(
                                text = hint,
                                color = hintColor,
                                fontSize = fontSize,
                                fontWeight = fontWeight,
                            )
                        }
                        innerTextField()
                    },
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,

                    ) {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = null,
                        )
                    }
                }
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(color = dropDownMenuBackgroundColor)
        ) {
            items.forEach { label: String ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = label,
                            textAlign = TextAlign.Start,
                            color = dropDownMenuTextColor,
                            fontSize = fontSize,
                            fontWeight = fontWeight,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = dropDownMenuTextBackgroundColor)
                                .drawBehind {
                                    this.drawUnderlineExt(dropDownMenuTextUnderlineWidth, dropDownMenuTextUnderlineColor)
//                                    val strokeWidth = dropDownMenuTextUnderlineWidth.toPx()
//                                    val y = size.height - (strokeWidth / 2f)
//                                    drawLine(
//                                        dropDownMenuTextUnderlineColor,
//                                        start = Offset(0f, y),
//                                        end = Offset(size.width, y),
//                                        strokeWidth
//                                    )
                                }
                                .padding(horizontal = paddingHorizontal, vertical = paddingVertical)
                        )
                    },
                    onClick = {
                        selectedText = label
                        expanded = false
                    },
                    contentPadding = PaddingValues(horizontal = 0.dp, vertical = 0.dp),
//                    colors = MenuDefaults.itemColors(
//                        textColor = Color.Red,
//                    ),
                )
            }
        }
    }
}



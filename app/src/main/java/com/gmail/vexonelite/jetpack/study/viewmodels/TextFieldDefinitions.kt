package com.gmail.vexonelite.jetpack.study.viewmodels


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue001
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue003
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue009
import com.gmail.vexonelite.jetpack.study.ui.theme.Grey85


@Preview
@Composable
fun BuiltInTextField01(
    modifier: Modifier = Modifier,
    value: TextFieldValue = TextFieldValue(),
    onValueChange: (TextFieldValue) -> Unit = {},
    hint: String = "Hint",
    paddingHorizontal: Dp = 12.dp,
    paddingVertical: Dp = 12.dp,
    textColor: Color = Blue003,
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
    focusRequester: FocusRequester? = null
) {
//    var textState: TextFieldValue by remember {
//        mutableStateOf(
//            TextFieldValue(text = initText, selection = TextRange(initText.length))
//        )
//    }

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
                val strokeWidth = underlineWidth.toPx()
                val y = size.height - (strokeWidth / 2f)
                drawLine(
                    underlineColor,
                    start = Offset(0f, y),
                    end = Offset(size.width, y),
                    strokeWidth
                )
            }
            .padding(horizontal = paddingHorizontal, vertical = paddingVertical),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val textFieldModifier: Modifier = if (null != focusRequester) {
            Modifier.focusRequester(focusRequester)
        } else { Modifier }

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            // https://proandroiddev.com/decorating-text-field-in-jetpack-compose-b033ade8ad6
            modifier = textFieldModifier
                // .padding(horizontal = 8.dp, vertical = 16.dp) // margin
                .fillMaxWidth()
                .wrapContentHeight(), // work only for BasicTextField
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
        )
    }
}


@Preview
@Composable
fun BuiltInTextField02(
    modifier: Modifier = Modifier,
    value: TextFieldValue = TextFieldValue(),
    onValueChange: (TextFieldValue) -> Unit = {},
    hint: String = "Hint",
    height: Dp = 0.dp,
    paddingHorizontal: Dp = 12.dp,
    paddingVertical: Dp = 12.dp,
    textColor: Color = Blue003,
    hintColor: Color = Grey85,
    fontSize: TextUnit = 20.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    elevation: Dp = 3.dp,
    cornerShape: Shape = RoundedCornerShape(4.dp),
    borderEnabled: Boolean = true,
    borderWidth: Dp = 1.dp,
    borderColor: Color = Blue001,
    backgroundColor: Color = Blue009,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    enabled: Boolean = true,
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
    textColor: Color = Blue003,
    hintColor: Color = Grey85,
    fontSize: TextUnit = 20.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    elevation: Dp = 3.dp,
    cornerShape: Shape = RoundedCornerShape(4.dp),
    borderEnabled: Boolean = true,
    borderWidth: Dp = 1.dp,
    borderColor: Color = Blue001,
    backgroundColor: Color = Blue009,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    enabled: Boolean = true,
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

        BasicTextField(
            modifier = textModifier
                //.weight(5f)
                .fillMaxWidth()
                .padding(horizontal = paddingHorizontal, vertical = paddingVertical),
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


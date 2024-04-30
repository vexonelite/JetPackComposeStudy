package com.gmail.vexonelite.jetpack.study.viewmodels


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue003
import com.gmail.vexonelite.jetpack.study.ui.theme.HoloGrayLight


@Composable
fun FmTextField01(
    modifier: Modifier = Modifier,
    initText: String = "",
    hint: String = "wtf",
    paddingHorizontal: Dp = 12.dp,
    paddingVertical: Dp = 12.dp,
    textColor: Color = Blue003,
    fontSize: TextUnit = 20.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    strokeWidth: Dp = 2.dp,
    strokeColor: Color = Blue,
    backgroundColor: Color = Color.White,
) {
    var textState: TextFieldValue by remember {
        mutableStateOf(
            TextFieldValue(text = initText, selection = TextRange(initText.length))
        )
    }

    val focusRequester: FocusRequester = remember { FocusRequester() }

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
                val strokeWidthInPx = strokeWidth.toPx()
                val y = size.height - (strokeWidthInPx / 2f)
                drawLine(
                    strokeColor,
                    start = Offset(0f, y),
                    end = Offset(size.width, y),
                    strokeWidthInPx
                )
            }
            .padding(horizontal = paddingHorizontal, vertical = paddingVertical),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BasicTextField(
            value = textState,
            onValueChange = { textState = it },
            // https://proandroiddev.com/decorating-text-field-in-jetpack-compose-b033ade8ad6
            modifier = Modifier
                .focusRequester(focusRequester)
                // .padding(horizontal = 8.dp, vertical = 16.dp) // margin
                .fillMaxWidth()
                .wrapContentHeight(), // work only for BasicTextField
                //.border(width = 1.5.dp, color = Color.Black, RoundedCornerShape(12.dp)),
                //.border(BorderStroke(1.dp, Color.Transparent), shape = MaterialTheme.shapes.small)
            textStyle = textStyle,
            //colors = theAppTextFieldColor01(), // colors only works for TextField,
        )
    }
}

@Composable
fun FmTextField02(
    modifier: Modifier = Modifier,
    initText: String = "",
    hint: String = "",
    //height: Dp = 40.dp,
    paddingHorizontal: Dp = 12.dp,
    paddingVertical: Dp = 12.dp,
    textColor: Color = Blue003,
    hintColor: Color = HoloGrayLight,
    fontSize: TextUnit = 20.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    elevation: Dp = 3.dp,
    cornerShape: Shape = RoundedCornerShape(0.dp),
    backgroundColor: Color = Color.White,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    enabled: Boolean = true,
) {
    var textState: TextFieldValue by remember {
        mutableStateOf(
            TextFieldValue(text = initText, selection = TextRange(initText.length))
        )
    }

    Row(
        modifier = Modifier
            //.height(height)
            .fillMaxWidth()
            .shadow(elevation = elevation, shape = cornerShape)
            .background(color = backgroundColor, shape = cornerShape),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BasicTextField(
            modifier = modifier
                .weight(5f)
                .fillMaxWidth()
                .padding(horizontal = paddingHorizontal, vertical = paddingVertical),
            value = textState,
            onValueChange = {
                textState = it
            },

            textStyle = TextStyle(
                color = textColor,
                fontSize = fontSize,
                fontWeight = fontWeight
            ),
            decorationBox = { innerTextField ->
                if (textState.text.isEmpty()) {
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
            singleLine = singleLine,
            enabled = enabled,
        )
    }
}

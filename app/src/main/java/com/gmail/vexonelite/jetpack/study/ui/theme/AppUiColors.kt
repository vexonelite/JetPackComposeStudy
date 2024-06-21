package com.gmail.vexonelite.jetpack.study.ui.theme


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardDoubleArrowUp
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue001
import com.gmail.vexonelite.jetpack.study.ui.theme.Grey136
import com.gmail.vexonelite.jetpack.study.ui.theme.HoloBlueDark


fun theBuiltInButtonColor01(): ButtonColors =
    ButtonColors(
        containerColor = Blue002,
        contentColor = Color.White,
        disabledContainerColor = Grey136,
        disabledContentColor = Color.Gray,
    )


@Composable
fun theBuiltInTextFieldColor01(): TextFieldColors =
    TextFieldDefaults.colors(
        focusedTextColor = Blue001,
        focusedLabelColor = Blue001,
        focusedLeadingIconColor = Blue001,
        focusedContainerColor = Blue012,

        unfocusedTextColor = Blue003,
        unfocusedLabelColor = Blue003,
        unfocusedLeadingIconColor = Blue003,
        unfocusedContainerColor = Blue012,

        errorTextColor = Pink001,
        errorLeadingIconColor = Pink001,
        errorLabelColor = Pink001,
        errorContainerColor = Pink80,

        disabledTextColor = Grey85,
        disabledLeadingIconColor = Grey85,
        disabledLabelColor = Grey85,
        disabledContainerColor = Grey94,
    )


@Composable
fun theBuiltInOutlinedTextFieldColor01(): TextFieldColors =
    OutlinedTextFieldDefaults.colors(
        focusedTextColor = Blue001,
        focusedBorderColor = Blue001,
        focusedLabelColor = Blue001,
        focusedLeadingIconColor = Blue001,
        focusedContainerColor = Blue012,

        unfocusedTextColor = Blue003,
        unfocusedBorderColor = Blue003,
        unfocusedLabelColor = Blue003,
        unfocusedLeadingIconColor = Blue003,
        unfocusedContainerColor = Blue012,

        errorTextColor = Pink001,
        errorBorderColor = Pink001,
        errorLeadingIconColor = Pink001,
        errorLabelColor = Pink001,
        errorContainerColor = Pink80,

        disabledTextColor = Grey85,
        disabledBorderColor = Grey85,
        disabledLeadingIconColor = Grey85,
        disabledLabelColor = Grey85,
        disabledContainerColor = Grey94,
    )


@Composable
fun theBuiltInCheckboxColor01(): CheckboxColors =
    CheckboxDefaults.colors().copy(
        checkedCheckmarkColor = Blue002,
        uncheckedCheckmarkColor = Grey27,
        checkedBorderColor = Grey27,
        uncheckedBorderColor = Grey27,
        checkedBoxColor = Color.Transparent,
        //uncheckedBoxColor = Blue009,
    )


@Composable
fun theBuiltInCheckboxColor02(): CheckboxColors =
    CheckboxDefaults.colors().copy(
        checkedCheckmarkColor = Blue004,
        uncheckedCheckmarkColor = Blue002,
        checkedBorderColor = Blue002,
        uncheckedBorderColor = Blue002,
        checkedBoxColor = Color.Transparent,
        //uncheckedBoxColor = Blue009,
    )


@Preview
@Composable
fun DoubleUpCircle01(
    arcColor: Color = Teal200,
    size: Dp = 200.dp,
) {
    Box(
        modifier = Modifier.size(size)
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            drawCircle(
                color = arcColor,
            )
        }

        Icon(
            modifier = Modifier.fillMaxSize(),
            imageVector = Icons.Filled.KeyboardDoubleArrowUp,
            tint = Color.White,
            contentDescription = null,
        )
    }
}


@Preview
@Composable
fun ColorCircle01(
    arcColor: Color = Teal200,
    size: Dp = 200.dp,
) {
    Canvas(
        modifier = Modifier.size(size)
    ) {
        drawCircle(
            color = arcColor,
        )
    }
}


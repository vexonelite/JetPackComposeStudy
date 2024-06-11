package com.gmail.vexonelite.jetpack.study.ui.theme


import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
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
    TextFieldDefaults.colors().copy(
        focusedTextColor = HoloBlueDark,
        unfocusedTextColor = HoloBlueDark,
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
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



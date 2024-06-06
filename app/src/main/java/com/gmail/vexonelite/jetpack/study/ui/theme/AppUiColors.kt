package com.gmail.vexonelite.jetpack.study.ui.theme


import androidx.compose.material3.ButtonColors
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue001
import com.gmail.vexonelite.jetpack.study.ui.theme.Grey136
import com.gmail.vexonelite.jetpack.study.ui.theme.HoloBlueDark


fun theBuiltInButtonColor01(): ButtonColors
    = ButtonColors(
        containerColor = Blue001,
        contentColor = Color.White,
        disabledContainerColor = Grey136,
        disabledContentColor = Color.Gray,
    )


@Composable
fun theBuiltInTextFieldColor01(): TextFieldColors
    = TextFieldDefaults.colors().copy(
        focusedTextColor = HoloBlueDark,
        unfocusedTextColor = HoloBlueDark,
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
    )




package com.gmail.vexonelite.jetpack.study.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue001
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue008
import com.gmail.vexonelite.jetpack.study.ui.theme.Green001
import com.gmail.vexonelite.jetpack.study.ui.theme.Grey136
import com.gmail.vexonelite.jetpack.study.ui.theme.Grey27
import com.gmail.vexonelite.jetpack.study.ui.theme.HoloBlueDark
import com.gmail.vexonelite.jetpack.study.ui.theme.Pink001
import com.gmail.vexonelite.jetpack.study.ui.theme.Pink002


fun theAppButtonColor01(): ButtonColors
    = ButtonColors(
        containerColor = Blue001,
        contentColor = Color.White,
        disabledContainerColor = Grey136,
        disabledContentColor = Color.Gray,
    )


@Composable
fun theAppTextFieldColor01(): TextFieldColors
    = TextFieldDefaults.colors().copy(
        focusedTextColor = HoloBlueDark,
        unfocusedTextColor = HoloBlueDark,
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
    )




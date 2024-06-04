package com.gmail.vexonelite.jetpack.study


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.gmail.vexonelite.jetpack.study.ui.theme.Grey004
import com.gmail.vexonelite.jetpack.study.ui.theme.Grey005
import com.gmail.vexonelite.jetpack.study.ui.theme.Pink001
import com.gmail.vexonelite.jetpack.study.ui.theme.Yellow001


/**
 * [Ref](https://foso.github.io/Jetpack-Compose-Playground/material/radiobutton/)
 */
@Preview
@Composable
fun RadioGroupDemo01() {
    val radioOptions = listOf("A", "B", "C")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1] ) }
    Column {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                        }
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = { onOptionSelected(text) }
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }
    }
}


/**
 * [Ref](https://medium.com/@android-world/jetpack-compose-radio-buttons-712c2b579870)
 */
@Preview
@Composable
fun BasicRadioButtonExample() {
    val selectedOption = remember { mutableStateOf("Option1") }

    Column {
        RadioButton(
            selected = selectedOption.value == "Option1",
            onClick = { selectedOption.value = "Option1" }
        )
        Text("Option 1")

        RadioButton(
            selected = selectedOption.value == "Option2",
            onClick = { selectedOption.value = "Option2" }
        )
        Text("Option 2")
    }
}


@Preview
@Composable
fun CustomRadioButtonExample() {
    val selectedOption = remember { mutableStateOf("Option1") }

    Column {
        RadioButton(
            selected = selectedOption.value == "Option1",
            onClick = { selectedOption.value = "Option1" },
            colors = RadioButtonColors(
                selectedColor = Color.Magenta,
                unselectedColor = Grey004,
                disabledSelectedColor = Grey005,
                disabledUnselectedColor = Grey005,
            )
        )
        Text("Custom Option 1")

        RadioButton(
            selected = selectedOption.value == "Option2",
            onClick = { selectedOption.value = "Option2" },
            colors = RadioButtonColors(
                selectedColor = Color.Cyan,
                unselectedColor = Color.Gray,
                disabledSelectedColor = Grey005,
                disabledUnselectedColor = Grey005,
            )
        )
        Text("Custom Option 2")
    }
}


@Preview
@Composable
fun RadioButtonWithLabelExample() {
    val selectedOption = remember { mutableStateOf("Option1") }

    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = selectedOption.value == "Option1",
            onClick = { selectedOption.value = "Option1" }
        )
        Text("Label for Option 1")
    }
}


@Preview
@Composable
fun RadioGroupExample() {
    val options = listOf("Option1", "Option2", "Option3")
    val selectedOption = remember { mutableStateOf(options[0]) }

    Column {
        options.forEach { option ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selectedOption.value == option,
                    onClick = { selectedOption.value = option }
                )
                Text(option)
            }
        }
    }
}



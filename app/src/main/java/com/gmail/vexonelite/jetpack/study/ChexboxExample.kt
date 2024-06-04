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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
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
import com.gmail.vexonelite.jetpack.study.ui.theme.HoloGreenDark
import com.gmail.vexonelite.jetpack.study.ui.theme.HoloOrangeDark
import com.gmail.vexonelite.jetpack.study.ui.theme.Pink001
import com.gmail.vexonelite.jetpack.study.ui.theme.Yellow001


/**
 * [Ref](https://foso.github.io/Jetpack-Compose-Playground/material/checkbox/)
 */
@Preview
@Composable
fun CheckBoxDemo001() {
    val checkedState = remember { mutableStateOf(true) }
    Checkbox(
        checked = checkedState.value,
        onCheckedChange = { checkedState.value = it }
    )
}


/**
 * [Ref](https://medium.com/@android-world/checkboxes-in-jetpack-compose-a-comprehensive-guide-85df23dbbd68)
 */
@Preview
@Composable
fun BasicCheckboxExample() {
    val checked = remember { mutableStateOf(false) }

    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = checked.value,
                onCheckedChange = { isChecked -> checked.value = isChecked }
            )
            Text("Checkbox is ${if (checked.value) "checked" else "unchecked"}")
        }
    }
}


/**
 * [Ref](https://medium.com/@android-world/checkboxes-in-jetpack-compose-a-comprehensive-guide-85df23dbbd68)
 */
@Preview
@Composable
fun CustomCheckboxExample() {
    val checked = remember { mutableStateOf(false) }

    Column {
        Checkbox(
            checked = checked.value,
            onCheckedChange = { isChecked -> checked.value = isChecked },
            colors = CheckboxDefaults.colors().copy(
                checkedCheckmarkColor = Color.Red,
                uncheckedCheckmarkColor = Color.Green,
                checkedBorderColor = Color.Green,
                uncheckedBorderColor = Color.Green,
                checkedBoxColor = Color.Transparent,
                //uncheckedBoxColor = Color.Yellow,

            )
        )
        Text("Custom checkbox is ${if (checked.value) "checked" else "unchecked"}")
    }
}




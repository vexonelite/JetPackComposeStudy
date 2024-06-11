package com.gmail.vexonelite.jetpack.study

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuDefaults
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue002
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue012
import com.gmail.vexonelite.jetpack.study.ui.theme.Green
import com.gmail.vexonelite.jetpack.study.ui.theme.Grey005
import com.gmail.vexonelite.jetpack.study.ui.theme.Grey85
import com.gmail.vexonelite.jetpack.study.ui.theme.Purple002
import com.gmail.vexonelite.jetpack.study.viewmodels.BuiltInTextField01


@Preview
@Composable
fun DropDownMenuSample01() {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("A", "B", "C", "D", "E", "F")
    val disabledValue = "B"
    var selectedIndex by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.TopStart)) {
        Text(
            items[selectedIndex],
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { expanded = true })
                .background(Color.Gray),
         )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth().background(
                Color.Red)
        ) {
            items.forEachIndexed { index: Int, s: String ->
                DropdownMenuItem(
                    text = {
                        val disabledText = if (s == disabledValue) { " (Disabled)" } else { "" }
                        Text(text = s + disabledText)
                    },
                    onClick = {
                        selectedIndex = index
                        expanded = false
                    },
                )
            }
        }
    }
}


@Preview
@Composable
fun ExposedDropdownMenuSample01() {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }

    val items = listOf("Option 1", "Option 2", "Option 3", "Option 4")

    Column {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            label = { Text("Label") },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                    Modifier.clickable { expanded = !expanded }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items.forEach { label ->
                DropdownMenuItem(
                    text = {
                        Text(text = label)
                    },
                    onClick = {
                        selectedText = label
                        expanded = false
                    }
                )
            }
        }
    }
}


//@Preview
//@Composable
//fun ExposedDropdownMenuSample01() {
//
//    var expanded by remember { mutableStateOf(false) }
//    var selectedText by remember { mutableStateOf(
//        TextFieldValue(text = "", selection = TextRange("".length))
//    ) }
//
//    val items = listOf("Option 1", "Option 2", "Option 3", "Option 4")
//
//    Column {
//        BuiltInTextField01(
//            value = selectedText,
//            onValueChange = { selectedText = it },
//            label = { Text("Label") },
//            trailingIcon = {
//                Icon(
//                    imageVector = Icons.Filled.ArrowDropDown,
//                    contentDescription = null,
//                    Modifier.clickable { expanded = !expanded }
//                )
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .clickable { expanded = !expanded }
//        )
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false },
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            items.forEach { label ->
//                DropdownMenuItem(
//                    text = {
//                        Text(text = label)
//                    },
//                    onClick = {
//                        selectedText = label
//                        expanded = false
//                    }
//                )
//            }
//        }
//    }
//}


@Composable
fun ExposedDropDownMenuWithBasicTextField01() {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }

    val items = listOf("Option 1", "Option 2", "Option 3", "Option 4")

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable { expanded = !expanded }
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                .padding(horizontal = 8.dp, vertical = 12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                BasicTextField(
                    value = selectedText,
                    onValueChange = { selectedText = it },
                    textStyle = TextStyle(fontSize = 16.sp),
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.clickable { expanded = !expanded }
                )
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items.forEach { label ->
                DropdownMenuItem(
                    text = {
                        Text(text = label)
                    },
                    onClick = {
                       selectedText = label
                        expanded = false
                    },
                )
            }
        }
    }
}


@Preview
@Composable
fun BuiltInDropDownMenu01(
    value: TextFieldValue = TextFieldValue(),
    onValueChange: (TextFieldValue) -> Unit = {},
    items: List<String> = listOf("Option 1", "Option 2", "Option 3", "Option 4"),
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

    val initText: String = if (items.isNotEmpty()) { items[0] } else { "" }
    var selectedText: String by remember { mutableStateOf(initText) }


    Column {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            label = { Text("Label") },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                    Modifier.clickable { expanded = !expanded }
                )
            },
            colors =
//            focusedTextColor = focusedTextColor,
//            unfocusedTextColor = unfocusedTextColor,
//            disabledTextColor = disabledTextColor,
//            errorTextColor = errorTextColor,

            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Cyan)
                .clickable { expanded = !expanded },
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
                                    val strokeWidth = dropDownMenuTextUnderlineWidth.toPx()
                                    val y = size.height - (strokeWidth / 2f)
                                    drawLine(
                                        dropDownMenuTextUnderlineColor,
                                        start = Offset(0f, y),
                                        end = Offset(size.width, y),
                                        strokeWidth
                                    )
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
    paddingHorizontal: Dp = 12.dp,
    paddingVertical: Dp = 12.dp,
    textColor: Color = Blue002,
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

    val initText: String = if (items.isNotEmpty()) { items[0] } else { "" }
    var selectedText: String by remember { mutableStateOf(initText) }

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
                        .padding(horizontal = paddingHorizontal, vertical = paddingVertical),
                    value = selectedText,
                    onValueChange = { selectedText = it },
                    textStyle = TextStyle(
                        textAlign = TextAlign.Start,
                        color = textColor,
                        fontSize = fontSize,
                        fontWeight = fontWeight
                    ),
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
                                    val strokeWidth = dropDownMenuTextUnderlineWidth.toPx()
                                    val y = size.height - (strokeWidth / 2f)
                                    drawLine(
                                        dropDownMenuTextUnderlineColor,
                                        start = Offset(0f, y),
                                        end = Offset(size.width, y),
                                        strokeWidth
                                    )
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



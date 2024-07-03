package com.gmail.vexonelite.jetpack.study


import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ChipColors
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue004
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue005
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue012
import com.gmail.vexonelite.jetpack.study.ui.theme.Grey20
import com.gmail.vexonelite.jetpack.study.ui.theme.Grey85
import com.gmail.vexonelite.jetpack.study.ui.theme.Grey94
import java.util.logging.Level
import java.util.logging.Logger


@Preview
@Composable
fun ChipAlls() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        AssistChipExample()

        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        FilterChipExample()

        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        InputChipExample()

        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        SuggestionChipExample()

        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        FlowRowSimpleUsageExample()
    }
}


@Preview
@Composable
fun AssistChipExample() {
    AssistChip(
        onClick = { Log.d("Assist chip", "hello world") },
        label = { Text("Assist chip") },
        leadingIcon = {
            Icon(
                Icons.Filled.Settings,
                contentDescription = "Localized description",
                Modifier.size(AssistChipDefaults.IconSize)
            )
        }
    )
}


@Preview
@Composable
fun FilterChipExample() {
    var selected by remember { mutableStateOf(false) }

    FilterChip(
        onClick = { selected = !selected },
        label = {
            Text("Filter chip")
        },
        selected = selected,
        leadingIcon = if (selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else {
            null
        },
    )
}


@Preview
@Composable
fun InputChipExample(
    text: String = "WTF",
    onDismiss: () -> Unit = {},
) {
    var enabled by remember { mutableStateOf(true) }
    if (!enabled) return

    InputChip(
        onClick = {
            onDismiss()
            enabled = !enabled
        },
        label = { Text(text) },
        selected = enabled,
        avatar = {
            Icon(
                Icons.Filled.Person,
                contentDescription = "Localized description",
                Modifier.size(InputChipDefaults.AvatarSize)
            )
        },
        trailingIcon = {
            Icon(
                Icons.Default.Close,
                contentDescription = "Localized description",
                Modifier.size(InputChipDefaults.AvatarSize)
            )
        },
    )
}


//fun SuggestionChip(
//    onClick: () -> Unit,
//    label: @Composable () -> Unit,
//    modifier: Modifier = Modifier,
//    enabled: Boolean = true,
//    icon: @Composable (() -> Unit)? = null,
//    shape: Shape = SuggestionChipDefaults.shape,
//    colors: ChipColors = SuggestionChipDefaults.suggestionChipColors(),
//    elevation: ChipElevation? = SuggestionChipDefaults.suggestionChipElevation(),
//    border: BorderStroke? = SuggestionChipDefaults.suggestionChipBorder(enabled),
//    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },

@Composable
fun SuggestionChipExample() {
    SuggestionChip(
        onClick = { Log.d("Suggestion chip", "hello world") },
        label = { Text("Suggestion chip") }
    )
}


@Preview
@Composable
fun BuiltInFilterChip01(
    modifier: Modifier = Modifier,
    text: String = "Title",
    selected: Boolean = false,
    enabled: Boolean = true,
    //color = hintColor,
    fontSize: TextUnit = 20.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    chipColors: SelectableChipColors = theBuiltInSelectableChipColors01(),
    borderWidth: Dp = 1.dp,
    borderColor: Color = Grey20,
    selectedBorderColor: Color = Blue005,
    paddingValues: PaddingValues = PaddingValues(horizontal = 4.dp),
    onClick: () -> Unit = {},
) {
    var rememberSelected by remember { mutableStateOf(selected) }

    FilterChip(
        modifier = Modifier
            .then(modifier)
            .padding(paddingValues),
        selected = selected,
        onClick = onClick,
        label = {
            Text(
                text = text,
                //color = hintColor,
                fontSize = fontSize,
                fontWeight = fontWeight,
            )
        },
        colors = chipColors,
        border = FilterChipDefaults.filterChipBorder(
            enabled = enabled,
            selected = selected,
            borderColor = borderColor,
            selectedBorderColor = selectedBorderColor,
        ),
    )
}


@Preview
@Composable
fun BuiltInSuggestionChip01(
    modifier: Modifier = Modifier,
    text: String = "Title",
    //color = hintColor,
    fontSize: TextUnit = 20.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    chipColors: ChipColors = theBuiltInChipColors01(),
    borderWidth: Dp = 1.dp,
    borderColor: Color = Grey20,
    paddingValues: PaddingValues = PaddingValues(horizontal = 4.dp),
    onClick: () -> Unit = {},
) {
    SuggestionChip(
        modifier = Modifier
            .then(modifier)
            .padding(paddingValues),
        onClick = onClick,
        label = {
            Text(
                text = text,
                //color = hintColor,
                fontSize = fontSize,
                fontWeight = fontWeight,
            )
        },
        colors = chipColors,
        border = BorderStroke(width = borderWidth, color = borderColor),
    )
}


@Composable
fun theBuiltInSelectableChipColors01(
    containerColor: Color = Grey94,
    labelColor: Color = Grey85,
    selectedContainerColor: Color = Blue012,
    selectedLabelColor: Color = Blue004,
): SelectableChipColors =
    SelectableChipColors(
        containerColor = containerColor,
        labelColor = labelColor,
        leadingIconColor = Color.Unspecified,
        trailingIconColor = Color.Unspecified,
        disabledContainerColor = Color.Unspecified,
        disabledLabelColor = Color.Unspecified,
        disabledLeadingIconColor = Color.Unspecified,
        disabledTrailingIconColor = Color.Unspecified,
        selectedContainerColor = selectedContainerColor,
        disabledSelectedContainerColor = Color.Unspecified,
        selectedLabelColor = selectedLabelColor,
        selectedLeadingIconColor = Color.Unspecified,
        selectedTrailingIconColor = Color.Unspecified,
    )


@Composable
fun theBuiltInChipColors01(
    containerColor: Color = Grey94,
    labelColor: Color = Grey85,
): ChipColors =
    ChipColors(
        containerColor = containerColor,
        labelColor = labelColor,
        leadingIconContentColor = Color.Unspecified,
        trailingIconContentColor = Color.Unspecified,
        disabledContainerColor = Color.Unspecified,
        disabledLabelColor = Color.Unspecified,
        disabledLeadingIconContentColor = Color.Unspecified,
        disabledTrailingIconContentColor = Color.Unspecified,
    )


@Preview
@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FlowRowSimpleUsageExample() {

    val tagList = remember {
        mutableStateListOf<String>(
            "Price: High to Low",
            "Avg rating: 4+",
            "Free breakfast",
            "Free cancellation",
            "£50 pn"
        )
    }

    val selectedList = remember { mutableStateMapOf<String, String>() }
    Logger.getLogger("FlowRowSimpleUsageExample").log(Level.INFO, "FlowRowSimpleUsageExample() - [${selectedList}]")

    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)) {

        tagList.forEach { tag: String ->
            BuiltInFilterChip01(
                text = tag,
                selected = selectedList.contains(tag),
                onClick = {
                    if (selectedList.contains(tag)) {
                        selectedList.remove(tag)
                    }
                    else {
                        selectedList[tag] = tag
                    }
                }
            )
        }
    }
}




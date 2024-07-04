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
import androidx.compose.material3.HorizontalDivider
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
import com.gmail.vexonelite.jetpack.study.ui.theme.ImmutableObjectList
import com.gmail.vexonelite.jetpack.study.ui.theme.ImmutableObjectMap
import com.gmail.vexonelite.jetpack.study.ui.theme.ImmutableObjectSet
import com.gmail.vexonelite.jetpack.study.ui.theme.Pink40
import com.gmail.vexonelite.jetpack.study.ui.theme.theBuiltInChipColors01
import com.gmail.vexonelite.jetpack.study.ui.theme.theBuiltInSelectableChipColors01
import com.gmail.vexonelite.jetpack.study.viewmodels.BuiltInFilterChip01
import com.gmail.vexonelite.jetpack.study.viewmodels.BuiltInFilterChipGroup01
import com.gmail.vexonelite.jetpack.study.viewmodels.BuiltInSuggestionChipGroup01
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

        HorizontalDivider(
            thickness = 1.dp,
            color = Pink40,
        )

        val items = remember {
            mutableStateListOf<String>(
                "Price: High to Low",
                "Avg rating: 4+",
                "Free breakfast",
                "Free cancellation",
                "£50 pn"
            )
        }

        val selectedItems = remember { mutableStateMapOf<String, String>() }
        Logger.getLogger("ChipAlls").log(Level.INFO, "ChipAlls()")
        BuiltInFilterChipGroup01(
            items = ImmutableObjectList<String>(items),
            selectedItems = ImmutableObjectMap<String, String>(selectedItems),
            onSelectedChanged = { tag: String ->
                if (tag in selectedItems) {
                    selectedItems.remove(tag)
                }
                else { selectedItems[tag] = tag }
                var text = ""
                selectedItems.forEach {
                    text += "${it.value},"
                }
                Logger.getLogger("ChipAlls").log(Level.INFO, "ChipAlls() - selectedItems: [${text}]")
            }
        )

        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        HorizontalDivider(
            thickness = 1.dp,
            color = Pink40,
        )

        BuiltInSuggestionChipGroup01(
            items = ImmutableObjectList<String>(items),
        )

        //FlowRowSimpleUsageExample()

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









package com.gmail.vexonelite.jetpack.study.viewmodels


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ChipColors
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue005
import com.gmail.vexonelite.jetpack.study.ui.theme.Grey20
import com.gmail.vexonelite.jetpack.study.ui.theme.ImmutableObjectList
import com.gmail.vexonelite.jetpack.study.ui.theme.ImmutableObjectMap
import com.gmail.vexonelite.jetpack.study.ui.theme.theBuiltInChipColors01
import com.gmail.vexonelite.jetpack.study.ui.theme.theBuiltInSelectableChipColors01
import java.util.logging.Level
import java.util.logging.Logger


@Preview
@Composable
fun BuiltInFilterChip01(
    modifier: Modifier = Modifier,
    text: String = "Title",
    selected: Boolean = false,
    enabled: Boolean = true,
    fontSize: TextUnit = 20.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    chipColors: SelectableChipColors = theBuiltInSelectableChipColors01(),
    borderWidth: Dp = 1.dp,
    borderColor: Color = Grey20,
    selectedBorderColor: Color = Blue005,
    paddingValues: PaddingValues = PaddingValues(horizontal = 4.dp),
    onClick: () -> Unit = {},
) {
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


@Preview
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BuiltInFilterChipGroup01(
    items: ImmutableObjectList<String> = ImmutableObjectList<String>(
        listOf<String>(
            "Price: High to Low",
            "Avg rating: 4+",
            "Free breakfast",
            "Free cancellation",
            "£50 pn"
        )
    ),
    selectedItems: ImmutableObjectMap<String, String> = ImmutableObjectMap<String, String>(),
    onSelectedChanged: (String) -> Unit = {}
) {
    var text = ""
    selectedItems.objectMap.forEach {
        text += "${it.value},"
    }
    Logger.getLogger("BuiltInFilterChipGroup01").log(Level.INFO, "BuiltInFilterChipGroup01() - selectedItems: [${text}]")

    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
    ) {
        items.objectList.forEach { tag: String ->
            BuiltInFilterChip01(
                text = tag,
                selected = tag in selectedItems.objectMap,
                onClick = { onSelectedChanged(tag) }
            )
        }
    }
}


@Preview
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BuiltInSuggestionChipGroup01(
    items: ImmutableObjectList<String> = ImmutableObjectList<String>(
        listOf<String>(
            "Price: High to Low",
            "Avg rating: 4+",
            "Free breakfast",
            "Free cancellation",
            "£50 pn"
        )
    ),
) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
    ) {
        items.objectList.forEach { tag: String ->
            BuiltInSuggestionChip01(
                text = tag,
            )
        }
    }
}




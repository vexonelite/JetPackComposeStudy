package com.gmail.vexonelite.jetpack.study


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gmail.vexonelite.jetpack.study.ui.theme.Green001
import com.gmail.vexonelite.jetpack.study.ui.theme.Green002
import com.gmail.vexonelite.jetpack.study.ui.theme.Orange
import com.gmail.vexonelite.jetpack.study.ui.theme.Pink001


// https://developer.android.com/develop/ui/compose/components/slider
@Preview
@Composable
fun SliderExamples() {
    Column(modifier = Modifier.padding(all = 10.dp),) {
        var sliderPositionBasic by remember { mutableFloatStateOf(0f) }
        Slider(
            value = sliderPositionBasic,
            onValueChange = { sliderPositionBasic = it }
        )
        Text(text = sliderPositionBasic.toString())

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 6.dp),
            thickness = 1.dp,
            color = Color.Gray,
        )

        var sliderPositionAdvanced by remember { mutableFloatStateOf(0f) }
        Slider(
            modifier = Modifier.padding(all = 10.dp),
            value = sliderPositionAdvanced,
            onValueChange = { sliderPositionAdvanced = it },
            colors = SliderDefaults.colors(
                thumbColor = Pink001,
                activeTrackColor = Green002,
                inactiveTrackColor = Orange,
            ),
            steps = 5,
            valueRange = 0f..50f
        )
        Text(text = sliderPositionAdvanced.toString())

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 6.dp),
            thickness = 1.dp,
            color = Color.Gray,
        )

        var sliderPositionRange by remember { mutableStateOf(0f..100f) }
        RangeSlider(
            modifier = Modifier.padding(all = 10.dp),
            value = sliderPositionRange,
            steps = 5,
            onValueChange = { range -> sliderPositionRange = range },
            valueRange = 0f..100f,
            onValueChangeFinished = {
                // launch some business logic update with the state you hold
                // viewModel.updateSelectedSliderValue(sliderPosition)
            },
        )
        Text(text = sliderPositionRange.toString())
    }
}



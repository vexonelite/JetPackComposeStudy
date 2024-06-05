package com.gmail.vexonelite.jetpack.study


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue003
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue007
import com.gmail.vexonelite.jetpack.study.ui.theme.Grey001
import com.gmail.vexonelite.jetpack.study.ui.theme.Grey004


/**
 * ``checked``: The initial state of the switch.
 * ``onCheckedChange``: A callback that is called when the state of the switch changes.
 * ``enabled``: Whether the switch is enabled or disabled.
 * ``colors``: The colors used for the switch.
 */
@Preview
@Composable
fun SwitchDemo01() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        //horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current

        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp),
            //horizontalArrangement = Arrangement.SpaceBetween
        ) {

            var lchecked by remember { mutableStateOf(true) }
            Switch(
                checked = lchecked,
                onCheckedChange = {
                    lchecked = it
                },
                modifier = Modifier.weight(1 / 2f),
            )

            Spacer(modifier = Modifier.padding(horizontal = 8.dp))

            var rchecked by remember { mutableStateOf(true) }
            Switch(
                checked = rchecked,
                onCheckedChange = {
                    rchecked = it
                },
                thumbContent = if (rchecked) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = null,
                            modifier = Modifier.size(SwitchDefaults.IconSize),
                        )
                    }
                }
                else {
                    null
                },
                modifier = Modifier.weight(1 / 2f),
            )
        }

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp),
            //horizontalArrangement = Arrangement.SpaceBetween
        ) {
            var lchecked by remember { mutableStateOf(true) }
            Switch(
                checked = lchecked,
                onCheckedChange = {
                    lchecked = it
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Blue007,
                    uncheckedThumbColor = Grey004,
                    uncheckedTrackColor = Blue003,
                ),
                modifier = Modifier.weight(1 / 2f),
            )

            Spacer(modifier = Modifier.padding(horizontal = 8.dp))

        }
    }
}


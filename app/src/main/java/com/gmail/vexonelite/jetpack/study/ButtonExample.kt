package com.gmail.vexonelite.jetpack.study


import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue003
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue007
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue008
import com.gmail.vexonelite.jetpack.study.ui.theme.Grey136
import com.gmail.vexonelite.jetpack.study.ui.theme.Grey27
import com.gmail.vexonelite.jetpack.study.ui.theme.Pink002
import com.gmail.vexonelite.jetpack.study.ui.theme.Teal001


/**
 * ``onClick``: The function called when the user presses the button.
 * ``enabled``: When false, this parameter causes the button to appear unavailable and inactive.
 * ``colors``: An instance of ButtonColors that determines the colors used in the button.
 * ``contentPadding``: The padding within the button.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ButtonDemo01() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        //horizontalAlignment = Alignment.CenterHorizontally

    ) {
        val context = LocalContext.current

        val buttonColors = ButtonColors(
            containerColor = Blue008,
            contentColor = Pink002,
            disabledContainerColor = Grey136,
            disabledContentColor = Grey27
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp),
            //horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    Toast.makeText(context, "Hello Button", Toast.LENGTH_LONG).show()
                },
                modifier = Modifier.weight(1 / 2f),
            ) {
                Text("Button")
            }

            Spacer(modifier = Modifier.padding(horizontal = 8.dp))

            FilledTonalButton(
                onClick = {
                    Toast.makeText(context, "Hello Filled Tonal Button", Toast.LENGTH_LONG).show()
                },
                modifier = Modifier.weight(1 / 2f),
            ) {
                Text("Filled Tonal")
            }
        }

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp),
            //horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(
                onClick = {
                    Toast.makeText(context, "Hello Outlined Button", Toast.LENGTH_LONG).show()
                },
                modifier = Modifier.weight(1 / 2f)
            ) {
                Text("Outlined")
            }

            Spacer(modifier = Modifier.padding(horizontal = 8.dp))

            ElevatedButton(
                onClick = {
                    Toast.makeText(context, "Hello Elevated Button", Toast.LENGTH_LONG).show()
                },
                modifier = Modifier.weight(1 / 2f)
            ) {
                Text("Elevated")
            }
        }

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(
                onClick = {
                    Toast.makeText(context, "Hello Text Button", Toast.LENGTH_LONG).show()
                },
                Modifier.weight(1 / 2f)
            ) {
                Text("Text")
            }

            Spacer(modifier = Modifier.padding(horizontal = 8.dp))

            Button(
                onClick = {
                    Toast.makeText(context, "Hello Customized Button 1", Toast.LENGTH_LONG).show()
                },
                modifier = Modifier.weight(1 / 2f),
                enabled = true,
                shape = ButtonDefaults.shape, // | elevatedShape | outlinedShape | textShape
                colors = buttonColors,
            ) {
                Text("Customized1")
            }
        }

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    Toast.makeText(context, "Hello Customized Button 2", Toast.LENGTH_LONG).show()
                },
                modifier = Modifier
                    .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
                    .weight(1 / 2f),
                enabled = true,
                shape = RoundedCornerShape(10.dp), // RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp)
                colors = buttonColors,
            ) {
                Text(
                    text = "Rounded corners 1",
                    Modifier.padding(1.dp)
                )
            }

            Spacer(modifier = Modifier.padding(horizontal = 8.dp))

            Button(
                onClick = {
                    Toast.makeText(context, "Hello Customized Button 2", Toast.LENGTH_LONG).show()
                },
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = Color.Red,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .weight(1 / 2f),
                //enabled = true,
                //colors = buttonColors,
            ) {
                Text(
                    text = "Rounded corners 2",
                    Modifier.padding(10.dp)
                )
            }
        }

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp),
            //horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Surface(
                modifier = Modifier
                    .clickable(
                        onClick = {
                            Toast
                                .makeText(context, "你点击了此文本", Toast.LENGTH_LONG)
                                .show()
                        },
                    )
                    .weight(1 / 2f),
                //shape = MaterialTheme.shapes.small,
                shape = RoundedCornerShape(10.dp),
                color = Pink002,
                //contentColor = Color.White,
                border = BorderStroke(2.dp, Blue007),

                ) {
                Text(
                    text = "Apple",
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(all = 8.dp)
                )
            }

            Spacer(modifier = Modifier.padding(horizontal = 8.dp))

            Text(
                text = "Banana",
                color = Blue003,
                fontSize = 10.sp,
                modifier = Modifier
                    .weight(1 / 2f)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(color = Color.Transparent),
                    ) {

                    }
            )
        }

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        CompositionLocalProvider(
            LocalMinimumTouchTargetEnforcement provides false,
        ) {
            TextButton(
                onClick = {},
                contentPadding = PaddingValues(),
            ) {
                Text(
                    "Button",
                    color = Blue007,
                    fontSize = 10.sp,
                )
            }
        }

    }
}














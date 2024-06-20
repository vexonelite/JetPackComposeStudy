package com.gmail.vexonelite.jetpack.study

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gmail.vexonelite.jetpack.study.ui.theme.Teal200


@Preview
@Composable
fun HalfCircle(
    arcColor: Color = Teal200
) {
    Canvas(
        modifier = Modifier
            .size(200.dp)

    ) {
        drawArc(
            color = arcColor,
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = true
        )
    }
}


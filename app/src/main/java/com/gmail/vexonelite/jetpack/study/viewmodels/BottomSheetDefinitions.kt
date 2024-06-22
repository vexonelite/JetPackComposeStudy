package com.gmail.vexonelite.jetpack.study.viewmodels


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardDoubleArrowUp
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SheetValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gmail.vexonelite.jetpack.study.ui.theme.Teal200
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Preview
@Composable
fun BuiltInBottomSheetDragHandle01(
    dragColor: Color = Teal200,
    iconImageVector: ImageVector = Icons.Filled.KeyboardDoubleArrowUp,
    iconTintColor: Color = Color.White,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(35.dp)
            .clipToBounds(), // Ensure the content is clipped to the box's size
        contentAlignment = Alignment.TopCenter,
    )
    {
        Box(
            modifier = Modifier.wrapContentHeight(align = Alignment.Top, unbounded=true)
        ) {
            Column(
                modifier = Modifier
                    .size(80.dp)
                    .background(color = dragColor, shape = CircleShape),
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = iconImageVector,
                    tint = iconTintColor,
                    contentDescription = null,
                )
            }
        }
    }
}


@Preview
@Composable
fun BuiltInBottomSheetDragHandle02(
    dragColor: Color = Teal200,
    iconImageVector: ImageVector = Icons.Filled.KeyboardDoubleArrowUp,
    iconTintColor: Color = Color.White,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(35.dp)
            .clipToBounds(), // Ensure the content is clipped to the box's size
        contentAlignment = Alignment.TopCenter,
    )
    {
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(
                    color = dragColor,
                    shape = RoundedCornerShape(
                        topStart = 20.dp,
                        topEnd = 20.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                ),
            contentAlignment = Alignment.TopCenter,
        ) {
            Icon(
                modifier = Modifier.size(40.dp),
                imageVector = iconImageVector,
                tint = iconTintColor,
                contentDescription = null,
            )
        }
    }
}


@Preview
@Composable
fun BuiltInBottomSheetDragHandle03(
    dragColor: Color = Teal200,
    iconImageVector: ImageVector = Icons.Filled.KeyboardDoubleArrowUp,
    iconTintColor: Color = Color.White,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(35.dp)
            .clipToBounds(), // Ensure the content is clipped to the box's size
        contentAlignment = Alignment.TopCenter,
    )
    {
        Column(
            modifier = Modifier
                .size(80.dp)
                .background(color = dragColor, shape = CircleShape),
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Icon(
                modifier = Modifier.size(40.dp),
                imageVector = iconImageVector,
                tint = iconTintColor,
                contentDescription = null,
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
fun BottomSheetScaffoldState.dragIconClickerExt01(coroutineScope: CoroutineScope) {
    val bottomSheetScaffoldState = this
    coroutineScope.launch {
        if (bottomSheetScaffoldState.bottomSheetState.targetValue == SheetValue.PartiallyExpanded) {
            bottomSheetScaffoldState.bottomSheetState.expand()
        }
        else {
            bottomSheetScaffoldState.bottomSheetState.partialExpand()
        }
    }
}




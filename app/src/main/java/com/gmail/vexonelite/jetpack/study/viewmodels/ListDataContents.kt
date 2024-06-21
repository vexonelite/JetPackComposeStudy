package com.gmail.vexonelite.jetpack.study.viewmodels


import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gmail.vexonelite.jetpack.study.HolderItemClickDelegate
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue003
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue008
import java.util.logging.Level
import java.util.logging.Logger

import com.gmail.vexonelite.jetpack.study.R


enum class ListDataStateType {
    Init,
    Loading,
    Available,
    Unavailable,
    None,
}


sealed interface ListDataState<T> {
    val theState: ListDataStateType
    val theList: List<T>

    data class Init<T>(
        override val theState: ListDataStateType = ListDataStateType.Loading,
        override val theList: List<T> = listOf(),
    ): ListDataState<T>

    data class Loading<T>(
        override val theState: ListDataStateType = ListDataStateType.Loading,
        override val theList: List<T> = listOf(),
    ): ListDataState<T>

    data class Available<T>(
        override val theState: ListDataStateType = ListDataStateType.Available,
        override val theList: List<T> = listOf(),
    ): ListDataState<T>

    data class Unavailable<T>(
        override val theState: ListDataStateType = ListDataStateType.Unavailable,
        override val theList: List<T> = listOf(),
    ): ListDataState<T>

    data class None<T>(
        override val theState: ListDataStateType = ListDataStateType.None,
        override val theList: List<T> = listOf(),
    ): ListDataState<T>
}


@Composable
fun <T> ListDataContent01(
    listDataState: ListDataState<T> = ListDataState.None(),
    itemClickCallback: HolderItemClickDelegate<T>? = null,
    unavailableOnClick: () -> Unit = {},
    initContent: @Composable () -> Unit = { },
    availableContent: @Composable (ListDataState<T>, HolderItemClickDelegate<T>?) -> Unit = { _, _ -> },
) {
    when(listDataState) {
        is ListDataState.Init -> {
            Logger.getLogger("ListDataContent01").log(Level.INFO, "ListDataContent01 [Init]")
            initContent()
        }
        is ListDataState.Loading -> {
            Logger.getLogger("ListDataContent01").log(Level.INFO, "ListDataContent01 [Loading]")
            ListDataLoading01()
        }
        is ListDataState.Available -> {
            Logger.getLogger("ListDataContent01").log(Level.INFO, "ListDataContent01 [Available]")
            availableContent(listDataState, itemClickCallback)
        }
        is ListDataState.Unavailable -> {
            if (listDataState.theList.isNotEmpty()) {
                Logger.getLogger("ListDataContent01").log(Level.INFO, "ListDataContent01 [Unavailable]")
                ListDataUnavailable01(
                    onClick = unavailableOnClick,
                )
            }
            else { Logger.getLogger("ListDataContent01").log(Level.WARNING, "ListDataContent01 [Unavailable] - listDataState.theList is empty!!") }
        }
        is ListDataState.None -> { Logger.getLogger("ListDataContent01").log(Level.INFO, "ListDataContent01 [None]") }
    }
}


@Preview
@Composable
fun ListDataInitContent01(
    progressColor: Color = Blue003,         // Pink001
    progressTrackColor: Color = Blue008,    // Yellow001
    backgroundColor: Color = Color.White,
) {
    Logger.getLogger("ListDataLoading01").log(Level.INFO, "ListDataLoading01")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor,),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(32.dp),
            color = progressColor,
            trackColor = progressTrackColor,
        )
    }
}


@Preview
@Composable
fun ListDataLoading01(
    progressColor: Color = Blue003,         // Pink001
    progressTrackColor: Color = Blue008,    // Yellow001
    backgroundColor: Color = Color.White,
) {
    Logger.getLogger("ListDataLoading01").log(Level.INFO, "ListDataLoading01")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor,),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(32.dp),
            color = progressColor,
            trackColor = progressTrackColor,
        )
    }
}


@Preview
@Composable
fun ListDataUnavailable01(
    unavailableText: String = "Woos....Unavailable",
    onClick: () -> Unit = {},
    textFontSize: TextUnit = 20.sp,
    textColor: Color = Color.Black,
    backgroundColor: Color = Color.White,
) {
    Logger.getLogger("ListDataUnavailable01").log(Level.INFO, "ListDataLoading01")
    val context: Context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor,),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column( // Child
            modifier = Modifier
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier,
//                    .fillMaxWidth()
//                    .background(Grey80)
//                    .padding(all = 12.dp),
                painter = painterResource(R.drawable.chrome_crash_icon_150x150),
                contentDescription = "",
            )
            Box(
                modifier = Modifier
                    .clickable(onClick = onClick)
                    //itemClickCallback?.onHolderItemClicked(delegate, delegate.theAction, -1)
                    //.background(Green001)
                    .wrapContentSize()
                    .padding(all = 10.dp),
            ) {
                Text(
                    text = unavailableText,
                    fontSize = textFontSize,
                    color = textColor,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    modifier = Modifier,
                    //.fillMaxWidth()
                    //.background(Grey80)
                    //.padding(vertical = 12.dp)
                )
            }
        }
    }
}


//@Preview
@Composable
fun <T> ListDataUnavailable02(
    delegate: T,
    itemClickAction: String = "",
    itemClickCallback: HolderItemClickDelegate<T>? = null,
    textFontSize: TextUnit = 20.sp,
    textColor: Color = Color.Black,
    backgroundColor: Color = Color.White,
) {
    ListDataUnavailable01(
        onClick = {
            itemClickCallback?.onHolderItemClicked(delegate, itemClickAction, -1)
        },
        textFontSize = textFontSize,
        textColor = textColor,
        backgroundColor = backgroundColor,
    )
}



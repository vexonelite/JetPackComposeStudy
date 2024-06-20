package com.gmail.vexonelite.jetpack.study


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gmail.vexonelite.jetpack.study.ui.theme.DoubleUpCircle01
import com.gmail.vexonelite.jetpack.study.ui.theme.ImmutableObjectList
import com.gmail.vexonelite.jetpack.study.ui.theme.Pink002
import kotlinx.coroutines.launch


/**
 * * Ref: [Android Jetpack Compose BottomSheet with Material-3](https://makb.medium.com/mastering-android-jetpack-compose-bottomsheet-with-material-3-e61af75c0cac)
 */

@Composable
fun BottomSheetExample01() {
    var showSheet by remember { mutableStateOf(false) }

    if (showSheet) {
        BottomSheetContent01(
            onDismiss = { showSheet = false }
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            showSheet = true
        }) {
            Text(text = "Show BottomSheet")
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetContent01(onDismiss: () -> Unit = {}) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        CountryList()
    }
}


@Composable
fun CountryList() {
    val countries = listOf<Pair<String, String>>(
        Pair<String, String>("United States", "\uD83C\uDDFA\uD83C\uDDF8"),
        Pair<String, String>("Canada", "\uD83C\uDDE8\uD83C\uDDE6"),
        Pair<String, String>("India", "\uD83C\uDDEE\uD83C\uDDF3"),
        Pair<String, String>("Germany", "\uD83C\uDDE9\uD83C\uDDEA"),
        Pair<String, String>("France", "\uD83C\uDDEB\uD83C\uDDF7"),
        Pair<String, String>("Japan", "\uD83C\uDDEF\uD83C\uDDF5"),
        Pair<String, String>("China", "\uD83C\uDDE8\uD83C\uDDF3"),
        Pair<String, String>("Brazil", "\uD83C\uDDE7\uD83C\uDDF7"),
        Pair<String, String>("Australia", "\uD83C\uDDE6\uD83C\uDDFA"),
        Pair<String, String>("Russia", "\uD83C\uDDF7\uD83C\uDDFA"),
        Pair<String, String>("United Kingdom", "\uD83C\uDDEC\uD83C\uDDE7"),
    )


    val countryList: ImmutableObjectList<Pair<String, String>> = ImmutableObjectList<Pair<String, String>>(countries)

    LazyColumn {
        items(
            items = countryList.objectList,
            key = { countery -> countery.first },
            //contentType = { countery -> countery.contentType },
        ) { (country, flag) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 20.dp)
            ) {
                Text(
                    text = flag,
                    modifier = Modifier.padding(end = 20.dp)
                )
                Text(text = country)
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun BottomSheetExample02() {
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(skipHiddenState = false),
        snackbarHostState = remember { SnackbarHostState() }
    )
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            Box(
                Modifier
                    .background(color = Pink002)
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "This is a bottom sheet", fontSize = 20.sp)
            }
        },
        sheetPeekHeight = 40.dp, // The height of the visible part of the sheet when collapsed
        sheetDragHandle = {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center)
            { DoubleUpCircle01(size = 20.dp) }

        }
    ) {
        // Main content of the scaffold
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Main Content", fontSize = 24.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                coroutineScope.launch {


                    if (scaffoldState.bottomSheetState.isVisible) {
                        scaffoldState.bottomSheetState.partialExpand()
                    }
                    else {
                        scaffoldState.bottomSheetState.expand()
                    }
                }
            }) {
                Text(text = "Toggle Bottom Sheet")
            }
        }
    }
}






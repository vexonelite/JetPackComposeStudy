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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardDoubleArrowUp
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue012
import com.gmail.vexonelite.jetpack.study.ui.theme.ColorCircle01
import com.gmail.vexonelite.jetpack.study.ui.theme.ImmutableObjectList
import com.gmail.vexonelite.jetpack.study.ui.theme.Pink002
import com.gmail.vexonelite.jetpack.study.ui.theme.Teal200
import com.gmail.vexonelite.jetpack.study.viewmodels.BuiltInBottomSheetDragHandle02
import com.gmail.vexonelite.jetpack.study.viewmodels.BuiltInDropDownMenu02
import com.gmail.vexonelite.jetpack.study.viewmodels.BuiltInTextField02
import kotlinx.coroutines.launch
import java.util.logging.Level
import java.util.logging.Logger


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
            BottomSheetExample02SheetContent(
                onClick = {
                    coroutineScope.launch {
                        scaffoldState.bottomSheetState.partialExpand()
                    }
                },
            )
        },
        sheetPeekHeight = 35.dp, // The height of the visible part of the sheet when collapsed
        sheetDragHandle = {
            BuiltInBottomSheetDragHandle02()
        },
        sheetContainerColor = Color.White,
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
                    scaffoldState.bottomSheetState.expand()
//                    if (scaffoldState.bottomSheetState.hasExpandedState) {
//                        scaffoldState.bottomSheetState.partialExpand()
//                    }
//                    else {
//                        scaffoldState.bottomSheetState.expand()
//                    }
                }
            }) {
                Text(text = "Toggle Bottom Sheet")
            }
        }
    }
}



@Preview
@Composable
fun BottomSheetExample02SheetDragHandle01() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        //.padding(start = 10.dp, end = 10.dp, top = 0.dp, bottom = 10.dp),
        contentAlignment = Alignment.TopCenter,
    )
    {
        ColorCircle01(size = 80.dp)
        Icon(
            modifier = Modifier.size(40.dp),
            imageVector = Icons.Filled.KeyboardDoubleArrowUp,
            tint = Color.White,
            contentDescription = null,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 35.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            HorizontalDivider(
                thickness = 45.dp, color = Color.White
            )
        }
    }
}


@Preview
@Composable
fun BottomSheetExample02SheetContent(
    onClick: () -> Unit = {},
) {
    val initText = ""
    var keywordValue: TextFieldValue by remember {
        mutableStateOf(
            TextFieldValue(text = initText, selection = TextRange(initText.length))
        )
    }
    var keywordFocusIndicator by remember { mutableIntStateOf(0) }
    val keywordFocusRequester: FocusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 15.dp, end = 15.dp, top = 0.dp, bottom = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.padding(vertical = 6.dp))

        BuiltInTextField02(
            modifier = Modifier
                .padding(horizontal = 20.dp),
            textValue = keywordValue,
            onValueChange = { newValue: TextFieldValue ->
                val pair: Pair<TextFieldValue, Int> = newValue.builtInTextFieldValueChangeHandler01(true, keywordFocusIndicator)
                keywordFocusIndicator = pair.second
                keywordValue = pair.first
            },
            cornerShape = RoundedCornerShape(4.dp),
            //backgroundColor = Yellow002,
            hint = "Keyword",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done),
            //keyboardActions = KeyboardActions(onNext = { keyboardFocusRequester.requestFocus() }),
            selectAllOnFocus = true,
            onFocusChanged = {
                val pair: Pair<TextFieldValue, Int> = keywordValue.builtInTextFieldFocusChangedHandler01(true, it)
                keywordFocusIndicator = pair.second
                keywordValue = pair.first
            },
            focusRequester = keywordFocusRequester,
        )

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        BuiltInDropDownMenu02(
            items = listOf<String>("AAA", "BBB", "CCC", "DDD", "EEE"),
            hint = "Hint",
        )

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        Button(onClick = onClick,) {
            Text(text = "Search")
        }
    }

}



@Preview
@Composable
fun Top50DpOfChildren() {
    Box(
        modifier = Modifier
            .width(200.dp)
            .height(50.dp)
            //.clipToBounds() // Ensure the content is clipped to the box's size
            .background(Color.Gray) // Just for visibility
    ) {
        // Child content with height greater than the container's height
        Column(
            modifier = Modifier
                .height(120.dp)
                .background(Color.Blue)
        ) {
            // Sample child content
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
                    .background(Color.Red)
            )
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
                    .background(Color.Green)
            )
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
                    .background(Color.Yellow)
            )
        }
    }
}
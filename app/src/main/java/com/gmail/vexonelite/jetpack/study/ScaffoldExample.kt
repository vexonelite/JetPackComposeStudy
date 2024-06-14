package com.gmail.vexonelite.jetpack.study


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gmail.vexonelite.jetpack.study.screens.TextCenterScreenContent
import com.gmail.vexonelite.jetpack.study.ui.theme.Grey94
import com.gmail.vexonelite.jetpack.study.ui.theme.HoloGreenDark
import com.gmail.vexonelite.jetpack.study.ui.theme.HoloOrangeDark
import com.gmail.vexonelite.jetpack.study.ui.theme.HoloPurple
import com.gmail.vexonelite.jetpack.study.ui.theme.ImmutableObjectList
import com.gmail.vexonelite.jetpack.study.ui.theme.Pink40
import com.gmail.vexonelite.jetpack.study.ui.theme.Pink80
import com.gmail.vexonelite.jetpack.study.ui.theme.Teal200
import com.gmail.vexonelite.jetpack.study.viewmodels.BuiltInMixedTabRow01
import com.gmail.vexonelite.jetpack.study.viewmodels.BuiltInScrollableTabRow01
import com.gmail.vexonelite.jetpack.study.viewmodels.BuiltInTabRow01
import com.gmail.vexonelite.jetpack.study.viewmodels.SimpleTabDelegateImpl
import com.gmail.vexonelite.jetpack.study.viewmodels.TabItemDelegate


@Composable
fun ScaffoldExample() {

    var presses by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {

        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Bottom app bar",
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { presses++ }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text =
                """
                    This is an example of a scaffold. It uses the Scaffold composable's parameters to create a screen with a simple top app bar, bottom app bar, and floating action button.

                    It also contains some basic inner content, such as this text.

                    You have pressed the floating action button $presses times.
                """.trimIndent(),
            )
        }
    }
}


/**
 * [Ref](https://medium.com/@munbonecci/how-to-add-tabrow-in-jetpack-compose-ec58473ae655)
 */
@Composable
fun ScaffoldExample02() {
    val titles = listOf<String>("Text", "Draw")
    var tabIndex: Int by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TabRow(
                selectedTabIndex = tabIndex,
                containerColor = Pink80,
                contentColor = HoloPurple, // text color
                divider = {
                    HorizontalDivider(
                        thickness = 3.dp,
                        color = Pink40,
                    )
                },
                indicator = { tabPositions: List<TabPosition> ->
                    // Indicator for the selected tab
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[tabIndex]),
                        height = 6.dp,
                        color = Teal200
                    )
                }
            ) {
                titles.forEachIndexed { index: Int, title: String ->
                    Tab(
                        text = {
                            Text(
                                text = title,
                                fontSize = 26.sp,
                                //color = HoloRedLight, // override  TabRow's contentColor
                            )
                        },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index },
                        selectedContentColor = HoloOrangeDark,
                        unselectedContentColor = HoloGreenDark,
                        modifier = Modifier.background(
                            if (tabIndex == index) { Grey94 } else { Color.Transparent }
                        )
                    )
                }
            }
        }
    ) { paddingValues: PaddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextCenterScreenContent(titles[tabIndex])
        }
    }
}



@Preview
@Composable
fun ScaffoldExample03() {
    val tabItems: List<TabItemDelegate> = listOf(
        SimpleTabDelegateImpl(theIdentifier = "001", theDescription = "John"),
        SimpleTabDelegateImpl(theIdentifier = "002", theDescription = "Mary"),
        SimpleTabDelegateImpl(theIdentifier = "003", theDescription = "Alex"),
        SimpleTabDelegateImpl(theIdentifier = "004", theDescription = "Belt"),
        SimpleTabDelegateImpl(theIdentifier = "005", theDescription = "Nash"),
        SimpleTabDelegateImpl(theIdentifier = "006", theDescription = "Nico"),
        SimpleTabDelegateImpl(theIdentifier = "007", theDescription = "Andy"),
        SimpleTabDelegateImpl(theIdentifier = "008", theDescription = "Roxy"),
        SimpleTabDelegateImpl(theIdentifier = "009", theDescription = "Sain"),
        SimpleTabDelegateImpl(theIdentifier = "010", theDescription = "Fuji"),
    )

    var selectedTabIndex: Int by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            BuiltInMixedTabRow01(

            //BuiltInTabRow01(
                scrollableThreshold = 6,
                selectedTabIndex = selectedTabIndex,
                tabItems = ImmutableObjectList(tabItems).objectList,
                onTabItemClick = { index: Int -> selectedTabIndex = index }
            )
        }
    ) { paddingValues: PaddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TextCenterScreenContent(tabItems[selectedTabIndex].theDescription)
//            when (tabIndex) {
//                0 -> { SimpleText() }
//                1 -> { TabDrawContent() }
//            }
        }
    }
}


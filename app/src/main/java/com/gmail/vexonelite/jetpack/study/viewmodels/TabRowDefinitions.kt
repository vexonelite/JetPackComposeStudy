package com.gmail.vexonelite.jetpack.study.viewmodels


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ScrollableTabRow
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue005
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue012
import com.gmail.vexonelite.jetpack.study.ui.theme.Grey85
import java.util.logging.Level
import java.util.logging.Logger


interface IdentifierDelegate {
    /**
     * @return Object ID
     */
    val theIdentifier: String
}


interface DescriptionDelegate {
    val theDescription: String
}


interface TabItemDelegate : IdentifierDelegate, DescriptionDelegate


data class SimpleTabDelegateImpl(
    override val theIdentifier: String,
    override val theDescription: String
): TabItemDelegate


@Preview
@Composable
fun BuiltInMixedTabRow01(
    scrollableThreshold: Int = 4,
    selectedTabIndex: Int = 0,
    tabItems: List<TabItemDelegate> = listOf(
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
    ),
    selectedContentColor: Color = Blue005,
    unselectedContentColor: Color = Grey85,
    selectedContainerColor: Color = Blue012,
    unselectedContainerColor: Color = Color.White,
    dividerThickness: Dp = 1.dp,
    dividerColor: Color = Grey85,
    indicatorHeight: Dp = 3.dp,
    indicatorColor: Color = Blue005,
    tabTextFontSize: TextUnit = 20.sp,
    edgePadding: Dp = 16.dp,
    onTabItemClick: (Int) -> Unit = {}
) {
    //var selectedTabIndex: Int by remember { mutableIntStateOf(initSelectedIndex) }

    Logger.getLogger("BuiltInMixedTabRow01").log(Level.INFO, "BuiltInMixedTabRow01 - selectedTabIndex[$selectedTabIndex]")

    if (tabItems.size > scrollableThreshold) {
        BuiltInScrollableTabRow01(
            selectedTabIndex = selectedTabIndex,
            tabItems = tabItems,
            selectedContentColor = selectedContentColor,
            unselectedContentColor = unselectedContentColor,
            selectedContainerColor = selectedContainerColor,
            unselectedContainerColor = unselectedContainerColor,
            dividerThickness = dividerThickness,
            dividerColor = dividerColor,
            indicatorHeight = indicatorHeight,
            indicatorColor = indicatorColor,
            tabTextFontSize = tabTextFontSize,
            edgePadding = edgePadding,
            onTabItemClick = onTabItemClick,
        )
    }
    else {
        BuiltInTabRow01(
            selectedTabIndex = selectedTabIndex,
            tabItems = tabItems,
            selectedContentColor = selectedContentColor,
            unselectedContentColor = unselectedContentColor,
            selectedContainerColor = selectedContainerColor,
            unselectedContainerColor = unselectedContainerColor,
            dividerThickness= dividerThickness,
            dividerColor = dividerColor,
            indicatorHeight = indicatorHeight,
            indicatorColor = indicatorColor,
            tabTextFontSize = tabTextFontSize,
            onTabItemClick = onTabItemClick,
        )
    }
}


/**
 * [Ref](https://medium.com/@munbonecci/how-to-add-tabrow-in-jetpack-compose-ec58473ae655)
 */
@Preview
@Composable
fun BuiltInTabRow01(
    selectedTabIndex: Int = 0,
    tabItems: List<TabItemDelegate> = listOf(
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
    ),
    selectedContentColor: Color = Blue005,
    unselectedContentColor: Color = Grey85,
    selectedContainerColor: Color = Blue012,
    unselectedContainerColor: Color = Color.White,
    dividerThickness: Dp = 1.dp,
    dividerColor: Color = Grey85,
    indicatorHeight: Dp = 3.dp,
    indicatorColor: Color = Blue005,
    tabTextFontSize: TextUnit = 20.sp,
    onTabItemClick: (Int) -> Unit = {}
) {
    //var selectedTabIndex: Int by remember { mutableIntStateOf(initSelectedIndex) }

    Logger.getLogger("BuiltInTabRow01").log(Level.INFO, "BuiltInTabRow01 - selectedTabIndex[$selectedTabIndex]")

    TabRow(
        modifier = Modifier.fillMaxWidth(),
        selectedTabIndex = selectedTabIndex,
        containerColor = unselectedContainerColor,
        contentColor = unselectedContentColor, // text color
        divider = {
            HorizontalDivider(
                thickness = dividerThickness,
                color = dividerColor,
            )
        },
        indicator = { tabPositions: List<TabPosition> ->
            // Indicator for the selected tab
            TabRowDefaults.SecondaryIndicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                height = indicatorHeight,
                color = indicatorColor,
            )
        }
    ) {
        tabItems.forEachIndexed { index: Int, tabItem: TabItemDelegate ->
            BuiltInTabContent01(
                selectedTabIndex = selectedTabIndex,
                index = index,
                tabItem = tabItem,
                selectedContentColor = selectedContentColor,
                unselectedContentColor = unselectedContentColor,
                selectedContainerColor = selectedContainerColor,
                unselectedContainerColor = unselectedContainerColor,
                tabTextFontSize = tabTextFontSize,
                onTabItemClick = onTabItemClick,
            )
        }
    }
}


@Preview
@Composable
fun BuiltInScrollableTabRow01(
    selectedTabIndex: Int = 0,
    tabItems: List<TabItemDelegate> = listOf(
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
    ),
    selectedContentColor: Color = Blue005,
    unselectedContentColor: Color = Grey85,
    selectedContainerColor: Color = Blue012,
    unselectedContainerColor: Color = Color.White,
    dividerThickness: Dp = 1.dp,
    dividerColor: Color = Grey85,
    indicatorHeight: Dp = 3.dp,
    indicatorColor: Color = Blue005,
    tabTextFontSize: TextUnit = 20.sp,
    edgePadding: Dp = 16.dp,
    onTabItemClick: (Int) -> Unit = {}
) {
    //var selectedTabIndex: Int by remember { mutableIntStateOf(initSelectedIndex) }

    Logger.getLogger("BuiltInScrollableTabRow01").log(Level.INFO, "BuiltInScrollableTabRow01 - selectedTabIndex[$selectedTabIndex]")

    ScrollableTabRow(
        modifier = Modifier.fillMaxWidth(),
        selectedTabIndex = selectedTabIndex,
        containerColor = unselectedContainerColor,
        contentColor = unselectedContentColor, // text color
        divider = {
            HorizontalDivider(
                thickness = dividerThickness,
                color = dividerColor,
            )
        },
        indicator = { tabPositions: List<TabPosition> ->
            // Indicator for the selected tab
            TabRowDefaults.SecondaryIndicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                height = indicatorHeight,
                color = indicatorColor,
            )
        },
        edgePadding = edgePadding,
    ) {
        tabItems.forEachIndexed { index: Int, tabItem: TabItemDelegate ->
            BuiltInTabContent01(
                selectedTabIndex = selectedTabIndex,
                index = index,
                tabItem = tabItem,
                selectedContentColor = selectedContentColor,
                unselectedContentColor = unselectedContentColor,
                selectedContainerColor = selectedContainerColor,
                unselectedContainerColor = unselectedContainerColor,
                tabTextFontSize = tabTextFontSize,
                onTabItemClick = onTabItemClick,
            )
        }
    }
}


@Preview
@Composable
fun BuiltInTabContent01(
    selectedTabIndex: Int = 0,
    index: Int = 0,
    tabItem: TabItemDelegate = SimpleTabDelegateImpl("000", "ZZZ"),
    selectedContentColor: Color = Blue005,
    unselectedContentColor: Color = Grey85,
    selectedContainerColor: Color = Blue012,
    unselectedContainerColor: Color = Color.White,
    tabTextFontSize: TextUnit = 20.sp,
    onTabItemClick: (Int) -> Unit = {}
) {
    Tab(
        text = {
            Text(
                text = tabItem.theDescription,
                fontSize = tabTextFontSize,
                //color = HoloRedLight, // override  TabRow's contentColor
            )
        },
        selected = selectedTabIndex == index,
        onClick = { onTabItemClick(index) },
        selectedContentColor = selectedContentColor,
        unselectedContentColor = unselectedContentColor,
        modifier = Modifier.background(
            if (selectedTabIndex == index) { selectedContainerColor } else { Color.Transparent }
        )
    )
}



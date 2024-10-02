package com.gmail.vexonelite.jetpack.study.viewmodels


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.NextPlan
import androidx.compose.material.icons.automirrored.filled.Outbound
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.KeyboardDoubleArrowUp
import androidx.compose.material.icons.filled.LeakAdd
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Pending
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlaylistAddCheck
import androidx.compose.material.icons.filled.PlaylistRemove
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.gmail.vexonelite.jetpack.study.ui.theme.Blue001


class MaterialFilledIcons{
    fun theArrowBack(): ImageVector = Icons.AutoMirrored.Filled.ArrowBack
    fun theNextPlan(): ImageVector = Icons.AutoMirrored.Filled.NextPlan
    fun theOutbound(): ImageVector = Icons.AutoMirrored.Filled.Outbound
    fun theAdd(): ImageVector = Icons.Filled.Add
    fun theCheckCircle(): ImageVector = Icons.Filled.CheckCircle
    fun theCircle(): ImageVector = Icons.Filled.Circle
    fun theClear(): ImageVector = Icons.Filled.Clear
    fun theClose(): ImageVector = Icons.Filled.Close
    fun theDone(): ImageVector = Icons.Filled.Done
    fun theEditNote(): ImageVector = Icons.Filled.EditNote
    fun theKeyboardDoubleArrowUp(): ImageVector = Icons.Filled.KeyboardDoubleArrowUp
    fun theLeakAdd(): ImageVector = Icons.Filled.LeakAdd
    fun theMenu(): ImageVector = Icons.Filled.Menu
    fun thePending(): ImageVector = Icons.Filled.Pending
    fun thePerson(): ImageVector = Icons.Filled.Person
    fun thePlaylistAddCheck(): ImageVector = Icons.Filled.PlaylistAddCheck
    fun thePlaylistRemove(): ImageVector = Icons.Filled.PlaylistRemove
    fun theRadioButtonChecked(): ImageVector = Icons.Filled.RadioButtonChecked
    fun theRadioButtonUnchecked(): ImageVector = Icons.Filled.RadioButtonUnchecked
    fun theRemoveCircle(): ImageVector = Icons.Filled.RemoveCircle
    fun theRemoveCircleOutline(): ImageVector = Icons.Filled.RemoveCircleOutline
    fun theSave(): ImageVector = Icons.Filled.Save
    fun theSearch(): ImageVector = Icons.Filled.Search
    fun theSettings(): ImageVector = Icons.Filled.Settings
    fun theShare(): ImageVector = Icons.Filled.Share

}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun BuiltInTopAppBarScreenContent(
    topAppBarTitle: String = "Test",
    topAppBarContainerColor: Color = Blue001,
    topAppBarTitleContentColor: Color = Color.White,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    bodyContent: @Composable () -> Unit = {},
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        TopAppBar(
            title = { Text(text = topAppBarTitle) },
            //backgroundColor =  MaterialTheme.colors.primarySurface,
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = topAppBarContainerColor,
                titleContentColor = topAppBarTitleContentColor,
            ),
            navigationIcon = navigationIcon,
//            {
//                IconButton(onClick = {/* Do Something*/ }) {
//                    Icon(
//                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                        contentDescription = null,
//                    )
//                }
//            },
            actions = actions,
//            {
//                IconButton(onClick = {/* Do Something*/ }) {
//                    Icon(Icons.Filled.Share, null)
//                }
//                IconButton(onClick = {/* Do Something*/ }) {
//                    Icon(Icons.Filled.Settings, null)
//                }
//                IconButton(onClick = { /* do something */ }) {
//                    Icon(
//                        imageVector = Icons.Filled.Menu,
//                        contentDescription = "Localized description"
//                    )
//                }
//            },
        )

        bodyContent()
    }
}


@Preview
@Composable
fun TopAppBarNavigation01(
    imageVector: ImageVector = Icons.Filled.Menu,
    tint: Color = Color.White,
    onClick: () -> Unit = {},
) {
    IconButton(
        onClick = onClick,
    ) {
        Icon(imageVector = imageVector, tint = tint, contentDescription = "")
    }
}


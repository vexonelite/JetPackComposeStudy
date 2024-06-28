package com.gmail.vexonelite.jetpack.study.viewmodels


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Outbound
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Keyboard
import androidx.compose.material.icons.filled.KeyboardDoubleArrowUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Pending
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material.icons.filled.RemoveCircleOutline
import androidx.compose.material.icons.filled.Save
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
    fun theSettings(): ImageVector = Icons.Filled.Settings
    fun theShare(): ImageVector = Icons.Filled.Share
    fun theMenu(): ImageVector = Icons.Filled.Menu
    fun theCheckCircle(): ImageVector = Icons.Filled.CheckCircle
    fun thePending(): ImageVector = Icons.Filled.Pending
    fun theRemoveCircleOutline(): ImageVector = Icons.Filled.RemoveCircleOutline
    fun theOutbound(): ImageVector = Icons.AutoMirrored.Filled.Outbound
    fun theCircle(): ImageVector = Icons.Filled.Circle
    fun theRadioButtonUnchecked(): ImageVector = Icons.Filled.RadioButtonUnchecked
    fun theRadioButtonChecked(): ImageVector = Icons.Filled.RadioButtonChecked
    fun theSave(): ImageVector = Icons.Filled.Save
    fun theKeyboardDoubleArrowUp(): ImageVector = Icons.Filled.KeyboardDoubleArrowUp
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun BuildInTopAppBarScreenContent(
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


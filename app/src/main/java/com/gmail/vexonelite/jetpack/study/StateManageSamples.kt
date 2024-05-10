package com.gmail.vexonelite.jetpack.study


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update



/**
 * * [Managing UI States in Jetpack Compose](https://medium.com/@rowaido.game/managing-ui-states-in-jetpack-compose-7eb15e4f6931)
 */
@Composable
fun UiStateSample1(modifier: Modifier = Modifier) {
    // This approach won't work as intended
    var count = 0
    UiStateSample1Core(
        count = count,
        onClick = {
            count++
            println(count)
        }
    )
}


@Composable
fun UiStateSample1Rev(modifier: Modifier = Modifier) {
    // This approach will work
    var count by remember { mutableStateOf(0) }
    UiStateSample1Core(
        count = count,
        onClick = {
            count++
            println(count)
        }
    )
}

@Composable
fun UiStateSample1Core(
    modifier: Modifier = Modifier,
    count: Int,
    onClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "$count",
            fontSize = 32.sp
        )
        Button(
            onClick = onClick
        ) {
            Text(
                text = "Count Up",
                fontSize = 32.sp
            )
        }
    }
}

///

// SampleUiState.kt
data class SampleUiState(
    val count: Int = 0,
    val switchState: Boolean = false
)


//SampleAppViewModel.kt
class SampleAppViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(SampleUiState())
    val uiState: StateFlow<SampleUiState> = _uiState.asStateFlow()

    fun countUp() {
        _uiState.update { currentState ->
            currentState.copy(
                count = currentState.count + 1
            )
        }
    }

    fun toggleSwitchState() {
        _uiState.update { currentState ->
            currentState.copy(
                switchState = !currentState.switchState
            )
        }
    }
}

// SampleComposables.kt
@Composable
fun ComposableA(
    modifier: Modifier = Modifier,
    uiState: SampleUiState,
    viewModel: SampleAppViewModel = viewModel(),
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Button(
            onClick = { viewModel.countUp() },
        ) {
            Text(
                text = "Count Up",
                fontSize = 24.sp
            )
        }
        Switch(
            checked = uiState.switchState,
            onCheckedChange = { viewModel.toggleSwitchState() })
    }
}


@Composable
fun ComposableB(
    modifier: Modifier = Modifier,
    uiState: SampleUiState,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(text = "${uiState.count}", fontSize = 36.sp)
    }
}


@Composable
fun ComposableC(
    modifier: Modifier = Modifier,
    uiState: SampleUiState,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(text = "${uiState.switchState}", fontSize = 36.sp)
    }
}


// Home.kt
@Composable
fun SampleManageHome1(
    modifier: Modifier = Modifier,
    viewModel: SampleAppViewModel = viewModel()
) {
    // Enable to use UiState
    val uiState by viewModel.uiState.collectAsState()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        ComposableA(
            uiState = uiState,
            viewModel = viewModel,
            modifier = modifier.weight(1F)
        )
        ComposableB(
            uiState = uiState,
            modifier = modifier.weight(1F)
        )
        ComposableC(
            uiState = uiState,
            modifier = modifier.weight(1F)
        )
    }
}



package com.gmail.vexonelite.jetpack.study.ui.theme


import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.vexonelite.jetpack.study.ui.theme.DarkerGray
import com.gmail.vexonelite.jetpack.study.ui.theme.HoloBlueLight
import com.gmail.vexonelite.jetpack.study.ui.theme.HoloGreenLight
import com.gmail.vexonelite.jetpack.study.ui.theme.HoloOrangeLight
import com.gmail.vexonelite.jetpack.study.ui.theme.HoloPurple
import com.gmail.vexonelite.jetpack.study.ui.theme.HoloRedLight
//import kotlinx.collections.immutable.ImmutableList
//import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import java.util.logging.Level
import java.util.logging.Logger


// Generic Immutable Object List
@Immutable
data class ImmutableObjectList<T>(
    val objectList: List<T> = listOf() // List = Unstable or Article = Unstable
)




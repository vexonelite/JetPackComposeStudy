package com.gmail.vexonelite.jetpack.study.ntmofa


import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.gmail.vexonelite.jetpack.study.R
import com.gmail.vexonelite.jetpack.study.screens.TextCenterScreenContent
import com.gmail.vexonelite.jetpack.study.ui.theme.Grey001
import com.gmail.vexonelite.jetpack.study.ui.theme.JetPackComposeStudyTheme
import com.gmail.vexonelite.jetpack.study.ui.theme.Pink001
import com.gmail.vexonelite.jetpack.study.ui.theme.Purple003
import com.gmail.vexonelite.jetpack.study.ui.theme.Yellow001
import com.gmail.vexonelite.jetpack.study.viewmodels.BuiltInProgressDialog01
import com.gmail.vexonelite.jetpack.study.viewmodels.BuiltInSingleActionDialog01
import com.gmail.vexonelite.jetpack.study.viewmodels.BuiltInTwinActionsDialog01
import com.gmail.vexonelite.jetpack.study.viewmodels.BuiltInUiStateViewModel
import com.gmail.vexonelite.jetpack.study.viewmodels.SignUpViewModel
import java.util.logging.Level
import java.util.logging.Logger


object NtmofaRoute {
    const val LOGIN = "route_login"
    const val MENU = "route_menu"
    const val FUNC1 = "route_func1"
    const val FUNC2 = "route_func2"
    const val FUNC3 = "route_func3"
    const val FUNC4 = "route_func4"
    const val FUNC5 = "route_func5"
    const val FUNC6 = "route_func6"
}


sealed interface NtmofaRouteDestination {
    val theRoute: String
    val theTitle: String
    val theColor: Color

    data object Login: NtmofaRouteDestination {
        override val theRoute: String = NtmofaRoute.LOGIN
        override val theTitle: String = "Login"
        override val theColor: Color = Purple003
    }

    data object Menu: NtmofaRouteDestination {
        override val theRoute: String = NtmofaRoute.MENU
        override val theTitle: String = "Menu"
        override val theColor: Color = Grey001
    }

    data object Query: NtmofaRouteDestination {
        override val theRoute: String = NtmofaRoute.FUNC1
        override val theTitle: String = "Function1"
        override val theColor: Color = Pink001
    }

    data object Location: NtmofaRouteDestination {
        override val theRoute: String = NtmofaRoute.FUNC2
        override val theTitle: String = "Function2"
        override val theColor: Color = Yellow001
    }

    data object Inventory: NtmofaRouteDestination {
        override val theRoute: String = NtmofaRoute.FUNC3
        override val theTitle: String = "Function3"
        override val theColor: Color = Yellow001
    }

    data object Loan: NtmofaRouteDestination {
        override val theRoute: String = NtmofaRoute.FUNC4
        override val theTitle: String = "Function4"
        override val theColor: Color = Yellow001
    }

    data object Storage: NtmofaRouteDestination {
        override val theRoute: String = NtmofaRoute.FUNC5
        override val theTitle: String = "Function5"
        override val theColor: Color = Yellow001
    }

    data object RfidTag: NtmofaRouteDestination {
        override val theRoute: String = NtmofaRoute.FUNC6
        override val theTitle: String = "Function6"
        override val theColor: Color = Yellow001
    }
}


@Composable
fun Function1Screen(
    navController: NavHostController,
) {
    val context = LocalContext.current
    val title = context.getString(R.string.menu_func1)
    TextCenterScreenContent(title)
}


@Composable
fun Function2Screen(
    navController: NavHostController,
) {
    val context = LocalContext.current
    val title = context.getString(R.string.menu_func2)
    TextCenterScreenContent(title)
}


@Composable
fun Function3Screen(
    navController: NavHostController,
) {
    val context = LocalContext.current
    val title = context.getString(R.string.menu_func3)
    TextCenterScreenContent(title)
}


@Composable
fun Function4Screen(
    navController: NavHostController,
) {
    val context = LocalContext.current
    val title = context.getString(R.string.menu_func4)
    TextCenterScreenContent(title)
}


@Composable
fun Function5Screen(
    navController: NavHostController,
) {
    val context = LocalContext.current
    val title = context.getString(R.string.menu_func5)
    TextCenterScreenContent(title)
}


@Composable
fun Function6Screen(
    navController: NavHostController,
) {
    val context = LocalContext.current
    val title = context.getString(R.string.menu_func6)
    TextCenterScreenContent(title)

}


@Composable
fun NtmofaRfidNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = NtmofaRouteDestination.Login.theRoute,
        modifier = modifier
    ) {
        composable(route = NtmofaRouteDestination.Login.theRoute) {
            LegacyNtmofaLoginScreen(navController = navController,)
        }
        composable(NtmofaRouteDestination.Menu.theRoute) {
            NtmofaMenuScreen(navController = navController,)
        }
        composable(route = NtmofaRouteDestination.Query.theRoute) {
            Function1Screen(navController = navController)
        }
        composable(route = NtmofaRouteDestination.Location.theRoute) {
            Function2Screen(navController = navController)
        }
        composable(route = NtmofaRouteDestination.Inventory.theRoute) {
            Function3Screen(navController = navController)
        }
        composable(route = NtmofaRouteDestination.Loan.theRoute) {
            Function4Screen(navController = navController)
        }
        composable(route = NtmofaRouteDestination.Storage.theRoute) {
            Function5Screen(navController = navController)
        }
        composable(route = NtmofaRouteDestination.RfidTag.theRoute) {
            Function6Screen(navController = navController)
        }
    }
}


@Composable
fun NtmofaRfidApp(
    navController: NavHostController,
) {
    JetPackComposeStudyTheme {
        val screenList = listOf<NtmofaRouteDestination>(
            NtmofaRouteDestination.Login,
            NtmofaRouteDestination.Menu,
            NtmofaRouteDestination.Query,
            NtmofaRouteDestination.Location,
            NtmofaRouteDestination.Inventory,
            NtmofaRouteDestination.Loan,
            NtmofaRouteDestination.Storage,
            NtmofaRouteDestination.RfidTag,
        )
        val screens = remember { mutableStateOf(screenList) }

        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination: NavDestination? = currentBackStack?.destination
        val currentScreen =
            screens.value.find { it.theRoute == currentDestination?.route } ?: NtmofaRouteDestination.Login

        NtmofaRfidNavHost(
            navController = navController,
            modifier = Modifier.padding(all = 4.dp)
        )

        val builtInUiStateViewModel: BuiltInUiStateViewModel = viewModel()

        val progressDialogState by builtInUiStateViewModel.progressDialogState.collectAsState()
        val singleActionDialogState by builtInUiStateViewModel.singleActionDialogState.collectAsState()
        val twinActionsDialogState by builtInUiStateViewModel.twinActionsDialogState.collectAsState()

        Logger.getLogger("NtmofaRfidApp").log(Level.INFO, "progressDialogState: [${progressDialogState.theDialogType}, ${progressDialogState.theDialogState}]")
        Logger.getLogger("NtmofaRfidApp").log(Level.INFO, "singleActionDialogState: [${singleActionDialogState.theDialogType}, ${singleActionDialogState.theDialogState}]")
        Logger.getLogger("NtmofaRfidApp").log(Level.INFO, "twinActionsDialogState: [${twinActionsDialogState.theDialogType}, ${twinActionsDialogState.theDialogState}]")

        BuiltInProgressDialog01(progressDialogState,)
        BuiltInSingleActionDialog01(singleActionDialogState,)
        BuiltInTwinActionsDialog01(twinActionsDialogState,)
    }
}



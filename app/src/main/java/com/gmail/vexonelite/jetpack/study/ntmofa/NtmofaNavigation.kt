package com.gmail.vexonelite.jetpack.study.ntmofa


import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.gmail.vexonelite.jetpack.study.HolderItemClickDelegate
import com.gmail.vexonelite.jetpack.study.R
import com.gmail.vexonelite.jetpack.study.screens.LoginScreenContent
import com.gmail.vexonelite.jetpack.study.screens.MenuScreenContent
import com.gmail.vexonelite.jetpack.study.screens.TextCenterScreenContent
import com.gmail.vexonelite.jetpack.study.ui.theme.DarkerGray
import com.gmail.vexonelite.jetpack.study.ui.theme.Grey001
import com.gmail.vexonelite.jetpack.study.ui.theme.JetPackComposeStudyTheme
import com.gmail.vexonelite.jetpack.study.ui.theme.Pink001
import com.gmail.vexonelite.jetpack.study.ui.theme.Purple003
import com.gmail.vexonelite.jetpack.study.ui.theme.Yellow001
import com.gmail.vexonelite.jetpack.study.ui.theme.navigateSingleTopTo
import com.gmail.vexonelite.jetpack.study.ui.theme.navigateToExt
import com.gmail.vexonelite.jetpack.study.viewmodels.ImmutableObjectList
import com.gmail.vexonelite.jetpack.study.viewmodels.MenuItemContentType
import com.gmail.vexonelite.jetpack.study.viewmodels.MenuItemModel
import java.util.UUID
import java.util.logging.Level
import java.util.logging.Logger


object NtmofaRoute {
    const val LOGIN = "route_login"
    const val MENU = "route_menu"
    const val QUERY = "route_query"
    const val LOCATION = "route_locate"
    const val INVENTORY = "route_inventory"
    const val LOAN = "route_loan"
    const val STORAGE = "route_storage"
    const val RFID_TAG = "route_rfid_tag"
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
        override val theRoute: String = NtmofaRoute.QUERY
        override val theTitle: String = "Query"
        override val theColor: Color = Pink001
    }

    data object Location: NtmofaRouteDestination {
        override val theRoute: String = NtmofaRoute.LOCATION
        override val theTitle: String = "Location"
        override val theColor: Color = Yellow001
    }

    data object Inventory: NtmofaRouteDestination {
        override val theRoute: String = NtmofaRoute.INVENTORY
        override val theTitle: String = "Inventory"
        override val theColor: Color = Yellow001
    }

    data object Loan: NtmofaRouteDestination {
        override val theRoute: String = NtmofaRoute.LOAN
        override val theTitle: String = "Loan"
        override val theColor: Color = Yellow001
    }

    data object Storage: NtmofaRouteDestination {
        override val theRoute: String = NtmofaRoute.STORAGE
        override val theTitle: String = "Storage"
        override val theColor: Color = Yellow001
    }

    data object RfidTag: NtmofaRouteDestination {
        override val theRoute: String = NtmofaRoute.RFID_TAG
        override val theTitle: String = "RfidTag"
        override val theColor: Color = Yellow001
    }
}


@Composable
fun NtmofaLoginScreen(
    navController: NavHostController,
) {
    LoginScreenContent(
        onLoginButtonClick = {
            //navController.navigate(NtmofaRouteDestination.Menu.theRoute)
            navController.navigateToExt(NtmofaRouteDestination.Menu.theRoute, clearBackStack = true)
        },
    )
}


object NtmofaMenuAction {
    const val QUERY = "menu_action_query"
    const val LOCATE = "menu_action_locate"
    const val INVENTORY = "menu_action_inventory"
    const val LOAN = "menu_action_loan"
    const val STORAGE = "menu_action_storage"
    const val RFID_TAG = "menu_action_rfid_tag"
}


fun generateType3List(context: Context): List<MenuItemModel> {
    val dataList: MutableList<MenuItemModel> = mutableListOf()

    val uuid = UUID.randomUUID().toString()
    val descriptionArray = arrayOf<String>(
        context.getString(R.string.menu_query),
        context.getString(R.string.menu_locate),
        context.getString(R.string.menu_inventory),
        context.getString(R.string.menu_loan),
        context.getString(R.string.menu_storage),
        context.getString(R.string.menu_rfid_tag),
    )
    val imageResIdArray = arrayOf<Int>(
        R.drawable.ic_query,
        R.drawable.ic_locate,
        R.drawable.ic_inventory,
        R.drawable.ic_loan,
        R.drawable.ic_storage,
        R.drawable.transponder_256,
    )
    val actionArray = arrayOf<String>(
        NtmofaMenuAction.QUERY,
        NtmofaMenuAction.LOCATE,
        NtmofaMenuAction.INVENTORY,
        NtmofaMenuAction.LOAN,
        NtmofaMenuAction.STORAGE,
        NtmofaMenuAction.RFID_TAG
    )

    for (i in imageResIdArray.indices) {
        val identifier = "${uuid}_${i + 1}"
        val delegate = MenuItemModel(
            id = identifier,
            description = descriptionArray[i],
            contentType = MenuItemContentType.TYPE3,
            action = actionArray[i],
            color = DarkerGray,
            imageResId = imageResIdArray[i],
        )
        dataList.add(delegate)
    }

    return dataList
}


@Composable
fun NtmofaMenuScreen(
    navController: NavHostController,
) {
    val itemClickCallback = HolderItemClickDelegate<MenuItemModel> { dataObject, action, position ->
        Logger.getLogger("NtmofaMenuScreen").log(Level.INFO, "itemClickCallback - delegate: [${dataObject.description}], action: [$action], position: [$position]")
        when(action) {
            NtmofaMenuAction.QUERY -> {
                navController.navigateSingleTopTo(
                    NtmofaRouteDestination.Query.theRoute,
                    NtmofaRouteDestination.Menu.theRoute
                )
            }
            NtmofaMenuAction.LOCATE -> {
                navController.navigateSingleTopTo(
                    NtmofaRouteDestination.Location.theRoute,
                    NtmofaRouteDestination.Menu.theRoute
                )
            }
            NtmofaMenuAction.INVENTORY -> {
                navController.navigateSingleTopTo(
                    NtmofaRouteDestination.Inventory.theRoute,
                    NtmofaRouteDestination.Menu.theRoute
                )
            }
            NtmofaMenuAction.LOAN -> {
                navController.navigateSingleTopTo(
                    NtmofaRouteDestination.Loan.theRoute,
                    NtmofaRouteDestination.Menu.theRoute
                )
            }
            NtmofaMenuAction.STORAGE -> {
                navController.navigateSingleTopTo(
                    NtmofaRouteDestination.Storage.theRoute,
                    NtmofaRouteDestination.Menu.theRoute
                )
            }
            NtmofaMenuAction.RFID_TAG -> {
                navController.navigateSingleTopTo(
                    NtmofaRouteDestination.RfidTag.theRoute,
                    NtmofaRouteDestination.Menu.theRoute
                )
            }
        }
    }

    val context = LocalContext.current
    val menuItemList = ImmutableObjectList<MenuItemModel>(generateType3List(context))
    MenuScreenContent(menuItemList = menuItemList, itemClickCallback = itemClickCallback)
}


@Composable
fun QueryScreen(
    navController: NavHostController,
) {
    val context = LocalContext.current
    val title = context.getString(R.string.menu_query)
    TextCenterScreenContent(title)
}

@Composable
fun LocationScreen(
    navController: NavHostController,
) {
    val context = LocalContext.current
    val title = context.getString(R.string.menu_locate)
    TextCenterScreenContent(title)
}

@Composable
fun InventoryScreen(
    navController: NavHostController,
) {
    val context = LocalContext.current
    val title = context.getString(R.string.menu_inventory)
    TextCenterScreenContent(title)
}

@Composable
fun LoanScreen(
    navController: NavHostController,
) {
    val context = LocalContext.current
    val title = context.getString(R.string.menu_loan)
    TextCenterScreenContent(title)
}

@Composable
fun StorageScreen(
    navController: NavHostController,
) {
    val context = LocalContext.current
    val title = context.getString(R.string.menu_storage)
    TextCenterScreenContent(title)
}

@Composable
fun RfidTagScreen(
    navController: NavHostController,
) {
    val context = LocalContext.current
    val title = context.getString(R.string.menu_rfid_tag)
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
            NtmofaLoginScreen(navController = navController,)
        }
        composable(NtmofaRouteDestination.Menu.theRoute) {
            NtmofaMenuScreen(navController = navController,)
        }
        composable(route = NtmofaRouteDestination.Query.theRoute) {
            QueryScreen(navController = navController)
        }
        composable(route = NtmofaRouteDestination.Location.theRoute) {
            LocationScreen(navController = navController)
        }
        composable(route = NtmofaRouteDestination.Inventory.theRoute) {
            InventoryScreen(navController = navController)
        }
        composable(route = NtmofaRouteDestination.Loan.theRoute) {
            LoanScreen(navController = navController)
        }
        composable(route = NtmofaRouteDestination.Storage.theRoute) {
            StorageScreen(navController = navController)
        }
        composable(route = NtmofaRouteDestination.RfidTag.theRoute) {
            RfidTagScreen(navController = navController)
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
    }
}


